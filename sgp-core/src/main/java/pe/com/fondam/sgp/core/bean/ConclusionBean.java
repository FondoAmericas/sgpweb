/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.fondam.sgp.core.bean;

import java.io.Serializable;

import pe.com.fondam.sgp.core.domain.DetalleConcluIf;
import pe.com.fondam.sgp.core.domain.InformeFinal;

public class ConclusionBean implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7271539336166828123L;
	private Integer conclusionID;
    private String comentario;
    private DetalleConcluIf detalleConcluIf;
    private InformeFinal informeFinal;

     public Integer getConclusionID() {
        return conclusionID;
    }

    public void setConclusionID(Integer conclusionID) {
        this.conclusionID = conclusionID;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public DetalleConcluIf getDetalleConcluIf() {
        return detalleConcluIf;
    }

    public void setDetalleConcluIf(DetalleConcluIf detalleConcluIf) {
        this.detalleConcluIf = detalleConcluIf;
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
        hash += (conclusionID != null ? conclusionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConclusionBean)) {
            return false;
        }
        ConclusionBean other = (ConclusionBean) object;
        if ((this.conclusionID == null && other.conclusionID != null) || (this.conclusionID != null && !this.conclusionID.equals(other.conclusionID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.Conclusion[conclusionID=" + conclusionID + "]";
    }

}
