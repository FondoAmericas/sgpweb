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
@Table(name = "informe_pers_tecn_admin")
public class InformePersTecnAdmin implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     
    @Column(name = "Informe_Pers_Tecn_Admin_ID")
    private Integer informePersTecnAdminID;
     
    @Lob
    @Column(name = "comentario")
    private String comentario;
    @JoinColumn(name = "Informe_Visita_Campo_ID", referencedColumnName = "Informe_Visita_Campo_ID")
    @ManyToOne(optional = false)
    private InformeVisitaCampo informeVisitaCampo;
    @JoinColumn(name = "Personal_Tecnico_Administrativo_ID", referencedColumnName = "Personal_Tecnico_Administrativo_ID")
    @ManyToOne(optional = false)
    private PersonalTecnicoAdministrativo personalTecnicoAdministrativo;

    public InformePersTecnAdmin() {
    }

    public InformePersTecnAdmin(Integer informePersTecnAdminID) {
        this.informePersTecnAdminID = informePersTecnAdminID;
    }

    public InformePersTecnAdmin(Integer informePersTecnAdminID, String comentario) {
        this.informePersTecnAdminID = informePersTecnAdminID;
        this.comentario = comentario;
    }

    public Integer getInformePersTecnAdminID() {
        return informePersTecnAdminID;
    }

    public void setInformePersTecnAdminID(Integer informePersTecnAdminID) {
        this.informePersTecnAdminID = informePersTecnAdminID;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public InformeVisitaCampo getInformeVisitaCampo() {
        return informeVisitaCampo;
    }

    public void setInformeVisitaCampo(InformeVisitaCampo informeVisitaCampo) {
        this.informeVisitaCampo = informeVisitaCampo;
    }

    public PersonalTecnicoAdministrativo getPersonalTecnicoAdministrativo() {
        return personalTecnicoAdministrativo;
    }

    public void setPersonalTecnicoAdministrativo(PersonalTecnicoAdministrativo personalTecnicoAdministrativo) {
        this.personalTecnicoAdministrativo = personalTecnicoAdministrativo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (informePersTecnAdminID != null ? informePersTecnAdminID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InformePersTecnAdmin)) {
            return false;
        }
        InformePersTecnAdmin other = (InformePersTecnAdmin) object;
        if ((this.informePersTecnAdminID == null && other.informePersTecnAdminID != null) || (this.informePersTecnAdminID != null && !this.informePersTecnAdminID.equals(other.informePersTecnAdminID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.InformePersTecnAdmin[informePersTecnAdminID=" + informePersTecnAdminID + "]";
    }

}
