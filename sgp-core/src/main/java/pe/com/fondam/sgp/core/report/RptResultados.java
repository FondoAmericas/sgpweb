package pe.com.fondam.sgp.core.report;

import java.util.List;

import pe.com.fondam.sgp.core.domain.BeneficiariosPorResultado;
import pe.com.fondam.sgp.core.domain.CronogramaMetaPorResultado;
import pe.com.fondam.sgp.core.domain.IndicadorResultado;

public class RptResultados {
	private Integer codigoResultado;
	private String definicionResultado;
	private String supuestoResultado;
	private Integer metaResultado;
	private String descripcionUnidadMedida;
	private Integer duracionMeses;
	private List<IndicadorResultado> listIndicadorResultado;
	private List<ActividadReporteBean> listActividadReporteBean;
	private List<BeneficiariosPorResultado> listBeneficiariosPorResultado;
	private List<CronogramaMetaPorResultado> listCronogramaMetaPorResultado;
	
	public List<IndicadorResultado> getListIndicadorResultado() {
		return listIndicadorResultado;
	}

	public void setListIndicadorResultado(
			List<IndicadorResultado> listIndicadorResultado) {
		this.listIndicadorResultado = listIndicadorResultado;
	}

	public Integer getCodigoResultado() {
		return codigoResultado;
	}

	public void setCodigoResultado(Integer codigoResultado) {
		this.codigoResultado = codigoResultado;
	}

	public String getDefinicionResultado() {
		return definicionResultado;
	}

	public void setDefinicionResultado(String definicionResultado) {
		this.definicionResultado = definicionResultado;
	}

	public String getSupuestoResultado() {
		return supuestoResultado;
	}

	public void setSupuestoResultado(String supuestoResultado) {
		this.supuestoResultado = supuestoResultado;
	}

	public void setListActividadReporteBean(List<ActividadReporteBean> listActividadReporteBean) {
		this.listActividadReporteBean = listActividadReporteBean;
	}

	public List<ActividadReporteBean> getListActividadReporteBean() {
		return listActividadReporteBean;
	}

	public List<BeneficiariosPorResultado> getListBeneficiariosPorResultado() {
		return listBeneficiariosPorResultado;
	}

	public void setListBeneficiariosPorResultado(
			List<BeneficiariosPorResultado> listBeneficiariosPorResultado) {
		this.listBeneficiariosPorResultado = listBeneficiariosPorResultado;
	}

	public Integer getMetaResultado() {
		return metaResultado;
	}

	public void setMetaResultado(Integer metaResultado) {
		this.metaResultado = metaResultado;
	}

	public String getDescripcionUnidadMedida() {
		return descripcionUnidadMedida;
	}

	public void setDescripcionUnidadMedida(String descripcionUnidadMedida) {
		this.descripcionUnidadMedida = descripcionUnidadMedida;
	}

	public Integer getDuracionMeses() {
		return duracionMeses;
	}

	public void setDuracionMeses(Integer duracionMeses) {
		this.duracionMeses = duracionMeses;
	}

	public List<CronogramaMetaPorResultado> getListCronogramaMetaPorResultado() {
		return listCronogramaMetaPorResultado;
	}

	public void setListCronogramaMetaPorResultado(
			List<CronogramaMetaPorResultado> listCronogramaMetaPorResultado) {
		this.listCronogramaMetaPorResultado = listCronogramaMetaPorResultado;
	}
}