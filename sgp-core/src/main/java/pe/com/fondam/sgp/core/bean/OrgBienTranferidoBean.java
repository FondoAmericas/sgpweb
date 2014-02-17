/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.fondam.sgp.core.bean;

import java.io.Serializable;

import pe.com.fondam.sgp.core.domain.Organizacion;
import pe.com.fondam.sgp.core.domain.PropuestaTransferenciaBeneficiario;
import pe.com.fondam.sgp.core.domain.PropuestaTransferenciaBien;

public class OrgBienTranferidoBean implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1753961364468084585L;
	private Integer orgBienTranferidoID;
    private PropuestaTransferenciaBien propuestaTransferenciaBien;
    private Organizacion organizacion;
    private PropuestaTransferenciaBeneficiario propuestaTransferenciaBeneficiario;
    private String observaciones;
    
    public OrgBienTranferidoBean() {
    }

    public OrgBienTranferidoBean(Integer orgBienTranferidoID) {
        this.orgBienTranferidoID = orgBienTranferidoID;
    }

    public Integer getOrgBienTranferidoID() {
        return orgBienTranferidoID;
    }

    public void setOrgBienTranferidoID(Integer orgBienTranferidoID) {
        this.orgBienTranferidoID = orgBienTranferidoID;
    }

    public PropuestaTransferenciaBien getPropuestaTransferenciaBien() {
        return propuestaTransferenciaBien;
    }

    public void setPropuestaTransferenciaBien(PropuestaTransferenciaBien propuestaTransferenciaBien) {
        this.propuestaTransferenciaBien = propuestaTransferenciaBien;
    }

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }


    public void setPropuestaTransferenciaBeneficiario(
			PropuestaTransferenciaBeneficiario propuestaTransferenciaBeneficiario) {
		this.propuestaTransferenciaBeneficiario = propuestaTransferenciaBeneficiario;
	}

	public PropuestaTransferenciaBeneficiario getPropuestaTransferenciaBeneficiario() {
		return propuestaTransferenciaBeneficiario;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getObservaciones() {
		return observaciones;
	}

}
