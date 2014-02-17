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
@Table(name = "actividad")
public class Actividad implements Serializable {

	private static final long serialVersionUID = 7792190898733907614L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Actividad_ID")
	private Integer actividadID;

	@Column(name = "codigo_actividad")
	private Integer codigoActividad;

	@Column(name = "fk_idtablaesp_tipo_actividad")
	private Integer fkIdtablaespTipoActividad;
	
	@Transient
	private String descripcionTipoActividad;

	@Lob
	@Column(name = "nombre_actividad")
	private String nombreActividad;

	@Lob
	@Column(name = "descripcion_actividad")
	private String descripcionActividad;

	@Column(name = "duracion_meses")
	private Integer duracionMeses;

	@Column(name = "fk_idtablaesp_actividad_transf")
	private Integer fkIdtablaespActividadTransf;
	
	@Transient
	private List<CostoActividad> listCostoActividad;
	
	@Transient
	private List<MetaPorActividad> listMetaPorActividad;

	@ManyToOne
	@JoinColumn(name = "Resultado_ID", referencedColumnName = "Resultado_ID")
	private Resultado resultado;

	@Column(name = "estado_eliminado")
	private Integer estadoEliminado;
	
	public Integer getActividadID() {
		return actividadID;
	}

	public void setActividadID(Integer actividadID) {
		this.actividadID = actividadID;
	}

	public Integer getCodigoActividad() {
		return codigoActividad;
	}

	public void setCodigoActividad(Integer codigoActividad) {
		this.codigoActividad = codigoActividad;
	}

	public Integer getFkIdtablaespTipoActividad() {
		return fkIdtablaespTipoActividad;
	}

	public void setFkIdtablaespTipoActividad(Integer fkIdtablaespTipoActividad) {
		this.fkIdtablaespTipoActividad = fkIdtablaespTipoActividad;
	}

	public String getNombreActividad() {
		return nombreActividad;
	}

	public void setNombreActividad(String nombreActividad) {
		this.nombreActividad = nombreActividad;
	}

	public String getDescripcionActividad() {
		return descripcionActividad;
	}

	public void setDescripcionActividad(String descripcionActividad) {
		this.descripcionActividad = descripcionActividad;
	}

	public Integer getDuracionMeses() {
		return duracionMeses;
	}

	public void setDuracionMeses(Integer duracionMeses) {
		this.duracionMeses = duracionMeses;
	}

	public Integer getFkIdtablaespActividadTransf() {
		return fkIdtablaespActividadTransf;
	}

	public void setFkIdtablaespActividadTransf(
			Integer fkIdtablaespActividadTransf) {
		this.fkIdtablaespActividadTransf = fkIdtablaespActividadTransf;
	}

	public Resultado getResultado() {
		return resultado;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}

	public List<CostoActividad> getListCostoActividad() {
		return listCostoActividad;
	}

	public void setListCostoActividad(List<CostoActividad> listCostoActividad) {
		this.listCostoActividad = listCostoActividad;
	}

	public List<MetaPorActividad> getListMetaPorActividad() {
		return listMetaPorActividad;
	}

	public void setListMetaPorActividad(List<MetaPorActividad> listMetaPorActividad) {
		this.listMetaPorActividad = listMetaPorActividad;
	}

	public void setEstadoEliminado(Integer estadoEliminado) {
		this.estadoEliminado = estadoEliminado;
	}

	public Integer getEstadoEliminado() {
		return estadoEliminado;
	}

	public String getDescripcionTipoActividad() {
		return descripcionTipoActividad;
	}

	public void setDescripcionTipoActividad(String descripcionTipoActividad) {
		this.descripcionTipoActividad = descripcionTipoActividad;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (actividadID != null ? actividadID.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Actividad)) {
			return false;
		}
		Actividad other = (Actividad) object;
		if ((this.actividadID == null && other.actividadID != null)
				|| (this.actividadID != null && !this.actividadID
						.equals(other.actividadID))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.com.fondam.sgp.core.domain.Actividad[actividadID="
				+ actividadID + "]";
	}

}
