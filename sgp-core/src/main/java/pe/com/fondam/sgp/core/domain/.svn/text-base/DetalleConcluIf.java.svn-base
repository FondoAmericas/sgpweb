/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.fondam.sgp.core.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "detalle_conclu_if")
public class DetalleConcluIf implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 5134412631024689526L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "Detalle_Conclu_IF_ID")
    private Integer detalleConcluIFID;
    
    @Basic(optional = false)
    @Column(name = "fl_idtablaesp_cabecera_conclu_IF")
    private int flidtablaespcabeceraconcluIF;
    
    @Transient
    private String descripcionCabeceraConcluIF;
    
    @Lob
    @Basic(optional = false)
    @Column(name = "descripcion_conclusion")
    private String descripcionConclusion;
    public DetalleConcluIf() {
    }

    public DetalleConcluIf(Integer detalleConcluIFID) {
        this.detalleConcluIFID = detalleConcluIFID;
    }

    public DetalleConcluIf(Integer detalleConcluIFID, int flidtablaespcabeceraconcluIF, String descripcionConclusion) {
        this.detalleConcluIFID = detalleConcluIFID;
        this.flidtablaespcabeceraconcluIF = flidtablaespcabeceraconcluIF;
        this.descripcionConclusion = descripcionConclusion;
    }

    public Integer getDetalleConcluIFID() {
        return detalleConcluIFID;
    }

    public void setDetalleConcluIFID(Integer detalleConcluIFID) {
        this.detalleConcluIFID = detalleConcluIFID;
    }

    public int getFlidtablaespcabeceraconcluIF() {
        return flidtablaespcabeceraconcluIF;
    }

    public void setFlidtablaespcabeceraconcluIF(int flidtablaespcabeceraconcluIF) {
        this.flidtablaespcabeceraconcluIF = flidtablaespcabeceraconcluIF;
    }

    public String getDescripcionConclusion() {
        return descripcionConclusion;
    }

    public void setDescripcionConclusion(String descripcionConclusion) {
        this.descripcionConclusion = descripcionConclusion;
    }
    public void setDescripcionCabeceraConcluIF(
			String descripcionCabeceraConcluIF) {
		this.descripcionCabeceraConcluIF = descripcionCabeceraConcluIF;
	}

	public String getDescripcionCabeceraConcluIF() {
		return descripcionCabeceraConcluIF;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleConcluIFID != null ? detalleConcluIFID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleConcluIf)) {
            return false;
        }
        DetalleConcluIf other = (DetalleConcluIf) object;
        if ((this.detalleConcluIFID == null && other.detalleConcluIFID != null) || (this.detalleConcluIFID != null && !this.detalleConcluIFID.equals(other.detalleConcluIFID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.DetalleConcluIf[detalleConcluIFID=" + detalleConcluIFID + "]";
    }

}
