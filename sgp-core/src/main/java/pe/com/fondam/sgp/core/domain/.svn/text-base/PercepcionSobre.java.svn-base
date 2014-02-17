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
@Table(name = "percepcion_sobre")

public class PercepcionSobre implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "Percepcion_Sobre_ID")
    private Integer percepcionSobreID;
    
    @Column(name = "fk_idtablaesp_tipo_percepcion")
    private int fkIdtablaespTipoPercepcion;
    
    @Lob
    @Column(name = "comentario")
    private String comentario;
    @JoinColumn(name = "Informe_Visita_Campo_ID", referencedColumnName = "Informe_Visita_Campo_ID")
    @ManyToOne(optional = false)
    private InformeVisitaCampo informeVisitaCampo;

    public PercepcionSobre() {
    }

    public PercepcionSobre(Integer percepcionSobreID) {
        this.percepcionSobreID = percepcionSobreID;
    }

    public PercepcionSobre(Integer percepcionSobreID, int fkIdtablaespTipoPercepcion, String comentario) {
        this.percepcionSobreID = percepcionSobreID;
        this.fkIdtablaespTipoPercepcion = fkIdtablaespTipoPercepcion;
        this.comentario = comentario;
    }

    public Integer getPercepcionSobreID() {
        return percepcionSobreID;
    }

    public void setPercepcionSobreID(Integer percepcionSobreID) {
        this.percepcionSobreID = percepcionSobreID;
    }

    public int getFkIdtablaespTipoPercepcion() {
        return fkIdtablaespTipoPercepcion;
    }

    public void setFkIdtablaespTipoPercepcion(int fkIdtablaespTipoPercepcion) {
        this.fkIdtablaespTipoPercepcion = fkIdtablaespTipoPercepcion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
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
        hash += (percepcionSobreID != null ? percepcionSobreID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PercepcionSobre)) {
            return false;
        }
        PercepcionSobre other = (PercepcionSobre) object;
        if ((this.percepcionSobreID == null && other.percepcionSobreID != null) || (this.percepcionSobreID != null && !this.percepcionSobreID.equals(other.percepcionSobreID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.PercepcionSobre[percepcionSobreID=" + percepcionSobreID + "]";
    }

}
