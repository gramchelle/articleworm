// Bismillahirrahmanirrahim
package iau.articleworm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ArticlewormApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArticlewormApplication.class, args);
	}

}
