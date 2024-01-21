package spring.boot.webflu.ms.cliente.app.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoCuentaBanco {
	@NotEmpty
	private String idTipo;
	@NotEmpty
	private String descripcion;
	
}
