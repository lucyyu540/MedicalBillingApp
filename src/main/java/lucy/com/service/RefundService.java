package lucy.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import lucy.com.dao.RefundRepo;
import lucy.com.model.Refund;
@Service 
public class RefundService {
	private final RefundRepo refundRepo;
	@Autowired
	public RefundService(@Qualifier("refundRepo") RefundRepo rr) {
		this.refundRepo = rr;
	}
	
}
