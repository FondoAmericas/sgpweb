/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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

@Entity
@Table(name = "compromiso_pago")
public class CompromisoPago implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     
    @Column(name = "Compromiso_Pago_ID")
    private Integer compromisoPagoID;
     
    @Column(name = "monto_compromiso")
    private double montoCompromiso;
     
    @Lob
    @Column(name = "observacion")
    private String observacion;
    
    @ManyToOne
    @JoinColumn(name = "Liquidacion_Gasto_ID", referencedColumnName = "Liquidacion_Gasto_ID")
    private LiquidacionGasto liquidacionGasto;
    
    @ManyToOne
    @JoinColumn(name = "Cronograma_Costo_Actividad_ID", referencedColumnName = "Cronograma_Costo_Actividad_ID")
    private CronogramaCostoActividad cronogramaCostoActividad;

    public void setCompromisoPagoID(Integer compromisoPagoID) {
        this.compromisoPagoID = compromisoPagoID;
    }

    public Integer getCompromisoPagoID() {
		return compromisoPagoID;
	}
    public double getMontoCompromiso() {
        return montoCompromiso;
    }

    public void setMontoCompromiso(double montoCompromiso) {
        this.montoCompromiso = montoCompromiso;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public LiquidacionGasto getLiquidacionGasto() {
        return liquidacionGasto;
    }

    public void setLiquidacionGasto(LiquidacionGasto liquidacionGasto) {
        this.liquidacionGasto = liquidacionGasto;
    }

    public CronogramaCostoActividad getCronogramaCostoActividad() {
        return cronogramaCostoActividad;
    }

    public void setCronogramaCostoActividad(CronogramaCostoActividad cronogramaCostoActividad) {
        this.cronogramaCostoActividad = cronogramaCostoActividad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (compromisoPagoID != null ? compromisoPagoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompromisoPago)) {
            return false;
        }
        CompromisoPago other = (CompromisoPago) object;
        if ((this.compromisoPagoID == null && other.compromisoPagoID != null) || (this.compromisoPagoID != null && !this.compromisoPagoID.equals(other.compromisoPagoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.CompromisoPago[compromisoPagoID=" + compromisoPagoID + "]";
    }

}
