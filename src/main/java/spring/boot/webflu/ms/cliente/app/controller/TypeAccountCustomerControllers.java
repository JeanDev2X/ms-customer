package spring.boot.webflu.ms.cliente.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.boot.webflu.ms.cliente.app.documents.Customer;
import spring.boot.webflu.ms.cliente.app.documents.TypeCustomer;
import spring.boot.webflu.ms.cliente.app.service.TypeCustomerService;

@RequestMapping("/api/TypeCustomer")
@RestController
public class TypeAccountCustomerControllers {

	
	@Autowired
	private TypeCustomerService tipoClienteService;
	

	@GetMapping
	public Mono<ResponseEntity<Flux<TypeCustomer>>> findAll() 
	{
		return Mono.just(
				ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(tipoClienteService.findAllTipoCustomer())
				);
	}
	
	
	
//	@GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public Mono<ResponseEntity<TypeCustomer>> viewId(@PathVariable("id") String id){
//		return tipoClienteService.findByIdTipoCustomer(id).map(p-> ResponseEntity.ok()
//				.contentType(MediaType.APPLICATION_JSON_UTF8)
//				.body(p))
//				.defaultIfEmpty(ResponseEntity.notFound().build());	
//	}
	
	//LISTA CLIENTES POR ID
	@GetMapping("/{id}")
	public Mono<ResponseEntity<TypeCustomer>> viewId(@PathVariable String id) {
		return tipoClienteService.findByIdTipoCustomer(id)
				.map(p -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@PutMapping
	public Mono<TypeCustomer> updateClientePersonal(@RequestBody TypeCustomer tipoCliente)
	{
		//System.out.println(cliente.toString());
		return tipoClienteService.saveTipoCustomer(tipoCliente);
	}	

}
