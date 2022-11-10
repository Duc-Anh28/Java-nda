package Java6.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import Java6.Entity.Order;

public interface OrderDAO extends JpaRepository<Order, Long>{

	@Query("select p  from Order p where p.account.username=?1")
	List<Order> findByUsername(String username);
}

