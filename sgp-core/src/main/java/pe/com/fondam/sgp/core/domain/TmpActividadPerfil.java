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
@Table(name = "tmp_actividad_perfil")
public class TmpActividadPerfil implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    @Column(name = "TMP_Actividad_Perfil_ID")
    private Integer tMPActividadPerfilID;

    @Lob
    @Column(name = "nombre_actividad")
    private String nombreActividad;

    @Lob
    @Column(name = "descripcion_actividad")
    private String descripcionActividad;
   
    @Column(name = "duracion_meses")
    private int duracionMeses;
    
    @ManyToOne
    @JoinColumn(name = "TMP_Perfil_ID", referencedColumnName = "TMP_Perfil_ID")
    private TmpPerfil tMPPerfil;

    public Integer getTMPActividadPerfilID() {
        return tMPActividadPerfilID;
    }

    public void setTMPActividadPerfilID(Integer tMPActividadPerfilID) {
        this.tMPActividadPerfilID = tMPActividadPerfilID;
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

    public TmpPerfil getTMPPerfil() {
        return tMPPerfil;
    }

    public void setTMPPerfil(TmpPerfil tMPPerfil) {
        this.tMPPerfil = tMPPerfil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tMPActividadPerfilID != null ? tMPActividadPerfilID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmpActividadPerfil)) {
            return false;
        }
        TmpActividadPerfil other = (TmpActividadPerfil) object;
        if ((this.tMPActividadPerfilID == null && other.tMPActividadPerfilID != null) || (this.tMPActividadPerfilID != null && !this.tMPActividadPerfilID.equals(other.tMPActividadPerfilID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.TmpActividadPerfil[ tMPActividadPerfilID=" + tMPActividadPerfilID + " ]";
    }
    
}
