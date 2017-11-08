package br.uff.dao;

import br.uff.model.Administrador;

public abstract class AdministradorDao implements IGenericDao<Administrador>{
	
	public AdministradorDao() {
		
	}
	
	public abstract Administrador getByLogin(String login,String password);
}
