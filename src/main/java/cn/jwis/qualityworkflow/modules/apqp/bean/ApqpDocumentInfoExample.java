package cn.jwis.qualityworkflow.modules.apqp.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApqpDocumentInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ApqpDocumentInfoExample() {
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

        public Criteria andDocumentNodeIsNull() {
            addCriterion("document_node is null");
            return (Criteria) this;
        }

        public Criteria andDocumentNodeIsNotNull() {
            addCriterion("document_node is not null");
            return (Criteria) this;
        }

        public Criteria andDocumentNodeEqualTo(String value) {
            addCriterion("document_node =", value, "documentNode");
            return (Criteria) this;
        }

        public Criteria andDocumentNodeNotEqualTo(String value) {
            addCriterion("document_node <>", value, "documentNode");
            return (Criteria) this;
        }

        public Criteria andDocumentNodeGreaterThan(String value) {
            addCriterion("document_node >", value, "documentNode");
            return (Criteria) this;
        }

        public Criteria andDocumentNodeGreaterThanOrEqualTo(String value) {
            addCriterion("document_node >=", value, "documentNode");
            return (Criteria) this;
        }

        public Criteria andDocumentNodeLessThan(String value) {
            addCriterion("document_node <", value, "documentNode");
            return (Criteria) this;
        }

        public Criteria andDocumentNodeLessThanOrEqualTo(String value) {
            addCriterion("document_node <=", value, "documentNode");
            return (Criteria) this;
        }

        public Criteria andDocumentNodeLike(String value) {
            addCriterion("document_node like", value, "documentNode");
            return (Criteria) this;
        }

        public Criteria andDocumentNodeNotLike(String value) {
            addCriterion("document_node not like", value, "documentNode");
            return (Criteria) this;
        }

        public Criteria andDocumentNodeIn(List<String> values) {
            addCriterion("document_node in", values, "documentNode");
            return (Criteria) this;
        }

        public Criteria andDocumentNodeNotIn(List<String> values) {
            addCriterion("document_node not in", values, "documentNode");
            return (Criteria) this;
        }

        public Criteria andDocumentNodeBetween(String value1, String value2) {
            addCriterion("document_node between", value1, value2, "documentNode");
            return (Criteria) this;
        }

        public Criteria andDocumentNodeNotBetween(String value1, String value2) {
            addCriterion("document_node not between", value1, value2, "documentNode");
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

        public Criteria andDocumentIsNull() {
            addCriterion("document is null");
            return (Criteria) this;
        }

        public Criteria andDocumentIsNotNull() {
            addCriterion("document is not null");
            return (Criteria) this;
        }

        public Criteria andDocumentEqualTo(String value) {
            addCriterion("document =", value, "document");
            return (Criteria) this;
        }

        public Criteria andDocumentNotEqualTo(String value) {
            addCriterion("document <>", value, "document");
            return (Criteria) this;
        }

        public Criteria andDocumentGreaterThan(String value) {
            addCriterion("document >", value, "document");
            return (Criteria) this;
        }

        public Criteria andDocumentGreaterThanOrEqualTo(String value) {
            addCriterion("document >=", value, "document");
            return (Criteria) this;
        }

        public Criteria andDocumentLessThan(String value) {
            addCriterion("document <", value, "document");
            return (Criteria) this;
        }

        public Criteria andDocumentLessThanOrEqualTo(String value) {
            addCriterion("document <=", value, "document");
            return (Criteria) this;
        }

        public Criteria andDocumentLike(String value) {
            addCriterion("document like", value, "document");
            return (Criteria) this;
        }

        public Criteria andDocumentNotLike(String value) {
            addCriterion("document not like", value, "document");
            return (Criteria) this;
        }

        public Criteria andDocumentIn(List<String> values) {
            addCriterion("document in", values, "document");
            return (Criteria) this;
        }

        public Criteria andDocumentNotIn(List<String> values) {
            addCriterion("document not in", values, "document");
            return (Criteria) this;
        }

        public Criteria andDocumentBetween(String value1, String value2) {
            addCriterion("document between", value1, value2, "document");
            return (Criteria) this;
        }

        public Criteria andDocumentNotBetween(String value1, String value2) {
            addCriterion("document not between", value1, value2, "document");
            return (Criteria) this;
        }

        public Criteria andDocumentNameIsNull() {
            addCriterion("document_name is null");
            return (Criteria) this;
        }

        public Criteria andDocumentNameIsNotNull() {
            addCriterion("document_name is not null");
            return (Criteria) this;
        }

        public Criteria andDocumentNameEqualTo(String value) {
            addCriterion("document_name =", value, "documentName");
            return (Criteria) this;
        }

        public Criteria andDocumentNameNotEqualTo(String value) {
            addCriterion("document_name <>", value, "documentName");
            return (Criteria) this;
        }

        public Criteria andDocumentNameGreaterThan(String value) {
            addCriterion("document_name >", value, "documentName");
            return (Criteria) this;
        }

        public Criteria andDocumentNameGreaterThanOrEqualTo(String value) {
            addCriterion("document_name >=", value, "documentName");
            return (Criteria) this;
        }

        public Criteria andDocumentNameLessThan(String value) {
            addCriterion("document_name <", value, "documentName");
            return (Criteria) this;
        }

        public Criteria andDocumentNameLessThanOrEqualTo(String value) {
            addCriterion("document_name <=", value, "documentName");
            return (Criteria) this;
        }

        public Criteria andDocumentNameLike(String value) {
            addCriterion("document_name like", value, "documentName");
            return (Criteria) this;
        }

        public Criteria andDocumentNameNotLike(String value) {
            addCriterion("document_name not like", value, "documentName");
            return (Criteria) this;
        }

        public Criteria andDocumentNameIn(List<String> values) {
            addCriterion("document_name in", values, "documentName");
            return (Criteria) this;
        }

        public Criteria andDocumentNameNotIn(List<String> values) {
            addCriterion("document_name not in", values, "documentName");
            return (Criteria) this;
        }

        public Criteria andDocumentNameBetween(String value1, String value2) {
            addCriterion("document_name between", value1, value2, "documentName");
            return (Criteria) this;
        }

        public Criteria andDocumentNameNotBetween(String value1, String value2) {
            addCriterion("document_name not between", value1, value2, "documentName");
            return (Criteria) this;
        }

        public Criteria andDocumentOidIsNull() {
            addCriterion("document_oid is null");
            return (Criteria) this;
        }

        public Criteria andDocumentOidIsNotNull() {
            addCriterion("document_oid is not null");
            return (Criteria) this;
        }

        public Criteria andDocumentOidEqualTo(String value) {
            addCriterion("document_oid =", value, "documentOid");
            return (Criteria) this;
        }

        public Criteria andDocumentOidNotEqualTo(String value) {
            addCriterion("document_oid <>", value, "documentOid");
            return (Criteria) this;
        }

        public Criteria andDocumentOidGreaterThan(String value) {
            addCriterion("document_oid >", value, "documentOid");
            return (Criteria) this;
        }

        public Criteria andDocumentOidGreaterThanOrEqualTo(String value) {
            addCriterion("document_oid >=", value, "documentOid");
            return (Criteria) this;
        }

        public Criteria andDocumentOidLessThan(String value) {
            addCriterion("document_oid <", value, "documentOid");
            return (Criteria) this;
        }

        public Criteria andDocumentOidLessThanOrEqualTo(String value) {
            addCriterion("document_oid <=", value, "documentOid");
            return (Criteria) this;
        }

        public Criteria andDocumentOidLike(String value) {
            addCriterion("document_oid like", value, "documentOid");
            return (Criteria) this;
        }

        public Criteria andDocumentOidNotLike(String value) {
            addCriterion("document_oid not like", value, "documentOid");
            return (Criteria) this;
        }

        public Criteria andDocumentOidIn(List<String> values) {
            addCriterion("document_oid in", values, "documentOid");
            return (Criteria) this;
        }

        public Criteria andDocumentOidNotIn(List<String> values) {
            addCriterion("document_oid not in", values, "documentOid");
            return (Criteria) this;
        }

        public Criteria andDocumentOidBetween(String value1, String value2) {
            addCriterion("document_oid between", value1, value2, "documentOid");
            return (Criteria) this;
        }

        public Criteria andDocumentOidNotBetween(String value1, String value2) {
            addCriterion("document_oid not between", value1, value2, "documentOid");
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

        public Criteria andDeadlineIsNull() {
            addCriterion("deadline is null");
            return (Criteria) this;
        }

        public Criteria andDeadlineIsNotNull() {
            addCriterion("deadline is not null");
            return (Criteria) this;
        }

        public Criteria andDeadlineEqualTo(Date value) {
            addCriterion("deadline =", value, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineNotEqualTo(Date value) {
            addCriterion("deadline <>", value, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineGreaterThan(Date value) {
            addCriterion("deadline >", value, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineGreaterThanOrEqualTo(Date value) {
            addCriterion("deadline >=", value, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineLessThan(Date value) {
            addCriterion("deadline <", value, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineLessThanOrEqualTo(Date value) {
            addCriterion("deadline <=", value, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineIn(List<Date> values) {
            addCriterion("deadline in", values, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineNotIn(List<Date> values) {
            addCriterion("deadline not in", values, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineBetween(Date value1, Date value2) {
            addCriterion("deadline between", value1, value2, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineNotBetween(Date value1, Date value2) {
            addCriterion("deadline not between", value1, value2, "deadline");
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

        public Criteria andSummitTimeIsNull() {
            addCriterion("summit_time is null");
            return (Criteria) this;
        }

        public Criteria andSummitTimeIsNotNull() {
            addCriterion("summit_time is not null");
            return (Criteria) this;
        }

        public Criteria andSummitTimeEqualTo(Date value) {
            addCriterion("summit_time =", value, "summitTime");
            return (Criteria) this;
        }

        public Criteria andSummitTimeNotEqualTo(Date value) {
            addCriterion("summit_time <>", value, "summitTime");
            return (Criteria) this;
        }

        public Criteria andSummitTimeGreaterThan(Date value) {
            addCriterion("summit_time >", value, "summitTime");
            return (Criteria) this;
        }

        public Criteria andSummitTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("summit_time >=", value, "summitTime");
            return (Criteria) this;
        }

        public Criteria andSummitTimeLessThan(Date value) {
            addCriterion("summit_time <", value, "summitTime");
            return (Criteria) this;
        }

        public Criteria andSummitTimeLessThanOrEqualTo(Date value) {
            addCriterion("summit_time <=", value, "summitTime");
            return (Criteria) this;
        }

        public Criteria andSummitTimeIn(List<Date> values) {
            addCriterion("summit_time in", values, "summitTime");
            return (Criteria) this;
        }

        public Criteria andSummitTimeNotIn(List<Date> values) {
            addCriterion("summit_time not in", values, "summitTime");
            return (Criteria) this;
        }

        public Criteria andSummitTimeBetween(Date value1, Date value2) {
            addCriterion("summit_time between", value1, value2, "summitTime");
            return (Criteria) this;
        }

        public Criteria andSummitTimeNotBetween(Date value1, Date value2) {
            addCriterion("summit_time not between", value1, value2, "summitTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIsNull() {
            addCriterion("audit_time is null");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIsNotNull() {
            addCriterion("audit_time is not null");
            return (Criteria) this;
        }

        public Criteria andAuditTimeEqualTo(Date value) {
            addCriterion("audit_time =", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotEqualTo(Date value) {
            addCriterion("audit_time <>", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeGreaterThan(Date value) {
            addCriterion("audit_time >", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("audit_time >=", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLessThan(Date value) {
            addCriterion("audit_time <", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLessThanOrEqualTo(Date value) {
            addCriterion("audit_time <=", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIn(List<Date> values) {
            addCriterion("audit_time in", values, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotIn(List<Date> values) {
            addCriterion("audit_time not in", values, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeBetween(Date value1, Date value2) {
            addCriterion("audit_time between", value1, value2, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotBetween(Date value1, Date value2) {
            addCriterion("audit_time not between", value1, value2, "auditTime");
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