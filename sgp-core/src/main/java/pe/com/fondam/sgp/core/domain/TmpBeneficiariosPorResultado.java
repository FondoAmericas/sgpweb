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
@Table(name = "tmp_beneficiarios_por_resultado")
public class TmpBeneficiariosPorResultado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    @Column(name = "TMP_Beneficiarios_Por_Resultado_ID")
    private Integer tMPBeneficiariosPorResultadoID;
    
    @Column(name = "fk_idtablaesp_tipo_beneficiario")
    private int fkIdtablaespTipoBeneficiario;
    
    @Lob
    @Column(name = "caracteristicas_poblacion")
    private String caracteristicasPoblacion;
    
    @Column(name = "cantidad_programado")
    private int cantidadProgramado;
    
    @Column(name = "fk_idtablaesp_Estrato")
    private int fkidtablaespEstrato;
    
    @Lob
    @Column(name = "descripcion")
    private String descripcion;
    
    @ManyToOne
    @JoinColumn(name = "TMP_Perfil_ID", referencedColumnName = "TMP_Perfil_ID")
    private TmpPerfil tMPPerfil;
    
    @ManyToOne
    @JoinColumn(name = "tmp_ubicacion_proyecto_ID", referencedColumnName = "TMP_Ubicacion_Proyecto_ID")
    private TmpUbicacionProyecto tmpubicacionproyecto;

    public Integer getTMPBeneficiariosPorResultadoID() {
        return tMPBeneficiariosPorResultadoID;
    }

    public void setTMPBeneficiariosPorResultadoID(Integer tMPBeneficiariosPorResultadoID) {
        this.tMPBeneficiariosPorResultadoID = tMPBeneficiariosPorResultadoID;
    }

    public int getFkIdtablaespTipoBeneficiario() {
        return fkIdtablaespTipoBeneficiario;
    }

    public void setFkIdtablaespTipoBeneficiario(int fkIdtablaespTipoBeneficiario) {
        this.fkIdtablaespTipoBeneficiario = fkIdtablaespTipoBeneficiario;
    }

    public String getCaracteristicasPoblacion() {
        return caracteristicasPoblacion;
    }

    public void setCaracteristicasPoblacion(String caracteristicasPoblacion) {
        this.caracteristicasPoblacion = caracteristicasPoblacion;
    }

    public int getCantidadProgramado() {
        return cantidadProgramado;
    }

    public void setCantidadProgramado(int cantidadProgramado) {
        this.cantidadProgramado = cantidadProgramado;
    }

    public int getFkidtablaespEstrato() {
        return fkidtablaespEstrato;
    }

    public void setFkidtablaespEstrato(int fkidtablaespEstrato) {
        this.fkidtablaespEstrato = fkidtablaespEstrato;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TmpPerfil getTMPPerfil() {
        return tMPPerfil;
    }

    public void setTMPPerfil(TmpPerfil tMPPerfil) {
        this.tMPPerfil = tMPPerfil;
    }

    public TmpUbicacionProyecto getTmpubicacionproyecto() {
        return tmpubicacionproyecto;
    }

    public void setTmpubicacionproyecto(TmpUbicacionProyecto tmpubicacionproyecto) {
        this.tmpubicacionproyecto = tmpubicacionproyecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tMPBeneficiariosPorResultadoID != null ? tMPBeneficiariosPorResultadoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmpBeneficiariosPorResultado)) {
            return false;
        }
        TmpBeneficiariosPorResultado other = (TmpBeneficiariosPorResultado) object;
        if ((this.tMPBeneficiariosPorResultadoID == null && other.tMPBeneficiariosPorResultadoID != null) || (this.tMPBeneficiariosPorResultadoID != null && !this.tMPBeneficiariosPorResultadoID.equals(other.tMPBeneficiariosPorResultadoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.TmpBeneficiariosPorResultado[ tMPBeneficiariosPorResultadoID=" + tMPBeneficiariosPorResultadoID + " ]";
    }
    
}
