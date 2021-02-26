package cn.jwis.qualityworkflow.modules.ipqc.TimerManager;
import cn.jwis.qualityworkflow.id.IDGeneratorRunner;
import cn.jwis.qualityworkflow.modules.ipqc.bean.AuditQualityProblemInfo;
import cn.jwis.qualityworkflow.modules.ipqc.bean.PatrolProblemRecord;
import cn.jwis.qualityworkflow.modules.ipqc.dao.AuditQualityProblemInfoMapper;
import cn.jwis.qualityworkflow.modules.ipqc.dao.PatrolProblemRecordMapper;
import cn.jwis.qualityworkflow.modules.ipqc.service.AuditQualityProblemService;
import cn.jwis.qualityworkflow.modules.ipqc.service.imp.AuditQualityProblemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
@EnableScheduling
public class TimerManager{


     @Autowired
     PatrolProblemRecordMapper patrolProblemRecordMapper;

     @Autowired
     AuditQualityProblemServiceImpl auditQualityProblemServiceImpl;

     @Autowired
    AuditQualityProblemInfoMapper auditQualityProblemInfoMapper;

    @Autowired
    IDGeneratorRunner idGeneratorRunner;

    @Autowired
    AuditQualityProblemService auditQualityProblemService;



    @Scheduled (cron = "0 0 8 * * ?")
    public void nightTimerManager()throws Exception {
        getNightAuditQualityProblem();
    }

    @Scheduled (cron = "0 30 8 * * ?")
    public void countEsdEos()throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(simpleDateFormat.parse(sdf.format(new Date()) + " 08:00:00"));
        calendar.add(Calendar.DATE, -1);
        String startTime = simpleDateFormat.format(calendar.getTime());
        String endTime = sdf.format(new Date()) + " 07:59:59";
        auditQualityProblemService.getCountAuditEsdEos(startTime,endTime);
    }
    @Scheduled (cron = "0 0 20 * * ?")
    public void dayTimerManager()throws Exception {
        getDayAuditQualityProblem();
    }

    public  void getNightAuditQualityProblem()throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(simpleDateFormat.parse(sdf.format(new Date()) + " 20:00:00"));
        calendar.add(Calendar.DATE, -1);
        Date date3 = calendar.getTime();
        Date startTime = date3;
        Date date4 = simpleDateFormat.parse(sdf.format(new Date()) + " 08:00:00");
        Date endTime = date4;
        List<PatrolProblemRecord> list = patrolProblemRecordMapper.getNighthtPatrolProblemRecord(startTime, endTime);
        if (list!=null){
            for (int i = 0; i <list.size() ; i++) {
                setData(list.get(i));
            }
        }
    }


    public  void getDayAuditQualityProblem()throws Exception{
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String time=sdf.format(new Date());
                    Date startTime = simpleDateFormat.parse(time + " 08:00:00");
                    String dateTime=sdf.format(new Date());
                    Date endTime = simpleDateFormat.parse(dateTime + " 20:00:00");
                    List<PatrolProblemRecord> list = patrolProblemRecordMapper.getDaytPatrolProblemRecord(startTime, endTime);
                    for (int i = 0; i <list.size() ; i++) {
                    setData(list.get(i));
                }
    }


    private  void setData(PatrolProblemRecord patrolProblemRecord)throws Exception{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if ("红黄线".equals(patrolProblemRecord.getProblemAttribute()) || "批量异常".equals(patrolProblemRecord.getProblemAttribute()) || "停线异常".equals(patrolProblemRecord.getProblemAttribute())) {
            AuditQualityProblemInfo auditQualityProblemInfo = new AuditQualityProblemInfo();
            auditQualityProblemInfo.setAuditType(patrolProblemRecord.getAuditType());
            auditQualityProblemInfo.setAuditDate(patrolProblemRecord.getAuditDate());
            auditQualityProblemInfo.setModel(patrolProblemRecord.getModel());
            auditQualityProblemInfo.setWorksSection(patrolProblemRecord.getWorksSection());
            auditQualityProblemInfo.setLine(patrolProblemRecord.getLine());
            auditQualityProblemInfo.setProblemDescription(patrolProblemRecord.getProblemDescription());
            auditQualityProblemInfo.setProblemPicture(patrolProblemRecord.getProblemPicture());
            auditQualityProblemInfo.setProblemType(patrolProblemRecord.getProblemType());
            auditQualityProblemInfo.setProblemOccurDate(simpleDateFormat.format(patrolProblemRecord.getCreateDate()));
            auditQualityProblemInfo.setFaultType(patrolProblemRecord.getFaultType());
            auditQualityProblemInfo.setProblemAttribute(patrolProblemRecord.getProblemAttribute());
            auditQualityProblemInfo.setOwner(patrolProblemRecord.getOwner());
            auditQualityProblemInfo.setCumulativeFrequency(patrolProblemRecord.getCumulativeFrequency());
            auditQualityProblemInfo.setFrequency(patrolProblemRecord.getFrequency()) ;
            auditQualityProblemInfo.setResponsible(patrolProblemRecord.getSquadLeader());
            auditQualityProblemInfo.setCreator("qualityadmin");
            auditQualityProblemServiceImpl.insertAuditQualityProblemInfo(auditQualityProblemInfo);
        }
    }



}
