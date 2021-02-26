package cn.jwis.qualityworkflow.modules.apqp.dao;

import org.apache.ibatis.annotations.Mapper;

import com.github.pagehelper.Page;

import cn.jwis.qualityworkflow.modules.apqp.bean.ApqpFileUpdateRecord;
import cn.jwis.qualityworkflow.modules.apqp.bean.QueryFileUpdateVo;


@Mapper
public interface ApqpFileUpdateRecordMapper {

    int deleteByPrimaryKey(String id);

    int insert(ApqpFileUpdateRecord record);

    int insertSelective(ApqpFileUpdateRecord record);

    ApqpFileUpdateRecord selectByPrimaryKey(String id);


    int updateByPrimaryKeySelective(ApqpFileUpdateRecord record);

    int updateByPrimaryKey(ApqpFileUpdateRecord record);

    /**
     * 分页查询SQL
     * @param vo
     * @return
     */
    Page<ApqpFileUpdateRecord> getList(QueryFileUpdateVo vo);
}