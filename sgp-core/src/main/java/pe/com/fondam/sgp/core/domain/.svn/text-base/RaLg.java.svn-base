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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ra_lg")

public class RaLg implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    @Column(name = "RA_LG_ID")
    private Integer raLgId;
    
    @ManyToOne
    @JoinColumn(name = "Reporte_Avance_ID", referencedColumnName = "Reporte_Avance_ID")
    private ReporteAvance reporteAvance;
    
    @ManyToOne
    @JoinColumn(name = "Liquidacion_Gasto_ID", referencedColumnName = "Liquidacion_Gasto_ID")
    private LiquidacionGasto liquidacionGasto;



    public Integer getRaLgId() {
		return raLgId;
	}

	public void setRaLgId(Integer raLgId) {
        this.raLgId = raLgId;
    }

    public ReporteAvance getReporteAvance() {
        return reporteAvance;
    }

    public void setReporteAvance(ReporteAvance reporteAvance) {
        this.reporteAvance = reporteAvance;
    }

    public LiquidacionGasto getLiquidacionGasto() {
        return liquidacionGasto;
    }

    public void setLiquidacionGasto(LiquidacionGasto liquidacionGasto) {
        this.liquidacionGasto = liquidacionGasto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (raLgId != null ? raLgId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RaLg)) {
            return false;
        }
        RaLg other = (RaLg) object;
        if ((this.raLgId == null && other.raLgId != null) || (this.raLgId != null && !this.raLgId.equals(other.raLgId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.RaLg[raLgId=" + raLgId + "]";
    }

}
