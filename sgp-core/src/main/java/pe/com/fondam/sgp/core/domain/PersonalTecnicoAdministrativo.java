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
import javax.persistence.Transient;

@Entity
@Table(name = "personal_tecnico_administrativo")

public class PersonalTecnicoAdministrativo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Personal_Tecnico_Administrativo_ID")
    private Integer personalTecnicoAdministrativoID;
    
    @Column(name = "nombre_completo")
    private String nombreCompleto;
    
    @Column(name = "fk_idtablaesp_formacion_profesional")
    private int fkIdtablaespFormacionProfesional;
    
    @Transient
    private String formacionProfesionalNombre;
    
    @Column(name = "responsabilidad")
    private String responsabilidad;
    
    @Column(name = "fk_idtablaesp_tiempo_dedicado")
    private int fkIdtablaespTiempoDedicado;
    
    @Transient
    private String tiempoDedicadoNombre;
    
    @Column(name = "porcentage_participacion")
    private int porcentageParticipacion;
    
    @Column(name = "fk_idtablaesp_etapa_personal_tecnico")
    private int fkIdtablaespEtapaPersonalTecnico;
    
    @Transient
    private String etapaPersonalTecnicoNombre;
    
    @Column(name = "fk_idpersonal_tecnico_adm_reemplazo")
    private int fkIdpersonalTecnicoAdmReemplazo;
    
    @Transient
    private String personalTecnicoAdmReemplazoNombre;
    
    @Column(name = "salario_mensual")
    private double salarioMensual;
    
    @Column(name = "fk_idtablaesp_tipo_moneda")
    private Integer fkIdtablaespTipoMoneda;

	@Column(name = "estado_eliminado")
	private Integer estadoEliminado;
	
    @Transient
    private String tipoMonedaNombre;
    
    /*@ManyToOne
    @JoinColumn(name = "Reporte_Avance_ID", referencedColumnName = "Reporte_Avance_ID")
    private ReporteAvance reporteAvance;
    
    @ManyToOne
    @JoinColumn(name = "Dato_Plan_Operativo_ID", referencedColumnName = "Dato_Plan_Operativo_ID")
    private DatoPlanOperativo datoPlanOperativo;
  */  
    @ManyToOne
    @JoinColumn(name = "Dato_Proyecto_ID", referencedColumnName = "Dato_Proyecto_ID")
    private DatoProyecto datoProyecto;
        
    @ManyToOne
    @JoinColumn(name = "Institucion_ID", referencedColumnName = "Institucion_ID")
    private Institucion institucion;
    

    public PersonalTecnicoAdministrativo() {
    }

    public PersonalTecnicoAdministrativo(Integer personalTecnicoAdministrativoID) {
        this.personalTecnicoAdministrativoID = personalTecnicoAdministrativoID;
    }

    public PersonalTecnicoAdministrativo(Integer personalTecnicoAdministrativoID, String nombreCompleto, int fkIdtablaespFormacionProfesional, String responsabilidad, int fkIdtablaespTiempoDedicado, int porcentageParticipacion, int fkIdtablaespEtapaPersonalTecnico, int fkIdpersonalTecnicoAdmReemplazo, double salarioMensual) {
        this.personalTecnicoAdministrativoID = personalTecnicoAdministrativoID;
        this.nombreCompleto = nombreCompleto;
        this.fkIdtablaespFormacionProfesional = fkIdtablaespFormacionProfesional;
        this.responsabilidad = responsabilidad;
        this.fkIdtablaespTiempoDedicado = fkIdtablaespTiempoDedicado;
        this.porcentageParticipacion = porcentageParticipacion;
        this.fkIdtablaespEtapaPersonalTecnico = fkIdtablaespEtapaPersonalTecnico;
        this.fkIdpersonalTecnicoAdmReemplazo = fkIdpersonalTecnicoAdmReemplazo;
        this.salarioMensual = salarioMensual;
    }

    public Integer getPersonalTecnicoAdministrativoID() {
        return personalTecnicoAdministrativoID;
    }

    public void setPersonalTecnicoAdministrativoID(Integer personalTecnicoAdministrativoID) {
        this.personalTecnicoAdministrativoID = personalTecnicoAdministrativoID;
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

    public void setFkIdtablaespTipoMoneda(Integer fkIdtablaespTipoMoneda) {
		this.fkIdtablaespTipoMoneda = fkIdtablaespTipoMoneda;
	}

	public Integer getFkIdtablaespTipoMoneda() {
		return fkIdtablaespTipoMoneda;
	}

	/*
	public ReporteAvance getReporteAvance() {
        return reporteAvance;
    }

    public void setReporteAvance(ReporteAvance reporteAvance) {
        this.reporteAvance = reporteAvance;
    }

    public DatoPlanOperativo getDatoPlanOperativo() {
        return datoPlanOperativo;
    }

    public void setDatoPlanOperativo(DatoPlanOperativo datoPlanOperativo) {
        this.datoPlanOperativo = datoPlanOperativo;
    }*/

    public Institucion getInstitucion() {
        return institucion;
    }

    public void setInstitucion(Institucion institucion) {
        this.institucion = institucion;
    }    

    public String getFormacionProfesionalNombre() {
		return formacionProfesionalNombre;
	}

	public void setFormacionProfesionalNombre(String formacionProfesionalNombre) {
		this.formacionProfesionalNombre = formacionProfesionalNombre;
	}

	public String getTiempoDedicadoNombre() {
		return tiempoDedicadoNombre;
	}

	public void setTiempoDedicadoNombre(String tiempoDedicadoNombre) {
		this.tiempoDedicadoNombre = tiempoDedicadoNombre;
	}

	public String getEtapaPersonalTecnicoNombre() {
		return etapaPersonalTecnicoNombre;
	}

	public void setEtapaPersonalTecnicoNombre(String etapaPersonalTecnicoNombre) {
		this.etapaPersonalTecnicoNombre = etapaPersonalTecnicoNombre;
	}

	public String getPersonalTecnicoAdmReemplazoNombre() {
		return personalTecnicoAdmReemplazoNombre;
	}

	public void setPersonalTecnicoAdmReemplazoNombre(
			String personalTecnicoAdmReemplazoNombre) {
		this.personalTecnicoAdmReemplazoNombre = personalTecnicoAdmReemplazoNombre;
	}
	
	public String getTipoMonedaNombre() {
		return tipoMonedaNombre;
	}
	
	public void setTipoMonedaNombre(String tipoMonedaNombre) {
		this.tipoMonedaNombre = tipoMonedaNombre;
	}

	public void setDatoProyecto(DatoProyecto datoProyecto) {
		this.datoProyecto = datoProyecto;
	}

	public DatoProyecto getDatoProyecto() {
		return datoProyecto;
	}

	public Integer getEstadoEliminado() {
		return estadoEliminado;
	}

	public void setEstadoEliminado(Integer estadoEliminado) {
		this.estadoEliminado = estadoEliminado;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (personalTecnicoAdministrativoID != null ? personalTecnicoAdministrativoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonalTecnicoAdministrativo)) {
            return false;
        }
        PersonalTecnicoAdministrativo other = (PersonalTecnicoAdministrativo) object;
        if ((this.personalTecnicoAdministrativoID == null && other.personalTecnicoAdministrativoID != null) || (this.personalTecnicoAdministrativoID != null && !this.personalTecnicoAdministrativoID.equals(other.personalTecnicoAdministrativoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.PersonalTecnicoAdministrativo[personalTecnicoAdministrativoID=" + personalTecnicoAdministrativoID + "]";
    }

}
