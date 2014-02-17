package pe.com.fondam.sgp.core.report;

import java.util.List;

public class RptEstructuraInversionFinanciamiento {
	private String resultado;
	private String institucionNombre1;
	private String institucionNombre2;
	private String institucionNombre3;
	private List<RptEstructuraInversionFinanciamientoDet> detalles;

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getInstitucionNombre1() {
		return institucionNombre1;
	}

	public void setInstitucionNombre1(String institucionNombre1) {
		this.institucionNombre1 = institucionNombre1;
	}

	public String getInstitucionNombre2() {
		return institucionNombre2;
	}

	public void setInstitucionNombre2(String institucionNombre2) {
		this.institucionNombre2 = institucionNombre2;
	}

	public String getInstitucionNombre3() {
		return institucionNombre3;
	}

	public void setInstitucionNombre3(String institucionNombre3) {
		this.institucionNombre3 = institucionNombre3;
	}

	public List<RptEstructuraInversionFinanciamientoDet> getDetalles() {
		return detalles;
	}

	public void setDetalles(
			List<RptEstructuraInversionFinanciamientoDet> detalles) {
		this.detalles = detalles;
	}

}
