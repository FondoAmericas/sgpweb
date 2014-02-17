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
@Table(name = "funcionalidad_perfil")
public class FuncionalidadPerfil implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     
    @Column(name = "Funcionalidad_Perfil_ID")
    private Integer funcionalidadPerfilID;
    @JoinColumn(name = "Funcionalidad_ID", referencedColumnName = "Funcionalidad_ID")
    @ManyToOne(optional = false)
    private Funcionalidad funcionalidad;
    @JoinColumn(name = "Perfil_Usuario_ID", referencedColumnName = "Perfil_Usuario_ID")
    @ManyToOne(optional = false)
    private PerfilUsuario perfilUsuario;

    public FuncionalidadPerfil() {
    }

    public FuncionalidadPerfil(Integer funcionalidadPerfilID) {
        this.funcionalidadPerfilID = funcionalidadPerfilID;
    }

    public Integer getFuncionalidadPerfilID() {
        return funcionalidadPerfilID;
    }

    public void setFuncionalidadPerfilID(Integer funcionalidadPerfilID) {
        this.funcionalidadPerfilID = funcionalidadPerfilID;
    }

    public Funcionalidad getFuncionalidad() {
        return funcionalidad;
    }

    public void setFuncionalidad(Funcionalidad funcionalidad) {
        this.funcionalidad = funcionalidad;
    }

    public PerfilUsuario getPerfilUsuario() {
        return perfilUsuario;
    }

    public void setPerfilUsuario(PerfilUsuario perfilUsuario) {
        this.perfilUsuario = perfilUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (funcionalidadPerfilID != null ? funcionalidadPerfilID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FuncionalidadPerfil)) {
            return false;
        }
        FuncionalidadPerfil other = (FuncionalidadPerfil) object;
        if ((this.funcionalidadPerfilID == null && other.funcionalidadPerfilID != null) || (this.funcionalidadPerfilID != null && !this.funcionalidadPerfilID.equals(other.funcionalidadPerfilID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.FuncionalidadPerfil[funcionalidadPerfilID=" + funcionalidadPerfilID + "]";
    }

}
