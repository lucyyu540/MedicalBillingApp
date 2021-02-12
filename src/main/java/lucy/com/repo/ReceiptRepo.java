package lucy.com.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import lucy.com.model.Receipt;

@Repository("receiptRepo")
public interface ReceiptRepo extends CrudRepository<Receipt, Long>  {
	@Query("SELECT r FROM Receipt r WHERE r.pid = ?1")
	public Iterable<Receipt> getReceiptsByPid(long pid);
	
}


