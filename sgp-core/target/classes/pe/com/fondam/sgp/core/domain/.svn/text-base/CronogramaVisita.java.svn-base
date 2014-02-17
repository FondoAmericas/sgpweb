/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.fondam.sgp.core.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "cronograma_visita")
public class CronogramaVisita implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     
    @Column(name = "Cronograma_Visita_ID")
    private Integer cronogramaVisitaID;
     
    @Column(name = "fecha_visita_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaVisitaInicio;
     
    @Column(name = "fecha_visita_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaVisitaFin;
     
    @Column(name = "periodo")
    private String periodo;
    
    @ManyToOne
    @JoinColumn(name = "Ubicacion_Proyecto_ID", referencedColumnName = "Ubicacion_Proyecto_ID")
    private UbicacionProyecto ubicacionProyecto;
    
    @ManyToOne
    @JoinColumn(name = "Dato_Proyecto_ID", referencedColumnName = "Dato_Proyecto_ID")
    private DatoProyecto datoProyecto;
 

    public Integer getCronogramaVisitaID() {
        return cronogramaVisitaID;
    }

    public void setCronogramaVisitaID(Integer cronogramaVisitaID) {
        this.cronogramaVisitaID = cronogramaVisitaID;
    }

    public Date getFechaVisitaInicio() {
        return fechaVisitaInicio;
    }

    public void setFechaVisitaInicio(Date fechaVisitaInicio) {
        this.fechaVisitaInicio = fechaVisitaInicio;
    }

    public Date getFechaVisitaFin() {
        return fechaVisitaFin;
    }

    public void setFechaVisitaFin(Date fechaVisitaFin) {
        this.fechaVisitaFin = fechaVisitaFin;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public UbicacionProyecto getUbicacionProyecto() {
        return ubicacionProyecto;
    }

    public void setUbicacionProyecto(UbicacionProyecto ubicacionProyecto) {
        this.ubicacionProyecto = ubicacionProyecto;
    }

    public DatoProyecto getDatoProyecto() {
        return datoProyecto;
    }

    public void setDatoProyecto(DatoProyecto datoProyecto) {
        this.datoProyecto = datoProyecto;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cronogramaVisitaID != null ? cronogramaVisitaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CronogramaVisita)) {
            return false;
        }
        CronogramaVisita other = (CronogramaVisita) object;
        if ((this.cronogramaVisitaID == null && other.cronogramaVisitaID != null) || (this.cronogramaVisitaID != null && !this.cronogramaVisitaID.equals(other.cronogramaVisitaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.CronogramaVisita[cronogramaVisitaID=" + cronogramaVisitaID + "]";
    }

}
