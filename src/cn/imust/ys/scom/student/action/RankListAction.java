package cn.imust.ys.scom.student.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.imust.ys.scom.base.web.action.BaseAction;
import cn.imust.ys.scom.student.domain.RankList;
import cn.imust.ys.scom.student.service.IRankListService;

/**
 * 考试科目列表
 * */
@Controller
@Scope("prototype")
public class RankListAction extends BaseAction<RankList>{
	private static final long serialVersionUID = 1L;
	@Resource IRankListService rankListService;
	public String list() throws IOException{
		List<RankList> rankLists = rankListService.findAll();
		String[] excludes = new String[]{"collengTests"};
		writeList2Json(rankLists, excludes);
		return NONE;
	}

}
