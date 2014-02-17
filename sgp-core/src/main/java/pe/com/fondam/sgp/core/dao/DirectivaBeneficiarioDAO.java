package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.DirectivaBeneficiario;

public interface DirectivaBeneficiarioDAO {

	void saveDirectivaBeneficiario(DirectivaBeneficiario directivaBeneficiario);
	
	DirectivaBeneficiario updateDirectivaBeneficiario(DirectivaBeneficiario directivaBeneficiario);
	
	void deleteDirectivaBeneficiario(DirectivaBeneficiario directivaBeneficiario);
	
	DirectivaBeneficiario findDirectivaBeneficiarioById(Integer id);

	List<DirectivaBeneficiario> findDirectivaBeneficiarioByConsulta(
			String consulta, Object[] params);

}
