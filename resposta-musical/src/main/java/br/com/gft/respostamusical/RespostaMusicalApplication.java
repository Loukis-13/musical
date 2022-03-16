package br.com.gft.respostamusical;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class RespostaMusicalApplication {

	public static void main(String[] args) {
		SpringApplication.run(RespostaMusicalApplication.class, args);
	}

}
