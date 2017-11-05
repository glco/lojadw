package br.uff.dao;

import br.uff.model.Administrador;

public interface AdministradorDao{
	public Administrador getByLogin(String login,String password);
}
