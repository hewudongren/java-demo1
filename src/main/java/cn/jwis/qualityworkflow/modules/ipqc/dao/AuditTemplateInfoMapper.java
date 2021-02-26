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
import cn.jwis.qualityworkflow.modules.ipqc.bean.AuditTemplateDetail;
import cn.jwis.qualityworkflow.modules.ipqc.bean.AuditTemplateInfo;
import cn.jwis.qualityworkflow.modules.ipqc.bean.QueryTemplateInfoBean;

@Mapper
@Component
public interface AuditTemplateInfoMapper {

    List<AuditTemplateInfo> getAuditTemplateList(QueryTemplateInfoBean bean);

    Long getAuditTemplateCount(QueryTemplateInfoBean bean);


    @Insert("insert into audit_template_info(id,model,process,file_number,template_number,template_name,create_date,creator,remark,item_number,update_person,version) values(#{id},#{model},#{process},#{fileNumber},concat_ws('-','E-Audit',#{model},#{process},ipqc_number_generator()),#{templateName},#{createDate},#{creator},#{remark},ipqc_number_generator(),#{updatePerson},#{version})")
    void insertAuditTemplateInfo(AuditTemplateInfo bean);

    @Insert("insert into audit_template_detail(id,audit_template_id,audit_type,specific_requirement,not_conformity_opinion,create_date) values(#{id},#{auditTemplateId},#{auditType},#{specificRequirement},#{notConformityOpinion},#{createDate})")
    void insertAuditTemplateDetail(AuditTemplateDetail auditTemplateDetail);

    @Select("SELECT ipqc_number_generator()")
    String getTemplateNumber();

    @Select("select a.* from audit_template_info a where a.id=#{id}")
    AuditTemplateInfo getAuditTemplateInfoById(JSONObject  jsonObject);

    @Select("select d.* from audit_template_detail d where d.audit_template_id=#{id}")
    List<AuditTemplateDetail> getTemplateDetailList(JSONObject  jsonObject);

    @Select("select d.* from audit_list_detail d where d.audit_list_id=#{id}")
    List<AuditListDetail> getListDetail(@Param("id") String id);

    @Update("update audit_template_info set remark=#{remark},update_person=#{updatePerson},version=#{version} where id=#{id}")
    void updateAuditTemplateInfo(AuditTemplateInfo bean);

    @Update("update audit_template_detail set audit_type=#{auditType},specific_requirement=#{specificRequirement},not_conformity_opinion=#{notConformityOpinion} where id=#{id}")
    void updateAuditTemplateDetail(AuditTemplateDetail auditTemplateDetail);

    @Delete({"<script>delete from audit_template_detail where 1=1" +
            "<if test = 'arrAyList != null and arrAyList.size > 0'>" +
            "and" +
            "<foreach collection='arrAyList' index='index' item ='item'  open='(' separator='or'  close=')'> id=#{item} </foreach> </if>" +
            "</script>"})
    void deleteAuditTemplateDetail(@Param("arrAyList")List<String> arrAyList);

    @Delete("delete i,d from audit_template_info i,audit_template_detail d where i.id=d.audit_template_id and i.id=#{id}")
    void deleteAuditTemplateInfo(@Param("id")String id);

    List<AuditTemplateInfo> exportAuditTemplateInfo(QueryTemplateInfoBean bean);

    @Select("select id,creator,model,template_name from audit_template_info group by creator,model,template_name")
    List<AuditTemplateInfo> getPullDownList();

    @Select("select id from audit_template_detail where audit_template_id=#{id}")
    List<String>  getAuditTemplateDetailId(@Param("id") String id);

    @Select({"<script>select id,template_name from audit_template_info where 1=1" +
            "<if test = 'model != null and model != \"\"'>" +
            " and model = #{model} </if>" +
            "<if test = 'process != null and process != \"\"'>" +
            " and process = #{process} </if>" +
            "</script>"})
    List<JSONObject> getTemplateNameIdList(JSONObject jsonObject);
    @Select("select model from audit_template_info group by model")
    List<JSONObject> getModelList();

    @Select("select * from audit_list_info where audit_template_id=#{id}")
    List<AuditListInfo> getListInfo(@Param("id") String id);
}