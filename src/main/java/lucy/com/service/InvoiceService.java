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
	public Invoice addInvoice(@Valid Invoice i) {
		this.invoiceRepo.save(i);
		return i;
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
	/*UPDATE*/
	public Invoice updatePaidById(Long id, int amount) {
		Invoice inv = getInvoiceById(id);
		if(inv == null || amount+inv.getPaid()>inv.getOutOfPocket()) return null;
		inv.setPaid(inv.getPaid()+amount);
		if(inv.getPaid() == inv.getOutOfPocket()) inv.setClear(true);
		this.invoiceRepo.save(inv);
		return inv;
	}
	public void deleteInvoiceById(Long id) {
		this.invoiceRepo.deleteById(id);
	}

	
}

