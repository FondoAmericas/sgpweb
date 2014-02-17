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
@Table(name = "conclusion")
public class Conclusion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     
    @Column(name = "Conclusion_ID")
    private Integer conclusionID;
     
    @Lob
    @Column(name = "comentario")
    private String comentario;
    
    @ManyToOne
    @JoinColumn(name = "Detalle_Conclu_IF_ID", referencedColumnName = "Detalle_Conclu_IF_ID")
    private DetalleConcluIf detalleConcluIf;
    
    @ManyToOne
    @JoinColumn(name = "Informe_Final_ID", referencedColumnName = "Informe_Final_ID")
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
        if (!(object instanceof Conclusion)) {
            return false;
        }
        Conclusion other = (Conclusion) object;
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
