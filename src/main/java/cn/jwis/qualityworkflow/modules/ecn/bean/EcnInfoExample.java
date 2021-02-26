package cn.jwis.qualityworkflow.modules.ecn.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EcnInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EcnInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class BaseGeneratedCriteria {
        protected List<Criterion> criteria;

        protected BaseGeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andItemNumberIsNull() {
            addCriterion("item_number is null");
            return (Criteria) this;
        }

        public Criteria andItemNumberIsNotNull() {
            addCriterion("item_number is not null");
            return (Criteria) this;
        }

        public Criteria andItemNumberEqualTo(String value) {
            addCriterion("item_number =", value, "itemNumber");
            return (Criteria) this;
        }

        public Criteria andItemNumberNotEqualTo(String value) {
            addCriterion("item_number <>", value, "itemNumber");
            return (Criteria) this;
        }

        public Criteria andItemNumberGreaterThan(String value) {
            addCriterion("item_number >", value, "itemNumber");
            return (Criteria) this;
        }

        public Criteria andItemNumberGreaterThanOrEqualTo(String value) {
            addCriterion("item_number >=", value, "itemNumber");
            return (Criteria) this;
        }

        public Criteria andItemNumberLessThan(String value) {
            addCriterion("item_number <", value, "itemNumber");
            return (Criteria) this;
        }

        public Criteria andItemNumberLessThanOrEqualTo(String value) {
            addCriterion("item_number <=", value, "itemNumber");
            return (Criteria) this;
        }

        public Criteria andItemNumberLike(String value) {
            addCriterion("item_number like", value, "itemNumber");
            return (Criteria) this;
        }

        public Criteria andItemNumberNotLike(String value) {
            addCriterion("item_number not like", value, "itemNumber");
            return (Criteria) this;
        }

        public Criteria andItemNumberIn(List<String> values) {
            addCriterion("item_number in", values, "itemNumber");
            return (Criteria) this;
        }

        public Criteria andItemNumberNotIn(List<String> values) {
            addCriterion("item_number not in", values, "itemNumber");
            return (Criteria) this;
        }

        public Criteria andItemNumberBetween(String value1, String value2) {
            addCriterion("item_number between", value1, value2, "itemNumber");
            return (Criteria) this;
        }

        public Criteria andItemNumberNotBetween(String value1, String value2) {
            addCriterion("item_number not between", value1, value2, "itemNumber");
            return (Criteria) this;
        }

        public Criteria andItemTypeIsNull() {
            addCriterion("item_type is null");
            return (Criteria) this;
        }

        public Criteria andItemTypeIsNotNull() {
            addCriterion("item_type is not null");
            return (Criteria) this;
        }

        public Criteria andItemTypeEqualTo(String value) {
            addCriterion("item_type =", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeNotEqualTo(String value) {
            addCriterion("item_type <>", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeGreaterThan(String value) {
            addCriterion("item_type >", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeGreaterThanOrEqualTo(String value) {
            addCriterion("item_type >=", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeLessThan(String value) {
            addCriterion("item_type <", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeLessThanOrEqualTo(String value) {
            addCriterion("item_type <=", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeLike(String value) {
            addCriterion("item_type like", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeNotLike(String value) {
            addCriterion("item_type not like", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeIn(List<String> values) {
            addCriterion("item_type in", values, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeNotIn(List<String> values) {
            addCriterion("item_type not in", values, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeBetween(String value1, String value2) {
            addCriterion("item_type between", value1, value2, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeNotBetween(String value1, String value2) {
            addCriterion("item_type not between", value1, value2, "itemType");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNull() {
            addCriterion("file_name is null");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNotNull() {
            addCriterion("file_name is not null");
            return (Criteria) this;
        }

        public Criteria andFileNameEqualTo(String value) {
            addCriterion("file_name =", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotEqualTo(String value) {
            addCriterion("file_name <>", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThan(String value) {
            addCriterion("file_name >", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThanOrEqualTo(String value) {
            addCriterion("file_name >=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThan(String value) {
            addCriterion("file_name <", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThanOrEqualTo(String value) {
            addCriterion("file_name <=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLike(String value) {
            addCriterion("file_name like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotLike(String value) {
            addCriterion("file_name not like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameIn(List<String> values) {
            addCriterion("file_name in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotIn(List<String> values) {
            addCriterion("file_name not in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameBetween(String value1, String value2) {
            addCriterion("file_name between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotBetween(String value1, String value2) {
            addCriterion("file_name not between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileOidIsNull() {
            addCriterion("file_oid is null");
            return (Criteria) this;
        }

        public Criteria andFileOidIsNotNull() {
            addCriterion("file_oid is not null");
            return (Criteria) this;
        }

        public Criteria andFileOidEqualTo(String value) {
            addCriterion("file_oid =", value, "fileOid");
            return (Criteria) this;
        }

        public Criteria andFileOidNotEqualTo(String value) {
            addCriterion("file_oid <>", value, "fileOid");
            return (Criteria) this;
        }

        public Criteria andFileOidGreaterThan(String value) {
            addCriterion("file_oid >", value, "fileOid");
            return (Criteria) this;
        }

        public Criteria andFileOidGreaterThanOrEqualTo(String value) {
            addCriterion("file_oid >=", value, "fileOid");
            return (Criteria) this;
        }

        public Criteria andFileOidLessThan(String value) {
            addCriterion("file_oid <", value, "fileOid");
            return (Criteria) this;
        }

        public Criteria andFileOidLessThanOrEqualTo(String value) {
            addCriterion("file_oid <=", value, "fileOid");
            return (Criteria) this;
        }

        public Criteria andFileOidLike(String value) {
            addCriterion("file_oid like", value, "fileOid");
            return (Criteria) this;
        }

        public Criteria andFileOidNotLike(String value) {
            addCriterion("file_oid not like", value, "fileOid");
            return (Criteria) this;
        }

        public Criteria andFileOidIn(List<String> values) {
            addCriterion("file_oid in", values, "fileOid");
            return (Criteria) this;
        }

        public Criteria andFileOidNotIn(List<String> values) {
            addCriterion("file_oid not in", values, "fileOid");
            return (Criteria) this;
        }

        public Criteria andFileOidBetween(String value1, String value2) {
            addCriterion("file_oid between", value1, value2, "fileOid");
            return (Criteria) this;
        }

        public Criteria andFileOidNotBetween(String value1, String value2) {
            addCriterion("file_oid not between", value1, value2, "fileOid");
            return (Criteria) this;
        }

        public Criteria andSenderSideIsNull() {
            addCriterion("sender_side is null");
            return (Criteria) this;
        }

        public Criteria andSenderSideIsNotNull() {
            addCriterion("sender_side is not null");
            return (Criteria) this;
        }

        public Criteria andSenderSideEqualTo(String value) {
            addCriterion("sender_side =", value, "senderSide");
            return (Criteria) this;
        }

        public Criteria andSenderSideNotEqualTo(String value) {
            addCriterion("sender_side <>", value, "senderSide");
            return (Criteria) this;
        }

        public Criteria andSenderSideGreaterThan(String value) {
            addCriterion("sender_side >", value, "senderSide");
            return (Criteria) this;
        }

        public Criteria andSenderSideGreaterThanOrEqualTo(String value) {
            addCriterion("sender_side >=", value, "senderSide");
            return (Criteria) this;
        }

        public Criteria andSenderSideLessThan(String value) {
            addCriterion("sender_side <", value, "senderSide");
            return (Criteria) this;
        }

        public Criteria andSenderSideLessThanOrEqualTo(String value) {
            addCriterion("sender_side <=", value, "senderSide");
            return (Criteria) this;
        }

        public Criteria andSenderSideLike(String value) {
            addCriterion("sender_side like", value, "senderSide");
            return (Criteria) this;
        }

        public Criteria andSenderSideNotLike(String value) {
            addCriterion("sender_side not like", value, "senderSide");
            return (Criteria) this;
        }

        public Criteria andSenderSideIn(List<String> values) {
            addCriterion("sender_side in", values, "senderSide");
            return (Criteria) this;
        }

        public Criteria andSenderSideNotIn(List<String> values) {
            addCriterion("sender_side not in", values, "senderSide");
            return (Criteria) this;
        }

        public Criteria andSenderSideBetween(String value1, String value2) {
            addCriterion("sender_side between", value1, value2, "senderSide");
            return (Criteria) this;
        }

        public Criteria andSenderSideNotBetween(String value1, String value2) {
            addCriterion("sender_side not between", value1, value2, "senderSide");
            return (Criteria) this;
        }

        public Criteria andDocumentOriginatorIsNull() {
            addCriterion("document_originator is null");
            return (Criteria) this;
        }

        public Criteria andDocumentOriginatorIsNotNull() {
            addCriterion("document_originator is not null");
            return (Criteria) this;
        }

        public Criteria andDocumentOriginatorEqualTo(String value) {
            addCriterion("document_originator =", value, "documentOriginator");
            return (Criteria) this;
        }

        public Criteria andDocumentOriginatorNotEqualTo(String value) {
            addCriterion("document_originator <>", value, "documentOriginator");
            return (Criteria) this;
        }

        public Criteria andDocumentOriginatorGreaterThan(String value) {
            addCriterion("document_originator >", value, "documentOriginator");
            return (Criteria) this;
        }

        public Criteria andDocumentOriginatorGreaterThanOrEqualTo(String value) {
            addCriterion("document_originator >=", value, "documentOriginator");
            return (Criteria) this;
        }

        public Criteria andDocumentOriginatorLessThan(String value) {
            addCriterion("document_originator <", value, "documentOriginator");
            return (Criteria) this;
        }

        public Criteria andDocumentOriginatorLessThanOrEqualTo(String value) {
            addCriterion("document_originator <=", value, "documentOriginator");
            return (Criteria) this;
        }

        public Criteria andDocumentOriginatorLike(String value) {
            addCriterion("document_originator like", value, "documentOriginator");
            return (Criteria) this;
        }

        public Criteria andDocumentOriginatorNotLike(String value) {
            addCriterion("document_originator not like", value, "documentOriginator");
            return (Criteria) this;
        }

        public Criteria andDocumentOriginatorIn(List<String> values) {
            addCriterion("document_originator in", values, "documentOriginator");
            return (Criteria) this;
        }

        public Criteria andDocumentOriginatorNotIn(List<String> values) {
            addCriterion("document_originator not in", values, "documentOriginator");
            return (Criteria) this;
        }

        public Criteria andDocumentOriginatorBetween(String value1, String value2) {
            addCriterion("document_originator between", value1, value2, "documentOriginator");
            return (Criteria) this;
        }

        public Criteria andDocumentOriginatorNotBetween(String value1, String value2) {
            addCriterion("document_originator not between", value1, value2, "documentOriginator");
            return (Criteria) this;
        }

        public Criteria andModelNameIsNull() {
            addCriterion("model_name is null");
            return (Criteria) this;
        }

        public Criteria andModelNameIsNotNull() {
            addCriterion("model_name is not null");
            return (Criteria) this;
        }

        public Criteria andModelNameEqualTo(String value) {
            addCriterion("model_name =", value, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameNotEqualTo(String value) {
            addCriterion("model_name <>", value, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameGreaterThan(String value) {
            addCriterion("model_name >", value, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameGreaterThanOrEqualTo(String value) {
            addCriterion("model_name >=", value, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameLessThan(String value) {
            addCriterion("model_name <", value, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameLessThanOrEqualTo(String value) {
            addCriterion("model_name <=", value, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameLike(String value) {
            addCriterion("model_name like", value, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameNotLike(String value) {
            addCriterion("model_name not like", value, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameIn(List<String> values) {
            addCriterion("model_name in", values, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameNotIn(List<String> values) {
            addCriterion("model_name not in", values, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameBetween(String value1, String value2) {
            addCriterion("model_name between", value1, value2, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameNotBetween(String value1, String value2) {
            addCriterion("model_name not between", value1, value2, "modelName");
            return (Criteria) this;
        }

        public Criteria andChangeTypeIsNull() {
            addCriterion("change_type is null");
            return (Criteria) this;
        }

        public Criteria andChangeTypeIsNotNull() {
            addCriterion("change_type is not null");
            return (Criteria) this;
        }

        public Criteria andChangeTypeEqualTo(String value) {
            addCriterion("change_type =", value, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeNotEqualTo(String value) {
            addCriterion("change_type <>", value, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeGreaterThan(String value) {
            addCriterion("change_type >", value, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("change_type >=", value, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeLessThan(String value) {
            addCriterion("change_type <", value, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeLessThanOrEqualTo(String value) {
            addCriterion("change_type <=", value, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeLike(String value) {
            addCriterion("change_type like", value, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeNotLike(String value) {
            addCriterion("change_type not like", value, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeIn(List<String> values) {
            addCriterion("change_type in", values, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeNotIn(List<String> values) {
            addCriterion("change_type not in", values, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeBetween(String value1, String value2) {
            addCriterion("change_type between", value1, value2, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeNotBetween(String value1, String value2) {
            addCriterion("change_type not between", value1, value2, "changeType");
            return (Criteria) this;
        }

        public Criteria andPostingTimeIsNull() {
            addCriterion("posting_time is null");
            return (Criteria) this;
        }

        public Criteria andPostingTimeIsNotNull() {
            addCriterion("posting_time is not null");
            return (Criteria) this;
        }

        public Criteria andPostingTimeEqualTo(Date value) {
            addCriterion("posting_time =", value, "postingTime");
            return (Criteria) this;
        }

        public Criteria andPostingTimeNotEqualTo(Date value) {
            addCriterion("posting_time <>", value, "postingTime");
            return (Criteria) this;
        }

        public Criteria andPostingTimeGreaterThan(Date value) {
            addCriterion("posting_time >", value, "postingTime");
            return (Criteria) this;
        }

        public Criteria andPostingTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("posting_time >=", value, "postingTime");
            return (Criteria) this;
        }

        public Criteria andPostingTimeLessThan(Date value) {
            addCriterion("posting_time <", value, "postingTime");
            return (Criteria) this;
        }

        public Criteria andPostingTimeLessThanOrEqualTo(Date value) {
            addCriterion("posting_time <=", value, "postingTime");
            return (Criteria) this;
        }

        public Criteria andPostingTimeIn(List<Date> values) {
            addCriterion("posting_time in", values, "postingTime");
            return (Criteria) this;
        }

        public Criteria andPostingTimeNotIn(List<Date> values) {
            addCriterion("posting_time not in", values, "postingTime");
            return (Criteria) this;
        }

        public Criteria andPostingTimeBetween(Date value1, Date value2) {
            addCriterion("posting_time between", value1, value2, "postingTime");
            return (Criteria) this;
        }

        public Criteria andPostingTimeNotBetween(Date value1, Date value2) {
            addCriterion("posting_time not between", value1, value2, "postingTime");
            return (Criteria) this;
        }

        public Criteria andWorksSectionIsNull() {
            addCriterion("works_section is null");
            return (Criteria) this;
        }

        public Criteria andWorksSectionIsNotNull() {
            addCriterion("works_section is not null");
            return (Criteria) this;
        }

        public Criteria andWorksSectionEqualTo(String value) {
            addCriterion("works_section =", value, "worksSection");
            return (Criteria) this;
        }

        public Criteria andWorksSectionNotEqualTo(String value) {
            addCriterion("works_section <>", value, "worksSection");
            return (Criteria) this;
        }

        public Criteria andWorksSectionGreaterThan(String value) {
            addCriterion("works_section >", value, "worksSection");
            return (Criteria) this;
        }

        public Criteria andWorksSectionGreaterThanOrEqualTo(String value) {
            addCriterion("works_section >=", value, "worksSection");
            return (Criteria) this;
        }

        public Criteria andWorksSectionLessThan(String value) {
            addCriterion("works_section <", value, "worksSection");
            return (Criteria) this;
        }

        public Criteria andWorksSectionLessThanOrEqualTo(String value) {
            addCriterion("works_section <=", value, "worksSection");
            return (Criteria) this;
        }

        public Criteria andWorksSectionLike(String value) {
            addCriterion("works_section like", value, "worksSection");
            return (Criteria) this;
        }

        public Criteria andWorksSectionNotLike(String value) {
            addCriterion("works_section not like", value, "worksSection");
            return (Criteria) this;
        }

        public Criteria andWorksSectionIn(List<String> values) {
            addCriterion("works_section in", values, "worksSection");
            return (Criteria) this;
        }

        public Criteria andWorksSectionNotIn(List<String> values) {
            addCriterion("works_section not in", values, "worksSection");
            return (Criteria) this;
        }

        public Criteria andWorksSectionBetween(String value1, String value2) {
            addCriterion("works_section between", value1, value2, "worksSection");
            return (Criteria) this;
        }

        public Criteria andWorksSectionNotBetween(String value1, String value2) {
            addCriterion("works_section not between", value1, value2, "worksSection");
            return (Criteria) this;
        }

        public Criteria andWorksHeadIsNull() {
            addCriterion("works_head is null");
            return (Criteria) this;
        }

        public Criteria andWorksHeadIsNotNull() {
            addCriterion("works_head is not null");
            return (Criteria) this;
        }

        public Criteria andWorksHeadEqualTo(String value) {
            addCriterion("works_head =", value, "worksHead");
            return (Criteria) this;
        }

        public Criteria andWorksHeadNotEqualTo(String value) {
            addCriterion("works_head <>", value, "worksHead");
            return (Criteria) this;
        }

        public Criteria andWorksHeadGreaterThan(String value) {
            addCriterion("works_head >", value, "worksHead");
            return (Criteria) this;
        }

        public Criteria andWorksHeadGreaterThanOrEqualTo(String value) {
            addCriterion("works_head >=", value, "worksHead");
            return (Criteria) this;
        }

        public Criteria andWorksHeadLessThan(String value) {
            addCriterion("works_head <", value, "worksHead");
            return (Criteria) this;
        }

        public Criteria andWorksHeadLessThanOrEqualTo(String value) {
            addCriterion("works_head <=", value, "worksHead");
            return (Criteria) this;
        }

        public Criteria andWorksHeadLike(String value) {
            addCriterion("works_head like", value, "worksHead");
            return (Criteria) this;
        }

        public Criteria andWorksHeadNotLike(String value) {
            addCriterion("works_head not like", value, "worksHead");
            return (Criteria) this;
        }

        public Criteria andWorksHeadIn(List<String> values) {
            addCriterion("works_head in", values, "worksHead");
            return (Criteria) this;
        }

        public Criteria andWorksHeadNotIn(List<String> values) {
            addCriterion("works_head not in", values, "worksHead");
            return (Criteria) this;
        }

        public Criteria andWorksHeadBetween(String value1, String value2) {
            addCriterion("works_head between", value1, value2, "worksHead");
            return (Criteria) this;
        }

        public Criteria andWorksHeadNotBetween(String value1, String value2) {
            addCriterion("works_head not between", value1, value2, "worksHead");
            return (Criteria) this;
        }

        public Criteria andQualityHeadIsNull() {
            addCriterion("quality_head is null");
            return (Criteria) this;
        }

        public Criteria andQualityHeadIsNotNull() {
            addCriterion("quality_head is not null");
            return (Criteria) this;
        }

        public Criteria andQualityHeadEqualTo(String value) {
            addCriterion("quality_head =", value, "qualityHead");
            return (Criteria) this;
        }

        public Criteria andQualityHeadNotEqualTo(String value) {
            addCriterion("quality_head <>", value, "qualityHead");
            return (Criteria) this;
        }

        public Criteria andQualityHeadGreaterThan(String value) {
            addCriterion("quality_head >", value, "qualityHead");
            return (Criteria) this;
        }

        public Criteria andQualityHeadGreaterThanOrEqualTo(String value) {
            addCriterion("quality_head >=", value, "qualityHead");
            return (Criteria) this;
        }

        public Criteria andQualityHeadLessThan(String value) {
            addCriterion("quality_head <", value, "qualityHead");
            return (Criteria) this;
        }

        public Criteria andQualityHeadLessThanOrEqualTo(String value) {
            addCriterion("quality_head <=", value, "qualityHead");
            return (Criteria) this;
        }

        public Criteria andQualityHeadLike(String value) {
            addCriterion("quality_head like", value, "qualityHead");
            return (Criteria) this;
        }

        public Criteria andQualityHeadNotLike(String value) {
            addCriterion("quality_head not like", value, "qualityHead");
            return (Criteria) this;
        }

        public Criteria andQualityHeadIn(List<String> values) {
            addCriterion("quality_head in", values, "qualityHead");
            return (Criteria) this;
        }

        public Criteria andQualityHeadNotIn(List<String> values) {
            addCriterion("quality_head not in", values, "qualityHead");
            return (Criteria) this;
        }

        public Criteria andQualityHeadBetween(String value1, String value2) {
            addCriterion("quality_head between", value1, value2, "qualityHead");
            return (Criteria) this;
        }

        public Criteria andQualityHeadNotBetween(String value1, String value2) {
            addCriterion("quality_head not between", value1, value2, "qualityHead");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andInvolvedAreaIsNull() {
            addCriterion("involved_area is null");
            return (Criteria) this;
        }

        public Criteria andInvolvedAreaIsNotNull() {
            addCriterion("involved_area is not null");
            return (Criteria) this;
        }

        public Criteria andInvolvedAreaEqualTo(String value) {
            addCriterion("involved_area =", value, "involvedArea");
            return (Criteria) this;
        }

        public Criteria andInvolvedAreaNotEqualTo(String value) {
            addCriterion("involved_area <>", value, "involvedArea");
            return (Criteria) this;
        }

        public Criteria andInvolvedAreaGreaterThan(String value) {
            addCriterion("involved_area >", value, "involvedArea");
            return (Criteria) this;
        }

        public Criteria andInvolvedAreaGreaterThanOrEqualTo(String value) {
            addCriterion("involved_area >=", value, "involvedArea");
            return (Criteria) this;
        }

        public Criteria andInvolvedAreaLessThan(String value) {
            addCriterion("involved_area <", value, "involvedArea");
            return (Criteria) this;
        }

        public Criteria andInvolvedAreaLessThanOrEqualTo(String value) {
            addCriterion("involved_area <=", value, "involvedArea");
            return (Criteria) this;
        }

        public Criteria andInvolvedAreaLike(String value) {
            addCriterion("involved_area like", value, "involvedArea");
            return (Criteria) this;
        }

        public Criteria andInvolvedAreaNotLike(String value) {
            addCriterion("involved_area not like", value, "involvedArea");
            return (Criteria) this;
        }

        public Criteria andInvolvedAreaIn(List<String> values) {
            addCriterion("involved_area in", values, "involvedArea");
            return (Criteria) this;
        }

        public Criteria andInvolvedAreaNotIn(List<String> values) {
            addCriterion("involved_area not in", values, "involvedArea");
            return (Criteria) this;
        }

        public Criteria andInvolvedAreaBetween(String value1, String value2) {
            addCriterion("involved_area between", value1, value2, "involvedArea");
            return (Criteria) this;
        }

        public Criteria andInvolvedAreaNotBetween(String value1, String value2) {
            addCriterion("involved_area not between", value1, value2, "involvedArea");
            return (Criteria) this;
        }

        public Criteria andChangeImportSchemeIsNull() {
            addCriterion("change_import_scheme is null");
            return (Criteria) this;
        }

        public Criteria andChangeImportSchemeIsNotNull() {
            addCriterion("change_import_scheme is not null");
            return (Criteria) this;
        }

        public Criteria andChangeImportSchemeEqualTo(String value) {
            addCriterion("change_import_scheme =", value, "changeImportScheme");
            return (Criteria) this;
        }

        public Criteria andChangeImportSchemeNotEqualTo(String value) {
            addCriterion("change_import_scheme <>", value, "changeImportScheme");
            return (Criteria) this;
        }

        public Criteria andChangeImportSchemeGreaterThan(String value) {
            addCriterion("change_import_scheme >", value, "changeImportScheme");
            return (Criteria) this;
        }

        public Criteria andChangeImportSchemeGreaterThanOrEqualTo(String value) {
            addCriterion("change_import_scheme >=", value, "changeImportScheme");
            return (Criteria) this;
        }

        public Criteria andChangeImportSchemeLessThan(String value) {
            addCriterion("change_import_scheme <", value, "changeImportScheme");
            return (Criteria) this;
        }

        public Criteria andChangeImportSchemeLessThanOrEqualTo(String value) {
            addCriterion("change_import_scheme <=", value, "changeImportScheme");
            return (Criteria) this;
        }

        public Criteria andChangeImportSchemeLike(String value) {
            addCriterion("change_import_scheme like", value, "changeImportScheme");
            return (Criteria) this;
        }

        public Criteria andChangeImportSchemeNotLike(String value) {
            addCriterion("change_import_scheme not like", value, "changeImportScheme");
            return (Criteria) this;
        }

        public Criteria andChangeImportSchemeIn(List<String> values) {
            addCriterion("change_import_scheme in", values, "changeImportScheme");
            return (Criteria) this;
        }

        public Criteria andChangeImportSchemeNotIn(List<String> values) {
            addCriterion("change_import_scheme not in", values, "changeImportScheme");
            return (Criteria) this;
        }

        public Criteria andChangeImportSchemeBetween(String value1, String value2) {
            addCriterion("change_import_scheme between", value1, value2, "changeImportScheme");
            return (Criteria) this;
        }

        public Criteria andChangeImportSchemeNotBetween(String value1, String value2) {
            addCriterion("change_import_scheme not between", value1, value2, "changeImportScheme");
            return (Criteria) this;
        }

        public Criteria andChangeImportTimeIsNull() {
            addCriterion("change_import_time is null");
            return (Criteria) this;
        }

        public Criteria andChangeImportTimeIsNotNull() {
            addCriterion("change_import_time is not null");
            return (Criteria) this;
        }

        public Criteria andChangeImportTimeEqualTo(Date value) {
            addCriterion("change_import_time =", value, "changeImportTime");
            return (Criteria) this;
        }

        public Criteria andChangeImportTimeNotEqualTo(Date value) {
            addCriterion("change_import_time <>", value, "changeImportTime");
            return (Criteria) this;
        }

        public Criteria andChangeImportTimeGreaterThan(Date value) {
            addCriterion("change_import_time >", value, "changeImportTime");
            return (Criteria) this;
        }

        public Criteria andChangeImportTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("change_import_time >=", value, "changeImportTime");
            return (Criteria) this;
        }

        public Criteria andChangeImportTimeLessThan(Date value) {
            addCriterion("change_import_time <", value, "changeImportTime");
            return (Criteria) this;
        }

        public Criteria andChangeImportTimeLessThanOrEqualTo(Date value) {
            addCriterion("change_import_time <=", value, "changeImportTime");
            return (Criteria) this;
        }

        public Criteria andChangeImportTimeIn(List<Date> values) {
            addCriterion("change_import_time in", values, "changeImportTime");
            return (Criteria) this;
        }

        public Criteria andChangeImportTimeNotIn(List<Date> values) {
            addCriterion("change_import_time not in", values, "changeImportTime");
            return (Criteria) this;
        }

        public Criteria andChangeImportTimeBetween(Date value1, Date value2) {
            addCriterion("change_import_time between", value1, value2, "changeImportTime");
            return (Criteria) this;
        }

        public Criteria andChangeImportTimeNotBetween(Date value1, Date value2) {
            addCriterion("change_import_time not between", value1, value2, "changeImportTime");
            return (Criteria) this;
        }

        public Criteria andChangeImportModeIsNull() {
            addCriterion("change_import_mode is null");
            return (Criteria) this;
        }

        public Criteria andChangeImportModeIsNotNull() {
            addCriterion("change_import_mode is not null");
            return (Criteria) this;
        }

        public Criteria andChangeImportModeEqualTo(String value) {
            addCriterion("change_import_mode =", value, "changeImportMode");
            return (Criteria) this;
        }

        public Criteria andChangeImportModeNotEqualTo(String value) {
            addCriterion("change_import_mode <>", value, "changeImportMode");
            return (Criteria) this;
        }

        public Criteria andChangeImportModeGreaterThan(String value) {
            addCriterion("change_import_mode >", value, "changeImportMode");
            return (Criteria) this;
        }

        public Criteria andChangeImportModeGreaterThanOrEqualTo(String value) {
            addCriterion("change_import_mode >=", value, "changeImportMode");
            return (Criteria) this;
        }

        public Criteria andChangeImportModeLessThan(String value) {
            addCriterion("change_import_mode <", value, "changeImportMode");
            return (Criteria) this;
        }

        public Criteria andChangeImportModeLessThanOrEqualTo(String value) {
            addCriterion("change_import_mode <=", value, "changeImportMode");
            return (Criteria) this;
        }

        public Criteria andChangeImportModeLike(String value) {
            addCriterion("change_import_mode like", value, "changeImportMode");
            return (Criteria) this;
        }

        public Criteria andChangeImportModeNotLike(String value) {
            addCriterion("change_import_mode not like", value, "changeImportMode");
            return (Criteria) this;
        }

        public Criteria andChangeImportModeIn(List<String> values) {
            addCriterion("change_import_mode in", values, "changeImportMode");
            return (Criteria) this;
        }

        public Criteria andChangeImportModeNotIn(List<String> values) {
            addCriterion("change_import_mode not in", values, "changeImportMode");
            return (Criteria) this;
        }

        public Criteria andChangeImportModeBetween(String value1, String value2) {
            addCriterion("change_import_mode between", value1, value2, "changeImportMode");
            return (Criteria) this;
        }

        public Criteria andChangeImportModeNotBetween(String value1, String value2) {
            addCriterion("change_import_mode not between", value1, value2, "changeImportMode");
            return (Criteria) this;
        }

        public Criteria andIpqcHandlerIsNull() {
            addCriterion("ipqc_handler is null");
            return (Criteria) this;
        }

        public Criteria andIpqcHandlerIsNotNull() {
            addCriterion("ipqc_handler is not null");
            return (Criteria) this;
        }

        public Criteria andIpqcHandlerEqualTo(String value) {
            addCriterion("ipqc_handler =", value, "ipqcHandler");
            return (Criteria) this;
        }

        public Criteria andIpqcHandlerNotEqualTo(String value) {
            addCriterion("ipqc_handler <>", value, "ipqcHandler");
            return (Criteria) this;
        }

        public Criteria andIpqcHandlerGreaterThan(String value) {
            addCriterion("ipqc_handler >", value, "ipqcHandler");
            return (Criteria) this;
        }

        public Criteria andIpqcHandlerGreaterThanOrEqualTo(String value) {
            addCriterion("ipqc_handler >=", value, "ipqcHandler");
            return (Criteria) this;
        }

        public Criteria andIpqcHandlerLessThan(String value) {
            addCriterion("ipqc_handler <", value, "ipqcHandler");
            return (Criteria) this;
        }

        public Criteria andIpqcHandlerLessThanOrEqualTo(String value) {
            addCriterion("ipqc_handler <=", value, "ipqcHandler");
            return (Criteria) this;
        }

        public Criteria andIpqcHandlerLike(String value) {
            addCriterion("ipqc_handler like", value, "ipqcHandler");
            return (Criteria) this;
        }

        public Criteria andIpqcHandlerNotLike(String value) {
            addCriterion("ipqc_handler not like", value, "ipqcHandler");
            return (Criteria) this;
        }

        public Criteria andIpqcHandlerIn(List<String> values) {
            addCriterion("ipqc_handler in", values, "ipqcHandler");
            return (Criteria) this;
        }

        public Criteria andIpqcHandlerNotIn(List<String> values) {
            addCriterion("ipqc_handler not in", values, "ipqcHandler");
            return (Criteria) this;
        }

        public Criteria andIpqcHandlerBetween(String value1, String value2) {
            addCriterion("ipqc_handler between", value1, value2, "ipqcHandler");
            return (Criteria) this;
        }

        public Criteria andIpqcHandlerNotBetween(String value1, String value2) {
            addCriterion("ipqc_handler not between", value1, value2, "ipqcHandler");
            return (Criteria) this;
        }

        public Criteria andIpqcVerificationResultsIsNull() {
            addCriterion("ipqc_verification_results is null");
            return (Criteria) this;
        }

        public Criteria andIpqcVerificationResultsIsNotNull() {
            addCriterion("ipqc_verification_results is not null");
            return (Criteria) this;
        }

        public Criteria andIpqcVerificationResultsEqualTo(String value) {
            addCriterion("ipqc_verification_results =", value, "ipqcVerificationResults");
            return (Criteria) this;
        }

        public Criteria andIpqcVerificationResultsNotEqualTo(String value) {
            addCriterion("ipqc_verification_results <>", value, "ipqcVerificationResults");
            return (Criteria) this;
        }

        public Criteria andIpqcVerificationResultsGreaterThan(String value) {
            addCriterion("ipqc_verification_results >", value, "ipqcVerificationResults");
            return (Criteria) this;
        }

        public Criteria andIpqcVerificationResultsGreaterThanOrEqualTo(String value) {
            addCriterion("ipqc_verification_results >=", value, "ipqcVerificationResults");
            return (Criteria) this;
        }

        public Criteria andIpqcVerificationResultsLessThan(String value) {
            addCriterion("ipqc_verification_results <", value, "ipqcVerificationResults");
            return (Criteria) this;
        }

        public Criteria andIpqcVerificationResultsLessThanOrEqualTo(String value) {
            addCriterion("ipqc_verification_results <=", value, "ipqcVerificationResults");
            return (Criteria) this;
        }

        public Criteria andIpqcVerificationResultsLike(String value) {
            addCriterion("ipqc_verification_results like", value, "ipqcVerificationResults");
            return (Criteria) this;
        }

        public Criteria andIpqcVerificationResultsNotLike(String value) {
            addCriterion("ipqc_verification_results not like", value, "ipqcVerificationResults");
            return (Criteria) this;
        }

        public Criteria andIpqcVerificationResultsIn(List<String> values) {
            addCriterion("ipqc_verification_results in", values, "ipqcVerificationResults");
            return (Criteria) this;
        }

        public Criteria andIpqcVerificationResultsNotIn(List<String> values) {
            addCriterion("ipqc_verification_results not in", values, "ipqcVerificationResults");
            return (Criteria) this;
        }

        public Criteria andIpqcVerificationResultsBetween(String value1, String value2) {
            addCriterion("ipqc_verification_results between", value1, value2, "ipqcVerificationResults");
            return (Criteria) this;
        }

        public Criteria andIpqcVerificationResultsNotBetween(String value1, String value2) {
            addCriterion("ipqc_verification_results not between", value1, value2, "ipqcVerificationResults");
            return (Criteria) this;
        }

        public Criteria andIpqcVerificationReasonIsNull() {
            addCriterion("ipqc_verification_reason is null");
            return (Criteria) this;
        }

        public Criteria andIpqcVerificationReasonIsNotNull() {
            addCriterion("ipqc_verification_reason is not null");
            return (Criteria) this;
        }

        public Criteria andIpqcVerificationReasonEqualTo(String value) {
            addCriterion("ipqc_verification_reason =", value, "ipqcVerificationReason");
            return (Criteria) this;
        }

        public Criteria andIpqcVerificationReasonNotEqualTo(String value) {
            addCriterion("ipqc_verification_reason <>", value, "ipqcVerificationReason");
            return (Criteria) this;
        }

        public Criteria andIpqcVerificationReasonGreaterThan(String value) {
            addCriterion("ipqc_verification_reason >", value, "ipqcVerificationReason");
            return (Criteria) this;
        }

        public Criteria andIpqcVerificationReasonGreaterThanOrEqualTo(String value) {
            addCriterion("ipqc_verification_reason >=", value, "ipqcVerificationReason");
            return (Criteria) this;
        }

        public Criteria andIpqcVerificationReasonLessThan(String value) {
            addCriterion("ipqc_verification_reason <", value, "ipqcVerificationReason");
            return (Criteria) this;
        }

        public Criteria andIpqcVerificationReasonLessThanOrEqualTo(String value) {
            addCriterion("ipqc_verification_reason <=", value, "ipqcVerificationReason");
            return (Criteria) this;
        }

        public Criteria andIpqcVerificationReasonLike(String value) {
            addCriterion("ipqc_verification_reason like", value, "ipqcVerificationReason");
            return (Criteria) this;
        }

        public Criteria andIpqcVerificationReasonNotLike(String value) {
            addCriterion("ipqc_verification_reason not like", value, "ipqcVerificationReason");
            return (Criteria) this;
        }

        public Criteria andIpqcVerificationReasonIn(List<String> values) {
            addCriterion("ipqc_verification_reason in", values, "ipqcVerificationReason");
            return (Criteria) this;
        }

        public Criteria andIpqcVerificationReasonNotIn(List<String> values) {
            addCriterion("ipqc_verification_reason not in", values, "ipqcVerificationReason");
            return (Criteria) this;
        }

        public Criteria andIpqcVerificationReasonBetween(String value1, String value2) {
            addCriterion("ipqc_verification_reason between", value1, value2, "ipqcVerificationReason");
            return (Criteria) this;
        }

        public Criteria andIpqcVerificationReasonNotBetween(String value1, String value2) {
            addCriterion("ipqc_verification_reason not between", value1, value2, "ipqcVerificationReason");
            return (Criteria) this;
        }

        public Criteria andIpqcVerifierIsNull() {
            addCriterion("ipqc_verifier is null");
            return (Criteria) this;
        }

        public Criteria andIpqcVerifierIsNotNull() {
            addCriterion("ipqc_verifier is not null");
            return (Criteria) this;
        }

        public Criteria andIpqcVerifierEqualTo(String value) {
            addCriterion("ipqc_verifier =", value, "ipqcVerifier");
            return (Criteria) this;
        }

        public Criteria andIpqcVerifierNotEqualTo(String value) {
            addCriterion("ipqc_verifier <>", value, "ipqcVerifier");
            return (Criteria) this;
        }

        public Criteria andIpqcVerifierGreaterThan(String value) {
            addCriterion("ipqc_verifier >", value, "ipqcVerifier");
            return (Criteria) this;
        }

        public Criteria andIpqcVerifierGreaterThanOrEqualTo(String value) {
            addCriterion("ipqc_verifier >=", value, "ipqcVerifier");
            return (Criteria) this;
        }

        public Criteria andIpqcVerifierLessThan(String value) {
            addCriterion("ipqc_verifier <", value, "ipqcVerifier");
            return (Criteria) this;
        }

        public Criteria andIpqcVerifierLessThanOrEqualTo(String value) {
            addCriterion("ipqc_verifier <=", value, "ipqcVerifier");
            return (Criteria) this;
        }

        public Criteria andIpqcVerifierLike(String value) {
            addCriterion("ipqc_verifier like", value, "ipqcVerifier");
            return (Criteria) this;
        }

        public Criteria andIpqcVerifierNotLike(String value) {
            addCriterion("ipqc_verifier not like", value, "ipqcVerifier");
            return (Criteria) this;
        }

        public Criteria andIpqcVerifierIn(List<String> values) {
            addCriterion("ipqc_verifier in", values, "ipqcVerifier");
            return (Criteria) this;
        }

        public Criteria andIpqcVerifierNotIn(List<String> values) {
            addCriterion("ipqc_verifier not in", values, "ipqcVerifier");
            return (Criteria) this;
        }

        public Criteria andIpqcVerifierBetween(String value1, String value2) {
            addCriterion("ipqc_verifier between", value1, value2, "ipqcVerifier");
            return (Criteria) this;
        }

        public Criteria andIpqcVerifierNotBetween(String value1, String value2) {
            addCriterion("ipqc_verifier not between", value1, value2, "ipqcVerifier");
            return (Criteria) this;
        }

        public Criteria andIpqcVerificationTimeIsNull() {
            addCriterion("ipqc_verification_time is null");
            return (Criteria) this;
        }

        public Criteria andIpqcVerificationTimeIsNotNull() {
            addCriterion("ipqc_verification_time is not null");
            return (Criteria) this;
        }

        public Criteria andIpqcVerificationTimeEqualTo(Date value) {
            addCriterion("ipqc_verification_time =", value, "ipqcVerificationTime");
            return (Criteria) this;
        }

        public Criteria andIpqcVerificationTimeNotEqualTo(Date value) {
            addCriterion("ipqc_verification_time <>", value, "ipqcVerificationTime");
            return (Criteria) this;
        }

        public Criteria andIpqcVerificationTimeGreaterThan(Date value) {
            addCriterion("ipqc_verification_time >", value, "ipqcVerificationTime");
            return (Criteria) this;
        }

        public Criteria andIpqcVerificationTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("ipqc_verification_time >=", value, "ipqcVerificationTime");
            return (Criteria) this;
        }

        public Criteria andIpqcVerificationTimeLessThan(Date value) {
            addCriterion("ipqc_verification_time <", value, "ipqcVerificationTime");
            return (Criteria) this;
        }

        public Criteria andIpqcVerificationTimeLessThanOrEqualTo(Date value) {
            addCriterion("ipqc_verification_time <=", value, "ipqcVerificationTime");
            return (Criteria) this;
        }

        public Criteria andIpqcVerificationTimeIn(List<Date> values) {
            addCriterion("ipqc_verification_time in", values, "ipqcVerificationTime");
            return (Criteria) this;
        }

        public Criteria andIpqcVerificationTimeNotIn(List<Date> values) {
            addCriterion("ipqc_verification_time not in", values, "ipqcVerificationTime");
            return (Criteria) this;
        }

        public Criteria andIpqcVerificationTimeBetween(Date value1, Date value2) {
            addCriterion("ipqc_verification_time between", value1, value2, "ipqcVerificationTime");
            return (Criteria) this;
        }

        public Criteria andIpqcVerificationTimeNotBetween(Date value1, Date value2) {
            addCriterion("ipqc_verification_time not between", value1, value2, "ipqcVerificationTime");
            return (Criteria) this;
        }

        public Criteria andProjectApprovalResultIsNull() {
            addCriterion("project_approval_result is null");
            return (Criteria) this;
        }

        public Criteria andProjectApprovalResultIsNotNull() {
            addCriterion("project_approval_result is not null");
            return (Criteria) this;
        }

        public Criteria andProjectApprovalResultEqualTo(String value) {
            addCriterion("project_approval_result =", value, "projectApprovalResult");
            return (Criteria) this;
        }

        public Criteria andProjectApprovalResultNotEqualTo(String value) {
            addCriterion("project_approval_result <>", value, "projectApprovalResult");
            return (Criteria) this;
        }

        public Criteria andProjectApprovalResultGreaterThan(String value) {
            addCriterion("project_approval_result >", value, "projectApprovalResult");
            return (Criteria) this;
        }

        public Criteria andProjectApprovalResultGreaterThanOrEqualTo(String value) {
            addCriterion("project_approval_result >=", value, "projectApprovalResult");
            return (Criteria) this;
        }

        public Criteria andProjectApprovalResultLessThan(String value) {
            addCriterion("project_approval_result <", value, "projectApprovalResult");
            return (Criteria) this;
        }

        public Criteria andProjectApprovalResultLessThanOrEqualTo(String value) {
            addCriterion("project_approval_result <=", value, "projectApprovalResult");
            return (Criteria) this;
        }

        public Criteria andProjectApprovalResultLike(String value) {
            addCriterion("project_approval_result like", value, "projectApprovalResult");
            return (Criteria) this;
        }

        public Criteria andProjectApprovalResultNotLike(String value) {
            addCriterion("project_approval_result not like", value, "projectApprovalResult");
            return (Criteria) this;
        }

        public Criteria andProjectApprovalResultIn(List<String> values) {
            addCriterion("project_approval_result in", values, "projectApprovalResult");
            return (Criteria) this;
        }

        public Criteria andProjectApprovalResultNotIn(List<String> values) {
            addCriterion("project_approval_result not in", values, "projectApprovalResult");
            return (Criteria) this;
        }

        public Criteria andProjectApprovalResultBetween(String value1, String value2) {
            addCriterion("project_approval_result between", value1, value2, "projectApprovalResult");
            return (Criteria) this;
        }

        public Criteria andProjectApprovalResultNotBetween(String value1, String value2) {
            addCriterion("project_approval_result not between", value1, value2, "projectApprovalResult");
            return (Criteria) this;
        }

        public Criteria andProjectApprovalOpinionIsNull() {
            addCriterion("project_approval_opinion is null");
            return (Criteria) this;
        }

        public Criteria andProjectApprovalOpinionIsNotNull() {
            addCriterion("project_approval_opinion is not null");
            return (Criteria) this;
        }

        public Criteria andProjectApprovalOpinionEqualTo(String value) {
            addCriterion("project_approval_opinion =", value, "projectApprovalOpinion");
            return (Criteria) this;
        }

        public Criteria andProjectApprovalOpinionNotEqualTo(String value) {
            addCriterion("project_approval_opinion <>", value, "projectApprovalOpinion");
            return (Criteria) this;
        }

        public Criteria andProjectApprovalOpinionGreaterThan(String value) {
            addCriterion("project_approval_opinion >", value, "projectApprovalOpinion");
            return (Criteria) this;
        }

        public Criteria andProjectApprovalOpinionGreaterThanOrEqualTo(String value) {
            addCriterion("project_approval_opinion >=", value, "projectApprovalOpinion");
            return (Criteria) this;
        }

        public Criteria andProjectApprovalOpinionLessThan(String value) {
            addCriterion("project_approval_opinion <", value, "projectApprovalOpinion");
            return (Criteria) this;
        }

        public Criteria andProjectApprovalOpinionLessThanOrEqualTo(String value) {
            addCriterion("project_approval_opinion <=", value, "projectApprovalOpinion");
            return (Criteria) this;
        }

        public Criteria andProjectApprovalOpinionLike(String value) {
            addCriterion("project_approval_opinion like", value, "projectApprovalOpinion");
            return (Criteria) this;
        }

        public Criteria andProjectApprovalOpinionNotLike(String value) {
            addCriterion("project_approval_opinion not like", value, "projectApprovalOpinion");
            return (Criteria) this;
        }

        public Criteria andProjectApprovalOpinionIn(List<String> values) {
            addCriterion("project_approval_opinion in", values, "projectApprovalOpinion");
            return (Criteria) this;
        }

        public Criteria andProjectApprovalOpinionNotIn(List<String> values) {
            addCriterion("project_approval_opinion not in", values, "projectApprovalOpinion");
            return (Criteria) this;
        }

        public Criteria andProjectApprovalOpinionBetween(String value1, String value2) {
            addCriterion("project_approval_opinion between", value1, value2, "projectApprovalOpinion");
            return (Criteria) this;
        }

        public Criteria andProjectApprovalOpinionNotBetween(String value1, String value2) {
            addCriterion("project_approval_opinion not between", value1, value2, "projectApprovalOpinion");
            return (Criteria) this;
        }

        public Criteria andProjectApproverIsNull() {
            addCriterion("project_approver is null");
            return (Criteria) this;
        }

        public Criteria andProjectApproverIsNotNull() {
            addCriterion("project_approver is not null");
            return (Criteria) this;
        }

        public Criteria andProjectApproverEqualTo(String value) {
            addCriterion("project_approver =", value, "projectApprover");
            return (Criteria) this;
        }

        public Criteria andProjectApproverNotEqualTo(String value) {
            addCriterion("project_approver <>", value, "projectApprover");
            return (Criteria) this;
        }

        public Criteria andProjectApproverGreaterThan(String value) {
            addCriterion("project_approver >", value, "projectApprover");
            return (Criteria) this;
        }

        public Criteria andProjectApproverGreaterThanOrEqualTo(String value) {
            addCriterion("project_approver >=", value, "projectApprover");
            return (Criteria) this;
        }

        public Criteria andProjectApproverLessThan(String value) {
            addCriterion("project_approver <", value, "projectApprover");
            return (Criteria) this;
        }

        public Criteria andProjectApproverLessThanOrEqualTo(String value) {
            addCriterion("project_approver <=", value, "projectApprover");
            return (Criteria) this;
        }

        public Criteria andProjectApproverLike(String value) {
            addCriterion("project_approver like", value, "projectApprover");
            return (Criteria) this;
        }

        public Criteria andProjectApproverNotLike(String value) {
            addCriterion("project_approver not like", value, "projectApprover");
            return (Criteria) this;
        }

        public Criteria andProjectApproverIn(List<String> values) {
            addCriterion("project_approver in", values, "projectApprover");
            return (Criteria) this;
        }

        public Criteria andProjectApproverNotIn(List<String> values) {
            addCriterion("project_approver not in", values, "projectApprover");
            return (Criteria) this;
        }

        public Criteria andProjectApproverBetween(String value1, String value2) {
            addCriterion("project_approver between", value1, value2, "projectApprover");
            return (Criteria) this;
        }

        public Criteria andProjectApproverNotBetween(String value1, String value2) {
            addCriterion("project_approver not between", value1, value2, "projectApprover");
            return (Criteria) this;
        }

        public Criteria andProjectApproverTimeIsNull() {
            addCriterion("project_approver_time is null");
            return (Criteria) this;
        }

        public Criteria andProjectApproverTimeIsNotNull() {
            addCriterion("project_approver_time is not null");
            return (Criteria) this;
        }

        public Criteria andProjectApproverTimeEqualTo(Date value) {
            addCriterion("project_approver_time =", value, "projectApproverTime");
            return (Criteria) this;
        }

        public Criteria andProjectApproverTimeNotEqualTo(Date value) {
            addCriterion("project_approver_time <>", value, "projectApproverTime");
            return (Criteria) this;
        }

        public Criteria andProjectApproverTimeGreaterThan(Date value) {
            addCriterion("project_approver_time >", value, "projectApproverTime");
            return (Criteria) this;
        }

        public Criteria andProjectApproverTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("project_approver_time >=", value, "projectApproverTime");
            return (Criteria) this;
        }

        public Criteria andProjectApproverTimeLessThan(Date value) {
            addCriterion("project_approver_time <", value, "projectApproverTime");
            return (Criteria) this;
        }

        public Criteria andProjectApproverTimeLessThanOrEqualTo(Date value) {
            addCriterion("project_approver_time <=", value, "projectApproverTime");
            return (Criteria) this;
        }

        public Criteria andProjectApproverTimeIn(List<Date> values) {
            addCriterion("project_approver_time in", values, "projectApproverTime");
            return (Criteria) this;
        }

        public Criteria andProjectApproverTimeNotIn(List<Date> values) {
            addCriterion("project_approver_time not in", values, "projectApproverTime");
            return (Criteria) this;
        }

        public Criteria andProjectApproverTimeBetween(Date value1, Date value2) {
            addCriterion("project_approver_time between", value1, value2, "projectApproverTime");
            return (Criteria) this;
        }

        public Criteria andProjectApproverTimeNotBetween(Date value1, Date value2) {
            addCriterion("project_approver_time not between", value1, value2, "projectApproverTime");
            return (Criteria) this;
        }

        public Criteria andOdmApprovalResultIsNull() {
            addCriterion("odm_approval_result is null");
            return (Criteria) this;
        }

        public Criteria andOdmApprovalResultIsNotNull() {
            addCriterion("odm_approval_result is not null");
            return (Criteria) this;
        }

        public Criteria andOdmApprovalResultEqualTo(String value) {
            addCriterion("odm_approval_result =", value, "odmApprovalResult");
            return (Criteria) this;
        }

        public Criteria andOdmApprovalResultNotEqualTo(String value) {
            addCriterion("odm_approval_result <>", value, "odmApprovalResult");
            return (Criteria) this;
        }

        public Criteria andOdmApprovalResultGreaterThan(String value) {
            addCriterion("odm_approval_result >", value, "odmApprovalResult");
            return (Criteria) this;
        }

        public Criteria andOdmApprovalResultGreaterThanOrEqualTo(String value) {
            addCriterion("odm_approval_result >=", value, "odmApprovalResult");
            return (Criteria) this;
        }

        public Criteria andOdmApprovalResultLessThan(String value) {
            addCriterion("odm_approval_result <", value, "odmApprovalResult");
            return (Criteria) this;
        }

        public Criteria andOdmApprovalResultLessThanOrEqualTo(String value) {
            addCriterion("odm_approval_result <=", value, "odmApprovalResult");
            return (Criteria) this;
        }

        public Criteria andOdmApprovalResultLike(String value) {
            addCriterion("odm_approval_result like", value, "odmApprovalResult");
            return (Criteria) this;
        }

        public Criteria andOdmApprovalResultNotLike(String value) {
            addCriterion("odm_approval_result not like", value, "odmApprovalResult");
            return (Criteria) this;
        }

        public Criteria andOdmApprovalResultIn(List<String> values) {
            addCriterion("odm_approval_result in", values, "odmApprovalResult");
            return (Criteria) this;
        }

        public Criteria andOdmApprovalResultNotIn(List<String> values) {
            addCriterion("odm_approval_result not in", values, "odmApprovalResult");
            return (Criteria) this;
        }

        public Criteria andOdmApprovalResultBetween(String value1, String value2) {
            addCriterion("odm_approval_result between", value1, value2, "odmApprovalResult");
            return (Criteria) this;
        }

        public Criteria andOdmApprovalResultNotBetween(String value1, String value2) {
            addCriterion("odm_approval_result not between", value1, value2, "odmApprovalResult");
            return (Criteria) this;
        }

        public Criteria andOdmApprovalOpinionIsNull() {
            addCriterion("odm_approval_opinion is null");
            return (Criteria) this;
        }

        public Criteria andOdmApprovalOpinionIsNotNull() {
            addCriterion("odm_approval_opinion is not null");
            return (Criteria) this;
        }

        public Criteria andOdmApprovalOpinionEqualTo(String value) {
            addCriterion("odm_approval_opinion =", value, "odmApprovalOpinion");
            return (Criteria) this;
        }

        public Criteria andOdmApprovalOpinionNotEqualTo(String value) {
            addCriterion("odm_approval_opinion <>", value, "odmApprovalOpinion");
            return (Criteria) this;
        }

        public Criteria andOdmApprovalOpinionGreaterThan(String value) {
            addCriterion("odm_approval_opinion >", value, "odmApprovalOpinion");
            return (Criteria) this;
        }

        public Criteria andOdmApprovalOpinionGreaterThanOrEqualTo(String value) {
            addCriterion("odm_approval_opinion >=", value, "odmApprovalOpinion");
            return (Criteria) this;
        }

        public Criteria andOdmApprovalOpinionLessThan(String value) {
            addCriterion("odm_approval_opinion <", value, "odmApprovalOpinion");
            return (Criteria) this;
        }

        public Criteria andOdmApprovalOpinionLessThanOrEqualTo(String value) {
            addCriterion("odm_approval_opinion <=", value, "odmApprovalOpinion");
            return (Criteria) this;
        }

        public Criteria andOdmApprovalOpinionLike(String value) {
            addCriterion("odm_approval_opinion like", value, "odmApprovalOpinion");
            return (Criteria) this;
        }

        public Criteria andOdmApprovalOpinionNotLike(String value) {
            addCriterion("odm_approval_opinion not like", value, "odmApprovalOpinion");
            return (Criteria) this;
        }

        public Criteria andOdmApprovalOpinionIn(List<String> values) {
            addCriterion("odm_approval_opinion in", values, "odmApprovalOpinion");
            return (Criteria) this;
        }

        public Criteria andOdmApprovalOpinionNotIn(List<String> values) {
            addCriterion("odm_approval_opinion not in", values, "odmApprovalOpinion");
            return (Criteria) this;
        }

        public Criteria andOdmApprovalOpinionBetween(String value1, String value2) {
            addCriterion("odm_approval_opinion between", value1, value2, "odmApprovalOpinion");
            return (Criteria) this;
        }

        public Criteria andOdmApprovalOpinionNotBetween(String value1, String value2) {
            addCriterion("odm_approval_opinion not between", value1, value2, "odmApprovalOpinion");
            return (Criteria) this;
        }

        public Criteria andOdmApproverIsNull() {
            addCriterion("odm_approver is null");
            return (Criteria) this;
        }

        public Criteria andOdmApproverIsNotNull() {
            addCriterion("odm_approver is not null");
            return (Criteria) this;
        }

        public Criteria andOdmApproverEqualTo(String value) {
            addCriterion("odm_approver =", value, "odmApprover");
            return (Criteria) this;
        }

        public Criteria andOdmApproverNotEqualTo(String value) {
            addCriterion("odm_approver <>", value, "odmApprover");
            return (Criteria) this;
        }

        public Criteria andOdmApproverGreaterThan(String value) {
            addCriterion("odm_approver >", value, "odmApprover");
            return (Criteria) this;
        }

        public Criteria andOdmApproverGreaterThanOrEqualTo(String value) {
            addCriterion("odm_approver >=", value, "odmApprover");
            return (Criteria) this;
        }

        public Criteria andOdmApproverLessThan(String value) {
            addCriterion("odm_approver <", value, "odmApprover");
            return (Criteria) this;
        }

        public Criteria andOdmApproverLessThanOrEqualTo(String value) {
            addCriterion("odm_approver <=", value, "odmApprover");
            return (Criteria) this;
        }

        public Criteria andOdmApproverLike(String value) {
            addCriterion("odm_approver like", value, "odmApprover");
            return (Criteria) this;
        }

        public Criteria andOdmApproverNotLike(String value) {
            addCriterion("odm_approver not like", value, "odmApprover");
            return (Criteria) this;
        }

        public Criteria andOdmApproverIn(List<String> values) {
            addCriterion("odm_approver in", values, "odmApprover");
            return (Criteria) this;
        }

        public Criteria andOdmApproverNotIn(List<String> values) {
            addCriterion("odm_approver not in", values, "odmApprover");
            return (Criteria) this;
        }

        public Criteria andOdmApproverBetween(String value1, String value2) {
            addCriterion("odm_approver between", value1, value2, "odmApprover");
            return (Criteria) this;
        }

        public Criteria andOdmApproverNotBetween(String value1, String value2) {
            addCriterion("odm_approver not between", value1, value2, "odmApprover");
            return (Criteria) this;
        }

        public Criteria andOdmApproverTimeIsNull() {
            addCriterion("odm_approver_time is null");
            return (Criteria) this;
        }

        public Criteria andOdmApproverTimeIsNotNull() {
            addCriterion("odm_approver_time is not null");
            return (Criteria) this;
        }

        public Criteria andOdmApproverTimeEqualTo(Date value) {
            addCriterion("odm_approver_time =", value, "odmApproverTime");
            return (Criteria) this;
        }

        public Criteria andOdmApproverTimeNotEqualTo(Date value) {
            addCriterion("odm_approver_time <>", value, "odmApproverTime");
            return (Criteria) this;
        }

        public Criteria andOdmApproverTimeGreaterThan(Date value) {
            addCriterion("odm_approver_time >", value, "odmApproverTime");
            return (Criteria) this;
        }

        public Criteria andOdmApproverTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("odm_approver_time >=", value, "odmApproverTime");
            return (Criteria) this;
        }

        public Criteria andOdmApproverTimeLessThan(Date value) {
            addCriterion("odm_approver_time <", value, "odmApproverTime");
            return (Criteria) this;
        }

        public Criteria andOdmApproverTimeLessThanOrEqualTo(Date value) {
            addCriterion("odm_approver_time <=", value, "odmApproverTime");
            return (Criteria) this;
        }

        public Criteria andOdmApproverTimeIn(List<Date> values) {
            addCriterion("odm_approver_time in", values, "odmApproverTime");
            return (Criteria) this;
        }

        public Criteria andOdmApproverTimeNotIn(List<Date> values) {
            addCriterion("odm_approver_time not in", values, "odmApproverTime");
            return (Criteria) this;
        }

        public Criteria andOdmApproverTimeBetween(Date value1, Date value2) {
            addCriterion("odm_approver_time between", value1, value2, "odmApproverTime");
            return (Criteria) this;
        }

        public Criteria andOdmApproverTimeNotBetween(Date value1, Date value2) {
            addCriterion("odm_approver_time not between", value1, value2, "odmApproverTime");
            return (Criteria) this;
        }

        public Criteria andSqeApprovalResultIsNull() {
            addCriterion("sqe_approval_result is null");
            return (Criteria) this;
        }

        public Criteria andSqeApprovalResultIsNotNull() {
            addCriterion("sqe_approval_result is not null");
            return (Criteria) this;
        }

        public Criteria andSqeApprovalResultEqualTo(String value) {
            addCriterion("sqe_approval_result =", value, "sqeApprovalResult");
            return (Criteria) this;
        }

        public Criteria andSqeApprovalResultNotEqualTo(String value) {
            addCriterion("sqe_approval_result <>", value, "sqeApprovalResult");
            return (Criteria) this;
        }

        public Criteria andSqeApprovalResultGreaterThan(String value) {
            addCriterion("sqe_approval_result >", value, "sqeApprovalResult");
            return (Criteria) this;
        }

        public Criteria andSqeApprovalResultGreaterThanOrEqualTo(String value) {
            addCriterion("sqe_approval_result >=", value, "sqeApprovalResult");
            return (Criteria) this;
        }

        public Criteria andSqeApprovalResultLessThan(String value) {
            addCriterion("sqe_approval_result <", value, "sqeApprovalResult");
            return (Criteria) this;
        }

        public Criteria andSqeApprovalResultLessThanOrEqualTo(String value) {
            addCriterion("sqe_approval_result <=", value, "sqeApprovalResult");
            return (Criteria) this;
        }

        public Criteria andSqeApprovalResultLike(String value) {
            addCriterion("sqe_approval_result like", value, "sqeApprovalResult");
            return (Criteria) this;
        }

        public Criteria andSqeApprovalResultNotLike(String value) {
            addCriterion("sqe_approval_result not like", value, "sqeApprovalResult");
            return (Criteria) this;
        }

        public Criteria andSqeApprovalResultIn(List<String> values) {
            addCriterion("sqe_approval_result in", values, "sqeApprovalResult");
            return (Criteria) this;
        }

        public Criteria andSqeApprovalResultNotIn(List<String> values) {
            addCriterion("sqe_approval_result not in", values, "sqeApprovalResult");
            return (Criteria) this;
        }

        public Criteria andSqeApprovalResultBetween(String value1, String value2) {
            addCriterion("sqe_approval_result between", value1, value2, "sqeApprovalResult");
            return (Criteria) this;
        }

        public Criteria andSqeApprovalResultNotBetween(String value1, String value2) {
            addCriterion("sqe_approval_result not between", value1, value2, "sqeApprovalResult");
            return (Criteria) this;
        }

        public Criteria andSqeApprovalOpinionIsNull() {
            addCriterion("sqe_approval_opinion is null");
            return (Criteria) this;
        }

        public Criteria andSqeApprovalOpinionIsNotNull() {
            addCriterion("sqe_approval_opinion is not null");
            return (Criteria) this;
        }

        public Criteria andSqeApprovalOpinionEqualTo(String value) {
            addCriterion("sqe_approval_opinion =", value, "sqeApprovalOpinion");
            return (Criteria) this;
        }

        public Criteria andSqeApprovalOpinionNotEqualTo(String value) {
            addCriterion("sqe_approval_opinion <>", value, "sqeApprovalOpinion");
            return (Criteria) this;
        }

        public Criteria andSqeApprovalOpinionGreaterThan(String value) {
            addCriterion("sqe_approval_opinion >", value, "sqeApprovalOpinion");
            return (Criteria) this;
        }

        public Criteria andSqeApprovalOpinionGreaterThanOrEqualTo(String value) {
            addCriterion("sqe_approval_opinion >=", value, "sqeApprovalOpinion");
            return (Criteria) this;
        }

        public Criteria andSqeApprovalOpinionLessThan(String value) {
            addCriterion("sqe_approval_opinion <", value, "sqeApprovalOpinion");
            return (Criteria) this;
        }

        public Criteria andSqeApprovalOpinionLessThanOrEqualTo(String value) {
            addCriterion("sqe_approval_opinion <=", value, "sqeApprovalOpinion");
            return (Criteria) this;
        }

        public Criteria andSqeApprovalOpinionLike(String value) {
            addCriterion("sqe_approval_opinion like", value, "sqeApprovalOpinion");
            return (Criteria) this;
        }

        public Criteria andSqeApprovalOpinionNotLike(String value) {
            addCriterion("sqe_approval_opinion not like", value, "sqeApprovalOpinion");
            return (Criteria) this;
        }

        public Criteria andSqeApprovalOpinionIn(List<String> values) {
            addCriterion("sqe_approval_opinion in", values, "sqeApprovalOpinion");
            return (Criteria) this;
        }

        public Criteria andSqeApprovalOpinionNotIn(List<String> values) {
            addCriterion("sqe_approval_opinion not in", values, "sqeApprovalOpinion");
            return (Criteria) this;
        }

        public Criteria andSqeApprovalOpinionBetween(String value1, String value2) {
            addCriterion("sqe_approval_opinion between", value1, value2, "sqeApprovalOpinion");
            return (Criteria) this;
        }

        public Criteria andSqeApprovalOpinionNotBetween(String value1, String value2) {
            addCriterion("sqe_approval_opinion not between", value1, value2, "sqeApprovalOpinion");
            return (Criteria) this;
        }

        public Criteria andSqeApproverIsNull() {
            addCriterion("sqe_approver is null");
            return (Criteria) this;
        }

        public Criteria andSqeApproverIsNotNull() {
            addCriterion("sqe_approver is not null");
            return (Criteria) this;
        }

        public Criteria andSqeApproverEqualTo(String value) {
            addCriterion("sqe_approver =", value, "sqeApprover");
            return (Criteria) this;
        }

        public Criteria andSqeApproverNotEqualTo(String value) {
            addCriterion("sqe_approver <>", value, "sqeApprover");
            return (Criteria) this;
        }

        public Criteria andSqeApproverGreaterThan(String value) {
            addCriterion("sqe_approver >", value, "sqeApprover");
            return (Criteria) this;
        }

        public Criteria andSqeApproverGreaterThanOrEqualTo(String value) {
            addCriterion("sqe_approver >=", value, "sqeApprover");
            return (Criteria) this;
        }

        public Criteria andSqeApproverLessThan(String value) {
            addCriterion("sqe_approver <", value, "sqeApprover");
            return (Criteria) this;
        }

        public Criteria andSqeApproverLessThanOrEqualTo(String value) {
            addCriterion("sqe_approver <=", value, "sqeApprover");
            return (Criteria) this;
        }

        public Criteria andSqeApproverLike(String value) {
            addCriterion("sqe_approver like", value, "sqeApprover");
            return (Criteria) this;
        }

        public Criteria andSqeApproverNotLike(String value) {
            addCriterion("sqe_approver not like", value, "sqeApprover");
            return (Criteria) this;
        }

        public Criteria andSqeApproverIn(List<String> values) {
            addCriterion("sqe_approver in", values, "sqeApprover");
            return (Criteria) this;
        }

        public Criteria andSqeApproverNotIn(List<String> values) {
            addCriterion("sqe_approver not in", values, "sqeApprover");
            return (Criteria) this;
        }

        public Criteria andSqeApproverBetween(String value1, String value2) {
            addCriterion("sqe_approver between", value1, value2, "sqeApprover");
            return (Criteria) this;
        }

        public Criteria andSqeApproverNotBetween(String value1, String value2) {
            addCriterion("sqe_approver not between", value1, value2, "sqeApprover");
            return (Criteria) this;
        }

        public Criteria andSqeApproverTimeIsNull() {
            addCriterion("sqe_approver_time is null");
            return (Criteria) this;
        }

        public Criteria andSqeApproverTimeIsNotNull() {
            addCriterion("sqe_approver_time is not null");
            return (Criteria) this;
        }

        public Criteria andSqeApproverTimeEqualTo(Date value) {
            addCriterion("sqe_approver_time =", value, "sqeApproverTime");
            return (Criteria) this;
        }

        public Criteria andSqeApproverTimeNotEqualTo(Date value) {
            addCriterion("sqe_approver_time <>", value, "sqeApproverTime");
            return (Criteria) this;
        }

        public Criteria andSqeApproverTimeGreaterThan(Date value) {
            addCriterion("sqe_approver_time >", value, "sqeApproverTime");
            return (Criteria) this;
        }

        public Criteria andSqeApproverTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("sqe_approver_time >=", value, "sqeApproverTime");
            return (Criteria) this;
        }

        public Criteria andSqeApproverTimeLessThan(Date value) {
            addCriterion("sqe_approver_time <", value, "sqeApproverTime");
            return (Criteria) this;
        }

        public Criteria andSqeApproverTimeLessThanOrEqualTo(Date value) {
            addCriterion("sqe_approver_time <=", value, "sqeApproverTime");
            return (Criteria) this;
        }

        public Criteria andSqeApproverTimeIn(List<Date> values) {
            addCriterion("sqe_approver_time in", values, "sqeApproverTime");
            return (Criteria) this;
        }

        public Criteria andSqeApproverTimeNotIn(List<Date> values) {
            addCriterion("sqe_approver_time not in", values, "sqeApproverTime");
            return (Criteria) this;
        }

        public Criteria andSqeApproverTimeBetween(Date value1, Date value2) {
            addCriterion("sqe_approver_time between", value1, value2, "sqeApproverTime");
            return (Criteria) this;
        }

        public Criteria andSqeApproverTimeNotBetween(Date value1, Date value2) {
            addCriterion("sqe_approver_time not between", value1, value2, "sqeApproverTime");
            return (Criteria) this;
        }

        public Criteria andFileAddressIsNull() {
            addCriterion("file_address is null");
            return (Criteria) this;
        }

        public Criteria andFileAddressIsNotNull() {
            addCriterion("file_address is not null");
            return (Criteria) this;
        }

        public Criteria andFileAddressEqualTo(String value) {
            addCriterion("file_address =", value, "fileAddress");
            return (Criteria) this;
        }

        public Criteria andFileAddressNotEqualTo(String value) {
            addCriterion("file_address <>", value, "fileAddress");
            return (Criteria) this;
        }

        public Criteria andFileAddressGreaterThan(String value) {
            addCriterion("file_address >", value, "fileAddress");
            return (Criteria) this;
        }

        public Criteria andFileAddressGreaterThanOrEqualTo(String value) {
            addCriterion("file_address >=", value, "fileAddress");
            return (Criteria) this;
        }

        public Criteria andFileAddressLessThan(String value) {
            addCriterion("file_address <", value, "fileAddress");
            return (Criteria) this;
        }

        public Criteria andFileAddressLessThanOrEqualTo(String value) {
            addCriterion("file_address <=", value, "fileAddress");
            return (Criteria) this;
        }

        public Criteria andFileAddressLike(String value) {
            addCriterion("file_address like", value, "fileAddress");
            return (Criteria) this;
        }

        public Criteria andFileAddressNotLike(String value) {
            addCriterion("file_address not like", value, "fileAddress");
            return (Criteria) this;
        }

        public Criteria andFileAddressIn(List<String> values) {
            addCriterion("file_address in", values, "fileAddress");
            return (Criteria) this;
        }

        public Criteria andFileAddressNotIn(List<String> values) {
            addCriterion("file_address not in", values, "fileAddress");
            return (Criteria) this;
        }

        public Criteria andFileAddressBetween(String value1, String value2) {
            addCriterion("file_address between", value1, value2, "fileAddress");
            return (Criteria) this;
        }

        public Criteria andFileAddressNotBetween(String value1, String value2) {
            addCriterion("file_address not between", value1, value2, "fileAddress");
            return (Criteria) this;
        }

        public Criteria andAssigneeIsNull() {
            addCriterion("assignee is null");
            return (Criteria) this;
        }

        public Criteria andAssigneeIsNotNull() {
            addCriterion("assignee is not null");
            return (Criteria) this;
        }

        public Criteria andAssigneeEqualTo(String value) {
            addCriterion("assignee =", value, "assignee");
            return (Criteria) this;
        }

        public Criteria andAssigneeNotEqualTo(String value) {
            addCriterion("assignee <>", value, "assignee");
            return (Criteria) this;
        }

        public Criteria andAssigneeGreaterThan(String value) {
            addCriterion("assignee >", value, "assignee");
            return (Criteria) this;
        }

        public Criteria andAssigneeGreaterThanOrEqualTo(String value) {
            addCriterion("assignee >=", value, "assignee");
            return (Criteria) this;
        }

        public Criteria andAssigneeLessThan(String value) {
            addCriterion("assignee <", value, "assignee");
            return (Criteria) this;
        }

        public Criteria andAssigneeLessThanOrEqualTo(String value) {
            addCriterion("assignee <=", value, "assignee");
            return (Criteria) this;
        }

        public Criteria andAssigneeLike(String value) {
            addCriterion("assignee like", value, "assignee");
            return (Criteria) this;
        }

        public Criteria andAssigneeNotLike(String value) {
            addCriterion("assignee not like", value, "assignee");
            return (Criteria) this;
        }

        public Criteria andAssigneeIn(List<String> values) {
            addCriterion("assignee in", values, "assignee");
            return (Criteria) this;
        }

        public Criteria andAssigneeNotIn(List<String> values) {
            addCriterion("assignee not in", values, "assignee");
            return (Criteria) this;
        }

        public Criteria andAssigneeBetween(String value1, String value2) {
            addCriterion("assignee between", value1, value2, "assignee");
            return (Criteria) this;
        }

        public Criteria andAssigneeNotBetween(String value1, String value2) {
            addCriterion("assignee not between", value1, value2, "assignee");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNull() {
            addCriterion("creator is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("creator is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(String value) {
            addCriterion("creator =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(String value) {
            addCriterion("creator <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(String value) {
            addCriterion("creator >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("creator >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(String value) {
            addCriterion("creator <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(String value) {
            addCriterion("creator <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLike(String value) {
            addCriterion("creator like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotLike(String value) {
            addCriterion("creator not like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<String> values) {
            addCriterion("creator in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<String> values) {
            addCriterion("creator not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(String value1, String value2) {
            addCriterion("creator between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(String value1, String value2) {
            addCriterion("creator not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("update_date is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("update_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("update_date =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("update_date <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("update_date >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("update_date >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("update_date <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("update_date <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("update_date in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("update_date not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("update_date between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("update_date not between", value1, value2, "updateDate");
            return (Criteria) this;
        }
    }

    public static class Criteria extends BaseGeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}