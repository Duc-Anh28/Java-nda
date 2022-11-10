package Java6.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import Java6.Entity.OrderDetail;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long>{

	@Query("SELECT p FROM OrderDetail p WHERE p.order.id= ?1")
	List<OrderDetail> findByIDOrder(Long id);

	@Query(value = "SELECT products.name, orderdetails.price , orderdetails.quantity\r\n"
			+ " FROM orderdetails join products on products.id = orderdetails.productid\r\n"
			+ " join orders on orders.id = orderdetails.orderid\r\n"
			+ " WHERE orders.createdate = ?1", nativeQuery = true)
	List<Object> findBycreateDate(String date);

	@Query(value = "SELECT products.name, orderdetails.price , orderdetails.quantity\r\n"
			+ " FROM orderdetails join products on products.id = orderdetails.productid\r\n"
			+ " join orders on orders.id = orderdetails.orderid\r\n"
			+ " WHERE orders.createdate = ?1", nativeQuery = true)
	Object findBycreateDateA(String date);
}
