package pe.com.fondam.sgp.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "cronograma_costo_actividad")
public class CronogramaCostoActividad implements Serializable {

	private static final long serialVersionUID = -2932170305160375227L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Cronograma_Costo_Actividad_ID")
	private Integer cronogramaCostoActividadID;

	@ManyToOne
	@JoinColumn(name = "Costo_Actividad_ID", referencedColumnName = "Costo_Actividad_ID")
	private CostoActividad costoActividad;

	@Column(name = "periodo")
	private String periodo;

	@Column(name = "estado_liquidacion")
	private String estadoLiquidacion;

	@Column(name = "cantidad")
	private Integer cantidad;
	
	@Transient
	private String descripcionUnidadMedida;
	
	@Transient
	private Double precioUnitario;
	
	@Transient
	private Double montoPorGastar;
	
	@Transient
	private String descripcionTipoMoneda;

	@Transient
	private PartidaGenerica partidaGenerica;
	
	@Transient
	private PartidaEspecifica partidaEspecifica;
	
	@ManyToOne
	@JoinColumn(name = "Fuente_Financiadora_ID", referencedColumnName = "Fuente_Financiadora_ID")
	private FuenteFinanciadora fuenteFinanciadora;

	/*@Transient
	private String nombreFuenteFinanciamiento;*/
	
	@Column(name = "Detalle_Pago_Liquidacion_ID")
	private Integer detallePagoLiquidacionID;
	
	@Column(name = "ejecutado")
	private Integer ejecutado;

	@Column(name = "estado_eliminado")
	private Integer estadoEliminado;
	
	@Transient
	private double montoLiquidadoHastaElMomento; //suma de actividades detalle pago asociadas al cronograma
	
	
	public Integer getEjecutado() {
		return ejecutado;
	}

	public void setEjecutado(Integer ejecutado) {
		this.ejecutado = ejecutado;
	}

	public Integer getCronogramaCostoActividadID() {
		return cronogramaCostoActividadID;
	}

	public void setCronogramaCostoActividadID(Integer cronogramaCostoActividadID) {
		this.cronogramaCostoActividadID = cronogramaCostoActividadID;
	}

	public CostoActividad getCostoActividad() {
		return costoActividad;
	}

	public void setCostoActividad(CostoActividad costoActividad) {
		this.costoActividad = costoActividad;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getEstadoLiquidacion() {
		return estadoLiquidacion;
	}

	public void setEstadoLiquidacion(String estadoLiquidacion) {
		this.estadoLiquidacion = estadoLiquidacion;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public FuenteFinanciadora getFuenteFinanciadora() {
		return fuenteFinanciadora;
	}

	public void setFuenteFinanciadora(FuenteFinanciadora fuenteFinanciadora) {
		this.fuenteFinanciadora = fuenteFinanciadora;
	}

	public Integer getDetallePagoLiquidacionID() {
		return detallePagoLiquidacionID;
	}

	public void setDetallePagoLiquidacionID(Integer detallePagoLiquidacionID) {
		this.detallePagoLiquidacionID = detallePagoLiquidacionID;
	}

	public String getDescripcionUnidadMedida() {
		return descripcionUnidadMedida;
	}

	public void setDescripcionUnidadMedida(String descripcionUnidadMedida) {
		this.descripcionUnidadMedida = descripcionUnidadMedida;
	}

	public Double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public Double getMontoPorGastar() {
		return montoPorGastar;
	}

	public void setMontoPorGastar(Double montoPorGastar) {
		this.montoPorGastar = montoPorGastar;
	}

	public void setDescripcionTipoMoneda(String descripcionTipoMoneda) {
		this.descripcionTipoMoneda = descripcionTipoMoneda;
	}

	public String getDescripcionTipoMoneda() {
		return descripcionTipoMoneda;
	}

	public PartidaGenerica getPartidaGenerica() {
		return partidaGenerica;
	}

	public void setPartidaGenerica(PartidaGenerica partidaGenerica) {
		this.partidaGenerica = partidaGenerica;
	}

	public PartidaEspecifica getPartidaEspecifica() {
		return partidaEspecifica;
	}

	public void setPartidaEspecifica(PartidaEspecifica partidaEspecifica) {
		this.partidaEspecifica = partidaEspecifica;
	}

	public void setEstadoEliminado(Integer estadoEliminado) {
		this.estadoEliminado = estadoEliminado;
	}

	public Integer getEstadoEliminado() {
		return estadoEliminado;
	}

	public double getMontoLiquidadoHastaElMomento() {
		return montoLiquidadoHastaElMomento;
	}

	public void setMontoLiquidadoHastaElMomento(double montoLiquidadoHastaElMomento) {
		this.montoLiquidadoHastaElMomento = montoLiquidadoHastaElMomento;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (cronogramaCostoActividadID != null ? cronogramaCostoActividadID
				.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof CronogramaCostoActividad)) {
			return false;
		}
		CronogramaCostoActividad other = (CronogramaCostoActividad) object;
		if ((this.cronogramaCostoActividadID == null && other.cronogramaCostoActividadID != null)
				|| (this.cronogramaCostoActividadID != null && !this.cronogramaCostoActividadID
						.equals(other.cronogramaCostoActividadID))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.com.fondam.sgp.core.domain.CronogramaCostoActividad[cronogramaCostoActividadID="
				+ cronogramaCostoActividadID + "]";
	}

	/*public void setObservacionAtendida(String observacionAtendida) {
		this.observacionAtendida = observacionAtendida;
	}

	public String getObservacionAtendida() {
		return observacionAtendida;
	}*/

}
