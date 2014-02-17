package pe.com.fondam.sgp.core.report;

import java.util.List;

public class CostoActividadReporteBean {
	
	private String partidaGenericaDescripcion;
	private String partidaEspecificaDescripcion;
	private String unidadMedidaDescripcion;
	private String tipoMonedaDescripcion;
	private String simboloTipoMoneda;
	private Integer cantidad;
	private double precioUnitario;
	private List<CronogramaCostoActividadReporteBean> listCronogramaCostoActividadReporteBean;

	
	public CostoActividadReporteBean(){
		
	}


	public String getPartidaGenericaDescripcion() {
		return partidaGenericaDescripcion;
	}


	public void setPartidaGenericaDescripcion(String partidaGenericaDescripcion) {
		this.partidaGenericaDescripcion = partidaGenericaDescripcion;
	}


	public String getPartidaEspecificaDescripcion() {
		return partidaEspecificaDescripcion;
	}


	public void setPartidaEspecificaDescripcion(String partidaEspecificaDescripcion) {
		this.partidaEspecificaDescripcion = partidaEspecificaDescripcion;
	}


	public String getUnidadMedidaDescripcion() {
		return unidadMedidaDescripcion;
	}


	public void setUnidadMedidaDescripcion(String unidadMedidaDescripcion) {
		this.unidadMedidaDescripcion = unidadMedidaDescripcion;
	}


	public String getTipoMonedaDescripcion() {
		return tipoMonedaDescripcion;
	}


	public void setTipoMonedaDescripcion(String tipoMonedaDescripcion) {
		this.tipoMonedaDescripcion = tipoMonedaDescripcion;
	}


	public String getSimboloTipoMoneda() {
		return simboloTipoMoneda;
	}


	public void setSimboloTipoMoneda(String simboloTipoMoneda) {
		this.simboloTipoMoneda = simboloTipoMoneda;
	}


	public Integer getCantidad() {
		return cantidad;
	}


	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}


	public double getPrecioUnitario() {
		return precioUnitario;
	}


	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}


	public List<CronogramaCostoActividadReporteBean> getListCronogramaCostoActividadReporteBean() {
		return listCronogramaCostoActividadReporteBean;
	}


	public void setListCronogramaCostoActividadReporteBean(
			List<CronogramaCostoActividadReporteBean> listCronogramaCostoActividadReporteBean) {
		this.listCronogramaCostoActividadReporteBean = listCronogramaCostoActividadReporteBean;
	}
	
	
}
