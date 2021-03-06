/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.fondam.sgp.core.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tmp_perfil")
public class TmpPerfil implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -8681180878449896900L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    @Column(name = "TMP_Perfil_ID")
    private Integer tMPPerfilID;
    
    @Column(name = "monto_solicitado_fondam")
    private Double montoSolicitadoFondam;
    
    @Column(name = "monto_contrapartida")
    private Double montoContrapartida;
    
    @Column(name = "monto_cofinanciador")
    private Double montoCofinanciador;
    
    @Column(name = "fk_iddetallestadocab_estperfil")
    private Integer fkIddetallestadocabEstperfil;
    
    @Column(name = "duracion_meses")
    private int duracionMeses;
    
    @ManyToOne
    @JoinColumn(name = "TMP_Dato_Proyecto_ID", referencedColumnName = "TMP_Dato_Proyecto_ID")
    private TmpDatoProyecto tMPDatoProyecto;
    
    @Transient
    private List<TmpFuenteFinanciadora> listTmpFuenteFinanciadoraCofinanciador = new ArrayList<TmpFuenteFinanciadora>();
    
    @Transient
    private List<TmpFuenteFinanciadora> listTmpFuenteFinanciadoraContrapartida = new ArrayList<TmpFuenteFinanciadora>();
    
    
    public Integer getTMPPerfilID() {
        return tMPPerfilID;
    }

    public void setTMPPerfilID(Integer tMPPerfilID) {
        this.tMPPerfilID = tMPPerfilID;
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

    public Double getMontoCofinanciador() {
        return montoCofinanciador;
    }

    public void setMontoCofinanciador(Double montoCofinanciador) {
        this.montoCofinanciador = montoCofinanciador;
    }

    public Integer getFkIddetallestadocabEstperfil() {
        return fkIddetallestadocabEstperfil;
    }

    public void setFkIddetallestadocabEstperfil(Integer fkIddetallestadocabEstperfil) {
        this.fkIddetallestadocabEstperfil = fkIddetallestadocabEstperfil;
    }

    public int getDuracionMeses() {
        return duracionMeses;
    }

    public void setDuracionMeses(int duracionMeses) {
        this.duracionMeses = duracionMeses;
    }

    public TmpDatoProyecto getTMPDatoProyecto() {
        return tMPDatoProyecto;
    }

    public void setTMPDatoProyectoID(TmpDatoProyecto tMPDatoProyecto) {
        this.tMPDatoProyecto = tMPDatoProyecto;
    }

    public void setListTmpFuenteFinanciadoraCofinanciador(
			List<TmpFuenteFinanciadora> listTmpFuenteFinanciadoraCofinanciador) {
		this.listTmpFuenteFinanciadoraCofinanciador = listTmpFuenteFinanciadoraCofinanciador;
	}

	public List<TmpFuenteFinanciadora> getListTmpFuenteFinanciadoraCofinanciador() {
		return listTmpFuenteFinanciadoraCofinanciador;
	}

	public void setListTmpFuenteFinanciadoraContrapartida(
			List<TmpFuenteFinanciadora> listTmpFuenteFinanciadoraContrapartida) {
		this.listTmpFuenteFinanciadoraContrapartida = listTmpFuenteFinanciadoraContrapartida;
	}

	public List<TmpFuenteFinanciadora> getListTmpFuenteFinanciadoraContrapartida() {
		return listTmpFuenteFinanciadoraContrapartida;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (tMPPerfilID != null ? tMPPerfilID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmpPerfil)) {
            return false;
        }
        TmpPerfil other = (TmpPerfil) object;
        if ((this.tMPPerfilID == null && other.tMPPerfilID != null) || (this.tMPPerfilID != null && !this.tMPPerfilID.equals(other.tMPPerfilID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.TmpPerfil[ tMPPerfilID=" + tMPPerfilID + " ]";
    }
    
}
