package cn.imust.ys.scom.admin.service;

import java.util.List;

import cn.imust.ys.scom.admin.domain.BackBug;

public interface IBackBugService {

	void save(BackBug model);

	List<BackBug> findAll();

}
