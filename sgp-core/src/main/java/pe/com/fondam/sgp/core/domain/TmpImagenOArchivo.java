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
@Table(name = "tmp_imagen_o_archivo")
public class TmpImagenOArchivo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    @Column(name = "tmp_Imagen_O_Archivo_ID")
    private Integer tmpImagenOArchivoID;
    
    @Lob
    @Column(name = "descripcion_doc_img")
    private String descripcionDocImg;
    
    @Lob
    @Column(name = "imagen")
    private byte[] imagen;
    
    @Column(name = "fk_idtablaesp_tipo_archivo")
    private int fkIdtablaespTipoArchivo;
    
    @JoinColumn(name = "tmp_Perfil_ID", referencedColumnName = "TMP_Perfil_ID")
    @ManyToOne
    private TmpPerfil tmpPerfil;

    public Integer getTmpImagenOArchivoID() {
        return tmpImagenOArchivoID;
    }

    public void setTmpImagenOArchivoID(Integer tmpImagenOArchivoID) {
        this.tmpImagenOArchivoID = tmpImagenOArchivoID;
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

    public TmpPerfil getTmpPerfil() {
        return tmpPerfil;
    }

    public void setTmpPerfil(TmpPerfil tmpPerfil) {
        this.tmpPerfil = tmpPerfil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tmpImagenOArchivoID != null ? tmpImagenOArchivoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmpImagenOArchivo)) {
            return false;
        }
        TmpImagenOArchivo other = (TmpImagenOArchivo) object;
        if ((this.tmpImagenOArchivoID == null && other.tmpImagenOArchivoID != null) || (this.tmpImagenOArchivoID != null && !this.tmpImagenOArchivoID.equals(other.tmpImagenOArchivoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.TmpImagenOArchivo[ tmpImagenOArchivoID=" + tmpImagenOArchivoID + " ]";
    }
    
}
