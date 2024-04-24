package com.example.app.domain.user.dao;

import com.example.app.domain.common.Crud;
import com.example.app.domain.user.dto.BussinessMan;

public interface BussinessManDao extends Crud<BussinessMan, String>{
	BussinessMan select2(String bussinessManId) throws Exception;
}