package cn.jwis.qualityworkflow.modules.apqp.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApqpPfmeaRiskItemExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ApqpPfmeaRiskItemExample() {
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

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
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

        public Criteria andSubdocumentIdIsNull() {
            addCriterion("subdocument_id is null");
            return (Criteria) this;
        }

        public Criteria andSubdocumentIdIsNotNull() {
            addCriterion("subdocument_id is not null");
            return (Criteria) this;
        }

        public Criteria andSubdocumentIdEqualTo(String value) {
            addCriterion("subdocument_id =", value, "subdocumentId");
            return (Criteria) this;
        }

        public Criteria andSubdocumentIdNotEqualTo(String value) {
            addCriterion("subdocument_id <>", value, "subdocumentId");
            return (Criteria) this;
        }

        public Criteria andSubdocumentIdGreaterThan(String value) {
            addCriterion("subdocument_id >", value, "subdocumentId");
            return (Criteria) this;
        }

        public Criteria andSubdocumentIdGreaterThanOrEqualTo(String value) {
            addCriterion("subdocument_id >=", value, "subdocumentId");
            return (Criteria) this;
        }

        public Criteria andSubdocumentIdLessThan(String value) {
            addCriterion("subdocument_id <", value, "subdocumentId");
            return (Criteria) this;
        }

        public Criteria andSubdocumentIdLessThanOrEqualTo(String value) {
            addCriterion("subdocument_id <=", value, "subdocumentId");
            return (Criteria) this;
        }

        public Criteria andSubdocumentIdLike(String value) {
            addCriterion("subdocument_id like", value, "subdocumentId");
            return (Criteria) this;
        }

        public Criteria andSubdocumentIdNotLike(String value) {
            addCriterion("subdocument_id not like", value, "subdocumentId");
            return (Criteria) this;
        }

        public Criteria andSubdocumentIdIn(List<String> values) {
            addCriterion("subdocument_id in", values, "subdocumentId");
            return (Criteria) this;
        }

        public Criteria andSubdocumentIdNotIn(List<String> values) {
            addCriterion("subdocument_id not in", values, "subdocumentId");
            return (Criteria) this;
        }

        public Criteria andSubdocumentIdBetween(String value1, String value2) {
            addCriterion("subdocument_id between", value1, value2, "subdocumentId");
            return (Criteria) this;
        }

        public Criteria andSubdocumentIdNotBetween(String value1, String value2) {
            addCriterion("subdocument_id not between", value1, value2, "subdocumentId");
            return (Criteria) this;
        }

        public Criteria andModelIsNull() {
            addCriterion("model is null");
            return (Criteria) this;
        }

        public Criteria andModelIsNotNull() {
            addCriterion("model is not null");
            return (Criteria) this;
        }

        public Criteria andModelEqualTo(String value) {
            addCriterion("model =", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelNotEqualTo(String value) {
            addCriterion("model <>", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelGreaterThan(String value) {
            addCriterion("model >", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelGreaterThanOrEqualTo(String value) {
            addCriterion("model >=", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelLessThan(String value) {
            addCriterion("model <", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelLessThanOrEqualTo(String value) {
            addCriterion("model <=", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelLike(String value) {
            addCriterion("model like", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelNotLike(String value) {
            addCriterion("model not like", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelIn(List<String> values) {
            addCriterion("model in", values, "model");
            return (Criteria) this;
        }

        public Criteria andModelNotIn(List<String> values) {
            addCriterion("model not in", values, "model");
            return (Criteria) this;
        }

        public Criteria andModelBetween(String value1, String value2) {
            addCriterion("model between", value1, value2, "model");
            return (Criteria) this;
        }

        public Criteria andModelNotBetween(String value1, String value2) {
            addCriterion("model not between", value1, value2, "model");
            return (Criteria) this;
        }

        public Criteria andEngPhaseIsNull() {
            addCriterion("eng_phase is null");
            return (Criteria) this;
        }

        public Criteria andEngPhaseIsNotNull() {
            addCriterion("eng_phase is not null");
            return (Criteria) this;
        }

        public Criteria andEngPhaseEqualTo(String value) {
            addCriterion("eng_phase =", value, "engPhase");
            return (Criteria) this;
        }

        public Criteria andEngPhaseNotEqualTo(String value) {
            addCriterion("eng_phase <>", value, "engPhase");
            return (Criteria) this;
        }

        public Criteria andEngPhaseGreaterThan(String value) {
            addCriterion("eng_phase >", value, "engPhase");
            return (Criteria) this;
        }

        public Criteria andEngPhaseGreaterThanOrEqualTo(String value) {
            addCriterion("eng_phase >=", value, "engPhase");
            return (Criteria) this;
        }

        public Criteria andEngPhaseLessThan(String value) {
            addCriterion("eng_phase <", value, "engPhase");
            return (Criteria) this;
        }

        public Criteria andEngPhaseLessThanOrEqualTo(String value) {
            addCriterion("eng_phase <=", value, "engPhase");
            return (Criteria) this;
        }

        public Criteria andEngPhaseLike(String value) {
            addCriterion("eng_phase like", value, "engPhase");
            return (Criteria) this;
        }

        public Criteria andEngPhaseNotLike(String value) {
            addCriterion("eng_phase not like", value, "engPhase");
            return (Criteria) this;
        }

        public Criteria andEngPhaseIn(List<String> values) {
            addCriterion("eng_phase in", values, "engPhase");
            return (Criteria) this;
        }

        public Criteria andEngPhaseNotIn(List<String> values) {
            addCriterion("eng_phase not in", values, "engPhase");
            return (Criteria) this;
        }

        public Criteria andEngPhaseBetween(String value1, String value2) {
            addCriterion("eng_phase between", value1, value2, "engPhase");
            return (Criteria) this;
        }

        public Criteria andEngPhaseNotBetween(String value1, String value2) {
            addCriterion("eng_phase not between", value1, value2, "engPhase");
            return (Criteria) this;
        }

        public Criteria andRiskDescriptionIsNull() {
            addCriterion("risk_description is null");
            return (Criteria) this;
        }

        public Criteria andRiskDescriptionIsNotNull() {
            addCriterion("risk_description is not null");
            return (Criteria) this;
        }

        public Criteria andRiskDescriptionEqualTo(String value) {
            addCriterion("risk_description =", value, "riskDescription");
            return (Criteria) this;
        }

        public Criteria andRiskDescriptionNotEqualTo(String value) {
            addCriterion("risk_description <>", value, "riskDescription");
            return (Criteria) this;
        }

        public Criteria andRiskDescriptionGreaterThan(String value) {
            addCriterion("risk_description >", value, "riskDescription");
            return (Criteria) this;
        }

        public Criteria andRiskDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("risk_description >=", value, "riskDescription");
            return (Criteria) this;
        }

        public Criteria andRiskDescriptionLessThan(String value) {
            addCriterion("risk_description <", value, "riskDescription");
            return (Criteria) this;
        }

        public Criteria andRiskDescriptionLessThanOrEqualTo(String value) {
            addCriterion("risk_description <=", value, "riskDescription");
            return (Criteria) this;
        }

        public Criteria andRiskDescriptionLike(String value) {
            addCriterion("risk_description like", value, "riskDescription");
            return (Criteria) this;
        }

        public Criteria andRiskDescriptionNotLike(String value) {
            addCriterion("risk_description not like", value, "riskDescription");
            return (Criteria) this;
        }

        public Criteria andRiskDescriptionIn(List<String> values) {
            addCriterion("risk_description in", values, "riskDescription");
            return (Criteria) this;
        }

        public Criteria andRiskDescriptionNotIn(List<String> values) {
            addCriterion("risk_description not in", values, "riskDescription");
            return (Criteria) this;
        }

        public Criteria andRiskDescriptionBetween(String value1, String value2) {
            addCriterion("risk_description between", value1, value2, "riskDescription");
            return (Criteria) this;
        }

        public Criteria andRiskDescriptionNotBetween(String value1, String value2) {
            addCriterion("risk_description not between", value1, value2, "riskDescription");
            return (Criteria) this;
        }

        public Criteria andRpnIsNull() {
            addCriterion("rpn is null");
            return (Criteria) this;
        }

        public Criteria andRpnIsNotNull() {
            addCriterion("rpn is not null");
            return (Criteria) this;
        }

        public Criteria andRpnEqualTo(String value) {
            addCriterion("rpn =", value, "rpn");
            return (Criteria) this;
        }

        public Criteria andRpnNotEqualTo(String value) {
            addCriterion("rpn <>", value, "rpn");
            return (Criteria) this;
        }

        public Criteria andRpnGreaterThan(String value) {
            addCriterion("rpn >", value, "rpn");
            return (Criteria) this;
        }

        public Criteria andRpnGreaterThanOrEqualTo(String value) {
            addCriterion("rpn >=", value, "rpn");
            return (Criteria) this;
        }

        public Criteria andRpnLessThan(String value) {
            addCriterion("rpn <", value, "rpn");
            return (Criteria) this;
        }

        public Criteria andRpnLessThanOrEqualTo(String value) {
            addCriterion("rpn <=", value, "rpn");
            return (Criteria) this;
        }

        public Criteria andRpnLike(String value) {
            addCriterion("rpn like", value, "rpn");
            return (Criteria) this;
        }

        public Criteria andRpnNotLike(String value) {
            addCriterion("rpn not like", value, "rpn");
            return (Criteria) this;
        }

        public Criteria andRpnIn(List<String> values) {
            addCriterion("rpn in", values, "rpn");
            return (Criteria) this;
        }

        public Criteria andRpnNotIn(List<String> values) {
            addCriterion("rpn not in", values, "rpn");
            return (Criteria) this;
        }

        public Criteria andRpnBetween(String value1, String value2) {
            addCriterion("rpn between", value1, value2, "rpn");
            return (Criteria) this;
        }

        public Criteria andRpnNotBetween(String value1, String value2) {
            addCriterion("rpn not between", value1, value2, "rpn");
            return (Criteria) this;
        }

        public Criteria andOperationStepIsNull() {
            addCriterion("operation_step is null");
            return (Criteria) this;
        }

        public Criteria andOperationStepIsNotNull() {
            addCriterion("operation_step is not null");
            return (Criteria) this;
        }

        public Criteria andOperationStepEqualTo(String value) {
            addCriterion("operation_step =", value, "operationStep");
            return (Criteria) this;
        }

        public Criteria andOperationStepNotEqualTo(String value) {
            addCriterion("operation_step <>", value, "operationStep");
            return (Criteria) this;
        }

        public Criteria andOperationStepGreaterThan(String value) {
            addCriterion("operation_step >", value, "operationStep");
            return (Criteria) this;
        }

        public Criteria andOperationStepGreaterThanOrEqualTo(String value) {
            addCriterion("operation_step >=", value, "operationStep");
            return (Criteria) this;
        }

        public Criteria andOperationStepLessThan(String value) {
            addCriterion("operation_step <", value, "operationStep");
            return (Criteria) this;
        }

        public Criteria andOperationStepLessThanOrEqualTo(String value) {
            addCriterion("operation_step <=", value, "operationStep");
            return (Criteria) this;
        }

        public Criteria andOperationStepLike(String value) {
            addCriterion("operation_step like", value, "operationStep");
            return (Criteria) this;
        }

        public Criteria andOperationStepNotLike(String value) {
            addCriterion("operation_step not like", value, "operationStep");
            return (Criteria) this;
        }

        public Criteria andOperationStepIn(List<String> values) {
            addCriterion("operation_step in", values, "operationStep");
            return (Criteria) this;
        }

        public Criteria andOperationStepNotIn(List<String> values) {
            addCriterion("operation_step not in", values, "operationStep");
            return (Criteria) this;
        }

        public Criteria andOperationStepBetween(String value1, String value2) {
            addCriterion("operation_step between", value1, value2, "operationStep");
            return (Criteria) this;
        }

        public Criteria andOperationStepNotBetween(String value1, String value2) {
            addCriterion("operation_step not between", value1, value2, "operationStep");
            return (Criteria) this;
        }

        public Criteria andPotentialFailureModeIsNull() {
            addCriterion("potential_failure_mode is null");
            return (Criteria) this;
        }

        public Criteria andPotentialFailureModeIsNotNull() {
            addCriterion("potential_failure_mode is not null");
            return (Criteria) this;
        }

        public Criteria andPotentialFailureModeEqualTo(String value) {
            addCriterion("potential_failure_mode =", value, "potentialFailureMode");
            return (Criteria) this;
        }

        public Criteria andPotentialFailureModeNotEqualTo(String value) {
            addCriterion("potential_failure_mode <>", value, "potentialFailureMode");
            return (Criteria) this;
        }

        public Criteria andPotentialFailureModeGreaterThan(String value) {
            addCriterion("potential_failure_mode >", value, "potentialFailureMode");
            return (Criteria) this;
        }

        public Criteria andPotentialFailureModeGreaterThanOrEqualTo(String value) {
            addCriterion("potential_failure_mode >=", value, "potentialFailureMode");
            return (Criteria) this;
        }

        public Criteria andPotentialFailureModeLessThan(String value) {
            addCriterion("potential_failure_mode <", value, "potentialFailureMode");
            return (Criteria) this;
        }

        public Criteria andPotentialFailureModeLessThanOrEqualTo(String value) {
            addCriterion("potential_failure_mode <=", value, "potentialFailureMode");
            return (Criteria) this;
        }

        public Criteria andPotentialFailureModeLike(String value) {
            addCriterion("potential_failure_mode like", value, "potentialFailureMode");
            return (Criteria) this;
        }

        public Criteria andPotentialFailureModeNotLike(String value) {
            addCriterion("potential_failure_mode not like", value, "potentialFailureMode");
            return (Criteria) this;
        }

        public Criteria andPotentialFailureModeIn(List<String> values) {
            addCriterion("potential_failure_mode in", values, "potentialFailureMode");
            return (Criteria) this;
        }

        public Criteria andPotentialFailureModeNotIn(List<String> values) {
            addCriterion("potential_failure_mode not in", values, "potentialFailureMode");
            return (Criteria) this;
        }

        public Criteria andPotentialFailureModeBetween(String value1, String value2) {
            addCriterion("potential_failure_mode between", value1, value2, "potentialFailureMode");
            return (Criteria) this;
        }

        public Criteria andPotentialFailureModeNotBetween(String value1, String value2) {
            addCriterion("potential_failure_mode not between", value1, value2, "potentialFailureMode");
            return (Criteria) this;
        }

        public Criteria andPotentialFailureResultIsNull() {
            addCriterion("potential_failure_result is null");
            return (Criteria) this;
        }

        public Criteria andPotentialFailureResultIsNotNull() {
            addCriterion("potential_failure_result is not null");
            return (Criteria) this;
        }

        public Criteria andPotentialFailureResultEqualTo(String value) {
            addCriterion("potential_failure_result =", value, "potentialFailureResult");
            return (Criteria) this;
        }

        public Criteria andPotentialFailureResultNotEqualTo(String value) {
            addCriterion("potential_failure_result <>", value, "potentialFailureResult");
            return (Criteria) this;
        }

        public Criteria andPotentialFailureResultGreaterThan(String value) {
            addCriterion("potential_failure_result >", value, "potentialFailureResult");
            return (Criteria) this;
        }

        public Criteria andPotentialFailureResultGreaterThanOrEqualTo(String value) {
            addCriterion("potential_failure_result >=", value, "potentialFailureResult");
            return (Criteria) this;
        }

        public Criteria andPotentialFailureResultLessThan(String value) {
            addCriterion("potential_failure_result <", value, "potentialFailureResult");
            return (Criteria) this;
        }

        public Criteria andPotentialFailureResultLessThanOrEqualTo(String value) {
            addCriterion("potential_failure_result <=", value, "potentialFailureResult");
            return (Criteria) this;
        }

        public Criteria andPotentialFailureResultLike(String value) {
            addCriterion("potential_failure_result like", value, "potentialFailureResult");
            return (Criteria) this;
        }

        public Criteria andPotentialFailureResultNotLike(String value) {
            addCriterion("potential_failure_result not like", value, "potentialFailureResult");
            return (Criteria) this;
        }

        public Criteria andPotentialFailureResultIn(List<String> values) {
            addCriterion("potential_failure_result in", values, "potentialFailureResult");
            return (Criteria) this;
        }

        public Criteria andPotentialFailureResultNotIn(List<String> values) {
            addCriterion("potential_failure_result not in", values, "potentialFailureResult");
            return (Criteria) this;
        }

        public Criteria andPotentialFailureResultBetween(String value1, String value2) {
            addCriterion("potential_failure_result between", value1, value2, "potentialFailureResult");
            return (Criteria) this;
        }

        public Criteria andPotentialFailureResultNotBetween(String value1, String value2) {
            addCriterion("potential_failure_result not between", value1, value2, "potentialFailureResult");
            return (Criteria) this;
        }

        public Criteria andPotentialFailureCauseIsNull() {
            addCriterion("potential_failure_cause is null");
            return (Criteria) this;
        }

        public Criteria andPotentialFailureCauseIsNotNull() {
            addCriterion("potential_failure_cause is not null");
            return (Criteria) this;
        }

        public Criteria andPotentialFailureCauseEqualTo(String value) {
            addCriterion("potential_failure_cause =", value, "potentialFailureCause");
            return (Criteria) this;
        }

        public Criteria andPotentialFailureCauseNotEqualTo(String value) {
            addCriterion("potential_failure_cause <>", value, "potentialFailureCause");
            return (Criteria) this;
        }

        public Criteria andPotentialFailureCauseGreaterThan(String value) {
            addCriterion("potential_failure_cause >", value, "potentialFailureCause");
            return (Criteria) this;
        }

        public Criteria andPotentialFailureCauseGreaterThanOrEqualTo(String value) {
            addCriterion("potential_failure_cause >=", value, "potentialFailureCause");
            return (Criteria) this;
        }

        public Criteria andPotentialFailureCauseLessThan(String value) {
            addCriterion("potential_failure_cause <", value, "potentialFailureCause");
            return (Criteria) this;
        }

        public Criteria andPotentialFailureCauseLessThanOrEqualTo(String value) {
            addCriterion("potential_failure_cause <=", value, "potentialFailureCause");
            return (Criteria) this;
        }

        public Criteria andPotentialFailureCauseLike(String value) {
            addCriterion("potential_failure_cause like", value, "potentialFailureCause");
            return (Criteria) this;
        }

        public Criteria andPotentialFailureCauseNotLike(String value) {
            addCriterion("potential_failure_cause not like", value, "potentialFailureCause");
            return (Criteria) this;
        }

        public Criteria andPotentialFailureCauseIn(List<String> values) {
            addCriterion("potential_failure_cause in", values, "potentialFailureCause");
            return (Criteria) this;
        }

        public Criteria andPotentialFailureCauseNotIn(List<String> values) {
            addCriterion("potential_failure_cause not in", values, "potentialFailureCause");
            return (Criteria) this;
        }

        public Criteria andPotentialFailureCauseBetween(String value1, String value2) {
            addCriterion("potential_failure_cause between", value1, value2, "potentialFailureCause");
            return (Criteria) this;
        }

        public Criteria andPotentialFailureCauseNotBetween(String value1, String value2) {
            addCriterion("potential_failure_cause not between", value1, value2, "potentialFailureCause");
            return (Criteria) this;
        }

        public Criteria andSeverityIsNull() {
            addCriterion("severity is null");
            return (Criteria) this;
        }

        public Criteria andSeverityIsNotNull() {
            addCriterion("severity is not null");
            return (Criteria) this;
        }

        public Criteria andSeverityEqualTo(String value) {
            addCriterion("severity =", value, "severity");
            return (Criteria) this;
        }

        public Criteria andSeverityNotEqualTo(String value) {
            addCriterion("severity <>", value, "severity");
            return (Criteria) this;
        }

        public Criteria andSeverityGreaterThan(String value) {
            addCriterion("severity >", value, "severity");
            return (Criteria) this;
        }

        public Criteria andSeverityGreaterThanOrEqualTo(String value) {
            addCriterion("severity >=", value, "severity");
            return (Criteria) this;
        }

        public Criteria andSeverityLessThan(String value) {
            addCriterion("severity <", value, "severity");
            return (Criteria) this;
        }

        public Criteria andSeverityLessThanOrEqualTo(String value) {
            addCriterion("severity <=", value, "severity");
            return (Criteria) this;
        }

        public Criteria andSeverityLike(String value) {
            addCriterion("severity like", value, "severity");
            return (Criteria) this;
        }

        public Criteria andSeverityNotLike(String value) {
            addCriterion("severity not like", value, "severity");
            return (Criteria) this;
        }

        public Criteria andSeverityIn(List<String> values) {
            addCriterion("severity in", values, "severity");
            return (Criteria) this;
        }

        public Criteria andSeverityNotIn(List<String> values) {
            addCriterion("severity not in", values, "severity");
            return (Criteria) this;
        }

        public Criteria andSeverityBetween(String value1, String value2) {
            addCriterion("severity between", value1, value2, "severity");
            return (Criteria) this;
        }

        public Criteria andSeverityNotBetween(String value1, String value2) {
            addCriterion("severity not between", value1, value2, "severity");
            return (Criteria) this;
        }

        public Criteria andFrequencyIsNull() {
            addCriterion("frequency is null");
            return (Criteria) this;
        }

        public Criteria andFrequencyIsNotNull() {
            addCriterion("frequency is not null");
            return (Criteria) this;
        }

        public Criteria andFrequencyEqualTo(String value) {
            addCriterion("frequency =", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyNotEqualTo(String value) {
            addCriterion("frequency <>", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyGreaterThan(String value) {
            addCriterion("frequency >", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyGreaterThanOrEqualTo(String value) {
            addCriterion("frequency >=", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyLessThan(String value) {
            addCriterion("frequency <", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyLessThanOrEqualTo(String value) {
            addCriterion("frequency <=", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyLike(String value) {
            addCriterion("frequency like", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyNotLike(String value) {
            addCriterion("frequency not like", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyIn(List<String> values) {
            addCriterion("frequency in", values, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyNotIn(List<String> values) {
            addCriterion("frequency not in", values, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyBetween(String value1, String value2) {
            addCriterion("frequency between", value1, value2, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyNotBetween(String value1, String value2) {
            addCriterion("frequency not between", value1, value2, "frequency");
            return (Criteria) this;
        }

        public Criteria andDetectabilityIsNull() {
            addCriterion("detectability is null");
            return (Criteria) this;
        }

        public Criteria andDetectabilityIsNotNull() {
            addCriterion("detectability is not null");
            return (Criteria) this;
        }

        public Criteria andDetectabilityEqualTo(String value) {
            addCriterion("detectability =", value, "detectability");
            return (Criteria) this;
        }

        public Criteria andDetectabilityNotEqualTo(String value) {
            addCriterion("detectability <>", value, "detectability");
            return (Criteria) this;
        }

        public Criteria andDetectabilityGreaterThan(String value) {
            addCriterion("detectability >", value, "detectability");
            return (Criteria) this;
        }

        public Criteria andDetectabilityGreaterThanOrEqualTo(String value) {
            addCriterion("detectability >=", value, "detectability");
            return (Criteria) this;
        }

        public Criteria andDetectabilityLessThan(String value) {
            addCriterion("detectability <", value, "detectability");
            return (Criteria) this;
        }

        public Criteria andDetectabilityLessThanOrEqualTo(String value) {
            addCriterion("detectability <=", value, "detectability");
            return (Criteria) this;
        }

        public Criteria andDetectabilityLike(String value) {
            addCriterion("detectability like", value, "detectability");
            return (Criteria) this;
        }

        public Criteria andDetectabilityNotLike(String value) {
            addCriterion("detectability not like", value, "detectability");
            return (Criteria) this;
        }

        public Criteria andDetectabilityIn(List<String> values) {
            addCriterion("detectability in", values, "detectability");
            return (Criteria) this;
        }

        public Criteria andDetectabilityNotIn(List<String> values) {
            addCriterion("detectability not in", values, "detectability");
            return (Criteria) this;
        }

        public Criteria andDetectabilityBetween(String value1, String value2) {
            addCriterion("detectability between", value1, value2, "detectability");
            return (Criteria) this;
        }

        public Criteria andDetectabilityNotBetween(String value1, String value2) {
            addCriterion("detectability not between", value1, value2, "detectability");
            return (Criteria) this;
        }

        public Criteria andCurrentControlIsNull() {
            addCriterion("current_control is null");
            return (Criteria) this;
        }

        public Criteria andCurrentControlIsNotNull() {
            addCriterion("current_control is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentControlEqualTo(String value) {
            addCriterion("current_control =", value, "currentControl");
            return (Criteria) this;
        }

        public Criteria andCurrentControlNotEqualTo(String value) {
            addCriterion("current_control <>", value, "currentControl");
            return (Criteria) this;
        }

        public Criteria andCurrentControlGreaterThan(String value) {
            addCriterion("current_control >", value, "currentControl");
            return (Criteria) this;
        }

        public Criteria andCurrentControlGreaterThanOrEqualTo(String value) {
            addCriterion("current_control >=", value, "currentControl");
            return (Criteria) this;
        }

        public Criteria andCurrentControlLessThan(String value) {
            addCriterion("current_control <", value, "currentControl");
            return (Criteria) this;
        }

        public Criteria andCurrentControlLessThanOrEqualTo(String value) {
            addCriterion("current_control <=", value, "currentControl");
            return (Criteria) this;
        }

        public Criteria andCurrentControlLike(String value) {
            addCriterion("current_control like", value, "currentControl");
            return (Criteria) this;
        }

        public Criteria andCurrentControlNotLike(String value) {
            addCriterion("current_control not like", value, "currentControl");
            return (Criteria) this;
        }

        public Criteria andCurrentControlIn(List<String> values) {
            addCriterion("current_control in", values, "currentControl");
            return (Criteria) this;
        }

        public Criteria andCurrentControlNotIn(List<String> values) {
            addCriterion("current_control not in", values, "currentControl");
            return (Criteria) this;
        }

        public Criteria andCurrentControlBetween(String value1, String value2) {
            addCriterion("current_control between", value1, value2, "currentControl");
            return (Criteria) this;
        }

        public Criteria andCurrentControlNotBetween(String value1, String value2) {
            addCriterion("current_control not between", value1, value2, "currentControl");
            return (Criteria) this;
        }

        public Criteria andPemeaAttachmentIsNull() {
            addCriterion("pemea_attachment is null");
            return (Criteria) this;
        }

        public Criteria andPemeaAttachmentIsNotNull() {
            addCriterion("pemea_attachment is not null");
            return (Criteria) this;
        }

        public Criteria andPemeaAttachmentEqualTo(String value) {
            addCriterion("pemea_attachment =", value, "pemeaAttachment");
            return (Criteria) this;
        }

        public Criteria andPemeaAttachmentNotEqualTo(String value) {
            addCriterion("pemea_attachment <>", value, "pemeaAttachment");
            return (Criteria) this;
        }

        public Criteria andPemeaAttachmentGreaterThan(String value) {
            addCriterion("pemea_attachment >", value, "pemeaAttachment");
            return (Criteria) this;
        }

        public Criteria andPemeaAttachmentGreaterThanOrEqualTo(String value) {
            addCriterion("pemea_attachment >=", value, "pemeaAttachment");
            return (Criteria) this;
        }

        public Criteria andPemeaAttachmentLessThan(String value) {
            addCriterion("pemea_attachment <", value, "pemeaAttachment");
            return (Criteria) this;
        }

        public Criteria andPemeaAttachmentLessThanOrEqualTo(String value) {
            addCriterion("pemea_attachment <=", value, "pemeaAttachment");
            return (Criteria) this;
        }

        public Criteria andPemeaAttachmentLike(String value) {
            addCriterion("pemea_attachment like", value, "pemeaAttachment");
            return (Criteria) this;
        }

        public Criteria andPemeaAttachmentNotLike(String value) {
            addCriterion("pemea_attachment not like", value, "pemeaAttachment");
            return (Criteria) this;
        }

        public Criteria andPemeaAttachmentIn(List<String> values) {
            addCriterion("pemea_attachment in", values, "pemeaAttachment");
            return (Criteria) this;
        }

        public Criteria andPemeaAttachmentNotIn(List<String> values) {
            addCriterion("pemea_attachment not in", values, "pemeaAttachment");
            return (Criteria) this;
        }

        public Criteria andPemeaAttachmentBetween(String value1, String value2) {
            addCriterion("pemea_attachment between", value1, value2, "pemeaAttachment");
            return (Criteria) this;
        }

        public Criteria andPemeaAttachmentNotBetween(String value1, String value2) {
            addCriterion("pemea_attachment not between", value1, value2, "pemeaAttachment");
            return (Criteria) this;
        }

        public Criteria andPemeaCommitTimeIsNull() {
            addCriterion("pemea_commit_time is null");
            return (Criteria) this;
        }

        public Criteria andPemeaCommitTimeIsNotNull() {
            addCriterion("pemea_commit_time is not null");
            return (Criteria) this;
        }

        public Criteria andPemeaCommitTimeEqualTo(Date value) {
            addCriterion("pemea_commit_time =", value, "pemeaCommitTime");
            return (Criteria) this;
        }

        public Criteria andPemeaCommitTimeNotEqualTo(Date value) {
            addCriterion("pemea_commit_time <>", value, "pemeaCommitTime");
            return (Criteria) this;
        }

        public Criteria andPemeaCommitTimeGreaterThan(Date value) {
            addCriterion("pemea_commit_time >", value, "pemeaCommitTime");
            return (Criteria) this;
        }

        public Criteria andPemeaCommitTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("pemea_commit_time >=", value, "pemeaCommitTime");
            return (Criteria) this;
        }

        public Criteria andPemeaCommitTimeLessThan(Date value) {
            addCriterion("pemea_commit_time <", value, "pemeaCommitTime");
            return (Criteria) this;
        }

        public Criteria andPemeaCommitTimeLessThanOrEqualTo(Date value) {
            addCriterion("pemea_commit_time <=", value, "pemeaCommitTime");
            return (Criteria) this;
        }

        public Criteria andPemeaCommitTimeIn(List<Date> values) {
            addCriterion("pemea_commit_time in", values, "pemeaCommitTime");
            return (Criteria) this;
        }

        public Criteria andPemeaCommitTimeNotIn(List<Date> values) {
            addCriterion("pemea_commit_time not in", values, "pemeaCommitTime");
            return (Criteria) this;
        }

        public Criteria andPemeaCommitTimeBetween(Date value1, Date value2) {
            addCriterion("pemea_commit_time between", value1, value2, "pemeaCommitTime");
            return (Criteria) this;
        }

        public Criteria andPemeaCommitTimeNotBetween(Date value1, Date value2) {
            addCriterion("pemea_commit_time not between", value1, value2, "pemeaCommitTime");
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

        public Criteria andRiskSourceIsNull() {
            addCriterion("risk_source is null");
            return (Criteria) this;
        }

        public Criteria andRiskSourceIsNotNull() {
            addCriterion("risk_source is not null");
            return (Criteria) this;
        }

        public Criteria andRiskSourceEqualTo(String value) {
            addCriterion("risk_source =", value, "riskSource");
            return (Criteria) this;
        }

        public Criteria andRiskSourceNotEqualTo(String value) {
            addCriterion("risk_source <>", value, "riskSource");
            return (Criteria) this;
        }

        public Criteria andRiskSourceGreaterThan(String value) {
            addCriterion("risk_source >", value, "riskSource");
            return (Criteria) this;
        }

        public Criteria andRiskSourceGreaterThanOrEqualTo(String value) {
            addCriterion("risk_source >=", value, "riskSource");
            return (Criteria) this;
        }

        public Criteria andRiskSourceLessThan(String value) {
            addCriterion("risk_source <", value, "riskSource");
            return (Criteria) this;
        }

        public Criteria andRiskSourceLessThanOrEqualTo(String value) {
            addCriterion("risk_source <=", value, "riskSource");
            return (Criteria) this;
        }

        public Criteria andRiskSourceLike(String value) {
            addCriterion("risk_source like", value, "riskSource");
            return (Criteria) this;
        }

        public Criteria andRiskSourceNotLike(String value) {
            addCriterion("risk_source not like", value, "riskSource");
            return (Criteria) this;
        }

        public Criteria andRiskSourceIn(List<String> values) {
            addCriterion("risk_source in", values, "riskSource");
            return (Criteria) this;
        }

        public Criteria andRiskSourceNotIn(List<String> values) {
            addCriterion("risk_source not in", values, "riskSource");
            return (Criteria) this;
        }

        public Criteria andRiskSourceBetween(String value1, String value2) {
            addCriterion("risk_source between", value1, value2, "riskSource");
            return (Criteria) this;
        }

        public Criteria andRiskSourceNotBetween(String value1, String value2) {
            addCriterion("risk_source not between", value1, value2, "riskSource");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

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