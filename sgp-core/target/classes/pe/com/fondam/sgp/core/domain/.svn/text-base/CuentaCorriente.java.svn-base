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
import javax.persistence.Transient;

@Entity
@Table(name = "cuenta_corriente")
public class CuentaCorriente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     
    @Column(name = "Cuenta_Corriente_ID")
    private Integer cuentaCorrienteID;
     
    @Column(name = "numero_cuenta")
    private String numeroCuenta;
     
    @Column(name = "fk_idtablaesp_tipomoneda")
    private int fkIdtablaespTipomoneda;
     
    @Column(name = "fk_idtablaesp_banco")
    private int fkIdtablaespBanco;
    
    @ManyToOne
    @JoinColumn(name = "Dato_Proyecto_ID", referencedColumnName = "Dato_Proyecto_ID")
    private DatoProyecto datoProyecto;

    @Transient
    private int flagReferencia;
   

	public Integer getCuentaCorrienteID() {
        return cuentaCorrienteID;
    }

    public void setCuentaCorrienteID(Integer cuentaCorrienteID) {
        this.cuentaCorrienteID = cuentaCorrienteID;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public int getFkIdtablaespTipomoneda() {
        return fkIdtablaespTipomoneda;
    }

    public void setFkIdtablaespTipomoneda(int fkIdtablaespTipomoneda) {
        this.fkIdtablaespTipomoneda = fkIdtablaespTipomoneda;
    }

    public int getFkIdtablaespBanco() {
        return fkIdtablaespBanco;
    }

    public void setFkIdtablaespBanco(int fkIdtablaespBanco) {
        this.fkIdtablaespBanco = fkIdtablaespBanco;
    }

    public DatoProyecto getDatoProyecto() {
        return datoProyecto;
    }

    public void setDatoProyecto(DatoProyecto datoProyecto) {
        this.datoProyecto = datoProyecto;
    }

    public void setFlagReferencia(int flagReferencia) {
		this.flagReferencia = flagReferencia;
	}

	public int getFlagReferencia() {
		return flagReferencia;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (cuentaCorrienteID != null ? cuentaCorrienteID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuentaCorriente)) {
            return false;
        }
        CuentaCorriente other = (CuentaCorriente) object;
        if ((this.cuentaCorrienteID == null && other.cuentaCorrienteID != null) || (this.cuentaCorrienteID != null && !this.cuentaCorrienteID.equals(other.cuentaCorrienteID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.CuentaCorriente[cuentaCorrienteID=" + cuentaCorrienteID + "]";
    }

}
