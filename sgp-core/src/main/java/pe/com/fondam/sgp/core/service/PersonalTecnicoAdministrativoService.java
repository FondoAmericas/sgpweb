package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.domain.PersonalTecnicoAdministrativo;

public interface PersonalTecnicoAdministrativoService {

	List<PersonalTecnicoAdministrativo> findPersonalTecnicoReemplazadoXDatoProyectoId(Integer datoProyectoID);

	List<PersonalTecnicoAdministrativo> findPersonalTecnicoAdministrativoByDatoProyectoId(Integer datoProyectoID);
	
	PersonalTecnicoAdministrativo findPersonalTecnicoAdministrativoById(
			int fkIdpersonalTecnicoAdmReemplazo);

	PersonalTecnicoAdministrativo updatePersonalTecnicoAdministrativo(
			PersonalTecnicoAdministrativo personalTA);

	void deletePersonalTecnicoAdministrativo(
			Integer personalTecnicoAdministrativoID);

	}
