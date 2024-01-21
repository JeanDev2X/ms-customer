package spring.boot.webflu.ms.cliente.app.config;

import javax.annotation.PostConstruct;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import spring.boot.webflu.ms.cliente.app.dto.CuentaBanco;

@RestController
@RequestMapping("/api/Clientes")
public class WebClientController {
	
	WebClient webClient;
	@PostConstruct
	 public void init() { 
			 webClient = WebClient 
			.builder()
			.baseUrl("http://localhost:8021/api/ProductoBancario")
			.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
			.build(); 		 
	}


   @GetMapping("/ProductoBancario/{dniCliente}")
   public Flux<CuentaBanco> getFamilyList(@PathVariable String dniCliente) 
   { 
		return webClient.get().uri("/dni/"+dniCliente).retrieve().bodyToFlux(CuentaBanco.class); 
   }
  

}
