package spring.boot.webflu.ms.cliente.app.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.boot.webflu.ms.cliente.app.documents.TypeCustomer;


public interface TypeCustomerService {
	
	Flux<TypeCustomer> findAllTipoCustomer();
	Mono<TypeCustomer> findByIdTipoCustomer(String id);
	Mono<TypeCustomer> saveTipoCustomer(TypeCustomer tipoCliente);
	
}
