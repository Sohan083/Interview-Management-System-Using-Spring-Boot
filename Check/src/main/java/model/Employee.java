package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee
{
    @Id //    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
    private String name;
    private String pass;
    
    public static Integer serial = 1;
 
    public Employee()
    {
    }

    public Employee(Integer id,String name,String pass)
    {
         this.id = id;
         this.name = name;
         this.pass = pass;

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
    
    public String getPass()
    {
        return pass;
    }

    public void setPass(String pass)
    {
        this.pass = pass;
    }
}