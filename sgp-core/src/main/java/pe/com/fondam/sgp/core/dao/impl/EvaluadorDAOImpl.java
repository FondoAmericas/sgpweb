package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.EvaluadorDAO;
import pe.com.fondam.sgp.core.domain.Evaluador;
/**
*
*/
@Repository
public class EvaluadorDAOImpl extends JpaBaseDAOImpl implements EvaluadorDAO {
	protected final Log logger = LogFactory.getLog(EvaluadorDAOImpl.class);

	@Autowired
	public EvaluadorDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	@Override
	public void saveEvaluador(Evaluador evaluador) {
			super.save(evaluador);

	}

	public List<Evaluador>  findEvaluadorByDatoUsuarioIDAndProgramaID(
			Integer datoUsuarioID, Integer programaID) {
		
		//Evaluador evaluador = null;
		String queryString = "from Evaluador where datoUsuario.datoUsuarioID = ? and programa.programaID = ? ";
		Object[] params = new Object[2];
		params[0] = datoUsuarioID;
		params[1] = programaID;
		
		return  super.find(queryString, params);
	}


	@Override
	public Evaluador updateEvaluador(Evaluador evaluador) {
		
		return super.update(evaluador);
	}

	@Override
	public void deleteEvaluador(Integer id) {
		Evaluador eva =super.findById(Evaluador.class, id);
		super.delete(eva);
		
	}


	@Override
	public List<Evaluador> findEvaluador() {
		return find("from Evaluador") ;
	}

	@Override
	public List<Evaluador> findEvaluadorByProgramaId(Integer id) {


		String queryString = "from Evaluador where programa.programaID = ?";
		Object[] params = new Object[1];
		params[0] = id;
		
		return super.find(queryString, params);
	}

	@Override
	public List<Evaluador> findComiteTecnicoByFiltro(Evaluador evaluador,Integer idFiltro) {
		//tipo periodo
		if (idFiltro== 100) {
			String queryString = "from Evaluador where programa.tipoPeriodo.tipoPeriodoID= ?";
			Object[] params = new Object[1];
			params[0] = evaluador.getPrograma().getTipoPeriodo().getTipoPeriodoID();
			return super.find(queryString,params);
			//tipo cuenta
		}else if (idFiltro== 101) {
			String queryString = "from Evaluador where programa.fkIdtablaespTipoCuenta=?  ";
			Object[] params = new Object[1];
			params[0] = evaluador.getPrograma().getFkIdtablaespTipoCuenta();
			return super.find(queryString,params);
		}else if (idFiltro== 102) {
			String queryString = "from Evaluador where programa.fkIdtablaespModalidadFinancia=?  ";
			Object[] params = new Object[1];
			params[0] = evaluador.getPrograma().getFkIdtablaespModalidadFinancia();
			return super.find(queryString,params);
		}else if (idFiltro== 103) {
			String queryString = "from Evaluador where programa.nombrePrograma like '%"+ evaluador.getPrograma().getNombrePrograma()+"%'";
			return super.find(queryString);
		}else if (idFiltro== 105) {
			String queryString = "from Evaluador where datoUsuario.nombre like '%"+evaluador.getDatoUsuario().getNombre()+"%'";
			return super.find(queryString);
		}else if (idFiltro== 106) {
			Object[] params = new Object[1];
			params[0] = evaluador.getFkIdtablaespTipoEvaluacion();
			String queryString = "from Evaluador where fkIdtablaespTipoEvaluacion=?";
			return super.find(queryString,params);
		}

		return null;
	}
	@Override
	public List<Evaluador> findEvaluadorByTipoEvaluacion(Integer evaluadorID,Integer tipoEvaluacion) {
		Object[] params = new Object[2];
		params[0] =evaluadorID ;
		params[1] = tipoEvaluacion;
		String queryString = "from Evaluador where datoUsuario.datoUsuarioID=? and fkIdtablaespTipoEvaluacion=? ";
		return super.find(queryString,params);
	}
	@Override
	public List<Evaluador> findEvaluadorByTipoEvaluacionByFiltroPrograma(Integer evaluadorID,Integer tipoEvaluacion,int idFiltro,int valor) {

		if (idFiltro== 100) {
			String queryString = "from Evaluador where datoUsuario.datoUsuarioID=?  and fkIdtablaespTipoEvaluacion=? and programa.tipoPeriodo.tipoPeriodoID= ?";
			Object[] params = new Object[3];
			params[0] =evaluadorID ;
			params[1] = tipoEvaluacion;
			params[2] = valor;
			return super.find(queryString,params);
			//tipo cuenta
		}else if (idFiltro== 101) {
			String queryString = "from Evaluador where datoUsuario.datoUsuarioID=?  and fkIdtablaespTipoEvaluacion=? and programa.fkIdtablaespTipoCuenta= ?";
			Object[] params = new Object[3];
			params[0] =evaluadorID ;
			params[1] = tipoEvaluacion;
			params[2] = valor;
			return super.find(queryString,params);
		}else if (idFiltro== 102) {
			String queryString = "from Evaluador where datoUsuario.datoUsuarioID=?  and fkIdtablaespTipoEvaluacion=? and programa.fkIdtablaespModalidadFinancia=?  ";
			Object[] params = new Object[3];
			params[0] =evaluadorID ;
			params[1] = tipoEvaluacion;
			params[2] = valor;
			return super.find(queryString,params);
		}
		return null;

	}

