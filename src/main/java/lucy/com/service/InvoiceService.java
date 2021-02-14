package lucy.com.service;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import lucy.com.model.Invoice;
import lucy.com.repo.InvoiceRepo;
@Service 
public class InvoiceService {
	private final InvoiceRepo invoiceRepo;
	@Autowired
	public InvoiceService(@Qualifier("invoiceRepo") InvoiceRepo ir) {
		this.invoiceRepo = ir;
	}
	/*CREATE*/
	public void addInvoice(@Valid Invoice i) {
		i.setId(this.invoiceRepo.count()+1);
		this.invoiceRepo.save(i);
	}
	/*READ*/
	public Invoice getInvoiceById(long id) {
		return this.invoiceRepo.findById(id).orElse(null);
	}
	public Iterable<Invoice> getAllInvoices() {
		return this.invoiceRepo.findAll();
	}
	public Iterable<Invoice> getAllUnclearedInvoices() {
		return this.invoiceRepo.getAllUnclearedInvoices();
	}
	public Iterable<Invoice> getUnclearedInvoicesByPid(long pid) {
		return this.invoiceRepo.getUnclearedInvoicesByPid(pid);
	}
	public void getInvoiceById(Long id, Map obj) {
		Invoice i = this.getInvoiceById(id);
		obj.put("total", i.getTotal());//총진료비 
		obj.put("outOfPocket", i.getOutOfPocket());//본인부담금 
		obj.put("paid", i.getPaid());//총수납액 
	}
	/*UPDATE*/
	public void updatePaidById(Long id, int amount) {
		try {
			Invoice inv = this.invoiceRepo.findById(id).get();
			if(amount>inv.getOutOfPocket()) throw new Exception("Overpayment");
			inv.setPaid(inv.getPaid()+amount);
			if(inv.getPaid() == inv.getOutOfPocket()) inv.setClear(true);
			this.invoiceRepo.save(inv);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void deleteInvoiceById(Long id) {
		this.invoiceRepo.deleteById(id);
	}

	
}

