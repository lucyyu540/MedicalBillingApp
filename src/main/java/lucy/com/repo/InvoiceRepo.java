package lucy.com.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import lucy.com.model.Invoice;

@Repository("invoiceRepo")
public interface InvoiceRepo extends CrudRepository<Invoice, Long>  {

	//@Query(value = "", nativeQuery = true)
	@Query("SELECT i FROM Invoice i WHERE i.pid = ?1 AND i.clear=FAlSE")
	public Iterable<Invoice> getUnclearedInvoicesByPid(long pid);

}

