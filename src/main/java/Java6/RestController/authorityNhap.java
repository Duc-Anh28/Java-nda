package Java6.RestController;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Java6.DAO.AccountDAO;
import Java6.DAO.AuthorityDAO;
import Java6.DAO.RoleDAO;
import Java6.Entity.Authority;

@RestController
public class authorityNhap {

	@Autowired
	AuthorityDAO authorityDAO;
	@Autowired
	RoleDAO roleDAO;
	@Autowired
	AccountDAO accountDAO;
//	
//	@GetMapping("/rest/autho")
//	public Map<String, Object> getAutho(){
//		Map<String, Object> data = new HashMap<>();
//		data.put("authorities", authorityDAO.findAll());
//		data.put("roles", roleDAO.findAll());
//		data.put("accounts", accountDAO.findAll());
//		return data;
//	}
	
//	@PostMapping("/rest/autho")
//	public Authority create(@RequestBody Authority authority) {
//		return authorityDAO.save(authority);
//	}
//	
//	@DeleteMapping("/rest/autho/{id}")
//	public void delete(@PathVariable("id") Integer id) {
//		authorityDAO.deleteById(id);
//	}
}
