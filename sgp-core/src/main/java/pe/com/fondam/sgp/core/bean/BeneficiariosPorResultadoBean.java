package pe.com.fondam.sgp.core.bean;

import java.util.ArrayList;
import java.util.List;

import pe.com.fondam.sgp.core.domain.PropuestaTransferenciaBeneficiario;



public class BeneficiariosPorResultadoBean {

	private Integer beneficiariosPorResultadoID;
	private Integer fkIdtablaespTipoBeneficiario;
	private String descripcionTipoBeneficiario;
	private String caracteristicasPoblacion;
	private Integer cantidadProgramado;
	private Integer fkidtablaespEstrato;
	private String descripcionEstrato;
	private String descripcion;
	private Integer perfilID;
	private Integer resultadoID;
	private String descripcionResultado;
	private Integer ubicacionProyectoID;
	private String departamento;
	private String provincia;
	private String distrito;
	private String detalleUbicacion;
    private boolean flagReferencia;
    private List<ReporteAvanceBeneficiarioBean> listReporteAvanceBeneficiarioBean = new ArrayList<ReporteAvanceBeneficiarioBean>();
    private PropuestaTransferenciaBeneficiario propuestaTransferenciaBeneficiario;
    private Integer datoProyectoId;
    
	public BeneficiariosPorResultadoBean(){
		
	}

	public boolean isFlagReferencia() {
		return flagReferencia;
	}

	public void setFlagReferencia(boolean flagReferencia) {
		this.flagReferencia = flagReferencia;
	}
	
	public String getDescripcionTipoBeneficiario() {
		return descripcionTipoBeneficiario;
	}
	public void setDescripcionTipoBeneficiario(String descripcionTipoBeneficiario) {
		this.descripcionTipoBeneficiario = descripcionTipoBeneficiario;
	}
	public String getDescripcionEstrato() {
		return descripcionEstrato;
	}
	public void setDescripcionEstrato(String descripcionEstrato) {
		this.descripcionEstrato = descripcionEstrato;
	}
	public Integer getBeneficiariosPorResultadoID() {
		return beneficiariosPorResultadoID;
	}
	public void setBeneficiariosPorResultadoID(Integer beneficiariosPorResultadoID) {
		this.beneficiariosPorResultadoID = beneficiariosPorResultadoID;
	}
	public Integer getFkIdtablaespTipoBeneficiario() {
		return fkIdtablaespTipoBeneficiario;
	}
	public void setFkIdtablaespTipoBeneficiario(Integer fkIdtablaespTipoBeneficiario) {
		this.fkIdtablaespTipoBeneficiario = fkIdtablaespTipoBeneficiario;
	}
	public String getCaracteristicasPoblacion() {
		return caracteristicasPoblacion;
	}
	public void setCaracteristicasPoblacion(String caracteristicasPoblacion) {
		this.caracteristicasPoblacion = caracteristicasPoblacion;
	}
	public Integer getCantidadProgramado() {
		return cantidadProgramado;
	}
	public void setCantidadProgramado(Integer cantidadProgramado) {
		this.cantidadProgramado = cantidadProgramado;
	}
	public Integer getFkidtablaespEstrato() {
		return fkidtablaespEstrato;
	}
	public void setFkidtablaespEstrato(Integer fkidtablaespEstrato) {
		this.fkidtablaespEstrato = fkidtablaespEstrato;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getPerfilID() {
		return perfilID;
	}
	public void setPerfilID(Integer perfilID) {
		this.perfilID = perfilID;
	}
	public Integer getResultadoID() {
		return resultadoID;
	}
	public void setResultadoID(Integer resultadoID) {
		this.resultadoID = resultadoID;
	}
	public Integer getUbicacionProyectoID() {
		return ubicacionProyectoID;
	}
	public void setUbicacionProyectoID(Integer ubicacionProyectoID) {
		this.ubicacionProyectoID = ubicacionProyectoID;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public PropuestaTransferenciaBeneficiario getPropuestaTransferenciaBeneficiario() {
		return propuestaTransferenciaBeneficiario;
	}

	public void setPropuestaTransferenciaBeneficiario(
			PropuestaTransferenciaBeneficiario propuestaTransferenciaBeneficiario) {
		this.propuestaTransferenciaBeneficiario = propuestaTransferenciaBeneficiario;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public String getDetalleUbicacion() {
		return detalleUbicacion;
	}

	public void setDetalleUbicacion(String detalleUbicacion) {
		this.detalleUbicacion = detalleUbicacion;
	}

	public void setDescripcionResultado(String descripcionResultado) {
		this.descripcionResultado = descripcionResultado;
	}

	public String getDescripcionResultado() {
		return descripcionResultado;
	}

	public void setListReporteAvanceBeneficiarioBean(
			List<ReporteAvanceBeneficiarioBean> listReporteAvanceBeneficiarioBean) {
		this.listReporteAvanceBeneficiarioBean = listReporteAvanceBeneficiarioBean;
	}

	public List<ReporteAvanceBeneficiarioBean> getListReporteAvanceBeneficiarioBean() {
		return listReporteAvanceBeneficiarioBean;
	}

	public void setDatoProyectoId(Integer datoProyectoId) {
		this.datoProyectoId = datoProyectoId;
	}

	public Integer getDatoProyectoId() {
		return datoProyectoId;
	}
}