	@Override
	public List<Evaluador> findComiteTecnicoByFiltro(Integer usuarioID,
			Integer evaluacion, int idFiltro, String dato) {
		//tipo periodo
		if (idFiltro== 100) {
			String queryString = "from Evaluador where programa.tipoPeriodo.tipoPeriodoID= ? and datoUsuario.datoUsuarioID=? and fkIdtablaespTipoEvaluacion=?";
			Object[] params = new Object[3];
			params[0] =Integer.parseInt(dato);
			params[1] =usuarioID;
			params[2] =evaluacion;
			return super.find(queryString,params);
			//tipo cuenta
		}else if (idFiltro== 101) {
			String queryString = "from Evaluador where programa.fkIdtablaespTipoCuenta=? and datoUsuario.datoUsuarioID=? and fkIdtablaespTipoEvaluacion=?  ";
			Object[] params = new Object[3];
			params[0] =Integer.parseInt(dato);
			params[1] =usuarioID;
			params[2] =evaluacion;
			return super.find(queryString,params);
		}else if (idFiltro== 102) {
			String queryString = "from Evaluador where programa.fkIdtablaespModalidadFinancia=? and datoUsuario.datoUsuarioID=? and fkIdtablaespTipoEvaluacion=?  ";
			Object[] params = new Object[3];
			params[0] =Integer.parseInt(dato);
			params[1] =usuarioID;
			params[2] =evaluacion;
			return super.find(queryString,params);
		}else if (idFiltro== 103) {
			String nombre= dato.toString();
			String queryString = "from Evaluador where programa.nombrePrograma like '%"+ nombre+"%' and fkIdtablaespTipoEvaluacion=? and datoUsuario.datoUsuarioID=?" ;
			Object[] params = new Object[2];
			params[0] =usuarioID;
			params[1] =evaluacion;
			
			return super.find(queryString);
		}else if (idFiltro== 105) {
			String nombre= dato.toString();
			String queryString = "from Evaluador where datoUsuario.nombre like '%"+nombre+"%'";
			return super.find(queryString);
		}else if (idFiltro== 106) {
			String queryString = "from Evaluador where fkIdtablaespTipoEvaluacion=? and datoUsuario.datoUsuarioID=? ";

			Object[] params = new Object[2];
			params[0] =evaluacion;
			params[1] =usuarioID;
			return super.find(queryString,params);
		}

		return null;
	}

	@Override
	public Evaluador findEvaluadorByDatoUsuarioIDAndTipoEvaluacionAndProyectoID(Evaluador evaluador) {
		
		Evaluador evaluado = null;
		String queryString = "from Evaluador where datoUsuario.datoUsuarioID = ? and  fkIdtablaespTipoEvaluacion= ? and programa.programaID=? ";
		Object[] params = new Object[3];
		params[0] = evaluador.getDatoUsuario().getDatoUsuarioID();
		params[1] = evaluador.getFkIdtablaespTipoEvaluacion();
		params[2] = evaluador.getPrograma().getProgramaID();
	
		List<Evaluador> evaluadores = super.find(queryString, params);
		if(evaluadores!=null && evaluadores.size()!=0){
			evaluado = evaluadores.get(0);
		}
		return evaluado;
	}

	@Override
	public List<Evaluador> obtenerEvaluadorByProgramaByTipoEvaluacion(Integer programaID, int tipoEvaluacion) {
		String queryString = "from Evaluador where fkIdtablaespTipoEvaluacion =? and programa.programaID=? order by 1";
		Object[] params = new Object[2];
		params[0] = tipoEvaluacion;
		params[1] = programaID;

		return  super.find(queryString, params);

	}

	@Override
	public Evaluador findEvaluadorByID(Integer idEvaluador) {
		return super.findById(Evaluador.class, idEvaluador);
	}


}
