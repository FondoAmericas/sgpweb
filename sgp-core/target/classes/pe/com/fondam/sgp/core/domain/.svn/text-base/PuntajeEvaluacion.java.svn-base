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
@Table(name = "puntaje_evaluacion")

public class PuntajeEvaluacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    @Column(name = "Puntaje_Evaluacion_ID")
    private Integer puntajeEvaluacionID;
    
    @Column(name = "maximo")
    private int maximo;
    
    @Column(name = "minimo")
    private int minimo;
    
    @Column(name = "minimo_aprobatorio")
    private int minimoAprobatorio;
    
    @Column(name = "fk_idtablaesp_tipo_evaluacion")
    private int fkIdtablaespTipoEvaluacion;
   
    @ManyToOne
    @JoinColumn(name = "Programa_ID", referencedColumnName = "Programa_ID")
    private Programa programa;

    public Integer getPuntajeEvaluacionID() {
        return puntajeEvaluacionID;
    }

    public void setPuntajeEvaluacionID(Integer puntajeEvaluacionID) {
        this.puntajeEvaluacionID = puntajeEvaluacionID;
    }

    public int getMaximo() {
        return maximo;
    }

    public void setMaximo(int maximo) {
        this.maximo = maximo;
    }

    public int getMinimo() {
        return minimo;
    }

    public void setMinimo(int minimo) {
        this.minimo = minimo;
    }

    public int getMinimoAprobatorio() {
        return minimoAprobatorio;
    }

    public void setMinimoAprobatorio(int minimoAprobatorio) {
        this.minimoAprobatorio = minimoAprobatorio;
    }

    public int getFkIdtablaespTipoEvaluacion() {
        return fkIdtablaespTipoEvaluacion;
    }

    public void setFkIdtablaespTipoEvaluacion(int fkIdtablaespTipoEvaluacion) {
        this.fkIdtablaespTipoEvaluacion = fkIdtablaespTipoEvaluacion;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (puntajeEvaluacionID != null ? puntajeEvaluacionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PuntajeEvaluacion)) {
            return false;
        }
        PuntajeEvaluacion other = (PuntajeEvaluacion) object;
        if ((this.puntajeEvaluacionID == null && other.puntajeEvaluacionID != null) || (this.puntajeEvaluacionID != null && !this.puntajeEvaluacionID.equals(other.puntajeEvaluacionID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.PuntajeEvaluacion[puntajeEvaluacionID=" + puntajeEvaluacionID + "]";
    }

}
