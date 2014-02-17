/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.fondam.sgp.core.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
@Table(name = "desembolso")
public class Desembolso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     
    @Column(name = "Desembolso_ID")
    private Integer desembolsoID;
     
    @Column(name = "fk_idtablaesp_tipo_desembolso")
    private int fkIdtablaespTipoDesembolso;
    
    @Transient
    private String descripcionTipoDesembolso;
     
    @Column(name = "monto_solicitado")
    private double montoSolicitado;
    
    @Column(name = "monto_autorizado")
    private double montoAutorizado;
     
    @Column(name = "fk_idtablaesp_tipo_moneda")
    private int fkIdtablaespTipoMoneda;

    @Transient
    private String descripcionTipoMoneda;
    
    @Column(name = "saldo_desembolso_anterior")
    private double saldoDesembolsoAnterior;
     
    @Column(name = "periodo")
    private String periodo;
     
    @Column(name = "fk_idtablaesp_est_desembolso")
    private int fkIdtablaespEstDesembolso;
    
    @Transient
    private String descripcionEstDesembolso;
     
    @Column(name = "version_de_periodo")
    private int versionDePeriodo;

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
    
    @ManyToOne
    @JoinColumn(name = "Rubro_Especial_ID", referencedColumnName = "Rubro_Especial_ID")
    private RubroEspecial rubroEspecial;
    
    @ManyToOne
    @JoinColumn(name = "Cuenta_Corriente_ID", referencedColumnName = "Cuenta_Corriente_ID")
    private CuentaCorriente cuentaCorriente;
    
    @ManyToOne
    @JoinColumn(name = "Fuente_Financiadora_ID", referencedColumnName = "Fuente_Financiadora_ID")
    private FuenteFinanciadora fuenteFinanciadora;
    
    @ManyToOne
    @JoinColumn(name = "Dato_Proyecto_ID", referencedColumnName = "Dato_Proyecto_ID")
    private DatoProyecto datoProyecto;
 
    @Transient
    private List<DetalleDesembolso> listDetalleDesembolso;
 
    @Transient
    private List<TipoCambio> listTipoCambio;
    
    @Transient
    private Institucion institucionEjecutora;
    
    @Transient
	private Integer cantObservacionesRelevantes;
    
    public Integer getDesembolsoID() {
        return desembolsoID;
    }

    public void setDesembolsoID(Integer desembolsoID) {
        this.desembolsoID = desembolsoID;
    }

    public int getFkIdtablaespTipoDesembolso() {
        return fkIdtablaespTipoDesembolso;
    }

    public void setFkIdtablaespTipoDesembolso(int fkIdtablaespTipoDesembolso) {
        this.fkIdtablaespTipoDesembolso = fkIdtablaespTipoDesembolso;
    }

    public double getMontoSolicitado() {
        return montoSolicitado;
    }

    public void setMontoSolicitado(double montoSolicitado) {
        this.montoSolicitado = montoSolicitado;
    }
