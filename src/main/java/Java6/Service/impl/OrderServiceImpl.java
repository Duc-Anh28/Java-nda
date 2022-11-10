package Java6.Service.impl;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import Java6.DAO.OrderDAO;
import Java6.DAO.OrderDetailDAO;
import Java6.Entity.Order;
import Java6.Entity.OrderDetail;
import Java6.Service.OrderService;




@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	OrderDAO dao;
	@Autowired
	OrderDetailDAO ddao;
	
	@Override
	public Order create(JsonNode orderData) {
		ObjectMapper mapper = new ObjectMapper();
		
		Order order = mapper.convertValue(orderData, Order.class);
		dao.save(order);
		
		TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {};
		List<OrderDetail> detail = mapper.convertValue(orderData.get("orderDetails"),type)
				.stream().peek(d -> d.setOrder(order)).collect(Collectors.toList());
		ddao.saveAll(detail);
		
		return order;
	}
	
	@Override
	public Order findByID(Long id) {
		return dao.findById(id).get();
	}
	
	@Override
	public List<Order> findByUsername(String username) {
		return dao.findByUsername(username);
	}

	@Override
	public List<Order> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
	}
	
}
