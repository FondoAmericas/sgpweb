package pe.com.fondam.sgp.core.domain;

import java.io.Serializable;

public class RecursoUtilizadoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 547597737209232644L;
	private Integer recursoUtilizadoID;   
    private double monto;   
    private int fkIdtablaespTipoMoneda;    
    private String descripcionTipoMoneda;    
    private Bien bien;    
    private FuenteFinanciadora fuenteFinanciadora;
    
    public RecursoUtilizadoBean(){
    	
    }

	public Integer getRecursoUtilizadoID() {
		return recursoUtilizadoID;
	}

	public void setRecursoUtilizadoID(Integer recursoUtilizadoID) {
		this.recursoUtilizadoID = recursoUtilizadoID;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public int getFkIdtablaespTipoMoneda() {
		return fkIdtablaespTipoMoneda;
	}

	public void setFkIdtablaespTipoMoneda(int fkIdtablaespTipoMoneda) {
		this.fkIdtablaespTipoMoneda = fkIdtablaespTipoMoneda;
	}

	public String getDescripcionTipoMoneda() {
		return descripcionTipoMoneda;
	}

	public void setDescripcionTipoMoneda(String descripcionTipoMoneda) {
		this.descripcionTipoMoneda = descripcionTipoMoneda;
	}

	public Bien getBien() {
		return bien;
	}

	public void setBien(Bien bien) {
		this.bien = bien;
	}

	public FuenteFinanciadora getFuenteFinanciadora() {
		return fuenteFinanciadora;
	}

	public void setFuenteFinanciadora(FuenteFinanciadora fuenteFinanciadora) {
		this.fuenteFinanciadora = fuenteFinanciadora;
	}
    
    
}
