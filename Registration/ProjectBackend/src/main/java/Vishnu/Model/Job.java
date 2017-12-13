package Vishnu.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Job {
	
	@Id
	private int id;
	private String JobTitle;
	private String JobDescription;
	private String skillsRequired;
	private String location;
	private String companyname;
	private String salary;
	private String yrsOfExp;
	private Date postedon;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJobTitle() {
		return JobTitle;
	}
	public void setJobTitle(String jobTitle) {
		JobTitle = jobTitle;
	}
	public String getJobDescription() {
		return JobDescription;
	}
	public void setJobDescription(String jobDescription) {
		JobDescription = jobDescription;
	}
	public String getSkillsRequired() {
		return skillsRequired;
	}
	public void setSkillsRequired(String skillsRequired) {
		this.skillsRequired = skillsRequired;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getYrsOfExp() {
		return yrsOfExp;
	}
	public void setYrsOfExp(String yrsOfExp) {
		this.yrsOfExp = yrsOfExp;
	}
	public Date getPostedon() {
		return postedon;
	}
	public void setPostedon(Date postedon) {
		this.postedon = postedon;
	}
}
