/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.fondam.sgp.core.bean;

import java.io.Serializable;

import pe.com.fondam.sgp.core.domain.InformeFinal;

public class MaterialProducidoBean implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1898302616572649740L;
	private Integer materialProducidoID;
    private int fkIdtablaespTipoMaterial;
    private String descripcionTipoMaterial;
    private int cantidad;
    private String descripcionMaterialProducido;
    private InformeFinal informeFinal;

    public MaterialProducidoBean() {
    }

    public MaterialProducidoBean(Integer materialProducidoID) {
        this.materialProducidoID = materialProducidoID;
    }

    public MaterialProducidoBean(Integer materialProducidoID, int fkIdtablaespTipoMaterial, int cantidad, String descripcionMaterialProducido) {
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
        if (!(object instanceof MaterialProducidoBean)) {
            return false;
        }
        MaterialProducidoBean other = (MaterialProducidoBean) object;
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
