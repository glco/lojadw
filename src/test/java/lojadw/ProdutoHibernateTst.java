package lojadw;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;


import br.uff.dao.ProdutoHibernateDao;
import br.uff.dao.util.SessionFactoryHelper;
import br.uff.model.Produto;

public class ProdutoHibernateTst {
	
	private ProdutoHibernateDao prodDao = new ProdutoHibernateDao(SessionFactoryHelper.getSessionFactory());
	
	@Test
	public void listShouldNotBeEmpty() {
		List<Produto> l = prodDao.getByCategoryId(2); 
		assertNotNull(l);
		assertNotEquals(0, l.size());
	}
	@Test
	public void listContainsOnlyRightCategory() {
		List<Produto> l = prodDao.getByCategoryId(2);
		assertNotNull(l);
		for(Produto p : l){
			System.out.println(p.getDescricao()+" "+p.getCategoria().getId());
			assertEquals(2, p.getCategoria().getId());
		}
	}
}
