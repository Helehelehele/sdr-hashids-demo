package io.github.cepr0.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import static java.util.Arrays.asList;

@RequiredArgsConstructor
@SpringBootApplication
public class Application {

	private final ModelRepo modelRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	RepositoryRestConfigurerAdapter repositoryRestConfigurerAdapter() {
		return new RestConfigurer();
	}
	
	@EventListener
	public void onReady(ApplicationReadyEvent e) {
		modelRepo.saveAll(asList(
				new Model("model1"),
				new Model("model2"),
				new Model("model3")
		));
	}
	
}
