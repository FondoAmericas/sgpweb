/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.fondam.sgp.core.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import pe.com.fondam.sgp.core.domain.DetallePagoLiquidacion;


public class PagoLiquidacionBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2058207241832341086L;

	private Integer pagoLiquidacionID;
    
    private String numeroCheque;
    
    private String tipoComprobantePago;
    
    private String numeroDocumento;
    
    private Date fechaEmision;
    
    private String rucProveedor;
    
    private String razonSocial;
    
    private double montoTotal;
    
    private double saldoPagado;
    
    private double saldoDisponible;
    
    private  ArrayList<DetallePagoLiquidacion> listaDetallePagoLiquidacion;

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

	public String getTipoComprobantePago() {
		return tipoComprobantePago;
	}

	public void setTipoComprobantePago(String tipoComprobantePago) {
		this.tipoComprobantePago = tipoComprobantePago;
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

	public ArrayList<DetallePagoLiquidacion> getListaDetallePagoLiquidacion() {
		return listaDetallePagoLiquidacion;
	}

	public void setListaDetallePagoLiquidacion(
			ArrayList<DetallePagoLiquidacion> listaDetallePagoLiquidacion) {
		this.listaDetallePagoLiquidacion = listaDetallePagoLiquidacion;
	} 
    
    
}
