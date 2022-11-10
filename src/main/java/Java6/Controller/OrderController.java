package Java6.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import Java6.Entity.OrderDetail;
import Java6.Service.OrderService;

@Controller
public class OrderController {
    
	@Autowired
	OrderService orderService;
	
	@RequestMapping("/order")
	public String order(){
		 return "layout/Orders";
	}
	
	@RequestMapping("/order/checkout")
	public String checkout(){
		 return "layout/checkout";
	}
	
	@RequestMapping("/order/list")
	public String list(Model model,HttpServletRequest request){
		String username = request.getRemoteUser();
		model.addAttribute("orders", orderService.findByUsername(username));
		 return "layout/Orders";
	}
	@RequestMapping("/order/detail/{id}")
	public String detail(@PathVariable("id") Long id,Model model){
		model.addAttribute("order", orderService.findByID(id));
		 return "layout/Orderdetails";
	}
	
	
}
