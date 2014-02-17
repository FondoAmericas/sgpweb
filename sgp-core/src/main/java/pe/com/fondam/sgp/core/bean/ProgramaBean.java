package pe.com.fondam.sgp.core.bean;

public class ProgramaBean {

	private Integer programaID;
	private int duracionPrograma;
	private String nombrePrograma;
	private int fkIdtablaespModalidadFinancia;
	
	public ProgramaBean(){
		
	}
	
	public void setProgramaID(Integer programaID) {
		this.programaID = programaID;
	}
	public Integer getProgramaID() {
		return programaID;
	}
	public void setDuracionPrograma(int duracionPrograma) {
		this.duracionPrograma = duracionPrograma;
	}
	public int getDuracionPrograma() {
		return duracionPrograma;
	}
	public void setNombrePrograma(String nombrePrograma) {
		this.nombrePrograma = nombrePrograma;
	}
	public String getNombrePrograma() {
		return nombrePrograma;
	}
	public void setFkIdtablaespModalidadFinancia(
			int fkIdtablaespModalidadFinancia) {
		this.fkIdtablaespModalidadFinancia = fkIdtablaespModalidadFinancia;
	}

	public int getFkIdtablaespModalidadFinancia() {
		return fkIdtablaespModalidadFinancia;
	}
}
