package lojadw;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import br.uff.dao.AdministradorHibernateDao;
import br.uff.dao.util.SessionFactoryHelper;
import br.uff.model.Administrador;

public class AdministradorDAOTester {

	private AdministradorHibernateDao admDao = new AdministradorHibernateDao(SessionFactoryHelper.getSessionFactory());
	
	@Test
	public void assertListIsNotEmpty() {
		assertNotEquals(0,admDao.getAll().size());
	}
	
	
	
	@Test
	public void assertLoginExists() {
		String login = "admin";
		String senha = "123";
		Administrador adm = new Administrador();
		adm.setLogin(login);
		adm.setSenha("123");
		admDao.save(adm);
		
		Administrador adm2 = admDao.getByLogin(login,senha);
		
		assertNotNull(adm2);
		assertEquals(login,adm2.getLogin());
	}
	
	
}
