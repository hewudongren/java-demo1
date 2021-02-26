package cn.jwis.qualityworkflow.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryProcessRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public HistoryProcessRecordExample() {
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

        public Criteria andWorkflowTypeIsNull() {
            addCriterion("workflow_type is null");
            return (Criteria) this;
        }

        public Criteria andWorkflowTypeIsNotNull() {
            addCriterion("workflow_type is not null");
            return (Criteria) this;
        }

        public Criteria andWorkflowTypeEqualTo(String value) {
            addCriterion("workflow_type =", value, "workflowType");
            return (Criteria) this;
        }

        public Criteria andWorkflowTypeNotEqualTo(String value) {
            addCriterion("workflow_type <>", value, "workflowType");
            return (Criteria) this;
        }

        public Criteria andWorkflowTypeGreaterThan(String value) {
            addCriterion("workflow_type >", value, "workflowType");
            return (Criteria) this;
        }

        public Criteria andWorkflowTypeGreaterThanOrEqualTo(String value) {
            addCriterion("workflow_type >=", value, "workflowType");
            return (Criteria) this;
        }

        public Criteria andWorkflowTypeLessThan(String value) {
            addCriterion("workflow_type <", value, "workflowType");
            return (Criteria) this;
        }

        public Criteria andWorkflowTypeLessThanOrEqualTo(String value) {
            addCriterion("workflow_type <=", value, "workflowType");
            return (Criteria) this;
        }

        public Criteria andWorkflowTypeLike(String value) {
            addCriterion("workflow_type like", value, "workflowType");
            return (Criteria) this;
        }

        public Criteria andWorkflowTypeNotLike(String value) {
            addCriterion("workflow_type not like", value, "workflowType");
            return (Criteria) this;
        }

        public Criteria andWorkflowTypeIn(List<String> values) {
            addCriterion("workflow_type in", values, "workflowType");
            return (Criteria) this;
        }

        public Criteria andWorkflowTypeNotIn(List<String> values) {
            addCriterion("workflow_type not in", values, "workflowType");
            return (Criteria) this;
        }

        public Criteria andWorkflowTypeBetween(String value1, String value2) {
            addCriterion("workflow_type between", value1, value2, "workflowType");
            return (Criteria) this;
        }

        public Criteria andWorkflowTypeNotBetween(String value1, String value2) {
            addCriterion("workflow_type not between", value1, value2, "workflowType");
            return (Criteria) this;
        }

        public Criteria andWorkflowNodeIsNull() {
            addCriterion("workflow_node is null");
            return (Criteria) this;
        }

        public Criteria andWorkflowNodeIsNotNull() {
            addCriterion("workflow_node is not null");
            return (Criteria) this;
        }

        public Criteria andWorkflowNodeEqualTo(String value) {
            addCriterion("workflow_node =", value, "workflowNode");
            return (Criteria) this;
        }

        public Criteria andWorkflowNodeNotEqualTo(String value) {
            addCriterion("workflow_node <>", value, "workflowNode");
            return (Criteria) this;
        }

        public Criteria andWorkflowNodeGreaterThan(String value) {
            addCriterion("workflow_node >", value, "workflowNode");
            return (Criteria) this;
        }

        public Criteria andWorkflowNodeGreaterThanOrEqualTo(String value) {
            addCriterion("workflow_node >=", value, "workflowNode");
            return (Criteria) this;
        }

        public Criteria andWorkflowNodeLessThan(String value) {
            addCriterion("workflow_node <", value, "workflowNode");
            return (Criteria) this;
        }

        public Criteria andWorkflowNodeLessThanOrEqualTo(String value) {
            addCriterion("workflow_node <=", value, "workflowNode");
            return (Criteria) this;
        }

        public Criteria andWorkflowNodeLike(String value) {
            addCriterion("workflow_node like", value, "workflowNode");
            return (Criteria) this;
        }

        public Criteria andWorkflowNodeNotLike(String value) {
            addCriterion("workflow_node not like", value, "workflowNode");
            return (Criteria) this;
        }

        public Criteria andWorkflowNodeIn(List<String> values) {
            addCriterion("workflow_node in", values, "workflowNode");
            return (Criteria) this;
        }

        public Criteria andWorkflowNodeNotIn(List<String> values) {
            addCriterion("workflow_node not in", values, "workflowNode");
            return (Criteria) this;
        }

        public Criteria andWorkflowNodeBetween(String value1, String value2) {
            addCriterion("workflow_node between", value1, value2, "workflowNode");
            return (Criteria) this;
        }

        public Criteria andWorkflowNodeNotBetween(String value1, String value2) {
            addCriterion("workflow_node not between", value1, value2, "workflowNode");
            return (Criteria) this;
        }

        public Criteria andWorkflowBusinessidIsNull() {
            addCriterion("workflow_businessId is null");
            return (Criteria) this;
        }

        public Criteria andWorkflowBusinessidIsNotNull() {
            addCriterion("workflow_businessId is not null");
            return (Criteria) this;
        }

        public Criteria andWorkflowBusinessidEqualTo(String value) {
            addCriterion("workflow_businessId =", value, "workflowBusinessid");
            return (Criteria) this;
        }

        public Criteria andWorkflowBusinessidNotEqualTo(String value) {
            addCriterion("workflow_businessId <>", value, "workflowBusinessid");
            return (Criteria) this;
        }

        public Criteria andWorkflowBusinessidGreaterThan(String value) {
            addCriterion("workflow_businessId >", value, "workflowBusinessid");
            return (Criteria) this;
        }

        public Criteria andWorkflowBusinessidGreaterThanOrEqualTo(String value) {
            addCriterion("workflow_businessId >=", value, "workflowBusinessid");
            return (Criteria) this;
        }

        public Criteria andWorkflowBusinessidLessThan(String value) {
            addCriterion("workflow_businessId <", value, "workflowBusinessid");
            return (Criteria) this;
        }

        public Criteria andWorkflowBusinessidLessThanOrEqualTo(String value) {
            addCriterion("workflow_businessId <=", value, "workflowBusinessid");
            return (Criteria) this;
        }

        public Criteria andWorkflowBusinessidLike(String value) {
            addCriterion("workflow_businessId like", value, "workflowBusinessid");
            return (Criteria) this;
        }

        public Criteria andWorkflowBusinessidNotLike(String value) {
            addCriterion("workflow_businessId not like", value, "workflowBusinessid");
            return (Criteria) this;
        }

        public Criteria andWorkflowBusinessidIn(List<String> values) {
            addCriterion("workflow_businessId in", values, "workflowBusinessid");
            return (Criteria) this;
        }

        public Criteria andWorkflowBusinessidNotIn(List<String> values) {
            addCriterion("workflow_businessId not in", values, "workflowBusinessid");
            return (Criteria) this;
        }

        public Criteria andWorkflowBusinessidBetween(String value1, String value2) {
            addCriterion("workflow_businessId between", value1, value2, "workflowBusinessid");
            return (Criteria) this;
        }

        public Criteria andWorkflowBusinessidNotBetween(String value1, String value2) {
            addCriterion("workflow_businessId not between", value1, value2, "workflowBusinessid");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
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

        public Criteria andDepartmentIsNull() {
            addCriterion("department is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentIsNotNull() {
            addCriterion("department is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentEqualTo(String value) {
            addCriterion("department =", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentNotEqualTo(String value) {
            addCriterion("department <>", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentGreaterThan(String value) {
            addCriterion("department >", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentGreaterThanOrEqualTo(String value) {
            addCriterion("department >=", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentLessThan(String value) {
            addCriterion("department <", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentLessThanOrEqualTo(String value) {
            addCriterion("department <=", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentLike(String value) {
            addCriterion("department like", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentNotLike(String value) {
            addCriterion("department not like", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentIn(List<String> values) {
            addCriterion("department in", values, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentNotIn(List<String> values) {
            addCriterion("department not in", values, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentBetween(String value1, String value2) {
            addCriterion("department between", value1, value2, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentNotBetween(String value1, String value2) {
            addCriterion("department not between", value1, value2, "department");
            return (Criteria) this;
        }

        public Criteria andHandleOpinionIsNull() {
            addCriterion("handle_opinion is null");
            return (Criteria) this;
        }

        public Criteria andHandleOpinionIsNotNull() {
            addCriterion("handle_opinion is not null");
            return (Criteria) this;
        }

        public Criteria andHandleOpinionEqualTo(String value) {
            addCriterion("handle_opinion =", value, "handleOpinion");
            return (Criteria) this;
        }

        public Criteria andHandleOpinionNotEqualTo(String value) {
            addCriterion("handle_opinion <>", value, "handleOpinion");
            return (Criteria) this;
        }

        public Criteria andHandleOpinionGreaterThan(String value) {
            addCriterion("handle_opinion >", value, "handleOpinion");
            return (Criteria) this;
        }

        public Criteria andHandleOpinionGreaterThanOrEqualTo(String value) {
            addCriterion("handle_opinion >=", value, "handleOpinion");
            return (Criteria) this;
        }

        public Criteria andHandleOpinionLessThan(String value) {
            addCriterion("handle_opinion <", value, "handleOpinion");
            return (Criteria) this;
        }

        public Criteria andHandleOpinionLessThanOrEqualTo(String value) {
            addCriterion("handle_opinion <=", value, "handleOpinion");
            return (Criteria) this;
        }

        public Criteria andHandleOpinionLike(String value) {
            addCriterion("handle_opinion like", value, "handleOpinion");
            return (Criteria) this;
        }

        public Criteria andHandleOpinionNotLike(String value) {
            addCriterion("handle_opinion not like", value, "handleOpinion");
            return (Criteria) this;
        }

        public Criteria andHandleOpinionIn(List<String> values) {
            addCriterion("handle_opinion in", values, "handleOpinion");
            return (Criteria) this;
        }

        public Criteria andHandleOpinionNotIn(List<String> values) {
            addCriterion("handle_opinion not in", values, "handleOpinion");
            return (Criteria) this;
        }

        public Criteria andHandleOpinionBetween(String value1, String value2) {
            addCriterion("handle_opinion between", value1, value2, "handleOpinion");
            return (Criteria) this;
        }

        public Criteria andHandleOpinionNotBetween(String value1, String value2) {
            addCriterion("handle_opinion not between", value1, value2, "handleOpinion");
            return (Criteria) this;
        }

        public Criteria andRejectReasonIsNull() {
            addCriterion("reject_reason is null");
            return (Criteria) this;
        }

        public Criteria andRejectReasonIsNotNull() {
            addCriterion("reject_reason is not null");
            return (Criteria) this;
        }

        public Criteria andRejectReasonEqualTo(String value) {
            addCriterion("reject_reason =", value, "rejectReason");
            return (Criteria) this;
        }

        public Criteria andRejectReasonNotEqualTo(String value) {
            addCriterion("reject_reason <>", value, "rejectReason");
            return (Criteria) this;
        }

        public Criteria andRejectReasonGreaterThan(String value) {
            addCriterion("reject_reason >", value, "rejectReason");
            return (Criteria) this;
        }

        public Criteria andRejectReasonGreaterThanOrEqualTo(String value) {
            addCriterion("reject_reason >=", value, "rejectReason");
            return (Criteria) this;
        }

        public Criteria andRejectReasonLessThan(String value) {
            addCriterion("reject_reason <", value, "rejectReason");
            return (Criteria) this;
        }

        public Criteria andRejectReasonLessThanOrEqualTo(String value) {
            addCriterion("reject_reason <=", value, "rejectReason");
            return (Criteria) this;
        }

        public Criteria andRejectReasonLike(String value) {
            addCriterion("reject_reason like", value, "rejectReason");
            return (Criteria) this;
        }

        public Criteria andRejectReasonNotLike(String value) {
            addCriterion("reject_reason not like", value, "rejectReason");
            return (Criteria) this;
        }

        public Criteria andRejectReasonIn(List<String> values) {
            addCriterion("reject_reason in", values, "rejectReason");
            return (Criteria) this;
        }

        public Criteria andRejectReasonNotIn(List<String> values) {
            addCriterion("reject_reason not in", values, "rejectReason");
            return (Criteria) this;
        }

        public Criteria andRejectReasonBetween(String value1, String value2) {
            addCriterion("reject_reason between", value1, value2, "rejectReason");
            return (Criteria) this;
        }

        public Criteria andRejectReasonNotBetween(String value1, String value2) {
            addCriterion("reject_reason not between", value1, value2, "rejectReason");
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