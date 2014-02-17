package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.ActividadPerfil;
import pe.com.fondam.sgp.core.domain.TmpActividadPerfil;

public interface TmpActividadPerfilDAO {
	public void saveTmpActividadPerfil(TmpActividadPerfil tmpActividadPerfil);
	public ActividadPerfil updateTmpActividadPerfil(TmpActividadPerfil tmpActividadPerfil);
	public void deleteTmpActividadPerfil(TmpActividadPerfil tmpActividadPerfil);
	public TmpActividadPerfil findTmpActividadPerfilById(Integer id);
	public List<TmpActividadPerfil> findTmpActividadPerfil();
	public List<TmpActividadPerfil> findTmpActividadPerfilByTmpPerfilID(Integer tmpPerfilID);
	public List<TmpActividadPerfil> findTmpActividadPerfilByPerfilID(Integer tmpPerfilID);
}
