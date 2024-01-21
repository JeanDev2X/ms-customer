package spring.boot.webflu.ms.cliente.app.documents;


import javax.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;
import lombok.Setter;

//clase de la collection CLIENTES y su tipo de cliente
@Getter
@Setter
@Document(collection ="Customer")
public class Customer {
	
	@Id
	@NotEmpty
	private String id;
	@NotEmpty
	private String numdoc; //numero de documento - dni
	@NotEmpty
	private String nombres; //razon social - descripcion
//	@NotEmpty
//	private String apellidos;
//	@NotEmpty
//	private String sexo;
//	@NotEmpty
	private String telefono;
//	@NotEmpty
//	private String edad;
	@NotEmpty
	private String correo;
	@NotEmpty
	private TipoCuentaClient tipoCliente;	
	@NotEmpty
	private String codigoBanco;
	
	public Customer(String numdoc,String nombres,
			TipoCuentaClient tipoCliente,String codigoBanco) {
		this.numdoc = numdoc;
		this.nombres = nombres;
		this.tipoCliente = tipoCliente;
		this.codigoBanco = codigoBanco;
	}

	public Customer() {
	
	}
	
}










