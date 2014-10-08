package org.smal2.test.testutil.mock;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public abstract class AInMemoryDAO<T, K> {

	private Hashtable<K, T> entities;

	public AInMemoryDAO() {
		entities = new Hashtable<K, T>();
	}

	public void createByKey(T entity, K key) {
		entities.put(key, entity);
	}

	public T read(K key) {
		return entities.get(key);
	}

	public List<T> readAll() {
		return new ArrayList<T>(entities.values());
	}

	public void updateByKey(T entity, K key) {
		entities.replace(key, entity);
	}

	public void delete(K key) {
		entities.remove(key);
	}
}
