package lojadw;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.jupiter.api.Test;

import br.uff.dao.CategoriaHibernateDao;
import br.uff.dao.util.SessionFactoryHelper;
import br.uff.model.Categoria;

public class CategoriaDAOTest {

	
	@Test
	public void assertListSizeIsZero() {
		CategoriaHibernateDao catDao = new CategoriaHibernateDao(SessionFactoryHelper.getSessionFactory());
		assertNotEquals(0, catDao.getAll().size());
	}
	
	
}
