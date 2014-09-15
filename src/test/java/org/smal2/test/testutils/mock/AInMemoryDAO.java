package org.smal2.test.testutils.mock;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public abstract class AInMemoryDAO<T, K> {

	private Hashtable<K, T> entities;

	public AInMemoryDAO() {
		entities = new Hashtable<K, T>();
	}

	public void createById(T entity, K id) {
		entities.put(id, entity);
	}

	public T read(long id) {
		return entities.get(id);
	}

	public List<T> readAll() {
		return new ArrayList<T>(entities.values());
	}

	public void updateById(T entity, K id) {
		entities.replace(id, entity);
	}

	public void delete(long id) {
		entities.remove(id);
	}
}
