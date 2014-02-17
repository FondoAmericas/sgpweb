package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.DocInternoFondam;

/**
*
* @author Zolanch Távara
*/
public interface DocInternoFondamDAO {

	void saveDocInternoFondam(DocInternoFondam docInternoFondam);
	
	DocInternoFondam updateDocInternoFondam(DocInternoFondam docInternoFondam);
	
	void deleteDocInternoFondam(DocInternoFondam docInternoFondam);
	
	DocInternoFondam findDocInternoFondamById(Integer id);
	
	List<DocInternoFondam> findDocInternoFondam();

}
