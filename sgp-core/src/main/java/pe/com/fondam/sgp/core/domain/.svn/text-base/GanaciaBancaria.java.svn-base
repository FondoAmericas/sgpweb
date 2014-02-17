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
@Table(name = "ganacia_bancaria")
public class GanaciaBancaria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     
    @Column(name = "Ganacia_Bancaria_ID")
    private Integer ganaciaBancariaID;
     
    @Column(name = "monto")
    private double monto;
     
    @Column(name = "fk_idtablaesp_tipo_moneda")
    private int fkIdtablaespTipoMoneda;
     
    @Lob
    @Column(name = "compromiso")
    private String compromiso;
     
    @Column(name = "entidad")
    private String entidad;
    @JoinColumn(name = "Dato_Proyecto_ID", referencedColumnName = "Dato_Proyecto_ID")
    @ManyToOne(optional = false)
    private DatoProyecto datoProyecto;

    public GanaciaBancaria() {
    }

    public GanaciaBancaria(Integer ganaciaBancariaID) {
        this.ganaciaBancariaID = ganaciaBancariaID;
    }

    public GanaciaBancaria(Integer ganaciaBancariaID, double monto, int fkIdtablaespTipoMoneda, String compromiso, String entidad) {
        this.ganaciaBancariaID = ganaciaBancariaID;
        this.monto = monto;
        this.fkIdtablaespTipoMoneda = fkIdtablaespTipoMoneda;
        this.compromiso = compromiso;
        this.entidad = entidad;
    }

    public Integer getGanaciaBancariaID() {
        return ganaciaBancariaID;
    }

    public void setGanaciaBancariaID(Integer ganaciaBancariaID) {
        this.ganaciaBancariaID = ganaciaBancariaID;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getFkIdtablaespTipoMoneda() {
        return fkIdtablaespTipoMoneda;
    }

    public void setFkIdtablaespTipoMoneda(int fkIdtablaespTipoMoneda) {
        this.fkIdtablaespTipoMoneda = fkIdtablaespTipoMoneda;
    }

    public String getCompromiso() {
        return compromiso;
    }

    public void setCompromiso(String compromiso) {
        this.compromiso = compromiso;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
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
        hash += (ganaciaBancariaID != null ? ganaciaBancariaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GanaciaBancaria)) {
            return false;
        }
        GanaciaBancaria other = (GanaciaBancaria) object;
        if ((this.ganaciaBancariaID == null && other.ganaciaBancariaID != null) || (this.ganaciaBancariaID != null && !this.ganaciaBancariaID.equals(other.ganaciaBancariaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.GanaciaBancaria[ganaciaBancariaID=" + ganaciaBancariaID + "]";
    }

}
