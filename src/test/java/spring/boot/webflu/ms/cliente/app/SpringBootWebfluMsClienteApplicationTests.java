package spring.boot.webflu.ms.cliente.app;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Mono;
import spring.boot.webflu.ms.cliente.app.documents.Client;
import spring.boot.webflu.ms.cliente.app.documents.TipoCuentaClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootWebfluMsClienteApplicationTests {
	
	@Autowired
	private WebTestClient client;

	@Test
	void contextLoads() {
	}
	
	@Test
	public void listarClientes() {
		client.get().uri("/api/Clientes")
		.accept(MediaType.APPLICATION_JSON)
		.exchange()
		.expectStatus().isOk() 
		.expectHeader().contentType(MediaType.APPLICATION_JSON) //.hasSize(2);
		.expectBodyList(Client.class).consumeWith(response -> {
			
			List<Client> cliente = response.getResponseBody();
			
			cliente.forEach(p -> {
				System.out.println(p.getNumdoc());
			});
			
			Assertions.assertThat(cliente.size() > 0).isTrue();
		});
	}
	
	@Test
	void crearCliente() {
		
		TipoCuentaClient ticl = new TipoCuentaClient();
		ticl.setIdTipo("1");
		ticl.setDescripcion("personal");
	
		Client cli = new Client();
		cli.setNumdoc("47305710");
		cli.setNombres("Jean");
		cli.setTelefono("999999999");
		cli.setCorreo("jean@gmail.com");
		cli.setTipoCliente(ticl);
		cli.setCodigoBanco("bcp");
		
		client.post()
		.uri("/api/Clientes")
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON)
		.body(Mono.just(cli), Client.class)
		.exchange()
		.expectStatus().isOk()
		.expectHeader().contentType(MediaType.APPLICATION_JSON)
		.expectBody(Client.class)
		.consumeWith(response -> {
			Client c = response.getResponseBody();
			Assertions.assertThat(c.getNumdoc()).isNotEmpty().isEqualTo("47305710");
			Assertions.assertThat(c.getNombres()).isNotEmpty().isEqualTo("Jean");
			Assertions.assertThat(c.getTelefono()).isNotEmpty().isEqualTo("999999999");
			Assertions.assertThat(c.getCorreo()).isNotEmpty().isEqualTo("jean@gmail.com");
			Assertions.assertThat(c.getTipoCliente().getDescripcion()).isNotEmpty().isEqualTo("personal");
			Assertions.assertThat(c.getCodigoBanco()).isNotEmpty().isEqualTo("bcp");
		});
	}

}
