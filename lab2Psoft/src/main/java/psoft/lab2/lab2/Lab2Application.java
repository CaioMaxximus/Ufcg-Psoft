package psoft.lab2.lab2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.core.filter.TokenFilter;

import psoft.lab2.lab2.filtros.FiltroToken;

@SpringBootApplication
public class Lab2Application {

	@Bean
	public FilterRegistrationBean<FiltroToken> filterJwt() {
		FilterRegistrationBean<FiltroToken> filterRB = new FilterRegistrationBean<FiltroToken>();
		filterRB.setFilter(new FiltroToken());
		filterRB.addUrlPatterns("/disciplinas/**","/auth/usuarios");
	return filterRB;
}

	public static void main(String[] args) {
		SpringApplication.run(Lab2Application.class, args);
	}



}
