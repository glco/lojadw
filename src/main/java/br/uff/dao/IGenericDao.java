package br.uff.dao;

import java.util.List;



public interface IGenericDao<T> {
	
	public List<T> getAll();
	public T getById(int id);
	public void save(T item);
	public T delete(int id);
}
