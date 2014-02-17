package pe.com.fondam.sgp.core.domain;

import java.io.Serializable;
import java.util.List;

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
@Table(name = "dato_plan_operativo")
public class DatoPlanOperativo implements Serializable {

	private static final long serialVersionUID = 1590979994840165446L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Dato_Plan_Operativo_ID")
	private Integer datoPlanOperativoID;

	@Column(name = "version")
	private String version;

	@Column(name = "fk_id_detalle_estado_cab_estado_plan_oper")
	private Integer fkIdDetalleEstadoCabEstadoPlanOper;
	
	@Transient
	private String descripcionDetalleEstadoCabEstadoPlanOper;
	
	@Column(name = "fk_idtablaesp_tipo_moneda")
	private Integer fkIdtablaespTipoMoneda;

	@Column(name = "estado_eliminado")
	private Integer estadoEliminado;
	
	public Integer getEstadoEliminado() {
		return estadoEliminado;
	}

	public void setEstadoEliminado(Integer estadoEliminado) {
		this.estadoEliminado = estadoEliminado;
	}

	@ManyToOne
	@JoinColumn(name = "Dato_Proyecto_ID", referencedColumnName = "Dato_Proyecto_ID")
	private DatoProyecto datoProyecto;
	
	@Transient
	private List<Resultado> listResultado;

	public Integer getDatoPlanOperativoID() {
		return datoPlanOperativoID;
	}

	public void setDatoPlanOperativoID(Integer datoPlanOperativoID) {
		this.datoPlanOperativoID = datoPlanOperativoID;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Integer getFkIdDetalleEstadoCabEstadoPlanOper() {
		return fkIdDetalleEstadoCabEstadoPlanOper;
	}

	public void setFkIdDetalleEstadoCabEstadoPlanOper(
			Integer fkIdDetalleEstadoCabEstadoPlanOper) {
		this.fkIdDetalleEstadoCabEstadoPlanOper = fkIdDetalleEstadoCabEstadoPlanOper;
	}

	public void setFkIdtablaespTipoMoneda(Integer fkIdtablaespTipoMoneda) {
		this.fkIdtablaespTipoMoneda = fkIdtablaespTipoMoneda;
	}

	public Integer getFkIdtablaespTipoMoneda() {
		return fkIdtablaespTipoMoneda;
	}

	public DatoProyecto getDatoProyecto() {
		return datoProyecto;
	}

	public void setDatoProyecto(DatoProyecto datoProyecto) {
		this.datoProyecto = datoProyecto;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (datoPlanOperativoID != null ? datoPlanOperativoID.hashCode()
				: 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof DatoPlanOperativo)) {
			return false;
		}
		DatoPlanOperativo other = (DatoPlanOperativo) object;
		if ((this.datoPlanOperativoID == null && other.datoPlanOperativoID != null)
				|| (this.datoPlanOperativoID != null && !this.datoPlanOperativoID
						.equals(other.datoPlanOperativoID))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.com.fondam.sgp.core.domain.DatoPlanOperativo[datoPlanOperativoID="
				+ datoPlanOperativoID + "]";
	}

	public void setListResultado(List<Resultado> listResultado) {
		this.listResultado = listResultado;
	}

	public List<Resultado> getListResultado() {
		return listResultado;
	}

	public String getDescripcionDetalleEstadoCabEstadoPlanOper() {
		return descripcionDetalleEstadoCabEstadoPlanOper;
	}

	public void setDescripcionDetalleEstadoCabEstadoPlanOper(
			String descripcionDetalleEstadoCabEstadoPlanOper) {
		this.descripcionDetalleEstadoCabEstadoPlanOper = descripcionDetalleEstadoCabEstadoPlanOper;
	}

}
