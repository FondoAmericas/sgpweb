package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.CargaFormulario;

public interface CargaFormularioDao {

	void saveCargaFormulario(CargaFormulario cargaFormulario);

	CargaFormulario updateCargaFormulario(CargaFormulario cargaFormulario);

	List<CargaFormulario> findFormularios();

	CargaFormulario findById(Integer formularioId);

}
