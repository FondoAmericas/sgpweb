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
@Table(name = "tmp_personal_tecnico_administrativo")
public class TmpPersonalTecnicoAdministrativo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    @Column(name = "tmp_Personal_Tecnico_Administrativo_ID")
    private Integer tmpPersonalTecnicoAdministrativoID;
    
    @Column(name = "nombre_completo")
    private String nombreCompleto;
    
    @Column(name = "fk_idtablaesp_formacion_profesional")
    private int fkIdtablaespFormacionProfesional;
    
    @Column(name = "responsabilidad")
    private String responsabilidad;
    
    @Column(name = "fk_idtablaesp_tiempo_dedicado")
    private int fkIdtablaespTiempoDedicado;
    
    @Column(name = "porcentage_participacion")
    private int porcentageParticipacion;
    
    @Column(name = "fk_idtablaesp_etapa_personal_tecnico")
    private int fkIdtablaespEtapaPersonalTecnico;
    
    @Column(name = "fk_idpersonal_tecnico_adm_reemplazo")
    private int fkIdpersonalTecnicoAdmReemplazo;
    
    @Column(name = "salario_mensual")
    private double salarioMensual;
    
    @Column(name = "Dato_Plan_Operativo_ID")
    private int datoPlanOperativoID;
    
    @ManyToOne
    @JoinColumn(name = "tmp_Institucion_ID", referencedColumnName = "TMP_Institucion_ID")
    private TmpInstitucion tmpInstitucion;

    public Integer getTmpPersonalTecnicoAdministrativoID() {
        return tmpPersonalTecnicoAdministrativoID;
    }

    public void setTmpPersonalTecnicoAdministrativoID(Integer tmpPersonalTecnicoAdministrativoID) {
        this.tmpPersonalTecnicoAdministrativoID = tmpPersonalTecnicoAdministrativoID;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getFkIdtablaespFormacionProfesional() {
        return fkIdtablaespFormacionProfesional;
    }

    public void setFkIdtablaespFormacionProfesional(int fkIdtablaespFormacionProfesional) {
        this.fkIdtablaespFormacionProfesional = fkIdtablaespFormacionProfesional;
    }

    public String getResponsabilidad() {
        return responsabilidad;
    }

    public void setResponsabilidad(String responsabilidad) {
        this.responsabilidad = responsabilidad;
    }

    public int getFkIdtablaespTiempoDedicado() {
        return fkIdtablaespTiempoDedicado;
    }

    public void setFkIdtablaespTiempoDedicado(int fkIdtablaespTiempoDedicado) {
        this.fkIdtablaespTiempoDedicado = fkIdtablaespTiempoDedicado;
    }

    public int getPorcentageParticipacion() {
        return porcentageParticipacion;
    }

    public void setPorcentageParticipacion(int porcentageParticipacion) {
        this.porcentageParticipacion = porcentageParticipacion;
    }

    public int getFkIdtablaespEtapaPersonalTecnico() {
        return fkIdtablaespEtapaPersonalTecnico;
    }

    public void setFkIdtablaespEtapaPersonalTecnico(int fkIdtablaespEtapaPersonalTecnico) {
        this.fkIdtablaespEtapaPersonalTecnico = fkIdtablaespEtapaPersonalTecnico;
    }

    public int getFkIdpersonalTecnicoAdmReemplazo() {
        return fkIdpersonalTecnicoAdmReemplazo;
    }

    public void setFkIdpersonalTecnicoAdmReemplazo(int fkIdpersonalTecnicoAdmReemplazo) {
        this.fkIdpersonalTecnicoAdmReemplazo = fkIdpersonalTecnicoAdmReemplazo;
    }

    public double getSalarioMensual() {
        return salarioMensual;
    }

    public void setSalarioMensual(double salarioMensual) {
        this.salarioMensual = salarioMensual;
    }

    public int getDatoPlanOperativoID() {
        return datoPlanOperativoID;
    }

    public void setDatoPlanOperativoID(int datoPlanOperativoID) {
        this.datoPlanOperativoID = datoPlanOperativoID;
    }

    public TmpInstitucion getTmpInstitucion() {
        return tmpInstitucion;
    }

    public void setTmpInstitucionID(TmpInstitucion tmpInstitucion) {
        this.tmpInstitucion = tmpInstitucion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tmpPersonalTecnicoAdministrativoID != null ? tmpPersonalTecnicoAdministrativoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmpPersonalTecnicoAdministrativo)) {
            return false;
        }
        TmpPersonalTecnicoAdministrativo other = (TmpPersonalTecnicoAdministrativo) object;
        if ((this.tmpPersonalTecnicoAdministrativoID == null && other.tmpPersonalTecnicoAdministrativoID != null) || (this.tmpPersonalTecnicoAdministrativoID != null && !this.tmpPersonalTecnicoAdministrativoID.equals(other.tmpPersonalTecnicoAdministrativoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.TmpPersonalTecnicoAdministrativo[ tmpPersonalTecnicoAdministrativoID=" + tmpPersonalTecnicoAdministrativoID + " ]";
    }
    
}
