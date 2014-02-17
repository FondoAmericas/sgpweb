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
@Table(name = "telefono")

public class Telefono implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "Telefono_ID")
    private Integer telefonoID;
    
    @Column(name = "numero")
    private String numero;
    
    @Column(name = "operador")
    private String operador;
    @JoinColumn(name = "Dato_Usuario_ID", referencedColumnName = "Dato_Usuario_ID")
    @ManyToOne(optional = false)
    private DatoUsuario datoUsuario;
    @JoinColumn(name = "Institucion_ID", referencedColumnName = "Institucion_ID")
    @ManyToOne(optional = false)
    private Institucion institucion;

    public Telefono() {
    }

    public Telefono(Integer telefonoID) {
        this.telefonoID = telefonoID;
    }

    public Telefono(Integer telefonoID, String numero, String operador) {
        this.telefonoID = telefonoID;
        this.numero = numero;
        this.operador = operador;
    }

    public Integer getTelefonoID() {
        return telefonoID;
    }

    public void setTelefonoID(Integer telefonoID) {
        this.telefonoID = telefonoID;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public DatoUsuario getDatoUsuario() {
        return datoUsuario;
    }

    public void setDatoUsuario(DatoUsuario datoUsuario) {
        this.datoUsuario = datoUsuario;
    }

    public Institucion getInstitucion() {
        return institucion;
    }

    public void setInstitucion(Institucion institucion) {
        this.institucion = institucion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (telefonoID != null ? telefonoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Telefono)) {
            return false;
        }
        Telefono other = (Telefono) object;
        if ((this.telefonoID == null && other.telefonoID != null) || (this.telefonoID != null && !this.telefonoID.equals(other.telefonoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.Telefono[telefonoID=" + telefonoID + "]";
    }

}
