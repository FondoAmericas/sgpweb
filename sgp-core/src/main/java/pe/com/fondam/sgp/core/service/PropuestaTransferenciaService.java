package pe.com.fondam.sgp.core.service;

import pe.com.fondam.sgp.core.domain.PropuestaTransferencia;

public interface PropuestaTransferenciaService {

	PropuestaTransferencia findPropuestaTransferenciaByDatoProyectoId(
			Integer datoProyectoID);

	PropuestaTransferencia updatePropuestaTransferencia(
			PropuestaTransferencia propuestaTransferencia);

	PropuestaTransferencia findPropuestaTransferenciaById(
			Integer propuestaTransferenciaId);

	PropuestaTransferencia llenaPropuestaTransferenciaCompleto(
			PropuestaTransferencia propuestaTransferencia);

	
}
