package lucy.com.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dto.ReceiptDto;
import lucy.com.service.*;
import lucy.com.model.*;
@Service 
public class MainService {
	private final InvoiceService invoiceService;
	private final ReceiptService receiptService;
	private final PersonService personService;
	private final RefundService refundService;
	@Autowired
	public MainService(
			InvoiceService invoiceService, 
			ReceiptService receiptService, 
			PersonService personService,
			RefundService refundService) {
		this.invoiceService = invoiceService;
		this.receiptService = receiptService;
		this.personService = personService;
		this.refundService = refundService;
	}
	/*CREATE*/
	public Refund addRefund(@Valid Refund r) {
		//match to receipt
		if(this.receiptService.updateRefundedById(r.getRid(), r.getTotal())!= null) {
			//create refund invoice
			Invoice i = this.invoiceService.addInvoice(new Invoice(r.getPid(), "환불", null, - r.getTotal(), 0, -r.getTotal(), true));;
			r.setIid(i.getId());
			return this.refundService.addRefund(r);
		};
		return null;
	}

	public void cancelRefund(long id, int amount) {
		Refund r = this.refundService.updateCancelledTotalById(id, amount);
		if(r!=null) {
			//create cancel refund invoice 
			Invoice i = this.invoiceService.addInvoice(new Invoice(r.getPid(), "재수납", null, amount, amount, amount, true));
			this.receiptService.addReceipt(new Receipt(r.getPid(), i.getId(),amount));
		}
	}
	public void addReceipt(@Valid Receipt r) {
		if(this.invoiceService.updatePaidById(r.getIid(), r.getAmount())!= null) {
			this.receiptService.addReceipt(r);
		}
	}
	/*READ*/
	public Iterable<Object> getReceiptsByPid(long pid) {
		ArrayList res = new ArrayList();
		Iterable<Receipt> receipts = this.receiptService.getReceiptsByPid(pid);
		for(Receipt r : receipts) {
			Invoice invoice = this.invoiceService.getInvoiceById(r.getIid());//총진료비, 본인부담금, 총수납액 
			Iterable<Refund> refunds = this.refundService.getRefundsByRid(r.getId());//날짜, 현금,카드,이체,총금액 
			ReceiptDto obj = new ReceiptDto(
					invoice,
					refunds,
					r.getId(),
					r.getPid(),
					r.getIid(),
					r.getDate(),
					r.getAmount(),
					r.getRefunded());
			res.add(obj);
		}
		return res;
	}
	
}
