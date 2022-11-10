package Java6.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import Java6.DAO.AccountDAO;
import Java6.Entity.Account;
import Java6.Service.AccountService;
import Java6.Service.SessionService;

@Controller
public class homeController {
	
	@Autowired
	SessionService session;
	@Autowired
	AccountDAO acdao;
	
	
	@RequestMapping("/home/orders")
	public String cart(Model model) {
      return "layout/Orders";
	}
	
	@RequestMapping({"/","home/index"})
	public String home() {
		return "redirect:/product/list";
	}
	
	
	@Autowired
	AccountService accountService;
	
		@RequestMapping({"/admin"})
	public String admin() {
		return "redirect:/Admin/index2.html";
	}
	
	
	@RequestMapping("/autho")
	public String autho() {
		return "nhap/table";
	}
	
	
}
