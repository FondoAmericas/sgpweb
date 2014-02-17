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
import javax.persistence.Transient;

@Entity
@Table(name = "tmp_fuente_financiadora")
public class TmpFuenteFinanciadora implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 7965467988710604539L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    @Column(name = "TMP_Fuente_Financiadora_ID")
    private Integer tMPFuenteFinanciadoraID;
    
    @Column(name = "define_si_es_ejecutor")
    private int defineSiEsEjecutor;
    
    @Column(name = "fk_idtablaesp_tipo_fuente_financiadora")
    private int fkIdtablaespTipoFuenteFinanciadora;
    
    @Column(name = "monto_financiado")
    private double montoFinanciado;
    
    @Column(name = "fk_idtablaesp_tipo_moneda")
    private int fkIdtablaespTipoMoneda;
    
    @JoinColumn(name = "TMP_Institucion_ID", referencedColumnName = "TMP_Institucion_ID")
    @ManyToOne
    private TmpInstitucion tMPInstitucion;
    
    @Transient
    private String nombreInstitucion;
    
    @JoinColumn(name = "TMP_Dato_Proyecto_ID", referencedColumnName = "TMP_Dato_Proyecto_ID")
    @ManyToOne(optional = false)
    private TmpDatoProyecto tMPDatoProyecto;

    public Integer getTMPFuenteFinanciadoraID() {
        return tMPFuenteFinanciadoraID;
    }

    public void setTMPFuenteFinanciadoraID(Integer tMPFuenteFinanciadoraID) {
        this.tMPFuenteFinanciadoraID = tMPFuenteFinanciadoraID;
    }

    public int getDefineSiEsEjecutor() {
        return defineSiEsEjecutor;
    }

    public void setDefineSiEsEjecutor(int defineSiEsEjecutor) {
        this.defineSiEsEjecutor = defineSiEsEjecutor;
    }

    public int getFkIdtablaespTipoFuenteFinanciadora() {
        return fkIdtablaespTipoFuenteFinanciadora;
    }

    public void setFkIdtablaespTipoFuenteFinanciadora(int fkIdtablaespTipoFuenteFinanciadora) {
        this.fkIdtablaespTipoFuenteFinanciadora = fkIdtablaespTipoFuenteFinanciadora;
    }

    public double getMontoFinanciado() {
        return montoFinanciado;
    }

    public void setMontoFinanciado(double montoFinanciado) {
        this.montoFinanciado = montoFinanciado;
    }

    public int getFkIdtablaespTipoMoneda() {
        return fkIdtablaespTipoMoneda;
    }

    public void setFkIdtablaespTipoMoneda(int fkIdtablaespTipoMoneda) {
        this.fkIdtablaespTipoMoneda = fkIdtablaespTipoMoneda;
    }

    public TmpInstitucion getTMPInstitucion() {
        return tMPInstitucion;
    }

    public void setTMPInstitucion(TmpInstitucion tMPInstitucion) {
        this.tMPInstitucion = tMPInstitucion;
    }

    public TmpDatoProyecto getTMPDatoProyecto() {
        return tMPDatoProyecto;
    }

    public void setTMPDatoProyectoID(TmpDatoProyecto tMPDatoProyecto) {
        this.tMPDatoProyecto = tMPDatoProyecto;
    }

    public void setNombreInstitucion(String nombreInstitucion) {
		this.nombreInstitucion = nombreInstitucion;
	}

	public String getNombreInstitucion() {
		return nombreInstitucion;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (tMPFuenteFinanciadoraID != null ? tMPFuenteFinanciadoraID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmpFuenteFinanciadora)) {
            return false;
        }
        TmpFuenteFinanciadora other = (TmpFuenteFinanciadora) object;
        if ((this.tMPFuenteFinanciadoraID == null && other.tMPFuenteFinanciadoraID != null) || (this.tMPFuenteFinanciadoraID != null && !this.tMPFuenteFinanciadoraID.equals(other.tMPFuenteFinanciadoraID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.TmpFuenteFinanciadora[ tMPFuenteFinanciadoraID=" + tMPFuenteFinanciadoraID + " ]";
    }
    
}
