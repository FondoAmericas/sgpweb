package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.ReporteAvanceDAO;
import pe.com.fondam.sgp.core.domain.ReporteAvance;
/**
*
*/
@Repository
public class ReporteAvanceDAOImpl extends JpaBaseDAOImpl implements ReporteAvanceDAO {
	//************  inyecciones  ****************//
	@Autowired
	public ReporteAvanceDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}
	
	//************  metodos ****************//
	@Override
	public ReporteAvance updateReporteAvance(
			ReporteAvance reporteAvance) {
		return super.update(reporteAvance);
	}

	@Override
	public void deleteReporteAvance(
			ReporteAvance reporteAvance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ReporteAvance findReporteAvanceById(Integer reporteAvanceId) {
		return super.findById(ReporteAvance.class, reporteAvanceId);
	}

/*
	@Override
	public List<ReporteAvance> findReporteAvanceByDatoProyectoID(
			Integer datoProyectoID) {
		String queryString = "from ReporteAvance  where  datoProyecto.datoProyectoID= ? ";
		Object[] params = new Object[1];
		params[0] = datoProyectoID;
		List<ReporteAvance> listReporteAvance = super.find(queryString, params);

		return listReporteAvance;

		
	}
	*/


	@Override
	public List<ReporteAvance> findReporteAvanceXConsulta(
			String consulta, Object[] params) {
		return super.find(consulta, params);
	}
	
	
	public int getCantidadReporteAvanceByProyectoIdByEstadoRepAvanceGenerado(Integer datoProyectoID) {
		String queryString = "from ReporteAvance where datoProyecto.datoProyectoID = ? ";//and fkIdDetalleEstadoCabEstRepAvance=34 ";//34 estado reporte avance(Reporte Generado)
		Object[] params = new Object[1];
		params[0] = datoProyectoID;
		List<ReporteAvance> listReporteAvance = super.find(queryString, params);
		
		return listReporteAvance.size();
	}
	
	public List<ReporteAvance> findReporteAvanceByProyectoIdByEstadoRepAvanceGenerado(Integer datoProyectoID) {
		String queryString = "from ReporteAvance where datoProyecto.datoProyectoID = ? order by reporteAvanceID asc ";//and fkIdDetalleEstadoCabEstRepAvance=34 order by reporteAvanceID asc";//34 estado reporte avance(Reporte Generado)
		Object[] params = new Object[1];
		params[0] = datoProyectoID;
		List<ReporteAvance> listReporteAvance = super.find(queryString, params);
		
		return listReporteAvance;
	}

}
