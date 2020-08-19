package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import formbean.ApplicantForm;
@Entity
@Table(name = "applicant")
public class Applicant {
	
	  	@Id //    @GeneratedValue(strategy=GenerationType.AUTO)
		public Integer id;
	  	public Integer level;
	  	private String pass;
	    private String name, varsity, cgpa;
	    public static Integer serial = 1;
	 
	    public Applicant()
	    {
	    }
	    
	    public Applicant(Integer id,ApplicantForm af)
	    {
	    	this.id = id;
	    	this.level = 1;
	    	this.cgpa = af.getCgpa();
	    	this.name = af.getName();
	    	this.varsity = af.getVarsity();
	    }

	    public Applicant(Integer id,String name,String varsity, String cgpa)
	    {
	    	 this.level = 1;
	         this.id = id;
	         this.name = name;
	         this.varsity = varsity;
	         this.cgpa = cgpa;

	    }

	    public Integer getId()
	    {
	        return id;
	    }

	    public void setId(Integer id)
	    {
	        this.id = id;
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
