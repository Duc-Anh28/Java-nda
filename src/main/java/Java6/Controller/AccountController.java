package Java6.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import Java6.DAO.AccountDAO;
import Java6.Entity.Account;
import Java6.Service.ParamService;
import Java6.Service.SessionService;

@Controller
public class AccountController {

	@Autowired
	ParamService paramService;
	@Autowired
	SessionService session;
	@Autowired
	AccountDAO acdao;
	
	@GetMapping("/account/index")
	public String form(Model model) {
		String use = session.get("acc");
		Account Account = acdao.findById(use).get();
		model.addAttribute("account", Account);
        return "layout/myAccount";
	}
	
	@RequestMapping("/account/save")
	public String save(@ModelAttribute("account") Account item ) {
		String un = paramService.getString("username", "");
		System.out.println("Aa=" + item );
		return  "layout/myAccount";
	}
}
