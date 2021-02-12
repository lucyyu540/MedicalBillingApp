package lucy.com.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import lucy.com.model.Invoice;

@Repository("invoiceRepo")
public interface InvoiceRepo extends CrudRepository<Invoice, Long>  {
	
}

