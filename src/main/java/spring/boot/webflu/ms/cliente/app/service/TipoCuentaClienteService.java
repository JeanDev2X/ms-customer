package spring.boot.webflu.ms.cliente.app.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.boot.webflu.ms.cliente.app.documents.TipoCuentaClient;

public interface TipoCuentaClienteService {
	
	Flux<TipoCuentaClient> findAllTipoCliente();
	Mono<TipoCuentaClient> findByIdTipoCliente(String id);
	Mono<TipoCuentaClient> saveTipoCliente(TipoCuentaClient tipoCliente);
	
}
