package br.com.gft.musical;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"br.com.gft.musical"})
@EnableFeignClients
@OpenAPIDefinition(
	info = @Info(title = "Musical API", version = "v1"),
	servers = {@Server(url= "http://localhost:8080")},
	tags = {@Tag(name = "musical", description = "Documentação da API")}
)
public class MusicalApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicalApplication.class, args);
	}
	
}
