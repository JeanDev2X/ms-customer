package spring.boot.webflu.ms.cliente.app.service;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.boot.webflu.ms.cliente.app.documents.Customer;


public interface CustomerService {

	Flux<Customer> findAllCustomer();
	Mono<Customer> findByIdCustomer(String id);
	Mono<Customer> saveCustomer(Customer clientePersonal);
	Mono<Void> deleteCustomer(Customer cliente);
	//---------------------------------------------------
	Mono<Customer> viewDniCliente(String dni);
}
