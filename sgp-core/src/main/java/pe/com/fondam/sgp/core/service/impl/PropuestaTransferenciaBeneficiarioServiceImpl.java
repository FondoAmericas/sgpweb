package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.PropuestaTransferenciaBeneficiarioDAO;
import pe.com.fondam.sgp.core.domain.PropuestaTransferenciaBeneficiario;
import pe.com.fondam.sgp.core.service.PropuestaTransferenciaBeneficiarioService;

@Service
public class PropuestaTransferenciaBeneficiarioServiceImpl implements
		PropuestaTransferenciaBeneficiarioService {

	//********* inyecciones ******************/
	@Resource
	PropuestaTransferenciaBeneficiarioDAO propuestaTransferenciaBeneficiarioDAO;
	
	//********* metodos ******************/
	@Override
	public PropuestaTransferenciaBeneficiario findPropuestaTransferenciaBeneficiarioByBeneficiarioId(
			Integer beneficiariosPorResultadoID) {
		String consulta = " from PropuestaTransferenciaBeneficiario where beneficiariosPorResultado.beneficiariosPorResultadoID = ? ";
		Object[] params= new Object[1];
		params[0]=beneficiariosPorResultadoID;
		
		List<PropuestaTransferenciaBeneficiario> listPropuestaTransferenciaBeneficiario =propuestaTransferenciaBeneficiarioDAO.findPropuestaTransferenciaBeneficiarioByConsulta(consulta, params);  
		PropuestaTransferenciaBeneficiario propuestaTransferenciaBeneficiario= null;
		if(listPropuestaTransferenciaBeneficiario.size()>0){
			propuestaTransferenciaBeneficiario= listPropuestaTransferenciaBeneficiario.get(0);
		}
		return propuestaTransferenciaBeneficiario;
	}
	
	@Override
	public List<PropuestaTransferenciaBeneficiario> findPropuestaTransferenciaBeneficiarioByBeneficiarioPorResultadoId(
			Integer beneficiariosPorResultadoID) {
		String consulta = " from PropuestaTransferenciaBeneficiario where beneficiariosPorResultado.beneficiariosPorResultadoID = ? ";
		Object[] params= new Object[1];
		params[0]=beneficiariosPorResultadoID;
		
		List<PropuestaTransferenciaBeneficiario> listPropuestaTransferenciaBeneficiario =propuestaTransferenciaBeneficiarioDAO.findPropuestaTransferenciaBeneficiarioByConsulta(consulta, params);  
		
		return listPropuestaTransferenciaBeneficiario;
	}

	@Override
	public void savePropuestaTransferenciaBeneficiario(
			PropuestaTransferenciaBeneficiario propuestaTransferenciaBeneficiario) {
		
			String consulta =" from PropuestaTransferenciaBeneficiario where propuestaTransferencia.propuestaTransferenciaID = ? and beneficiariosPorResultado.beneficiariosPorResultadoID = ? ";
			Object[] params= new Object[2];
			params[0]= propuestaTransferenciaBeneficiario.getPropuestaTransferencia().getPropuestaTransferenciaID();
			params[1]=propuestaTransferenciaBeneficiario.getBeneficiariosPorResultado().getBeneficiariosPorResultadoID();

			List<PropuestaTransferenciaBeneficiario> listPropuestaTransferenciaBeneficiarioTemp= propuestaTransferenciaBeneficiarioDAO.findPropuestaTransferenciaBeneficiarioByConsulta(consulta,params);
			if(listPropuestaTransferenciaBeneficiarioTemp.size()==0){
				propuestaTransferenciaBeneficiarioDAO.savePropuestaTransferenciaBeneficiario(propuestaTransferenciaBeneficiario);
			}else{
				PropuestaTransferenciaBeneficiario propuestaTransferenciaBeneficiarioTemp=listPropuestaTransferenciaBeneficiarioTemp.get(0); 
				propuestaTransferenciaBeneficiarioTemp.setCantidadFinal(propuestaTransferenciaBeneficiario.getCantidadFinal());
				propuestaTransferenciaBeneficiarioDAO.updatePropuestaTransferenciaBeneficiario(propuestaTransferenciaBeneficiarioTemp);
				
			}
		}

	@Override
	public List<PropuestaTransferenciaBeneficiario> findPropuestaTransferenciaBeneficiarioByPropuestaTransferenciaId(
			int propuestaTransferenciaId) {


		String consulta =" from PropuestaTransferenciaBeneficiario where propuestaTransferencia.propuestaTransferenciaID = ? ";
		Object[] params= new Object[1];
		params[0]= propuestaTransferenciaId;
		
		return propuestaTransferenciaBeneficiarioDAO.findPropuestaTransferenciaBeneficiarioByConsulta(consulta, params);
	}

	@Override
	public PropuestaTransferenciaBeneficiario findPropuestaTransferenciaBeneficiarioById(
			Integer sltBeneficiarioBienTransferido) {
		return propuestaTransferenciaBeneficiarioDAO.findPropuestaTransferenciaBeneficiarioById(sltBeneficiarioBienTransferido);
	}

}
