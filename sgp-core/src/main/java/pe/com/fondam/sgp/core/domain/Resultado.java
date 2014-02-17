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
@Table(name = "resultado")
public class Resultado implements Serializable {

	private static final long serialVersionUID = -8299038596492439748L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Resultado_ID")
	private Integer resultadoID;

	@Column(name = "codigo_resultado")
	private Integer codigoResultado;

	@Lob
	@Column(name = "definicion_resultado")
	private String definicionResultado;

	@Lob
	@Column(name = "supuesto_resultado")
	private String supuestoResultado;

	@Column(name = "meta_resultado")
	private Integer metaResultado;

	@Column(name = "fk_idtablaesp_unidad_medida")
	private Integer fkIdtablaespUnidadMedida;
	
	@Transient
	private String descripcionUnidadMedida;

	@Column(name = "duracion_meses")
	private Integer duracionMeses;

	@Column(name = "estado_eliminado")
	private Integer estadoEliminado;
	
	@ManyToOne
	@JoinColumn(name = "Dato_Plan_Operativo_ID", referencedColumnName = "Dato_Plan_Operativo_ID")
	private DatoPlanOperativo datoPlanOperativo;

	@Transient
	private List<Actividad> listActividad;
	
	@Transient
	private List<CronogramaMetaPorResultado> listCronogramaMetaPorResultado;
	
	@Transient
	private Integer sumaTotalCronogramaMetaPorResultado;	
	
	@Transient
	private List<BeneficiariosPorResultado> listBeneficiariosPorResultado;
	
	
	public Integer getResultadoID() {
		return resultadoID;
	}

	public void setResultadoID(Integer resultadoID) {
		this.resultadoID = resultadoID;
	}

	public Integer getCodigoResultado() {
		return codigoResultado;
	}

	public void setCodigoResultado(Integer codigoResultado) {
		this.codigoResultado = codigoResultado;
	}

	public String getDefinicionResultado() {
		return definicionResultado;
	}

	public void setDefinicionResultado(String definicionResultado) {
		this.definicionResultado = definicionResultado;
	}

	public String getSupuestoResultado() {
		return supuestoResultado;
	}

	public void setSupuestoResultado(String supuestoResultado) {
		this.supuestoResultado = supuestoResultado;
	}

	public Integer getMetaResultado() {
		return metaResultado;
	}

	public void setMetaResultado(Integer metaResultado) {
		this.metaResultado = metaResultado;
	}

	public Integer getFkIdtablaespUnidadMedida() {
		return fkIdtablaespUnidadMedida;
	}

	public void setFkIdtablaespUnidadMedida(Integer fkIdtablaespUnidadMedida) {
		this.fkIdtablaespUnidadMedida = fkIdtablaespUnidadMedida;
	}

	public String getDescripcionUnidadMedida() {
		return descripcionUnidadMedida;
	}

	public void setDescripcionUnidadMedida(String descripcionUnidadMedida) {
		this.descripcionUnidadMedida = descripcionUnidadMedida;
	}

	public Integer getDuracionMeses() {
		return duracionMeses;
	}

	public void setDuracionMeses(Integer duracionMeses) {
		this.duracionMeses = duracionMeses;
	}

	public DatoPlanOperativo getDatoPlanOperativo() {
		return datoPlanOperativo;
	}

	public void setDatoPlanOperativo(DatoPlanOperativo datoPlanOperativo) {
		this.datoPlanOperativo = datoPlanOperativo;
	}

	public List<Actividad> getListActividad() {
		return listActividad;
	}

	public void setListActividad(List<Actividad> listActividad) {
		this.listActividad = listActividad;
	}

	public List<CronogramaMetaPorResultado> getListCronogramaMetaPorResultado() {
		return listCronogramaMetaPorResultado;
	}

	public void setListCronogramaMetaPorResultado(
			List<CronogramaMetaPorResultado> listCronogramaMetaPorResultado) {
		this.listCronogramaMetaPorResultado = listCronogramaMetaPorResultado;
	}

	public Integer getEstadoEliminado() {
		return estadoEliminado;
	}

	public void setEstadoEliminado(Integer estadoEliminado) {
		this.estadoEliminado = estadoEliminado;
	}

	public Integer getSumaTotalCronogramaMetaPorResultado() {
		return sumaTotalCronogramaMetaPorResultado;
	}

	public void setSumaTotalCronogramaMetaPorResultado(
			Integer sumaTotalCronogramaMetaPorResultado) {
		this.sumaTotalCronogramaMetaPorResultado = sumaTotalCronogramaMetaPorResultado;
	}

	public List<BeneficiariosPorResultado> getListBeneficiariosPorResultado() {
		return listBeneficiariosPorResultado;
	}

	public void setListBeneficiariosPorResultado(
			List<BeneficiariosPorResultado> listBeneficiariosPorResultado) {
		this.listBeneficiariosPorResultado = listBeneficiariosPorResultado;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (resultadoID != null ? resultadoID.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Resultado)) {
			return false;
		}
		Resultado other = (Resultado) object;
		if ((this.resultadoID == null && other.resultadoID != null)
				|| (this.resultadoID != null && !this.resultadoID
						.equals(other.resultadoID))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.com.fondam.sgp.core.domain.Resultado[resultadoID="
				+ resultadoID + "]";
	}

}
