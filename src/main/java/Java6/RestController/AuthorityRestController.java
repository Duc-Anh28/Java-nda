package Java6.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Java6.Entity.Authority;
import Java6.Service.AccountService;
import Java6.Service.AuthorityService;
import Java6.Service.RoleService;

@CrossOrigin("*")
@RestController

public class AuthorityRestController {

	@Autowired
	AuthorityService authorityService;
	
	
	@GetMapping("/rest/authorities")
	public List<Authority> getAuthority(@RequestParam("admin") Optional<Boolean> admin){
		if (admin.orElse(false)) {
			return authorityService.findAuthoritiesOfAdministrators();
		}
		return authorityService.findAll();
	}
	
	@PostMapping("/rest/authorities")
	public Authority post(@RequestBody Authority auth) {
		return authorityService.create(auth);
	}
	@DeleteMapping("/rest/authorities/{id}")
	public void delete(@PathVariable("id") Integer id) {
		authorityService.delete(id);
	}
	
	
	@Autowired
	AccountService accountService;
	@Autowired
	RoleService roleService;

	@GetMapping("/rest/autho")
	public Map<String, Object> getAutho(){
		Map<String, Object> data = new HashMap<>();
		data.put("authorities", authorityService.findAll());
		data.put("roles", roleService.findAll());
		data.put("accounts", accountService.findAll());
		return data;
	}
	
	@PostMapping("/rest/autho")
	public Authority create(@RequestBody Authority authority) {
		return authorityService.create(authority);
	}
	
	@DeleteMapping("/rest/autho/{id}")
	public void delete2(@PathVariable("id") Integer id) {
		authorityService.delete(id);
	}
}
