package pe.com.fondam.sgp.core.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "meta_por_actividad")
public class MetaPorActividad implements Serializable {

	private static final long serialVersionUID = 8494766231116943479L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Meta_Por_Actividad_ID")
	private Integer metaPorActividadID;

	@Column(name = "fk_idtablaesp_tipo_indicador_actividad")
	private Integer fkIdtablaespTipoIndicadorActividad;

	@Column(name = "fk_idtablaesp_unidad_medida")
	private Integer fkIdtablaespUnidadMedida;

	@Column(name = "cantidad_meta_actividad")
	private Integer cantidadMetaActividad;

	@Column(name = "cantidad_meta_actividad_ejecutada")
	private Integer cantidadMetaActividadEjecutada;

	@Column(name = "logro_alcanzado")
	private Integer logroAlcanzado;

	@Lob
	@Column(name = "contribucion_proposito")
	private String contribucionProposito;

	@Transient
	private List<CronogramaMetaPorActividad> listCronogramaMetaPorActividad;
	
	@Transient
	private String descripcionTipoIndicadorActividad;
	
	@Transient
	private String descripcionUnidadMedida;
	
	@ManyToOne
	@JoinColumn(name = "Actividad_ID", referencedColumnName = "Actividad_ID")
	private Actividad actividad;

	@Column(name = "estado_eliminado")
	private Integer estadoEliminado;
	
	public Integer getMetaPorActividadID() {
		return metaPorActividadID;
	}

	public void setMetaPorActividadID(Integer metaPorActividadID) {
		this.metaPorActividadID = metaPorActividadID;
	}

	public Integer getFkIdtablaespTipoIndicadorActividad() {
		return fkIdtablaespTipoIndicadorActividad;
	}

	public void setFkIdtablaespTipoIndicadorActividad(
			Integer fkIdtablaespTipoIndicadorActividad) {
		this.fkIdtablaespTipoIndicadorActividad = fkIdtablaespTipoIndicadorActividad;
	}

	public Integer getFkIdtablaespUnidadMedida() {
		return fkIdtablaespUnidadMedida;
	}

	public void setFkIdtablaespUnidadMedida(Integer fkIdtablaespUnidadMedida) {
		this.fkIdtablaespUnidadMedida = fkIdtablaespUnidadMedida;
	}

	public Integer getCantidadMetaActividad() {
		return cantidadMetaActividad;
	}

	public void setCantidadMetaActividad(Integer cantidadMetaActividad) {
		this.cantidadMetaActividad = cantidadMetaActividad;
	}

	public Integer getCantidadMetaActividadEjecutada() {
		return cantidadMetaActividadEjecutada;
	}

	public void setCantidadMetaActividadEjecutada(
			Integer cantidadMetaActividadEjecutada) {
		this.cantidadMetaActividadEjecutada = cantidadMetaActividadEjecutada;
	}

	public Integer getLogroAlcanzado() {
		return logroAlcanzado;
	}

	public void setLogroAlcanzado(Integer logroAlcanzado) {
		this.logroAlcanzado = logroAlcanzado;
	}

	public String getContribucionProposito() {
		return contribucionProposito;
	}

	public void setContribucionProposito(String contribucionProposito) {
		this.contribucionProposito = contribucionProposito;
	}

	public void setListCronogramaMetaPorActividad(
			List<CronogramaMetaPorActividad> listCronogramaMetaPorActividad) {
		this.listCronogramaMetaPorActividad = listCronogramaMetaPorActividad;
	}

	public List<CronogramaMetaPorActividad> getListCronogramaMetaPorActividad() {
		return listCronogramaMetaPorActividad;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public String getDescripcionTipoIndicadorActividad() {
		return descripcionTipoIndicadorActividad;
	}

	public void setDescripcionTipoIndicadorActividad(
			String descripcionTipoIndicadorActividad) {
		this.descripcionTipoIndicadorActividad = descripcionTipoIndicadorActividad;
	}

	public String getDescripcionUnidadMedida() {
		return descripcionUnidadMedida;
	}

	public void setDescripcionUnidadMedida(String descripcionUnidadMedida) {
		this.descripcionUnidadMedida = descripcionUnidadMedida;
	}

	public void setEstadoEliminado(Integer estadoEliminado) {
		this.estadoEliminado = estadoEliminado;
	}

	public Integer getEstadoEliminado() {
		return estadoEliminado;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (metaPorActividadID != null ? metaPorActividadID.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof MetaPorActividad)) {
			return false;
		}
		MetaPorActividad other = (MetaPorActividad) object;
		if ((this.metaPorActividadID == null && other.metaPorActividadID != null)
				|| (this.metaPorActividadID != null && !this.metaPorActividadID
						.equals(other.metaPorActividadID))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.com.fondam.sgp.core.domain.MetaPorActividad[metaPorActividadID="
				+ metaPorActividadID + "]";
	}

}
