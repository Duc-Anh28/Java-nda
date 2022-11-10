package Java6;

import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import Java6.Entity.Account;
import Java6.Service.AccountService;
import Java6.Service.SessionService;
import Java6.Service.UserService;

@Configuration
@EnableWebSecurity
public class AuthConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	AccountService accountService;

	@Autowired
	BCryptPasswordEncoder pe;
	

	//cơ chế mã hóa mật khẩu
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Quản lý dl người dùng
	@Autowired
	UserService userService;
	@Autowired
	SessionService session;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
//		auth.userDetailsService(username -> {
//			try {
//				Account user = accountService.findById(username);
//				String password = user.getPassword();
//				String[] roles = user.getAuthorities().stream()
//						.map(er -> er.getRole().getId())
//						.collect(Collectors.toList()).toArray(new String[0]);			
//				return User.withUsername(username).password(pe.encode(password)).roles(roles).build();
//				
//			} catch (Exception e) {
//				throw new UsernameNotFoundException(username + "not found!");
//			}
//		});
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// CSRF,CORS
		http.csrf().disable().cors().disable();
		// Phân quyền sử dụng
		http.authorizeRequests()
//		.antMatchers("/home/**","/order/**","/account/**").authenticated() // bắt đăng nhập
//		.antMatchers("/admin/**").hasAnyRole("STAF", "DIRE")
//		.antMatchers("/rest/autho").hasRole("DIRE")
		.antMatchers("/**").permitAll()
		.anyRequest().permitAll();
		// Điều khiển lỗi truy cập không đúng vai trò
		http.exceptionHandling().accessDeniedPage("/auth/access/denied");

		// Giao diện đăng nhập
        // http.httpBasic();
		http.formLogin()
		.loginPage("/security/login/form")
		.loginProcessingUrl("/security/login")
		.defaultSuccessUrl("/security/login/success", false)// đn thàng công chuyển về trang này
		.failureUrl("/security/login/error");// đn sai về trang này

		http.rememberMe().tokenValiditySeconds(86400);// ghi nhớ tài khoản

		// cố tình truy xuất đến địa chỉ độc quyến
		http.exceptionHandling().accessDeniedPage("/security/unauthoried");
		// Đăng xuất
		http.logout()
		.logoutUrl("/security/logoff")
		.logoutSuccessUrl("/security/logoff/success");
	}
	
	//cho phép truy xuất rest api từ bên ngoài(domain khác)
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers(HttpMethod.OPTIONS,"/**");
	}
	
}
