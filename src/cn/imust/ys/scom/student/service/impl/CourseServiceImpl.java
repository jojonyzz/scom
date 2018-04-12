package cn.imust.ys.scom.student.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.imust.ys.scom.student.dao.ICourseDao;
import cn.imust.ys.scom.student.service.ICourseService;

@Service
@Transactional
public class CourseServiceImpl implements ICourseService{
	@Resource ICourseDao courseDao;

}
