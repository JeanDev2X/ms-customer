package spring.boot.webflu.ms.cliente.app.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import spring.boot.webflu.ms.cliente.app.documents.TypeCustomer;


public interface TypeAccountCustomerDao extends ReactiveMongoRepository<TypeCustomer, String> {
	
}
