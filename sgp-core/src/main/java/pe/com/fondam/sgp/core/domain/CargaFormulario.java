package pe.com.fondam.sgp.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "carga_formulario")
public class CargaFormulario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5211247931189000253L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Carga_Formulario_ID")
    private Integer cargaFormularioID;
     
    @Lob
    @Column(name = "descripcion_formulario")
    private String descripcionFormulario;
     
    @Column(name = "formulario")
    private byte[] formulario;
     
    @Column(name = "fk_idtablaesp_tipo_archivo")
    private int fkIdtablaespTipoArchivo;
    
    
    public CargaFormulario(){
    	
    }


	public Integer getCargaFormularioID() {
		return cargaFormularioID;
	}


	public void setCargaFormularioID(Integer cargaFormularioID) {
		this.cargaFormularioID = cargaFormularioID;
	}


	public String getDescripcionFormulario() {
		return descripcionFormulario;
	}


	public void setDescripcionFormulario(String descripcionFormulario) {
		this.descripcionFormulario = descripcionFormulario;
	}


	public byte[] getFormulario() {
		return formulario;
	}


	public void setFormulario(byte[] formulario) {
		this.formulario = formulario;
	}


	public int getFkIdtablaespTipoArchivo() {
		return fkIdtablaespTipoArchivo;
	}


	public void setFkIdtablaespTipoArchivo(int fkIdtablaespTipoArchivo) {
		this.fkIdtablaespTipoArchivo = fkIdtablaespTipoArchivo;
	}
    
    
	@Override
    public int hashCode() {
        int hash = 0;
        hash += (cargaFormularioID != null ? cargaFormularioID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CargaFormulario)) {
            return false;
        }
        CargaFormulario other = (CargaFormulario) object;
        if ((this.cargaFormularioID == null && other.cargaFormularioID != null) || (this.cargaFormularioID != null && !this.cargaFormularioID.equals(other.cargaFormularioID))) {
            return false;
        }
        return true;
    }
    
}
