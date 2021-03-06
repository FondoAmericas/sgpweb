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
@Table(name = "rubro_especial")

public class RubroEspecial implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "Rubro_Especial_ID")
    private Integer rubroEspecialID;

    @Column(name = "fk_idCategoriaActividad")
    private Integer fkidCategoriaActividad;

    @Column(name = "fk_idRubroGenerico")
    private Integer fkidRubroGenerico;

    @Column(name = "fk_idPartidaGenerica")
    private Integer fkidPartidaGenerica;

    @Column(name = "fk_idPartidaEspecial")
    private Integer fkidPartidaEspecial;

    @Column(name = "tabla_que_lo_usa")
    private String tablaQueLoUsa;

    @Column(name = "Prefijo")
    private String prefijo;


    public RubroEspecial() {
    }

    public RubroEspecial(Integer rubroEspecialID) {
        this.rubroEspecialID = rubroEspecialID;
    }

    public RubroEspecial(Integer rubroEspecialID, int fkidCategoriaActividad, int fkidRubroGenerico, int fkidPartidaGenerica, int fkidPartidaEspecial, String tablaQueLoUsa, String prefijo) {
        this.rubroEspecialID = rubroEspecialID;
        this.fkidCategoriaActividad = fkidCategoriaActividad;
        this.fkidRubroGenerico = fkidRubroGenerico;
        this.fkidPartidaGenerica = fkidPartidaGenerica;
        this.fkidPartidaEspecial = fkidPartidaEspecial;
        this.tablaQueLoUsa = tablaQueLoUsa;
        this.prefijo = prefijo;
    }

    public Integer getRubroEspecialID() {
        return rubroEspecialID;
    }

    public void setRubroEspecialID(Integer rubroEspecialID) {
        this.rubroEspecialID = rubroEspecialID;
    }

    public Integer getFkidCategoriaActividad() {
        return fkidCategoriaActividad;
    }

    public void setFkidCategoriaActividad(Integer fkidCategoriaActividad) {
        this.fkidCategoriaActividad = fkidCategoriaActividad;
    }

    public Integer getFkidRubroGenerico() {
        return fkidRubroGenerico;
    }

    public void setFkidRubroGenerico(Integer fkidRubroGenerico) {
        this.fkidRubroGenerico = fkidRubroGenerico;
    }

    public Integer getFkidPartidaGenerica() {
        return fkidPartidaGenerica;
    }

    public void setFkidPartidaGenerica(Integer fkidPartidaGenerica) {
        this.fkidPartidaGenerica = fkidPartidaGenerica;
    }

    public Integer getFkidPartidaEspecial() {
        return fkidPartidaEspecial;
    }

    public void setFkidPartidaEspecial(Integer fkidPartidaEspecial) {
        this.fkidPartidaEspecial = fkidPartidaEspecial;
    }

    public String getTablaQueLoUsa() {
        return tablaQueLoUsa;
    }

    public void setTablaQueLoUsa(String tablaQueLoUsa) {
        this.tablaQueLoUsa = tablaQueLoUsa;
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rubroEspecialID != null ? rubroEspecialID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RubroEspecial)) {
            return false;
        }
        RubroEspecial other = (RubroEspecial) object;
        if ((this.rubroEspecialID == null && other.rubroEspecialID != null) || (this.rubroEspecialID != null && !this.rubroEspecialID.equals(other.rubroEspecialID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.RubroEspecial[rubroEspecialID=" + rubroEspecialID + "]";
    }

}
