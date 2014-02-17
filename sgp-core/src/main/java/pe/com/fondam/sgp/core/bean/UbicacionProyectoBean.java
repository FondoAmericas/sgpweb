package pe.com.fondam.sgp.core.bean;



public class UbicacionProyectoBean {

	private Integer ubicacionProyectoID;
	private String detalleUbicacion;
	private Integer depProvDistID;
	private Integer datoProyectoID;
	private boolean flagReferencia;
	
	

	public boolean isFlagReferencia() {
		return flagReferencia;
	}
	public void setFlagReferencia(boolean flagReferencia) {
		this.flagReferencia = flagReferencia;
	}
	public Integer getUbicacionProyectoID() {
		return ubicacionProyectoID;
	}
	public void setUbicacionProyectoID(Integer ubicacionProyectoID) {
		this.ubicacionProyectoID = ubicacionProyectoID;
	}
	public String getDetalleUbicacion() {
		return detalleUbicacion;
	}
	public void setDetalleUbicacion(String detalleUbicacion) {
		this.detalleUbicacion = detalleUbicacion;
	}
	public Integer getDepProvDistID() {
		return depProvDistID;
	}
	public void setDepProvDistID(Integer depProvDistID) {
		this.depProvDistID = depProvDistID;
	}
	public Integer getDatoProyectoID() {
		return datoProyectoID;
	}
	public void setDatoProyectoID(Integer datoProyectoID) {
		this.datoProyectoID = datoProyectoID;
	}

	
	
}
