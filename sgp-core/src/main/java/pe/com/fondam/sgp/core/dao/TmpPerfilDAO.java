package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.TmpPerfil;

public interface TmpPerfilDAO {

	public void saveTmpPerfil(TmpPerfil tmpPerfil);
	public TmpPerfil updateTmpPerfil(TmpPerfil tmpPerfil);
	public void deleteTmpPerfil(TmpPerfil tmpPerfil);
	public TmpPerfil findTmpPerfilById(Integer id);
	public List<TmpPerfil> findTmpPerfil();
	public TmpPerfil findTmpPerfilByTmpDatoProyectoID(Integer idProyecto);
	//List<TmpPerfil> findTmpPerfilByProgramaID(Integer idPrograma);

}
