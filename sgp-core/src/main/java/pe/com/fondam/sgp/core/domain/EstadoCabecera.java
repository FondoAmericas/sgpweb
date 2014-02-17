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
import javax.persistence.Table;

@Entity
@Table(name = "estado_cabecera")
public class EstadoCabecera implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     
    @Column(name = "Estado_Cabecera_ID")
    private Integer estadoCabeceraID;
     
    @Column(name = "prefijo_estado")
    private String prefijoEstado;
     
    @Column(name = "descripcion_documento")
    private String descripcionDocumento;
     
    @Column(name = "estado_eliminado")
    private int estadoEliminado;

   
    public Integer getEstadoCabeceraID() {
        return estadoCabeceraID;
    }

    public void setEstadoCabeceraID(Integer estadoCabeceraID) {
        this.estadoCabeceraID = estadoCabeceraID;
    }

    public String getPrefijoEstado() {
        return prefijoEstado;
    }

    public void setPrefijoEstado(String prefijoEstado) {
        this.prefijoEstado = prefijoEstado;
    }

    public String getDescripcionDocumento() {
        return descripcionDocumento;
    }

    public void setDescripcionDocumento(String descripcionDocumento) {
        this.descripcionDocumento = descripcionDocumento;
    }

    public int getEstadoEliminado() {
        return estadoEliminado;
    }

    public void setEstadoEliminado(int estadoEliminado) {
        this.estadoEliminado = estadoEliminado;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estadoCabeceraID != null ? estadoCabeceraID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoCabecera)) {
            return false;
        }
        EstadoCabecera other = (EstadoCabecera) object;
        if ((this.estadoCabeceraID == null && other.estadoCabeceraID != null) || (this.estadoCabeceraID != null && !this.estadoCabeceraID.equals(other.estadoCabeceraID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.EstadoCabecera[estadoCabeceraID=" + estadoCabeceraID + "]";
    }

}
