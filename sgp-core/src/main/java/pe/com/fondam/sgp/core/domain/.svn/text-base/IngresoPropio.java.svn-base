/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ingreso_propio")

public class IngresoPropio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    @Column(name = "Ingreso_Propio_ID")
    private Integer ingresoPropioID;
    
    @Column(name = "fk_idtablaesp_tipo_comprobante_pago")
    private int fkIdtablaespTipoComprobantePago;
    
    @Column(name = "numero_comprobante")
    private String numeroComprobante;
    
    @Column(name = "fecha_emision")
    @Temporal(TemporalType.DATE)
    private Date fechaEmision;
    
    @Column(name = "ruc_comprador")
    private String rucComprador;
    
    @Column(name = "razon_social")
    private String razonSocial;
    
    @Lob
    @Column(name = "concepto")
    private String concepto;
    
    @Column(name = "fk_idtablaesp_tipo_moneda")
    private int fkIdtablaespTipoMoneda;
    
    @Column(name = "tasa_igv")
    private int tasaIgv;
    
    @Column(name = "igv")
    private double igv;
    
    @Column(name = "precio_sin_igv")
    private double precioSinIgv;
    
    @Column(name = "precio_total")
    private double precioTotal;

    @ManyToOne
    @JoinColumn(name = "Liquidacion_Gasto_ID", referencedColumnName = "Liquidacion_Gasto_ID")
    private LiquidacionGasto liquidacionGasto;
    
    @ManyToOne
    @JoinColumn(name = "Actividad_ID", referencedColumnName = "Actividad_ID")
    private Actividad actividad;
    
    @ManyToOne
    @JoinColumn(name = "Resultado_ID", referencedColumnName = "Resultado_ID")
    private Resultado resultado;

    public Integer getIngresoPropioID() {
        return ingresoPropioID;
    }

    public void setIngresoPropioID(Integer ingresoPropioID) {
        this.ingresoPropioID = ingresoPropioID;
    }

    public int getFkIdtablaespTipoComprobantePago() {
        return fkIdtablaespTipoComprobantePago;
    }

    public void setFkIdtablaespTipoComprobantePago(int fkIdtablaespTipoComprobantePago) {
        this.fkIdtablaespTipoComprobantePago = fkIdtablaespTipoComprobantePago;
    }

    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getRucComprador() {
        return rucComprador;
    }

    public void setRucComprador(String rucComprador) {
        this.rucComprador = rucComprador;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public int getFkIdtablaespTipoMoneda() {
        return fkIdtablaespTipoMoneda;
    }

    public void setFkIdtablaespTipoMoneda(int fkIdtablaespTipoMoneda) {
        this.fkIdtablaespTipoMoneda = fkIdtablaespTipoMoneda;
    }

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    public double getPrecioSinIgv() {
        return precioSinIgv;
    }

    public void setPrecioSinIgv(double precioSinIgv) {
        this.precioSinIgv = precioSinIgv;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public LiquidacionGasto getLiquidacionGasto() {
        return liquidacionGasto;
    }

    public void setLiquidacionGasto(LiquidacionGasto liquidacionGasto) {
        this.liquidacionGasto = liquidacionGasto;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public Resultado getResultado() {
        return resultado;
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ingresoPropioID != null ? ingresoPropioID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IngresoPropio)) {
            return false;
        }
        IngresoPropio other = (IngresoPropio) object;
        if ((this.ingresoPropioID == null && other.ingresoPropioID != null) || (this.ingresoPropioID != null && !this.ingresoPropioID.equals(other.ingresoPropioID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.IngresoPropio[ingresoPropioID=" + ingresoPropioID + "]";
    }

	public void setTasaIgv(int tasaIgv) {
		this.tasaIgv = tasaIgv;
	}

	public int getTasaIgv() {
		return tasaIgv;
	}

}
