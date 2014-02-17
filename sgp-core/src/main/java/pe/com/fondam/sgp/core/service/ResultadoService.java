package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.domain.Resultado;

public interface ResultadoService {

	List<Resultado> findResultadoXDatoProyectoID(Integer datoProyectoID);

	List<Resultado> findResultadoXDatoPlanOperativoID(Integer datoPlanOperativoID);
	
	Resultado findResultadoByID(Integer resultadoID);
	
	Resultado updateResultado(Resultado resultado);

	Resultado llenaResultadoCompleto(Resultado resultado);

	List<Resultado> llenaResultadoActividadesMeta(
			List<Resultado> listResultado);

	List<Resultado> llenaResultadoActividadesCosto(
			List<Resultado> listResultado);

	List<Resultado> llenaResultadoBeneficiarios(
			List<Resultado> listResultado);

	List<Resultado> llenaResultadoCronogramaMetas(
			List<Resultado> listResultado);

	
}
