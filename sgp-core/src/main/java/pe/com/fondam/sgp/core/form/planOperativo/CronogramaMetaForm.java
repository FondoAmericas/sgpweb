package pe.com.fondam.sgp.core.form.planOperativo;

public class CronogramaMetaForm {

	private Integer cronogramaMetaPorResultadoID;
	private Integer avanceMeta;
	private String periodo;

	public Integer getCronogramaMetaPorResultadoID() {
		return cronogramaMetaPorResultadoID;
	}

	public void setCronogramaMetaPorResultadoID(
			Integer cronogramaMetaPorResultadoID) {
		this.cronogramaMetaPorResultadoID = cronogramaMetaPorResultadoID;
	}

	public Integer getAvanceMeta() {
		return avanceMeta;
	}

	public void setAvanceMeta(Integer avanceMeta) {
		this.avanceMeta = avanceMeta;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

}
