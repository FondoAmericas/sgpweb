package pe.com.fondam.sgp.core.bean;

public class FuenteFinanciadoraBean {
	
	private Integer fuenteFinanciadoraID;
	private String defineSiEsEjecutor;
	private Integer fkIdtablaespTipoFuenteFinanciadora;
	private Double montoFinanciado;
	private Integer fkIdtablaespTipoMoneda;
	private Integer institucionID;
	private Integer datoProyectoID;
	
	public Integer getFuenteFinanciadoraID() {
		return fuenteFinanciadoraID;
	}
	public void setFuenteFinanciadoraID(Integer fuenteFinanciadoraID) {
		this.fuenteFinanciadoraID = fuenteFinanciadoraID;
	}
	public String getDefineSiEsEjecutor() {
		return defineSiEsEjecutor;
	}
	public void setDefineSiEsEjecutor(String defineSiEsEjecutor) {
		this.defineSiEsEjecutor = defineSiEsEjecutor;
	}
	public Integer getFkIdtablaespTipoFuenteFinanciadora() {
		return fkIdtablaespTipoFuenteFinanciadora;
	}
	public void setFkIdtablaespTipoFuenteFinanciadora(
			Integer fkIdtablaespTipoFuenteFinanciadora) {
		this.fkIdtablaespTipoFuenteFinanciadora = fkIdtablaespTipoFuenteFinanciadora;
	}
	public Double getMontoFinanciado() {
		return montoFinanciado;
	}
	public void setMontoFinanciado(Double montoFinanciado) {
		this.montoFinanciado = montoFinanciado;
	}
	public Integer getFkIdtablaespTipoMoneda() {
		return fkIdtablaespTipoMoneda;
	}
	public void setFkIdtablaespTipoMoneda(Integer fkIdtablaespTipoMoneda) {
		this.fkIdtablaespTipoMoneda = fkIdtablaespTipoMoneda;
	}
	public Integer getInstitucionID() {
		return institucionID;
	}
	public void setInstitucionID(Integer institucionID) {
		this.institucionID = institucionID;
	}
	public Integer getDatoProyectoID() {
		return datoProyectoID;
	}
	public void setDatoProyectoID(Integer datoProyectoID) {
		this.datoProyectoID = datoProyectoID;
	}
	

}
