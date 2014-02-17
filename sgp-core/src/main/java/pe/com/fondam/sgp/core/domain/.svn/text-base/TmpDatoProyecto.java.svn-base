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
@Table(name = "tmp_dato_proyecto")
public class TmpDatoProyecto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    @Column(name = "TMP_Dato_Proyecto_ID")
    private Integer tMPDatoProyectoID;
    
    @Column(name = "duracion_proyecto")
    private int duracionProyecto;
    
    @Column(name = "cantidad_Periodo")
    private int cantidadPeriodo;
    
    @Lob
    @Column(name = "nombre_proyecto")
    private String nombreProyecto;
    
    @Column(name = "numero_orden_dato_proyecto")
    private int numeroOrdenDatoProyecto;
    
    @Column(name = "codigo_proyecto")
    private String codigoProyecto;
    
    @Column(name = "fk_iddetallestadocab_estproy")
    private int fkIddetallestadocabEstproy;
    
    @Column(name = "flag_paso_tabla_normal")
    private Integer flagPasoTablaNormal;
    
    public Integer gettMPDatoProyectoID() {
		return tMPDatoProyectoID;
	}

	public void settMPDatoProyectoID(Integer tMPDatoProyectoID) {
		this.tMPDatoProyectoID = tMPDatoProyectoID;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	@ManyToOne
    @JoinColumn(name = "Programa_ID", referencedColumnName = "Programa_ID")
    private Programa programa;
    
    @ManyToOne
    @JoinColumn(name = "Sub_Area_Tematica_ID", referencedColumnName = "Sub_Area_Tematica_ID")
    private SubAreaTematica subAreaTematica;
    
    public Integer getTMPDatoProyectoID() {
        return tMPDatoProyectoID;
    }

    public void setTMPDatoProyectoID(Integer tMPDatoProyectoID) {
        this.tMPDatoProyectoID = tMPDatoProyectoID;
    }

    public int getDuracionProyecto() {
        return duracionProyecto;
    }

    public void setDuracionProyecto(int duracionProyecto) {
        this.duracionProyecto = duracionProyecto;
    }

    public int getCantidadPeriodo() {
        return cantidadPeriodo;
    }

    public void setCantidadPeriodo(int cantidadPeriodo) {
        this.cantidadPeriodo = cantidadPeriodo;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public int getNumeroOrdenDatoProyecto() {
        return numeroOrdenDatoProyecto;
    }

    public void setNumeroOrdenDatoProyecto(int numeroOrdenDatoProyecto) {
        this.numeroOrdenDatoProyecto = numeroOrdenDatoProyecto;
    }

    public String getCodigoProyecto() {
        return codigoProyecto;
    }

    public void setCodigoProyecto(String codigoProyecto) {
        this.codigoProyecto = codigoProyecto;
    }

    public int getFkIddetallestadocabEstproy() {
        return fkIddetallestadocabEstproy;
    }

    public void setFkIddetallestadocabEstproy(int fkIddetallestadocabEstproy) {
        this.fkIddetallestadocabEstproy = fkIddetallestadocabEstproy;
    }

    public void setFlagPasoTablaNormal(Integer flagPasoTablaNormal) {
		this.flagPasoTablaNormal = flagPasoTablaNormal;
	}

	public int getFlagPasoTablaNormal() {
		return flagPasoTablaNormal;
	}

	public Programa getPrograma() {
        return programa;
    }

    public void setProgramaID(Programa programa) {
        this.programa = programa;
    }

    public SubAreaTematica getSubAreaTematica() {
        return subAreaTematica;
    }

    public void setSubAreaTematica(SubAreaTematica subAreaTematica) {
        this.subAreaTematica = subAreaTematica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tMPDatoProyectoID != null ? tMPDatoProyectoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmpDatoProyecto)) {
            return false;
        }
        TmpDatoProyecto other = (TmpDatoProyecto) object;
        if ((this.tMPDatoProyectoID == null && other.tMPDatoProyectoID != null) || (this.tMPDatoProyectoID != null && !this.tMPDatoProyectoID.equals(other.tMPDatoProyectoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.TmpDatoProyecto[ tMPDatoProyectoID=" + tMPDatoProyectoID + " ]";
    }
    
}
