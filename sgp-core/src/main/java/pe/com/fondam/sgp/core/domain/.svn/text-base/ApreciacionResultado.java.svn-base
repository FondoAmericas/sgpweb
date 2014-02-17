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
@Table(name = "apreciacion_resultado")
public class ApreciacionResultado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     
    @Column(name = "Apreciacion_Resultado_ID")
    private Integer apreciacionResultadoID;
     
    @Column(name = "fk_idtablaesp_apreciacion_resultado_ra")
    private int fkIdtablaespApreciacionResultadoRa;
     
    @Lob
    @Column(name = "comentario")
    private String comentario;
    
    @ManyToOne
    @JoinColumn(name = "Reporte_Avance_ID", referencedColumnName = "Reporte_Avance_ID")
    private ReporteAvance reporteAvance;

    public Integer getApreciacionResultadoID() {
        return apreciacionResultadoID;
    }

    public void setApreciacionResultadoID(Integer apreciacionResultadoID) {
        this.apreciacionResultadoID = apreciacionResultadoID;
    }

    public int getFkIdtablaespApreciacionResultadoRa() {
        return fkIdtablaespApreciacionResultadoRa;
    }

    public void setFkIdtablaespApreciacionResultadoRa(int fkIdtablaespApreciacionResultadoRa) {
        this.fkIdtablaespApreciacionResultadoRa = fkIdtablaespApreciacionResultadoRa;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public ReporteAvance getReporteAvance() {
        return reporteAvance;
    }

    public void setReporteAvance(ReporteAvance reporteAvance) {
        this.reporteAvance = reporteAvance;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (apreciacionResultadoID != null ? apreciacionResultadoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ApreciacionResultado)) {
            return false;
        }
        ApreciacionResultado other = (ApreciacionResultado) object;
        if ((this.apreciacionResultadoID == null && other.apreciacionResultadoID != null) || (this.apreciacionResultadoID != null && !this.apreciacionResultadoID.equals(other.apreciacionResultadoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.ApreciacionResultado[apreciacionResultadoID=" + apreciacionResultadoID + "]";
    }

}
