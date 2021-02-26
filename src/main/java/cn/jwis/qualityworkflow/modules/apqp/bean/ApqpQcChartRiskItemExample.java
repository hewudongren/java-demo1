package cn.jwis.qualityworkflow.modules.apqp.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApqpQcChartRiskItemExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ApqpQcChartRiskItemExample() {
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

        public Criteria andProductControlModeIsNull() {
            addCriterion("product_control_mode is null");
            return (Criteria) this;
        }

        public Criteria andProductControlModeIsNotNull() {
            addCriterion("product_control_mode is not null");
            return (Criteria) this;
        }

        public Criteria andProductControlModeEqualTo(String value) {
            addCriterion("product_control_mode =", value, "productControlMode");
            return (Criteria) this;
        }

        public Criteria andProductControlModeNotEqualTo(String value) {
            addCriterion("product_control_mode <>", value, "productControlMode");
            return (Criteria) this;
        }

        public Criteria andProductControlModeGreaterThan(String value) {
            addCriterion("product_control_mode >", value, "productControlMode");
            return (Criteria) this;
        }

        public Criteria andProductControlModeGreaterThanOrEqualTo(String value) {
            addCriterion("product_control_mode >=", value, "productControlMode");
            return (Criteria) this;
        }

        public Criteria andProductControlModeLessThan(String value) {
            addCriterion("product_control_mode <", value, "productControlMode");
            return (Criteria) this;
        }

        public Criteria andProductControlModeLessThanOrEqualTo(String value) {
            addCriterion("product_control_mode <=", value, "productControlMode");
            return (Criteria) this;
        }

        public Criteria andProductControlModeLike(String value) {
            addCriterion("product_control_mode like", value, "productControlMode");
            return (Criteria) this;
        }

        public Criteria andProductControlModeNotLike(String value) {
            addCriterion("product_control_mode not like", value, "productControlMode");
            return (Criteria) this;
        }

        public Criteria andProductControlModeIn(List<String> values) {
            addCriterion("product_control_mode in", values, "productControlMode");
            return (Criteria) this;
        }

        public Criteria andProductControlModeNotIn(List<String> values) {
            addCriterion("product_control_mode not in", values, "productControlMode");
            return (Criteria) this;
        }

        public Criteria andProductControlModeBetween(String value1, String value2) {
            addCriterion("product_control_mode between", value1, value2, "productControlMode");
            return (Criteria) this;
        }

        public Criteria andProductControlModeNotBetween(String value1, String value2) {
            addCriterion("product_control_mode not between", value1, value2, "productControlMode");
            return (Criteria) this;
        }

        public Criteria andProductProcessSpecificationIsNull() {
            addCriterion("product_process_specification is null");
            return (Criteria) this;
        }

        public Criteria andProductProcessSpecificationIsNotNull() {
            addCriterion("product_process_specification is not null");
            return (Criteria) this;
        }

        public Criteria andProductProcessSpecificationEqualTo(String value) {
            addCriterion("product_process_specification =", value, "productProcessSpecification");
            return (Criteria) this;
        }

        public Criteria andProductProcessSpecificationNotEqualTo(String value) {
            addCriterion("product_process_specification <>", value, "productProcessSpecification");
            return (Criteria) this;
        }

        public Criteria andProductProcessSpecificationGreaterThan(String value) {
            addCriterion("product_process_specification >", value, "productProcessSpecification");
            return (Criteria) this;
        }

        public Criteria andProductProcessSpecificationGreaterThanOrEqualTo(String value) {
            addCriterion("product_process_specification >=", value, "productProcessSpecification");
            return (Criteria) this;
        }

        public Criteria andProductProcessSpecificationLessThan(String value) {
            addCriterion("product_process_specification <", value, "productProcessSpecification");
            return (Criteria) this;
        }

        public Criteria andProductProcessSpecificationLessThanOrEqualTo(String value) {
            addCriterion("product_process_specification <=", value, "productProcessSpecification");
            return (Criteria) this;
        }

        public Criteria andProductProcessSpecificationLike(String value) {
            addCriterion("product_process_specification like", value, "productProcessSpecification");
            return (Criteria) this;
        }

        public Criteria andProductProcessSpecificationNotLike(String value) {
            addCriterion("product_process_specification not like", value, "productProcessSpecification");
            return (Criteria) this;
        }

        public Criteria andProductProcessSpecificationIn(List<String> values) {
            addCriterion("product_process_specification in", values, "productProcessSpecification");
            return (Criteria) this;
        }

        public Criteria andProductProcessSpecificationNotIn(List<String> values) {
            addCriterion("product_process_specification not in", values, "productProcessSpecification");
            return (Criteria) this;
        }

        public Criteria andProductProcessSpecificationBetween(String value1, String value2) {
            addCriterion("product_process_specification between", value1, value2, "productProcessSpecification");
            return (Criteria) this;
        }

        public Criteria andProductProcessSpecificationNotBetween(String value1, String value2) {
            addCriterion("product_process_specification not between", value1, value2, "productProcessSpecification");
            return (Criteria) this;
        }

        public Criteria andQcChartAttachmentIsNull() {
            addCriterion("qc_chart_attachment is null");
            return (Criteria) this;
        }

        public Criteria andQcChartAttachmentIsNotNull() {
            addCriterion("qc_chart_attachment is not null");
            return (Criteria) this;
        }

        public Criteria andQcChartAttachmentEqualTo(String value) {
            addCriterion("qc_chart_attachment =", value, "qcChartAttachment");
            return (Criteria) this;
        }

        public Criteria andQcChartAttachmentNotEqualTo(String value) {
            addCriterion("qc_chart_attachment <>", value, "qcChartAttachment");
            return (Criteria) this;
        }

        public Criteria andQcChartAttachmentGreaterThan(String value) {
            addCriterion("qc_chart_attachment >", value, "qcChartAttachment");
            return (Criteria) this;
        }

        public Criteria andQcChartAttachmentGreaterThanOrEqualTo(String value) {
            addCriterion("qc_chart_attachment >=", value, "qcChartAttachment");
            return (Criteria) this;
        }

        public Criteria andQcChartAttachmentLessThan(String value) {
            addCriterion("qc_chart_attachment <", value, "qcChartAttachment");
            return (Criteria) this;
        }

        public Criteria andQcChartAttachmentLessThanOrEqualTo(String value) {
            addCriterion("qc_chart_attachment <=", value, "qcChartAttachment");
            return (Criteria) this;
        }

        public Criteria andQcChartAttachmentLike(String value) {
            addCriterion("qc_chart_attachment like", value, "qcChartAttachment");
            return (Criteria) this;
        }

        public Criteria andQcChartAttachmentNotLike(String value) {
            addCriterion("qc_chart_attachment not like", value, "qcChartAttachment");
            return (Criteria) this;
        }

        public Criteria andQcChartAttachmentIn(List<String> values) {
            addCriterion("qc_chart_attachment in", values, "qcChartAttachment");
            return (Criteria) this;
        }

        public Criteria andQcChartAttachmentNotIn(List<String> values) {
            addCriterion("qc_chart_attachment not in", values, "qcChartAttachment");
            return (Criteria) this;
        }

        public Criteria andQcChartAttachmentBetween(String value1, String value2) {
            addCriterion("qc_chart_attachment between", value1, value2, "qcChartAttachment");
            return (Criteria) this;
        }

        public Criteria andQcChartAttachmentNotBetween(String value1, String value2) {
            addCriterion("qc_chart_attachment not between", value1, value2, "qcChartAttachment");
            return (Criteria) this;
        }

        public Criteria andQcChartCommitTimeIsNull() {
            addCriterion("qc_chart_commit_time is null");
            return (Criteria) this;
        }

        public Criteria andQcChartCommitTimeIsNotNull() {
            addCriterion("qc_chart_commit_time is not null");
            return (Criteria) this;
        }

        public Criteria andQcChartCommitTimeEqualTo(Date value) {
            addCriterion("qc_chart_commit_time =", value, "qcChartCommitTime");
            return (Criteria) this;
        }

        public Criteria andQcChartCommitTimeNotEqualTo(Date value) {
            addCriterion("qc_chart_commit_time <>", value, "qcChartCommitTime");
            return (Criteria) this;
        }

        public Criteria andQcChartCommitTimeGreaterThan(Date value) {
            addCriterion("qc_chart_commit_time >", value, "qcChartCommitTime");
            return (Criteria) this;
        }

        public Criteria andQcChartCommitTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("qc_chart_commit_time >=", value, "qcChartCommitTime");
            return (Criteria) this;
        }

        public Criteria andQcChartCommitTimeLessThan(Date value) {
            addCriterion("qc_chart_commit_time <", value, "qcChartCommitTime");
            return (Criteria) this;
        }

        public Criteria andQcChartCommitTimeLessThanOrEqualTo(Date value) {
            addCriterion("qc_chart_commit_time <=", value, "qcChartCommitTime");
            return (Criteria) this;
        }

        public Criteria andQcChartCommitTimeIn(List<Date> values) {
            addCriterion("qc_chart_commit_time in", values, "qcChartCommitTime");
            return (Criteria) this;
        }

        public Criteria andQcChartCommitTimeNotIn(List<Date> values) {
            addCriterion("qc_chart_commit_time not in", values, "qcChartCommitTime");
            return (Criteria) this;
        }

        public Criteria andQcChartCommitTimeBetween(Date value1, Date value2) {
            addCriterion("qc_chart_commit_time between", value1, value2, "qcChartCommitTime");
            return (Criteria) this;
        }

        public Criteria andQcChartCommitTimeNotBetween(Date value1, Date value2) {
            addCriterion("qc_chart_commit_time not between", value1, value2, "qcChartCommitTime");
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