/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.fondam.sgp.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "recurso_utilizado")

public class RecursoUtilizado implements Serializable {
   
	/**
	 * 
	 */
	private static final long serialVersionUID = -4661717989596196536L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "Recurso_Utilizado_ID")
    private Integer recursoUtilizadoID;
   
    @Column(name = "monto")
    private double monto;
   
    @Column(name = "fk_idtablaesp_tipo_moneda")
    private int fkIdtablaespTipoMoneda;
    
    @Transient
    private String descripcionTipoMoneda;
    
    @JoinColumn(name = "Bien_ID", referencedColumnName = "Bien_ID")
    @ManyToOne
    private Bien bien;
    
    @JoinColumn(name = "Fuente_Financiadora_ID", referencedColumnName = "Fuente_Financiadora_ID")
    @ManyToOne
    private FuenteFinanciadora fuenteFinanciadora;

    public RecursoUtilizado() {
    }

    public RecursoUtilizado(Integer recursoUtilizadoID) {
        this.recursoUtilizadoID = recursoUtilizadoID;
    }

    public RecursoUtilizado(Integer recursoUtilizadoID, double monto, int fkIdtablaespTipoMoneda) {
        this.recursoUtilizadoID = recursoUtilizadoID;
        this.monto = monto;
        this.fkIdtablaespTipoMoneda = fkIdtablaespTipoMoneda;
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

    public String getDescripcionTipoMoneda() {
		return descripcionTipoMoneda;
	}

	public void setDescripcionTipoMoneda(String descripcionTipoMoneda) {
		this.descripcionTipoMoneda = descripcionTipoMoneda;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (recursoUtilizadoID != null ? recursoUtilizadoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecursoUtilizado)) {
            return false;
        }
        RecursoUtilizado other = (RecursoUtilizado) object;
        if ((this.recursoUtilizadoID == null && other.recursoUtilizadoID != null) || (this.recursoUtilizadoID != null && !this.recursoUtilizadoID.equals(other.recursoUtilizadoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.RecursoUtilizado[recursoUtilizadoID=" + recursoUtilizadoID + "]";
    }

}
