package cn.jwis.qualityworkflow.modules.linequalify.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.modules.linequalify.bean.ESDTemplateInfo;

@Service
public interface ESDTemplateService {

	void saveEsdTemplates(List<ESDTemplateInfo> beans);

	List<ESDTemplateInfo> getEsdTemplateList();

	void exportTemplate(JSONObject jsonObject, HttpServletResponse response, HttpServletRequest request);

	void exportEsdTemplateList(JSONObject jsonObject, HttpServletResponse response, HttpServletRequest request);

}