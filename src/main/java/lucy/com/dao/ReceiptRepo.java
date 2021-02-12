package lucy.com.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import lucy.com.model.Receipt;

@Repository("receiptRepo")
public interface ReceiptRepo extends CrudRepository<Receipt, Long>  {
	
}


