package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.PersonalTecnicoAdministrativoDAO;
import pe.com.fondam.sgp.core.domain.PersonalTecnicoAdministrativo;
/**
*
* @author Zolanch Távara
*/
@Repository
public class PersonalTecnicoAdministrativoDAOImpl  extends JpaBaseDAOImpl implements PersonalTecnicoAdministrativoDAO {

	@Autowired
	public PersonalTecnicoAdministrativoDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	@Override
	public void savePersonalTecnicoAdministrativo(	PersonalTecnicoAdministrativo personalTA) {
		super.save(personalTA);
		
	}

	@Override
	public PersonalTecnicoAdministrativo updatePersonalTecnicoAdministrativo(
			PersonalTecnicoAdministrativo personalTA) {
		return super.update(personalTA);
	}

	@Override
	public void deletePersonalTecnicoAdministrativo(
			PersonalTecnicoAdministrativo personalTA) {
		super.delete(personalTA);
		
	}

	@Override
	public PersonalTecnicoAdministrativo findPersonalTecnicoAdministrativoById(Integer id) {
		return super.findById(PersonalTecnicoAdministrativo.class , id);
	}

	@Override
	public List<PersonalTecnicoAdministrativo> findPersonalTecnicoAdministrativo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PersonalTecnicoAdministrativo> findPersonalTecnicoAdministrativoXConsulta(
			String consulta, Object[] params) {
		
		return super.find(consulta, params);
	}

}
