package Java6.Service;

import java.util.List;

import Java6.Entity.Authority;

public interface AuthorityService {

	public List<Authority> findAuthoritiesOfAdministrators();

	public List<Authority> findAll();
	
	public void delete(Integer id);
	
	public Authority create(Authority auth);

}
