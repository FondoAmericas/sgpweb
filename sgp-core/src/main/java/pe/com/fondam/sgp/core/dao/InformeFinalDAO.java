package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.InformeFinal;

public interface InformeFinalDAO {
	void saveInformeFinal(InformeFinal informeFinal);

	InformeFinal updateInformeFinal(InformeFinal informeFinal);

	void deleteInformeFinal(InformeFinal informeFinal);

	InformeFinal findInformeFinalById(Integer id);

	List<InformeFinal> findInformeFinalByConsulta(String consulta,
			Object[] params);

}
