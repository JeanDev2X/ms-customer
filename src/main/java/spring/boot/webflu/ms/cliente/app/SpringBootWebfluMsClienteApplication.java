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
import spring.boot.webflu.ms.cliente.app.documents.Customer;
import spring.boot.webflu.ms.cliente.app.documents.TypeCustomer;
import spring.boot.webflu.ms.cliente.app.service.CustomerService;
import spring.boot.webflu.ms.cliente.app.service.TypeCustomerService;

@SpringBootApplication
public class SpringBootWebfluMsClienteApplication implements CommandLineRunner{
	
	@Autowired
	private CustomerService serviceCliente;
	
	@Autowired
	private TypeCustomerService serviceTipoCliente;
	
	@Autowired
	private ReactiveMongoTemplate mongoTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(SpringBootWebfluMsClienteApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebfluMsClienteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		mongoTemplate.dropCollection("Customer").subscribe();
		mongoTemplate.dropCollection("TypeCustomer").subscribe();
		
//		TipoCuentaClient personal = new TipoCuentaClient("1","personal");
//		TipoCuentaClient empresa = new TipoCuentaClient("2","empresa");
		
		TypeCustomer personal = new TypeCustomer("1","personal");
		TypeCustomer empresa = new TypeCustomer("2","empresa");
		
		//
		Flux.just(personal,empresa)
		.flatMap(serviceTipoCliente::saveTipoCustomer)
		.doOnNext(c -> {
			log.info("Tipo cliente creado: " +  c.getDescripcion() + ", Id: " + c.getId());
		}).thenMany(					
				Flux.just(
						new Customer("47305710","JUAN CARLOS",personal,"bcp"),
						new Customer("47305711","ESMERALDA CORP",empresa,"bcp"),
						new Customer("07091424","LUIS RAMIREZ",personal,"bcp")
						)					
					.flatMap(customer -> {
						return serviceCliente.saveCustomer(customer);
					})					
				).subscribe(customer -> log.info("Insert: " + customer.getId() + " " + customer.getNombres()));
		
	}

}
