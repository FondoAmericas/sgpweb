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
@Table(name = "propuesta_transferencia")

public class PropuestaTransferencia implements Serializable {
  
	/**
	 * 
	 */
	private static final long serialVersionUID = 3185425113278264135L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Propuesta_Transferencia_ID")
    private Integer propuestaTransferenciaID;
    
    @Column(name = "fk_id_detalle_estado_cab_est_inf_prop_transfer")
    private int fkIdDetalleEstadoCabEstInfPropTransfer;
    
    @Transient
    private String descripcionEstadoPropuesta;
    
    @Lob
    @Column(name = "resumen_descrip_trans")
    private String resumenDescripTrans;
    
    @Lob
    @Column(name = "resumen_plan_trans")
    private String resumenPlanTrans;
    
    @JoinColumn(name = "Dato_Proyecto_ID", referencedColumnName = "Dato_Proyecto_ID")
    @ManyToOne
    private DatoProyecto datoProyecto;
   
    @Transient
    private List<DirectivaBeneficiario> listDirectivaBeneficiario;
    
    @Transient
    private List<Organizacion> listOrganizacion;
    
    @Transient
    private List<PropuestaTransferenciaBien> listPropuestaTransferenciaBien;
    
    @Transient
    private List<PropuestaTransferenciaBeneficiario> listPropuestaTransferenciaBeneficiario;
    
	@Transient
	private Integer cantObservacionesRelevantes;

    public PropuestaTransferencia() {
    }


    public Integer getPropuestaTransferenciaID() {
        return propuestaTransferenciaID;
    }

    public void setPropuestaTransferenciaID(Integer propuestaTransferenciaID) {
        this.propuestaTransferenciaID = propuestaTransferenciaID;
    }

    public int getFkIdDetalleEstadoCabEstInfPropTransfer() {
        return fkIdDetalleEstadoCabEstInfPropTransfer;
    }

    public void setFkIdDetalleEstadoCabEstInfPropTransfer(int fkIdDetalleEstadoCabEstInfPropTransfer) {
        this.fkIdDetalleEstadoCabEstInfPropTransfer = fkIdDetalleEstadoCabEstInfPropTransfer;
    }

    public String getResumenDescripTrans() {
        return resumenDescripTrans;
    }

    public void setResumenDescripTrans(String resumenDescripTrans) {
        this.resumenDescripTrans = resumenDescripTrans;
    }

    public String getResumenPlanTrans() {
        return resumenPlanTrans;
    }

    public void setResumenPlanTrans(String resumenPlanTrans) {
        this.resumenPlanTrans = resumenPlanTrans;
    }

  
    public DatoProyecto getDatoProyecto() {
        return datoProyecto;
    }

    public void setDatoProyecto(DatoProyecto datoProyecto) {
        this.datoProyecto = datoProyecto;
    }

    public void setDescripcionEstadoPropuesta(String descripcionEstadoPropuesta) {
		this.descripcionEstadoPropuesta = descripcionEstadoPropuesta;
	}


	public String getDescripcionEstadoPropuesta() {
		return descripcionEstadoPropuesta;
	}


	

	public List<DirectivaBeneficiario> getListDirectivaBeneficiario() {
		return listDirectivaBeneficiario;
	}


	public void setListDirectivaBeneficiario(
			List<DirectivaBeneficiario> listDirectivaBeneficiario) {
		this.listDirectivaBeneficiario = listDirectivaBeneficiario;
	}


	public List<Organizacion> getListOrganizacion() {
		return listOrganizacion;
	}


	public void setListOrganizacion(List<Organizacion> listOrganizacion) {
		this.listOrganizacion = listOrganizacion;
	}


	public List<PropuestaTransferenciaBien> getListPropuestaTransferenciaBien() {
		return listPropuestaTransferenciaBien;
	}


	public void setListPropuestaTransferenciaBien(
			List<PropuestaTransferenciaBien> listPropuestaTransferenciaBien) {
		this.listPropuestaTransferenciaBien = listPropuestaTransferenciaBien;
	}


	public List<PropuestaTransferenciaBeneficiario> getListPropuestaTransferenciaBeneficiario() {
		return listPropuestaTransferenciaBeneficiario;
	}


	public void setListPropuestaTransferenciaBeneficiario(
			List<PropuestaTransferenciaBeneficiario> listPropuestaTransferenciaBeneficiario) {
		this.listPropuestaTransferenciaBeneficiario = listPropuestaTransferenciaBeneficiario;
	}


	public Integer getCantObservacionesRelevantes() {
		return cantObservacionesRelevantes;
	}


	public void setCantObservacionesRelevantes(Integer cantObservacionesRelevantes) {
		this.cantObservacionesRelevantes = cantObservacionesRelevantes;
	}


	@Override
    public int hashCode() {
        int hash = 0;
        hash += (propuestaTransferenciaID != null ? propuestaTransferenciaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PropuestaTransferencia)) {
            return false;
        }
        PropuestaTransferencia other = (PropuestaTransferencia) object;
        if ((this.propuestaTransferenciaID == null && other.propuestaTransferenciaID != null) || (this.propuestaTransferenciaID != null && !this.propuestaTransferenciaID.equals(other.propuestaTransferenciaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.PropuestaTransferencia[propuestaTransferenciaID=" + propuestaTransferenciaID + "]";
    }

}
