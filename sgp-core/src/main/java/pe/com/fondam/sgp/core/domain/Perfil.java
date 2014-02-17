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
@Table(name = "perfil")

public class Perfil implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    @Column(name = "Perfil_ID")
    private Integer perfilID;
    
    @Column(name = "monto_solicitado_fondam")
    private double montoSolicitadoFondam;
    
    @Column(name = "monto_contrapartida")
    private double montoContrapartida;
    
    @Column(name = "monto_cofinanciador")
    private double montoCofinanciador;
    

	@Column(name = "fk_iddetallestadocab_estperfil")
    private Integer fkIddetallestadocabEstperfil;
    

	@Column(name = "duracion_meses")
    private int duracionMeses;
    
    @ManyToOne
    @JoinColumn(name = "Dato_Proyecto_ID", referencedColumnName = "Dato_Proyecto_ID")
    private DatoProyecto datoProyecto;
  

    public Integer getPerfilID() {
        return perfilID;
    }

    public void setPerfilID(Integer perfilID) {
        this.perfilID = perfilID;
    }

    public double getMontoSolicitadoFondam() {
        return montoSolicitadoFondam;
    }

    public void setMontoSolicitadoFondam(double montoSolicitadoFondam) {
        this.montoSolicitadoFondam = montoSolicitadoFondam;
    }

    public double getMontoContrapartida() {
        return montoContrapartida;
    }

    public void setMontoContrapartida(double montoContrapartida) {
        this.montoContrapartida = montoContrapartida;
    }

    
    public double getMontoCofinanciador() {
		return montoCofinanciador;
	}

	public void setMontoCofinanciador(double montoCofinanciador) {
		this.montoCofinanciador = montoCofinanciador;
	}
	
    public int getDuracionMeses() {
        return duracionMeses;
    }

    public void setDuracionMeses(int duracionMeses) {
        this.duracionMeses = duracionMeses;
    }


    public DatoProyecto getDatoProyecto() {
        return datoProyecto;
    }

    public void setDatoProyecto(DatoProyecto datoProyecto) {
        this.datoProyecto = datoProyecto;
    }

    public Integer getFkIddetallestadocabEstperfil() {
		return fkIddetallestadocabEstperfil;
	}

	public void setFkIddetallestadocabEstperfil(Integer fkIddetallestadocabEstperfil) {
		this.fkIddetallestadocabEstperfil = fkIddetallestadocabEstperfil;
	}

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (perfilID != null ? perfilID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Perfil)) {
            return false;
        }
        Perfil other = (Perfil) object;
        if ((this.perfilID == null && other.perfilID != null) || (this.perfilID != null && !this.perfilID.equals(other.perfilID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.Perfil[perfilID=" + perfilID + "]";
    }

}
