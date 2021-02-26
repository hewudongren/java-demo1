package cn.jwis.qualityworkflow.modules.apqp.service.imp;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jwis.qualityworkflow.id.IDGeneratorRunner;
import cn.jwis.qualityworkflow.modules.apqp.bean.ApqpDvt1RiskItem;
import cn.jwis.qualityworkflow.modules.apqp.bean.ApqpPfmeaRiskItem;
import cn.jwis.qualityworkflow.modules.apqp.bean.ApqpQcChartRiskItem;
import cn.jwis.qualityworkflow.modules.apqp.bean.CreateDvt1RiskItem;
import cn.jwis.qualityworkflow.modules.apqp.bean.CreatePfmeaRiskItem;
import cn.jwis.qualityworkflow.modules.apqp.bean.CreateQcChartRiskItem;
import cn.jwis.qualityworkflow.modules.apqp.dao.ApqpDvt1RiskItemMapper;
import cn.jwis.qualityworkflow.modules.apqp.dao.ApqpPfmeaRiskItemMapper;
import cn.jwis.qualityworkflow.modules.apqp.dao.ApqpQcChartRiskItemMapper;
import cn.jwis.qualityworkflow.modules.apqp.service.RiskItemService;
import cn.jwis.qualityworkflow.util.UserUtil;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-05-30 14:31
 * @since 0.1.0
 **/
@Service
public class RiskItemServiceImpl implements RiskItemService {

	@Resource
	ApqpDvt1RiskItemMapper dvt1RiskItemDao;

	@Resource
	ApqpPfmeaRiskItemMapper pfmeaRiskItemDao;

	@Resource
	ApqpQcChartRiskItemMapper qcChartRiskItemDao;


	@Autowired
	IDGeneratorRunner idGeneratorRunner;

	@Override
	public void insertDvt1RiskList(CreateDvt1RiskItem vo) throws Exception {
		if (vo != null && CollectionUtils.isNotEmpty(vo.getList())) {
			String currentUserName = UserUtil.getCurrentUserName();
			for (CreateDvt1RiskItem.Dvt1Inner innerClass: vo.getList()) {
				ApqpDvt1RiskItem riskItem = new ApqpDvt1RiskItem();
				long id = idGeneratorRunner.nextId();
				riskItem.setId(String.valueOf(id));
				riskItem.setModel(vo.getModel());
				riskItem.setSubdocumentId(vo.getSubdocumentId());
				riskItem.setEngPhase(vo.getEngPhase());
				riskItem.setCreator(currentUserName);
				riskItem.setCurrentControlMode(innerClass.getCurrentControlMode());
				riskItem.setDvt1Attachment(innerClass.getDvt1Attachment());
				riskItem.setPotentialCause(innerClass.getPotentialCause());
				riskItem.setRiskDescription(innerClass.getRiskDescription());
				riskItem.setRiskImpact(innerClass.getRiskImpact());
				riskItem.setDvt1CommitTime(innerClass.getDvt1CommitTime());
				riskItem.setRiskSource(innerClass.getRiskSource());
				dvt1RiskItemDao.insert(riskItem);
			}
		}
	}



	@Override
	public List<ApqpDvt1RiskItem> getDvt1PreviousModelRiskList(String previousProduct, String engPhase,Date updateTime) throws Exception{
		// 根据上一代机型 和 工程段 获取 对应的高风险项
		// 子单据更新时间
		List<ApqpDvt1RiskItem> apqpRiskItems = dvt1RiskItemDao.getApqpDvt1RiskItem(previousProduct, engPhase, updateTime);
		return apqpRiskItems;
	}

	@Override
	public void insertPfmeaRiskList(CreatePfmeaRiskItem vo) throws Exception {
		if (vo != null && CollectionUtils.isNotEmpty(vo.getList())) {
			String currentUserName = UserUtil.getCurrentUserName();
			for (CreatePfmeaRiskItem.PfmeaInner innerClass: vo.getList()) {
				ApqpPfmeaRiskItem riskItem = new ApqpPfmeaRiskItem();
				long id = idGeneratorRunner.nextId();
				riskItem.setSubdocumentId(vo.getSubdocumentId());
				riskItem.setCreator(currentUserName);
				riskItem.setId(String.valueOf(id));
				riskItem.setEngPhase(vo.getEngPhase());
				riskItem.setModel(vo.getModel());
				riskItem.setRiskDescription(innerClass.getRiskDescription());
				riskItem.setRpn(innerClass.getRpn());
				riskItem.setSeverity(innerClass.getSeverity());
				riskItem.setCurrentControl(innerClass.getCurrentControl());
				riskItem.setDetectability(innerClass.getDetectability());
				riskItem.setFrequency(innerClass.getFrequency());
				riskItem.setOperationStep(innerClass.getOperationStep());
				riskItem.setPotentialFailureCause(innerClass.getPotentialFailureCause());
				riskItem.setPotentialFailureMode(innerClass.getPotentialFailureMode());
				riskItem.setPotentialFailureResult(innerClass.getPotentialFailureResult());
				riskItem.setPemeaAttachment(innerClass.getPemeaAttachment());
				riskItem.setPemeaCommitTime(innerClass.getPemeaCommitTime());
				riskItem.setRiskSource(innerClass.getRiskSource());
				pfmeaRiskItemDao.insert(riskItem);
			}
		}
	}

	@Override
	public List<ApqpPfmeaRiskItem> getPfmeaPreviousModelRiskList(String previousProduct, String engPhase,Date updateTime) throws Exception {
		// 根据上一代机型 和 工程段 获取 对应的高风险项
		List<ApqpPfmeaRiskItem> apqpRiskItems = pfmeaRiskItemDao.getApqpPfmeaRiskItem(previousProduct, engPhase, updateTime);
		return apqpRiskItems;
	}

	@Override
	public void insertQcChartRiskList(CreateQcChartRiskItem vo) throws Exception {
		if (vo != null && CollectionUtils.isNotEmpty(vo.getList())) {
			String currentUserName = UserUtil.getCurrentUserName();
			for (CreateQcChartRiskItem.InnerClass innerClass: vo.getList()) {
				ApqpQcChartRiskItem riskItem = new ApqpQcChartRiskItem();
				long id = idGeneratorRunner.nextId();
				riskItem.setId(String.valueOf(id));
				riskItem.setModel(vo.getModel());
				riskItem.setCreator(currentUserName);
				riskItem.setSubdocumentId(vo.getSubdocumentId());
				riskItem.setRiskDescription(innerClass.getRiskDescription());
				riskItem.setEngPhase(vo.getEngPhase());
				riskItem.setProductControlMode(innerClass.getProductControlMode());
				riskItem.setProductProcessSpecification(innerClass.getProductProcessSpecification());
				riskItem.setQcChartAttachment(innerClass.getQcChartAttachment());
				riskItem.setQcChartCommitTime(innerClass.getQcChartCommitTime());
				riskItem.setRiskDescription(innerClass.getRiskDescription());
				riskItem.setRiskSource(innerClass.getRiskSource());
				qcChartRiskItemDao.insert(riskItem);
			}
		}
	}

	@Override
	public List<ApqpQcChartRiskItem> getQcChartPreviousModelRiskList(String previousProduct, String engPhase, Date updateTime) throws Exception {
		// 根据上一代机型 和 工程段 获取 对应的高风险项
		List<ApqpQcChartRiskItem> apqpRiskItems = qcChartRiskItemDao.getApqpQcChartRiskItem(previousProduct, engPhase, updateTime);
		return apqpRiskItems;
	}


}
