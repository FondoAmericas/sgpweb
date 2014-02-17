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
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "detalle_inspeccion_admin")
public class DetalleInspeccionAdmin implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     
    @Column(name = "Detalle_Inspeccion_Admin_ID")
    private Integer detalleInspeccionAdminID;
     
    @Column(name = "fk_idtablaesp_tipo_superv_admin")
    private int fkIdtablaespTipoSupervAdmin;
     
    @Lob
    @Column(name = "descripcion_detalle_inspec_admin")
    private String descripcionDetalleInspecAdmin;
     
    @Column(name = "estado_eliminado")
    private int estadoEliminado;

    public DetalleInspeccionAdmin() {
    }

    public DetalleInspeccionAdmin(Integer detalleInspeccionAdminID) {
        this.detalleInspeccionAdminID = detalleInspeccionAdminID;
    }

    public DetalleInspeccionAdmin(Integer detalleInspeccionAdminID, int fkIdtablaespTipoSupervAdmin, String descripcionDetalleInspecAdmin, int estadoEliminado) {
        this.detalleInspeccionAdminID = detalleInspeccionAdminID;
        this.fkIdtablaespTipoSupervAdmin = fkIdtablaespTipoSupervAdmin;
        this.descripcionDetalleInspecAdmin = descripcionDetalleInspecAdmin;
        this.estadoEliminado = estadoEliminado;
    }

    public Integer getDetalleInspeccionAdminID() {
        return detalleInspeccionAdminID;
    }

    public void setDetalleInspeccionAdminID(Integer detalleInspeccionAdminID) {
        this.detalleInspeccionAdminID = detalleInspeccionAdminID;
    }

    public int getFkIdtablaespTipoSupervAdmin() {
        return fkIdtablaespTipoSupervAdmin;
    }

    public void setFkIdtablaespTipoSupervAdmin(int fkIdtablaespTipoSupervAdmin) {
        this.fkIdtablaespTipoSupervAdmin = fkIdtablaespTipoSupervAdmin;
    }

    public String getDescripcionDetalleInspecAdmin() {
        return descripcionDetalleInspecAdmin;
    }

    public void setDescripcionDetalleInspecAdmin(String descripcionDetalleInspecAdmin) {
        this.descripcionDetalleInspecAdmin = descripcionDetalleInspecAdmin;
    }

    public int getEstadoEliminado() {
        return estadoEliminado;
    }

    public void setEstadoEliminado(int estadoEliminado) {
        this.estadoEliminado = estadoEliminado;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleInspeccionAdminID != null ? detalleInspeccionAdminID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleInspeccionAdmin)) {
            return false;
        }
        DetalleInspeccionAdmin other = (DetalleInspeccionAdmin) object;
        if ((this.detalleInspeccionAdminID == null && other.detalleInspeccionAdminID != null) || (this.detalleInspeccionAdminID != null && !this.detalleInspeccionAdminID.equals(other.detalleInspeccionAdminID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.DetalleInspeccionAdmin[detalleInspeccionAdminID=" + detalleInspeccionAdminID + "]";
    }

}
