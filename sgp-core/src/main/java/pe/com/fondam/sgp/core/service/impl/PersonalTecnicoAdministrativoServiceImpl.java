package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.DatoPlanOperativoDAO;
import pe.com.fondam.sgp.core.dao.PersonalTecnicoAdministrativoDAO;
import pe.com.fondam.sgp.core.dao.TablaEspecificaDAO;
import pe.com.fondam.sgp.core.domain.PersonalTecnicoAdministrativo;
import pe.com.fondam.sgp.core.domain.TablaEspecifica;
import pe.com.fondam.sgp.core.service.PersonalTecnicoAdministrativoService;

@Service
public class PersonalTecnicoAdministrativoServiceImpl implements
		PersonalTecnicoAdministrativoService {

	/********************** inyecciones ***************************/
	@Resource
	private DatoPlanOperativoDAO datoPlanOperativoDAO;
	
	@Resource
	private TablaEspecificaDAO tablaEspecificaDAO;
	
	public DatoPlanOperativoDAO getDatoPlanOperativoDAO() {
		return datoPlanOperativoDAO;
	}

	public void setDatoPlanOperativoDAO(DatoPlanOperativoDAO datoPlanOperativoDAO) {
		this.datoPlanOperativoDAO = datoPlanOperativoDAO;
	}

	@Resource
	private PersonalTecnicoAdministrativoDAO personalTecnicoAdministrativoDAO;
	
	public PersonalTecnicoAdministrativoDAO getPersonalTecnicoAdministrativoDAO() {
		return personalTecnicoAdministrativoDAO;
	}

	public void setPersonalTecnicoAdministrativoDAO(
			PersonalTecnicoAdministrativoDAO personalTecnicoAdministrativoDAO) {
		this.personalTecnicoAdministrativoDAO = personalTecnicoAdministrativoDAO;
	}

	/********************** metodos ***************************/
	@Override
	public List<PersonalTecnicoAdministrativo> findPersonalTecnicoReemplazadoXDatoProyectoId(
			Integer datoProyectoID) {

		//DatoPlanOperativo datoPlanOperativo= datoPlanOperativoDAO.findDatoPlanOperativoByDatoProyectoID(datoProyectoID);
		
		String consulta=" from PersonalTecnicoAdministrativo where datoProyecto.datoProyectoID = ? ";
		
		Object[] params = new Object[1];
		params[0]= datoProyectoID;
		
		return personalTecnicoAdministrativoDAO.findPersonalTecnicoAdministrativoXConsulta(consulta,params);
	
	}
	
	@Override
	public PersonalTecnicoAdministrativo updatePersonalTecnicoAdministrativo(PersonalTecnicoAdministrativo personalTA) {
		return personalTecnicoAdministrativoDAO.updatePersonalTecnicoAdministrativo(personalTA);
		
	}

	@Override
	public PersonalTecnicoAdministrativo findPersonalTecnicoAdministrativoById(
			int fkIdpersonalTecnicoAdmReemplazo) {
		return personalTecnicoAdministrativoDAO.findPersonalTecnicoAdministrativoById(fkIdpersonalTecnicoAdmReemplazo);
	}

	public List<PersonalTecnicoAdministrativo> findPersonalTecnicoAdministrativoByDatoProyectoId(Integer datoProyectoID) {
        
		String consulta=" from PersonalTecnicoAdministrativo where datoProyecto.datoProyectoID = ? ";	
		Object[] params = new Object[1];
		params[0]= datoProyectoID;
		/*if(tipoConsulta == FondamConstans.CONSULTA_PERSONAL_TEC_ADM_POR_PLAN_OPERATIVO){//por plan operativo
			consulta=" from PersonalTecnicoAdministrativo where datoPlanOperativo.datoPlanOperativoID = ? ";
			params[0]= datoID;
		}else if(tipoConsulta == FondamConstans.CONSULTA_PERSONAL_TEC_ADM_POR_REPORTE_AVANCE){
			consulta=" from PersonalTecnicoAdministrativo where reporteAvance.reporteAvanceID = ? ";
			params[0]= datoID;
		}*/		
		
		List<PersonalTecnicoAdministrativo> listPersonalTecnicoAdministrativo = personalTecnicoAdministrativoDAO.findPersonalTecnicoAdministrativoXConsulta(consulta,params);
		
		for (PersonalTecnicoAdministrativo personalTecnicoAdministrativo : listPersonalTecnicoAdministrativo) {
			personalTecnicoAdministrativo.setEtapaPersonalTecnicoNombre(this.getTablaEspecificaDescripcion(personalTecnicoAdministrativo.getFkIdtablaespEtapaPersonalTecnico()));
			personalTecnicoAdministrativo.setFormacionProfesionalNombre(this.getTablaEspecificaDescripcion(personalTecnicoAdministrativo.getFkIdtablaespFormacionProfesional()));
			personalTecnicoAdministrativo.setTiempoDedicadoNombre(this.getTablaEspecificaDescripcion(personalTecnicoAdministrativo.getFkIdtablaespTiempoDedicado()));
			personalTecnicoAdministrativo.setTipoMonedaNombre(this.getTablaEspecificaDescripcion(personalTecnicoAdministrativo.getFkIdtablaespTipoMoneda()));
			for (PersonalTecnicoAdministrativo personalTecnicoAdministrativo2 : listPersonalTecnicoAdministrativo) {
				 if(personalTecnicoAdministrativo.getFkIdpersonalTecnicoAdmReemplazo() == personalTecnicoAdministrativo2.getPersonalTecnicoAdministrativoID()){
					personalTecnicoAdministrativo.setPersonalTecnicoAdmReemplazoNombre(personalTecnicoAdministrativo2.getNombreCompleto());
					break;
				 }else{
					 personalTecnicoAdministrativo.setPersonalTecnicoAdmReemplazoNombre("No hay reemplazo."); 
				 }
			}
		}
		return listPersonalTecnicoAdministrativo;
	}
	
	public String getTablaEspecificaDescripcion(Integer tablaEspecificaID) {
		String descripcion = "";
		if (tablaEspecificaID != null) {
			TablaEspecifica tablaEspecifica = tablaEspecificaDAO.findTablaEspecificaById(tablaEspecificaID);
			if (tablaEspecifica != null) {
				descripcion = tablaEspecifica.getDescripcionCabecera();
			}
		}
		return descripcion;
	}

	@Override
	public void deletePersonalTecnicoAdministrativo(
			Integer personalTecnicoAdministrativoID) {
		personalTecnicoAdministrativoDAO.deletePersonalTecnicoAdministrativo(personalTecnicoAdministrativoDAO.findPersonalTecnicoAdministrativoById(personalTecnicoAdministrativoID));
		
	}

}
