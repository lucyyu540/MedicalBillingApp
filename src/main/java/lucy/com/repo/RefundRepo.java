package lucy.com.repo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import lucy.com.model.Refund;

@Repository("refundRepo")
public interface RefundRepo extends CrudRepository<Refund, Long>  {
	@Query("SELECT r FROM Refund r WHERE r.rid = ?1")
	Iterable<Refund> getRefundByRid(long rid);
	
}