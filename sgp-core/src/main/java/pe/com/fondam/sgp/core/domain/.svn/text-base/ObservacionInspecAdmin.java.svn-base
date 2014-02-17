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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "observacion_inspec_admin")

public class ObservacionInspecAdmin implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "Observacion_Inspec_Admin_ID")
    private Integer observacionInspecAdminID;
    
    @Column(name = "fk_idtablaesp_tipo_supev_admin")
    private int fkIdtablaespTipoSupevAdmin;
    
    @Lob
    @Column(name = "observacion")
    private String observacion;
    @JoinColumn(name = "Informe_Visita_Campo_ID", referencedColumnName = "Informe_Visita_Campo_ID")
    @ManyToOne(optional = false)
    private InformeVisitaCampo informeVisitaCampo;

    public ObservacionInspecAdmin() {
    }

    public ObservacionInspecAdmin(Integer observacionInspecAdminID) {
        this.observacionInspecAdminID = observacionInspecAdminID;
    }

    public ObservacionInspecAdmin(Integer observacionInspecAdminID, int fkIdtablaespTipoSupevAdmin, String observacion) {
        this.observacionInspecAdminID = observacionInspecAdminID;
        this.fkIdtablaespTipoSupevAdmin = fkIdtablaespTipoSupevAdmin;
        this.observacion = observacion;
    }

    public Integer getObservacionInspecAdminID() {
        return observacionInspecAdminID;
    }

    public void setObservacionInspecAdminID(Integer observacionInspecAdminID) {
        this.observacionInspecAdminID = observacionInspecAdminID;
    }

    public int getFkIdtablaespTipoSupevAdmin() {
        return fkIdtablaespTipoSupevAdmin;
    }

    public void setFkIdtablaespTipoSupevAdmin(int fkIdtablaespTipoSupevAdmin) {
        this.fkIdtablaespTipoSupevAdmin = fkIdtablaespTipoSupevAdmin;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public InformeVisitaCampo getInformeVisitaCampo() {
        return informeVisitaCampo;
    }

    public void setInformeVisitaCampo(InformeVisitaCampo informeVisitaCampo) {
        this.informeVisitaCampo = informeVisitaCampo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (observacionInspecAdminID != null ? observacionInspecAdminID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ObservacionInspecAdmin)) {
            return false;
        }
        ObservacionInspecAdmin other = (ObservacionInspecAdmin) object;
        if ((this.observacionInspecAdminID == null && other.observacionInspecAdminID != null) || (this.observacionInspecAdminID != null && !this.observacionInspecAdminID.equals(other.observacionInspecAdminID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.ObservacionInspecAdmin[observacionInspecAdminID=" + observacionInspecAdminID + "]";
    }

}
