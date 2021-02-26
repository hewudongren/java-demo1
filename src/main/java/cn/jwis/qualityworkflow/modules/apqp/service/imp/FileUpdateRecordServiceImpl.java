package cn.jwis.qualityworkflow.modules.apqp.service.imp;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import cn.jwis.qualityworkflow.id.IDGeneratorRunner;
import cn.jwis.qualityworkflow.modules.apqp.bean.ApqpFileUpdateRecord;
import cn.jwis.qualityworkflow.modules.apqp.bean.CreateFileUpdateRecord;
import cn.jwis.qualityworkflow.modules.apqp.bean.QueryFileUpdateVo;
import cn.jwis.qualityworkflow.modules.apqp.dao.ApqpFileUpdateRecordMapper;
import cn.jwis.qualityworkflow.modules.apqp.service.FileUpdateRecordService;
import cn.jwis.qualityworkflow.util.ImportAndExportUtil;
import cn.jwis.qualityworkflow.util.JSONObjectUtil;
import cn.jwis.qualityworkflow.util.ReflectUtil;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-08-14 11:48
 * @since 0.1.0
 **/
@Service
public class FileUpdateRecordServiceImpl implements FileUpdateRecordService {

	@Resource
	ApqpFileUpdateRecordMapper fileUpdateRecordDao;

	@Autowired
	IDGeneratorRunner idGeneratorRunner;

	@Autowired
	ImportAndExportUtil importAndExportUtil;

	@Autowired
	ReflectUtil reflectUtil;

	@Override
	public void insert(CreateFileUpdateRecord vo) throws Exception {
		ApqpFileUpdateRecord record = new ApqpFileUpdateRecord(vo);
		long id = idGeneratorRunner.nextId();
		record.setId(String.valueOf(id));
		fileUpdateRecordDao.insertSelective(record);
	}

	@Override
	public PageInfo<ApqpFileUpdateRecord> getList(QueryFileUpdateVo vo) throws Exception {
		reflectUtil.preHandledPageBean(vo);
		Page<ApqpFileUpdateRecord> list = fileUpdateRecordDao.getList(vo);
		PageInfo<ApqpFileUpdateRecord> page = new PageInfo<>(list.getResult());
		return page;
	}

	@Override
	public void exportInfo(HttpServletResponse response, HttpServletRequest request, QueryFileUpdateVo vo) throws Exception {
		vo.setPageSize(Integer.MAX_VALUE - 1);
		PageInfo<ApqpFileUpdateRecord> page = getList(vo);
		List<JSONObject> list = JSONObjectUtil.toJSONObjectList(page.getList());
		importAndExportUtil.export(response, request, list, "apqp_file_update_record");
	}
}
