package pe.com.fondam.sgp.core.domain;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
@Table(name = "restriccion_sub_area_tematica")

public class RestriccionSubAreaTematica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   
    @Column(name = "restriccion_sub_area_tematica_ID")
    private Integer restriccionsubareatematicaID;
    
    @ManyToOne
    @JoinColumn(name = "Programa_ID", referencedColumnName = "Programa_ID")
    private Programa programa;
    
    @ManyToOne
    @JoinColumn(name = "sub_Area_Tematica_ID", referencedColumnName = "Sub_Area_Tematica_ID")
    private SubAreaTematica subAreaTematica;

    public Integer getRestriccionsubareatematicaID() {
        return restriccionsubareatematicaID;
    }

    public void setRestriccionsubareatematicaID(Integer restriccionsubareatematicaID) {
        this.restriccionsubareatematicaID = restriccionsubareatematicaID;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public SubAreaTematica getSubAreaTematica() {
        return subAreaTematica;
    }

    public void setSubAreaTematica(SubAreaTematica subAreaTematica) {
        this.subAreaTematica = subAreaTematica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (restriccionsubareatematicaID != null ? restriccionsubareatematicaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RestriccionSubAreaTematica)) {
            return false;
        }
        RestriccionSubAreaTematica other = (RestriccionSubAreaTematica) object;
        if ((this.restriccionsubareatematicaID == null && other.restriccionsubareatematicaID != null) || (this.restriccionsubareatematicaID != null && !this.restriccionsubareatematicaID.equals(other.restriccionsubareatematicaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.RestriccionSubAreaTematica[restriccionsubareatematicaID=" + restriccionsubareatematicaID + "]";
    }

}
