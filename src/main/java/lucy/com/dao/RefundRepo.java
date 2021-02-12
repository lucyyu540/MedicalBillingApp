package lucy.com.dao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import lucy.com.model.Refund;

@Repository("refundRepo")
public interface RefundRepo extends CrudRepository<Refund, Long>  {
	
}