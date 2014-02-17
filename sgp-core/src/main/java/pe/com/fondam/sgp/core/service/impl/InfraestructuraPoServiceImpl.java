package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.DatoPlanOperativoDAO;
import pe.com.fondam.sgp.core.dao.InfraestructuraPoDAO;
import pe.com.fondam.sgp.core.domain.InfraestructuraPo;
import pe.com.fondam.sgp.core.service.ActivoService;
import pe.com.fondam.sgp.core.service.InfraestructuraPoService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;

@Service
public class InfraestructuraPoServiceImpl implements InfraestructuraPoService {
	//***************  inyecciones  ******************//
	@Resource
	InfraestructuraPoDAO infraestructuraPoDAO;
	
	@Resource
	DatoPlanOperativoDAO datoPlanOperativoDAO;
	
	@Resource
	ActivoService activoService;
	
	@Resource
	TablaEspecificaService tablaEspecificaService;
	
	
	//***************  metodos ******************//
	@Override
	public List<InfraestructuraPo> findInfraestructuraPoByDatoProyectoId(
			Integer datoProyectoID) {
		String consulta= " from InfraestructuraPo where datoProyecto.datoProyectoID = ? ";
		
		Object[] params= new Object[1];
		params[0]=datoProyectoID;
		
		List<InfraestructuraPo> listInfraestructuraPo= llenaInfraestructuraPoCompleto( infraestructuraPoDAO.findInfraestructuraPoByConsulta(consulta,params));
		/*InfraestructuraPo infraestructuraPo= null;
		if(listInfraestructuraPo.size()>0){
			infraestructuraPo=listInfraestructuraPo.get(0);
		}*/
		return listInfraestructuraPo;
	}

	@Override
	public InfraestructuraPo saveInfraestructuraPoVacio(Integer datoPlanOperativoID) {
		InfraestructuraPo infraestructuraPo = new InfraestructuraPo();
		
		infraestructuraPo.setDatoProyecto(datoPlanOperativoDAO.findDatoPlanOperativoById(datoPlanOperativoID).getDatoProyecto());
		infraestructuraPo.setDescripcion("Sin especificar");
		infraestructuraPo.setUbicacion("Sin especificar");
		
		return infraestructuraPoDAO.updateInfraestructuraPo(infraestructuraPo);
	}

	@Override
	public InfraestructuraPo findInfraestructuraPoById(
			Integer infraestructuraPOID) {
		return infraestructuraPoDAO.findInfraestructuraPoById(infraestructuraPOID);
	}

	@Override
	public InfraestructuraPo updateInfraestructuraPo(
			InfraestructuraPo infraestructuraPo) {
		return infraestructuraPoDAO.updateInfraestructuraPo(infraestructuraPo);
	}

	
	//*************** Listas ************************//

	private List<InfraestructuraPo> llenaInfraestructuraPoCompleto(
			List<InfraestructuraPo> listInfraestructuraPo) {
		
		for (InfraestructuraPo infraestructuraPo : listInfraestructuraPo) {
			infraestructuraPo.setDescripcionCategoriaActivo(tablaEspecificaService.findTablaEspecificaById(infraestructuraPo.getActivo().getFkIdtablaespCategoriaActivo()).getDescripcionCabecera());
			infraestructuraPo.setDescripcionActivo(infraestructuraPo.getActivo().getDescripcionActivo());
		}
		
		return listInfraestructuraPo;
	}

	@Override
	public void deleteInfraestructuraPo(Integer infraestructuraPOID) {
		infraestructuraPoDAO.deleteInfraestructuraPo(infraestructuraPoDAO.findInfraestructuraPoById(infraestructuraPOID));
		
	}

}
