/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.fondam.sgp.core.domain;

import java.io.Serializable;
import java.util.List;

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
@Table(name = "ubicacion_proyecto")
public class UbicacionProyecto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    @Column(name = "Ubicacion_Proyecto_ID")
    private Integer ubicacionProyectoID;
    
    @Lob
    @Column(name = "detalle_ubicacion")
    private String detalleUbicacion;
    
    @ManyToOne
    @JoinColumn(name = "Dep_Prov_Dist_ID", referencedColumnName = "Dep_Prov_Dist_ID")
    private DepProvDist depProvDist;

    @Transient
    private String descripcionDepartamento;
    
    @Transient
    private String descripcionProvincia;
    
    @Transient
    private String descripcionDistrito;
    
    @Transient
    private List<BeneficiariosPorResultado> listBeneficiariosPorResultado;
    
    @ManyToOne
    @JoinColumn(name = "Dato_Proyecto_ID", referencedColumnName = "Dato_Proyecto_ID")
    private DatoProyecto datoProyecto;


    public Integer getUbicacionProyectoID() {
        return ubicacionProyectoID;
    }

    public void setUbicacionProyectoID(Integer ubicacionProyectoID) {
        this.ubicacionProyectoID = ubicacionProyectoID;
    }

    public String getDetalleUbicacion() {
        return detalleUbicacion;
    }

    public void setDetalleUbicacion(String detalleUbicacion) {
        this.detalleUbicacion = detalleUbicacion;
    }


    public DepProvDist getDepProvDist() {
        return depProvDist;
    }

    public void setDepProvDist(DepProvDist depProvDist) {
        this.depProvDist = depProvDist;
    }

    public String getDescripcionDepartamento() {
		return descripcionDepartamento;
	}

	public void setDescripcionDepartamento(String descripcionDepartamento) {
		this.descripcionDepartamento = descripcionDepartamento;
	}

	public String getDescripcionProvincia() {
		return descripcionProvincia;
	}

	public void setDescripcionProvincia(String descripcionProvincia) {
		this.descripcionProvincia = descripcionProvincia;
	}

	public String getDescripcionDistrito() {
		return descripcionDistrito;
	}

	public void setDescripcionDistrito(String descripcionDistrito) {
		this.descripcionDistrito = descripcionDistrito;
	}

	public List<BeneficiariosPorResultado> getListBeneficiariosPorResultado() {
		return listBeneficiariosPorResultado;
	}

	public void setListBeneficiariosPorResultado(
			List<BeneficiariosPorResultado> listBeneficiariosPorResultado) {
		this.listBeneficiariosPorResultado = listBeneficiariosPorResultado;
	}

	public DatoProyecto getDatoProyecto() {
        return datoProyecto;
    }

    public void setDatoProyecto(DatoProyecto datoProyecto) {
        this.datoProyecto = datoProyecto;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ubicacionProyectoID != null ? ubicacionProyectoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UbicacionProyecto)) {
            return false;
        }
        UbicacionProyecto other = (UbicacionProyecto) object;
        if ((this.ubicacionProyectoID == null && other.ubicacionProyectoID != null) || (this.ubicacionProyectoID != null && !this.ubicacionProyectoID.equals(other.ubicacionProyectoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.UbicacionProyecto[ubicacionProyectoID=" + ubicacionProyectoID + "]";
    }

}
