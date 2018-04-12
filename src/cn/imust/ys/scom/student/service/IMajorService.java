package cn.imust.ys.scom.student.service;

import java.util.List;

import cn.imust.ys.scom.student.domain.Major;


public interface IMajorService {

	void save(Major model);

	List<Major> findAll();

	List<Major> findAllByAid(Integer academyId);


}
