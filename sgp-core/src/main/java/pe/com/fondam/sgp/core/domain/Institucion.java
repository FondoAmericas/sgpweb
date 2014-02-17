package pe.com.fondam.sgp.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "institucion")
public class Institucion implements Serializable {

	private static final long serialVersionUID = -5214960779849593933L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Institucion_ID")
    private Integer institucionID;
    
    @Column(name = "nombre_institucion")
    private String nombreInstitucion;
    
    @Column(name = "ruc_institucion")
    private String rucInstitucion;
    
    @Column(name = "direccion")
    private String direccion;
    
    @Column(name = "telefono")
    private String telefono;
    
    @Column(name = "representante_legal")
    private String representanteLegal;
    
    @Column(name = "contacto")
    private String contacto;
    
    @Lob
    @Column(name = "observacion_de_institucion")
    private String observacionDeInstitucion;
    
    @Column(name = "fk_id_detalle_estado_cab_est_institucion")
    private Integer fkIdDetalleEstadoCabEstInstitucion;
    
   @Column(name="correo")
    private String correo;
   
   @Column(name="abreviatura")
   private String abreviatura;
   
   @Transient
   private boolean flagReferencia;

    public boolean isFlagReferencia() {
	return flagReferencia;
}

public void setFlagReferencia(boolean flagReferencia) {
	this.flagReferencia = flagReferencia;
}

	public Integer getInstitucionID() {
        return institucionID;
    }

    public void setInstitucionID(Integer institucionID) {
        this.institucionID = institucionID;
    }

    public String getNombreInstitucion() {
        return nombreInstitucion;
    }

    public void setNombreInstitucion(String nombreInstitucion) {
        this.nombreInstitucion = nombreInstitucion;
    }

    public String getRucInstitucion() {
        return rucInstitucion;
    }

    public void setRucInstitucion(String rucInstitucion) {
        this.rucInstitucion = rucInstitucion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRepresentanteLegal() {
        return representanteLegal;
    }

    public void setRepresentanteLegal(String representanteLegal) {
        this.representanteLegal = representanteLegal;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getObservacionDeInstitucion() {
        return observacionDeInstitucion;
    }

    public void setObservacionDeInstitucion(String observacionDeInstitucion) {
        this.observacionDeInstitucion = observacionDeInstitucion;
    }

    public Integer getFkIdDetalleEstadoCabEstInstitucion() {
        return fkIdDetalleEstadoCabEstInstitucion;
    }

    public void setFkIdDetalleEstadoCabEstInstitucion(int fkIdDetalleEstadoCabEstInstitucion) {
        this.fkIdDetalleEstadoCabEstInstitucion = fkIdDetalleEstadoCabEstInstitucion;
    }

    public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (institucionID != null ? institucionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Institucion)) {
            return false;
        }
        Institucion other = (Institucion) object;
        if ((this.institucionID == null && other.institucionID != null) || (this.institucionID != null && !this.institucionID.equals(other.institucionID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.Institucion[institucionID=" + institucionID + "]";
    }

}
