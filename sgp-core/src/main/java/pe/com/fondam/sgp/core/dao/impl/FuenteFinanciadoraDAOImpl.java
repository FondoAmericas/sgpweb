package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.FuenteFinanciadoraDAO;
import pe.com.fondam.sgp.core.domain.FuenteFinanciadora;

@Repository
public class FuenteFinanciadoraDAOImpl extends JpaBaseDAOImpl implements
		FuenteFinanciadoraDAO {
	
	//**************** inyecciones *********************//
	@Autowired
	public FuenteFinanciadoraDAOImpl(
			@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	
	//**************** metodos *********************//
	@Override
	public void saveFuenteFinanciadora(FuenteFinanciadora fuenteFinanciadora) {
		super.save(fuenteFinanciadora);

	}

	@Override
	public FuenteFinanciadora updateFuenteFinanciadora(
			FuenteFinanciadora fuenteFinanciadora) {
		// TODO Auto-generated method stub
		return super.update(fuenteFinanciadora);
	}

	@Override
	public void deleteFuenteFinanciadora(FuenteFinanciadora fuenteFinanciadora) {
		super.delete(fuenteFinanciadora);

	}

	@Override
	public FuenteFinanciadora findFuenteFinanciadoraById(Integer id) {
		return super.findById(FuenteFinanciadora.class, id);
	}

	@Override
	public List<FuenteFinanciadora> findFuenteFinanciadoras() {
		return null;
	}

	public FuenteFinanciadora findFuenteFinanciadoraByIdDatoProyecto(Integer id) {
		String queryString = "from FuenteFinanciadora where datoProyecto.datoProyectoID= ? and defineSiEsEjecutor=1 ";
		Object[] params = new Object[1];
		params[0] = id;
		List<FuenteFinanciadora> listFuneteFinanciadora = super.find(
				queryString, params);
		if (listFuneteFinanciadora != null
				&& listFuneteFinanciadora.size() != 0) {
			return listFuneteFinanciadora.get(0);
		}
		return null;

	}

	public List<FuenteFinanciadora> findFuenteFinanciadorasByIdDatoProy(
			Integer id) {
		String queryString = "from FuenteFinanciadora where datoProyecto.datoProyectoID=?";
		Object[] params = new Object[1];
		params[0] = id;
		return super.find(queryString, params);

	}

	@Override
	public List<FuenteFinanciadora> findFuenteFinanciadoraByDatoProyectoID(
			Integer datoProyectoID) {
		String queryString = "from FuenteFinanciadora where datoProyecto.datoProyectoID=?";
		Object[] params = new Object[1];
		params[0] = datoProyectoID;
		return super.find(queryString, params);
	}

	public FuenteFinanciadora findFuenteFinanciadoraByDatoProyectoIDByInstitucionID(Integer datoProyectoID, Integer institucionID){
		String queryString = "from FuenteFinanciadora where datoProyecto.datoProyectoID=? and institucion.institucionID=? ";
		Object[] params = new Object[2];
		params[0] = datoProyectoID;
		params[1] = institucionID;
		//return super.find(queryString, params);
		List<FuenteFinanciadora> listFuenteFinanciadora = super.find(queryString, params);
		if (listFuenteFinanciadora != null && listFuenteFinanciadora.size() != 0) {
			return listFuenteFinanciadora.get(0);
		}
		return null;
	}


	@Override
	public List<FuenteFinanciadora> findFuenteFinanciadoraByConsulta(
			String consulta, Object[] params) {
		return super.find(consulta, params);
	}
		
}
