/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.fondam.sgp.core.bean;

import java.io.Serializable;

import pe.com.fondam.sgp.core.domain.DescripcionEf;
import pe.com.fondam.sgp.core.domain.InformeFinal;

public class EvaluacionFinalBean implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5152821904895544621L;
	private Integer evaluacionFinalID;
    private String comentario;
    private DescripcionEf descripcionEf;
    private InformeFinal informeFinal;

    public EvaluacionFinalBean() {
    }

    public EvaluacionFinalBean(Integer evaluacionFinalID) {
        this.evaluacionFinalID = evaluacionFinalID;
    }

    public EvaluacionFinalBean(Integer evaluacionFinalID, String comentario) {
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
        if (!(object instanceof EvaluacionFinalBean)) {
            return false;
        }
        EvaluacionFinalBean other = (EvaluacionFinalBean) object;
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
