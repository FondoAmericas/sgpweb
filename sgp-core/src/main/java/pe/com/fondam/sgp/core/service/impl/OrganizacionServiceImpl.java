package pe.com.fondam.sgp.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.bean.OrganizacionBean;
import pe.com.fondam.sgp.core.dao.OrganizacionDAO;
import pe.com.fondam.sgp.core.domain.Organizacion;
import pe.com.fondam.sgp.core.service.OrgBienTranferidoService;
import pe.com.fondam.sgp.core.service.OrganizacionService;

@Service
public class OrganizacionServiceImpl implements OrganizacionService {


	//*****************  inyecciones  *****************//
	@Resource
	OrganizacionDAO organizacionDAO;

	@Resource
	OrgBienTranferidoService orgBienTranferidoService;
	
	
	//*****************  metodos  *****************//
	@Override
	public List<Organizacion> findOrganizacionByPropuestaTransferenciaId(
			Integer propuestaTransferenciaId) {

		String consulta=" from Organizacion where propuestaTransferencia.propuestaTransferenciaID = ? ";
		Object[] params =new Object[1];
		params[0]=propuestaTransferenciaId;
		
		return organizacionDAO.findOrganizacionByConsulta(consulta,params);
	}


	@Override
	public Organizacion updateOrganizacion(Organizacion organizacion) {

		return organizacionDAO.updateOrganizacion(organizacion);
	}


	@Override
	public Organizacion findOrganizacionById(
			Integer sltOrganizacionBienTrnasferido) {
		return organizacionDAO.findOrganizacionById(sltOrganizacionBienTrnasferido);
	}


	@Override
	public List<Organizacion> findOrganizacionByDatoProyectoId(
			Integer datoProyectoId) {
		String consulta=" from Organizacion where datoProyecto.datoProyectoID = ? ";
		Object[] params =new Object[1];
		params[0]=datoProyectoId;
		
		return organizacionDAO.findOrganizacionByConsulta(consulta,params);
	}


	@Override
	public void deleteOrganizacion(Integer idRegistro) {
		organizacionDAO.deleteOrganizacion(findOrganizacionById(idRegistro));
		
	}


	@Override
	public List<OrganizacionBean> llenaListOrganizacionBean(
			List<Organizacion> listOrganizacion) {
		
		List<OrganizacionBean> listOrganizacionBean= new ArrayList<OrganizacionBean>();
		for (Organizacion organizacion : listOrganizacion) {
			listOrganizacionBean.add(llenaOrganizacionBean(organizacion));
		}
		return listOrganizacionBean;
	}


	private OrganizacionBean llenaOrganizacionBean(Organizacion organizacion) {
		OrganizacionBean organizacionBean= new OrganizacionBean();
		
		organizacionBean.setDatoProyecto(organizacion.getDatoProyecto());
		organizacionBean.setInformeFinal(organizacion.getInformeFinal());
		organizacionBean.setNombreOrganizacion(organizacion.getNombreOrganizacion());
		organizacionBean.setOrganizacionID(organizacion.getOrganizacionID());
		organizacionBean.setPropuestaTransferencia(organizacion.getPropuestaTransferencia());
		organizacionBean.setSituacionFinal(organizacion.getSituacionFinal());
		organizacionBean.setCantidadOrgEnBienTransferido(orgBienTranferidoService.findOrgBienTranferidoByOrganizacionId(organizacion.getOrganizacionID()));
		return organizacionBean;
	}
}
