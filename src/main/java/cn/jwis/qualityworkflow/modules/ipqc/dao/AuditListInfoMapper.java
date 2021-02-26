package cn.jwis.qualityworkflow.modules.ipqc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.modules.ipqc.bean.AuditListDetail;
import cn.jwis.qualityworkflow.modules.ipqc.bean.AuditListInfo;
import cn.jwis.qualityworkflow.modules.ipqc.bean.AuditListSave;
import cn.jwis.qualityworkflow.modules.ipqc.bean.QueryListInfoBean;

@Mapper
@Component
public interface AuditListInfoMapper {

    List<AuditListInfo> getAuditListInfo(QueryListInfoBean bean);

    Long getAuditListCount(QueryListInfoBean bean);

    void insertAuditListInfo(AuditListInfo bean);

    @Select ("SELECT ipqc_number_generator()")
    String getTemplateNumber();

    List<AuditListInfo> exportAuditListInfo(QueryListInfoBean bean);

    @Select("select i.*,a.template_name as templateName from audit_list_info i,audit_template_info a where i.audit_template_id=a.id and i.id=#{id} ")
    AuditListInfo getAuditListInfoById(JSONObject jsonObject);


    @Insert ("insert into audit_list_detail(id,audit_list_id,audit_type,specific_requirement,not_conformity_opinion,content,create_date,audit_template_detail_id) values(#{id},#{auditListId},#{auditType},#{specificRequirement},#{notConformityOpinion},#{content},#{createDate},#{auditTemplateDetailId})")
    void insertAuditListDetail(AuditListDetail auditListDetail);

    @Select("select content from audit_list_detail where id=#{id}")
    String getContent(@Param("id") String id);


    @Update({"<script>update  audit_list_info set  model = #{model},process = #{process},inspector = #{inspector},line = #{line},time_interval = #{timeInterval} ,update_person = #{updatePerson}" +
            " where id=#{id}" +
            "</script>"})
    void updateAuditListInfo(AuditListInfo bean);

    @Update("update audit_list_detail set content=#{content} where id=#{id}")
    void updateAuditListDetail(AuditListDetail auditListDetail);

    @Update({"<script>update audit_list_info set time_interval=#{timeInterval} where id=#{id}"+
            "</script>"})
    void updateTimeInterval(AuditListInfo bean);

    @Insert("insert into audit_list_save(id,status,creator,content,audit_list_id) values(#{id},#{status},#{creator},#{content},#{auditListId})")
    void insertAuditListSave(AuditListSave auditListSave);

    @Select("select  * from audit_list_save where creator=#{creator} and status='InsertSave'")
    List<AuditListSave> getAuditListSave(@Param("creator") String creator);

    @Delete("delete from audit_list_save where id=#{id}")
    void deleteAuditListSave(@Param("id") String id);

    @Select("select  * from audit_list_save where creator=#{creator} and status='UpdateSave' and id=#{auditListId}")
    AuditListSave getAuditListUpdateSave(@Param("creator")String creator,@Param("auditListId")String auditListId);

    @Select("select content from audit_list_save where creator=#{string} and status='InsertSave'")
    String getAuditAddListSave(@Param("string") String string);


    @Select("select * from audit_list_save where audit_list_id=#{id}")
    AuditListSave getAuditListUpdateSaveById(JSONObject jsonObject);

    @Select("select * from audit_list_save where audit_list_id=#{id}")
    AuditListSave getUpDateSave(String id);

    @Select("select * from audit_list_info where model=#{model} and process=#{process} and line=#{line}")
    AuditListInfo getAuditListData(AuditListInfo bean);

    @Select("select * from audit_list_info where audit_date=#{auditDate} and model=#{model} and process=#{process} and line=#{line}  and frequency=#{frequency}")
    List<AuditListInfo> getAuditListInfoRepetition(AuditListInfo bean);
}