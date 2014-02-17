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
@Table(name = "usuario")

public class Usuario implements Serializable {
   
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
  
    @Column(name = "Usuario_ID")
    private Integer usuarioID;
    
    @Column(name = "login")
    private String login;
    
    @Column(name = "password")
    private String password;
    
    @ManyToOne
    @JoinColumn(name = "Dato_Usuario_ID", referencedColumnName = "Dato_Usuario_ID")
    private DatoUsuario datoUsuario;
    
    @ManyToOne
    @JoinColumn(name = "Perfil_Usuario_ID", referencedColumnName = "Perfil_Usuario_ID")
    private PerfilUsuario perfilUsuario;
    

    public Integer getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(Integer usuarioID) {
        this.usuarioID = usuarioID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PerfilUsuario getPerfilUsuario() {
        return perfilUsuario;
    }

    public void setPerfilUsuario(PerfilUsuario perfilUsuario) {
        this.perfilUsuario = perfilUsuario;
    }

    public DatoUsuario getDatoUsuario() {
        return datoUsuario;
    }

    public void setDatoUsuario(DatoUsuario datoUsuario) {
        this.datoUsuario = datoUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioID != null ? usuarioID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.usuarioID == null && other.usuarioID != null) || (this.usuarioID != null && !this.usuarioID.equals(other.usuarioID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.Usuario[usuarioID=" + usuarioID + "]";
    }

}
