package pe.com.fondam.sgp.core.report;

import java.util.List;

public class FuenteFinanciadoraReporte {
	
	private Integer fuenteFinanciadoraId;
	private String fuenteFinanciadoraDescripcion;
	private Integer cantidadPeriodos;
	private List<ActividadReporteBean> listActividadReporteBean;
	private double montoTotalPorActividad;
	private double montoTotalFinanciadoPorFuente;
	
	public FuenteFinanciadoraReporte(){
		
	}

	public Integer getFuenteFinanciadoraId() {
		return fuenteFinanciadoraId;
	}

	public void setFuenteFinanciadoraId(Integer fuenteFinanciadoraId) {
		this.fuenteFinanciadoraId = fuenteFinanciadoraId;
	}

	public String getFuenteFinanciadoraDescripcion() {
		return fuenteFinanciadoraDescripcion;
	}

	public void setFuenteFinanciadoraDescripcion(
			String fuenteFinanciadoraDescripcion) {
		this.fuenteFinanciadoraDescripcion = fuenteFinanciadoraDescripcion;
	}

	public List<ActividadReporteBean> getListActividadReporteBean() {
		return listActividadReporteBean;
	}

	public void setListActividadReporteBean(
			List<ActividadReporteBean> listActividadReporteBean) {
		this.listActividadReporteBean = listActividadReporteBean;
	}

	public void setCantidadPeriodos(Integer cantidadPeriodos) {
		this.cantidadPeriodos = cantidadPeriodos;
	}

	public Integer getCantidadPeriodos() {
		return cantidadPeriodos;
	}

	public double getMontoTotalPorActividad() {
		return montoTotalPorActividad;
	}

	public void setMontoTotalPorActividad(double montoTotalPorActividad) {
		this.montoTotalPorActividad = montoTotalPorActividad;
	}

	public double getMontoTotalFinanciadoPorFuente() {
		return montoTotalFinanciadoPorFuente;
	}

	public void setMontoTotalFinanciadoPorFuente(
			double montoTotalFinanciadoPorFuente) {
		this.montoTotalFinanciadoPorFuente = montoTotalFinanciadoPorFuente;
	}

	
}
