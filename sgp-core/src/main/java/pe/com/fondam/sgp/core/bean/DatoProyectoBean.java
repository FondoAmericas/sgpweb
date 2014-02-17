package pe.com.fondam.sgp.core.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pe.com.fondam.sgp.core.domain.CuentaCorriente;
import pe.com.fondam.sgp.core.domain.InformeFinal;
import pe.com.fondam.sgp.core.domain.LiquidacionGasto;
import pe.com.fondam.sgp.core.domain.Observacion;
import pe.com.fondam.sgp.core.domain.PropuestaTransferencia;
import pe.com.fondam.sgp.core.domain.ReporteAvance;
import pe.com.fondam.sgp.core.domain.SolicitaRpRf;

public class DatoProyectoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -276896353932438920L;
	private Integer datoProyectoID;
	private Integer duracionProyecto;
	private Integer cantidadPeriodo;
	private String nombreProyecto;
	private String codigoProyecto;
	private Integer fkIddetallestadocabEstproy;
	private Integer numero_orden_dato_proyecto;
	private Integer idprograma;
	private Integer idSubAreaTematica;
	private String descSubAreaTematica;
	private String descAreaTematica;
	private List<LiquidacionGasto> lstLiqGasto;
	private List<ReporteAvance> lstRepAvance;
	private List<CuentaCorriente> listCuentaCorriente;
	private Integer cantCuentaCorriente;
	private int cantReportesPorEval;
	private int cantLiqPorEvaluar;
	private double promEvalInstitucion;
	private double promEvalPerfil;
	private double promEvalProyecto;
	private PropuestaTransferencia propuestaTransferencia;
	private Integer cantPropuestaTransferencia;
	private InformeFinal informeFinal;	
	private Integer cantInformeFinal;
	private List<Observacion> listObservacion =  new ArrayList<Observacion>();
	private Integer cantObservaciones;
	private List<SolicitaRpRf> listSolicitaRpRf;
	private int cuentaPlanOperativo;
	
	
	public DatoProyectoBean() {
		
	}
	
	public List<LiquidacionGasto> getLstLiqGasto() {
		return lstLiqGasto;
	}
	public void setLstLiqGasto(List<LiquidacionGasto> lstLiqGasto) {
		this.lstLiqGasto = lstLiqGasto;
	}
	public List<ReporteAvance> getLstRepAvance() {
		return lstRepAvance;
	}
	public void setLstRepAvance(List<ReporteAvance> lstRepAvance) {
		this.lstRepAvance = lstRepAvance;
	}
	public int getCantReportesPorEval() {
		return cantReportesPorEval;
	}
	public void setCantReportesPorEval(int cantReportesPorEval) {
		this.cantReportesPorEval = cantReportesPorEval;
	}
	public int getCantLiqPorEvaluar() {
		return cantLiqPorEvaluar;
	}
	public void setCantLiqPorEvaluar(int cantLiqPorEvaluar) {
		this.cantLiqPorEvaluar = cantLiqPorEvaluar;
	}
	public double getPromEvalInstitucion() {
		return promEvalInstitucion;
	}
	public void setPromEvalInstitucion(double promEvalInstitucion) {
		this.promEvalInstitucion = promEvalInstitucion;
	}
	public double getPromEvalPerfil() {
		return promEvalPerfil;
	}
	public void setPromEvalPerfil(double promEvalPerfil) {
		this.promEvalPerfil = promEvalPerfil;
	}
	public double getPromEvalProyecto() {
		return promEvalProyecto;
	}
	public void setPromEvalProyecto(double promEvalProyecto) {
		this.promEvalProyecto = promEvalProyecto;
	}
	public Integer getDatoProyectoID() {
		return datoProyectoID;
	}
	public void setDatoProyectoID(Integer datoProyectoID) {
		this.datoProyectoID = datoProyectoID;
	}
	public Integer getDuracionProyecto() {
		return duracionProyecto;
	}
	public void setDuracionProyecto(Integer duracionProyecto) {
		this.duracionProyecto = duracionProyecto;
	}
	public Integer getCantidadPeriodo() {
		return cantidadPeriodo;
	}
	public void setCantidadPeriodo(Integer cantidadPeriodo) {
		this.cantidadPeriodo = cantidadPeriodo;
	}
	public String getNombreProyecto() {
		return nombreProyecto;
	}
	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}
	public String getCodigoProyecto() {
		return codigoProyecto;
	}
	public void setCodigoProyecto(String codigoProyecto) {
		this.codigoProyecto = codigoProyecto;
	}
	public Integer getFkIddetallestadocabEstproy() {
		return fkIddetallestadocabEstproy;
	}
	public void setFkIddetallestadocabEstproy(Integer fkIddetallestadocabEstproy) {
		this.fkIddetallestadocabEstproy = fkIddetallestadocabEstproy;
	}
	public Integer getNumero_orden_dato_proyecto() {
		return numero_orden_dato_proyecto;
	}
	public void setNumero_orden_dato_proyecto(Integer numero_orden_dato_proyecto) {
		this.numero_orden_dato_proyecto = numero_orden_dato_proyecto;
	}
	public Integer getIdprograma() {
		return idprograma;
	}
	public void setIdprograma(Integer idprograma) {
		this.idprograma = idprograma;
	}
	public Integer getIdSubAreaTematica() {
		return idSubAreaTematica;
	}
	public void setIdSubAreaTematica(Integer idSubAreaTematica) {
		this.idSubAreaTematica = idSubAreaTematica;
	}
	public void setDescSubAreaTematica(String descSubAreaTematica) {
		this.descSubAreaTematica = descSubAreaTematica;
	}
	public String getDescSubAreaTematica() {
		return descSubAreaTematica;
	}
	public void setDescAreaTematica(String descAreaTematica) {
		this.descAreaTematica = descAreaTematica;
	}
	public String getDescAreaTematica() {
		return descAreaTematica;
	}
	public void setListCuentaCorriente(List<CuentaCorriente> listCuentaCorriente) {
		this.listCuentaCorriente = listCuentaCorriente;
	}
	public List<CuentaCorriente> getListCuentaCorriente() {
		return listCuentaCorriente;
	}

	public void setCantCuentaCorriente(Integer cantCuentaCorriente) {
		this.cantCuentaCorriente = cantCuentaCorriente;
	}

	public Integer getCantCuentaCorriente() {
		return cantCuentaCorriente;
	}

	public void setPropuestaTransferencia(
			PropuestaTransferencia propuestaTransferencia) {
		this.propuestaTransferencia = propuestaTransferencia;
	}

	public PropuestaTransferencia getPropuestaTransferencia() {
		return propuestaTransferencia;
	}

	public void setInformeFinal(InformeFinal informeFinal) {
		this.informeFinal = informeFinal;
	}

	public InformeFinal getInformeFinal() {
		return informeFinal;
	}

	public void setListObservacion(List<Observacion> listObservacion) {
		this.listObservacion = listObservacion;
	}

	public List<Observacion> getListObservacion() {
		return listObservacion;
	}

	public void setCantObservaciones(Integer cantObservaciones) {
		this.cantObservaciones = cantObservaciones;
	}

	public Integer getCantObservaciones() {
		return cantObservaciones;
	}

	public void setListSolicitaRpRf(List<SolicitaRpRf> listSolicitaRpRf) {
		this.listSolicitaRpRf = listSolicitaRpRf;
	}

	public List<SolicitaRpRf> getListSolicitaRpRf() {
		return listSolicitaRpRf;
	}

	public Integer getCantPropuestaTransferencia() {
		return cantPropuestaTransferencia;
	}

	public void setCantPropuestaTransferencia(Integer cantPropuestaTransferencia) {
		this.cantPropuestaTransferencia = cantPropuestaTransferencia;
	}

	public Integer getCantInformeFinal() {
		return cantInformeFinal;
	}

	public void setCantInformeFinal(Integer cantInformeFinal) {
		this.cantInformeFinal = cantInformeFinal;
	}

	public void setCuentaPlanOperativo(int cuentaPlanOperativo) {
		this.cuentaPlanOperativo = cuentaPlanOperativo;
	}

	public int getCuentaPlanOperativo() {
		return cuentaPlanOperativo;
	}
	
	
}
