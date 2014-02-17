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
@Table(name = "doc_interno_fondam")
public class DocInternoFondam implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     
    @Column(name = "Doc_Interno_Fondam_ID")
    private Integer docInternoFondamID;
     
    @Column(name = "fk_idtablaesp_tipo_informe")
    private int fkIdtablaespTipoInforme;
     
    @Column(name = "numero_documento")
    private int numeroDocumento;
     
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Lob
    @Column(name = "observacion")
    private String observacion;
    
    @ManyToOne
    @JoinColumn(name = "Reporte_Avance_ID", referencedColumnName = "Reporte_Avance_ID")
    private ReporteAvance reporteAvance;
   
    @ManyToOne
    @JoinColumn(name = "Liquidacion_Gasto_ID", referencedColumnName = "Liquidacion_Gasto_ID")
    private LiquidacionGasto liquidacionGasto;

   
    public Integer getDocInternoFondamID() {
        return docInternoFondamID;
    }

    public void setDocInternoFondamID(Integer docInternoFondamID) {
        this.docInternoFondamID = docInternoFondamID;
    }

    public int getFkIdtablaespTipoInforme() {
        return fkIdtablaespTipoInforme;
    }

    public void setFkIdtablaespTipoInforme(int fkIdtablaespTipoInforme) {
        this.fkIdtablaespTipoInforme = fkIdtablaespTipoInforme;
    }

    public int getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(int numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public ReporteAvance getReporteAvance() {
        return reporteAvance;
    }

    public void setReporteAvance(ReporteAvance reporteAvance) {
        this.reporteAvance = reporteAvance;
    }

    public LiquidacionGasto getLiquidacionGasto() {
        return liquidacionGasto;
    }

    public void setLiquidacionGasto(LiquidacionGasto liquidacionGasto) {
        this.liquidacionGasto = liquidacionGasto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (docInternoFondamID != null ? docInternoFondamID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocInternoFondam)) {
            return false;
        }
        DocInternoFondam other = (DocInternoFondam) object;
        if ((this.docInternoFondamID == null && other.docInternoFondamID != null) || (this.docInternoFondamID != null && !this.docInternoFondamID.equals(other.docInternoFondamID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.DocInternoFondam[docInternoFondamID=" + docInternoFondamID + "]";
    }

}
