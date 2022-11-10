package Java6.Service;

import java.util.List;

import Java6.Entity.Account;

public interface AccountService {
	
    public	Account findById(String username);

	public List<Account> findAll();
	
	public List<Account> getAdministrators();

	public Account update(Account account);

	public Account create(Account account);

	public void delete(String username);
}
