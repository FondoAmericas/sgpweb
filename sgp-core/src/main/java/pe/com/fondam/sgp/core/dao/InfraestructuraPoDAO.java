package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.InfraestructuraPo;

/**
*
* 
*/
public interface InfraestructuraPoDAO {
	void saveInfraestructuraPo(InfraestructuraPo infraestructuraPo);

	InfraestructuraPo updateInfraestructuraPo(
			InfraestructuraPo infraestructuraPo);

	void deleteInfraestructuraPo(InfraestructuraPo infraestructuraPo);

	InfraestructuraPo findInfraestructuraPoById(Integer id);

	List<InfraestructuraPo> findInfraestructuraPo();

	List<InfraestructuraPo> findInfraestructuraPoByConsulta(String consulta,
			Object[] params);
}
