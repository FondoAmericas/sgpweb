/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
@Table(name = "detalle_pago_liquidacion")
public class DetallePagoLiquidacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     
    @Column(name = "Detalle_Pago_Liquidacion_ID")
    private Integer detallePagoLiquidacionID;
     
    @Column(name = "fk_idtablaesp_unidad_medida")
    private int fkIdtablaespUnidadMedida;
     
    @Column(name = "cantidad")
    private int cantidad;
     
    @Column(name = "precio_unitario")
    private double precioUnitario;
     
    @Column(name = "precio_total")
    private double precioTotal;
     
    @Column(name = "fk_id_detalle_estado_cab_estado_pago_liq")
    private int fkIdDetalleEstadoCabEstadoPagoLiq;
   
    @Lob
    @Column(name = "concepto")
    private String concepto;
    
    @ManyToOne
    @JoinColumn(name = "Activo_ID", referencedColumnName = "Activo_ID")
    private Activo activo;
    
    @ManyToOne
    @JoinColumn(name = "Pago_Liquidacion_ID", referencedColumnName = "Pago_Liquidacion_ID")
    private PagoLiquidacion pagoLiquidacion;
    
    @Transient
    private List<ActividadDetallePago> lstActividadDetallePago;

    @Transient
    private String unidadMedidaDesc;  

	@Transient
    private String estadoPagoLiquidacionDesc;
    
	@Transient
    private String activoDesc;
    
	@Transient
	private double montoActividadDetallePagoDeclarado;

	
	public String getActivoDesc() {
		return activoDesc;
	}

	public void setActivoDesc(String activoDesc) {
		this.activoDesc = activoDesc;
	}

	public Integer getDetallePagoLiquidacionID() {
        return detallePagoLiquidacionID;
    }

    public void setDetallePagoLiquidacionID(Integer detallePagoLiquidacionID) {
        this.detallePagoLiquidacionID = detallePagoLiquidacionID;
    }

    public int getFkIdtablaespUnidadMedida() {
        return fkIdtablaespUnidadMedida;
    }

    public void setFkIdtablaespUnidadMedida(int fkIdtablaespUnidadMedida) {
        this.fkIdtablaespUnidadMedida = fkIdtablaespUnidadMedida;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public int getFkIdDetalleEstadoCabEstadoPagoLiq() {
        return fkIdDetalleEstadoCabEstadoPagoLiq;
    }

    public void setFkIdDetalleEstadoCabEstadoPagoLiq(int fkIdDetalleEstadoCabEstadoPagoLiq) {
        this.fkIdDetalleEstadoCabEstadoPagoLiq = fkIdDetalleEstadoCabEstadoPagoLiq;
    }

    public Activo getActivo() {
        return activo;
    }

    public void setActivo(Activo activo) {
        this.activo = activo;
    }

    public PagoLiquidacion getPagoLiquidacion() {
        return pagoLiquidacion;
    }

    public void setPagoLiquidacion(PagoLiquidacion pagoLiquidacion) {
        this.pagoLiquidacion = pagoLiquidacion;
    }
    
    public List<ActividadDetallePago> getLstActividadDetallePago() {
		return lstActividadDetallePago;
	}

	public void setLstActividadDetallePago(
			List<ActividadDetallePago> lstActividadDetallePago) {
		this.lstActividadDetallePago = lstActividadDetallePago;
	}
    
    public String getEstadoPagoLiquidacionDesc() {
		return estadoPagoLiquidacionDesc;
	}

	public void setEstadoPagoLiquidacionDesc(String estadoPagoLiquidacionDesc) {
		this.estadoPagoLiquidacionDesc = estadoPagoLiquidacionDesc;
	}
	
    public String getUnidadMedidaDesc() {
		return unidadMedidaDesc;
	}

	public void setUnidadMedidaDesc(String unidadMedidaDesc) {
		this.unidadMedidaDesc = unidadMedidaDesc;
	}
	
	

    public void setMontoActividadDetallePagoDeclarado(
			double montoActividadDetallePagoDeclarado) {
		this.montoActividadDetallePagoDeclarado = montoActividadDetallePagoDeclarado;
	}

	public double getMontoActividadDetallePagoDeclarado() {
		return montoActividadDetallePagoDeclarado;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (detallePagoLiquidacionID != null ? detallePagoLiquidacionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallePagoLiquidacion)) {
            return false;
        }
        DetallePagoLiquidacion other = (DetallePagoLiquidacion) object;
        if ((this.detallePagoLiquidacionID == null && other.detallePagoLiquidacionID != null) || (this.detallePagoLiquidacionID != null && !this.detallePagoLiquidacionID.equals(other.detallePagoLiquidacionID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.DetallePagoLiquidacion[detallePagoLiquidacionID=" + detallePagoLiquidacionID + "]";
    }

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getConcepto() {
		return concepto;
	}

}
