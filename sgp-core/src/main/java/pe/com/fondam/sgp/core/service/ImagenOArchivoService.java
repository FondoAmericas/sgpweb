package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.domain.ImagenOArchivo;

public interface ImagenOArchivoService {

	ImagenOArchivo findImagenOArchivoByPropuestaTransferenciaId(
			Integer propuestaTransferenciaId);

	ImagenOArchivo updateImagenOArchivo(ImagenOArchivo imagenOArchivo);

	void saveImagenOArchivo(ImagenOArchivo imagenOArchivo);

	ImagenOArchivo findImagenOArchivoByPagoLiquidacinId(int ingresoPagoID);

	void deleteImagenOArchivo(Integer imagenOArchivoID);

	ImagenOArchivo findImagenOArchivoByLiquidacionGastoIdAndFormulario(Integer liquidacionGastoID,
			Integer formularioId);

	List<ImagenOArchivo> findImagenOArchivoByLiquidacionGastoId(
			Integer liquidacionGastoID);

	ImagenOArchivo findImagenOArchivoById(int imagenOArchivoId);
	
}
