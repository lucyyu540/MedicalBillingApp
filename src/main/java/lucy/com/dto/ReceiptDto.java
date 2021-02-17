package lucy.com.dto;

import javax.validation.constraints.NotNull;

import lucy.com.model.Invoice;
import lucy.com.model.Refund;

public class ReceiptDto {
	private Invoice invoice;
	private Iterable<Refund> refunds;
	private Long id;
	private Long pid;//환저등록번호 
	private Long iid;//invoice등록번호 
	private String date;
	private Integer amount;//수납액 
	private int refunded;//환불액 
	public ReceiptDto( 
			Invoice invoice,
			Iterable<Refund> refunds, 
			Long id,
			Long pid,
			Long iid,
			String date,
			int amount,
			int refunded) {
		this.setInvoice(invoice);
		this.setRefunds(refunds);
		this.setId(id);
		this.setPid(pid);
		this.setIid(iid);
		this.setDate(date);
		this.setAmount(amount);
		this.setRefunded(refunded);

	}
	public Iterable<Refund> getRefunds() {
		return refunds;
	}
	public void setRefunds(Iterable<Refund> refunds) {
		this.refunds = refunds;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public Long getIid() {
		return iid;
	}
	public void setIid(Long iid) {
		this.iid = iid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getRefunded() {
		return refunded;
	}
	public void setRefunded(int refunded) {
		this.refunded = refunded;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Invoice getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	

}
