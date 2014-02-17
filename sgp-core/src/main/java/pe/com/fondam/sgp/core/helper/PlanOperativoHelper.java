package pe.com.fondam.sgp.core.helper;

import java.util.List;

import pe.com.fondam.sgp.core.domain.Actividad;
import pe.com.fondam.sgp.core.domain.FuenteFinanciadora;
import pe.com.fondam.sgp.core.domain.Institucion;

public interface PlanOperativoHelper {

	Integer getDatoPlanOperativoIDByDatoProyectoID(Integer datoProyectoID);
	
	Integer getDetalleEstadoCabeceraID(Integer datoPlanOperativoID);
	
	Integer getCantidadPeriodo(Integer datoProyectoID);
	
	List<FuenteFinanciadora> getListFuenteFinanciadoraByDatoPlanOperativoID(Integer datoPlanOperativoID);
	
	List<Institucion> getListInstitucionByDatoPlanOperativoID(Integer datoPlanOperativoID);
	
	List<Actividad> getListActividadByDatoPlanOperativoID(Integer datoPlanOperativoID);
	
	Double sumMontoActividadByFuenteFinanciadoraID(Integer actividadID, Integer fuenteFinanciadoraID);
	
	Double sumMontoFinanciadoByFuenteFinanciadoras(Integer datoPlanOperativoID);
	
	Double sumCostoActividadByDatoPlanOperativoID(Integer datoPlanOperativoID);
	
	Double montoFinanciadoByFuenteFinanciadoraIDAndDatoProyectoID(Integer fuenteFinanciadoraID);
	
	Double sumCronogramaCostoActividadByFuenteFinanciadoraIDAndDatoProyectoID(Integer fuenteFinanciadoraID, Integer datoPlanOperativoID);
	
}
