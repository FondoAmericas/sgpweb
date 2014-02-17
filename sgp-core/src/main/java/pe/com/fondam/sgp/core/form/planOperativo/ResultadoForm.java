package pe.com.fondam.sgp.core.form.planOperativo;

import java.util.List;

public class ResultadoForm {

	private Integer resultadoID;
	private Integer codigoResultado;
	private String definicionResultado;
	private String supuestoResultado;
	private Integer metaResultado;
	private Integer estratoId;
	private String estratoNombre;
	private Integer duracionMeses;
	private List<IndicadorForm> listIndicadorForm;
	private List<BeneficiarioForm> listBeneficiarioForm;
	private List<CronogramaMetaForm> listCronogramaMetaForm;
	private List<ActividadForm> listActividadForm;

	public Integer getResultadoID() {
		return resultadoID;
	}

	public void setResultadoID(Integer resultadoID) {
		this.resultadoID = resultadoID;
	}

	public Integer getCodigoResultado() {
		return codigoResultado;
	}

	public void setCodigoResultado(Integer codigoResultado) {
		this.codigoResultado = codigoResultado;
	}

	public String getDefinicionResultado() {
		return definicionResultado;
	}

	public void setDefinicionResultado(String definicionResultado) {
		this.definicionResultado = definicionResultado;
	}

	public String getSupuestoResultado() {
		return supuestoResultado;
	}

	public void setSupuestoResultado(String supuestoResultado) {
		this.supuestoResultado = supuestoResultado;
	}

	public Integer getMetaResultado() {
		return metaResultado;
	}

	public void setMetaResultado(Integer metaResultado) {
		this.metaResultado = metaResultado;
	}

	public Integer getEstratoId() {
		return estratoId;
	}

	public void setEstratoId(Integer estratoId) {
		this.estratoId = estratoId;
	}

	public String getEstratoNombre() {
		return estratoNombre;
	}

	public void setEstratoNombre(String estratoNombre) {
		this.estratoNombre = estratoNombre;
	}

	public Integer getDuracionMeses() {
		return duracionMeses;
	}

	public void setDuracionMeses(Integer duracionMeses) {
		this.duracionMeses = duracionMeses;
	}

	public List<IndicadorForm> getListIndicadorForm() {
		return listIndicadorForm;
	}

	public void setListIndicadorForm(List<IndicadorForm> listIndicadorForm) {
		this.listIndicadorForm = listIndicadorForm;
	}

	public List<BeneficiarioForm> getListBeneficiarioForm() {
		return listBeneficiarioForm;
	}

	public void setListBeneficiarioForm(
			List<BeneficiarioForm> listBeneficiarioForm) {
		this.listBeneficiarioForm = listBeneficiarioForm;
	}

	public List<CronogramaMetaForm> getListCronogramaMetaForm() {
		return listCronogramaMetaForm;
	}

	public void setListCronogramaMetaForm(
			List<CronogramaMetaForm> listCronogramaMetaForm) {
		this.listCronogramaMetaForm = listCronogramaMetaForm;
	}

	public List<ActividadForm> getListActividadForm() {
		return listActividadForm;
	}

	public void setListActividadForm(List<ActividadForm> listActividadForm) {
		this.listActividadForm = listActividadForm;
	}

}
