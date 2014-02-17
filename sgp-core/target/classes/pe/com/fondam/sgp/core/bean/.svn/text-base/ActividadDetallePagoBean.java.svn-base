package pe.com.fondam.sgp.core.bean;

import java.io.Serializable;

import pe.com.fondam.sgp.core.domain.CronogramaCostoActividad;
import pe.com.fondam.sgp.core.domain.Desembolso;
import pe.com.fondam.sgp.core.domain.DetallePagoLiquidacion;

public class ActividadDetallePagoBean implements Serializable {


	private static final long serialVersionUID = 8965233051670700005L;

	private Integer actividadDetallePagoID;

	private Integer fkIdactividades;

	private Double porcentajeMontoTotal;

	private Double montoGastado;

	private Double montoPorGastarDeActiv;
	
    private Integer fkIdtablaespTipoMoneda;
  
    private String descripcionTipoMoneda;
    
	private CronogramaCostoActividad cronogramaCostoActividad;

	private Desembolso desembolso;

	private DetallePagoLiquidacion detallePagoLiquidacion;

    private Integer datoProyectoID;
	
	public String getDescripcionTipoMoneda() {
		return descripcionTipoMoneda;
	}

	public void setDescripcionTipoMoneda(String descripcionTipoMoneda) {
		this.descripcionTipoMoneda = descripcionTipoMoneda;
	}

	public Integer getActividadDetallePagoID() {
		return actividadDetallePagoID;
	}

	public void setActividadDetallePagoID(Integer actividadDetallePagoID) {
		this.actividadDetallePagoID = actividadDetallePagoID;
	}

	public Integer getFkIdactividades() {
		return fkIdactividades;
	}

	public void setFkIdactividades(Integer fkIdactividades) {
		this.fkIdactividades = fkIdactividades;
	}

	public Double getPorcentajeMontoTotal() {
		return porcentajeMontoTotal;
	}

	public void setPorcentajeMontoTotal(Double porcentajeMontoTotal) {
		this.porcentajeMontoTotal = porcentajeMontoTotal;
	}

	public Double getMontoGastado() {
		return montoGastado;
	}

	public void setMontoGastado(Double montoGastado) {
		this.montoGastado = montoGastado;
	}

	public Double getMontoPorGastarDeActiv() {
		return montoPorGastarDeActiv;
	}

	public void setMontoPorGastarDeActiv(Double montoPorGastarDeActiv) {
		this.montoPorGastarDeActiv = montoPorGastarDeActiv;
	}

	public CronogramaCostoActividad getCronogramaCostoActividad() {
		return cronogramaCostoActividad;
	}

	public void setCronogramaCostoActividad(
			CronogramaCostoActividad cronogramaCostoActividad) {
		this.cronogramaCostoActividad = cronogramaCostoActividad;
	}

	public Desembolso getDesembolso() {
		return desembolso;
	}

	public void setDesembolso(Desembolso desembolso) {
		this.desembolso = desembolso;
	}

	public DetallePagoLiquidacion getDetallePagoLiquidacion() {
		return detallePagoLiquidacion;
	}

	public void setDetallePagoLiquidacion(
			DetallePagoLiquidacion detallePagoLiquidacion) {
		this.detallePagoLiquidacion = detallePagoLiquidacion;
	}

	public Integer getFkIdtablaespTipoMoneda() {
		return fkIdtablaespTipoMoneda;
	}

	public void setFkIdtablaespTipoMoneda(Integer fkIdtablaespTipoMoneda) {
		this.fkIdtablaespTipoMoneda = fkIdtablaespTipoMoneda;
	}

	public void setDatoProyectoID(Integer datoProyectoID) {
		this.datoProyectoID = datoProyectoID;
	}

	public Integer getDatoProyectoID() {
		return datoProyectoID;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (actividadDetallePagoID != null ? actividadDetallePagoID
				.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof ActividadDetallePagoBean)) {
			return false;
		}
		ActividadDetallePagoBean other = (ActividadDetallePagoBean) object;
		if ((this.actividadDetallePagoID == null && other.actividadDetallePagoID != null)
				|| (this.actividadDetallePagoID != null && !this.actividadDetallePagoID
						.equals(other.actividadDetallePagoID))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.com.fondam.sgp.core.domain.ActividadDetallePago[actividadDetallePagoID="
				+ actividadDetallePagoID + "]";
	}

}
