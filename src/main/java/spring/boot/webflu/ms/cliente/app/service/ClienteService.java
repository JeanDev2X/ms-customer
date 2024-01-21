package spring.boot.webflu.ms.cliente.app.service;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.boot.webflu.ms.cliente.app.documents.Client;

public interface ClienteService {

	Flux<Client> findAllCliente();
	Mono<Client> findByIdCliente(String id);
	Mono<Client> saveCliente(Client clientePersonal);
	Mono<Void> deleteCliente(Client cliente);
	Mono<Client> viewDniCliente(String dni);
	
}
