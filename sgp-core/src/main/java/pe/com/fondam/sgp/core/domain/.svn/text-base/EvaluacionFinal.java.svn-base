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
@Table(name = "evaluacion_final")
public class EvaluacionFinal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     
    @Column(name = "Evaluacion_Final_ID")
    private Integer evaluacionFinalID;
     
    @Lob
    @Column(name = "comentario")
    private String comentario;
    
    @JoinColumn(name = "Descripcion_EF_ID", referencedColumnName = "Descripcion_EF_ID")
    @ManyToOne
    private DescripcionEf descripcionEf;
    
    @JoinColumn(name = "Informe_Final_ID", referencedColumnName = "Informe_Final_ID")
    @ManyToOne
    private InformeFinal informeFinal;

    public EvaluacionFinal() {
    }

    public EvaluacionFinal(Integer evaluacionFinalID) {
        this.evaluacionFinalID = evaluacionFinalID;
    }

    public EvaluacionFinal(Integer evaluacionFinalID, String comentario) {
        this.evaluacionFinalID = evaluacionFinalID;
        this.comentario = comentario;
    }

    public Integer getEvaluacionFinalID() {
        return evaluacionFinalID;
    }

    public void setEvaluacionFinalID(Integer evaluacionFinalID) {
        this.evaluacionFinalID = evaluacionFinalID;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public DescripcionEf getDescripcionEf() {
        return descripcionEf;
    }

    public void setDescripcionEf(DescripcionEf descripcionEf) {
        this.descripcionEf = descripcionEf;
    }

    public InformeFinal getInformeFinal() {
        return informeFinal;
    }

    public void setInformeFinal(InformeFinal informeFinal) {
        this.informeFinal = informeFinal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (evaluacionFinalID != null ? evaluacionFinalID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EvaluacionFinal)) {
            return false;
        }
        EvaluacionFinal other = (EvaluacionFinal) object;
        if ((this.evaluacionFinalID == null && other.evaluacionFinalID != null) || (this.evaluacionFinalID != null && !this.evaluacionFinalID.equals(other.evaluacionFinalID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.EvaluacionFinal[evaluacionFinalID=" + evaluacionFinalID + "]";
    }

}
