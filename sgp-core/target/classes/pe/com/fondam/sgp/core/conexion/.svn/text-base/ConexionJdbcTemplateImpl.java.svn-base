package pe.com.fondam.sgp.core.conexion;


import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class ConexionJdbcTemplateImpl implements ConexionJdbcTemplate{

	private SimpleJdbcTemplate jdbcTemplate;
	
	@Autowired
	public ConexionJdbcTemplateImpl(DataSource dataSource) {
		this.jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}
	
	public String deleteCuentasCorrientesByProyectoId(Integer idProyecto){

		String rpt=null;
		try {
			List <Object[]> listData=new ArrayList<Object[]>();
			Object ArrayObj[] = new Object[1];
			Object idProyectoObj= idProyecto;
			ArrayObj[0]= idProyectoObj;
			listData.add(ArrayObj);
			String sql = "DELETE FROM cuenta_corriente WHERE Dato_Proyecto_ID=?";
			jdbcTemplate.batchUpdate(sql, listData);
			rpt="true";
			
		} catch (Exception e) {
			rpt="false";
		}
		return rpt;
	}
	
	public String deleteCuentaCorrientesByCuentaIdByProyectoId(String cuentaCorriente, Integer idProyecto){

		String rpt=null;
		try {
			List <Object[]> listData=new ArrayList<Object[]>();
			Object ArrayObj[] = new Object[2];
			Object idProyectoObj= idProyecto;
			ArrayObj[0]= idProyectoObj;
			Object cuentaCorrienteObj = cuentaCorriente;
			ArrayObj[1]= cuentaCorrienteObj;
			listData.add(ArrayObj);
			String sql = "DELETE FROM cuenta_corriente WHERE Dato_Proyecto_ID=? and numero_cuenta=? ";
			jdbcTemplate.batchUpdate(sql, listData);
			rpt="true";
			
		} catch (Exception e) {
			rpt="false";
		}
		return rpt;
	}

	public String deleteResumenProyectoByIdByProyectoId(Integer idTipoResumen, Integer idProyecto){

		String rpt=null;
		try {
			List <Object[]> listData=new ArrayList<Object[]>();
			Object ArrayObj[] = new Object[2];
			Object idProyectoObj= idProyecto;
			ArrayObj[0]= idProyectoObj;
			Object idTipoResumenProy = idTipoResumen;
			ArrayObj[1]= idTipoResumenProy;
			listData.add(ArrayObj);
			String sql = "DELETE FROM resumen_proyecto where Dato_Proyecto_ID=? and fk_idtablaesp_tipo_resumen_proy=?  ";
			jdbcTemplate.batchUpdate(sql, listData);
			rpt="true";
			
		} catch (Exception e) {
			rpt="false";
		}
		return rpt;
	}
	
	public String deleteUbicacionProyectoById(Integer idUbicacionProyecto){
		String rpt=null;
		try {
			List <Object[]> listData=new ArrayList<Object[]>();
			Object ArrayObj[] = new Object[1];
			Object idProyectoObj= idUbicacionProyecto;
			ArrayObj[0]= idProyectoObj;
			listData.add(ArrayObj);
			String sql = "DELETE FROM ubicacion_proyecto WHERE ubicacion_proyecto_ID=?";
			jdbcTemplate.batchUpdate(sql, listData);
			rpt="true";
			
		} catch (Exception e) {
			rpt="false";
		}
		return rpt;
	}

	public String deleteBeneficiariosByUbicacionProyecyoId(Integer ubicacionProyectoId){
		String rpt=null;
		try {
			List <Object[]> listData=new ArrayList<Object[]>();
			Object ArrayObj[] = new Object[1];
			Object idUbiProyectoObj= ubicacionProyectoId;
			ArrayObj[0]= idUbiProyectoObj;
			listData.add(ArrayObj);
			String sql = "DELETE FROM beneficiarios_por_resultado WHERE Ubicacion_Proyecto_ID=?";
			jdbcTemplate.batchUpdate(sql, listData);
			rpt="true";
		} catch (Exception e) {
			rpt="false";
		}
		return rpt;
		
	}
	
	public String deleteFuenteFinanciadoraByProyectoId(Integer idProyecto){
		String rpt=null;
		try {
			List <Object[]> listData=new ArrayList<Object[]>();
			Object ArrayObj[] = new Object[1];
			Object idUbiProyectoObj= idProyecto;
			ArrayObj[0]= idUbiProyectoObj;
			listData.add(ArrayObj);
			String sql = "DELETE FROM fuente_financiadora WHERE Dato_Proyecto_ID=?";
			jdbcTemplate.batchUpdate(sql, listData);
			rpt="true";
		} catch (Exception e) {
			rpt="false";
		}
		return rpt;
	}
	
	public String deleteInstitucionById(Integer institucionId){
		String rpt=null;
		try {
			List <Object[]> listData=new ArrayList<Object[]>();
			Object ArrayObj[] = new Object[1];
			Object idUbiProyectoObj= institucionId;
			ArrayObj[0]= idUbiProyectoObj;
			listData.add(ArrayObj);
			String sql = "DELETE FROM institucion WHERE Institucion_ID=?";
			jdbcTemplate.batchUpdate(sql, listData);
			rpt="true";
		} catch (Exception e) {
			rpt="false";
		}
		return rpt;
	}
	
	public String deleteResumenProyectoByProyectoId(Integer idProyecto){
		String rpt=null;
		try {
			List <Object[]> listData=new ArrayList<Object[]>();
			Object ArrayObj[] = new Object[1];
			Object idUbiProyectoObj= idProyecto;
			ArrayObj[0]= idUbiProyectoObj;
			listData.add(ArrayObj);
			String sql = "DELETE FROM resumen_proyecto WHERE Dato_Proyecto_ID=?";
			jdbcTemplate.batchUpdate(sql, listData);
			rpt="true";
		} catch (Exception e) {
			rpt="false";
		}
		return rpt;
	}
	
	public String deleteMarcoLogicoByProyectoId(Integer idProyecto){
		String rpt=null;
		try {
			List <Object[]> listData=new ArrayList<Object[]>();
			Object ArrayObj[] = new Object[1];
			Object idUbiProyectoObj= idProyecto;
			ArrayObj[0]= idUbiProyectoObj;
			listData.add(ArrayObj);
			String sql = "DELETE FROM marco_logico WHERE Dato_Proyecto_ID=?";
			jdbcTemplate.batchUpdate(sql, listData);
			rpt="true";
		} catch (Exception e) {
			rpt="false";
		}
		return rpt;
	}
	
	public String deleteIndicadorMarcoLogicoByMarcoLogicoId(Integer idMarcoLogico){
		String rpt=null;
		try {
			List <Object[]> listData=new ArrayList<Object[]>();
			Object ArrayObj[] = new Object[1];
			Object idUbiProyectoObj= idMarcoLogico;
			ArrayObj[0]= idUbiProyectoObj;
			listData.add(ArrayObj);
			String sql = "DELETE FROM indicador_marco_logico WHERE Marco_Logico_ID=?";
			jdbcTemplate.batchUpdate(sql, listData);
			rpt="true";
		} catch (Exception e) {
			rpt="false";
		}
		return rpt;
	}
	
}
