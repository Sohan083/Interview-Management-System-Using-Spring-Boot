package formbean;

public class ApplicantForm {
	
	private String name;
	private String varsity;
	private String cgpa;
	public ApplicantForm()
    {
    }

    public ApplicantForm(String name,String varsity, String cgpa)
    {
         this.name = name;
         this.varsity = varsity;
         this.cgpa = cgpa;

    }

   
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getVarsity()
    {
        return varsity;
    }
    public void setVarsity(String varsity)
    {
        this.varsity = varsity;
    }

    public void setCgpa(String cgpa)
    {
        this.cgpa = cgpa;
    }
    public String getCgpa()
    {
        return cgpa;
    }
}
