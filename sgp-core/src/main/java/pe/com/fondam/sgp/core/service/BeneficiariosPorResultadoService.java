package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.bean.BeneficiariosPorResultadoBean;
import pe.com.fondam.sgp.core.domain.BeneficiariosPorResultado;

public interface BeneficiariosPorResultadoService {

	List<BeneficiariosPorResultado> findBeneficiariosPorResultadoXUbicacionProyectoId(Integer ubicacionProyectoID);

	void saveBeneficiariosPorResultado(BeneficiariosPorResultado beneficiariosPorResultado);

	BeneficiariosPorResultado findBeneficiariosPorResultadoById(Integer beneficiarioPorResultadoId);
	
	List<BeneficiariosPorResultado> findBeneficiariosPorResultadoByResultadoID(Integer resultadoID);
	
	void deleteBeneficiariosPorResultado(BeneficiariosPorResultado beneficiariosPorResultado);

	BeneficiariosPorResultado updateBeneficiariosPorResultado(
			BeneficiariosPorResultado beneficiariosPorResultado);
	
	List<BeneficiariosPorResultadoBean> llenaListBeneficiariosPorResultadoBean(List<BeneficiariosPorResultado> listBeneficiariosPorResultado);
	
	BeneficiariosPorResultadoBean llenaBeneficiariosPorResultadoBean(
			BeneficiariosPorResultado beneficiariosPorResultado);

	List<BeneficiariosPorResultado> llenaListBeneficiariosPorResultado(
			List<BeneficiariosPorResultado> listBeneficiariosPorResultado);

	List<BeneficiariosPorResultado> findBeneficiariosPorResultadoByPerfilID(
			Integer perfilID);
}
