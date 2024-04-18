package spring.boot.webflu.ms.cliente.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.boot.webflu.ms.cliente.app.documents.Customer;
import spring.boot.webflu.ms.cliente.app.documents.TypeCustomer;
import spring.boot.webflu.ms.cliente.app.service.CustomerService;
import spring.boot.webflu.ms.cliente.app.service.TypeCustomerService;

@RequestMapping("/api/Customer")
@RestController
public class CustomerControllers {

	@Autowired
	private CustomerService clientService;

	@Autowired
	private TypeCustomerService tipoClienteService;
	
	private static final Logger log = LoggerFactory.getLogger(CustomerControllers.class);

	//LISTA TODOS LOS CLIENTES	
	@GetMapping
	public Mono<ResponseEntity<Flux<Customer>>> findAll() {
		
		log.info("listar clientes");
		return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
				.body(clientService.findAllCustomer())
		);
	}
	
	//LISTA CLIENTES POR ID
	@GetMapping("/{id}")
	public Mono<ResponseEntity<Customer>> viewId(@PathVariable String id) {
		return clientService.findByIdCustomer(id)
				.map(p -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
		
	
	//ACTUALIZA CLIENTE POR ID
	@PutMapping
	public Mono<Customer> updateCliente(@RequestBody Customer cliente) {
		System.out.println(cliente.toString());
		return clientService.saveCustomer(cliente);
	}
	
	
	//GUARDA CLIENTE VALIDANDO EL TIPO CLIENTE
	@PostMapping
	public Mono<Customer> guardarCliente(@RequestBody Customer cli) {
		log.info("CLIENTE==>"+cli);	
		
		return tipoClienteService.findByIdTipoCustomer(cli.getTipoCliente().getId())
	            .switchIfEmpty(Mono.error(new InterruptedException("NO EXISTE EL TIPO DE CUENTA CLIENTE")))
	            .flatMap(tipoCliente -> {
	                cli.setTipoCliente(tipoCliente);
	                return clientService.saveCustomer(cli);
	            });
		
	}

	//ELIMINA CLIENTE POR ID
	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
		System.out.println("eliminar clientes");
		return clientService.findByIdCustomer(id)
				.flatMap(s -> {
			return clientService.deleteCustomer(s).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
		}).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NO_CONTENT));
	}

	//================================================
	
	@GetMapping("/dni/{dni}")
	public Mono<ResponseEntity<Customer>> listarCLienteDni(@PathVariable String dni) {
		System.out.println("listarClienteDNI["+dni);
		return clientService.viewDniCliente(dni)
				.map(p -> ResponseEntity
						.ok()
						.contentType(MediaType.APPLICATION_JSON).body(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());		
	}
	
}



