package xml.projekat.XMLAgent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
@EntityScan("xml.projekat")
@ComponentScan(basePackages = "xml.projekat")
@EnableJpaRepositories("xml.projekat")
@SpringBootApplication
public class XmlAgentApplication {

	public static void main(String[] args) {
		SpringApplication.run(XmlAgentApplication.class, args); 
		
		
	}
}
