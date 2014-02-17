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
@Table(name = "inspeccion_admin")

public class InspeccionAdmin implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "Inspeccion_Admin_ID")
    private Integer inspeccionAdminID;
    
    @Column(name = "estado_eliminado")
    private int estadoEliminado;
    
    @Column(name = "si_no")
    private int siNo;
    @JoinColumn(name = "Detalle_Inspeccion_Admin_ID", referencedColumnName = "Detalle_Inspeccion_Admin_ID")
    @ManyToOne(optional = false)
    private DetalleInspeccionAdmin detalleInspeccionAdmin;
    @JoinColumn(name = "Informe_Visita_Campo_ID", referencedColumnName = "Informe_Visita_Campo_ID")
    @ManyToOne(optional = false)
    private InformeVisitaCampo informeVisitaCampo;

    public InspeccionAdmin() {
    }

    public InspeccionAdmin(Integer inspeccionAdminID) {
        this.inspeccionAdminID = inspeccionAdminID;
    }

    public InspeccionAdmin(Integer inspeccionAdminID, short estadoEliminado, short siNo) {
        this.inspeccionAdminID = inspeccionAdminID;
        this.estadoEliminado = estadoEliminado;
        this.siNo = siNo;
    }

    public Integer getInspeccionAdminID() {
        return inspeccionAdminID;
    }

    public void setInspeccionAdminID(Integer inspeccionAdminID) {
        this.inspeccionAdminID = inspeccionAdminID;
    }

    public int getEstadoEliminado() {
        return estadoEliminado;
    }

    public void setEstadoEliminado(int estadoEliminado) {
        this.estadoEliminado = estadoEliminado;
    }

    public int getSiNo() {
        return siNo;
    }

    public void setSiNo(int siNo) {
        this.siNo = siNo;
    }

    public DetalleInspeccionAdmin getDetalleInspeccionAdmin() {
        return detalleInspeccionAdmin;
    }

    public void setDetalleInspeccionAdmin(DetalleInspeccionAdmin detalleInspeccionAdmin) {
        this.detalleInspeccionAdmin = detalleInspeccionAdmin;
    }

    public InformeVisitaCampo getInformeVisitaCampo() {
        return informeVisitaCampo;
    }

    public void setInformeVisitaCampo(InformeVisitaCampo informeVisitaCampo) {
        this.informeVisitaCampo = informeVisitaCampo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (inspeccionAdminID != null ? inspeccionAdminID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InspeccionAdmin)) {
            return false;
        }
        InspeccionAdmin other = (InspeccionAdmin) object;
        if ((this.inspeccionAdminID == null && other.inspeccionAdminID != null) || (this.inspeccionAdminID != null && !this.inspeccionAdminID.equals(other.inspeccionAdminID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.InspeccionAdmin[inspeccionAdminID=" + inspeccionAdminID + "]";
    }

}
