/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.fondam.sgp.core.domain;

import java.io.Serializable;
import java.util.List;

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
@Table(name = "bien")
public class Bien implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     
    @Column(name = "Bien_ID")
    private Integer bienID;
     
    @Lob
    @Column(name = "descripcion_bien")
    private String descripcionBien;
     
    @Column(name = "fk_idtablaesp_tipo_bien")
    private int fkIdtablaespTipoBien;
    
    @Transient
    private String descripcionTipoBien;
     
    @Column(name = "fk_idtablaesp_unidad_medida")
    private int fkIdtablaespUnidadMedida;
    
    @Transient
    private String descripcionUnidadMedida;
     
    @Column(name = "cantidad_total")
    private int cantidadTotal;
     
    @Column(name = "cantidad_sin_transferir")
    private int cantidadSinTransferir;
     
    @Column(name = "fk_id_detalle_est_cab_estado_conservacion")
    private int fkIdDetalleEstCabEstadoConservacion;
     
    @Transient
    private String descripcionEstadoConservacion;
    
    @Lob
    @Column(name = "observacion")
    private String observacion;
     
    @Column(name = "localizacion_ubicacion")
    private String localizacionUbicacion;
     
    @Column(name = "costo_unitario")
    private double costoUnitario;
     
    @Column(name = "fk_idtablaesp_tipo_moneda")
    private int fkIdtablaespTipoMoneda;
     
    @Transient
    private String descripcionTipoMoneda;
    
    @Column(name = "costo_total")
    private double costoTotal;

    @Column(name = "Dato_Proyecto_ID")
    private int datoProyectoId;
    
    /*
    @ManyToOne
    @JoinColumn(name = "Propuesta_Transferencia_ID", referencedColumnName = "Propuesta_Transferencia_ID")
    private PropuestaTransferencia propuestaTransferencia;*/
    
    @JoinColumn(name = "Activo_ID", referencedColumnName = "Activo_ID")
    @ManyToOne
    private Activo activo;
   
    @Transient
    private List<RecursoUtilizadoBean> listRecursoUtilizadoBean;
    /*
    @JoinColumn(name = "Informe_Final_ID", referencedColumnName = "Informe_Final_ID")
    @ManyToOne
    private InformeFinal informeFinal;*/
    
    @Transient
    private List<PropuestaTransferenciaBien> listPropuestaTransferenciaBien;

    @Transient
    private Integer cantPropuestaTransferenciaBien;
    
    @Transient
    private Integer cantOrgBienTransferido;
    
    
   public Integer getBienID() {
        return bienID;
    }

    public void setBienID(Integer bienID) {
        this.bienID = bienID;
    }

    public String getDescripcionBien() {
        return descripcionBien;
    }

    public void setDescripcionBien(String descripcionBien) {
        this.descripcionBien = descripcionBien;
    }

    public int getFkIdtablaespTipoBien() {
        return fkIdtablaespTipoBien;
    }

    public void setFkIdtablaespTipoBien(int fkIdtablaespTipoBien) {
        this.fkIdtablaespTipoBien = fkIdtablaespTipoBien;
    }

    public int getFkIdtablaespUnidadMedida() {
        return fkIdtablaespUnidadMedida;
    }

    public void setFkIdtablaespUnidadMedida(int fkIdtablaespUnidadMedida) {
        this.fkIdtablaespUnidadMedida = fkIdtablaespUnidadMedida;
    }

    public int getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(int cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public int getCantidadSinTransferir() {
        return cantidadSinTransferir;
    }

    public void setCantidadSinTransferir(int cantidadSinTransferir) {
        this.cantidadSinTransferir = cantidadSinTransferir;
    }

    public int getFkIdDetalleEstCabEstadoConservacion() {
        return fkIdDetalleEstCabEstadoConservacion;
    }

    public void setFkIdDetalleEstCabEstadoConservacion(int fkIdDetalleEstCabEstadoConservacion) {
        this.fkIdDetalleEstCabEstadoConservacion = fkIdDetalleEstCabEstadoConservacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getLocalizacionUbicacion() {
        return localizacionUbicacion;
    }

    public void setLocalizacionUbicacion(String localizacionUbicacion) {
        this.localizacionUbicacion = localizacionUbicacion;
    }

    public double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public int getFkIdtablaespTipoMoneda() {
        return fkIdtablaespTipoMoneda;
    }

    public void setFkIdtablaespTipoMoneda(int fkIdtablaespTipoMoneda) {
        this.fkIdtablaespTipoMoneda = fkIdtablaespTipoMoneda;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }

    /*public PropuestaTransferencia getPropuestaTransferencia() {
        return propuestaTransferencia;
    }

    public void setPropuestaTransferencia(PropuestaTransferencia propuestaTransferencia) {
        this.propuestaTransferencia = propuestaTransferencia;
    }*/

    public Activo getActivo() {
        return activo;
    }

    public void setActivo(Activo activo) {
        this.activo = activo;
    }

    /*
    public InformeFinal getInformeFinal() {
        return informeFinal;
    }

    public void setInformeFinal(InformeFinal informeFinal) {
        this.informeFinal = informeFinal;
    }*/

    public String getDescripcionTipoBien() {
		return descripcionTipoBien;
	}

	public void setDescripcionTipoBien(String descripcionTipoBien) {
		this.descripcionTipoBien = descripcionTipoBien;
	}

	public String getDescripcionUnidadMedida() {
		return descripcionUnidadMedida;
	}

	public void setDescripcionUnidadMedida(String descripcionUnidadMedida) {
		this.descripcionUnidadMedida = descripcionUnidadMedida;
	}

	public String getDescripcionEstadoConservacion() {
		return descripcionEstadoConservacion;
	}

	public void setDescripcionEstadoConservacion(
			String descripcionEstadoConservacion) {
		this.descripcionEstadoConservacion = descripcionEstadoConservacion;
	}

	public String getDescripcionTipoMoneda() {
		return descripcionTipoMoneda;
	}

	public void setDescripcionTipoMoneda(String descripcionTipoMoneda) {
		this.descripcionTipoMoneda = descripcionTipoMoneda;
	}

	public int getDatoProyectoId() {
		return datoProyectoId;
	}

	public void setDatoProyectoId(int datoProyectoId) {
		this.datoProyectoId = datoProyectoId;
	}

	public void setListRecursoUtilizadoBean(List<RecursoUtilizadoBean> listRecursoUtilizadoBean) {
		this.listRecursoUtilizadoBean = listRecursoUtilizadoBean;
	}

	public List<RecursoUtilizadoBean> getListRecursoUtilizadoBean() {
		return listRecursoUtilizadoBean;
	}

	public void setListPropuestaTransferenciaBien(
			List<PropuestaTransferenciaBien> listPropuestaTransferenciaBien) {
		this.listPropuestaTransferenciaBien = listPropuestaTransferenciaBien;
	}

	public List<PropuestaTransferenciaBien> getListPropuestaTransferenciaBien() {
		return listPropuestaTransferenciaBien;
	}

	public Integer getCantPropuestaTransferenciaBien() {
		return cantPropuestaTransferenciaBien;
	}

	public void setCantPropuestaTransferenciaBien(
			Integer cantPropuestaTransferenciaBien) {
		this.cantPropuestaTransferenciaBien = cantPropuestaTransferenciaBien;
	}

	public Integer getCantOrgBienTransferido() {
		return cantOrgBienTransferido;
	}

	public void setCantOrgBienTransferido(Integer cantOrgBienTransferido) {
		this.cantOrgBienTransferido = cantOrgBienTransferido;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (bienID != null ? bienID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bien)) {
            return false;
        }
        Bien other = (Bien) object;
        if ((this.bienID == null && other.bienID != null) || (this.bienID != null && !this.bienID.equals(other.bienID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.Bien[bienID=" + bienID + "]";
    }

}
