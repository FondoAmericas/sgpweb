package pe.com.fondam.sgp.core.view.planOperativo;

import java.util.List;

public class CronogramaMetaActividadView {

	private Integer cronogramaMetaActividadID;
	private Integer metaActividadID;
	private Integer cantidadTotal;
	private List<PeriodoView> listPeriodo;
	
	
	public Integer getCronogramaMetaActividadID() {
		return cronogramaMetaActividadID;
	}
	public void setCronogramaMetaActividadID(Integer cronogramaMetaActividadID) {
		this.cronogramaMetaActividadID = cronogramaMetaActividadID;
	}
	public Integer getMetaActividadID() {
		return metaActividadID;
	}
	public void setMetaActividadID(Integer metaActividadID) {
		this.metaActividadID = metaActividadID;
	}
	public Integer getCantidadTotal() {
		return cantidadTotal;
	}
	public void setCantidadTotal(Integer cantidadTotal) {
		this.cantidadTotal = cantidadTotal;
	}
	public List<PeriodoView> getListPeriodo() {
		return listPeriodo;
	}
	public void setListPeriodo(List<PeriodoView> listPeriodo) {
		this.listPeriodo = listPeriodo;
	}
 

}
