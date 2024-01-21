package spring.boot.webflu.ms.cliente.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import reactor.core.publisher.Flux;
import spring.boot.webflu.ms.cliente.app.documents.Client;
import spring.boot.webflu.ms.cliente.app.documents.TipoCuentaClient;
import spring.boot.webflu.ms.cliente.app.service.ClienteService;
import spring.boot.webflu.ms.cliente.app.service.TipoCuentaClienteService;

@SpringBootApplication
public class SpringBootWebfluMsClienteApplication implements CommandLineRunner{
	
	@Autowired
	private ClienteService serviceCliente;
	
	@Autowired
	private TipoCuentaClienteService serviceTipoCliente;
	
	@Autowired
	private ReactiveMongoTemplate mongoTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(SpringBootWebfluMsClienteApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebfluMsClienteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		mongoTemplate.dropCollection("Clientes").subscribe();
		mongoTemplate.dropCollection("TipoCliente").subscribe();
		
		TipoCuentaClient personal = new TipoCuentaClient("1","personal");
		TipoCuentaClient empresa = new TipoCuentaClient("2","empresa");
		
		//
		Flux.just(personal,empresa)
		.flatMap(serviceTipoCliente::saveTipoCliente)
		.doOnNext(c -> {
			log.info("Tipo cliente creado: " +  c.getDescripcion() + ", Id: " + c.getIdTipo());
		}).thenMany(					
				Flux.just(
						new Client("47305710","JUAN CARLOS",personal,"bcp"),
						new Client("47305711","ESMERALDA CORP",empresa,"bcp"),
						new Client("07091424","LUIS RAMIREZ",personal,"bcp")
						)					
					.flatMap(client -> {
						return serviceCliente.saveCliente(client);
					})					
				).subscribe(client -> log.info("Insert: " + client.getId() + " " + client.getNombres()));
		
	}

}
