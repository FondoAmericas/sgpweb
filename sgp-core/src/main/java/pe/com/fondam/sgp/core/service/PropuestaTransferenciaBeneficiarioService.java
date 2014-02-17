package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.domain.PropuestaTransferenciaBeneficiario;

public interface PropuestaTransferenciaBeneficiarioService {

	PropuestaTransferenciaBeneficiario findPropuestaTransferenciaBeneficiarioByBeneficiarioId(
			Integer beneficiariosPorResultadoID);
	
	List<PropuestaTransferenciaBeneficiario> findPropuestaTransferenciaBeneficiarioByBeneficiarioPorResultadoId(
			Integer beneficiariosPorResultadoID);

	void savePropuestaTransferenciaBeneficiario(
			PropuestaTransferenciaBeneficiario propuestaTransferenciaBeneficiario);

	List<PropuestaTransferenciaBeneficiario> findPropuestaTransferenciaBeneficiarioByPropuestaTransferenciaId(
			int propuestaTransferenciaId);

	PropuestaTransferenciaBeneficiario findPropuestaTransferenciaBeneficiarioById(
			Integer sltBeneficiarioBienTransferido);

}
