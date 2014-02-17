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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "actividad_perfil")
public class ActividadPerfil implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    @Column(name = "Actividad_Perfil_ID")
    private Integer actividadPerfilID;
     
    @Lob
    @Column(name = "nombre_actividad")
    private String nombreActividad;
    
    @Lob
    @Column(name = "descripcion_actividad")
    private String descripcionActividad;
     
    @Column(name = "duracion_meses")
    private int duracionMeses;
    
    @ManyToOne
    @JoinColumn(name = "Perfil_ID", referencedColumnName = "Perfil_ID")
    private Perfil perfil;

    public Integer getActividadPerfilID() {
        return actividadPerfilID;
    }

    public void setActividadPerfilID(Integer actividadPerfilID) {
        this.actividadPerfilID = actividadPerfilID;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public String getDescripcionActividad() {
        return descripcionActividad;
    }

    public void setDescripcionActividad(String descripcionActividad) {
        this.descripcionActividad = descripcionActividad;
    }

    public int getDuracionMeses() {
        return duracionMeses;
    }

    public void setDuracionMeses(int duracionMeses) {
        this.duracionMeses = duracionMeses;
    }
    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (actividadPerfilID != null ? actividadPerfilID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActividadPerfil)) {
            return false;
        }
        ActividadPerfil other = (ActividadPerfil) object;
        if ((this.actividadPerfilID == null && other.actividadPerfilID != null) || (this.actividadPerfilID != null && !this.actividadPerfilID.equals(other.actividadPerfilID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.ActividadPerfil[actividadPerfilID=" + actividadPerfilID + "]";
    }

}
