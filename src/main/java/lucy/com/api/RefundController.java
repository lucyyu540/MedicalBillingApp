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
import lucy.com.model.Refund;
import lucy.com.service.RefundService;
import lucy.com.service.MainService;
@RequestMapping("api/v1/refund")
@RestController
public class RefundController {
	private final RefundService refundService;
	private final MainService mainService; 
	@Autowired
	public RefundController(RefundService rs, MainService ms) {
		this.refundService = rs;
		this.mainService = ms;
	}
	@PostMapping
	public void addRefund(@RequestBody @Valid @NonNull Refund r) {
		this.mainService.addRefund(r);
	}
	@GetMapping
	public Iterable<Refund> getAllRefunds() {
		return this.refundService.getAllRefunds();
	}
}
