package cc.superliar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.autoconfigure.web.*;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@Import({
		DispatcherServletAutoConfiguration.class,
		EmbeddedServletContainerAutoConfiguration.class,
		ErrorMvcAutoConfiguration.class,
		HttpEncodingAutoConfiguration.class,
		HttpMessageConvertersAutoConfiguration.class,
		JacksonAutoConfiguration.class,
//        JmxAutoConfiguration.class,
		MultipartAutoConfiguration.class,
		ServerPropertiesAutoConfiguration.class,
		PropertyPlaceholderAutoConfiguration.class,
		ThymeleafAutoConfiguration.class,
		WebMvcAutoConfiguration.class,
//        WebSocketAutoConfiguration.class,
})
public class KanbanApplication  {
	public static void main(String[] args) {
		SpringApplication.run(KanbanApplication.class, args);
	}



}
