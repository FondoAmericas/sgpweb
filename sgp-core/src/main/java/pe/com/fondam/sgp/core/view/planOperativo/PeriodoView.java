package pe.com.fondam.sgp.core.view.planOperativo;

public class PeriodoView {
	private Integer numeroPerido;
	private Integer cantidadPeriodo;
	private Double montoPerido;
	private Integer periodoReportado;

	public Integer getNumeroPerido() {
		return numeroPerido;
	}

	public void setNumeroPerido(Integer numeroPerido) {
		this.numeroPerido = numeroPerido;
	}

	public Integer getCantidadPeriodo() {
		return cantidadPeriodo;
	}

	public void setCantidadPeriodo(Integer cantidadPeriodo) {
		this.cantidadPeriodo = cantidadPeriodo;
	}

	public Double getMontoPerido() {
		return montoPerido;
	}

	public void setMontoPerido(Double montoPerido) {
		this.montoPerido = montoPerido;
	}

	public void setPeriodoReportado(Integer periodoReportado) {
		this.periodoReportado = periodoReportado;
	}

	public Integer getPeriodoReportado() {
		return periodoReportado;
	}

}
