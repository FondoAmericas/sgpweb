package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.DatoPlanOperativoDAO;
import pe.com.fondam.sgp.core.domain.DatoPlanOperativo;

@Repository
public class DatoPlanOperativoDAOImpl extends JpaBaseDAOImpl implements
		DatoPlanOperativoDAO {

	//*************  inyecciones  **********//
	@Autowired
	public DatoPlanOperativoDAOImpl(
			@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	
	//*************  metodos **********//
	@Override
	public void saveDatoPlanOperativo(DatoPlanOperativo datoPlanOperativo) {
		super.save(datoPlanOperativo);
	}

	@Override
	public DatoPlanOperativo updateDatoPlanOperativo(
			DatoPlanOperativo datoPlanOperativo) {
		return super.update(datoPlanOperativo);
	}

	@Override
	public void deleteActivo(DatoPlanOperativo datoPlanOperativo) {
		super.delete(datoPlanOperativo);
	}

	@Override
	public DatoPlanOperativo findDatoPlanOperativoById(Integer id) {
		return super.findById(DatoPlanOperativo.class, id);
	}

	@Override
	public List<DatoPlanOperativo> findListDatoPlanOperativo() {
		return super.find("from DatoPlanOperativo");
	}

	@Override
	public DatoPlanOperativo findDatoPlanOperativoByDatoProyectoID(
			Integer datoProyectoID) {

		//me manda el plan operativo con la mayor version
		DatoPlanOperativo datoPlanOperativo = null;

		String queryString = "from DatoPlanOperativo where datoProyecto.datoProyectoID = ? ";
		Object[] params = new Object[1];
		params[0] = datoProyectoID;

		List<DatoPlanOperativo> listDatoPlanOperativo = super.find(queryString,
				params);
		if (!listDatoPlanOperativo.isEmpty()) {
			Integer version=0;
			for (DatoPlanOperativo datoPlanOperativo2 : listDatoPlanOperativo) {
				if(version< Integer.valueOf(datoPlanOperativo2.getVersion())){
					version= Integer.valueOf(datoPlanOperativo2.getVersion());
					datoPlanOperativo = datoPlanOperativo2;		
				}
			}
		}
		return datoPlanOperativo;
	}

	
	public DatoPlanOperativo findDatoPlanOperativoByDatoProyectoID2(Integer datoProyectoID) {
		String queryString = " from DatoPlanOperativo where datoProyecto.datoProyectoID = ? ";
		Object[] params = new Object[1];
		params[0] = datoProyectoID;
		List<DatoPlanOperativo> listDatoPlanOperativo = super.find(queryString, params);
		
		return listDatoPlanOperativo.get(0);
	}

	@Override
	public List<DatoPlanOperativo> findDatoPlanOperativoByConsulta(
			String consulta, Object[] params) {
		return super.find(consulta, params);
	}


	@Override
	public DatoPlanOperativo findDatoPlanOperativoByDatoProyectoIDByVersion(
			Integer datoProyectoId, String versionPo) {
		//me manda el plan operativo con la mayor version
		DatoPlanOperativo datoPlanOperativo = null;

		String queryString = "from DatoPlanOperativo where datoProyecto.datoProyectoID = ? and version = ?";
		Object[] params = new Object[2];
		params[0] = datoProyectoId;
		params[1] = versionPo;

		List<DatoPlanOperativo> listDatoPlanOperativo = super.find(queryString,
				params);
		if(listDatoPlanOperativo.size()>0){
			datoPlanOperativo=listDatoPlanOperativo.get(0);
		}
		
		return datoPlanOperativo;
	}
	
	
}