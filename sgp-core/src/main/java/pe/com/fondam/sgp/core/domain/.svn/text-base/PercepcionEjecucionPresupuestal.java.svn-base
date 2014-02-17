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
@Table(name = "percepcion_ejecucion_presupuestal")

public class PercepcionEjecucionPresupuestal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
 
    @Column(name = "Percepcion_Ejecucion_Presupuestal_ID")
    private Integer percepcionEjecucionPresupuestalID;
 
    @Lob
    @Column(name = "percepcion_ejecucion")
    private String percepcionEjecucion;
    
    @JoinColumn(name = "Informe_Visita_Campo_ID", referencedColumnName = "Informe_Visita_Campo_ID")
    @ManyToOne(optional = false)
    private InformeVisitaCampo informeVisitaCampo;
    
    @JoinColumn(name = "Fuente_Financiadora_ID", referencedColumnName = "Fuente_Financiadora_ID")
    @ManyToOne
    private FuenteFinanciadora fuenteFinanciadora;

    public PercepcionEjecucionPresupuestal() {
    }

    public PercepcionEjecucionPresupuestal(Integer percepcionEjecucionPresupuestalID) {
        this.percepcionEjecucionPresupuestalID = percepcionEjecucionPresupuestalID;
    }

    public PercepcionEjecucionPresupuestal(Integer percepcionEjecucionPresupuestalID, String percepcionEjecucion) {
        this.percepcionEjecucionPresupuestalID = percepcionEjecucionPresupuestalID;
        this.percepcionEjecucion = percepcionEjecucion;
    }

    public Integer getPercepcionEjecucionPresupuestalID() {
        return percepcionEjecucionPresupuestalID;
    }

    public void setPercepcionEjecucionPresupuestalID(Integer percepcionEjecucionPresupuestalID) {
        this.percepcionEjecucionPresupuestalID = percepcionEjecucionPresupuestalID;
    }

    public String getPercepcionEjecucion() {
        return percepcionEjecucion;
    }

    public void setPercepcionEjecucion(String percepcionEjecucion) {
        this.percepcionEjecucion = percepcionEjecucion;
    }

    public InformeVisitaCampo getInformeVisitaCampo() {
        return informeVisitaCampo;
    }

    public void setInformeVisitaCampo(InformeVisitaCampo informeVisitaCampo) {
        this.informeVisitaCampo = informeVisitaCampo;
    }

    public FuenteFinanciadora getFuenteFinanciadora() {
        return fuenteFinanciadora;
    }

    public void setFuenteFinanciadora(FuenteFinanciadora fuenteFinanciadora) {
        this.fuenteFinanciadora = fuenteFinanciadora;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (percepcionEjecucionPresupuestalID != null ? percepcionEjecucionPresupuestalID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PercepcionEjecucionPresupuestal)) {
            return false;
        }
        PercepcionEjecucionPresupuestal other = (PercepcionEjecucionPresupuestal) object;
        if ((this.percepcionEjecucionPresupuestalID == null && other.percepcionEjecucionPresupuestalID != null) || (this.percepcionEjecucionPresupuestalID != null && !this.percepcionEjecucionPresupuestalID.equals(other.percepcionEjecucionPresupuestalID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.PercepcionEjecucionPresupuestal[percepcionEjecucionPresupuestalID=" + percepcionEjecucionPresupuestalID + "]";
    }

}
