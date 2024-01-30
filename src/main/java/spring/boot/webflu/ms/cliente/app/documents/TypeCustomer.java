package spring.boot.webflu.ms.cliente.app.documents;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "TypeCustomer")
public class TypeCustomer {

	private String id;
	@NotEmpty
	private String descripcion;
	
	public TypeCustomer(String id,String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}

	public TypeCustomer() {
		
	}
	
}
