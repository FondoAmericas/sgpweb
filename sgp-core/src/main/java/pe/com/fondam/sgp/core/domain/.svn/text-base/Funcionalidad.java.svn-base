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
@Table(name = "funcionalidad")
public class Funcionalidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     
    @Column(name = "Funcionalidad_ID")
    private Integer funcionalidadID;
     
    @Column(name = "titulo")
    private String titulo;
     
    @Column(name = "url")
    private String url;
    public Funcionalidad() {
    }

    public Funcionalidad(Integer funcionalidadID) {
        this.funcionalidadID = funcionalidadID;
    }

    public Funcionalidad(Integer funcionalidadID, String titulo, String url) {
        this.funcionalidadID = funcionalidadID;
        this.titulo = titulo;
        this.url = url;
    }

    public Integer getFuncionalidadID() {
        return funcionalidadID;
    }

    public void setFuncionalidadID(Integer funcionalidadID) {
        this.funcionalidadID = funcionalidadID;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (funcionalidadID != null ? funcionalidadID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funcionalidad)) {
            return false;
        }
        Funcionalidad other = (Funcionalidad) object;
        if ((this.funcionalidadID == null && other.funcionalidadID != null) || (this.funcionalidadID != null && !this.funcionalidadID.equals(other.funcionalidadID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.Funcionalidad[funcionalidadID=" + funcionalidadID + "]";
    }

}