/*
    public int getFkIdtablaespTipoMonedaMs() {
        return fkIdtablaespTipoMonedaMs;
    }

    public void setFkIdtablaespTipoMonedaMs(int fkIdtablaespTipoMonedaMs) {
        this.fkIdtablaespTipoMonedaMs = fkIdtablaespTipoMonedaMs;
    }
*/
    public double getMontoAutorizado() {
        return montoAutorizado;
    }

    public void setMontoAutorizado(double montoAutorizado) {
        this.montoAutorizado = montoAutorizado;
    }

    public int getFkIdtablaespTipoMoneda() {
        return fkIdtablaespTipoMoneda;
    }

    public void setFkIdtablaespTipoMoneda(int fkIdtablaespTipoMoneda) {
        this.fkIdtablaespTipoMoneda = fkIdtablaespTipoMoneda;
    }

    public double getSaldoDesembolsoAnterior() {
        return saldoDesembolsoAnterior;
    }

    public void setSaldoDesembolsoAnterior(double saldoDesembolsoAnterior) {
        this.saldoDesembolsoAnterior = saldoDesembolsoAnterior;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public int getFkIdtablaespEstDesembolso() {
        return fkIdtablaespEstDesembolso;
    }

    public void setFkIdtablaespEstDesembolso(int fkIdtablaespEstDesembolso) {
        this.fkIdtablaespEstDesembolso = fkIdtablaespEstDesembolso;
    }

    public int getVersionDePeriodo() {
        return versionDePeriodo;
    }

    public void setVersionDePeriodo(int versionDePeriodo) {
        this.versionDePeriodo = versionDePeriodo;
    }

    public RubroEspecial getRubroEspecial() {
        return rubroEspecial;
    }

    public void setRubroEspecial(RubroEspecial rubroEspecial) {
        this.rubroEspecial = rubroEspecial;
    }

    public CuentaCorriente getCuentaCorriente() {
        return cuentaCorriente;
    }

    public void setCuentaCorriente(CuentaCorriente cuentaCorriente) {
        this.cuentaCorriente = cuentaCorriente;
    }

    public FuenteFinanciadora getFuenteFinanciadora() {
        return fuenteFinanciadora;
    }

    public void setFuenteFinanciadora(FuenteFinanciadora fuenteFinanciadora) {
        this.fuenteFinanciadora = fuenteFinanciadora;
    }

    public DatoProyecto getDatoProyecto() {
        return datoProyecto;
    }

    public void setDatoProyecto(DatoProyecto datoProyecto) {
        this.datoProyecto = datoProyecto;
    }

    public void setListDetalleDesembolso(List<DetalleDesembolso> listDetalleDesembolso) {
		this.listDetalleDesembolso = listDetalleDesembolso;
	}

	public List<DetalleDesembolso> getListDetalleDesembolso() {
		return listDetalleDesembolso;
	}

	public String getDescripcionTipoDesembolso() {
		return descripcionTipoDesembolso;
	}

	public void setDescripcionTipoDesembolso(String descripcionTipoDesembolso) {
		this.descripcionTipoDesembolso = descripcionTipoDesembolso;
	}

	public String getDescripcionTipoMoneda() {
		return descripcionTipoMoneda;
	}

	public void setDescripcionTipoMoneda(String descripcionTipoMoneda) {
		this.descripcionTipoMoneda = descripcionTipoMoneda;
	}

	public String getDescripcionEstDesembolso() {
		return descripcionEstDesembolso;
	}

	public void setDescripcionEstDesembolso(String descripcionEstDesembolso) {
		this.descripcionEstDesembolso = descripcionEstDesembolso;
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

	public void setInstitucionEjecutora(Institucion institucionEjecutora) {
		this.institucionEjecutora = institucionEjecutora;
	}

	public Institucion getInstitucionEjecutora() {
		return institucionEjecutora;
	}

	public void setFechaSolicitudString(String fechaSolicitudString) {
		this.fechaSolicitudString = fechaSolicitudString;
	}

	public String getFechaSolicitudString() {
		return fechaSolicitudString;
	}

	public void setFechaAprobacionString(String fechaAprobacionString) {
		this.fechaAprobacionString = fechaAprobacionString;
	}

	public String getFechaAprobacionString() {
		return fechaAprobacionString;
	}

	public Integer getCantObservacionesRelevantes() {
		return cantObservacionesRelevantes;
	}

	public void setCantObservacionesRelevantes(Integer cantObservacionesRelevantes) {
		this.cantObservacionesRelevantes = cantObservacionesRelevantes;
	}

	public void setListTipoCambio(List<TipoCambio> listTipoCambio) {
		this.listTipoCambio = listTipoCambio;
	}

	public List<TipoCambio> getListTipoCambio() {
		return listTipoCambio;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (desembolsoID != null ? desembolsoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Desembolso)) {
            return false;
        }
        Desembolso other = (Desembolso) object;
        if ((this.desembolsoID == null && other.desembolsoID != null) || (this.desembolsoID != null && !this.desembolsoID.equals(other.desembolsoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.Desembolso[desembolsoID=" + desembolsoID + "]";
    }

}
