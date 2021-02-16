package lucy.com.service;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import lucy.com.model.Refund;
import lucy.com.repo.RefundRepo;
@Service 
public class RefundService {
	private final RefundRepo refundRepo;
	@Autowired
	public RefundService(@Qualifier("refundRepo") RefundRepo rr) {
		this.refundRepo = rr;		
	}
	/*CREATE*/
	public Refund addRefund(@Valid Refund r) {
		r.setId(this.refundRepo.count()+1);
		this.refundRepo.save(r);
		return r;
	}
	/*READ*/
	public Refund getRefundById(long id) {
		return this.refundRepo.findById(id).orElse(null);
	}
	public Iterable<Refund> getRefundsByRid(long rid) {
		return this.refundRepo.getRefundByRid(rid);
	}
	public Iterable<Refund> getAllRefunds() {
		return this.refundRepo.findAll();
	}
	/*UPDATE*/ 
	public Refund updateCancelledTotalById(long id, int amount) {
		Refund r = this.getRefundById(id);
		if(r==null || r.getTotal() < r.getCancelledTotal() + amount) return null;
		r.setCancelledTotal(r.getCancelledTotal()+amount);
		this.refundRepo.save(r);
		return r;
	}
	
}
