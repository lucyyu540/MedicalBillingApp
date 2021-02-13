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
	public void addRefund(@Valid Refund r) {
		r.setId(this.refundRepo.count()+1);
		this.refundRepo.save(r);
	}
	/*READ*/
	public Refund getRefundById(long id) {
		return this.refundRepo.findById(id).orElse(null);
	}
	public Refund getRefundByRid(long rid) {
		return this.refundRepo.getRefundByRid(rid).iterator().next();
	}
	public void getRefundByRid(Long rid, Map obj) {
		Refund ref = this.getRefundByRid(rid);
		obj.put("refundDate", ref.getDate());
		obj.put("refundTotal", ref.getTotal());
		obj.put("refundCash", ref.getCash());
		obj.put("refundCredit", ref.getCredit());
		obj.put("refundTransfer", ref.getTransfer());
	}
	public Iterable<Refund> getAllRefunds() {
		return this.refundRepo.findAll();
	}
	
}
