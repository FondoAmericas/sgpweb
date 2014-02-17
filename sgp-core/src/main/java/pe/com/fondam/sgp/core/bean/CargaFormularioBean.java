package pe.com.fondam.sgp.core.bean;

import java.io.Serializable;

public class CargaFormularioBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8829615255333618357L;
	private byte[] formulario;
	
	public CargaFormularioBean(){
		
	}

	public byte[] getFormulario() {
		return formulario;
	}

	public void setFormulario(byte[] formulario) {
		this.formulario = formulario;
	}
	
	
}
