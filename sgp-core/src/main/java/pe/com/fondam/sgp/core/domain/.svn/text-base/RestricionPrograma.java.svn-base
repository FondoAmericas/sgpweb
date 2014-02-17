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
@Table(name = "restricion_programa")

public class RestricionPrograma implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    @Column(name = "Restricion_Programa_ID")
    private Integer restricionProgramaID;
    
    @Column(name = "porcen_maximo")
    private int porcenMaximo;
    
    @Column(name = "porcen_minimo")
    private int porcenMinimo;
    
    @Column(name = "fk_idtablaesp_tipo_restriccion_prog")
    private int fkIdtablaespTipoRestriccionProg;
    @ManyToOne
    @JoinColumn(name = "Programa_ID", referencedColumnName = "Programa_ID")
    private Programa programa;
    @ManyToOne
    @JoinColumn(name = "Fuente_Financiadora_ID", referencedColumnName = "Fuente_Financiadora_ID")
    private FuenteFinanciadora fuenteFinanciadora;
    
    @ManyToOne
    @JoinColumn(name = "Dato_Proyecto_ID", referencedColumnName = "Dato_Proyecto_ID") 
    private DatoProyecto datoProyecto;


    public Integer getRestricionProgramaID() {
        return restricionProgramaID;
    }

    public void setRestricionProgramaID(Integer restricionProgramaID) {
        this.restricionProgramaID = restricionProgramaID;
    }

    public int getPorcenMaximo() {
        return porcenMaximo;
    }

    public void setPorcenMaximo(int porcenMaximo) {
        this.porcenMaximo = porcenMaximo;
    }

    public int getPorcenMinimo() {
        return porcenMinimo;
    }

    public void setPorcenMinimo(int porcenMinimo) {
        this.porcenMinimo = porcenMinimo;
    }

    public int getFkIdtablaespTipoRestriccionProg() {
        return fkIdtablaespTipoRestriccionProg;
    }

    public void setFkIdtablaespTipoRestriccionProg(int fkIdtablaespTipoRestriccionProg) {
        this.fkIdtablaespTipoRestriccionProg = fkIdtablaespTipoRestriccionProg;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public FuenteFinanciadora getFuenteFinanciadora() {
        return fuenteFinanciadora;
    }

    public void setFuenteFinanciadora(FuenteFinanciadora fuenteFinanciadora) {
        this.fuenteFinanciadora = fuenteFinanciadora;
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
        hash += (restricionProgramaID != null ? restricionProgramaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RestricionPrograma)) {
            return false;
        }
        RestricionPrograma other = (RestricionPrograma) object;
        if ((this.restricionProgramaID == null && other.restricionProgramaID != null) || (this.restricionProgramaID != null && !this.restricionProgramaID.equals(other.restricionProgramaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.RestricionPrograma[restricionProgramaID=" + restricionProgramaID + "]";
    }

}
