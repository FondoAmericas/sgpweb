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
@Table(name = "imagen_o_archivo")
public class ImagenOArchivo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Imagen_O_Archivo_ID")
    private Integer imagenOArchivoID;
     
    @Lob
    @Column(name = "descripcion_doc_img")
    private String descripcionDocImg;
     
    @Column(name = "imagen")
    private byte[] imagen;
     
    @Column(name = "fk_idtablaesp_tipo_archivo")
    private int fkIdtablaespTipoArchivo;
    
    @ManyToOne
    @JoinColumn(name = "Informe_Visita_Campo_ID", referencedColumnName = "Informe_Visita_Campo_ID")
    private InformeVisitaCampo informeVisitaCampo;
    
    @ManyToOne
    @JoinColumn(name = "Reporte_Avance_ID", referencedColumnName = "Reporte_Avance_ID")
    private ReporteAvance reporteAvance;
   
    @ManyToOne
    @JoinColumn(name = "Pago_Liquidacion_ID", referencedColumnName = "Pago_Liquidacion_ID")
    private PagoLiquidacion pagoLiquidacion;
   
    @ManyToOne
    @JoinColumn(name = "Perfil_ID", referencedColumnName = "Perfil_ID")
    private Perfil perfil;
    
    @ManyToOne
    @JoinColumn(name = "Programa_ID", referencedColumnName = "Programa_ID")
    private Programa programa;
    
    @ManyToOne
    @JoinColumn(name = "Dato_Proyecto_ID", referencedColumnName = "Dato_Proyecto_ID")
    private DatoProyecto datoProyecto;
    
    @ManyToOne
    @JoinColumn(name = "Propuesta_Transferencia_ID", referencedColumnName = "Propuesta_Transferencia_ID")
    private PropuestaTransferencia propuestaTransferencia;
    
    @ManyToOne
    @JoinColumn(name = "Liquidacion_Gasto_ID", referencedColumnName = "Liquidacion_Gasto_ID")
    private LiquidacionGasto liquidacionGasto;
    
    @Column(name="numero_formulario")
    private int numeroFormulario;

    
    public Integer getImagenOArchivoID() {
        return imagenOArchivoID;
    }

    public void setImagenOArchivoID(Integer imagenOArchivoID) {
        this.imagenOArchivoID = imagenOArchivoID;
    }

    public String getDescripcionDocImg() {
        return descripcionDocImg;
    }

    public void setDescripcionDocImg(String descripcionDocImg) {
        this.descripcionDocImg = descripcionDocImg;
    }


    public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public int getFkIdtablaespTipoArchivo() {
        return fkIdtablaespTipoArchivo;
    }

    public void setFkIdtablaespTipoArchivo(int fkIdtablaespTipoArchivo) {
        this.fkIdtablaespTipoArchivo = fkIdtablaespTipoArchivo;
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

    public PagoLiquidacion getPagoLiquidacion() {
        return pagoLiquidacion;
    }

    public void setPagoLiquidacion(PagoLiquidacion pagoLiquidacion) {
        this.pagoLiquidacion = pagoLiquidacion;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public DatoProyecto getDatoProyecto() {
        return datoProyecto;
    }

    public void setDatoProyecto(DatoProyecto datoProyecto) {
        this.datoProyecto = datoProyecto;
    }

    public void setPropuestaTransferencia(PropuestaTransferencia propuestaTransferencia) {
		this.propuestaTransferencia = propuestaTransferencia;
	}

	public PropuestaTransferencia getPropuestaTransferencia() {
		return propuestaTransferencia;
	}

	public void setLiquidacionGasto(LiquidacionGasto liquidacionGasto) {
		this.liquidacionGasto = liquidacionGasto;
	}

	public LiquidacionGasto getLiquidacionGasto() {
		return liquidacionGasto;
	}

	public void setNumeroFormulario(int numeroFormulario) {
		this.numeroFormulario = numeroFormulario;
	}

	public int getNumeroFormulario() {
		return numeroFormulario;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (imagenOArchivoID != null ? imagenOArchivoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ImagenOArchivo)) {
            return false;
        }
        ImagenOArchivo other = (ImagenOArchivo) object;
        if ((this.imagenOArchivoID == null && other.imagenOArchivoID != null) || (this.imagenOArchivoID != null && !this.imagenOArchivoID.equals(other.imagenOArchivoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.ImagenOArchivo[imagenOArchivoID=" + imagenOArchivoID + "]";
    }

}
