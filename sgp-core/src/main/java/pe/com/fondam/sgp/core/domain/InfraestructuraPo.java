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
@Table(name = "infraestructura_po")

public class InfraestructuraPo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8330064456942188442L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    @Column(name = "Infraestructura_PO_ID")
    private Integer infraestructuraPOID;
    
    @Lob
    @Column(name = "descripcion")
    private String descripcion;
    
    @Lob
    @Column(name = "ubicacion")
    private String ubicacion;
    
    @ManyToOne
    @JoinColumn(name = "Activo_ID", referencedColumnName = "Activo_ID")
    private Activo activo;
    
    @Transient
    private String descripcionCategoriaActivo;
    
    @Transient
    private String descripcionActivo;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "Dato_Proyecto_ID", referencedColumnName = "Dato_Proyecto_ID")
    private DatoProyecto datoProyecto;

	@Column(name = "estado_eliminado")
	private Integer estadoEliminado;
	
    public InfraestructuraPo() {
    }

    public InfraestructuraPo(Integer infraestructuraPOID) {
        this.infraestructuraPOID = infraestructuraPOID;
    }

    public InfraestructuraPo(Integer infraestructuraPOID, String descripcion, String ubicacion) {
        this.infraestructuraPOID = infraestructuraPOID;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
    }

    public Integer getInfraestructuraPOID() {
        return infraestructuraPOID;
    }

    public void setInfraestructuraPOID(Integer infraestructuraPOID) {
        this.infraestructuraPOID = infraestructuraPOID;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Activo getActivo() {
        return activo;
    }

    public void setActivo(Activo activo) {
        this.activo = activo;
    }

    public DatoProyecto getDatoProyecto() {
        return datoProyecto;
    }

    public void setDatoProyecto(DatoProyecto datoProyecto) {
        this.datoProyecto = datoProyecto;
    }

    public String getDescripcionCategoriaActivo() {
		return descripcionCategoriaActivo;
	}

	public void setDescripcionCategoriaActivo(String descripcionCategoriaActivo) {
		this.descripcionCategoriaActivo = descripcionCategoriaActivo;
	}

	public String getDescripcionActivo() {
		return descripcionActivo;
	}

	public void setDescripcionActivo(String descripcionActivo) {
		this.descripcionActivo = descripcionActivo;
	}

	public Integer getEstadoEliminado() {
		return estadoEliminado;
	}

	public void setEstadoEliminado(Integer estadoEliminado) {
		this.estadoEliminado = estadoEliminado;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (infraestructuraPOID != null ? infraestructuraPOID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InfraestructuraPo)) {
            return false;
        }
        InfraestructuraPo other = (InfraestructuraPo) object;
        if ((this.infraestructuraPOID == null && other.infraestructuraPOID != null) || (this.infraestructuraPOID != null && !this.infraestructuraPOID.equals(other.infraestructuraPOID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.InfraestructuraPo[infraestructuraPOID=" + infraestructuraPOID + "]";
    }

}
