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
@Table(name = "perfil_usuario")

public class PerfilUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "Perfil_Usuario_ID")
    private Integer perfilUsuarioID;
    
    @Column(name = "descripcion_perfil")
    private String descripcionPerfil;
    
    @Column(name = "prefijo")
    private String prefijo;


    public PerfilUsuario() {
    }

    public PerfilUsuario(Integer perfilUsuarioID) {
        this.perfilUsuarioID = perfilUsuarioID;
    }

    public PerfilUsuario(Integer perfilUsuarioID, String descripcionPerfil, String prefijo) {
        this.perfilUsuarioID = perfilUsuarioID;
        this.descripcionPerfil = descripcionPerfil;
        this.prefijo = prefijo;
    }

    public Integer getPerfilUsuarioID() {
        return perfilUsuarioID;
    }

    public void setPerfilUsuarioID(Integer perfilUsuarioID) {
        this.perfilUsuarioID = perfilUsuarioID;
    }

    public String getDescripcionPerfil() {
        return descripcionPerfil;
    }

    public void setDescripcionPerfil(String descripcionPerfil) {
        this.descripcionPerfil = descripcionPerfil;
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

 

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (perfilUsuarioID != null ? perfilUsuarioID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PerfilUsuario)) {
            return false;
        }
        PerfilUsuario other = (PerfilUsuario) object;
        if ((this.perfilUsuarioID == null && other.perfilUsuarioID != null) || (this.perfilUsuarioID != null && !this.perfilUsuarioID.equals(other.perfilUsuarioID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.PerfilUsuario[perfilUsuarioID=" + perfilUsuarioID + "]";
    }

}
