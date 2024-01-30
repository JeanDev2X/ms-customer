package spring.boot.webflu.ms.cliente.app.dao;


import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import reactor.core.publisher.Mono;
import spring.boot.webflu.ms.cliente.app.documents.Customer;

public interface CustomerDao extends ReactiveMongoRepository<Customer, String> {

	//buscar el cliente por dni
	@Query("{'numdoc' : ?0}")
	Mono<Customer> viewDniCliente(String dni);
	
}
