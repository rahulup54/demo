package com.vrs.dao;

import java.io.Serializable;
import java.util.List;

public interface VrsDao<T> {

	public void add(T t);
	public void update(T t);
	public void delete(Serializable id);
	public List<T> load();
	public T get(Serializable id);
	
}
