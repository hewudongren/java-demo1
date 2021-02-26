package cn.jwis.qualityworkflow.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TaskRecordExample() {
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

        public Criteria andTemplateKeyIsNull() {
            addCriterion("template_key is null");
            return (Criteria) this;
        }

        public Criteria andTemplateKeyIsNotNull() {
            addCriterion("template_key is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateKeyEqualTo(String value) {
            addCriterion("template_key =", value, "templateKey");
            return (Criteria) this;
        }

        public Criteria andTemplateKeyNotEqualTo(String value) {
            addCriterion("template_key <>", value, "templateKey");
            return (Criteria) this;
        }

        public Criteria andTemplateKeyGreaterThan(String value) {
            addCriterion("template_key >", value, "templateKey");
            return (Criteria) this;
        }

        public Criteria andTemplateKeyGreaterThanOrEqualTo(String value) {
            addCriterion("template_key >=", value, "templateKey");
            return (Criteria) this;
        }

        public Criteria andTemplateKeyLessThan(String value) {
            addCriterion("template_key <", value, "templateKey");
            return (Criteria) this;
        }

        public Criteria andTemplateKeyLessThanOrEqualTo(String value) {
            addCriterion("template_key <=", value, "templateKey");
            return (Criteria) this;
        }

        public Criteria andTemplateKeyLike(String value) {
            addCriterion("template_key like", value, "templateKey");
            return (Criteria) this;
        }

        public Criteria andTemplateKeyNotLike(String value) {
            addCriterion("template_key not like", value, "templateKey");
            return (Criteria) this;
        }

        public Criteria andTemplateKeyIn(List<String> values) {
            addCriterion("template_key in", values, "templateKey");
            return (Criteria) this;
        }

        public Criteria andTemplateKeyNotIn(List<String> values) {
            addCriterion("template_key not in", values, "templateKey");
            return (Criteria) this;
        }

        public Criteria andTemplateKeyBetween(String value1, String value2) {
            addCriterion("template_key between", value1, value2, "templateKey");
            return (Criteria) this;
        }

        public Criteria andTemplateKeyNotBetween(String value1, String value2) {
            addCriterion("template_key not between", value1, value2, "templateKey");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdIsNull() {
            addCriterion("process_instance_id is null");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdIsNotNull() {
            addCriterion("process_instance_id is not null");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdEqualTo(String value) {
            addCriterion("process_instance_id =", value, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdNotEqualTo(String value) {
            addCriterion("process_instance_id <>", value, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdGreaterThan(String value) {
            addCriterion("process_instance_id >", value, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdGreaterThanOrEqualTo(String value) {
            addCriterion("process_instance_id >=", value, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdLessThan(String value) {
            addCriterion("process_instance_id <", value, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdLessThanOrEqualTo(String value) {
            addCriterion("process_instance_id <=", value, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdLike(String value) {
            addCriterion("process_instance_id like", value, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdNotLike(String value) {
            addCriterion("process_instance_id not like", value, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdIn(List<String> values) {
            addCriterion("process_instance_id in", values, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdNotIn(List<String> values) {
            addCriterion("process_instance_id not in", values, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdBetween(String value1, String value2) {
            addCriterion("process_instance_id between", value1, value2, "processInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstanceIdNotBetween(String value1, String value2) {
            addCriterion("process_instance_id not between", value1, value2, "processInstanceId");
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

        public Criteria andWorkflowBusinessIdIsNull() {
            addCriterion("workflow_business_id is null");
            return (Criteria) this;
        }

        public Criteria andWorkflowBusinessIdIsNotNull() {
            addCriterion("workflow_business_id is not null");
            return (Criteria) this;
        }

        public Criteria andWorkflowBusinessIdEqualTo(String value) {
            addCriterion("workflow_business_id =", value, "workflowBusinessId");
            return (Criteria) this;
        }

        public Criteria andWorkflowBusinessIdNotEqualTo(String value) {
            addCriterion("workflow_business_id <>", value, "workflowBusinessId");
            return (Criteria) this;
        }

        public Criteria andWorkflowBusinessIdGreaterThan(String value) {
            addCriterion("workflow_business_id >", value, "workflowBusinessId");
            return (Criteria) this;
        }

        public Criteria andWorkflowBusinessIdGreaterThanOrEqualTo(String value) {
            addCriterion("workflow_business_id >=", value, "workflowBusinessId");
            return (Criteria) this;
        }

        public Criteria andWorkflowBusinessIdLessThan(String value) {
            addCriterion("workflow_business_id <", value, "workflowBusinessId");
            return (Criteria) this;
        }

        public Criteria andWorkflowBusinessIdLessThanOrEqualTo(String value) {
            addCriterion("workflow_business_id <=", value, "workflowBusinessId");
            return (Criteria) this;
        }

        public Criteria andWorkflowBusinessIdLike(String value) {
            addCriterion("workflow_business_id like", value, "workflowBusinessId");
            return (Criteria) this;
        }

        public Criteria andWorkflowBusinessIdNotLike(String value) {
            addCriterion("workflow_business_id not like", value, "workflowBusinessId");
            return (Criteria) this;
        }

        public Criteria andWorkflowBusinessIdIn(List<String> values) {
            addCriterion("workflow_business_id in", values, "workflowBusinessId");
            return (Criteria) this;
        }

        public Criteria andWorkflowBusinessIdNotIn(List<String> values) {
            addCriterion("workflow_business_id not in", values, "workflowBusinessId");
            return (Criteria) this;
        }

        public Criteria andWorkflowBusinessIdBetween(String value1, String value2) {
            addCriterion("workflow_business_id between", value1, value2, "workflowBusinessId");
            return (Criteria) this;
        }

        public Criteria andWorkflowBusinessIdNotBetween(String value1, String value2) {
            addCriterion("workflow_business_id not between", value1, value2, "workflowBusinessId");
            return (Criteria) this;
        }

        public Criteria andTaskStateIsNull() {
            addCriterion("task_state is null");
            return (Criteria) this;
        }

        public Criteria andTaskStateIsNotNull() {
            addCriterion("task_state is not null");
            return (Criteria) this;
        }

        public Criteria andTaskStateEqualTo(String value) {
            addCriterion("task_state =", value, "taskState");
            return (Criteria) this;
        }

        public Criteria andTaskStateNotEqualTo(String value) {
            addCriterion("task_state <>", value, "taskState");
            return (Criteria) this;
        }

        public Criteria andTaskStateGreaterThan(String value) {
            addCriterion("task_state >", value, "taskState");
            return (Criteria) this;
        }

        public Criteria andTaskStateGreaterThanOrEqualTo(String value) {
            addCriterion("task_state >=", value, "taskState");
            return (Criteria) this;
        }

        public Criteria andTaskStateLessThan(String value) {
            addCriterion("task_state <", value, "taskState");
            return (Criteria) this;
        }

        public Criteria andTaskStateLessThanOrEqualTo(String value) {
            addCriterion("task_state <=", value, "taskState");
            return (Criteria) this;
        }

        public Criteria andTaskStateLike(String value) {
            addCriterion("task_state like", value, "taskState");
            return (Criteria) this;
        }

        public Criteria andTaskStateNotLike(String value) {
            addCriterion("task_state not like", value, "taskState");
            return (Criteria) this;
        }

        public Criteria andTaskStateIn(List<String> values) {
            addCriterion("task_state in", values, "taskState");
            return (Criteria) this;
        }

        public Criteria andTaskStateNotIn(List<String> values) {
            addCriterion("task_state not in", values, "taskState");
            return (Criteria) this;
        }

        public Criteria andTaskStateBetween(String value1, String value2) {
            addCriterion("task_state between", value1, value2, "taskState");
            return (Criteria) this;
        }

        public Criteria andTaskStateNotBetween(String value1, String value2) {
            addCriterion("task_state not between", value1, value2, "taskState");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(String value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("state like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("state not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andTaskNameIsNull() {
            addCriterion("task_name is null");
            return (Criteria) this;
        }

        public Criteria andTaskNameIsNotNull() {
            addCriterion("task_name is not null");
            return (Criteria) this;
        }

        public Criteria andTaskNameEqualTo(String value) {
            addCriterion("task_name =", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotEqualTo(String value) {
            addCriterion("task_name <>", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameGreaterThan(String value) {
            addCriterion("task_name >", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameGreaterThanOrEqualTo(String value) {
            addCriterion("task_name >=", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameLessThan(String value) {
            addCriterion("task_name <", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameLessThanOrEqualTo(String value) {
            addCriterion("task_name <=", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameLike(String value) {
            addCriterion("task_name like", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotLike(String value) {
            addCriterion("task_name not like", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameIn(List<String> values) {
            addCriterion("task_name in", values, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotIn(List<String> values) {
            addCriterion("task_name not in", values, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameBetween(String value1, String value2) {
            addCriterion("task_name between", value1, value2, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotBetween(String value1, String value2) {
            addCriterion("task_name not between", value1, value2, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNull() {
            addCriterion("task_id is null");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNotNull() {
            addCriterion("task_id is not null");
            return (Criteria) this;
        }

        public Criteria andTaskIdEqualTo(String value) {
            addCriterion("task_id =", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotEqualTo(String value) {
            addCriterion("task_id <>", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThan(String value) {
            addCriterion("task_id >", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThanOrEqualTo(String value) {
            addCriterion("task_id >=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThan(String value) {
            addCriterion("task_id <", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThanOrEqualTo(String value) {
            addCriterion("task_id <=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLike(String value) {
            addCriterion("task_id like", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotLike(String value) {
            addCriterion("task_id not like", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdIn(List<String> values) {
            addCriterion("task_id in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotIn(List<String> values) {
            addCriterion("task_id not in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdBetween(String value1, String value2) {
            addCriterion("task_id between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotBetween(String value1, String value2) {
            addCriterion("task_id not between", value1, value2, "taskId");
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