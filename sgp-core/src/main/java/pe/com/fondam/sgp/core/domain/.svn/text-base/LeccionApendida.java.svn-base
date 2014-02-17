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
@Table(name = "leccion_apendida")

public class LeccionApendida implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "Leccion_Apendida_ID")
    private Integer leccionApendidaID;
    
    @Column(name = "fk_idtablaesp_tipo_leccion")
    private int fkIdtablaespTipoLeccion;
    
    @Transient
    private String descripcionTipoLeccion;
    
    @Lob
    @Column(name = "comentario")
    private String comentario;
    
    @JoinColumn(name = "Informe_Final_ID", referencedColumnName = "Informe_Final_ID")
    @ManyToOne(optional = false)
    private InformeFinal informeFinal;

    public LeccionApendida() {
    }

    public LeccionApendida(Integer leccionApendidaID) {
        this.leccionApendidaID = leccionApendidaID;
    }

    public LeccionApendida(Integer leccionApendidaID, int fkIdtablaespTipoLeccion, String comentario) {
        this.leccionApendidaID = leccionApendidaID;
        this.fkIdtablaespTipoLeccion = fkIdtablaespTipoLeccion;
        this.comentario = comentario;
    }

    public Integer getLeccionApendidaID() {
        return leccionApendidaID;
    }

    public void setLeccionApendidaID(Integer leccionApendidaID) {
        this.leccionApendidaID = leccionApendidaID;
    }

    public int getFkIdtablaespTipoLeccion() {
        return fkIdtablaespTipoLeccion;
    }

    public void setFkIdtablaespTipoLeccion(int fkIdtablaespTipoLeccion) {
        this.fkIdtablaespTipoLeccion = fkIdtablaespTipoLeccion;
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

    public void setDescripcionTipoLeccion(String descripcionTipoLeccion) {
		this.descripcionTipoLeccion = descripcionTipoLeccion;
	}

	public String getDescripcionTipoLeccion() {
		return descripcionTipoLeccion;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (leccionApendidaID != null ? leccionApendidaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LeccionApendida)) {
            return false;
        }
        LeccionApendida other = (LeccionApendida) object;
        if ((this.leccionApendidaID == null && other.leccionApendidaID != null) || (this.leccionApendidaID != null && !this.leccionApendidaID.equals(other.leccionApendidaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.LeccionApendida[leccionApendidaID=" + leccionApendidaID + "]";
    }

}
