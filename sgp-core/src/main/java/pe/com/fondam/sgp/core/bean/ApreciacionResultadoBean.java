package pe.com.fondam.sgp.core.bean;

import java.io.Serializable;

import pe.com.fondam.sgp.core.domain.ReporteAvance;

public class ApreciacionResultadoBean implements Serializable{

	    /**
	 * 
	 */
	private static final long serialVersionUID = -438004184385938487L;
		private Integer apreciacionResultadoID;
	    private int fkIdtablaespApreciacionResultadoRa;
	    private String descripcionTipoApreciacionResultadoRa;
	    private String comentario;
	    private ReporteAvance reporteAvance;
	    
	    public ApreciacionResultadoBean() {
			
		}

		public Integer getApreciacionResultadoID() {
			return apreciacionResultadoID;
		}

		public void setApreciacionResultadoID(Integer apreciacionResultadoID) {
			this.apreciacionResultadoID = apreciacionResultadoID;
		}

		public int getFkIdtablaespApreciacionResultadoRa() {
			return fkIdtablaespApreciacionResultadoRa;
		}

		public void setFkIdtablaespApreciacionResultadoRa(
				int fkIdtablaespApreciacionResultadoRa) {
			this.fkIdtablaespApreciacionResultadoRa = fkIdtablaespApreciacionResultadoRa;
		}

		public String getDescripcionTipoApreciacionResultadoRa() {
			return descripcionTipoApreciacionResultadoRa;
		}

		public void setDescripcionTipoApreciacionResultadoRa(
				String descripcionTipoApreciacionResultadoRa) {
			this.descripcionTipoApreciacionResultadoRa = descripcionTipoApreciacionResultadoRa;
		}

		public String getComentario() {
			return comentario;
		}

		public void setComentario(String comentario) {
			this.comentario = comentario;
		}

		public ReporteAvance getReporteAvance() {
			return reporteAvance;
		}

		public void setReporteAvance(ReporteAvance reporteAvance) {
			this.reporteAvance = reporteAvance;
		}
	    
}
