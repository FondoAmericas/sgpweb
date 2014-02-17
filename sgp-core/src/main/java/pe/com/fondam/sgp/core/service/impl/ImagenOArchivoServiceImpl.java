package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.ImagenOArchivoDAO;
import pe.com.fondam.sgp.core.domain.ImagenOArchivo;
import pe.com.fondam.sgp.core.service.ImagenOArchivoService;

@Service
public class ImagenOArchivoServiceImpl implements ImagenOArchivoService {
	//******************* inyecciones ************************//
	@Resource
	ImagenOArchivoDAO imagenOArchivoDAO;
	
	//******************* metodos ************************//
	@Override
	public ImagenOArchivo findImagenOArchivoByPropuestaTransferenciaId(
			Integer propuestaTransferenciaId) {
		//verifico si existe proyectos
		String queryString1 = "from ImagenOArchivo where propuestaTransferencia.propuestaTransferenciaID = ? ";
		Object[] params1 = new Object[1];
		params1[0] = propuestaTransferenciaId;
		
		List<ImagenOArchivo> list=imagenOArchivoDAO.findConsulta(queryString1,params1);
		if (list!=null && list.size()!=0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public ImagenOArchivo updateImagenOArchivo(ImagenOArchivo imagenOArchivo) {
		return imagenOArchivoDAO.updateImagenOArchivo(imagenOArchivo);
	}

	@Override
	public void saveImagenOArchivo(ImagenOArchivo imagenOArchivo) {
		imagenOArchivoDAO.saveImagenOArchivo(imagenOArchivo);
		
	}

	@Override
	public ImagenOArchivo findImagenOArchivoByPagoLiquidacinId(int ingresoPagoID) {
		//verifico si existe proyectos
		String queryString1 = "from ImagenOArchivo where pagoLiquidacion.pagoLiquidacionID = ? ";
		Object[] params1 = new Object[1];
		params1[0] = ingresoPagoID;
		
		List<ImagenOArchivo> list=imagenOArchivoDAO.findConsulta(queryString1,params1);
		if (list!=null && list.size()!=0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void deleteImagenOArchivo(Integer imagenOArchivoID) {
		imagenOArchivoDAO.deleteImagenOArchivo(imagenOArchivoDAO.findImagenOArchivoById(imagenOArchivoID));
		
	}

	@Override
	public ImagenOArchivo findImagenOArchivoByLiquidacionGastoIdAndFormulario(
			Integer liquidacionGastoID, Integer formularioId) {
		//verifico si existe proyectos
		String consulta = "from ImagenOArchivo where liquidacionGasto.liquidacionGastoID = ? and numeroFormulario = ? ";
		Object[] params1 = new Object[2];
		params1[0] = liquidacionGastoID;
		params1[1] = formularioId;
		
		List<ImagenOArchivo> list=imagenOArchivoDAO.findConsulta(consulta,params1);
		if (list!=null && list.size()!=0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<ImagenOArchivo> findImagenOArchivoByLiquidacionGastoId(
			Integer liquidacionGastoID) {
		//verifico si existe proyectos
		String consulta = "from ImagenOArchivo where liquidacionGasto.liquidacionGastoID = ? ";
		Object[] params1 = new Object[1];
		params1[0] = liquidacionGastoID;
		
		List<ImagenOArchivo> listImagenOArchivo = imagenOArchivoDAO.findConsulta(consulta,params1);
		if (listImagenOArchivo!=null && listImagenOArchivo.size()!=0) {
			return listImagenOArchivo;
		}
		return null;
	}

	@Override
	public ImagenOArchivo findImagenOArchivoById(int imagenOArchivoId) {
		return imagenOArchivoDAO.findImagenOArchivoById(imagenOArchivoId);
	}

}
