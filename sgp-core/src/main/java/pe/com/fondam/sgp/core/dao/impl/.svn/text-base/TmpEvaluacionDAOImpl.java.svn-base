package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.TmpEvaluacionDAO;
import pe.com.fondam.sgp.core.domain.TmpEvaluacion;
@Repository
public class TmpEvaluacionDAOImpl extends JpaBaseDAOImpl  implements TmpEvaluacionDAO {
	@Autowired
	public TmpEvaluacionDAOImpl(
			@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	@Override
	public void saveTmpEvaluacion(TmpEvaluacion tmpEvaluacion) {
		super.save(tmpEvaluacion);
		
	}

	@Override
	public TmpEvaluacion updateTmpEvaluacion(TmpEvaluacion tmpEvaluacion) {
		
		return super.update(tmpEvaluacion);
	}

	@Override
	public void deleteTmpEvaluacion(TmpEvaluacion tmpEvaluacion) {
	super.delete(tmpEvaluacion);
		
	}

	@Override
	public TmpEvaluacion findTmpEvaluacionById(Integer id) {
	
		return super.findById(TmpEvaluacion.class, id);
	}

	@Override
	public TmpEvaluacion findTmpEvaluacion(TmpEvaluacion evaluacion) {
	
		String queryString = "from TmpEvaluacion where evaluadorID.evaluadorID= ? and tmpDatoProyectoID.tMPDatoProyectoID=? ";
		Object[] params = new Object[2];
		params[0] = evaluacion.getEvaluadorID().getEvaluadorID();
		params[1] = evaluacion.getTmpDatoProyectoID().getTMPDatoProyectoID();
		
		List<TmpEvaluacion> list= super.find(queryString, params);
		if (list!=null && list.size()!=0) {
			return	list.get(0);
		}
		return null;
	}

	@Override
	public TmpEvaluacion findTmpEvaluacionByTipoEvaluacionByTmpDatoProyectoID(int tipoEvaluacion, Integer tmpDatoProyectoID) {
		String queryString = "from TmpEvaluacion where evaluadorID.fkIdtablaespTipoEvaluacion= ? and tmpDatoProyectoID.tMPDatoProyectoID=? ";
		Object[] params = new Object[2];
		params[0] = tipoEvaluacion;
		params[1] = tmpDatoProyectoID;
		
		List<TmpEvaluacion> list= super.find(queryString, params);
		if (list!=null && list.size()!=0) {
			return	list.get(0);
		}
		return null;
	}

	@Override
	public List<TmpEvaluacion> findTmpEvaluacionByTmpDatoProyectoID(Integer tmpDatoProyectoId) {
		String queryString = "from TmpEvaluacion where tmpDatoProyectoID.tMPDatoProyectoID=? ";
		Object[] params = new Object[1];
		params[0] = tmpDatoProyectoId;

		return super.find(queryString, params);
	}
	@Override
	public List<TmpEvaluacion> findTmpEvaluacionByProgramaIDbyTipoEvaluacionPerfil(Integer programaId) {
		String queryString = "from TmpEvaluacion where tmpDatoProyectoID.programa.programaID=? and evaluadorID.fkIdtablaespTipoEvaluacion =174 order by 1";
		Object[] params = new Object[1];
		params[0] = programaId;

		return super.find(queryString, params);
	}

	@Override
	public List<TmpEvaluacion> findByConsulta(String queryString,
			Object[] params) {
		
		return super.find(queryString, params);
	}




    
}
