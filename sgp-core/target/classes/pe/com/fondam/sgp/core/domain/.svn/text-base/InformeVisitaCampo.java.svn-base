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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "informe_visita_campo")

public class InformeVisitaCampo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "Informe_Visita_Campo_ID")
    private Integer informeVisitaCampoID;
    
    @Column(name = "fk_idtablaesp_tipo_inspeccion")
    private int fkIdtablaespTipoInspeccion;
    
    @Column(name = "fk_idtablaesp_modalidad_inspec")
    private int fkIdtablaespModalidadInspec;
    
    @Column(name = "fk_id_detalle_estado_cab_est_inf_visita_campo")
    private int fkIdDetalleEstadoCabEstInfVisitaCampo;
    
    @Column(name = "fecha_inspeccion")
    @Temporal(TemporalType.DATE)
    private Date fechaInspeccion;
    
    @Column(name = "fecha_presentacion")
    @Temporal(TemporalType.DATE)
    private Date fechaPresentacion;
    
    @Column(name = "fecha_inicio_inspeccion")
    @Temporal(TemporalType.DATE)
    private Date fechaInicioInspeccion;
    
    @Column(name = "fecha_fin_inspeccion")
    @Temporal(TemporalType.DATE)
    private Date fechaFinInspeccion;
    
    @Lob
    @Column(name = "alcance")
    private String alcance;
    
    @Lob
    @Column(name = "descripcion_alcance")
    private String descripcionAlcance;
    
    @Lob
    @Column(name = "aspecto_importante")
    private String aspectoImportante;
    
    @Lob
    @Column(name = "conclusion_recomendacion")
    private String conclusionRecomendacion;
   
    @JoinColumn(name = "Inspector_ID", referencedColumnName = "Inspector_ID")
    @ManyToOne(optional = false)
    private Inspector inspector;
    @JoinColumn(name = "Cronograma_Visita_ID", referencedColumnName = "Cronograma_Visita_ID")
    @ManyToOne(optional = false)
    private CronogramaVisita cronogramaVisita;
    @JoinColumn(name = "Dato_Proyecto_ID", referencedColumnName = "Dato_Proyecto_ID")
    @ManyToOne(optional = false)
    private DatoProyecto datoProyecto;
  

    public InformeVisitaCampo() {
    }

    public InformeVisitaCampo(Integer informeVisitaCampoID) {
        this.informeVisitaCampoID = informeVisitaCampoID;
    }

    public InformeVisitaCampo(Integer informeVisitaCampoID, int fkIdtablaespTipoInspeccion, int fkIdtablaespModalidadInspec, int fkIdDetalleEstadoCabEstInfVisitaCampo, Date fechaInspeccion, Date fechaPresentacion, Date fechaInicioInspeccion, Date fechaFinInspeccion, String alcance, String descripcionAlcance, String aspectoImportante, String conclusionRecomendacion) {
        this.informeVisitaCampoID = informeVisitaCampoID;
        this.fkIdtablaespTipoInspeccion = fkIdtablaespTipoInspeccion;
        this.fkIdtablaespModalidadInspec = fkIdtablaespModalidadInspec;
        this.fkIdDetalleEstadoCabEstInfVisitaCampo = fkIdDetalleEstadoCabEstInfVisitaCampo;
        this.fechaInspeccion = fechaInspeccion;
        this.fechaPresentacion = fechaPresentacion;
        this.fechaInicioInspeccion = fechaInicioInspeccion;
        this.fechaFinInspeccion = fechaFinInspeccion;
        this.alcance = alcance;
        this.descripcionAlcance = descripcionAlcance;
        this.aspectoImportante = aspectoImportante;
        this.conclusionRecomendacion = conclusionRecomendacion;
    }

    public Integer getInformeVisitaCampoID() {
        return informeVisitaCampoID;
    }

    public void setInformeVisitaCampoID(Integer informeVisitaCampoID) {
        this.informeVisitaCampoID = informeVisitaCampoID;
    }

    public int getFkIdtablaespTipoInspeccion() {
        return fkIdtablaespTipoInspeccion;
    }

    public void setFkIdtablaespTipoInspeccion(int fkIdtablaespTipoInspeccion) {
        this.fkIdtablaespTipoInspeccion = fkIdtablaespTipoInspeccion;
    }

    public int getFkIdtablaespModalidadInspec() {
        return fkIdtablaespModalidadInspec;
    }

    public void setFkIdtablaespModalidadInspec(int fkIdtablaespModalidadInspec) {
        this.fkIdtablaespModalidadInspec = fkIdtablaespModalidadInspec;
    }

    public int getFkIdDetalleEstadoCabEstInfVisitaCampo() {
        return fkIdDetalleEstadoCabEstInfVisitaCampo;
    }

    public void setFkIdDetalleEstadoCabEstInfVisitaCampo(int fkIdDetalleEstadoCabEstInfVisitaCampo) {
        this.fkIdDetalleEstadoCabEstInfVisitaCampo = fkIdDetalleEstadoCabEstInfVisitaCampo;
    }

    public Date getFechaInspeccion() {
        return fechaInspeccion;
    }

    public void setFechaInspeccion(Date fechaInspeccion) {
        this.fechaInspeccion = fechaInspeccion;
    }

    public Date getFechaPresentacion() {
        return fechaPresentacion;
    }

    public void setFechaPresentacion(Date fechaPresentacion) {
        this.fechaPresentacion = fechaPresentacion;
    }

    public Date getFechaInicioInspeccion() {
        return fechaInicioInspeccion;
    }

    public void setFechaInicioInspeccion(Date fechaInicioInspeccion) {
        this.fechaInicioInspeccion = fechaInicioInspeccion;
    }

    public Date getFechaFinInspeccion() {
        return fechaFinInspeccion;
    }

    public void setFechaFinInspeccion(Date fechaFinInspeccion) {
        this.fechaFinInspeccion = fechaFinInspeccion;
    }

    public String getAlcance() {
        return alcance;
    }

    public void setAlcance(String alcance) {
        this.alcance = alcance;
    }

    public String getDescripcionAlcance() {
        return descripcionAlcance;
    }

    public void setDescripcionAlcance(String descripcionAlcance) {
        this.descripcionAlcance = descripcionAlcance;
    }

    public String getAspectoImportante() {
        return aspectoImportante;
    }

    public void setAspectoImportante(String aspectoImportante) {
        this.aspectoImportante = aspectoImportante;
    }

    public String getConclusionRecomendacion() {
        return conclusionRecomendacion;
    }

    public void setConclusionRecomendacion(String conclusionRecomendacion) {
        this.conclusionRecomendacion = conclusionRecomendacion;
    }

  

    public Inspector getInspector() {
        return inspector;
    }

    public void setInspector(Inspector inspector) {
        this.inspector = inspector;
    }

    public CronogramaVisita getCronogramaVisita() {
        return cronogramaVisita;
    }

    public void setCronogramaVisita(CronogramaVisita cronogramaVisita) {
        this.cronogramaVisita = cronogramaVisita;
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
        hash += (informeVisitaCampoID != null ? informeVisitaCampoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InformeVisitaCampo)) {
            return false;
        }
        InformeVisitaCampo other = (InformeVisitaCampo) object;
        if ((this.informeVisitaCampoID == null && other.informeVisitaCampoID != null) || (this.informeVisitaCampoID != null && !this.informeVisitaCampoID.equals(other.informeVisitaCampoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.InformeVisitaCampo[informeVisitaCampoID=" + informeVisitaCampoID + "]";
    }

}
