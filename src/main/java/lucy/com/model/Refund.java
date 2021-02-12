package lucy.com.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
@Entity
public class Refund {
	@Id
	private Long id;
	private Long pid;//환자등록번호
	@NotBlank
	private Long rid;//수납등록번호 
	private String date;
	@NotBlank
	private int cash;
	@NotBlank
	private int credit;
	@NotBlank
	private int transfer;
	@NotBlank
	private int total;
	public Refund() {}
	public Refund(
			@JsonProperty("pid")long pid, 
			@JsonProperty("rid")long rid, 
			@JsonProperty("cash")int cash, 
			@JsonProperty("credit")int credit, 
			@JsonProperty("transfer")int transfer) {
		this.setPid(pid);
		this.setRid(rid);
		this.setDate(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
		this.setCash(cash);
		this.setCredit(credit);
		this.setTransfer(transfer);
		this.setTotal(cash+credit+transfer);
	}
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public String getDate() { return date; }
	public void setDate(String date) { this.date = date; }
	public int getCash() { return cash; }
	public void setCash(int cash) { this.cash = cash; }
	public int getCredit() { return credit; }
	public void setCredit(int credit) { this.credit = credit; }
	public int getTransfer() { return transfer; }
	public void setTransfer(int transfer) { this.transfer = transfer; }
	public int getTotal() { return total; }
	public void setTotal(int total) { this.total = total; }
	public Long getPid() { return pid; }
	public void setPid(Long pid) { this.pid = pid; }
	public Long getRid() { return rid; }
	public void setRid(Long rid) { this.rid = rid; }

}
