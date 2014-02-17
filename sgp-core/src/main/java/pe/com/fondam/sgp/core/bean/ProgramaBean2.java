package pe.com.fondam.sgp.core.bean;

public class ProgramaBean2 {

	private Integer programaID;
	private int duracionPrograma;
	private int fkIdtablaespTipoCuenta;
	private String tipoCuenta;
	private int fkIdtablaespModalidadFinancia;
	private String modalidadFinancia;
	private String nombrePrograma;
	private String nombreTipoPeriodo;
	
	public ProgramaBean2(){
		
	}

	public Integer getProgramaID() {
		return programaID;
	}

	public void setProgramaID(Integer programaID) {
		this.programaID = programaID;
	}

	public int getDuracionPrograma() {
		return duracionPrograma;
	}

	public void setDuracionPrograma(int duracionPrograma) {
		this.duracionPrograma = duracionPrograma;
	}

	public int getFkIdtablaespTipoCuenta() {
		return fkIdtablaespTipoCuenta;
	}

	public void setFkIdtablaespTipoCuenta(int fkIdtablaespTipoCuenta) {
		this.fkIdtablaespTipoCuenta = fkIdtablaespTipoCuenta;
	}

	public int getFkIdtablaespModalidadFinancia() {
		return fkIdtablaespModalidadFinancia;
	}

	public void setFkIdtablaespModalidadFinancia(int fkIdtablaespModalidadFinancia) {
		this.fkIdtablaespModalidadFinancia = fkIdtablaespModalidadFinancia;
	}

	public String getNombrePrograma() {
		return nombrePrograma;
	}

	public void setNombrePrograma(String nombrePrograma) {
		this.nombrePrograma = nombrePrograma;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setModalidadFinancia(String modalidadFinancia) {
		this.modalidadFinancia = modalidadFinancia;
	}

	public String getModalidadFinancia() {
		return modalidadFinancia;
	}

	public void setNombreTipoPeriodo(String nombreTipoPeriodo) {
		this.nombreTipoPeriodo = nombreTipoPeriodo;
	}

	public String getNombreTipoPeriodo() {
		return nombreTipoPeriodo;
	}
	
	
}
