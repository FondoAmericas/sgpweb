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
@Table(name = "propuesta_transferencia_bien")
public class PropuestaTransferenciaBien implements Serializable {
  

	/**
	 * 
	 */
	private static final long serialVersionUID = -7243694633524612476L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "propuesta_transferencia_bien_ID")
    private Integer propuestaTransferenciaBienID;
     
    @ManyToOne
    @JoinColumn(name = "Propuesta_Transferencia_ID", referencedColumnName = "Propuesta_Transferencia_ID")
    private PropuestaTransferencia propuestaTransferencia;
    
    @JoinColumn(name = "Bien_ID", referencedColumnName = "Bien_ID")
    @ManyToOne
    private Bien bien;
    
       
    public PropuestaTransferenciaBien(){
    	
    }
    
    
	public Integer getPropuestaTransferenciaBienID() {
		return propuestaTransferenciaBienID;
	}


	public void setPropuestaTransferenciaBienID(Integer propuestaTransferenciaBienID) {
		this.propuestaTransferenciaBienID = propuestaTransferenciaBienID;
	}


	public PropuestaTransferencia getPropuestaTransferencia() {
		return propuestaTransferencia;
	}


	public void setPropuestaTransferencia(
			PropuestaTransferencia propuestaTransferencia) {
		this.propuestaTransferencia = propuestaTransferencia;
	}


	public Bien getBien() {
		return bien;
	}


	public void setBien(Bien bien) {
		this.bien = bien;
	}


	@Override
    public int hashCode() {
        int hash = 0;
        hash += (propuestaTransferenciaBienID != null ? propuestaTransferenciaBienID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PropuestaTransferenciaBien)) {
            return false;
        }
        PropuestaTransferenciaBien other = (PropuestaTransferenciaBien) object;
        if ((this.propuestaTransferenciaBienID == null && other.propuestaTransferenciaBienID != null) || (this.propuestaTransferenciaBienID != null && !this.propuestaTransferenciaBienID.equals(other.propuestaTransferenciaBienID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.PropuestaTransferenciaBien[propuestaTransferenciaBienID=" + propuestaTransferenciaBienID + "]";
    }

}
