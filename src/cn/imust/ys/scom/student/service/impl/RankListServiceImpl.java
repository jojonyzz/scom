package cn.imust.ys.scom.student.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.imust.ys.scom.student.dao.IRankListDao;
import cn.imust.ys.scom.student.domain.RankList;
import cn.imust.ys.scom.student.service.IRankListService;

@Service
@Transactional
public class RankListServiceImpl implements IRankListService{
	@Resource private IRankListDao rankListDao;

	@Override
	public List<RankList> findAll() {
		return rankListDao.findAll();
	}
	
}
