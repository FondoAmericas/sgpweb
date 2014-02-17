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
@Table(name = "meta_perfil")

public class MetaPerfil implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   
    @Column(name = "Meta_Perfil_ID")
    private Integer metaPerfilID;
   
    @Column(name = "fk_idtablaesp_unidad_medida")
    private int fkIdtablaespUnidadMedida;
   
    @Column(name = "cantidad")
    private int cantidad;
    @JoinColumn(name = "Actividad_Perfil_ID", referencedColumnName = "Actividad_Perfil_ID")
    @ManyToOne(optional = false)
    private ActividadPerfil actividadPerfil;

    public MetaPerfil() {
    }

    public MetaPerfil(Integer metaPerfilID) {
        this.metaPerfilID = metaPerfilID;
    }

    public MetaPerfil(Integer metaPerfilID, int fkIdtablaespUnidadMedida, int cantidad) {
        this.metaPerfilID = metaPerfilID;
        this.fkIdtablaespUnidadMedida = fkIdtablaespUnidadMedida;
        this.cantidad = cantidad;
    }

    public Integer getMetaPerfilID() {
        return metaPerfilID;
    }

    public void setMetaPerfilID(Integer metaPerfilID) {
        this.metaPerfilID = metaPerfilID;
    }

    public int getFkIdtablaespUnidadMedida() {
        return fkIdtablaespUnidadMedida;
    }

    public void setFkIdtablaespUnidadMedida(int fkIdtablaespUnidadMedida) {
        this.fkIdtablaespUnidadMedida = fkIdtablaespUnidadMedida;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public ActividadPerfil getActividadPerfil() {
        return actividadPerfil;
    }

    public void setActividadPerfil(ActividadPerfil actividadPerfil) {
        this.actividadPerfil = actividadPerfil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (metaPerfilID != null ? metaPerfilID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MetaPerfil)) {
            return false;
        }
        MetaPerfil other = (MetaPerfil) object;
        if ((this.metaPerfilID == null && other.metaPerfilID != null) || (this.metaPerfilID != null && !this.metaPerfilID.equals(other.metaPerfilID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.MetaPerfil[metaPerfilID=" + metaPerfilID + "]";
    }

}
