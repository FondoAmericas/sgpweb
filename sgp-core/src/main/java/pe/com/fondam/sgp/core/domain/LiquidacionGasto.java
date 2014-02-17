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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "liquidacion_gasto")

public class LiquidacionGasto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    @Column(name = "Liquidacion_Gasto_ID")
    private Integer liquidacionGastoID;
    
    @Column(name = "fk_id_detalle_estado_cab_est_liq_gasto")
    private int fkIdDetalleEstadoCabEstLiqGasto;
    
    @Transient
    private String prefijoEstadoLiqGasto;
    
    @Column(name = "periodo")
    private String periodo;
    
    @Column(name = "cod_version")
    private String codVersion;
    
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    
    @Transient
    private String fechaInicioString;
    
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    
    @Transient
    private String fechaFinString;
    
    @Column(name = "saldo_disponible")
    private double saldoDisponible;
    
    @Lob
    @Column(name = "observacion")
    private String observacion;
    
    @Column(name = "num_liq_parcial")
    private int numLiqParcial;
    
    @Column(name = "num_vuelta_liq_parcial")
    private int numVueltaLiqParcial;
  
    @ManyToOne
    @JoinColumn(name = "Dato_Proyecto_ID", referencedColumnName = "Dato_Proyecto_ID")
    private DatoProyecto datoProyecto;

    @ManyToOne
    @JoinColumn(name = "Fuente_Financiadora_ID", referencedColumnName = "Fuente_Financiadora_ID")
    private FuenteFinanciadora fuenteFinanciadora;
    
    @Transient
    private List<PagoLiquidacion> listPagoLiquidacion;

    @Transient
    private String estLiqGastoDesc;    
    
    @Transient
    private String liqGastoDesc;

    @Transient
	private Integer cantObservacionesRelevantes;
  
    
    public String getEstLiqGastoDesc() {
		return estLiqGastoDesc;
	}

	public void setEstLiqGastoDesc(String estLiqGastoDesc) {
		this.estLiqGastoDesc = estLiqGastoDesc;
	}

	public String getLiqGastoDesc() {
		return liqGastoDesc;
	}

	public void setLiqGastoDesc(String liqGastoDesc) {
		this.liqGastoDesc = liqGastoDesc;
	}

	public Integer getLiquidacionGastoID() {
        return liquidacionGastoID;
    }

    public void setLiquidacionGastoID(Integer liquidacionGastoID) {
        this.liquidacionGastoID = liquidacionGastoID;
    }

    public int getFkIdDetalleEstadoCabEstLiqGasto() {
        return fkIdDetalleEstadoCabEstLiqGasto;
    }

    public void setFkIdDetalleEstadoCabEstLiqGasto(int fkIdDetalleEstadoCabEstLiqGasto) {
        this.fkIdDetalleEstadoCabEstLiqGasto = fkIdDetalleEstadoCabEstLiqGasto;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getCodVersion() {
        return codVersion;
    }

    public void setCodVersion(String codVersion) {
        this.codVersion = codVersion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getSaldoDisponible() {
        return saldoDisponible;
    }

    public void setSaldoDisponible(double saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getNumLiqParcial() {
        return numLiqParcial;
    }

    public void setNumLiqParcial(int numLiqParcial) {
        this.numLiqParcial = numLiqParcial;
    }

    public int getNumVueltaLiqParcial() {
        return numVueltaLiqParcial;
    }

    public void setNumVueltaLiqParcial(int numVueltaLiqParcial) {
        this.numVueltaLiqParcial = numVueltaLiqParcial;
    }



    public DatoProyecto getDatoProyecto() {
        return datoProyecto;
    }

    public void setDatoProyecto(DatoProyecto datoProyecto) {
        this.datoProyecto = datoProyecto;
    }


    public void setFuenteFinanciadora(FuenteFinanciadora fuenteFinanciadora) {
		this.fuenteFinanciadora = fuenteFinanciadora;
	}

	public FuenteFinanciadora getFuenteFinanciadora() {
		return fuenteFinanciadora;
	}

	public void setPrefijoEstadoLiqGasto(String prefijoEstadoLiqGasto) {
		this.prefijoEstadoLiqGasto = prefijoEstadoLiqGasto;
	}

	public String getPrefijoEstadoLiqGasto() {
		return prefijoEstadoLiqGasto;
	}

	public String getFechaInicioString() {
		return fechaInicioString;
	}

	public void setFechaInicioString(String fechaInicioString) {
		this.fechaInicioString = fechaInicioString;
	}

	public String getFechaFinString() {
		return fechaFinString;
	}

	public void setFechaFinString(String fechaFinString) {
		this.fechaFinString = fechaFinString;
	}

	public void setListPagoLiquidacion(List<PagoLiquidacion> listPagoLiquidacion) {
		this.listPagoLiquidacion = listPagoLiquidacion;
	}

	public List<PagoLiquidacion> getListPagoLiquidacion() {
		return listPagoLiquidacion;
	}

	public void setCantObservacionesRelevantes(
			Integer cantObservacionesRelevantes) {
		this.cantObservacionesRelevantes = cantObservacionesRelevantes;
	}

	public Integer getCantObservacionesRelevantes() {
		return cantObservacionesRelevantes;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (liquidacionGastoID != null ? liquidacionGastoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LiquidacionGasto)) {
            return false;
        }
        LiquidacionGasto other = (LiquidacionGasto) object;
        if ((this.liquidacionGastoID == null && other.liquidacionGastoID != null) || (this.liquidacionGastoID != null && !this.liquidacionGastoID.equals(other.liquidacionGastoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.LiquidacionGasto[liquidacionGastoID=" + liquidacionGastoID + "]";
    }

}
