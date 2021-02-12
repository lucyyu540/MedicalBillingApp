package lucy.com.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;
@Entity
public class Invoice {
	@Id
	private Long id;
	private Long pid;//환자등록번 
	private String pName;//환자이름 
	private String date;//진료기간 
	private String type;//외래/입원(퇴원/중간) 
	private String department;//진료과목 
	private int total;//총진료비 
	private int outOfPocket;//본인부담금
	private int paid;//누적실제수납 
	private boolean clear; 
	public Invoice() {}
	public Invoice(
			@JsonProperty("pid") long pid, 
			@JsonProperty("pName") String pName, 
			@JsonProperty("total") int total, 
			@JsonProperty("type") String type, 
			@JsonProperty("department") String department) {
		this.setPid(pid);
		this.setpName(pName);
		this.setDate(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
		//    Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);  
		this.setType(type);
		this.setDepartment(department);
		this.setTotal(total);
		this.setOutOfPocket((int) (total*0.3));
		this.setPaid(0);
		this.setClear(false);
	}
	/*GETTER SETTER*/
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getDate() {return date;}
	public void setDate(String date) {this.date = date;}
	public int getTotal() {return total;}
	public void setTotal(int total) {this.total = total;}
	public int getOutOfPocket() {return outOfPocket;}
	public void setOutOfPocket(int oop) {this.outOfPocket = oop;}
	public int getPaid() {return paid;}
	public void setPaid(int paid) {this.paid = paid;}
	public Long getPid() {return pid;}
	public void setPid(Long pid) {this.pid = pid;}
	public String getType() {return type;}
	public void setType(String type) {this.type = type;}
	public String getDepartment() {return department;}
	public void setDepartment(String department) {this.department = department;}
	public boolean isClear() {return clear;}
	public void setClear(boolean clear) {this.clear = clear;}
	public String getpName() {return pName;}
	public void setpName(String pName) {this.pName = pName;}
	
}
