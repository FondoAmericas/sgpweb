package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.CuentaCorrienteDAO;
import pe.com.fondam.sgp.core.domain.CuentaCorriente;
/**
*
* 
*/

@Repository
public class CuentaCorrienteDAOImpl extends JpaBaseDAOImpl implements CuentaCorrienteDAO {

	//****************  inyecciones  **********//
	@Autowired
	public CuentaCorrienteDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}
	
	//****************  metodos **********//	
	@Override
	public void saveCuentaCorriente(CuentaCorriente cuentaCorriente) {
		super.save(cuentaCorriente);
		
	}

	@Override
	public CuentaCorriente updateCuentaCorriente(CuentaCorriente cuentaCorriente) {
		return super.update(cuentaCorriente);
	}

		
	public void deleteCuentaCorriente(CuentaCorriente cuentaCorriente) {
		super.delete(cuentaCorriente);
	}
	
	@Override
	public CuentaCorriente findCuentaCorrienteById(Integer idCuentaCorriente) {
		return super.findById(CuentaCorriente.class, idCuentaCorriente);
	}
	
	public List<CuentaCorriente> findCuentaCorrienteByIdDatoProyecto(int idDatoProyecto) {
		String queryString = "from CuentaCorriente where datoProyecto.datoProyectoID=?";
		Object[] params = new Object[1];
		params[0] = idDatoProyecto;
		return super.find(queryString, params);
	}


	@Override
	public List<CuentaCorriente> findCuentaCorrienteByConsulta(String consulta,
			Object[] params) {
		return super.find(consulta, params);
	}

}
