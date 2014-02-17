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
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "descripcion_ef")
public class DescripcionEf implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     
    @Column(name = "Descripcion_EF_ID")
    private Integer descripcionEFID;
     
    @Column(name = "fk_idtablaesp_eval_final_cabecera")
    private int fkIdtablaespEvalFinalCabecera;
    
    @Transient
    private String descripcionEvalFinal;

    @Lob
    @Column(name = "descripcion_final")
    private String descripcionFinal;

   
    public Integer getDescripcionEFID() {
        return descripcionEFID;
    }

    public void setDescripcionEFID(Integer descripcionEFID) {
        this.descripcionEFID = descripcionEFID;
    }

    public int getFkIdtablaespEvalFinalCabecera() {
        return fkIdtablaespEvalFinalCabecera;
    }

    public void setFkIdtablaespEvalFinalCabecera(int fkIdtablaespEvalFinalCabecera) {
        this.fkIdtablaespEvalFinalCabecera = fkIdtablaespEvalFinalCabecera;
    }

    public String getDescripcionFinal() {
        return descripcionFinal;
    }

    public void setDescripcionFinal(String descripcionFinal) {
        this.descripcionFinal = descripcionFinal;
    }


    public void setDescripcionEvalFinal(String descripcionEvalFinal) {
		this.descripcionEvalFinal = descripcionEvalFinal;
	}

	public String getDescripcionEvalFinal() {
		return descripcionEvalFinal;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (descripcionEFID != null ? descripcionEFID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DescripcionEf)) {
            return false;
        }
        DescripcionEf other = (DescripcionEf) object;
        if ((this.descripcionEFID == null && other.descripcionEFID != null) || (this.descripcionEFID != null && !this.descripcionEFID.equals(other.descripcionEFID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.DescripcionEf[descripcionEFID=" + descripcionEFID + "]";
    }

}
