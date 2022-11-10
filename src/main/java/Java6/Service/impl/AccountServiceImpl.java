package Java6.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Java6.DAO.AccountDAO;
import Java6.Entity.Account;
import Java6.Service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
    AccountDAO adao;
	
	@Override
	public Account findById(String username) {
		return adao.findById(username).get();
	}

	
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return adao.findAll();
	}

	
	public List<Account> getAdministrators() {
		// TODO Auto-generated method stub
		return adao.getAdministrators();
	}

	@Override
	public Account update(Account account) {
		// TODO Auto-generated method stub
		return adao.save(account);
	}

	@Override
	public Account create(Account account) {
		// TODO Auto-generated method stub
		return adao.save(account);
	}

	@Override
	public void delete(String username) {
		// TODO Auto-generated method stub
		 adao.deleteById(username);
	}
}
