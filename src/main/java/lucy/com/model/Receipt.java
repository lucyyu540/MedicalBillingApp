package lucy.com.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
@Entity
public class Receipt {
	@Id
	private Long id;
	@NotBlank
	private Long pid;//환저등록번호 
	@NotBlank
	private Long Iid;//invoice등록번호 
	private String date;
	@NotBlank
	private int amount;//수납액 
	private int refunded;//환불액 
	/*CONSTRUCTORS*/
	public Receipt() {}
	public Receipt(
			@JsonProperty("pid") long pid, 
			@JsonProperty("iid") long iid, 
			@JsonProperty("amount") int amount) {
		this.setPid(pid);
		this.setIid(iid);
		this.setDate(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
		this.setAmount(amount);
		this.setRefunded(0);
	}
	/*GETTER SETTER*/
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public String getDate() { return date; }
	public void setDate(String date) { this.date = date;}
	public int getAmount() { return amount; }
	public void setAmount(int amount) { this.amount = amount;}
	public int getRefunded() {return refunded;}
	public void setRefunded(int refunded) {this.refunded = refunded;}
	public Long getPid() {return pid;}
	public void setPid(Long pid) {this.pid = pid;}
	public Long getIid() {return Iid;}
	public void setIid(Long iid) {this.Iid = iid;}

}
