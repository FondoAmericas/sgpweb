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

@Entity
@Table(name = "propuesta_transferencia_beneficiario")
public class PropuestaTransferenciaBeneficiario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5328277025514202803L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "propuesta_transferencia_beneficiario_id")
    private Integer propuestaTransferenciaBeneficiarioID;
    
    @Column(name = "cantidad_final")
    private int cantidadFinal;
    
    @JoinColumn(name = "Propuesta_Transferencia_ID", referencedColumnName = "Propuesta_Transferencia_ID")
    @ManyToOne(optional = false)
    private PropuestaTransferencia propuestaTransferencia;
   
    @JoinColumn(name = "beneficiarios_por_resultado_ID", referencedColumnName = "beneficiarios_por_resultado_ID")
    @ManyToOne(optional = false)
    private BeneficiariosPorResultado beneficiariosPorResultado;
   

    public PropuestaTransferenciaBeneficiario() {
    }

    
    
    public Integer getPropuestaTransferenciaBeneficiarioID() {
		return propuestaTransferenciaBeneficiarioID;
	}



	public void setPropuestaTransferenciaBeneficiarioID(
			Integer propuestaTransferenciaBeneficiarioID) {
		this.propuestaTransferenciaBeneficiarioID = propuestaTransferenciaBeneficiarioID;
	}



	public int getCantidadFinal() {
		return cantidadFinal;
	}



	public void setCantidadFinal(int cantidadFinal) {
		this.cantidadFinal = cantidadFinal;
	}



	public PropuestaTransferencia getPropuestaTransferencia() {
		return propuestaTransferencia;
	}



	public void setPropuestaTransferencia(
			PropuestaTransferencia propuestaTransferencia) {
		this.propuestaTransferencia = propuestaTransferencia;
	}



	public BeneficiariosPorResultado getBeneficiariosPorResultado() {
		return beneficiariosPorResultado;
	}



	public void setBeneficiariosPorResultado(
			BeneficiariosPorResultado beneficiariosPorResultado) {
		this.beneficiariosPorResultado = beneficiariosPorResultado;
	}



	@Override
    public int hashCode() {
        int hash = 0;
        hash += (propuestaTransferenciaBeneficiarioID != null ? propuestaTransferenciaBeneficiarioID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PropuestaTransferenciaBeneficiario)) {
            return false;
        }
        PropuestaTransferenciaBeneficiario other = (PropuestaTransferenciaBeneficiario) object;
        if ((this.propuestaTransferenciaBeneficiarioID == null && other.propuestaTransferenciaBeneficiarioID != null) || (this.propuestaTransferenciaBeneficiarioID != null && !this.propuestaTransferenciaBeneficiarioID.equals(other.propuestaTransferenciaBeneficiarioID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.PropuestaTransferenciaBeneficiario[propuestaTransferenciaBeneficiarioID=" + propuestaTransferenciaBeneficiarioID + "]";
    }

}
