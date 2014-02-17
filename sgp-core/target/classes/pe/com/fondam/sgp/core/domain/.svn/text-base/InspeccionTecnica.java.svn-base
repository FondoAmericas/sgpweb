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
@Table(name = "inspeccion_tecnica")

public class InspeccionTecnica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "Inspeccion_Tecnica_ID")
    private Integer inspeccionTecnicaID;
    
    @Column(name = "porcentaje_avance")
    private double porcentajeAvance;
    
    @Column(name = "avance_fisico")
    private int avanceFisico;
    @JoinColumn(name = "Informe_Visita_Campo_ID", referencedColumnName = "Informe_Visita_Campo_ID")
    @ManyToOne(optional = false)
    private InformeVisitaCampo informeVisitaCampo;
    @JoinColumn(name = "Meta_Por_Actividad_ID", referencedColumnName = "Meta_Por_Actividad_ID")
    @ManyToOne(optional = false)
    private MetaPorActividad metaPorActividad;

    public InspeccionTecnica() {
    }

    public InspeccionTecnica(Integer inspeccionTecnicaID) {
        this.inspeccionTecnicaID = inspeccionTecnicaID;
    }

    public InspeccionTecnica(Integer inspeccionTecnicaID, double porcentajeAvance, int avanceFisico) {
        this.inspeccionTecnicaID = inspeccionTecnicaID;
        this.porcentajeAvance = porcentajeAvance;
        this.avanceFisico = avanceFisico;
    }

    public Integer getInspeccionTecnicaID() {
        return inspeccionTecnicaID;
    }

    public void setInspeccionTecnicaID(Integer inspeccionTecnicaID) {
        this.inspeccionTecnicaID = inspeccionTecnicaID;
    }

    public double getPorcentajeAvance() {
        return porcentajeAvance;
    }

    public void setPorcentajeAvance(double porcentajeAvance) {
        this.porcentajeAvance = porcentajeAvance;
    }

    public int getAvanceFisico() {
        return avanceFisico;
    }

    public void setAvanceFisico(int avanceFisico) {
        this.avanceFisico = avanceFisico;
    }

    public InformeVisitaCampo getInformeVisitaCampo() {
        return informeVisitaCampo;
    }

    public void setInformeVisitaCampo(InformeVisitaCampo informeVisitaCampo) {
        this.informeVisitaCampo = informeVisitaCampo;
    }

    public MetaPorActividad getMetaPorActividad() {
        return metaPorActividad;
    }

    public void setMetaPorActividad(MetaPorActividad metaPorActividad) {
        this.metaPorActividad = metaPorActividad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (inspeccionTecnicaID != null ? inspeccionTecnicaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InspeccionTecnica)) {
            return false;
        }
        InspeccionTecnica other = (InspeccionTecnica) object;
        if ((this.inspeccionTecnicaID == null && other.inspeccionTecnicaID != null) || (this.inspeccionTecnicaID != null && !this.inspeccionTecnicaID.equals(other.inspeccionTecnicaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.InspeccionTecnica[inspeccionTecnicaID=" + inspeccionTecnicaID + "]";
    }

}
