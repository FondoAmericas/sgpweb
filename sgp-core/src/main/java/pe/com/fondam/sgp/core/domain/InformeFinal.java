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
@Table(name = "informe_final")
public class InformeFinal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     
    @Column(name = "Informe_Final_ID")
    private Integer informeFinalID;
     
    @Lob
    @Column(name = "resultado_proyecto")
    private String resultadoProyecto;
     
    @Column(name = "fk_id_detalle_estado_cab_est_inf_final")
    private int fkIdDetalleEstadoCabEstInfFinal;
    
    @Transient
    private String descripcionEstadoInformeFinal;
    
    @JoinColumn(name = "Dato_Proyecto_ID", referencedColumnName = "Dato_Proyecto_ID")
    @ManyToOne(optional = false)
    private DatoProyecto datoProyecto;

	@Transient
	private Integer cantObservacionesRelevantes;

    public InformeFinal() {
    }

    public InformeFinal(Integer informeFinalID) {
        this.informeFinalID = informeFinalID;
    }

    public InformeFinal(Integer informeFinalID, String resultadoProyecto, int fkIdDetalleEstadoCabEstInfFinal) {
        this.informeFinalID = informeFinalID;
        this.resultadoProyecto = resultadoProyecto;
        this.fkIdDetalleEstadoCabEstInfFinal = fkIdDetalleEstadoCabEstInfFinal;
    }

    public Integer getInformeFinalID() {
        return informeFinalID;
    }

    public void setInformeFinalID(Integer informeFinalID) {
        this.informeFinalID = informeFinalID;
    }

    public String getResultadoProyecto() {
        return resultadoProyecto;
    }

    public void setResultadoProyecto(String resultadoProyecto) {
        this.resultadoProyecto = resultadoProyecto;
    }

    public int getFkIdDetalleEstadoCabEstInfFinal() {
        return fkIdDetalleEstadoCabEstInfFinal;
    }

    public void setFkIdDetalleEstadoCabEstInfFinal(int fkIdDetalleEstadoCabEstInfFinal) {
        this.fkIdDetalleEstadoCabEstInfFinal = fkIdDetalleEstadoCabEstInfFinal;
    }

    public DatoProyecto getDatoProyecto() {
        return datoProyecto;
    }

    public void setDatoProyecto(DatoProyecto datoProyecto) {
        this.datoProyecto = datoProyecto;
    }

    public Integer getCantObservacionesRelevantes() {
		return cantObservacionesRelevantes;
	}

	public void setCantObservacionesRelevantes(Integer cantObservacionesRelevantes) {
		this.cantObservacionesRelevantes = cantObservacionesRelevantes;
	}

	public String getDescripcionEstadoInformeFinal() {
		return descripcionEstadoInformeFinal;
	}

	public void setDescripcionEstadoInformeFinal(
			String descripcionEstadoInformeFinal) {
		this.descripcionEstadoInformeFinal = descripcionEstadoInformeFinal;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (informeFinalID != null ? informeFinalID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InformeFinal)) {
            return false;
        }
        InformeFinal other = (InformeFinal) object;
        if ((this.informeFinalID == null && other.informeFinalID != null) || (this.informeFinalID != null && !this.informeFinalID.equals(other.informeFinalID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.InformeFinal[informeFinalID=" + informeFinalID + "]";
    }

}
