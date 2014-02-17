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
@Table(name = "detalle_desembolso")
public class DetalleDesembolso implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8115980263248274933L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Detalle_desembolso_ID")
	private Integer detalleDesembolsoID;
	
	@Column(name="monto_solicitado")
	private double montoSolicitado;
	
	@Column(name="fk_idtablaesp_tipo_moneda_ms")
	private Integer fkIdtablaespTipoMonedaMs;
	
	@Transient
	private String descripcionTipoMonedaMs;
	
	@ManyToOne
	@JoinColumn(name = "Desembolso_ID", referencedColumnName = "Desembolso_ID")
	private Desembolso desembolso;     
	
	@ManyToOne
	@JoinColumn(name = "Cronograma_Costo_Actividad_ID", referencedColumnName = "Cronograma_Costo_Actividad_ID")
	private CronogramaCostoActividad cronogramaCostoActividadID;
	
	@Transient
	private CostoActividad costoActividad;
	
	@Transient
	private Actividad actividad;
	
	@Transient
	private Resultado resultado;

	public Integer getDetalleDesembolsoID() {
		return detalleDesembolsoID;
	}

	public void setDetalleDesembolsoID(Integer detalleDesembolsoID) {
		this.detalleDesembolsoID = detalleDesembolsoID;
	}

	public double getMontoSolicitado() {
		return montoSolicitado;
	}

	public void setMontoSolicitado(double montoSolicitado) {
		this.montoSolicitado = montoSolicitado;
	}

	public Integer getFkIdtablaespTipoMonedaMs() {
		return fkIdtablaespTipoMonedaMs;
	}

	public void setFkIdtablaespTipoMonedaMs(Integer fkIdtablaespTipoMonedaMs) {
		this.fkIdtablaespTipoMonedaMs = fkIdtablaespTipoMonedaMs;
	}

	public Desembolso getDesembolso() {
		return desembolso;
	}

	public void setDesembolso(Desembolso desembolso) {
		this.desembolso = desembolso;
	}

	public CronogramaCostoActividad getCronogramaCostoActividadID() {
		return cronogramaCostoActividadID;
	}

	public void setCronogramaCostoActividadID(
			CronogramaCostoActividad cronogramaCostoActividadID) {
		this.cronogramaCostoActividadID = cronogramaCostoActividadID;
	}
	
	public CostoActividad getCostoActividad() {
		return costoActividad;
	}

	public void setCostoActividad(CostoActividad costoActividad) {
		this.costoActividad = costoActividad;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public Resultado getResultado() {
		return resultado;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}

	public void setDescripcionTipoMonedaMs(String descripcionTipoMonedaMs) {
		this.descripcionTipoMonedaMs = descripcionTipoMonedaMs;
	}

	public String getDescripcionTipoMonedaMs() {
		return descripcionTipoMonedaMs;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (detalleDesembolsoID != null ? detalleDesembolsoID
				.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof DetalleDesembolso)) {
			return false;
		}
		DetalleDesembolso other = (DetalleDesembolso) object;
		if ((this.detalleDesembolsoID == null && other.detalleDesembolsoID != null)
				|| (this.detalleDesembolsoID != null && !this.detalleDesembolsoID
						.equals(other.detalleDesembolsoID))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.com.fondam.sgp.core.domain.DetalleDesembolso[detalleDesembolsoID="
				+ detalleDesembolsoID + "]";
	}
	
}
