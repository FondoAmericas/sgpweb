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
@Table(name = "actividad_detalle_pago")
public class ActividadDetallePago implements Serializable {

	private static final long serialVersionUID = -3777212782117665303L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Actividad_Detalle_Pago_ID")
	private Integer actividadDetallePagoID;

	@Column(name = "fk_idactividades")
	private Integer fkIdactividades;

	@Column(name = "porcentaje_monto_total")
	private Double porcentajeMontoTotal;

	@Column(name = "monto_gastado")
	private Double montoGastado;

	@Column(name = "monto_total_de_costo_activ")
	private Double montoTotalDeCostoActividad;
	
    @Column(name = "fk_idtablaesp_tipo_moneda")
    private Integer fkIdtablaespTipoMoneda;

    @Transient
    private String descripcionTipoMoneda;
    
	@ManyToOne
	@JoinColumn(name = "Cronograma_Costo_Actividad_ID", referencedColumnName = "Cronograma_Costo_Actividad_ID")
	private CronogramaCostoActividad cronogramaCostoActividad;

	@ManyToOne
	@JoinColumn(name = "Desembolso_ID", referencedColumnName = "Desembolso_ID")
	private Desembolso desembolso;

	@ManyToOne
	@JoinColumn(name = "Detalle_Pago_Liquidacion_ID", referencedColumnName = "Detalle_Pago_Liquidacion_ID")
	private DetallePagoLiquidacion detallePagoLiquidacion;

	@Column(name = "Dato_Proyecto_ID")
    private Integer datoProyectoID;
	
	@Transient
	private String actividadDesc;  

	@Transient
	private String tipoMonedaDesc;
	
	@Column(name = "estado_eliminado")
	private Integer estadoEliminado;
		
	public String getTipoMonedaDesc() {
		return tipoMonedaDesc;
	}

	public void setTipoMonedaDesc(String tipoMonedaDesc) {
		this.tipoMonedaDesc = tipoMonedaDesc;
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

	public Double getMontoTotalDeCostoActividad() {
		return montoTotalDeCostoActividad;
	}

	public void setMontoTotalDeCostoActividad(Double montoTotalDeCostoActividad) {
		this.montoTotalDeCostoActividad = montoTotalDeCostoActividad;
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
	
	public String getActividadDesc() {
		return actividadDesc;
	}

	public void setActividadDesc(String actividadDesc) {
		this.actividadDesc = actividadDesc;
	}

	public void setDescripcionTipoMoneda(String descripcionTipoMoneda) {
		this.descripcionTipoMoneda = descripcionTipoMoneda;
	}

	public String getDescripcionTipoMoneda() {
		return descripcionTipoMoneda;
	}

	public Integer getEstadoEliminado() {
		return estadoEliminado;
	}

	public void setEstadoEliminado(Integer estadoEliminado) {
		this.estadoEliminado = estadoEliminado;
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
		if (!(object instanceof ActividadDetallePago)) {
			return false;
		}
		ActividadDetallePago other = (ActividadDetallePago) object;
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
