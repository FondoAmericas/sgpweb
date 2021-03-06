/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.fondam.sgp.core.domain;

import java.io.Serializable;
import java.util.ArrayList;
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
@Table(name = "dato_proyecto")
public class DatoProyecto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6731741147048703429L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Dato_Proyecto_ID")
	private Integer datoProyectoID;

	@Column(name = "duracion_proyecto")
	private Integer duracionProyecto;

	@Column(name = "cantidad_Periodo")
	private Integer cantidadPeriodo;

	@Lob
	@Column(name = "nombre_proyecto")
	private String nombreProyecto;

	@Column(name = "codigo_proyecto")
	private String codigoProyecto;

	@Column(name = "fk_iddetallestadocab_estproy")
	private Integer fkIddetallestadocabEstproy;

	@Transient
	private String descripcionEstadoProyecto;
	
	@Column(name = "numero_orden_dato_proyecto")
	private Integer numero_orden_dato_proyecto;

	@ManyToOne
	@JoinColumn(name = "Programa_ID", referencedColumnName = "Programa_ID")
	private Programa programa;

	@ManyToOne
	@JoinColumn(name = "Sub_Area_Tematica_ID", referencedColumnName = "Sub_Area_Tematica_ID")
	private SubAreaTematica subAreaTematica;

	@Transient
	private String descripcionAreaTematica;
	
	@Transient
	private String descripcionSubAreaTematica;
	
	@Transient
	private List<Desembolso> listDesembolso = new ArrayList<Desembolso>();
	
	@Transient
	private List<SolicitaRpRf> listSolicitaRpRf =  new ArrayList<SolicitaRpRf>();
	
	@Transient
	private Integer cantObservacionesRelevantes;
	
	
	public Integer getDatoProyectoID() {
		return datoProyectoID;
	}

	public void setDatoProyectoID(Integer datoProyectoID) {
		this.datoProyectoID = datoProyectoID;
	}

	public Integer getDuracionProyecto() {
		return duracionProyecto;
	}

	public void setDuracionProyecto(Integer duracionProyecto) {
		this.duracionProyecto = duracionProyecto;
	}

	public Integer getCantidadPeriodo() {
		return cantidadPeriodo;
	}

	public void setCantidadPeriodo(Integer cantidadPeriodo) {
		this.cantidadPeriodo = cantidadPeriodo;
	}

	public String getNombreProyecto() {
		return nombreProyecto;
	}

	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}

	public String getCodigoProyecto() {
		return codigoProyecto;
	}

	public void setCodigoProyecto(String codigoProyecto) {
		this.codigoProyecto = codigoProyecto;
	}

	public Integer getFkIddetallestadocabEstproy() {
		return fkIddetallestadocabEstproy;
	}

	public void setFkIddetallestadocabEstproy(Integer fkIddetallestadocabEstproy) {
		this.fkIddetallestadocabEstproy = fkIddetallestadocabEstproy;
	}

	public String getDescripcionEstadoProyecto() {
		return descripcionEstadoProyecto;
	}

	public void setDescripcionEstadoProyecto(String descripcionEstadoProyecto) {
		this.descripcionEstadoProyecto = descripcionEstadoProyecto;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public SubAreaTematica getSubAreaTematica() {
		return subAreaTematica;
	}

	public void setSubAreaTematica(SubAreaTematica subAreaTematica) {
		this.subAreaTematica = subAreaTematica;
	}

	public Integer getNumero_orden_dato_proyecto() {
		return numero_orden_dato_proyecto;
	}

	public void setNumero_orden_dato_proyecto(Integer numero_orden_dato_proyecto) {
		this.numero_orden_dato_proyecto = numero_orden_dato_proyecto;
	}

	public void setDescripcionAreaTematica(String descripcionAreaTematica) {
		this.descripcionAreaTematica = descripcionAreaTematica;
	}

	public String getDescripcionAreaTematica() {
		return descripcionAreaTematica;
	}

	public void setDescripcionSubAreaTematica(String descripcionSubAreaTematica) {
		this.descripcionSubAreaTematica = descripcionSubAreaTematica;
	}

	public String getDescripcionSubAreaTematica() {
		return descripcionSubAreaTematica;
	}

	public void setListDesembolso(List<Desembolso> listDesembolso) {
		this.listDesembolso = listDesembolso;
	}

	public List<Desembolso> getListDesembolso() {
		return listDesembolso;
	}

	public void setListSolicitaRpRf(List<SolicitaRpRf> listSolicitaRpRf) {
		this.listSolicitaRpRf = listSolicitaRpRf;
	}

	public List<SolicitaRpRf> getListSolicitaRpRf() {
		return listSolicitaRpRf;
	}

	public Integer getCantObservacionesRelevantes() {
		return cantObservacionesRelevantes;
	}

	public void setCantObservacionesRelevantes(Integer cantObservacionesRelevantes) {
		this.cantObservacionesRelevantes = cantObservacionesRelevantes;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (datoProyectoID != null ? datoProyectoID.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof DatoProyecto)) {
			return false;
		}
		DatoProyecto other = (DatoProyecto) object;
		if ((this.datoProyectoID == null && other.datoProyectoID != null)
				|| (this.datoProyectoID != null && !this.datoProyectoID
						.equals(other.datoProyectoID))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.com.fondam.sgp.core.domain.DatoProyecto[datoProyectoID="
				+ datoProyectoID + "]";
	}

}
