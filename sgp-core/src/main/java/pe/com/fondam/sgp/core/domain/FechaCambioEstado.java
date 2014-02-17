/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.fondam.sgp.core.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "fecha_cambio_estado")
public class FechaCambioEstado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     
    @Column(name = "Fecha_Cambio_Estado_ID")
    private Integer fechaCambioEstadoID;
     
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
     
    @Column(name = "fk_id_detalle_estado_cab_estado_eval")
    private int fkIdDetalleEstadoCabEstadoEval;
    @JoinColumn(name = "Dato_Usuario_ID", referencedColumnName = "Dato_Usuario_ID")
    @ManyToOne(optional = false)
    private DatoUsuario datoUsuario;
    @JoinColumn(name = "Propuesta_Transferencia_ID", referencedColumnName = "Propuesta_Transferencia_ID")
    @ManyToOne(optional = false)
    private PropuestaTransferencia propuestaTransferencia;
    @JoinColumn(name = "Informe_Final_ID", referencedColumnName = "Informe_Final_ID")
    @ManyToOne(optional = false)
    private InformeFinal informeFinal;
    @JoinColumn(name = "Informe_Visita_Campo_ID", referencedColumnName = "Informe_Visita_Campo_ID")
    @ManyToOne(optional = false)
    private InformeVisitaCampo informeVisitaCampo;
    @JoinColumn(name = "Reporte_Avance_ID", referencedColumnName = "Reporte_Avance_ID")
    @ManyToOne(optional = false)
    private ReporteAvance reporteAvance;
    @JoinColumn(name = "Detalle_Pago_Liquidacion_ID", referencedColumnName = "Detalle_Pago_Liquidacion_ID")
    @ManyToOne(optional = false)
    private DetallePagoLiquidacion detallePagoLiquidacion;
    @JoinColumn(name = "Liquidacion_Gasto_ID", referencedColumnName = "Liquidacion_Gasto_ID")
    @ManyToOne(optional = false)
    private LiquidacionGasto liquidacionGasto;
    @JoinColumn(name = "Desembolso_ID", referencedColumnName = "Desembolso_ID")
    @ManyToOne(optional = false)
    private Desembolso desembolso;
    @JoinColumn(name = "Dato_Plan_Operativo_ID", referencedColumnName = "Dato_Plan_Operativo_ID")
    @ManyToOne(optional = false)
    private DatoPlanOperativo datoPlanOperativo;
    @JoinColumn(name = "Institucion_ID", referencedColumnName = "Institucion_ID")
    @ManyToOne(optional = false)
    private Institucion institucion;
    @JoinColumn(name = "Dato_Proyecto_ID", referencedColumnName = "Dato_Proyecto_ID")
    @ManyToOne(optional = false)
    private DatoProyecto datoProyecto;

    public FechaCambioEstado() {
    }

    public FechaCambioEstado(Integer fechaCambioEstadoID) {
        this.fechaCambioEstadoID = fechaCambioEstadoID;
    }

    public FechaCambioEstado(Integer fechaCambioEstadoID, Date fecha, int fkIdDetalleEstadoCabEstadoEval) {
        this.fechaCambioEstadoID = fechaCambioEstadoID;
        this.fecha = fecha;
        this.fkIdDetalleEstadoCabEstadoEval = fkIdDetalleEstadoCabEstadoEval;
    }

    public Integer getFechaCambioEstadoID() {
        return fechaCambioEstadoID;
    }

    public void setFechaCambioEstadoID(Integer fechaCambioEstadoID) {
        this.fechaCambioEstadoID = fechaCambioEstadoID;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getFkIdDetalleEstadoCabEstadoEval() {
        return fkIdDetalleEstadoCabEstadoEval;
    }

    public void setFkIdDetalleEstadoCabEstadoEval(int fkIdDetalleEstadoCabEstadoEval) {
        this.fkIdDetalleEstadoCabEstadoEval = fkIdDetalleEstadoCabEstadoEval;
    }

    public DatoUsuario getDatoUsuario() {
        return datoUsuario;
    }

    public void setDatoUsuario(DatoUsuario datoUsuario) {
        this.datoUsuario = datoUsuario;
    }

    public PropuestaTransferencia getPropuestaTransferencia() {
        return propuestaTransferencia;
    }

    public void setPropuestaTransferencia(PropuestaTransferencia propuestaTransferencia) {
        this.propuestaTransferencia = propuestaTransferencia;
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

    public DetallePagoLiquidacion getDetallePagoLiquidacion() {
        return detallePagoLiquidacion;
    }

    public void setDetallePagoLiquidacion(DetallePagoLiquidacion detallePagoLiquidacion) {
        this.detallePagoLiquidacion = detallePagoLiquidacion;
    }

    public LiquidacionGasto getLiquidacionGasto() {
        return liquidacionGasto;
    }

    public void setLiquidacionGasto(LiquidacionGasto liquidacionGasto) {
        this.liquidacionGasto = liquidacionGasto;
    }

    public Desembolso getDesembolso() {
        return desembolso;
    }

    public void setDesembolso(Desembolso desembolso) {
        this.desembolso = desembolso;
    }

    public DatoPlanOperativo getDatoPlanOperativo() {
        return datoPlanOperativo;
    }

    public void setDatoPlanOperativo(DatoPlanOperativo datoPlanOperativo) {
        this.datoPlanOperativo = datoPlanOperativo;
    }

    public Institucion getInstitucion() {
        return institucion;
    }

    public void setInstitucion(Institucion institucion) {
        this.institucion = institucion;
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
        hash += (fechaCambioEstadoID != null ? fechaCambioEstadoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FechaCambioEstado)) {
            return false;
        }
        FechaCambioEstado other = (FechaCambioEstado) object;
        if ((this.fechaCambioEstadoID == null && other.fechaCambioEstadoID != null) || (this.fechaCambioEstadoID != null && !this.fechaCambioEstadoID.equals(other.fechaCambioEstadoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.FechaCambioEstado[fechaCambioEstadoID=" + fechaCambioEstadoID + "]";
    }

}
