package spring.boot.webflu.ms.cliente.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.boot.webflu.ms.cliente.app.dao.CustomerDao;
import spring.boot.webflu.ms.cliente.app.documents.Customer;
import spring.boot.webflu.ms.cliente.app.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	public CustomerDao clienteDao;

	@Override
	public Flux<Customer> findAllCustomer() {
		return clienteDao.findAll();

	}

	@Override
	public Mono<Customer> findByIdCustomer(String id) {
		return clienteDao.findById(id);

	}

	@Override
	public Mono<Customer> saveCustomer(Customer clientePersonal) {
		return clienteDao.save(clientePersonal);
	}

	@Override
	public Mono<Void> deleteCustomer(Customer cliente) {
		return clienteDao.delete(cliente);
	}

	//--------------------------------------------------
	
	@Override
	public Mono<Customer> viewDniCliente(String dni) {
		return clienteDao.viewDniCliente(dni);
	}

}
