package pl.sikorski.kamil.springapp.beans;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import pl.sikorski.kamil.springapp.entities.System;

@Configuration
@ComponentScan("pl.sikorski.kamil.*")
public class AppConfig extends WebMvcConfigurerAdapter {
	
	@Bean
    public TilesConfigurer tilesConfigurer(){
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions(new String[] {"/WEB-INF/tiles/tiles.xml"});
        tilesConfigurer.setCheckRefresh(true);
        return tilesConfigurer;
    }
 
    /**
     * Configure ViewResolvers to deliver preferred views.
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        TilesViewResolver viewResolver = new TilesViewResolver();
        registry.viewResolver(viewResolver);
    }
     
    /**
     * Configure ResourceHandlers to serve static resources like CSS/ Javascript etc...
     */
     
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }
    

	@Bean
	public List<System> sampleSystems() {
		List<System> systemList = new ArrayList<>();
		System system1 = new System();
		System system3 = new System();
		System system2 = new System();

		system1.setName("A100");
		system1.setDescription("testowy system A");
		system1.setSupportGroup("Bluehard");

		system2.setName("B020");
		system2.setDescription("testowy system B");
		system2.setSupportGroup("Redlight");

		system3.setName("C333");
		system3.setDescription("testowy system C");
		system3.setSupportGroup("Greenlol");

		systemList.add(system1);
		systemList.add(system2);
		systemList.add(system3);

		return systemList;
	}
	
	@Bean
	public Path rootLocation(){
		return Paths.get(("upload"));
		
	}

}
