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

import lucy.com.model.Receipt;
import lucy.com.service.ReceiptService;
import lucy.com.service.MainService;
@RequestMapping("api/v1/receipt")
@RestController
public class ReceiptController {
	private final ReceiptService receiptService;
	private final MainService mainService;
	@Autowired
	public ReceiptController(ReceiptService rs, MainService ms) {
		this.receiptService = rs;
		this.mainService = ms;
	}
	@PostMapping
	public void addReceipt(@RequestBody @Valid @NonNull Receipt r) {
		this.mainService.addReceipt(r);
	}
	@GetMapping(path = "pid={pid}")
	public Iterable<Object> getReceiptsByPid(@PathVariable("pid") long pid) {
		return this.mainService.getReceiptsByPid(pid);
	}
}
