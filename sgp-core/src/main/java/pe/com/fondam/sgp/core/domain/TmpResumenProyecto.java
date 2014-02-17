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
@Table(name = "tmp_resumen_proyecto")
public class TmpResumenProyecto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    @Column(name = "tmp_Resumen_Proyecto_ID")
    private Integer tmpResumenProyectoID;
    
    @Column(name = "fk_idtablaesp_tipo_resumen_proy")
    private int fkIdtablaespTipoResumenProy;
    
    @Column(name = "fk_id_tabla_general")
    private int fkIdTablaGeneral;
    
    @Lob
    @Column(name = "definicion")
    private String definicion;
    
    @ManyToOne
    @JoinColumn(name = "tmp_Dato_Proyecto_ID", referencedColumnName = "TMP_Dato_Proyecto_ID")
    private TmpDatoProyecto tmpDatoProyecto;

    public Integer getTmpResumenProyectoID() {
        return tmpResumenProyectoID;
    }

    public void setTmpResumenProyectoID(Integer tmpResumenProyectoID) {
        this.tmpResumenProyectoID = tmpResumenProyectoID;
    }

    public int getFkIdtablaespTipoResumenProy() {
        return fkIdtablaespTipoResumenProy;
    }

    public void setFkIdtablaespTipoResumenProy(int fkIdtablaespTipoResumenProy) {
        this.fkIdtablaespTipoResumenProy = fkIdtablaespTipoResumenProy;
    }

    public int getFkIdTablaGeneral() {
        return fkIdTablaGeneral;
    }

    public void setFkIdTablaGeneral(int fkIdTablaGeneral) {
        this.fkIdTablaGeneral = fkIdTablaGeneral;
    }

    public String getDefinicion() {
        return definicion;
    }

    public void setDefinicion(String definicion) {
        this.definicion = definicion;
    }

    public TmpDatoProyecto getTmpDatoProyecto() {
        return tmpDatoProyecto;
    }

    public void setTmpDatoProyectoID(TmpDatoProyecto tmpDatoProyecto) {
        this.tmpDatoProyecto = tmpDatoProyecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tmpResumenProyectoID != null ? tmpResumenProyectoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmpResumenProyecto)) {
            return false;
        }
        TmpResumenProyecto other = (TmpResumenProyecto) object;
        if ((this.tmpResumenProyectoID == null && other.tmpResumenProyectoID != null) || (this.tmpResumenProyectoID != null && !this.tmpResumenProyectoID.equals(other.tmpResumenProyectoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.TmpResumenProyecto[ tmpResumenProyectoID=" + tmpResumenProyectoID + " ]";
    }
    
}
