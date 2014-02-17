package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.MetaPerfil;

/**
*
* @author Zolanch Távara
*/
public interface MetaPerfilDAO {
	void saveMetaPerfil(MetaPerfil metaPerfil);

	MetaPerfil updateMetaPerfil(
			MetaPerfil metaPerfil);

	void deleteMetaPerfil(MetaPerfil metaPerfil);

	MetaPerfil findMetaPerfilById(Integer id);

	List<MetaPerfil> findMetaPerfil();

}
