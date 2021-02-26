package cn.jwis.qualityworkflow.modules.ecn.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExternalDocumentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExternalDocumentExample() {
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

        public Criteria andFileTypeIsNull() {
            addCriterion("file_type is null");
            return (Criteria) this;
        }

        public Criteria andFileTypeIsNotNull() {
            addCriterion("file_type is not null");
            return (Criteria) this;
        }

        public Criteria andFileTypeEqualTo(String value) {
            addCriterion("file_type =", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeNotEqualTo(String value) {
            addCriterion("file_type <>", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeGreaterThan(String value) {
            addCriterion("file_type >", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeGreaterThanOrEqualTo(String value) {
            addCriterion("file_type >=", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeLessThan(String value) {
            addCriterion("file_type <", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeLessThanOrEqualTo(String value) {
            addCriterion("file_type <=", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeLike(String value) {
            addCriterion("file_type like", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeNotLike(String value) {
            addCriterion("file_type not like", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeIn(List<String> values) {
            addCriterion("file_type in", values, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeNotIn(List<String> values) {
            addCriterion("file_type not in", values, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeBetween(String value1, String value2) {
            addCriterion("file_type between", value1, value2, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeNotBetween(String value1, String value2) {
            addCriterion("file_type not between", value1, value2, "fileType");
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

        public Criteria andInChargeIsNull() {
            addCriterion("in_charge is null");
            return (Criteria) this;
        }

        public Criteria andInChargeIsNotNull() {
            addCriterion("in_charge is not null");
            return (Criteria) this;
        }

        public Criteria andInChargeEqualTo(String value) {
            addCriterion("in_charge =", value, "inCharge");
            return (Criteria) this;
        }

        public Criteria andInChargeNotEqualTo(String value) {
            addCriterion("in_charge <>", value, "inCharge");
            return (Criteria) this;
        }

        public Criteria andInChargeGreaterThan(String value) {
            addCriterion("in_charge >", value, "inCharge");
            return (Criteria) this;
        }

        public Criteria andInChargeGreaterThanOrEqualTo(String value) {
            addCriterion("in_charge >=", value, "inCharge");
            return (Criteria) this;
        }

        public Criteria andInChargeLessThan(String value) {
            addCriterion("in_charge <", value, "inCharge");
            return (Criteria) this;
        }

        public Criteria andInChargeLessThanOrEqualTo(String value) {
            addCriterion("in_charge <=", value, "inCharge");
            return (Criteria) this;
        }

        public Criteria andInChargeLike(String value) {
            addCriterion("in_charge like", value, "inCharge");
            return (Criteria) this;
        }

        public Criteria andInChargeNotLike(String value) {
            addCriterion("in_charge not like", value, "inCharge");
            return (Criteria) this;
        }

        public Criteria andInChargeIn(List<String> values) {
            addCriterion("in_charge in", values, "inCharge");
            return (Criteria) this;
        }

        public Criteria andInChargeNotIn(List<String> values) {
            addCriterion("in_charge not in", values, "inCharge");
            return (Criteria) this;
        }

        public Criteria andInChargeBetween(String value1, String value2) {
            addCriterion("in_charge between", value1, value2, "inCharge");
            return (Criteria) this;
        }

        public Criteria andInChargeNotBetween(String value1, String value2) {
            addCriterion("in_charge not between", value1, value2, "inCharge");
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

        public Criteria andFileProcessingModeIsNull() {
            addCriterion("file_processing_mode is null");
            return (Criteria) this;
        }

        public Criteria andFileProcessingModeIsNotNull() {
            addCriterion("file_processing_mode is not null");
            return (Criteria) this;
        }

        public Criteria andFileProcessingModeEqualTo(String value) {
            addCriterion("file_processing_mode =", value, "fileProcessingMode");
            return (Criteria) this;
        }

        public Criteria andFileProcessingModeNotEqualTo(String value) {
            addCriterion("file_processing_mode <>", value, "fileProcessingMode");
            return (Criteria) this;
        }

        public Criteria andFileProcessingModeGreaterThan(String value) {
            addCriterion("file_processing_mode >", value, "fileProcessingMode");
            return (Criteria) this;
        }

        public Criteria andFileProcessingModeGreaterThanOrEqualTo(String value) {
            addCriterion("file_processing_mode >=", value, "fileProcessingMode");
            return (Criteria) this;
        }

        public Criteria andFileProcessingModeLessThan(String value) {
            addCriterion("file_processing_mode <", value, "fileProcessingMode");
            return (Criteria) this;
        }

        public Criteria andFileProcessingModeLessThanOrEqualTo(String value) {
            addCriterion("file_processing_mode <=", value, "fileProcessingMode");
            return (Criteria) this;
        }

        public Criteria andFileProcessingModeLike(String value) {
            addCriterion("file_processing_mode like", value, "fileProcessingMode");
            return (Criteria) this;
        }

        public Criteria andFileProcessingModeNotLike(String value) {
            addCriterion("file_processing_mode not like", value, "fileProcessingMode");
            return (Criteria) this;
        }

        public Criteria andFileProcessingModeIn(List<String> values) {
            addCriterion("file_processing_mode in", values, "fileProcessingMode");
            return (Criteria) this;
        }

        public Criteria andFileProcessingModeNotIn(List<String> values) {
            addCriterion("file_processing_mode not in", values, "fileProcessingMode");
            return (Criteria) this;
        }

        public Criteria andFileProcessingModeBetween(String value1, String value2) {
            addCriterion("file_processing_mode between", value1, value2, "fileProcessingMode");
            return (Criteria) this;
        }

        public Criteria andFileProcessingModeNotBetween(String value1, String value2) {
            addCriterion("file_processing_mode not between", value1, value2, "fileProcessingMode");
            return (Criteria) this;
        }

        public Criteria andTransmutesFileNumberIsNull() {
            addCriterion("transmutes_file_number is null");
            return (Criteria) this;
        }

        public Criteria andTransmutesFileNumberIsNotNull() {
            addCriterion("transmutes_file_number is not null");
            return (Criteria) this;
        }

        public Criteria andTransmutesFileNumberEqualTo(String value) {
            addCriterion("transmutes_file_number =", value, "transmutesFileNumber");
            return (Criteria) this;
        }

        public Criteria andTransmutesFileNumberNotEqualTo(String value) {
            addCriterion("transmutes_file_number <>", value, "transmutesFileNumber");
            return (Criteria) this;
        }

        public Criteria andTransmutesFileNumberGreaterThan(String value) {
            addCriterion("transmutes_file_number >", value, "transmutesFileNumber");
            return (Criteria) this;
        }

        public Criteria andTransmutesFileNumberGreaterThanOrEqualTo(String value) {
            addCriterion("transmutes_file_number >=", value, "transmutesFileNumber");
            return (Criteria) this;
        }

        public Criteria andTransmutesFileNumberLessThan(String value) {
            addCriterion("transmutes_file_number <", value, "transmutesFileNumber");
            return (Criteria) this;
        }

        public Criteria andTransmutesFileNumberLessThanOrEqualTo(String value) {
            addCriterion("transmutes_file_number <=", value, "transmutesFileNumber");
            return (Criteria) this;
        }

        public Criteria andTransmutesFileNumberLike(String value) {
            addCriterion("transmutes_file_number like", value, "transmutesFileNumber");
            return (Criteria) this;
        }

        public Criteria andTransmutesFileNumberNotLike(String value) {
            addCriterion("transmutes_file_number not like", value, "transmutesFileNumber");
            return (Criteria) this;
        }

        public Criteria andTransmutesFileNumberIn(List<String> values) {
            addCriterion("transmutes_file_number in", values, "transmutesFileNumber");
            return (Criteria) this;
        }

        public Criteria andTransmutesFileNumberNotIn(List<String> values) {
            addCriterion("transmutes_file_number not in", values, "transmutesFileNumber");
            return (Criteria) this;
        }

        public Criteria andTransmutesFileNumberBetween(String value1, String value2) {
            addCriterion("transmutes_file_number between", value1, value2, "transmutesFileNumber");
            return (Criteria) this;
        }

        public Criteria andTransmutesFileNumberNotBetween(String value1, String value2) {
            addCriterion("transmutes_file_number not between", value1, value2, "transmutesFileNumber");
            return (Criteria) this;
        }

        public Criteria andTransmutesFileNameIsNull() {
            addCriterion("transmutes_file_name is null");
            return (Criteria) this;
        }

        public Criteria andTransmutesFileNameIsNotNull() {
            addCriterion("transmutes_file_name is not null");
            return (Criteria) this;
        }

        public Criteria andTransmutesFileNameEqualTo(String value) {
            addCriterion("transmutes_file_name =", value, "transmutesFileName");
            return (Criteria) this;
        }

        public Criteria andTransmutesFileNameNotEqualTo(String value) {
            addCriterion("transmutes_file_name <>", value, "transmutesFileName");
            return (Criteria) this;
        }

        public Criteria andTransmutesFileNameGreaterThan(String value) {
            addCriterion("transmutes_file_name >", value, "transmutesFileName");
            return (Criteria) this;
        }

        public Criteria andTransmutesFileNameGreaterThanOrEqualTo(String value) {
            addCriterion("transmutes_file_name >=", value, "transmutesFileName");
            return (Criteria) this;
        }

        public Criteria andTransmutesFileNameLessThan(String value) {
            addCriterion("transmutes_file_name <", value, "transmutesFileName");
            return (Criteria) this;
        }

        public Criteria andTransmutesFileNameLessThanOrEqualTo(String value) {
            addCriterion("transmutes_file_name <=", value, "transmutesFileName");
            return (Criteria) this;
        }

        public Criteria andTransmutesFileNameLike(String value) {
            addCriterion("transmutes_file_name like", value, "transmutesFileName");
            return (Criteria) this;
        }

        public Criteria andTransmutesFileNameNotLike(String value) {
            addCriterion("transmutes_file_name not like", value, "transmutesFileName");
            return (Criteria) this;
        }

        public Criteria andTransmutesFileNameIn(List<String> values) {
            addCriterion("transmutes_file_name in", values, "transmutesFileName");
            return (Criteria) this;
        }

        public Criteria andTransmutesFileNameNotIn(List<String> values) {
            addCriterion("transmutes_file_name not in", values, "transmutesFileName");
            return (Criteria) this;
        }

        public Criteria andTransmutesFileNameBetween(String value1, String value2) {
            addCriterion("transmutes_file_name between", value1, value2, "transmutesFileName");
            return (Criteria) this;
        }

        public Criteria andTransmutesFileNameNotBetween(String value1, String value2) {
            addCriterion("transmutes_file_name not between", value1, value2, "transmutesFileName");
            return (Criteria) this;
        }

        public Criteria andFileUsageTimeIsNull() {
            addCriterion("file_usage_time is null");
            return (Criteria) this;
        }

        public Criteria andFileUsageTimeIsNotNull() {
            addCriterion("file_usage_time is not null");
            return (Criteria) this;
        }

        public Criteria andFileUsageTimeEqualTo(Date value) {
            addCriterion("file_usage_time =", value, "fileUsageTime");
            return (Criteria) this;
        }

        public Criteria andFileUsageTimeNotEqualTo(Date value) {
            addCriterion("file_usage_time <>", value, "fileUsageTime");
            return (Criteria) this;
        }

        public Criteria andFileUsageTimeGreaterThan(Date value) {
            addCriterion("file_usage_time >", value, "fileUsageTime");
            return (Criteria) this;
        }

        public Criteria andFileUsageTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("file_usage_time >=", value, "fileUsageTime");
            return (Criteria) this;
        }

        public Criteria andFileUsageTimeLessThan(Date value) {
            addCriterion("file_usage_time <", value, "fileUsageTime");
            return (Criteria) this;
        }

        public Criteria andFileUsageTimeLessThanOrEqualTo(Date value) {
            addCriterion("file_usage_time <=", value, "fileUsageTime");
            return (Criteria) this;
        }

        public Criteria andFileUsageTimeIn(List<Date> values) {
            addCriterion("file_usage_time in", values, "fileUsageTime");
            return (Criteria) this;
        }

        public Criteria andFileUsageTimeNotIn(List<Date> values) {
            addCriterion("file_usage_time not in", values, "fileUsageTime");
            return (Criteria) this;
        }

        public Criteria andFileUsageTimeBetween(Date value1, Date value2) {
            addCriterion("file_usage_time between", value1, value2, "fileUsageTime");
            return (Criteria) this;
        }

        public Criteria andFileUsageTimeNotBetween(Date value1, Date value2) {
            addCriterion("file_usage_time not between", value1, value2, "fileUsageTime");
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

        public Criteria andLtIsNull() {
            addCriterion("LT is null");
            return (Criteria) this;
        }

        public Criteria andLtIsNotNull() {
            addCriterion("LT is not null");
            return (Criteria) this;
        }

        public Criteria andLtEqualTo(String value) {
            addCriterion("LT =", value, "lt");
            return (Criteria) this;
        }

        public Criteria andLtNotEqualTo(String value) {
            addCriterion("LT <>", value, "lt");
            return (Criteria) this;
        }

        public Criteria andLtGreaterThan(String value) {
            addCriterion("LT >", value, "lt");
            return (Criteria) this;
        }

        public Criteria andLtGreaterThanOrEqualTo(String value) {
            addCriterion("LT >=", value, "lt");
            return (Criteria) this;
        }

        public Criteria andLtLessThan(String value) {
            addCriterion("LT <", value, "lt");
            return (Criteria) this;
        }

        public Criteria andLtLessThanOrEqualTo(String value) {
            addCriterion("LT <=", value, "lt");
            return (Criteria) this;
        }

        public Criteria andLtLike(String value) {
            addCriterion("LT like", value, "lt");
            return (Criteria) this;
        }

        public Criteria andLtNotLike(String value) {
            addCriterion("LT not like", value, "lt");
            return (Criteria) this;
        }

        public Criteria andLtIn(List<String> values) {
            addCriterion("LT in", values, "lt");
            return (Criteria) this;
        }

        public Criteria andLtNotIn(List<String> values) {
            addCriterion("LT not in", values, "lt");
            return (Criteria) this;
        }

        public Criteria andLtBetween(String value1, String value2) {
            addCriterion("LT between", value1, value2, "lt");
            return (Criteria) this;
        }

        public Criteria andLtNotBetween(String value1, String value2) {
            addCriterion("LT not between", value1, value2, "lt");
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