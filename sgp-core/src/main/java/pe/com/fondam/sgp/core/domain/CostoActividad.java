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
@Table(name = "costo_actividad")
public class CostoActividad implements Serializable {

	private static final long serialVersionUID = 761919248898468L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Costo_Actividad_ID")
	private Integer costoActividadID;

	@Column(name = "monto_gastado")
	private Double montoGastado;

	@Column(name = "fk_idtablaesp_unidad_medida")
	private Integer fkIdtablaespUnidadMedida;
	
	@Transient
	private String descripcionUnidadMedida;

	@Column(name = "cantidad_total")
	private Integer cantidadTotal;

	@Column(name = "precio_unitario")
	private Double precioUnitario;

	@Column(name = "fk_idtablaesp_tipo_moneda")
	private Integer fkIdtablaespTipoMoneda;
	
	@Transient
	private String descripcionTipoMoneda;

	@Lob
	@Column(name = "detalle_partida_generica")
	private String detallePartidaGenerica;

	@Lob
	@Column(name = "detalle_rubro_generico")
	private String detalleRubroGenerico;
	
	@Transient
	private List<CronogramaCostoActividad> listCronogramaCostoActividad;

	@ManyToOne
	@JoinColumn(name = "Actividad_ID", referencedColumnName = "Actividad_ID")
	private Actividad actividad;

	@ManyToOne
	@JoinColumn(name = "Categoria_Actividad_ID", referencedColumnName = "Categoria_Actividad_ID")
	private CategoriaActividad categoriaActividad;
	
	@ManyToOne
	@JoinColumn(name = "Rubro_Generico_ID", referencedColumnName = "Rubro_Generico_ID")
	private RubroGenerico rubroGenerico;

	@ManyToOne
	@JoinColumn(name = "Partida_Generica_ID", referencedColumnName = "Partida_Generica_ID")
	private PartidaGenerica partidaGenerica;
	
//	@Column(name = "grupo_partida")
//	private String grupoPartida;
	
	@ManyToOne
	@JoinColumn(name = "Partida_Especifica_ID", referencedColumnName = "Partida_Especifica_ID")
	private PartidaEspecifica partidaEspecifica;

	@Column(name = "estado_eliminado")
	private Integer estadoEliminado;
	
	public Integer getCostoActividadID() {
		return costoActividadID;
	}

	public void setCostoActividadID(Integer costoActividadID) {
		this.costoActividadID = costoActividadID;
	}

	public Double getMontoGastado() {
		return montoGastado;
	}

	public void setMontoGastado(Double montoGastado) {
		this.montoGastado = montoGastado;
	}
/*
	public Integer getFkIdtablaespTipoMonedaMg() {
		return fkIdtablaespTipoMonedaMg;
	}

	public void setFkIdtablaespTipoMonedaMg(Integer fkIdtablaespTipoMonedaMg) {
		this.fkIdtablaespTipoMonedaMg = fkIdtablaespTipoMonedaMg;
	}
*/
	public Integer getFkIdtablaespUnidadMedida() {
		return fkIdtablaespUnidadMedida;
	}

	public void setFkIdtablaespUnidadMedida(Integer fkIdtablaespUnidadMedida) {
		this.fkIdtablaespUnidadMedida = fkIdtablaespUnidadMedida;
	}

	public Integer getCantidadTotal() {
		return cantidadTotal;
	}

	public void setCantidadTotal(Integer cantidadTotal) {
		this.cantidadTotal = cantidadTotal;
	}
/*
	public Integer getFkIdtablaespTipoMonedaCt() {
		return fkIdtablaespTipoMonedaCt;
	}

	public void setFkIdtablaespTipoMonedaCt(Integer fkIdtablaespTipoMonedaCt) {
		this.fkIdtablaespTipoMonedaCt = fkIdtablaespTipoMonedaCt;
	}
*/
	public Double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public Integer getFkIdtablaespTipoMoneda() {
		return fkIdtablaespTipoMoneda;
	}

	public void setFkIdtablaespTipoMoneda(Integer fkIdtablaespTipoMoneda) {
		this.fkIdtablaespTipoMoneda = fkIdtablaespTipoMoneda;
	}

	/*
	public Integer getFkIdtablaespTipoMonedaPu() {
		return fkIdtablaespTipoMonedaPu;
	}

	public void setFkIdtablaespTipoMonedaPu(Integer fkIdtablaespTipoMonedaPu) {
		this.fkIdtablaespTipoMonedaPu = fkIdtablaespTipoMonedaPu;
	}
*/
	public String getDetallePartidaGenerica() {
		return detallePartidaGenerica;
	}

	public void setDetallePartidaGenerica(String detallePartidaGenerica) {
		this.detallePartidaGenerica = detallePartidaGenerica;
	}

	public String getDetalleRubroGenerico() {
		return detalleRubroGenerico;
	}

	public void setListCronogramaCostoActividad(List<CronogramaCostoActividad> listCronogramaCostoActividad) {
		this.listCronogramaCostoActividad = listCronogramaCostoActividad;
	}

	public List<CronogramaCostoActividad> getListCronogramaCostoActividad() {
		return listCronogramaCostoActividad;
	}

	public void setDetalleRubroGenerico(String detalleRubroGenerico) {
		this.detalleRubroGenerico = detalleRubroGenerico;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public CategoriaActividad getCategoriaActividad() {
		return categoriaActividad;
	}

	public void setCategoriaActividad(CategoriaActividad categoriaActividad) {
		this.categoriaActividad = categoriaActividad;
	}

	public RubroGenerico getRubroGenerico() {
		return rubroGenerico;
	}

	public void setRubroGenerico(RubroGenerico rubroGenerico) {
		this.rubroGenerico = rubroGenerico;
	}

	public PartidaGenerica getPartidaGenerica() {
		return partidaGenerica;
	}

	public void setPartidaGenerica(PartidaGenerica partidaGenerica) {
		this.partidaGenerica = partidaGenerica;
	}

//	public String getGrupoPartida() {
//		return grupoPartida;
//	}
//
//	public void setGrupoPartida(String grupoPartida) {
//		this.grupoPartida = grupoPartida;
//	}

	public PartidaEspecifica getPartidaEspecifica() {
		return partidaEspecifica;
	}

	public void setPartidaEspecifica(PartidaEspecifica partidaEspecifica) {
		this.partidaEspecifica = partidaEspecifica;
	}

	public void setDescripcionUnidadMedida(String descripcionUnidadMedida) {
		this.descripcionUnidadMedida = descripcionUnidadMedida;
	}

	public String getDescripcionUnidadMedida() {
		return descripcionUnidadMedida;
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
		hash += (costoActividadID != null ? costoActividadID.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof CostoActividad)) {
			return false;
		}
		CostoActividad other = (CostoActividad) object;
		if ((this.costoActividadID == null && other.costoActividadID != null)
				|| (this.costoActividadID != null && !this.costoActividadID
						.equals(other.costoActividadID))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.com.fondam.sgp.core.domain.CostoActividad[costoActividadID="
				+ costoActividadID + "]";
	}

}
