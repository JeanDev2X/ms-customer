package spring.boot.webflu.ms.cliente.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import spring.boot.webflu.ms.cliente.app.documents.TipoCuentaClient;
import spring.boot.webflu.ms.cliente.app.service.TipoCuentaClienteService;

@RequestMapping("/api/TipoCliente")
public class TipoCuentaClienteControllers {

	
	@Autowired
	private TipoCuentaClienteService tipoClienteService;
	

	@GetMapping
	public Mono<ResponseEntity<Flux<TipoCuentaClient>>> findAll() 
	{
		return Mono.just(
				ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(tipoClienteService.findAllTipoCliente())
				);
	}
	
	
	
	@GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Mono<ResponseEntity<TipoCuentaClient>> viewId(@PathVariable("id") String id){
		return tipoClienteService.findByIdTipoCliente(id).map(p-> ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());	
	}
	
	@PutMapping
	public Mono<TipoCuentaClient> updateClientePersonal(@RequestBody TipoCuentaClient tipoCliente)
	{
		//System.out.println(cliente.toString());
		return tipoClienteService.saveTipoCliente(tipoCliente);
	}	

}
