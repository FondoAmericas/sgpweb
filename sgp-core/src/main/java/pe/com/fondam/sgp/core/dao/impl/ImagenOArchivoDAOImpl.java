package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.ImagenOArchivoDAO;
import pe.com.fondam.sgp.core.domain.ImagenOArchivo;


@Repository
public class ImagenOArchivoDAOImpl extends JpaBaseDAOImpl implements
		ImagenOArchivoDAO {

	@Autowired
	public ImagenOArchivoDAOImpl(
			@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	@Override
	public void saveImagenOArchivo(ImagenOArchivo imagenOArchivo) {
		super.save(imagenOArchivo);
	}

	@Override
	public ImagenOArchivo updateImagenOArchivo(ImagenOArchivo imagenOArchivo) {
		
		return super.update(imagenOArchivo);
	}

	@Override
	public void deleteImagenOArchivo(ImagenOArchivo imagenOArchivo) {
		super.delete(imagenOArchivo);

	}

	@Override
	public ImagenOArchivo findImagenOArchivoById(Integer id) {
		return super.findById(ImagenOArchivo.class, id);
	}

	
	@Override
	public ImagenOArchivo findImagenOarchivoByIdDatoProyecto(int idProyecto) {
		//verifico si existe proyectos
		//String queryString1 = "from ImagenOArchivo where datoProyecto.datoProyectoID= ? and programa.programaID!=null and perfil.perfilID!=null ";
		String queryString1 = "from ImagenOArchivo where datoProyecto.datoProyectoID= ? ";
		Object[] params1 = new Object[1];
		params1[0] = idProyecto;
		
		List<ImagenOArchivo> list=super.find(queryString1,params1);
		if (list!=null && list.size()!=0) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public ImagenOArchivo findImagenOarchivoByPerfilId(int perfilId) {
		//verifico si existe proyectos
		//String queryString1 = "from ImagenOArchivo where datoProyecto.datoProyectoID= ? and programa.programaID!=null and perfil.perfilID!=null ";
		String queryString1 = "from ImagenOArchivo where perfil.perfilID = ? ";
		Object[] params1 = new Object[1];
		params1[0] = perfilId;
		
		List<ImagenOArchivo> listImagenOArchivo = super.find(queryString1,params1);
		if (listImagenOArchivo != null && listImagenOArchivo.size()!=0) {
			return listImagenOArchivo.get(0);
		}
		return null;
	}

	@Override
	public ImagenOArchivo findImagenOarchivoByPrograma(int programa) {
		//verifico si existe proyectos
		String queryString1 = "from ImagenOArchivo where programa.programaID= ? and datoProyecto.datoProyectoID=null";
		Object[] params1 = new Object[1];
		params1[0] = programa;
		
		List<ImagenOArchivo> list=super.find(queryString1,params1);
		if (list!=null && list.size()!=0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<ImagenOArchivo> findImagenOArchivo(String queryString,
			Object[] params) {

		return super.find(queryString,params);
	}

	@Override
	public List<ImagenOArchivo> findConsulta(String queryString1,
			Object[] params1) {
		return super.find(queryString1, params1);
	}

}
