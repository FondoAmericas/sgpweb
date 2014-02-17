package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.ProgramaDAO;
import pe.com.fondam.sgp.core.domain.ActividadObligatoriaPrograma;
import pe.com.fondam.sgp.core.domain.DatoProyecto;
import pe.com.fondam.sgp.core.domain.ImagenOArchivo;
import pe.com.fondam.sgp.core.domain.Programa;
import pe.com.fondam.sgp.core.domain.PuntajeEvaluacion;
import pe.com.fondam.sgp.core.domain.RestriccionDepProvDist;
import pe.com.fondam.sgp.core.domain.RestriccionSubAreaTematica;
import pe.com.fondam.sgp.core.domain.RestricionPrograma;


@Repository
public class ProgramaDAOImpl extends JpaBaseDAOImpl implements ProgramaDAO {

	//**************** inyecciones *********************//
	@Autowired
	public ProgramaDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	//**************** metodos *********************//
	@Override
	public Programa updatePrograma(Programa programa) {

		return super.update(programa);
	}

	@Override
	public void deletePrograma(Programa programa) {
		
		//verifico si existe proyectos
		String queryString1 = "from DatoProyecto where programa.programaID= ? ";
		Object[] params1 = new Object[1];
		params1[0] = programa.getProgramaID();

		List<DatoProyecto> listProyectos = super.find(queryString1, params1);
		if (listProyectos!= null) {
			//verificar si existen puntajes para este programa
			String queryString2 = "from PuntajeEvaluacion where programa.programaID= ? ";
			Object[] params2 = new Object[1];
			params2[0] = programa.getProgramaID();

			List<PuntajeEvaluacion> listPuntaje = super.find(queryString2, params2);
			// si la lista esta llena; eliminar
			if (listPuntaje!= null) {
				for (int i = 0; i < listPuntaje.size(); i++) {
					super.delete(listPuntaje.get(i));
				}
			
			}

			//verificar si existen actividades obligatorias para este programa
			String queryString3 = "from ActividadObligatoriaPrograma where programa.programaID= ? ";
			Object[] params3 = new Object[1];
			params3[0] = programa.getProgramaID();

			List<ActividadObligatoriaPrograma> listActividadObligatoria = super.find(queryString3, params3);
			// si la lista esta llena; eliminar
			if (listActividadObligatoria!= null) {
				for (int i = 0; i < listActividadObligatoria.size(); i++) {
					super.delete(listActividadObligatoria.get(i));
				}
			
			}
			
			//verificar si existen actividades obligatorias para este programa
			String queryString4 = "from RestricionPrograma where programa.programaID= ? ";
			Object[] params4 = new Object[1];
			params4[0] = programa.getProgramaID();

			List<RestricionPrograma> listRestricionPrograma = super.find(queryString4, params4);
			// si la lista esta llena; eliminar
			if (listRestricionPrograma!= null) {
				for (int i = 0; i < listRestricionPrograma.size(); i++) {
					super.delete(listRestricionPrograma.get(i));
				}
			
			}

			//verificar si existen RestriccionDepProvDist para este programa
			String queryString5 = "from RestriccionDepProvDist where programa.programaID= ? ";
			Object[] params5 = new Object[1];
			params5[0] = programa.getProgramaID();

			List<RestriccionDepProvDist> listRestriccionDepProvDist = super.find(queryString5, params5);
			// si la lista esta llena; eliminar
			if (listRestriccionDepProvDist!= null) {
				for (int i = 0; i < listRestriccionDepProvDist.size(); i++) {
					super.delete(listRestriccionDepProvDist.get(i));
				}
			
			}
			
			//verificar si existen RestriccionSubAreaTematica para este programa
			String queryString6 = "from RestriccionSubAreaTematica where programa.programaID= ? ";
			Object[] params6 = new Object[1];
			params6[0] = programa.getProgramaID();

			List<RestriccionSubAreaTematica> listRestriccionSubAreaTematica = super.find(queryString6, params6);
			// si la lista esta llena; eliminar
			if (listRestriccionSubAreaTematica!= null) {
				for (int i = 0; i < listRestriccionSubAreaTematica.size(); i++) {
					super.delete(listRestriccionSubAreaTematica.get(i));
				}
			
			}
			
			//verificar si existen RestriccionSubAreaTematica para este programa
			String queryString7 = "from ImagenOArchivo where programa.programaID= ? ";
			Object[] params7 = new Object[1];
			params7[0] = programa.getProgramaID();

			List<ImagenOArchivo> listImagenOArchivo = super.find(queryString7, params7);
			// si la lista esta llena; eliminar
			if (listImagenOArchivo != null) {
				for (int i = 0; i < listImagenOArchivo.size(); i++) {
					super.delete(listImagenOArchivo.get(i));
				}
			
			}

			Programa prog =super.findById(Programa.class, programa.getProgramaID());
			super.delete(prog);

		}
		
		

	}

	@Override
	public Programa findProgramaById(Integer id) {
	
		return super.findById(Programa.class, id);
	}

	@Override
	public List<Programa> findProgramas() {
		return super.find("from Programa");
	}

	@Override
	public Integer savePrograma(Programa programa) {
		// TODO Auto-generated method stub
		super.save(programa);
		
		return programa.getProgramaID();
	}
	
	@Override
	public List<Programa> findProgramaByFiltro(Programa programa,Integer idFiltro) {
		//tipo periodo
		if (idFiltro== 100) {
			String queryString = "from Programa where tipoPeriodo.tipoPeriodoID= ?";
			Object[] params = new Object[1];
			params[0] = programa.getTipoPeriodo().getTipoPeriodoID();
			return super.find(queryString,params);
			//tipo cuenta
		}else if (idFiltro== 101) {
			String queryString = "from Programa where fkIdtablaespTipoCuenta=?  ";
			Object[] params = new Object[1];
			params[0] = programa.getFkIdtablaespTipoCuenta();
			return super.find(queryString,params);
		}else if (idFiltro== 102) {
			String queryString = "from Programa where fkIdtablaespModalidadFinancia=?  ";
			Object[] params = new Object[1];
			params[0] = programa.getFkIdtablaespModalidadFinancia();
			return super.find(queryString,params);
		}else if (idFiltro== 103) {
			String queryString = "from Programa where nombrePrograma like '%"+programa.getNombrePrograma()+"%'";
			return super.find(queryString);
		}

		return null;
	}
	
	public List<Programa> findProgramaByNombre(String nombrePrograma) {
		String queryString = "from Programa where nombrePrograma like '%"+nombrePrograma+"%'";
		return super.find(queryString);
	}

	public List<Programa> findProgramaByModFinan(int modFinan) {
		String queryString = "from Programa where fkIdtablaespModalidadFinancia=?  ";
		Object[] params = new Object[1];
		params[0] = modFinan;
		return super.find(queryString,params);
	}
	
	public List<Programa> findProgramaByTipoCuenta(int tipoCuenta) {
		String queryString = "from Programa where fkIdtablaespTipoCuenta=?  ";
		Object[] params = new Object[1];
		params[0] = tipoCuenta;
		return super.find(queryString,params);
	}
	
}
