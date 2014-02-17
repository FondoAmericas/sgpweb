/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.fondam.sgp.core.bean;

import java.io.Serializable;

import pe.com.fondam.sgp.core.domain.DatoProyecto;
import pe.com.fondam.sgp.core.domain.InformeFinal;
import pe.com.fondam.sgp.core.domain.PropuestaTransferencia;


public class OrganizacionBean implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8798041211936946194L;
	private Integer organizacionID;
    private String nombreOrganizacion;
    private String situacionFinal;
    private PropuestaTransferencia propuestaTransferencia;
    private InformeFinal informeFinal;
    private DatoProyecto datoProyecto;
    private Integer cantidadOrgEnBienTransferido;

    public OrganizacionBean() {
    }

    public OrganizacionBean(Integer organizacionID) {
        this.organizacionID = organizacionID;
    }

    public OrganizacionBean(Integer organizacionID, String nombreOrganizacion, String situacionFinal) {
        this.organizacionID = organizacionID;
        this.nombreOrganizacion = nombreOrganizacion;
        this.situacionFinal = situacionFinal;
    }

    public Integer getOrganizacionID() {
        return organizacionID;
    }

    public void setOrganizacionID(Integer organizacionID) {
        this.organizacionID = organizacionID;
    }

    public String getNombreOrganizacion() {
        return nombreOrganizacion;
    }

    public void setNombreOrganizacion(String nombreOrganizacion) {
        this.nombreOrganizacion = nombreOrganizacion;
    }

    public String getSituacionFinal() {
        return situacionFinal;
    }

    public void setSituacionFinal(String situacionFinal) {
        this.situacionFinal = situacionFinal;
    }

    public PropuestaTransferencia getPropuestaTransferencia() {
        return propuestaTransferencia;
    }

    public void setPropuestaTransferencia(PropuestaTransferencia propuestaTransferencia) {
        this.propuestaTransferencia = propuestaTransferencia;
    }

    public InformeFinal getInformeFinal() {
        return informeFinal;
    }

    public void setInformeFinal(InformeFinal informeFinal) {
        this.informeFinal = informeFinal;
    }

    public void setDatoProyecto(DatoProyecto datoProyecto) {
		this.datoProyecto = datoProyecto;
	}

	public DatoProyecto getDatoProyecto() {
		return datoProyecto;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (organizacionID != null ? organizacionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrganizacionBean)) {
            return false;
        }
        OrganizacionBean other = (OrganizacionBean) object;
        if ((this.organizacionID == null && other.organizacionID != null) || (this.organizacionID != null && !this.organizacionID.equals(other.organizacionID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.Organizacion[organizacionID=" + organizacionID + "]";
    }

	public void setCantidadOrgEnBienTransferido(
			Integer cantidadOrgEnBienTransferido) {
		this.cantidadOrgEnBienTransferido = cantidadOrgEnBienTransferido;
	}

	public Integer getCantidadOrgEnBienTransferido() {
		return cantidadOrgEnBienTransferido;
	}

}
