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
@Table(name = "org_bien_tranferido")

public class OrgBienTranferido implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -2593443037437605007L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    @Column(name = "Org_Bien_Tranferido_ID")
    private Integer orgBienTranferidoID;
    
    @JoinColumn(name = "Propuesta_transferencia_bien_ID", referencedColumnName = "Propuesta_transferencia_bien_ID")
    @ManyToOne(optional = false)
    private PropuestaTransferenciaBien propuestaTransferenciaBien;
    
    @JoinColumn(name = "Organizacion_ID", referencedColumnName = "Organizacion_ID")
    @ManyToOne(optional = false)
    private Organizacion organizacion;
    
    @JoinColumn(name = "Propuesta_transferencia_beneficiario_id", referencedColumnName = "Propuesta_transferencia_beneficiario_id")
    @ManyToOne(optional = false)
    private PropuestaTransferenciaBeneficiario propuestaTransferenciaBeneficiario;

    @Lob
    @Column(name="observaciones")
    private String observaciones;
    
    public OrgBienTranferido() {
    }

    public OrgBienTranferido(Integer orgBienTranferidoID) {
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

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (orgBienTranferidoID != null ? orgBienTranferidoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrgBienTranferido)) {
            return false;
        }
        OrgBienTranferido other = (OrgBienTranferido) object;
        if ((this.orgBienTranferidoID == null && other.orgBienTranferidoID != null) || (this.orgBienTranferidoID != null && !this.orgBienTranferidoID.equals(other.orgBienTranferidoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.OrgBienTranferido[orgBienTranferidoID=" + orgBienTranferidoID + "]";
    }


}
