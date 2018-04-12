package cn.imust.ys.scom.student.service;

import java.util.List;

import cn.imust.ys.scom.student.domain.Academy;


public interface IAcademyService {

	void save(Academy model);

	List<Academy> findAll();
}
