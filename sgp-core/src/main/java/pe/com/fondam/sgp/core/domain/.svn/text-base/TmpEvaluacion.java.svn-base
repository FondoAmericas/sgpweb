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
@Table(name = "tmp_evaluacion")

public class TmpEvaluacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "tmp_Evaluacion_ID")
    private Integer tmpEvaluacionID;

    @Column(name = "calificacion")
    private double calificacion;
    
    @Lob
    @Column(name = "Observacion")
    private String observacion;
    
    @ManyToOne
    @JoinColumn(name = "Evaluador_ID", referencedColumnName = "Evaluador_ID")
    private Evaluador evaluadorID;
    
    @ManyToOne
    @JoinColumn(name = "tmp_Dato_Proyecto_ID", referencedColumnName = "TMP_Dato_Proyecto_ID")
    private TmpDatoProyecto tmpDatoProyectoID;

    public Integer getTmpEvaluacionID() {
        return tmpEvaluacionID;
    }

    public void setTmpEvaluacionID(Integer tmpEvaluacionID) {
        this.tmpEvaluacionID = tmpEvaluacionID;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Evaluador getEvaluadorID() {
        return evaluadorID;
    }

    public void setEvaluadorID(Evaluador evaluadorID) {
        this.evaluadorID = evaluadorID;
    }

    public TmpDatoProyecto getTmpDatoProyectoID() {
        return tmpDatoProyectoID;
    }

    public void setTmpDatoProyectoID(TmpDatoProyecto tmpDatoProyectoID) {
        this.tmpDatoProyectoID = tmpDatoProyectoID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tmpEvaluacionID != null ? tmpEvaluacionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmpEvaluacion)) {
            return false;
        }
        TmpEvaluacion other = (TmpEvaluacion) object;
        if ((this.tmpEvaluacionID == null && other.tmpEvaluacionID != null) || (this.tmpEvaluacionID != null && !this.tmpEvaluacionID.equals(other.tmpEvaluacionID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aaaa.TmpEvaluacion[ tmpEvaluacionID=" + tmpEvaluacionID + " ]";
    }
   
}

