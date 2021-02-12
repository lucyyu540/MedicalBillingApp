package lucy.com.service;

import javax.validation.Valid;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import lucy.com.dao.InvoiceRepo;
import lucy.com.model.Invoice;
@Service 
public class InvoiceService {
	private final InvoiceRepo invoiceRepo;
	@Autowired
	public InvoiceService(@Qualifier("invoiceRepo") InvoiceRepo ir) {
		this.invoiceRepo = ir;
	}
	public void addInvoice(@Valid Invoice i) {
		i.setId(this.invoiceRepo.count()+1);
		this.invoiceRepo.save(i);
	}

	public Iterable<Invoice> getInvoiceByPid(long pid) {
		
		return null;
	}
	public Invoice getInvoiceById(long id) {
		return null;
	}
	public void receivePayment(Long id, int amount) {
		try {
			Invoice inv = this.invoiceRepo.findById(id).get();
			inv.setPaid(inv.getPaid()+amount);
			if(inv.getPaid() == inv.getOutOfPocket()) inv.setClear(true);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	
}

