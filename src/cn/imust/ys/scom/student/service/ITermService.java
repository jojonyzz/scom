package cn.imust.ys.scom.student.service;

import java.util.List;

import cn.imust.ys.scom.student.domain.Term;

public interface ITermService {

	Term getNewTerm();

	List<Term> findAll();

	void save(Term model);

	Term findById(Integer termid);

}
