package Java6.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import Java6.Service.SessionService;

@Controller
public class loginController {

	@Autowired
	SessionService session;
	@Autowired
	Java6.Service.ParamService paramService;
	
	@RequestMapping("/login/form")
	public String Login(Model model) {
		//model.addAttribute("message", "Vui Lòng Đăng Nhập!");
		return "layout/login";
	}
	
	@RequestMapping("/security/login/form")
	public String LoginForm(Model model) {
		model.addAttribute("message", "Vui Lòng Đăng Nhập!");
		return "layout/login";
	}
	
	@RequestMapping("/security/login/success")
	public String LoginSuccess(Model model) {
		model.addAttribute("message", "Đăng Nhập Thành Công!");
		String un = paramService.getString("username", "");
		session.set("name", "NDA");
		System.out.println("account 2---->" + un);
		//return "forward:/auth/login/form";
		return "layout/login";
	}
	
	@RequestMapping("/security/login/error")
	public String LoginError(Model model) {
		model.addAttribute("message", "Sai Thông Tin Đăng Nhập!");
		//return "forward:/auth/login/form";
		return "layout/login";
	}
	
	@RequestMapping("/security/unauthoried")
	public String unauthoried(Model model) {
		model.addAttribute("message", "Không Có Quyền Truy Cập");
		//return "forward:/auth/login/form";
		return "layout/login";
	}
	
	@RequestMapping("/security/logoff/success")
	public String logoff(Model model) {
		model.addAttribute("message", "Bạn Đã Đăng Xuất!");
		//return "forward:/auth/login/form";
		return "layout/login";
	}

}
