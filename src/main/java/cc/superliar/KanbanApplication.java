package cc.superliar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class KanbanApplication extends WebMvcConfigurerAdapter {


	public static void main(String[] args) {
		SpringApplication.run(KanbanApplication.class, args);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/error").setViewName("error");
		registry.addViewController("/profile").setViewName("profile");
	}

}
