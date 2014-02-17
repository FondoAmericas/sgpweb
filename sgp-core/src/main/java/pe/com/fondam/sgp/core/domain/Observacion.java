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
import javax.persistence.Transient;

@Entity
@Table(name = "observacion")
public class Observacion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1981713827732212693L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "observacion_id")
	private Integer observacionID;

	@Lob
	@Column(name = "descripcion")
	private String descripcion;
	
	@Lob
	@Column(name = "descripcion_Ejecutor")
	private String descripcionEjecutor;

	@Column(name = "fk_idtablaesp_tipo_observacion")
	private Integer fkIdtablaespTipoObservacion;

	@Column(name = "fecha_observacion")
	private Date fechaObservacion;
	
	@Transient
	private String fechaObservacionString;

	@Column(name = "fecha_atencion_observacion")
	private Date fechaAtencionObservacion;
	
	@Transient
	private String fechaAtencionObservacionString;

	@Column(name = "fecha_levantamiento_observacion")
	private Date fechaLevantamientoObservacion;

	@Transient
	private String fechaLevantamientoObservacionString;
	
	@Column(name = "fk_idtablaesp_estado")
	private Integer fkIdtablaespEstado;
	
	@Transient
	private String descripcionEstado;

	@Column(name = "flag_estado")
	private Integer flagEstado;

    @ManyToOne
    @JoinColumn
    (name = "usuario_ID", referencedColumnName = "usuario_ID")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn
    (name = "Dato_Proyecto_ID", referencedColumnName = "Dato_Proyecto_ID")
    private DatoProyecto datoProyecto;

    @ManyToOne
    @JoinColumn
    (name = "table_clase_ID", referencedColumnName = "table_clase_ID")
    private TablaClase tablaClase;

	@Column(name = "tabla_ID")
	private Integer tablaID;//id de la tabla donde se esta poniendo la observacion, tabla de dentro del documento(en PO -Resultado, actividad, cronogramas, etc, por ejemplo).

	@Column(name = "clase_ID")
	private Integer claseID;//id de la tabla del documento que esta enlazado a la tabla donde se puso la obs (plan operativo, reporte avance, liquidacion, desembolso, etc.)
	
    @ManyToOne
    @JoinColumn
    (name = "tabla_profundidades_ID", referencedColumnName = "tabla_profundidades_ID")
    private TablaProfundidades tablaProfundidades;
    
    @Transient
    private String descripcionTablaProfundidades;

    @Column(name = "relevante_proyecto")
    private Integer relevanteProyecto;
	
	public Observacion(){
    	
    }
    
    public Integer getObservacionID() {
		return observacionID;
	}



	public void setObservacionID(Integer observacionID) {
		this.observacionID = observacionID;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public void setDescripcionEjecutor(String descripcionEjecutor) {
		this.descripcionEjecutor = descripcionEjecutor;
	}

	public String getDescripcionEjecutor() {
		return descripcionEjecutor;
	}

	public Integer getFkIdtablaespTipoObservacion() {
		return fkIdtablaespTipoObservacion;
	}



	public void setFkIdtablaespTipoObservacion(Integer fkIdtablaespTipoObservacion) {
		this.fkIdtablaespTipoObservacion = fkIdtablaespTipoObservacion;
	}



	public Date getFechaObservacion() {
		return fechaObservacion;
	}



	public void setFechaObservacion(Date fechaObservacion) {
		this.fechaObservacion = fechaObservacion;
	}



	public Date getFechaAtencionObservacion() {
		return fechaAtencionObservacion;
	}



	public void setFechaAtencionObservacion(Date fechaAtencionObservacion) {
		this.fechaAtencionObservacion = fechaAtencionObservacion;
	}



	public Date getFechaLevantamientoObservacion() {
		return fechaLevantamientoObservacion;
	}



	public void setFechaLevantamientoObservacion(Date fechaLevantamientoObservacion) {
		this.fechaLevantamientoObservacion = fechaLevantamientoObservacion;
	}



	public Integer getFkIdtablaespEstado() {
		return fkIdtablaespEstado;
	}



	public void setFkIdtablaespEstado(Integer fkIdtablaespEstado) {
		this.fkIdtablaespEstado = fkIdtablaespEstado;
	}



	public void setDescripcionEstado(String descripcionEstado) {
		this.descripcionEstado = descripcionEstado;
	}

	public String getDescripcionEstado() {
		return descripcionEstado;
	}

	public Integer getFlagEstado() {
		return flagEstado;
	}



	public void setFlagEstado(Integer flagEstado) {
		this.flagEstado = flagEstado;
	}



	public Usuario getUsuario() {
		return usuario;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	public DatoProyecto getDatoProyecto() {
		return datoProyecto;
	}



	public void setDatoProyecto(DatoProyecto datoProyecto) {
		this.datoProyecto = datoProyecto;
	}



	public TablaClase getTablaClase() {
		return tablaClase;
	}



	public void setTablaClase(TablaClase tablaClase) {
		this.tablaClase = tablaClase;
	}



	public void setClaseID(Integer claseID) {
		this.claseID = claseID;
	}

	public Integer getClaseID() {
		return claseID;
	}

	public void setTablaID(Integer tablaID) {
		this.tablaID = tablaID;
	}

	public Integer getTablaID() {
		return tablaID;
	}

	public void setTablaProfundidades(TablaProfundidades tablaProfundidades) {
		this.tablaProfundidades = tablaProfundidades;
	}

	public TablaProfundidades getTablaProfundidades() {
		return tablaProfundidades;
	}

	public void setRelevanteProyecto(Integer relevanteProyecto) {
		this.relevanteProyecto = relevanteProyecto;
	}

	public Integer getRelevanteProyecto() {
		return relevanteProyecto;
	}

	public String getFechaObservacionString() {
		return fechaObservacionString;
	}

	public void setFechaObservacionString(String fechaObservacionString) {
		this.fechaObservacionString = fechaObservacionString;
	}

	public String getFechaAtencionObservacionString() {
		return fechaAtencionObservacionString;
	}

	public void setFechaAtencionObservacionString(
			String fechaAtencionObservacionString) {
		this.fechaAtencionObservacionString = fechaAtencionObservacionString;
	}

	public String getFechaLevantamientoObservacionString() {
		return fechaLevantamientoObservacionString;
	}

	public void setFechaLevantamientoObservacionString(
			String fechaLevantamientoObservacionString) {
		this.fechaLevantamientoObservacionString = fechaLevantamientoObservacionString;
	}

	public void setDescripcionTablaProfundidades(
			String descripcionTablaProfundidades) {
		this.descripcionTablaProfundidades = descripcionTablaProfundidades;
	}

	public String getDescripcionTablaProfundidades() {
		return descripcionTablaProfundidades;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (observacionID != null ? observacionID.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Observacion)) {
			return false;
		}
		Observacion other = (Observacion) object;
		if ((this.observacionID == null && other.observacionID != null)
				|| (this.observacionID != null && !this.observacionID
						.equals(other.observacionID))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.com.fondam.sgp.core.domain.Observacion[observacionID="
				+ observacionID + "]";
	}

}
