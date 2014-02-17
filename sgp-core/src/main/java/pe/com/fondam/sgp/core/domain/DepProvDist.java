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
@Table(name = "dep_prov_dist")
public class DepProvDist implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     
    @Column(name = "Dep_Prov_Dist_ID")
    private Integer depProvDistID;
     
    @Column(name = "id_departamento")
    private String idDepartamento;
     
    @Column(name = "id_provincia")
    private String idProvincia;
     
    @Column(name = "id_distrito")
    private String idDistrito;
     
    @Column(name = "descripcion")
    private String descripcion;
   
    public Integer getDepProvDistID() {
        return depProvDistID;
    }

    public void setDepProvDistID(Integer depProvDistID) {
        this.depProvDistID = depProvDistID;
    }

    public String getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(String idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(String idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(String idDistrito) {
        this.idDistrito = idDistrito;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (depProvDistID != null ? depProvDistID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DepProvDist)) {
            return false;
        }
        DepProvDist other = (DepProvDist) object;
        if ((this.depProvDistID == null && other.depProvDistID != null) || (this.depProvDistID != null && !this.depProvDistID.equals(other.depProvDistID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.DepProvDist[depProvDistID=" + depProvDistID + "]";
    }

}
