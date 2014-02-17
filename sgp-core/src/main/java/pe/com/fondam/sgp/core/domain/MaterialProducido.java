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
@Table(name = "material_producido")

public class MaterialProducido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "Material_Producido_ID")
    private Integer materialProducidoID;
    
    @Column(name = "fk_idtablaesp_tipo_material")
    private int fkIdtablaespTipoMaterial;
    
    @Transient
    private String descripcionTipoMaterial;
    
    @Column(name = "cantidad")
    private int cantidad;
    
    @Lob
    @Column(name = "descripcion_material_producido")
    private String descripcionMaterialProducido;
    
    @JoinColumn(name = "Informe_Final_ID", referencedColumnName = "Informe_Final_ID")
    @ManyToOne(optional = false)
    private InformeFinal informeFinal;

    public MaterialProducido() {
    }

    public MaterialProducido(Integer materialProducidoID) {
        this.materialProducidoID = materialProducidoID;
    }

    public MaterialProducido(Integer materialProducidoID, int fkIdtablaespTipoMaterial, int cantidad, String descripcionMaterialProducido) {
        this.materialProducidoID = materialProducidoID;
        this.fkIdtablaespTipoMaterial = fkIdtablaespTipoMaterial;
        this.cantidad = cantidad;
        this.descripcionMaterialProducido = descripcionMaterialProducido;
    }

    public Integer getMaterialProducidoID() {
        return materialProducidoID;
    }

    public void setMaterialProducidoID(Integer materialProducidoID) {
        this.materialProducidoID = materialProducidoID;
    }

    public int getFkIdtablaespTipoMaterial() {
        return fkIdtablaespTipoMaterial;
    }

    public void setFkIdtablaespTipoMaterial(int fkIdtablaespTipoMaterial) {
        this.fkIdtablaespTipoMaterial = fkIdtablaespTipoMaterial;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcionMaterialProducido() {
        return descripcionMaterialProducido;
    }

    public void setDescripcionMaterialProducido(String descripcionMaterialProducido) {
        this.descripcionMaterialProducido = descripcionMaterialProducido;
    }

    public InformeFinal getInformeFinal() {
        return informeFinal;
    }

    public void setInformeFinal(InformeFinal informeFinal) {
        this.informeFinal = informeFinal;
    }

    public void setDescripcionTipoMaterial(String descripcionTipoMaterial) {
		this.descripcionTipoMaterial = descripcionTipoMaterial;
	}

	public String getDescripcionTipoMaterial() {
		return descripcionTipoMaterial;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (materialProducidoID != null ? materialProducidoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaterialProducido)) {
            return false;
        }
        MaterialProducido other = (MaterialProducido) object;
        if ((this.materialProducidoID == null && other.materialProducidoID != null) || (this.materialProducidoID != null && !this.materialProducidoID.equals(other.materialProducidoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.MaterialProducido[materialProducidoID=" + materialProducidoID + "]";
    }

}
