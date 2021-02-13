package lucy.com.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import lucy.com.repo.ReceiptRepo;
import lucy.com.model.Receipt;
@Service 
public class ReceiptService {
	private final ReceiptRepo receiptRepo;
	@Autowired
	public ReceiptService(@Qualifier("receiptRepo") ReceiptRepo rr) {
		this.receiptRepo = rr;
	}
	/*CREATE*/
	public void addReceipt(@Valid Receipt r) {
		r.setId(this.receiptRepo.count()+1);
		this.receiptRepo.save(r);
	}
	/*READ*/
	public Receipt getReceiptById(long id) {
		return this.receiptRepo.findById(id).orElse(null);
	}
	public Iterable<Receipt> getAllReceipts() {
		return this.receiptRepo.findAll();
	}
	public Iterable<Receipt> getReceiptsByPid(long pid) {
		return this.receiptRepo.getReceiptsByPid(pid);
	}
	/*UPDATE*/
	public void updateRefundedById(Long id, int amount) {
		try {
			Receipt r = this.getReceiptById(id);
			if(r.getAmount() < amount) throw new Exception("Refund total exceeds receipt");
			r.setRefunded(amount);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
}

