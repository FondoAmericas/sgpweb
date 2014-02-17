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
@Table(name = "problema_solucion")

public class ProblemaSolucion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "Problema_Solucion_ID")
    private Integer problemaSolucionID;
    
    @Lob
    @Column(name = "problema")
    private String problema;
    
    @Lob
    @Column(name = "solucion")
    private String solucion;
    
    @Lob
    @Column(name = "resultado")
    private String resultado;
    
    @Lob
    @Column(name = "comentario")
    private String comentario;
    
    @Column(name = "problema_relevante_al_proy")
    private int problemaRelevanteAlProy;
    
    @JoinColumn(name = "Informe_Final_ID", referencedColumnName = "Informe_Final_ID")
    @ManyToOne
    private InformeFinal informeFinal;
    
    @JoinColumn(name = "Informe_Visita_Campo_ID", referencedColumnName = "Informe_Visita_Campo_ID")
    @ManyToOne
    private InformeVisitaCampo informeVisitaCampo;
    
    @JoinColumn(name = "Reporte_Avance_ID", referencedColumnName = "Reporte_Avance_ID")
    @ManyToOne
    private ReporteAvance reporteAvance;
    
    @JoinColumn(name = "Dato_Proyecto_ID", referencedColumnName = "Dato_Proyecto_ID")
    @ManyToOne
    private DatoProyecto datoProyecto;

    public ProblemaSolucion() {
    }

    public Integer getProblemaSolucionID() {
        return problemaSolucionID;
    }

    public void setProblemaSolucionID(Integer problemaSolucionID) {
        this.problemaSolucionID = problemaSolucionID;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getProblemaRelevanteAlProy() {
        return problemaRelevanteAlProy;
    }

    public void setProblemaRelevanteAlProy(int problemaRelevanteAlProy) {
        this.problemaRelevanteAlProy = problemaRelevanteAlProy;
    }

    public InformeFinal getInformeFinal() {
        return informeFinal;
    }

    public void setInformeFinal(InformeFinal informeFinal) {
        this.informeFinal = informeFinal;
    }

    public InformeVisitaCampo getInformeVisitaCampo() {
        return informeVisitaCampo;
    }

    public void setInformeVisitaCampo(InformeVisitaCampo informeVisitaCampo) {
        this.informeVisitaCampo = informeVisitaCampo;
    }

    public ReporteAvance getReporteAvance() {
        return reporteAvance;
    }

    public void setReporteAvance(ReporteAvance reporteAvance) {
        this.reporteAvance = reporteAvance;
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
        hash += (problemaSolucionID != null ? problemaSolucionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProblemaSolucion)) {
            return false;
        }
        ProblemaSolucion other = (ProblemaSolucion) object;
        if ((this.problemaSolucionID == null && other.problemaSolucionID != null) || (this.problemaSolucionID != null && !this.problemaSolucionID.equals(other.problemaSolucionID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.ProblemaSolucion[problemaSolucionID=" + problemaSolucionID + "]";
    }

}
