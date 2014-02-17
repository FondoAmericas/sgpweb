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
@Table(name = "pago_liquidacion")

public class PagoLiquidacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    @Column(name = "Pago_Liquidacion_ID")
    private Integer pagoLiquidacionID;
    
    @Column(name = "numero_cheque")
    private String numeroCheque;
    
    @Column(name = "cheque_cobrado")
    private int chequeCobrado;
    
    @Column(name = "fk_idtablaesp_tipo_comprobante_pago")
    private int fkIdtablaespTipoComprobantePago;
    
    @Column(name = "numero_documento")
    private String numeroDocumento;
    
    @Column(name = "fecha_emision")
    @Temporal(TemporalType.DATE)
    private Date fechaEmision;
    
    @Transient
    private String fechaEmisionString;
    
    @Column(name = "ruc_proveedor")
    private String rucProveedor;
    
    @Column(name = "razon_social")
    private String razonSocial;
    
    @Column(name = "monto_total")
    private double montoTotal;
    
    @Column(name = "fk_idtablaesp_tipo_moneda")
    private Integer fkIdtablaespTipoMoneda;
    	
    @Column(name = "monto_igv")
    private double montoIgv;
    
    @Column(name = "tasa_igv")
    private Integer tasaIgv;
    
    @Column(name = "saldo_pagado")
    private double saldoPagado;
    
    @Column(name = "saldo_disponible")
    private double saldoDisponible;
    
    @ManyToOne
    @JoinColumn(name = "Liquidacion_Gasto_ID", referencedColumnName = "Liquidacion_Gasto_ID")
    private LiquidacionGasto liquidacionGasto;
    
    @Column(name = "Desembolso_ID")
    private Integer desembolsoID;
    
    @Transient
    private Desembolso desembolso;
    
    @Transient
    private String descripcionDesembolso;
    
    @Transient
    private String tipoComprobantePagoDesc;
   
	@Transient
    private String tipoMonedaDesc;
	
	@Transient
	private String bancoDesc;
	
	@Transient
	private String chekeCobradoDesc;

	@Transient
	private List<DetallePagoLiquidacion> lstDetallePagoLiquidacion;
	
	@Transient
	private Integer cantidadDetallePagoLiquidacion;
	
	public List<DetallePagoLiquidacion> getLstDetallePagoLiquidacion() {
		return lstDetallePagoLiquidacion;
	}

	public void setLstDetallePagoLiquidacion(
			List<DetallePagoLiquidacion> lstDetallePagoLiquidacion) {
		this.lstDetallePagoLiquidacion = lstDetallePagoLiquidacion;
	}
	
	public String getTipoMonedaDesc() {
		return tipoMonedaDesc;
	}

	public void setTipoMonedaDesc(String tipoMonedaDesc) {
		this.tipoMonedaDesc = tipoMonedaDesc;
	}

	public Integer getPagoLiquidacionID() {
        return pagoLiquidacionID;
    }

    public void setPagoLiquidacionID(Integer pagoLiquidacionID) {
        this.pagoLiquidacionID = pagoLiquidacionID;
    }

    public String getNumeroCheque() {
        return numeroCheque;
    }

    public void setNumeroCheque(String numeroCheque) {
        this.numeroCheque = numeroCheque;
    }

    public int getChequeCobrado() {
        return chequeCobrado;
    }

    public void setChequeCobrado(int chequeCobrado) {
        this.chequeCobrado = chequeCobrado;
    }

    public int getFkIdtablaespTipoComprobantePago() {
        return fkIdtablaespTipoComprobantePago;
    }

    public void setFkIdtablaespTipoComprobantePago(int fkIdtablaespTipoComprobantePago) {
        this.fkIdtablaespTipoComprobantePago = fkIdtablaespTipoComprobantePago;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getRucProveedor() {
        return rucProveedor;
    }

    public void setRucProveedor(String rucProveedor) {
        this.rucProveedor = rucProveedor;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public double getMontoIgv() {
        return montoIgv;
    }

    public void setMontoIgv(double montoIgv) {
        this.montoIgv = montoIgv;
    }

    public double getSaldoPagado() {
        return saldoPagado;
    }

    public void setSaldoPagado(double saldoPagado) {
        this.saldoPagado = saldoPagado;
    }

    public double getSaldoDisponible() {
        return saldoDisponible;
    }

    public void setSaldoDisponible(double saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
    }

    public LiquidacionGasto getLiquidacionGasto() {
        return liquidacionGasto;
    }

    public void setLiquidacionGasto(LiquidacionGasto liquidacionGasto) {
        this.liquidacionGasto = liquidacionGasto;
    }


    public Integer getFkIdtablaespTipoMoneda() {
		return fkIdtablaespTipoMoneda;
	}

	public void setFkIdtablaespTipoMoneda(Integer fkIdtablaespTipoMoneda) {
		this.fkIdtablaespTipoMoneda = fkIdtablaespTipoMoneda;
	}

    public void setDesembolsoID(Integer desembolsoID) {
		this.desembolsoID = desembolsoID;
	}

	public Integer getDesembolsoID() {
		return desembolsoID;
	}
	
    public String getTipoComprobantePagoDesc() {
		return tipoComprobantePagoDesc;
	}

	public void setTipoComprobantePagoDesc(String tipoComprobantePagoDesc) {
		this.tipoComprobantePagoDesc = tipoComprobantePagoDesc;
	}
	
	public String getBancoDesc() {
		return bancoDesc;
	}

	public void setBancoDesc(String bancoDesc) {
		this.bancoDesc = bancoDesc;
	}
	
	public String getChekeCobradoDesc() {
		return chekeCobradoDesc;
	}

	public void setChekeCobradoDesc(String chekeCobradoDesc) {
		this.chekeCobradoDesc = chekeCobradoDesc;
	}
	

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (pagoLiquidacionID != null ? pagoLiquidacionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PagoLiquidacion)) {
            return false;
        }
        PagoLiquidacion other = (PagoLiquidacion) object;
        if ((this.pagoLiquidacionID == null && other.pagoLiquidacionID != null) || (this.pagoLiquidacionID != null && !this.pagoLiquidacionID.equals(other.pagoLiquidacionID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.PagoLiquidacion[pagoLiquidacionID=" + pagoLiquidacionID + "]";
    }

	public void setTasaIgv(int tasaIgv) {
		this.tasaIgv = tasaIgv;
	}

	public int getTasaIgv() {
		return tasaIgv;
	}

	public void setFechaEmisionString(String fechaEmisionString) {
		this.fechaEmisionString = fechaEmisionString;
	}

	public String getFechaEmisionString() {
		return fechaEmisionString;
	}

	public Integer getCantidadDetallePagoLiquidacion() {
		return cantidadDetallePagoLiquidacion;
	}

	public void setCantidadDetallePagoLiquidacion(
			Integer cantidadDetallePagoLiquidacion) {
		this.cantidadDetallePagoLiquidacion = cantidadDetallePagoLiquidacion;
	}

	public void setDescripcionDesembolso(String descripcionDesembolso) {
		this.descripcionDesembolso = descripcionDesembolso;
	}

	public String getDescripcionDesembolso() {
		return descripcionDesembolso;
	}

	public void setDesembolso(Desembolso desembolso) {
		this.desembolso = desembolso;
	}

	public Desembolso getDesembolso() {
		return desembolso;
	}

}
