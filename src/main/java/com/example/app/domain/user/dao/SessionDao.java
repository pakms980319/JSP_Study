package com.example.app.domain.user.dao;

import com.example.app.domain.common.Crud;
import com.example.app.domain.user.dto.Session;

public interface SessionDao extends Crud<Session, Integer> {
	Session select(String userId) throws Exception;
}
