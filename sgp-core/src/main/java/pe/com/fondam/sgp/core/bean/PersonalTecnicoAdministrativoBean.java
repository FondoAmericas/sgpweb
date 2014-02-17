package pe.com.fondam.sgp.core.bean;

import java.io.Serializable;

import pe.com.fondam.sgp.core.domain.DatoPlanOperativo;
import pe.com.fondam.sgp.core.domain.DatoProyecto;
import pe.com.fondam.sgp.core.domain.Institucion;
import pe.com.fondam.sgp.core.domain.ReporteAvance;

public class PersonalTecnicoAdministrativoBean  implements Serializable {

		/**
	 * 
	 */
	private static final long serialVersionUID = -7281852216184609917L;
		private Integer personalTecnicoAdministrativoID;
	    private String nombreCompleto;
	    private int fkIdtablaespFormacionProfesional;
	    private String formacionProfesionalNombre;
	    private String responsabilidad;
	    private int fkIdtablaespTiempoDedicado;
	    private String tiempoDedicadoNombre;
	    private int porcentageParticipacion;
	    private int fkIdtablaespEtapaPersonalTecnico;
	    private String etapaPersonalTecnicoNombre;
	    private int fkIdpersonalTecnicoAdmReemplazo;
	    private String personalTecnicoAdmReemplazoNombre;
	    private double salarioMensual;
	    private Integer fkIdtablaespTipoMoneda;
	    private String tipoMonedaNombre;
	    private ReporteAvance reporteAvance;
	    private DatoPlanOperativo datoPlanOperativo;
	    private Institucion institucion;
	    private DatoProyecto datoProyecto;	    	    
	    
		public PersonalTecnicoAdministrativoBean() {
		
		}
		
		public Integer getPersonalTecnicoAdministrativoID() {
			return personalTecnicoAdministrativoID;
		}
		public void setPersonalTecnicoAdministrativoID(
				Integer personalTecnicoAdministrativoID) {
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
		public void setFkIdtablaespFormacionProfesional(
				int fkIdtablaespFormacionProfesional) {
			this.fkIdtablaespFormacionProfesional = fkIdtablaespFormacionProfesional;
		}
		public String getFormacionProfesionalNombre() {
			return formacionProfesionalNombre;
		}
		public void setFormacionProfesionalNombre(String formacionProfesionalNombre) {
			this.formacionProfesionalNombre = formacionProfesionalNombre;
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
		public String getTiempoDedicadoNombre() {
			return tiempoDedicadoNombre;
		}
		public void setTiempoDedicadoNombre(String tiempoDedicadoNombre) {
			this.tiempoDedicadoNombre = tiempoDedicadoNombre;
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
		public void setFkIdtablaespEtapaPersonalTecnico(
				int fkIdtablaespEtapaPersonalTecnico) {
			this.fkIdtablaespEtapaPersonalTecnico = fkIdtablaespEtapaPersonalTecnico;
		}
		public String getEtapaPersonalTecnicoNombre() {
			return etapaPersonalTecnicoNombre;
		}
		public void setEtapaPersonalTecnicoNombre(String etapaPersonalTecnicoNombre) {
			this.etapaPersonalTecnicoNombre = etapaPersonalTecnicoNombre;
		}
		public int getFkIdpersonalTecnicoAdmReemplazo() {
			return fkIdpersonalTecnicoAdmReemplazo;
		}
		public void setFkIdpersonalTecnicoAdmReemplazo(
				int fkIdpersonalTecnicoAdmReemplazo) {
			this.fkIdpersonalTecnicoAdmReemplazo = fkIdpersonalTecnicoAdmReemplazo;
		}
		public String getPersonalTecnicoAdmReemplazoNombre() {
			return personalTecnicoAdmReemplazoNombre;
		}
		public void setPersonalTecnicoAdmReemplazoNombre(
				String personalTecnicoAdmReemplazoNombre) {
			this.personalTecnicoAdmReemplazoNombre = personalTecnicoAdmReemplazoNombre;
		}
		public double getSalarioMensual() {
			return salarioMensual;
		}
		public void setSalarioMensual(double salarioMensual) {
			this.salarioMensual = salarioMensual;
		}
		public Integer getFkIdtablaespTipoMoneda() {
			return fkIdtablaespTipoMoneda;
		}
		public void setFkIdtablaespTipoMoneda(Integer fkIdtablaespTipoMoneda) {
			this.fkIdtablaespTipoMoneda = fkIdtablaespTipoMoneda;
		}
		public String getTipoMonedaNombre() {
			return tipoMonedaNombre;
		}
		public void setTipoMonedaNombre(String tipoMonedaNombre) {
			this.tipoMonedaNombre = tipoMonedaNombre;
		}
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
		}
		public Institucion getInstitucion() {
			return institucion;
		}
		public void setInstitucion(Institucion institucion) {
			this.institucion = institucion;
		}

		public DatoProyecto getDatoProyecto() {
			return datoProyecto;
		}

		public void setDatoProyecto(DatoProyecto datoProyecto) {
			this.datoProyecto = datoProyecto;
		}

}
