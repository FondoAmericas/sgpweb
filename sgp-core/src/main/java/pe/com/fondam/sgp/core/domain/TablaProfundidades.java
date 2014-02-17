package pe.com.fondam.sgp.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tabla_profundidades")
public class TablaProfundidades implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7203352692834737491L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tabla_profundidades_ID")
	private Integer tablaProfundidadesID;

	@Column(name = "profundidad_01")
	private String profundidad01;

	@Column(name = "profundidad_02")
	private String profundidad02;

	@Column(name = "profundidad_03")
	private String profundidad03;

	@Column(name = "profundidad_04")
	private String profundidad04;

	@Column(name = "profundidad_05")
	private String profundidad05;

	public TablaProfundidades(){
		
	}
	
	public Integer getTablaProfundidadesID() {
		return tablaProfundidadesID;
	}

	public void setTablaProfundidadesID(Integer tablaProfundidadesID) {
		this.tablaProfundidadesID = tablaProfundidadesID;
	}

	public String getProfundidad01() {
		return profundidad01;
	}

	public void setProfundidad01(String profundidad01) {
		this.profundidad01 = profundidad01;
	}

	public String getProfundidad02() {
		return profundidad02;
	}

	public void setProfundidad02(String profundidad02) {
		this.profundidad02 = profundidad02;
	}

	public String getProfundidad03() {
		return profundidad03;
	}

	public void setProfundidad03(String profundidad03) {
		this.profundidad03 = profundidad03;
	}

	public String getProfundidad04() {
		return profundidad04;
	}

	public void setProfundidad04(String profundidad04) {
		this.profundidad04 = profundidad04;
	}

	public String getProfundidad05() {
		return profundidad05;
	}

	public void setProfundidad05(String profundidad05) {
		this.profundidad05 = profundidad05;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (tablaProfundidadesID != null ? tablaProfundidadesID.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof TablaProfundidades)) {
			return false;
		}
		TablaProfundidades other = (TablaProfundidades) object;
		if ((this.tablaProfundidadesID == null && other.tablaProfundidadesID != null)
				|| (this.tablaProfundidadesID != null && !this.tablaProfundidadesID
						.equals(other.tablaProfundidadesID))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.com.fondam.sgp.core.domain.TablaProfundidades[tablaProfundidadesID="
				+ tablaProfundidadesID + "]";
	}
}
