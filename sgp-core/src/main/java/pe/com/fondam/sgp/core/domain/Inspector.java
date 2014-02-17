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

@Entity
@Table(name = "inspector")

public class Inspector implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "Inspector_ID")
    private Integer inspectorID;
    
    @Column(name = "monto_viatico")
    private double montoViatico;
    
    @Column(name = "fk_idtablaesp_tipo_moneda")
    private int fkIdtablaespTipoMoneda;
   
    @JoinColumn(name = "Dato_Usuario_ID", referencedColumnName = "Dato_Usuario_ID")
    @ManyToOne(optional = false)
    private DatoUsuario datoUsuario;
    @JoinColumn(name = "Cronograma_Visita_ID", referencedColumnName = "Cronograma_Visita_ID")
    @ManyToOne(optional = false)
    private CronogramaVisita cronogramaVisita;

    public Inspector() {
    }

    public Inspector(Integer inspectorID) {
        this.inspectorID = inspectorID;
    }

    public Inspector(Integer inspectorID, double montoViatico, int fkIdtablaespTipoMoneda) {
        this.inspectorID = inspectorID;
        this.montoViatico = montoViatico;
        this.fkIdtablaespTipoMoneda = fkIdtablaespTipoMoneda;
    }

    public Integer getInspectorID() {
        return inspectorID;
    }

    public void setInspectorID(Integer inspectorID) {
        this.inspectorID = inspectorID;
    }

    public double getMontoViatico() {
        return montoViatico;
    }

    public void setMontoViatico(double montoViatico) {
        this.montoViatico = montoViatico;
    }

    public int getFkIdtablaespTipoMoneda() {
        return fkIdtablaespTipoMoneda;
    }

    public void setFkIdtablaespTipoMoneda(int fkIdtablaespTipoMoneda) {
        this.fkIdtablaespTipoMoneda = fkIdtablaespTipoMoneda;
    }


    public DatoUsuario getDatoUsuario() {
        return datoUsuario;
    }

    public void setDatoUsuario(DatoUsuario datoUsuario) {
        this.datoUsuario = datoUsuario;
    }

    public CronogramaVisita getCronogramaVisita() {
        return cronogramaVisita;
    }

    public void setCronogramaVisita(CronogramaVisita cronogramaVisita) {
        this.cronogramaVisita = cronogramaVisita;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (inspectorID != null ? inspectorID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inspector)) {
            return false;
        }
        Inspector other = (Inspector) object;
        if ((this.inspectorID == null && other.inspectorID != null) || (this.inspectorID != null && !this.inspectorID.equals(other.inspectorID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.Inspector[inspectorID=" + inspectorID + "]";
    }

}
