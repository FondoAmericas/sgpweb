package pe.com.fondam.sgp.core.bean;

import java.io.Serializable;

import pe.com.fondam.sgp.core.domain.InformeFinal;
import pe.com.fondam.sgp.core.domain.InformeVisitaCampo;
import pe.com.fondam.sgp.core.domain.ReporteAvance;

public class ProblemaSolucionBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2227494350013631854L;
	private Integer problemaSolucionID;
    private String problema;
    private String solucion;
    private String resultado;
    private String comentario;    
    private int problemaRelevanteAlProy;
    private InformeFinal informeFinal;
    private InformeVisitaCampo informeVisitaCampo;
    private ReporteAvance reporteAvance;

    public ProblemaSolucionBean(){
    	
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
    
    
    
}
