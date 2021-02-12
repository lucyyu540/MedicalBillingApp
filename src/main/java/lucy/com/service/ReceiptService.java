package lucy.com.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import lucy.com.dao.ReceiptRepo;
import lucy.com.model.Receipt;
@Service 
public class ReceiptService {
	private final ReceiptRepo receiptRepo;
	@Autowired
	public ReceiptService(@Qualifier("receiptRepo") ReceiptRepo rr) {
		this.receiptRepo = rr;
	}
	
}

