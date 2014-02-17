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
import javax.persistence.Table;

@Entity
@Table(name = "tipo_periodo")
public class TipoPeriodo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Tipo_Periodo_ID")
    private Integer tipoPeriodoID;
   
    @Column(name = "descrip_periodo")
    private String descripPeriodo;
   
    @Column(name = "unidad_periodo")
    private String unidadPeriodo;
   
    @Column(name = "numero_meses")
    private int numeroMeses;
   
    @Column(name = "estado_eliminado")
    private int estadoEliminado;



    public Integer getTipoPeriodoID() {
        return tipoPeriodoID;
    }

    public void setTipoPeriodoID(Integer tipoPeriodoID) {
        this.tipoPeriodoID = tipoPeriodoID;
    }

    public String getDescripPeriodo() {
        return descripPeriodo;
    }

    public void setDescripPeriodo(String descripPeriodo) {
        this.descripPeriodo = descripPeriodo;
    }

    public String getUnidadPeriodo() {
        return unidadPeriodo;
    }

    public void setUnidadPeriodo(String unidadPeriodo) {
        this.unidadPeriodo = unidadPeriodo;
    }

    public int getNumeroMeses() {
        return numeroMeses;
    }

    public void setNumeroMeses(int numeroMeses) {
        this.numeroMeses = numeroMeses;
    }

    public int getEstadoEliminado() {
        return estadoEliminado;
    }

    public void setEstadoEliminado(int estadoEliminado) {
        this.estadoEliminado = estadoEliminado;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoPeriodoID != null ? tipoPeriodoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPeriodo)) {
            return false;
        }
        TipoPeriodo other = (TipoPeriodo) object;
        if ((this.tipoPeriodoID == null && other.tipoPeriodoID != null) || (this.tipoPeriodoID != null && !this.tipoPeriodoID.equals(other.tipoPeriodoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.TipoPeriodo[tipoPeriodoID=" + tipoPeriodoID + "]";
    }

}
