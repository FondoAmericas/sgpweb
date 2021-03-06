package pe.com.fondam.sgp.core.domain;

import java.io.Serializable;

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
@Table(name = "beneficiarios_por_resultado")
public class BeneficiariosPorResultado implements Serializable {

	private static final long serialVersionUID = 8518995351684464046L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Beneficiarios_Por_Resultado_ID")
	private Integer beneficiariosPorResultadoID;

	@Column(name = "fk_idtablaesp_tipo_beneficiario")
	private Integer fkIdtablaespTipoBeneficiario;
	
	@Transient
	private String descripcionTipoBeneficiario;

	@Lob
	@Column(name = "caracteristicas_poblacion")
	private String caracteristicasPoblacion;

	@Column(name = "cantidad_programado")
	private Integer cantidadProgramado;

	@Column(name = "fk_idtablaesp_Estrato")
	private Integer fkidtablaespEstrato;

	@Transient
	private String descripcionEstrato;
	
	@Lob
	@Column(name = "descripcion")
	private String descripcion;

	@ManyToOne
	@JoinColumn(name = "Perfil_ID", referencedColumnName = "Perfil_ID")
	private Perfil perfil;

	@ManyToOne
	@JoinColumn(name = "Resultado_ID", referencedColumnName = "Resultado_ID")
	private Resultado resultado;
	
    @ManyToOne
    @JoinColumn(name = "Ubicacion_proyecto_ID", referencedColumnName = "Ubicacion_Proyecto_ID")
    private UbicacionProyecto ubicacionProyecto;

    @Column(name = "estado_eliminado")
	private Integer estadoEliminado;
	
    @Transient
    private boolean flagReferencia;
    
	public boolean isFlagReferencia() {
		return flagReferencia;
	}

	public void setFlagReferencia(boolean flagReferencia) {
		this.flagReferencia = flagReferencia;
	}

	public UbicacionProyecto getUbicacionProyecto() {
		return ubicacionProyecto;
	}

	public void setUbicacionProyecto(UbicacionProyecto ubicacionProyecto) {
		this.ubicacionProyecto = ubicacionProyecto;
	}

	public Integer getBeneficiariosPorResultadoID() {
		return beneficiariosPorResultadoID;
	}

	public void setBeneficiariosPorResultadoID(
			Integer beneficiariosPorResultadoID) {
		this.beneficiariosPorResultadoID = beneficiariosPorResultadoID;
	}

	public Integer getFkIdtablaespTipoBeneficiario() {
		return fkIdtablaespTipoBeneficiario;
	}

	public void setFkIdtablaespTipoBeneficiario(
			Integer fkIdtablaespTipoBeneficiario) {
		this.fkIdtablaespTipoBeneficiario = fkIdtablaespTipoBeneficiario;
	}

	public String getDescripcionTipoBeneficiario() {
		return descripcionTipoBeneficiario;
	}

	public void setDescripcionTipoBeneficiario(String descripcionTipoBeneficiario) {
		this.descripcionTipoBeneficiario = descripcionTipoBeneficiario;
	}

	public String getCaracteristicasPoblacion() {
		return caracteristicasPoblacion;
	}

	public void setCaracteristicasPoblacion(String caracteristicasPoblacion) {
		this.caracteristicasPoblacion = caracteristicasPoblacion;
	}

	public Integer getCantidadProgramado() {
		return cantidadProgramado;
	}

	public void setCantidadProgramado(Integer cantidadProgramado) {
		this.cantidadProgramado = cantidadProgramado;
	}

	public Integer getFkidtablaespEstrato() {
		return fkidtablaespEstrato;
	}

	public void setFkidtablaespEstrato(Integer fkidtablaespEstrato) {
		this.fkidtablaespEstrato = fkidtablaespEstrato;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Resultado getResultado() {
		return resultado;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}

	public String getDescripcionEstrato() {
		return descripcionEstrato;
	}

	public void setDescripcionEstrato(String descripcionEstrato) {
		this.descripcionEstrato = descripcionEstrato;
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
		hash += (beneficiariosPorResultadoID != null ? beneficiariosPorResultadoID
				.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof BeneficiariosPorResultado)) {
			return false;
		}
		BeneficiariosPorResultado other = (BeneficiariosPorResultado) object;
		if ((this.beneficiariosPorResultadoID == null && other.beneficiariosPorResultadoID != null)
				|| (this.beneficiariosPorResultadoID != null && !this.beneficiariosPorResultadoID
						.equals(other.beneficiariosPorResultadoID))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.com.fondam.sgp.core.domain.BeneficiariosPorResultado[beneficiariosPorResultadoID="
				+ beneficiariosPorResultadoID + "]";
	}

}
