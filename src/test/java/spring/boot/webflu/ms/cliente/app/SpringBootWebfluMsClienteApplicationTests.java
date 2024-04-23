package spring.boot.webflu.ms.cliente.app;

import java.util.Collections;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Mono;
import spring.boot.webflu.ms.cliente.app.documents.Customer;
import spring.boot.webflu.ms.cliente.app.documents.TypeCustomer;
import spring.boot.webflu.ms.cliente.app.service.CustomerService;

@AutoConfigureWebTestClient
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)//SpringBootTest.WebEnvironment.RANDOM_PORT
class SpringBootWebfluMsClienteApplicationTests {
	
	@Autowired
	private WebTestClient client;
	
	@Autowired
	private CustomerService clientService;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void listarClientesCantidad() {
		client.get().uri("/api/Customer")
		.accept(MediaType.APPLICATION_JSON)
		.exchange()
		.expectStatus().isOk() 
		.expectHeader().contentType(MediaType.APPLICATION_JSON)//.hasSize(2); 
		.expectBodyList(Customer.class)
		.hasSize(7);
	}
	
	@Test
	public void listarClientes() {
		client.get().uri("/api/Customer")
		.accept(MediaType.APPLICATION_JSON)
		.exchange()
		.expectStatus().isOk() 
		.expectHeader().contentType(MediaType.APPLICATION_JSON)//.hasSize(2); 
		.expectBodyList(Customer.class).consumeWith(response -> {
				List<Customer> cliente = response.getResponseBody();
				cliente.forEach(p -> {
					System.out.println(p.getNombres());
				});
				Assertions.assertThat(cliente.size()>0).isTrue();
			});		
	}
	
	@Test
	public void verDNI() {
		
		Customer cliente = clientService.viewDniCliente("47305710").block();
				
		client.get().uri("/api/Customer/dni/{dni}",Collections.singletonMap("dni", cliente.getNumdoc()))
		.accept(MediaType.APPLICATION_JSON)
		.exchange()
		.expectStatus().isOk() 
		.expectHeader().contentType(MediaType.APPLICATION_JSON)
		.expectBody()
		.jsonPath("$.id").isNotEmpty()
		.jsonPath("$.numdoc").isEqualTo("47305710");
	}
	
	@Test
	void crearCustomer() {
		TypeCustomer personal = new TypeCustomer("1","personal");
		Customer cliente = new Customer("47305780","JUAN CARLOS",personal,"963791402",true,"bcp");
		
		client.post()
		.uri("/api/Customer")
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON)
		.body(Mono.just(cliente),Customer.class)
		.exchange()
		.expectStatus().isOk()
		.expectHeader().contentType(MediaType.APPLICATION_JSON)
		.expectBody()		
		.jsonPath("$.numdoc").isEqualTo("47305780")
		.jsonPath("$.tipoCliente.descripcion").isEqualTo("personal");
	}
	
	@Test
	void crearCustomerAsserts() {
		TypeCustomer personal = new TypeCustomer("1","personal");
		Customer cliente = new Customer("47305780","JUAN CARLOS",personal,"963791402",true,"bcp");
		
		client.post()
		.uri("/api/Customer")
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON)
		.body(Mono.just(cliente),Customer.class)
		.exchange()
		.expectStatus().isOk()
		.expectHeader().contentType(MediaType.APPLICATION_JSON)
		.expectBody(Customer.class)
		.consumeWith(response -> {
			Customer p = response.getResponseBody();
			Assertions.assertThat(p.getId()).isNotEmpty();
			Assertions.assertThat(p.getNombres()).isEqualTo("JUAN CARLOS");
			Assertions.assertThat(p.getTipoCliente().getDescripcion()).isEqualTo("personal");
		});
		
	}
	
	@Test
	void eliminarCustomer() {
		Customer customer = clientService.viewDniCliente("47305710").block();
		client.delete()
		.uri("/api/Customer/{id}",Collections.singletonMap("id",customer.getId()))
		.exchange()
		.expectStatus().isNoContent()
		.expectBody()
		.isEmpty();
		
		client.get()
		.uri("/api/Customer/{id}",Collections.singletonMap("id",customer.getId()))
		.exchange()
		.expectStatus().isNotFound()
		.expectBody()
		.isEmpty();
		
	}
	
}
