package spring.boot.webflu.ms.cliente.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.boot.webflu.ms.cliente.app.dao.TipoCuentaClienteDao;
import spring.boot.webflu.ms.cliente.app.documents.TipoCuentaClient;
import spring.boot.webflu.ms.cliente.app.service.TipoCuentaClienteService;

@Service
public class TipoCuentaClienteServiceImpl implements TipoCuentaClienteService{

	
	@Autowired
	public TipoCuentaClienteDao tipoClienteDao;
	
	@Override
	public Flux<TipoCuentaClient> findAllTipoCliente()
	{
	return tipoClienteDao.findAll();
	
	}
	@Override
	public Mono<TipoCuentaClient> findByIdTipoCliente(String id)
	{
	return tipoClienteDao.findByIdTipo(id);
	
	}
	
	@Override
	public Mono<TipoCuentaClient> saveTipoCliente(TipoCuentaClient tipoCliente)
	{
	return tipoClienteDao.save(tipoCliente);
	}
}
