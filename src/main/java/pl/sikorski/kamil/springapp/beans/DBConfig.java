package pl.sikorski.kamil.springapp.beans;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("pl.sikorski.kamil.*")
@EnableAutoConfiguration
@EnableJpaRepositories 
public class DBConfig {

	@Bean
	EntityManager entityManager(){
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bluesoft_db");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		return entityManager;
	}
	  
	
}
