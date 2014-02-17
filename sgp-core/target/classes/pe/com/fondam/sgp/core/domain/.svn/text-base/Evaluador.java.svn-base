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
import javax.persistence.Transient;

@Entity
@Table(name = "evaluador")
public class Evaluador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     
    @Column(name = "Evaluador_ID")
    private Integer evaluadorID;
     
    @Column(name = "fk_idtablaesp_tipo_evaluacion")
    private int fkIdtablaespTipoEvaluacion;
    
    @Transient
    private String nombreTipoEvaluacion;
    
    @ManyToOne
    @JoinColumn(name = "Dato_Usuario_ID", referencedColumnName = "Dato_Usuario_ID")
    private DatoUsuario datoUsuario;
    
    @ManyToOne
    @JoinColumn(name = "Programa_ID", referencedColumnName = "Programa_ID")
    private Programa programa;


    public Integer getEvaluadorID() {
		return evaluadorID;
	}

	public void setEvaluadorID(Integer evaluadorID) {
		this.evaluadorID = evaluadorID;
	}

	public int getFkIdtablaespTipoEvaluacion() {
		return fkIdtablaespTipoEvaluacion;
	}

	public void setFkIdtablaespTipoEvaluacion(int fkIdtablaespTipoEvaluacion) {
		this.fkIdtablaespTipoEvaluacion = fkIdtablaespTipoEvaluacion;
	}

	public DatoUsuario getDatoUsuario() {
		return datoUsuario;
	}

	public void setDatoUsuario(DatoUsuario datoUsuario) {
		this.datoUsuario = datoUsuario;
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
        hash += (evaluadorID != null ? evaluadorID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evaluador)) {
            return false;
        }
        Evaluador other = (Evaluador) object;
        if ((this.evaluadorID == null && other.evaluadorID != null) || (this.evaluadorID != null && !this.evaluadorID.equals(other.evaluadorID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.Evaluador[evaluadorID=" + evaluadorID + "]";
    }

	public void setNombreTipoEvaluacion(String nombreTipoEvaluacion) {
		this.nombreTipoEvaluacion = nombreTipoEvaluacion;
	}

	public String getNombreTipoEvaluacion() {
		return nombreTipoEvaluacion;
	}

}
