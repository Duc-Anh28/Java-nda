package Java6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import Java6.interceptor.Globalinterceptoe;


@Configuration
public class InterceptorConfig implements WebMvcConfigurer{
	@Autowired
	Globalinterceptoe globalinterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(globalinterceptor)
		.addPathPatterns("/**")
		.excludePathPatterns("/admin/**");
	}
	
}
