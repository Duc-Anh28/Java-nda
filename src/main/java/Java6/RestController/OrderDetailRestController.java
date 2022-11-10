package Java6.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Java6.Entity.Order;
import Java6.Entity.OrderDetail;
import Java6.Service.OrderDetailService;
import Java6.Service.OrderService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/OrderDetail")
public class OrderDetailRestController {
	
	@Autowired
	OrderDetailService orderDetailService;
	
	@GetMapping("{id}")
	public List<OrderDetail> findByid(@PathVariable("id") Long id) {
		return orderDetailService.findByIDOrder(id);
	}
  
}
