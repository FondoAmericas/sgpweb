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
@Table(name = "tmp_ubicacion_proyecto")
public class TmpUbicacionProyecto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "TMP_Ubicacion_Proyecto_ID")
    private Integer tMPUbicacionProyectoID;

    @Lob
    @Column(name = "detalle_ubicacion")
    private String detalleUbicacion;

    @JoinColumn(name = "Dep_Prov_Dist_ID", referencedColumnName = "Dep_Prov_Dist_ID")
    @ManyToOne(optional = false)
    private DepProvDist depProvDist;
    
    @JoinColumn(name = "TMP_Dato_Proyecto_ID", referencedColumnName = "TMP_Dato_Proyecto_ID")
    @ManyToOne(optional = false)
    private TmpDatoProyecto tMPDatoProyecto;

    public Integer getTMPUbicacionProyectoID() {
        return tMPUbicacionProyectoID;
    }

    public void setTMPUbicacionProyectoID(Integer tMPUbicacionProyectoID) {
        this.tMPUbicacionProyectoID = tMPUbicacionProyectoID;
    }

    public String getDetalleUbicacion() {
        return detalleUbicacion;
    }

    public void setDetalleUbicacion(String detalleUbicacion) {
        this.detalleUbicacion = detalleUbicacion;
    }

    public DepProvDist getDepProvDist() {
        return depProvDist;
    }

    public void setDepProvDist(DepProvDist depProvDist) {
        this.depProvDist = depProvDist;
    }

    public TmpDatoProyecto getTMPDatoProyecto() {
        return tMPDatoProyecto;
    }

    public void setTMPDatoProyecto(TmpDatoProyecto tMPDatoProyecto) {
        this.tMPDatoProyecto = tMPDatoProyecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tMPUbicacionProyectoID != null ? tMPUbicacionProyectoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmpUbicacionProyecto)) {
            return false;
        }
        TmpUbicacionProyecto other = (TmpUbicacionProyecto) object;
        if ((this.tMPUbicacionProyectoID == null && other.tMPUbicacionProyectoID != null) || (this.tMPUbicacionProyectoID != null && !this.tMPUbicacionProyectoID.equals(other.tMPUbicacionProyectoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.TmpUbicacionProyecto[ tMPUbicacionProyectoID=" + tMPUbicacionProyectoID + " ]";
    }
    
}

