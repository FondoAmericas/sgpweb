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
@Table(name = "conclusion_recomendacion")
public class ConclusionRecomendacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     
    @Column(name = "Conclusion_Recomendacion_ID")
    private Integer conclusionRecomendacionID;
     
    @Column(name = "fk_idtablaesp_tipo_observacion")
    private int fkIdtablaespTipoObservacion;

    @Lob
    @Column(name = "conclusion")
    private String conclusion;
     
    @Lob
    @Column(name = "recomendacion")
    private String recomendacion;
     
    @Lob
    @Column(name = "accion_tomada")
    private String accionTomada;
     
    @Column(name = "requiere_seguimiento")
    private int requiereSeguimiento;
    
    @ManyToOne
    @JoinColumn(name = "Informe_Visita_Campo_ID", referencedColumnName = "Informe_Visita_Campo_ID")
    private InformeVisitaCampo informeVisitaCampo;

   
    public Integer getConclusionRecomendacionID() {
        return conclusionRecomendacionID;
    }

    public void setConclusionRecomendacionID(Integer conclusionRecomendacionID) {
        this.conclusionRecomendacionID = conclusionRecomendacionID;
    }

    public int getFkIdtablaespTipoObservacion() {
        return fkIdtablaespTipoObservacion;
    }

    public void setFkIdtablaespTipoObservacion(int fkIdtablaespTipoObservacion) {
        this.fkIdtablaespTipoObservacion = fkIdtablaespTipoObservacion;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getRecomendacion() {
        return recomendacion;
    }

    public void setRecomendacion(String recomendacion) {
        this.recomendacion = recomendacion;
    }

    public String getAccionTomada() {
        return accionTomada;
    }

    public void setAccionTomada(String accionTomada) {
        this.accionTomada = accionTomada;
    }

    public int getRequiereSeguimiento() {
        return requiereSeguimiento;
    }

    public void setRequiereSeguimiento(int requiereSeguimiento) {
        this.requiereSeguimiento = requiereSeguimiento;
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
        hash += (conclusionRecomendacionID != null ? conclusionRecomendacionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConclusionRecomendacion)) {
            return false;
        }
        ConclusionRecomendacion other = (ConclusionRecomendacion) object;
        if ((this.conclusionRecomendacionID == null && other.conclusionRecomendacionID != null) || (this.conclusionRecomendacionID != null && !this.conclusionRecomendacionID.equals(other.conclusionRecomendacionID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.ConclusionRecomendacion[conclusionRecomendacionID=" + conclusionRecomendacionID + "]";
    }

}
