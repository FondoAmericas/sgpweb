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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "tipo_cambio")
public class TipoCambio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6470792919961395587L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Tipo_Cambio_ID")
	private Integer tipoCambioID;

	@Column(name = "tipo_cambio")
	private Double tipoCambio;

	@Column(name = "fecha_tipo_cambio")
	@Temporal(TemporalType.DATE)
	private Date fechaTipoCambio;

	@Column(name = "fk_idtablaesp_tipo_moneda_convert_de")
	private int fkIdtablaespTipoMonedaConvertDE;
	
	@Transient
	private String tipoMonedaDENombre;
	
	@Transient
	private String tipoMonedaANombre;
	
	@Transient
	private String fechaTipoCambioDesc;
	
	@Column(name = "ingreso_Usuario")
	private Integer ingresoUsuario;

	public String getFechaTipoCambioDesc() {
		return fechaTipoCambioDesc;
	}

	public void setFechaTipoCambioDesc(String fechaTipoCambioDesc) {
		this.fechaTipoCambioDesc = fechaTipoCambioDesc;
	}

	@Column(name = "fk_idtablaesp_tipo_moneda_convert_a")
	private int fkIdtablaespTipoMonedaConvertA;

	@ManyToOne
	@JoinColumn(name = "Desembolso_ID", referencedColumnName = "Desembolso_ID")
	private Desembolso desembolso;

	@ManyToOne
	@JoinColumn(name = "Dato_Plan_Operativo_ID", referencedColumnName = "Dato_Plan_Operativo_ID")
	private DatoPlanOperativo datoPlanOperativo;

	public Integer getTipoCambioID() {
		return tipoCambioID;
	}

	public void setTipoCambioID(Integer tipoCambioID) {
		this.tipoCambioID = tipoCambioID;
	}

	public Double getTipoCambio() {
		return tipoCambio;
	}

	public void setTipoCambio(Double tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	public Date getFechaTipoCambio() {
		return fechaTipoCambio;
	}

	public void setFechaTipoCambio(Date fechaTipoCambio) {
		this.fechaTipoCambio = fechaTipoCambio;
	}

	public int getFkIdtablaespTipoMonedaConvertDE() {
		return fkIdtablaespTipoMonedaConvertDE;
	}

	public void setFkIdtablaespTipoMonedaConvertDE(
			int fkIdtablaespTipoMonedaConvertDE) {
		this.fkIdtablaespTipoMonedaConvertDE = fkIdtablaespTipoMonedaConvertDE;
	}

	public int getFkIdtablaespTipoMonedaConvertA() {
		return fkIdtablaespTipoMonedaConvertA;
	}

	public void setFkIdtablaespTipoMonedaConvertA(
			int fkIdtablaespTipoMonedaConvertA) {
		this.fkIdtablaespTipoMonedaConvertA = fkIdtablaespTipoMonedaConvertA;
	}

	public Desembolso getDesembolso() {
		return desembolso;
	}

	public void setDesembolso(Desembolso desembolso) {
		this.desembolso = desembolso;
	}

	public DatoPlanOperativo getDatoPlanOperativo() {
		return datoPlanOperativo;
	}

	public void setDatoPlanOperativo(DatoPlanOperativo datoPlanOperativo) {
		this.datoPlanOperativo = datoPlanOperativo;
	}
	
	public String getTipoMonedaANombre() {
		return tipoMonedaANombre;
	}
	
	public void setTipoMonedaANombre(String tipoMonedaANombre) {
		this.tipoMonedaANombre = tipoMonedaANombre;
	}
	
	public String getTipoMonedaDENombre() {
		return tipoMonedaDENombre;
	}
	
	public void setTipoMonedaDENombre(String tipoMonedaDENombre) {
		this.tipoMonedaDENombre = tipoMonedaDENombre;
	}
	
	public Integer getIngresoUsuario() {
		return ingresoUsuario;
	}
	
	public void setIngresoUsuario(Integer ingresoUsuario) {
		this.ingresoUsuario = ingresoUsuario;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (tipoCambioID != null ? tipoCambioID.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof TipoCambio)) {
			return false;
		}
		TipoCambio other = (TipoCambio) object;
		if ((this.tipoCambioID == null && other.tipoCambioID != null)
				|| (this.tipoCambioID != null && !this.tipoCambioID
						.equals(other.tipoCambioID))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.com.fondam.sgp.core.domain.TipoCambio[tipoCambioID="
				+ tipoCambioID + "]";
	}
}
