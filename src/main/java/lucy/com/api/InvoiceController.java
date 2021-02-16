package lucy.com.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lucy.com.model.Invoice;
import lucy.com.model.Person;
import lucy.com.service.InvoiceService;
@RequestMapping("api/v1/invoice")
@RestController
public class InvoiceController {
	private InvoiceService invoiceService;
	@Autowired
	public InvoiceController(InvoiceService s) {
		this.invoiceService = s;
	}
	@PostMapping
	public Invoice addInvoice(@RequestBody @Valid @NonNull Invoice i) {
		return this.invoiceService.addInvoice(i);
	}
	@GetMapping
	public Iterable<Invoice> getAllInvoices() {
		return this.invoiceService.getAllInvoices();
	}
	@GetMapping(path = "uncleared")
	public Iterable<Invoice> getAllUnclearedInvoices() {
		return this.invoiceService.getAllUnclearedInvoices();
	}
	@GetMapping(path = "/uncleared/pid={pid}")
	public Iterable<Invoice> getUnclearedInvoicesByPid(@PathVariable("pid") long pid) {
		return this.invoiceService.getUnclearedInvoicesByPid(pid);
	}
	@GetMapping(path = "id={id}")
	public Invoice getInvoiceById(@PathVariable("id") long id) {
		return this.invoiceService.getInvoiceById(id);
	}
	@DeleteMapping(path = "{id}")
	public void deletInvoiceById(@PathVariable("id") Long id) {
		this.invoiceService.deleteInvoiceById(id);
	}

}

