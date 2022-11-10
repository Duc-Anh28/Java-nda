package Java6.Service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import Java6.Entity.Order;
import Java6.Entity.OrderDetail;

public interface OrderService {

	Order create(JsonNode orderData);
	
	Order findByID(Long id);

	List<Order> findByUsername(String username);

	List<Order> findAll();

	public void delete(Long id);
}
