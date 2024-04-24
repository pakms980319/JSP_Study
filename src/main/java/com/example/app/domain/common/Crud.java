package com.example.app.domain.common;

import java.util.List;

public interface Crud<T, S> {
	T select(S id) throws Exception;
	List<T> selectAll() throws Exception;
	boolean insert(T dto) throws Exception;
	boolean update(S id, T dto) throws Exception;
	boolean delete(S id) throws Exception;
}