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
import javax.persistence.Transient;

@Entity
@Table(name = "efecto_proyecto")
public class EfectoProyecto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     
    @Column(name = "Efecto_Proyecto_ID")
    private Integer efectoProyectoID;
     
    @Column(name = "fk_idtablaesp_efecto_Proy")
    private int fkidtablaespefectoProy;
    
    @Transient
    private String descripcionefectoProy;
     
    @Lob
    @Column(name = "comentario")
    private String comentario;

    @JoinColumn(name = "Informe_Final_ID", referencedColumnName = "Informe_Final_ID")
    @ManyToOne(optional = false)
    private InformeFinal informeFinal;

    public EfectoProyecto() {
    }

    public EfectoProyecto(Integer efectoProyectoID) {
        this.efectoProyectoID = efectoProyectoID;
    }

    public EfectoProyecto(Integer efectoProyectoID, int fkidtablaespefectoProy, String comentario) {
        this.efectoProyectoID = efectoProyectoID;
        this.fkidtablaespefectoProy = fkidtablaespefectoProy;
        this.comentario = comentario;
    }

    public Integer getEfectoProyectoID() {
        return efectoProyectoID;
    }

    public void setEfectoProyectoID(Integer efectoProyectoID) {
        this.efectoProyectoID = efectoProyectoID;
    }

    public int getFkidtablaespefectoProy() {
        return fkidtablaespefectoProy;
    }

    public void setFkidtablaespefectoProy(int fkidtablaespefectoProy) {
        this.fkidtablaespefectoProy = fkidtablaespefectoProy;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public InformeFinal getInformeFinal() {
        return informeFinal;
    }

    public void setInformeFinal(InformeFinal informeFinal) {
        this.informeFinal = informeFinal;
    }

    public void setDescripcionefectoProy(String descripcionefectoProy) {
		this.descripcionefectoProy = descripcionefectoProy;
	}

	public String getDescripcionefectoProy() {
		return descripcionefectoProy;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (efectoProyectoID != null ? efectoProyectoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof EfectoProyecto)) {
            return false;
        }
        EfectoProyecto other = (EfectoProyecto) object;
        if ((this.efectoProyectoID == null && other.efectoProyectoID != null) || (this.efectoProyectoID != null && !this.efectoProyectoID.equals(other.efectoProyectoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.EfectoProyecto[efectoProyectoID=" + efectoProyectoID + "]";
    }

}
