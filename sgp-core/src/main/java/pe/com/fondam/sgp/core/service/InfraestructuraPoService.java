package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.domain.InfraestructuraPo;

public interface InfraestructuraPoService {

	List<InfraestructuraPo> findInfraestructuraPoByDatoProyectoId(
			Integer datoProyectoID);

	InfraestructuraPo saveInfraestructuraPoVacio(Integer datoPlanOperativoID);

	InfraestructuraPo findInfraestructuraPoById(Integer infraestructuraPOID);

	InfraestructuraPo updateInfraestructuraPo(InfraestructuraPo infraestructuraPo);

	void deleteInfraestructuraPo(Integer infraestructuraPOID);

}
