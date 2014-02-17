package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.domain.CargaFormulario;

public interface CargaFormularioService {

	CargaFormulario updateCargaFormulario(CargaFormulario cargaFormulario);

	List<CargaFormulario> findFormularios();

	CargaFormulario findCargaFormularioById(Integer formularioId);

}
