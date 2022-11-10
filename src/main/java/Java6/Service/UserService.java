package Java6.Service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import Java6.DAO.AccountDAO;
import Java6.Entity.Account;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	AccountDAO accountDAO;
    @Autowired
    BCryptPasswordEncoder pe;
    @Autowired
	SessionService sessionService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		try {
			Account account = accountDAO.findById(username).get();
			sessionService.set("acc", username);
			//tạo UserDetails
			String password = account.getPassword();
			String[] roles = account.getAuthorities().stream()
					.map(er -> er.getRole().getId())
					.collect(Collectors.toList()).toArray(new String[0]);			
			return User.withUsername(username).password(pe.encode(password)).roles(roles).build(); 
		} catch (Exception e) {
			// TODO: handle exception
			throw new UsernameNotFoundException(username + "not found!");
		}
	}

}
