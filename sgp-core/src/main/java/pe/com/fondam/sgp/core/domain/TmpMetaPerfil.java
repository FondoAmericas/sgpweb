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
@Table(name = "tmp_meta_perfil")
public class TmpMetaPerfil implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    @Column(name = "TMP_Meta_Perfil_ID")
    private Integer tMPMetaPerfilID;
    
    @Column(name = "fk_idtablaesp_unidad_medida")
    private int fkIdtablaespUnidadMedida;
    
    @Column(name = "cantidad")
    private int cantidad;
    
    @ManyToOne 
    @JoinColumn(name = "TMP_Actividad_Perfil_ID", referencedColumnName = "TMP_Actividad_Perfil_ID")
    private TmpActividadPerfil tMPActividadPerfil;

    public Integer getTMPMetaPerfilID() {
        return tMPMetaPerfilID;
    }

    public void setTMPMetaPerfilID(Integer tMPMetaPerfilID) {
        this.tMPMetaPerfilID = tMPMetaPerfilID;
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

    public TmpActividadPerfil getTMPActividadPerfilID() {
        return tMPActividadPerfil;
    }

    public void setTMPActividadPerfilID(TmpActividadPerfil tMPActividadPerfil) {
        this.tMPActividadPerfil = tMPActividadPerfil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tMPMetaPerfilID != null ? tMPMetaPerfilID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmpMetaPerfil)) {
            return false;
        }
        TmpMetaPerfil other = (TmpMetaPerfil) object;
        if ((this.tMPMetaPerfilID == null && other.tMPMetaPerfilID != null) || (this.tMPMetaPerfilID != null && !this.tMPMetaPerfilID.equals(other.tMPMetaPerfilID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.TmpMetaPerfil[ tMPMetaPerfilID=" + tMPMetaPerfilID + " ]";
    }
    
}
