package Java6.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Java6.Service.OrderDetailService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/statistical")
public class thongkeRestController {
	
	@Autowired
	OrderDetailService orderDetailService;

	@GetMapping("{day}")
	public List<Object> getListOrderDetail(@PathVariable("day") Date day){
		
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		String date = formater.format(day);
		
		return orderDetailService.findBycreateDate(date);
	}
}
