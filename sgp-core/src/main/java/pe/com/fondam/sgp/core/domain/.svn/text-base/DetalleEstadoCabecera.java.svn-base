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
@Table(name = "detalle_estado_cabecera")
public class DetalleEstadoCabecera implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    @Column(name = "Detalle_Estado_Cabecera_ID")
    private Integer detalleEstadoCabeceraID;
    
    @Column(name = "prefijo_estado")
    private String prefijoEstado;
  
    @Column(name = "descrip_estado")
    private String descripEstado;
   
    @Column(name = "estado_eliminado")
    private int estadoEliminado;
    
    @JoinColumn(name = "Estado_Cabecera_ID", referencedColumnName = "Estado_Cabecera_ID")
    @ManyToOne
    private EstadoCabecera estadoCabecera;

    public Integer getDetalleEstadoCabeceraID() {
        return detalleEstadoCabeceraID;
    }

    public void setDetalleEstadoCabeceraID(Integer detalleEstadoCabeceraID) {
        this.detalleEstadoCabeceraID = detalleEstadoCabeceraID;
    }

    public String getPrefijoEstado() {
        return prefijoEstado;
    }

    public void setPrefijoEstado(String prefijoEstado) {
        this.prefijoEstado = prefijoEstado;
    }

    public String getDescripEstado() {
        return descripEstado;
    }

    public void setDescripEstado(String descripEstado) {
        this.descripEstado = descripEstado;
    }

    public int getEstadoEliminado() {
        return estadoEliminado;
    }

    public void setEstadoEliminado(int estadoEliminado) {
        this.estadoEliminado = estadoEliminado;
    }

    public EstadoCabecera getEstadoCabecera() {
        return estadoCabecera;
    }

    public void setEstadoCabecera(EstadoCabecera estadoCabecera) {
        this.estadoCabecera = estadoCabecera;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleEstadoCabeceraID != null ? detalleEstadoCabeceraID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleEstadoCabecera)) {
            return false;
        }
        DetalleEstadoCabecera other = (DetalleEstadoCabecera) object;
        if ((this.detalleEstadoCabeceraID == null && other.detalleEstadoCabeceraID != null) || (this.detalleEstadoCabeceraID != null && !this.detalleEstadoCabeceraID.equals(other.detalleEstadoCabeceraID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.DetalleEstadoCabecera[detalleEstadoCabeceraID=" + detalleEstadoCabeceraID + "]";
    }

}
