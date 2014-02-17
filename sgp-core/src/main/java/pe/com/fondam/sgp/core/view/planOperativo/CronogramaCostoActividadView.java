package pe.com.fondam.sgp.core.view.planOperativo;

import java.util.List;

public class CronogramaCostoActividadView {
	private Integer cronogramaCostoActividadID;
	private Integer costoActividadID;
	private Integer partidaEspecificaID;
	private String descripcionPartidaEspecifica;
	private Integer unidadMedidaId;
	private String unidadMedidaNombre;
	private Integer cantidadTotal;
	private Integer tipoMonedaPrecioUnitarioId;
	private String tipoMonedaPrecioUnitarioNombre;
	private Double precioUnitario;
	private Double montoTotal;
	private Integer fuenteFinanciadoraID;
	private Integer institucionID;
	private String nombreInstitucion;
	private List<PeriodoView> listPeriodo;

	public Integer getCronogramaCostoActividadID() {
		return cronogramaCostoActividadID;
	}

	public void setCronogramaCostoActividadID(Integer cronogramaCostoActividadID) {
		this.cronogramaCostoActividadID = cronogramaCostoActividadID;
	}

	public Integer getCostoActividadID() {
		return costoActividadID;
	}

	public void setCostoActividadID(Integer costoActividadID) {
		this.costoActividadID = costoActividadID;
	}

	public Integer getPartidaEspecificaID() {
		return partidaEspecificaID;
	}

	public void setPartidaEspecificaID(Integer partidaEspecificaID) {
		this.partidaEspecificaID = partidaEspecificaID;
	}

	public String getDescripcionPartidaEspecifica() {
		return descripcionPartidaEspecifica;
	}

	public void setDescripcionPartidaEspecifica(
			String descripcionPartidaEspecifica) {
		this.descripcionPartidaEspecifica = descripcionPartidaEspecifica;
	}

	public Integer getUnidadMedidaId() {
		return unidadMedidaId;
	}

	public void setUnidadMedidaId(Integer unidadMedidaId) {
		this.unidadMedidaId = unidadMedidaId;
	}

	public String getUnidadMedidaNombre() {
		return unidadMedidaNombre;
	}

	public void setUnidadMedidaNombre(String unidadMedidaNombre) {
		this.unidadMedidaNombre = unidadMedidaNombre;
	}

	public Integer getCantidadTotal() {
		return cantidadTotal;
	}

	public void setCantidadTotal(Integer cantidadTotal) {
		this.cantidadTotal = cantidadTotal;
	}

	public Integer getTipoMonedaPrecioUnitarioId() {
		return tipoMonedaPrecioUnitarioId;
	}

	public void setTipoMonedaPrecioUnitarioId(Integer tipoMonedaPrecioUnitarioId) {
		this.tipoMonedaPrecioUnitarioId = tipoMonedaPrecioUnitarioId;
	}

	public String getTipoMonedaPrecioUnitarioNombre() {
		return tipoMonedaPrecioUnitarioNombre;
	}

	public void setTipoMonedaPrecioUnitarioNombre(
			String tipoMonedaPrecioUnitarioNombre) {
		this.tipoMonedaPrecioUnitarioNombre = tipoMonedaPrecioUnitarioNombre;
	}

	public Double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public Double getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(Double montoTotal) {
		this.montoTotal = montoTotal;
	}

	public Integer getFuenteFinanciadoraID() {
		return fuenteFinanciadoraID;
	}

	public void setFuenteFinanciadoraID(Integer fuenteFinanciadoraID) {
		this.fuenteFinanciadoraID = fuenteFinanciadoraID;
	}

	public Integer getInstitucionID() {
		return institucionID;
	}

	public void setInstitucionID(Integer institucionID) {
		this.institucionID = institucionID;
	}

	public String getNombreInstitucion() {
		return nombreInstitucion;
	}

	public void setNombreInstitucion(String nombreInstitucion) {
		this.nombreInstitucion = nombreInstitucion;
	}

	public List<PeriodoView> getListPeriodo() {
		return listPeriodo;
	}

	public void setListPeriodo(List<PeriodoView> listPeriodo) {
		this.listPeriodo = listPeriodo;
	}

}
