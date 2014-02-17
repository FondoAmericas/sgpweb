package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.EvaluacionDAO;
import pe.com.fondam.sgp.core.domain.DatoProyecto;
import pe.com.fondam.sgp.core.domain.Evaluacion;


@Repository
public class EvaluacionDAOImpl extends JpaBaseDAOImpl implements EvaluacionDAO {
	@Autowired
	public EvaluacionDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}
	@Override
	public void saveEvaluacion(Evaluacion evaluacion) {
		super.save(evaluacion);
		
	}

	@Override
	public Evaluacion updateEvaluacion(Evaluacion evaluacion) {
		return super.update(evaluacion);
	}

	@Override
	public void deleteEvaluacion(Evaluacion evaluacion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Evaluacion findEvaluacionById(Integer id) {
		return super.findById(Evaluacion.class, id);
	}

	@Override
	public List<Evaluacion> findEvaluacionProyectos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Evaluacion> findEvaluacionByEvaluadorId(Integer idProyecto,Integer idTipoEvaluacion) {
		String queryString = "from Evaluacion where datoProyecto.datoProyectoID=? and evaluador.fkIdtablaespTipoEvaluacion= ?";
		Object[] params = new Object[2];
		params[0] = idProyecto;
		params[1] =idTipoEvaluacion;
		return super.find(queryString, params);
		
	}
	
	@Override
	public Evaluacion findEvaluacion(Evaluacion evaluacion) {
	
		String queryString = "from Evaluacion where evaluador.evaluadorID= ? and datoProyecto.datoProyectoID=? ";
		Object[] params = new Object[2];
		params[0] = evaluacion.getEvaluador().getEvaluadorID();
		params[1] = evaluacion.getDatoProyecto().getDatoProyectoID();
		
		List<Evaluacion> list= super.find(queryString, params);
		if (list!=null && list.size()!=0) {
			return	list.get(0);
		}
		return null;
	}
	@Override
	public Evaluacion findEvaluacionByDatoProyectoIDbyTipoEvaluacion(DatoProyecto datoProyecto, int tipoEvaluacion, int datoUsuario) {
		String queryString = "from Evaluacion where datoProyecto.datoProyectoID=? and evaluador.fkIdtablaespTipoEvaluacion= ? and evaluador.programa.programaID=? and evaluador.datoUsuario.datoUsuarioID=?  and datoProyecto.datoProyectoID=?";

		Object[] params = new Object[5];
		params[0] =  datoProyecto.getPrograma().getProgramaID();
		params[1] = tipoEvaluacion;
		params[2] = datoUsuario;
		params[3] = datoProyecto.getDatoProyectoID();
		
		List<Evaluacion> list= super.find(queryString, params);
		if (list!=null && list.size()!=0) {
			return	list.get(0);
		}
		return null;
	}
	
	@Override
	public List<Evaluacion> findEvaluacionByProgramaIDbyTipoEvaluacionDatoProyecto(
			Integer programaID) {
		String queryString = "from Evaluacion where datoProyecto.programa.programaID=? and evaluador.fkIdtablaespTipoEvaluacion =175 order by 1";
		Object[] params = new Object[1];
		params[0] = programaID;

		return super.find(queryString, params);

	}
	@Override
	public List<Evaluacion> findConsulta(String consulta, Object[] params) {
		return super.find(consulta, params);
	}
	}
