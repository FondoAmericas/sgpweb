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
@Table(name = "informe_final_bien")
public class InformeFinalBien implements Serializable {
  
	/**
	 * 
	 */
	private static final long serialVersionUID = 3868196954327349875L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "informe_final_bien_ID")
    private Integer informeFinalBienID;
     
    @ManyToOne
    @JoinColumn(name = "Informe_Final_ID", referencedColumnName = "Informe_Final_ID")
    private InformeFinal informeFinal;
    
    @JoinColumn(name = "Bien_ID", referencedColumnName = "Bien_ID")
    @ManyToOne
    private Bien bien;
   
    public InformeFinalBien(){
    	
    }
    
    
	public Integer getInformeFinalBienID() {
		return informeFinalBienID;
	}


	public void setInformeFinalBienID(Integer informeFinalBienID) {
		this.informeFinalBienID = informeFinalBienID;
	}


	public InformeFinal getInformeFinal() {
		return informeFinal;
	}


	public void setInformeFinal(
			InformeFinal informeFinal) {
		this.informeFinal = informeFinal;
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
        hash += (informeFinalBienID != null ? informeFinalBienID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InformeFinalBien)) {
            return false;
        }
        InformeFinalBien other = (InformeFinalBien) object;
        if ((this.informeFinalBienID == null && other.informeFinalBienID != null) || (this.informeFinalBienID != null && !this.informeFinalBienID.equals(other.informeFinalBienID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.InformeFinalBien[informeFinalBienID=" + informeFinalBienID + "]";
    }

}
