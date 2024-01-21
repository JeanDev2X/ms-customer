package spring.boot.webflu.ms.cliente.app.documents;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "TipoCliente")
public class TipoCuentaClient {

	private String idTipo;
	@NotEmpty
	private String descripcion;
	
	public TipoCuentaClient(String idTipo,String descripcion) {
		this.idTipo = idTipo;
		this.descripcion = descripcion;
	}

	public TipoCuentaClient() {
		
	}
	
}
