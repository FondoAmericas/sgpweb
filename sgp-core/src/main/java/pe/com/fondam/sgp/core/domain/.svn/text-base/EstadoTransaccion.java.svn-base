package pe.com.fondam.sgp.core.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "estado_transaccion")
public class EstadoTransaccion implements Serializable {

	private static final long serialVersionUID = -3077854311996897000L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "estado_transaccion_ID")
	private Long estadotransaccionID;

	@Column(name = "fecha_transaccion")
	private Date fechaTransaccion;

	@Column(name = "fk_id_detalle_estado_cab_estado_eval")
	private Integer fkIdDetalleEstadoCabEstadoEval;

	@Column(name = "valor_tabla_id")
	private Long valorTablaId;

	@ManyToOne
	@JoinColumn(name = "tabla_clase_ID", referencedColumnName = "table_clase_ID")
	private TablaClase tablaClase;

	@ManyToOne
	@JoinColumn(name = "Dato_Usuario_ID", referencedColumnName = "Dato_Usuario_ID")
	private DatoUsuario datoUsuario;

	public Long getEstadotransaccionID() {
		return estadotransaccionID;
	}

	public void setEstadotransaccionID(Long estadotransaccionID) {
		this.estadotransaccionID = estadotransaccionID;
	}

	public Date getFechaTransaccion() {
		return fechaTransaccion;
	}

	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public Integer getFkIdDetalleEstadoCabEstadoEval() {
		return fkIdDetalleEstadoCabEstadoEval;
	}

	public void setFkIdDetalleEstadoCabEstadoEval(
			Integer fkIdDetalleEstadoCabEstadoEval) {
		this.fkIdDetalleEstadoCabEstadoEval = fkIdDetalleEstadoCabEstadoEval;
	}

	public Long getValorTablaId() {
		return valorTablaId;
	}

	public void setValorTablaId(Long valorTablaId) {
		this.valorTablaId = valorTablaId;
	}

	public TablaClase getTablaClase() {
		return tablaClase;
	}

	public void setTablaClase(TablaClase tablaClase) {
		this.tablaClase = tablaClase;
	}

	public DatoUsuario getDatoUsuario() {
		return datoUsuario;
	}

	public void setDatoUsuario(DatoUsuario datoUsuario) {
		this.datoUsuario = datoUsuario;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (estadotransaccionID != null ? estadotransaccionID.hashCode()
				: 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof EstadoTransaccion)) {
			return false;
		}
		EstadoTransaccion other = (EstadoTransaccion) object;
		if ((this.estadotransaccionID == null && other.estadotransaccionID != null)
				|| (this.estadotransaccionID != null && !this.estadotransaccionID
						.equals(other.estadotransaccionID))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.com.fondam.sgp.core.domain.EstadoTransaccion[estadotransaccionID="
				+ estadotransaccionID + "]";
	}

}
