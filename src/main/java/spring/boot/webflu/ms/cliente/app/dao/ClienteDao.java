package spring.boot.webflu.ms.cliente.app.dao;


import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import reactor.core.publisher.Mono;
import spring.boot.webflu.ms.cliente.app.documents.Client;


public interface ClienteDao extends ReactiveMongoRepository<Client, String> {

	//buscar el cliente por dni
	@Query("{'numdoc' : ?0}")
	Mono<Client> viewDniCliente(String dni);
	
	
}
