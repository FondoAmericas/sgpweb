package pe.com.fondam.sgp.core.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "solicita_rp_rf")
public class SolicitaRpRf implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7121151730626973341L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Solicita_Rp_Rf_ID")
	private Integer solicitaRpRfID;

	@Column(name = "fk_id_detalle_estado_cab_rp_rf")
	private Integer fkIdDetalleEstadoCabRpRf;

	@Transient
	private String descripcionEstadoRpRf;
	
	@Transient
	private String prefijoEstadoRpRf;
	
	@Column(name = "version_po")
	private String versionPo;

	@Lob
	@Column(name = "observacion_de_solicitud")
	private String observacionDeSolicitud;

	@ManyToOne
	@JoinColumn(name = "Dato_Proyecto_ID", referencedColumnName = "Dato_Proyecto_ID")
	private DatoProyecto datoProyecto;
	
    @Column(name = "fecha_solicitud")
    @Temporal(TemporalType.DATE)
    private Date fechaSolicitud;
    
    @Transient
    private String fechaSolicitudString;
    
    @Column(name = "fecha_aprobacion")
    @Temporal(TemporalType.DATE)
    private Date fechaAprobacion;

    @Transient
    private String fechaAprobacionString;
    
	public SolicitaRpRf() {
		
	}
	
	public Integer getSolicitaRpRfID() {
		return solicitaRpRfID;
	}

	public void setSolicitaRpRfID(Integer solicitaRpRfID) {
		this.solicitaRpRfID = solicitaRpRfID;
	}

	public Integer getFkIdDetalleEstadoCabRpRf() {
		return fkIdDetalleEstadoCabRpRf;
	}

	public void setFkIdDetalleEstadoCabRpRf(Integer fkIdDetalleEstadoCabRpRf) {
		this.fkIdDetalleEstadoCabRpRf = fkIdDetalleEstadoCabRpRf;
	}

	public String getDescripcionEstadoRpRf() {
		return descripcionEstadoRpRf;
	}

	public void setDescripcionEstadoRpRf(String descripcionEstadoRpRf) {
		this.descripcionEstadoRpRf = descripcionEstadoRpRf;
	}

	public String getVersionPo() {
		return versionPo;
	}

	public void setVersionPo(String versionPo) {
		this.versionPo = versionPo;
	}

	public String getObservacionDeSolicitud() {
		return observacionDeSolicitud;
	}

	public void setObservacionDeSolicitud(String observacionDeSolicitud) {
		this.observacionDeSolicitud = observacionDeSolicitud;
	}

	public DatoProyecto getDatoProyecto() {
		return datoProyecto;
	}

	public void setDatoProyecto(DatoProyecto datoProyecto) {
		this.datoProyecto = datoProyecto;
	}

	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public Date getFechaAprobacion() {
		return fechaAprobacion;
	}

	public void setFechaAprobacion(Date fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
	}

	public String getFechaSolicitudString() {
		return fechaSolicitudString;
	}

	public void setFechaSolicitudString(String fechaSolicitudString) {
		this.fechaSolicitudString = fechaSolicitudString;
	}

	public String getFechaAprobacionString() {
		return fechaAprobacionString;
	}

	public void setFechaAprobacionString(String fechaAprobacionString) {
		this.fechaAprobacionString = fechaAprobacionString;
	}

	public void setPrefijoEstadoRpRf(String prefijoEstadoRpRf) {
		this.prefijoEstadoRpRf = prefijoEstadoRpRf;
	}

	public String getPrefijoEstadoRpRf() {
		return prefijoEstadoRpRf;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (solicitaRpRfID != null ? solicitaRpRfID.hashCode()
				: 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof SolicitaRpRf)) {
			return false;
		}
		SolicitaRpRf other = (SolicitaRpRf) object;
		if ((this.solicitaRpRfID == null && other.solicitaRpRfID != null)
				|| (this.solicitaRpRfID != null && !this.solicitaRpRfID
						.equals(other.solicitaRpRfID))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.com.fondam.sgp.core.domain.SolicitaRpRf[solicitaRpRfID="
				+ solicitaRpRfID + "]";
	}

}
