package lucy.com.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
	public void addRefund(@Valid Refund r) {
		this.receiptService.updateRefundedById(r.getRid(), r.getTotal());
		this.refundService.addRefund(r);
	}
	public void addReceipt(@Valid Receipt r) {
		this.invoiceService.updatePaidById(r.getIid(), r.getAmount());
		this.receiptService.addReceipt(r);
	}
	public Iterable<Object> getReceiptsByPid(long pid) {
		ArrayList res = new ArrayList();
		Iterable<Receipt> receipts = this.receiptService.getReceiptsByPid(pid);
		for(Receipt r : receipts) {
			Map obj = new HashMap();
			obj.put("id", r.getId());
			obj.put("date", r.getDate());//수납날짜 
			obj.put("amount", r.getAmount());//수납액 
			obj.put("refunded", r.getRefunded());//환불액 
			this.invoiceService.getInvoiceById(r.getIid(), obj);//총진료비, 본인부담금, 총수납액 
			if(r.getRefunded() > 0) this.refundService.getRefundByRid(r.getId(), obj);//날짜, 현금,카드,이체,총금액 
			res.add(obj);
		}
		return res;
	}
	
}
