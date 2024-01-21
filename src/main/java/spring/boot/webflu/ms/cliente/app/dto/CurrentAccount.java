package spring.boot.webflu.ms.cliente.app.dto;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CurrentAccount {
	
	@Id
	private String id;
	private String dni;
	private String numero_cuenta;
	private TypeCurrentAccount tipoProducto;
	private String fecha_afiliacion;
	private String fecha_caducidad;
	private double saldo;

	
	//private tipoProducto tipoCliente;
}










