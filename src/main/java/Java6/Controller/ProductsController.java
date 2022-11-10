package Java6.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Java6.Entity.Product;
import Java6.Service.ProductService;
import Java6.Service.SessionService;

@Controller
public class ProductsController {

	@Autowired
	ProductService productService;
	
	@Autowired
	SessionService session;
	@RequestMapping("/product/list")
	public String list1(Model model, @RequestParam("cid") Optional<String> cid ,@RequestParam("p") Optional<Integer> p) {
		if (cid.isPresent()) { 
        	Pageable pageable = PageRequest.of(p.orElse(0), 8);
			//List<Product> list = productService.findByCategory(cid.get());
        	Page<Product> list = productService.findByCategoryPage(cid.get(),pageable);
			model.addAttribute("items", list);
			return "layout/shop";
		}else  {
			Pageable pageable = PageRequest.of(p.orElse(0), 8);
			//List<Product> list = productService.findAll();
			Page<Product> list = productService.finPage(pageable);
			model.addAttribute("items", list);
			session.remove("cid");
			session.remove("keywords");
			return "layout/shop";
		}
	}
	
	@RequestMapping("/product/kay")
	public String key(Model model,@RequestParam("p") Optional<Integer> p,
		@RequestParam("keywords") Optional<String> kw) {
		System.out.println("keywords = " + kw.get());
		if (kw.get().equals("null")) {
			Pageable pageable = PageRequest.of(p.orElse(0), 8);
			//List<Product> list = productService.findAll();
			Page<Product> list = productService.finPage(pageable);
			model.addAttribute("items", list);
			System.out.println("keywords = null " + session.get("kwords"));
			return "layout/shop";
		}else {
//			System.out.println("keywords=" + kw.get());
			session.set("keywords", kw.get());
			Pageable pageable = PageRequest.of(p.orElse(0), 8);
			String kwords = kw.orElse(session.get("keywords"));
			//List<Product> list = productService.findAll();
			Page<Product> list = productService.findByKeywords("%"+kwords+"%", pageable);
			System.out.println("keywords khác null " + session.get("kwords"));
			model.addAttribute("items", list);
			return "layout/shop";
		}
	}
	
	@RequestMapping("/product/detail/{id}")
	public String detail(Model model ,@PathVariable("id") Integer id) {
		Product item = productService.findByid(id);
		model.addAttribute("item", item);
     	return "layout/product-details";
	}
	
	@RequestMapping("/product/cart")
	public String cart(Model model) {
      return "layout/Cart";
	}
	
}
