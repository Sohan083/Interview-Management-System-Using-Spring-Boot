package controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import formbean.ApplicantForm;
import formbean.LoginForm;
import model.Applicant;
import model.Employee;
import repository.ApplicantRepository;
import repository.EmployeeRepository;


@Controller
public class WebController
{
    @Autowired 
    EmployeeRepository employeeRepo;
    
    @Autowired
    ApplicantRepository applicantRepo;
    
    @RequestMapping(value= {"/"})
    public String home(Model model)
    {
    	return "home";
    }
    
    @RequestMapping(value= {"/login"})
    public String login(Model model)
    {
    	LoginForm lf = new LoginForm();
    	model.addAttribute("loginForm",lf);
    	
    	return "login";
    }
    
    //for validating loging info
    @RequestMapping(value= {"/loginValidate"},method = RequestMethod.POST)
    public String validateLogin(Model model, //
            @ModelAttribute("loginForm") @Validated LoginForm loginForm, //
            BindingResult result, //
            final RedirectAttributes redirectAttributes)
    {
    	if (result.hasErrors()) {
            return "login";
         }
    	System.out.println(loginForm.getName()+" "+loginForm.getPass());
    	Employee emp = employeeRepo.findByNamePass(loginForm.getName(), loginForm.getPass());
    	if(emp == null) return "login";
    	return "redirect:/applicantlevel1";
    }
    
    
    // for reseting database 
    @RequestMapping(value= {"/reset"})
    public String reset(Model model)
    {
    	employeeRepo.alterTable();
    	Employee emp = new Employee(1,"a","1234");
    	employeeRepo.saveAndFlush(emp);
    	
    	applicantRepo.alterTable();
    	return "redirect:/";
    }
 
    
    
    // for showing employee
    @RequestMapping(value={"/employee"})
    public String create(Model model)
    {
        model.addAttribute("employee", employeeRepo.findAll());
        return "employeelist";
    }
    
    @RequestMapping(value= {"/register"})
    public String register(Model model)
    {
    	ApplicantForm app = new ApplicantForm();
    	model.addAttribute("applicantForm",app);
    	
    	return "applicantRegister";
    }
    
    // for saving the applicant
    @RequestMapping(value={"/applicantRegister"},method = RequestMethod.POST)
    public String applicantRegister(Model model, //
            @ModelAttribute("applicantForm") @Validated ApplicantForm applicantForm, //
            BindingResult result, //
            final RedirectAttributes redirectAttributes)
    {
    	int c = applicantRepo.findAllCount();
    	Applicant app = new Applicant(c+1, applicantForm);
    	applicantRepo.saveAndFlush(app);
        return "applicantRegisterSuccessful";
    }
    
    // showing applicants by level
    @RequestMapping(value={"/applicantlevel1"})
    public String showApplicant(Model model)
    {
        model.addAttribute("applicant", applicantRepo.findAllOnlevel(1));
        model.addAttribute("applicantlevel",new Applicant());
        return "applicantlevel1";
    }
    
    @RequestMapping(value={"/applicantlevel2"})
    public String showApplicant2(Model model)
    {
        model.addAttribute("applicant", applicantRepo.findAllOnlevel(2));
        model.addAttribute("applicantlevel",new Applicant());
        return "applicantlevel2";
    }
    
    @RequestMapping(value={"/applicantlevel3"})
    public String showApplicant3(Model model)
    {
        model.addAttribute("applicant", applicantRepo.findAllOnlevel(3));
        model.addAttribute("applicantlevel",new Applicant());
        return "applicantlevel3";
    }
    
    @RequestMapping(value={"/applicantPass"},method = RequestMethod.POST)
    public String applicantPass(Model model, //
            @ModelAttribute("applicantForm") @Validated Applicant applicant, //
            BindingResult result, //
            final RedirectAttributes redirectAttributes)
    {
        int id = applicant.id;
        
        System.out.println("Applicant id: "+applicant.id);
        
        Applicant app = applicantRepo.findById(id);
        int level = app.level+1;
        System.out.println("Applicant level new: "+level);
        if(level == 4)
        {
        	int c = employeeRepo.findAllCount();
        	Employee emp = new Employee(c+1,app.getName(),"1234");
        	employeeRepo.saveAndFlush(emp);
        	applicantRepo.deleteById(app.getId());
        	return "redirect:/employee";
        }
        applicantRepo.changeLevel(id,level);
        //model.addAttribute(applicantRepo.findAllOnlevel(level));
        if(level == 1)
        	return "redirect:/applicantlevel1";
        else if(level == 2)
        	return "redirect:/applicantlevel2";
        else
        	return "redirect:/applicantlevel3";
        	
    }
    
}