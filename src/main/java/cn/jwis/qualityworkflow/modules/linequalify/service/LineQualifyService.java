package cn.jwis.qualityworkflow.modules.linequalify.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.QueryDetailedInfoVo;
import cn.jwis.qualityworkflow.modules.linequalify.bean.LineQualifyInfo;
import cn.jwis.qualityworkflow.modules.linequalify.bean.LineQualifySearch;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/5/26 17:56
 */
@Service
public interface LineQualifyService {

	void saveLineQualifyInfo(LineQualifyInfo lineQualifyInfo) throws Exception;

	void confirm(JSONObject bean) throws Exception;

	List<String> getDropdownValue(String parameter);

	List<LineQualifyInfo> getLineQualifyInfoList(LineQualifySearch qimsCqaSearch);

	Long getLineQualifyInfoCount(LineQualifySearch qimsCqaSearch);

	void exportLineQualifyInfoList(HttpServletResponse response, HttpServletRequest request,
			LineQualifySearch lineQualifySearch);

	Map<String, Object> getLineQualifyDetailednessInfo(QueryDetailedInfoVo bean) throws Exception;

	Map<String, String> getTitle(HttpServletRequest request);

	List<LineQualifyInfo> getCertificationList(LineQualifySearch lineQualifySearch);

	Long getCertificationCount(LineQualifySearch lineQualifySearch);

	void exportCertificationList(HttpServletResponse response, HttpServletRequest request,
			LineQualifySearch lineQualifySearch);

	void sendOverTimeMail();

	JSONObject getProgress(String id);
}