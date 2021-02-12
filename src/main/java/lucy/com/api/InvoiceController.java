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
import lucy.com.service.InvoiceService;
@RequestMapping("api/v1/invoice")
@RestController
public class InvoiceController {
	private InvoiceService invoiceService;
	@Autowired
	public InvoiceController(InvoiceService s) {
		this.invoiceService = s;
	}
	@PostMapping(path = "add")
	public void addInvoice(@RequestBody @Valid @NonNull Invoice i) {
		this.invoiceService.addInvoice(i);
	}
	@GetMapping(path = "pid={pid}")
	public Iterable<Invoice> getUnclearedInvoicesByPid(@PathVariable("pid") long pid) {
		return this.invoiceService.getUnclearedInvoicesByPid(pid);
	}
	@GetMapping(path = "id={id}")
	public Invoice getInvoiceById(@PathVariable("id") long id) {
		return this.invoiceService.getInvoiceById(id);
	}
	@PutMapping(path = "{id}/{amount}")
	public void receivePayment(@PathVariable("id") Long id, @PathVariable("amount") int amount) {
		this.invoiceService.updatePaidById(id,amount);
	}

}

