package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.DepProvDistDAO;
import pe.com.fondam.sgp.core.domain.DepProvDist;

@Repository
public class DepProvDistDAOImpl extends JpaBaseDAOImpl implements DepProvDistDAO {

	@Autowired
	public DepProvDistDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}
	
	@Override
	public void saveDepProvDist(DepProvDist depProvDist) {
		this.save(depProvDist);
	}

	@Override
	public DepProvDist updateDepProvDist(DepProvDist depProvDist) {
		return super.update(depProvDist);
	}

	@Override
	public void deleteDepProvDist(DepProvDist depProvDist) {
		super.delete(depProvDist);
	}

	@Override
	public DepProvDist findDepProvDistById(Integer id) {
		return super.findById(DepProvDist.class,id);
	}

	@Override
	public List<DepProvDist> findDepProvDistritos(int modo,String idDepartamento,String idProvincia,String idDistrito) {
		String queryString = null;
		switch(modo){
		case 1: queryString= "from DepProvDist WHERE idDepartamento<>? AND idProvincia=? AND idDistrito=? ORDER BY descripcion";break;//departamentos
		case 2: queryString= "from DepProvDist WHERE idDepartamento<>? AND idProvincia<>? AND idDistrito=? ORDER BY descripcion"; break;//provincias
		case 3: queryString= "from DepProvDist WHERE idDepartamento<>? AND idProvincia<>? AND idDistrito<>? ORDER BY descripcion";break;//distritos
		}
		
		Object[] params = new Object[3];
		params[0] = idDepartamento;
		params[1] = idProvincia;
		params[2] = idDistrito;
	    return super.find(queryString,params);
		
	}
	

	@Override
	public List<DepProvDist> findUbigeo(int flag,String idDepartamento,String idProvincia,String idDistrito) {
		String queryString = null;
		String departamento="0";
		String provincia="0";
		
		if(!idDepartamento.equals("0")){
			departamento=super.findById(DepProvDist.class,Integer.parseInt(idDepartamento)).getIdDepartamento();
	}
		if(!idProvincia.equals("0")){
			provincia=super.findById(DepProvDist.class,Integer.parseInt(idProvincia)).getIdProvincia();
	}
		switch(flag){
		case 1: queryString= "from DepProvDist WHERE idDepartamento<>? AND idProvincia=? AND idDistrito=? ORDER BY descripcion";break;//departamentos
		case 2: 
			//departamento=super.findById(DepProvDist.class,Integer.parseInt(idDepartamento)).getIdDepartamento();
			queryString= "from DepProvDist  WHERE idDepartamento=? AND idProvincia!=? AND idDistrito=? ORDER BY descripcion"; break;//provincias
		case 3: 
			//departamento=super.findById(DepProvDist.class,Integer.parseInt(idDepartamento)).getIdDepartamento();
			//provincia=super.findById(DepProvDist.class,Integer.parseInt(idProvincia)).getIdProvincia();
			queryString= "from DepProvDist WHERE idDepartamento=? AND idProvincia=? AND idDistrito!=? ORDER BY descripcion";break;//distritos
		}
		
		Object[] params = new Object[3];
		params[0] = departamento;
		params[1] = provincia;
		params[2] = idDistrito;
	    return super.find(queryString,params);
		
	}

	
	@Override
	public List<DepProvDist> findDepProvDist() {
		
		return super.find("from DepProvDist");
	}

	@Override
	public List<DepProvDist> findDepProvDistXConsulta(String consulta,
			Object[] params) {
		return super.find(consulta, params);
	}
	
	
	
//	@Override
//	public List<TablaEspecifica> findTablaEspecificasByTablaGeneralId(Integer id ) {
//
//		String queryString = "from TablaEspecifica where tablaGeneral.tablaGeneralID = ?" ;
//		Object[] params = new Object[1];
//		params[0] = id;
//		return  super.find(queryString, params);
//	}

}
