package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.bean.OrganizacionBean;
import pe.com.fondam.sgp.core.domain.Organizacion;

public interface OrganizacionService {

	List<Organizacion> findOrganizacionByPropuestaTransferenciaId(
			Integer propuestaTransferenciaId);

	Organizacion updateOrganizacion(Organizacion organizacion);

	Organizacion findOrganizacionById(Integer sltOrganizacionBienTrnasferido);

	List<Organizacion> findOrganizacionByDatoProyectoId(Integer datoProyectoId);

	void deleteOrganizacion(Integer idRegistro);

	List<OrganizacionBean> llenaListOrganizacionBean(
			List<Organizacion> listOrganizacion);

}
