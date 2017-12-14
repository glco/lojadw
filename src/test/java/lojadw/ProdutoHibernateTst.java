package lojadw;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;


import br.uff.dao.ProdutoHibernateDao;
import br.uff.dao.util.SessionFactoryHelper;
import br.uff.model.Produto;

public class ProdutoHibernateTst {
	
	private ProdutoHibernateDao prodDAo = new ProdutoHibernateDao(SessionFactoryHelper.getSessionFactory());
	
	@Test
	public void listShouldNotBeEmpty() {
		List<Produto> l = prodDAo.getByCategoryId(2); 
		assertNotNull(l);
		assertNotEquals(0, l.size());
	}

}
