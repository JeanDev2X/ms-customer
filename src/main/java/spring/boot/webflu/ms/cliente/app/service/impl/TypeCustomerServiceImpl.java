package spring.boot.webflu.ms.cliente.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.boot.webflu.ms.cliente.app.dao.TypeAccountCustomerDao;
import spring.boot.webflu.ms.cliente.app.documents.TypeCustomer;
import spring.boot.webflu.ms.cliente.app.service.TypeCustomerService;

@Service
public class TypeCustomerServiceImpl implements TypeCustomerService {

	@Autowired
	public TypeAccountCustomerDao tipoClienteDao;

	@Override
	public Flux<TypeCustomer> findAllTipoCustomer() {
		return tipoClienteDao.findAll();

	}

	@Override
	public Mono<TypeCustomer> findByIdTipoCustomer(String id) {
		return tipoClienteDao.findById(id);

	}

	@Override
	public Mono<TypeCustomer> saveTipoCustomer(TypeCustomer tipoCliente) {
		return tipoClienteDao.save(tipoCliente);
	}
}
