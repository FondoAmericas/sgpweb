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
import javax.persistence.Transient;

@Entity
@Table(name = "indicador_marco_logico")
public class IndicadorMarcoLogico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     
    @Column(name = "Indicador_Marco_Logico_ID")
    private Integer indicadorMarcoLogicoID;
     
    @Column(name = "indicador")
    private String indicador;
     
	@Lob
	@Column(name = "metodo_calculo")
	private String metodoCalculo;

    @Lob
    @Column(name = "definicion_indicador")
    private String definicionIndicador;
     
    @Column(name = "fk_idtablaesp_unidad_medida")
    private Integer unidadMedida;
     
    @Lob
    @Column(name = "medio_verificacion")
    private String medioVerificacion;
     
    @Column(name = "situacion_actural")
    private int situacionActural;
     
    @Transient
    private String situacionActualDescripcion;
   
	@Column(name = "situacion_final")
    private int situacionFinal;
    
	@JoinColumn(name = "Marco_Logico_ID", referencedColumnName = "Marco_Logico_ID")
    @ManyToOne(optional = false)
    private MarcoLogico marcoLogico;

    public IndicadorMarcoLogico() {
    }

    public Integer getIndicadorMarcoLogicoID() {
        return indicadorMarcoLogicoID;
    }

    public void setIndicadorMarcoLogicoID(Integer indicadorMarcoLogicoID) {
        this.indicadorMarcoLogicoID = indicadorMarcoLogicoID;
    }

    public String getIndicador() {
        return indicador;
    }

    public void setIndicador(String indicador) {
        this.indicador = indicador;
    }

    public String getDefinicionIndicador() {
        return definicionIndicador;
    }

    public void setDefinicionIndicador(String definicionIndicador) {
        this.definicionIndicador = definicionIndicador;
    }

    public Integer getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(Integer unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public String getMedioVerificacion() {
        return medioVerificacion;
    }

    public void setMedioVerificacion(String medioVerificacion) {
        this.medioVerificacion = medioVerificacion;
    }

    public int getSituacionActural() {
        return situacionActural;
    }

    public void setSituacionActural(int situacionActural) {
        this.situacionActural = situacionActural;
    }

    public int getSituacionFinal() {
        return situacionFinal;
    }

    public void setSituacionFinal(int situacionFinal) {
        this.situacionFinal = situacionFinal;
    }

    public MarcoLogico getMarcoLogico() {
        return marcoLogico;
    }

    public void setMarcoLogico(MarcoLogico marcoLogico) {
        this.marcoLogico = marcoLogico;
    }

    
    public String getSituacionActualDescripcion() {
		return situacionActualDescripcion;
	}

	public void setSituacionActualDescripcion(String situacionActualDescripcion) {
		this.situacionActualDescripcion = situacionActualDescripcion;
	}

	public String getMetodoCalculo() {
		return metodoCalculo;
	}

	public void setMetodoCalculo(String metodoCalculo) {
		this.metodoCalculo = metodoCalculo;
	}
	
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (indicadorMarcoLogicoID != null ? indicadorMarcoLogicoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IndicadorMarcoLogico)) {
            return false;
        }
        IndicadorMarcoLogico other = (IndicadorMarcoLogico) object;
        if ((this.indicadorMarcoLogicoID == null && other.indicadorMarcoLogicoID != null) || (this.indicadorMarcoLogicoID != null && !this.indicadorMarcoLogicoID.equals(other.indicadorMarcoLogicoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.IndicadorMarcoLogico[indicadorMarcoLogicoID=" + indicadorMarcoLogicoID + "]";
    }

}
