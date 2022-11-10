package Java6.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Java6.DAO.AccountDAO;
import Java6.DAO.AuthorityDAO;
import Java6.Entity.Account;
import Java6.Entity.Authority;
import Java6.Service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService{

	@Autowired
	AuthorityDAO dao;
	@Autowired
	AccountDAO acdao;
	public List<Authority> findAuthoritiesOfAdministrators() {
		// TODO Auto-generated method stub
		List<Account> Account = acdao.getAdministrators();
		//List<Account> Account = acdao.findAll();
		return dao.authoritiesOf(Account);
	}
	@Override
	public List<Authority> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}
	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
		
	}
	@Override
	public Authority create(Authority auth) {
		// TODO Auto-generated method stub
		return dao.save(auth);
	}
}
