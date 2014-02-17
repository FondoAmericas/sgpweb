package pe.com.fondam.sgp.core.report;

import java.math.BigDecimal;

public class RptEstructuraInversionFinanciamientoDet {
	private String actividad;
	private BigDecimal montoFuente1;
	private BigDecimal montoFuente2;
	private BigDecimal montoFuente3;
	private BigDecimal totalMontoFuentes;

	public String getActividad() {
		return actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	public BigDecimal getMontoFuente1() {
		return montoFuente1;
	}

	public void setMontoFuente1(BigDecimal montoFuente1) {
		this.montoFuente1 = montoFuente1;
	}

	public BigDecimal getMontoFuente2() {
		return montoFuente2;
	}

	public void setMontoFuente2(BigDecimal montoFuente2) {
		this.montoFuente2 = montoFuente2;
	}

	public BigDecimal getMontoFuente3() {
		return montoFuente3;
	}

	public void setMontoFuente3(BigDecimal montoFuente3) {
		this.montoFuente3 = montoFuente3;
	}

	public BigDecimal getTotalMontoFuentes() {
		return totalMontoFuentes;
	}

	public void setTotalMontoFuentes(BigDecimal totalMontoFuentes) {
		this.totalMontoFuentes = totalMontoFuentes;
	}

}
