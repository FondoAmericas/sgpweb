package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.BeneficiariosPorResultado;

public interface BeneficiariosPorResultadoDAO {

	void saveBeneficiariosPorResultado(
			BeneficiariosPorResultado beneficiariosPorResultado);

	BeneficiariosPorResultado updateBeneficiariosPorResultado(
			BeneficiariosPorResultado beneficiariosPorResultado);

	void deleteBeneficiariosPorResultado(
			BeneficiariosPorResultado beneficiariosPorResultado);

	BeneficiariosPorResultado findBeneficiariosPorResultadoById(Integer id);

	List<BeneficiariosPorResultado> findBeneficiariosPorResultadoByResultadoID(
			Integer resultadoID);

	List<BeneficiariosPorResultado> findBeneficiariosPorResultados();
	
	List<BeneficiariosPorResultado> findBeneficiariosPorResultadoByIdPerfil(Integer idPerfil);

	List<BeneficiariosPorResultado> findBeneficiariosPorResultadoByIdUbicacionProyecto(Integer idProyecto);
	
	BeneficiariosPorResultado findBeneficiariosPorResultadoByResultadoIDAndTipoBeneficiarioIDAndEstratoID(
			Integer resultadoID, Integer tipoBeneficiarioID, Integer estratoID);

	List<BeneficiariosPorResultado>  findBeneficiariosPorResultadoXConsulta(
			String consulta, Object[] params);

	List<BeneficiariosPorResultado> findBeneficiariosPorResultadoXConsultaSinParam(
			String consulta);
	
}
