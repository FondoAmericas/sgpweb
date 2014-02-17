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
@Table(name = "marco_logico")

public class MarcoLogico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    @Column(name = "Marco_Logico_ID")
    private Integer marcoLogicoID;
    
    @Lob
    @Column(name = "resumen_ejecutivo")
    private String resumenEjecutivo;
    
    @Lob
    @Column(name = "fin_descrip")
    private String finDescrip;
    
    @Lob
    @Column(name = "fin_supuesto")
    private String finSupuesto;
    
    @Lob
    @Column(name = "proposito_descrip")
    private String propositoDescrip;
    
    @Lob
    @Column(name = "proposito_supuesto")
    private String propositoSupuesto;
   
    @ManyToOne
    @JoinColumn(name = "Dato_Proyecto_ID", referencedColumnName = "Dato_Proyecto_ID")
    private DatoProyecto datoProyecto;

    public Integer getMarcoLogicoID() {
        return marcoLogicoID;
    }

    public void setMarcoLogicoID(Integer marcoLogicoID) {
        this.marcoLogicoID = marcoLogicoID;
    }

    public String getResumenEjecutivo() {
        return resumenEjecutivo;
    }

    public void setResumenEjecutivo(String resumenEjecutivo) {
        this.resumenEjecutivo = resumenEjecutivo;
    }

    public String getFinDescrip() {
        return finDescrip;
    }

    public void setFinDescrip(String finDescrip) {
        this.finDescrip = finDescrip;
    }

    public String getFinSupuesto() {
        return finSupuesto;
    }

    public void setFinSupuesto(String finSupuesto) {
        this.finSupuesto = finSupuesto;
    }

    public String getPropositoDescrip() {
        return propositoDescrip;
    }

    public void setPropositoDescrip(String propositoDescrip) {
        this.propositoDescrip = propositoDescrip;
    }

    public String getPropositoSupuesto() {
        return propositoSupuesto;
    }

    public void setPropositoSupuesto(String propositoSupuesto) {
        this.propositoSupuesto = propositoSupuesto;
    }


    public DatoProyecto getDatoProyecto() {
        return datoProyecto;
    }

    public void setDatoProyecto(DatoProyecto datoProyecto) {
        this.datoProyecto = datoProyecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (marcoLogicoID != null ? marcoLogicoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MarcoLogico)) {
            return false;
        }
        MarcoLogico other = (MarcoLogico) object;
        if ((this.marcoLogicoID == null && other.marcoLogicoID != null) || (this.marcoLogicoID != null && !this.marcoLogicoID.equals(other.marcoLogicoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.MarcoLogico[marcoLogicoID=" + marcoLogicoID + "]";
    }

}
