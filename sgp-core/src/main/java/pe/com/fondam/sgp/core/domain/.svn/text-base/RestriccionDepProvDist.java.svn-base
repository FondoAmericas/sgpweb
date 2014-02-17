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
@Table(name = "restriccion_dep_prov_dist")

public class RestriccionDepProvDist implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "restriccion_dep_prov_dist_ID")
    private Integer restricciondepprovdistID;
    
    @ManyToOne
    @JoinColumn(name = "Dep_Prov_Dist_ID", referencedColumnName = "Dep_Prov_Dist_ID")
    private DepProvDist depProvDist;
    
    @ManyToOne
    @JoinColumn(name = "Programa_ID", referencedColumnName = "Programa_ID")
    private Programa programa;

   
    public Integer getRestricciondepprovdistID() {
        return restricciondepprovdistID;
    }

    public void setRestricciondepprovdistID(Integer restricciondepprovdistID) {
        this.restricciondepprovdistID = restricciondepprovdistID;
    }

    public DepProvDist getDepProvDist() {
        return depProvDist;
    }

    public void setDepProvDist(DepProvDist depProvDist) {
        this.depProvDist = depProvDist;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (restricciondepprovdistID != null ? restricciondepprovdistID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RestriccionDepProvDist)) {
            return false;
        }
        RestriccionDepProvDist other = (RestriccionDepProvDist) object;
        if ((this.restricciondepprovdistID == null && other.restricciondepprovdistID != null) || (this.restricciondepprovdistID != null && !this.restricciondepprovdistID.equals(other.restricciondepprovdistID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.RestriccionDepProvDist[restricciondepprovdistID=" + restricciondepprovdistID + "]";
    }

}
