package cn.jwis.qualityworkflow.modules.rework.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReworkInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ReworkInfoExample() {
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

        public Criteria andDataSourceIsNull() {
            addCriterion("data_source is null");
            return (Criteria) this;
        }

        public Criteria andDataSourceIsNotNull() {
            addCriterion("data_source is not null");
            return (Criteria) this;
        }

        public Criteria andDataSourceEqualTo(String value) {
            addCriterion("data_source =", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceNotEqualTo(String value) {
            addCriterion("data_source <>", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceGreaterThan(String value) {
            addCriterion("data_source >", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceGreaterThanOrEqualTo(String value) {
            addCriterion("data_source >=", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceLessThan(String value) {
            addCriterion("data_source <", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceLessThanOrEqualTo(String value) {
            addCriterion("data_source <=", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceLike(String value) {
            addCriterion("data_source like", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceNotLike(String value) {
            addCriterion("data_source not like", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceIn(List<String> values) {
            addCriterion("data_source in", values, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceNotIn(List<String> values) {
            addCriterion("data_source not in", values, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceBetween(String value1, String value2) {
            addCriterion("data_source between", value1, value2, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceNotBetween(String value1, String value2) {
            addCriterion("data_source not between", value1, value2, "dataSource");
            return (Criteria) this;
        }

        public Criteria andReworkNumberIsNull() {
            addCriterion("rework_number is null");
            return (Criteria) this;
        }

        public Criteria andReworkNumberIsNotNull() {
            addCriterion("rework_number is not null");
            return (Criteria) this;
        }

        public Criteria andReworkNumberEqualTo(String value) {
            addCriterion("rework_number =", value, "reworkNumber");
            return (Criteria) this;
        }

        public Criteria andReworkNumberNotEqualTo(String value) {
            addCriterion("rework_number <>", value, "reworkNumber");
            return (Criteria) this;
        }

        public Criteria andReworkNumberGreaterThan(String value) {
            addCriterion("rework_number >", value, "reworkNumber");
            return (Criteria) this;
        }

        public Criteria andReworkNumberGreaterThanOrEqualTo(String value) {
            addCriterion("rework_number >=", value, "reworkNumber");
            return (Criteria) this;
        }

        public Criteria andReworkNumberLessThan(String value) {
            addCriterion("rework_number <", value, "reworkNumber");
            return (Criteria) this;
        }

        public Criteria andReworkNumberLessThanOrEqualTo(String value) {
            addCriterion("rework_number <=", value, "reworkNumber");
            return (Criteria) this;
        }

        public Criteria andReworkNumberLike(String value) {
            addCriterion("rework_number like", value, "reworkNumber");
            return (Criteria) this;
        }

        public Criteria andReworkNumberNotLike(String value) {
            addCriterion("rework_number not like", value, "reworkNumber");
            return (Criteria) this;
        }

        public Criteria andReworkNumberIn(List<String> values) {
            addCriterion("rework_number in", values, "reworkNumber");
            return (Criteria) this;
        }

        public Criteria andReworkNumberNotIn(List<String> values) {
            addCriterion("rework_number not in", values, "reworkNumber");
            return (Criteria) this;
        }

        public Criteria andReworkNumberBetween(String value1, String value2) {
            addCriterion("rework_number between", value1, value2, "reworkNumber");
            return (Criteria) this;
        }

        public Criteria andReworkNumberNotBetween(String value1, String value2) {
            addCriterion("rework_number not between", value1, value2, "reworkNumber");
            return (Criteria) this;
        }

        public Criteria andReworkThemeIsNull() {
            addCriterion("rework_theme is null");
            return (Criteria) this;
        }

        public Criteria andReworkThemeIsNotNull() {
            addCriterion("rework_theme is not null");
            return (Criteria) this;
        }

        public Criteria andReworkThemeEqualTo(String value) {
            addCriterion("rework_theme =", value, "reworkTheme");
            return (Criteria) this;
        }

        public Criteria andReworkThemeNotEqualTo(String value) {
            addCriterion("rework_theme <>", value, "reworkTheme");
            return (Criteria) this;
        }

        public Criteria andReworkThemeGreaterThan(String value) {
            addCriterion("rework_theme >", value, "reworkTheme");
            return (Criteria) this;
        }

        public Criteria andReworkThemeGreaterThanOrEqualTo(String value) {
            addCriterion("rework_theme >=", value, "reworkTheme");
            return (Criteria) this;
        }

        public Criteria andReworkThemeLessThan(String value) {
            addCriterion("rework_theme <", value, "reworkTheme");
            return (Criteria) this;
        }

        public Criteria andReworkThemeLessThanOrEqualTo(String value) {
            addCriterion("rework_theme <=", value, "reworkTheme");
            return (Criteria) this;
        }

        public Criteria andReworkThemeLike(String value) {
            addCriterion("rework_theme like", value, "reworkTheme");
            return (Criteria) this;
        }

        public Criteria andReworkThemeNotLike(String value) {
            addCriterion("rework_theme not like", value, "reworkTheme");
            return (Criteria) this;
        }

        public Criteria andReworkThemeIn(List<String> values) {
            addCriterion("rework_theme in", values, "reworkTheme");
            return (Criteria) this;
        }

        public Criteria andReworkThemeNotIn(List<String> values) {
            addCriterion("rework_theme not in", values, "reworkTheme");
            return (Criteria) this;
        }

        public Criteria andReworkThemeBetween(String value1, String value2) {
            addCriterion("rework_theme between", value1, value2, "reworkTheme");
            return (Criteria) this;
        }

        public Criteria andReworkThemeNotBetween(String value1, String value2) {
            addCriterion("rework_theme not between", value1, value2, "reworkTheme");
            return (Criteria) this;
        }

        public Criteria andReworkReasonIsNull() {
            addCriterion("rework_reason is null");
            return (Criteria) this;
        }

        public Criteria andReworkReasonIsNotNull() {
            addCriterion("rework_reason is not null");
            return (Criteria) this;
        }

        public Criteria andReworkReasonEqualTo(String value) {
            addCriterion("rework_reason =", value, "reworkReason");
            return (Criteria) this;
        }

        public Criteria andReworkReasonNotEqualTo(String value) {
            addCriterion("rework_reason <>", value, "reworkReason");
            return (Criteria) this;
        }

        public Criteria andReworkReasonGreaterThan(String value) {
            addCriterion("rework_reason >", value, "reworkReason");
            return (Criteria) this;
        }

        public Criteria andReworkReasonGreaterThanOrEqualTo(String value) {
            addCriterion("rework_reason >=", value, "reworkReason");
            return (Criteria) this;
        }

        public Criteria andReworkReasonLessThan(String value) {
            addCriterion("rework_reason <", value, "reworkReason");
            return (Criteria) this;
        }

        public Criteria andReworkReasonLessThanOrEqualTo(String value) {
            addCriterion("rework_reason <=", value, "reworkReason");
            return (Criteria) this;
        }

        public Criteria andReworkReasonLike(String value) {
            addCriterion("rework_reason like", value, "reworkReason");
            return (Criteria) this;
        }

        public Criteria andReworkReasonNotLike(String value) {
            addCriterion("rework_reason not like", value, "reworkReason");
            return (Criteria) this;
        }

        public Criteria andReworkReasonIn(List<String> values) {
            addCriterion("rework_reason in", values, "reworkReason");
            return (Criteria) this;
        }

        public Criteria andReworkReasonNotIn(List<String> values) {
            addCriterion("rework_reason not in", values, "reworkReason");
            return (Criteria) this;
        }

        public Criteria andReworkReasonBetween(String value1, String value2) {
            addCriterion("rework_reason between", value1, value2, "reworkReason");
            return (Criteria) this;
        }

        public Criteria andReworkReasonNotBetween(String value1, String value2) {
            addCriterion("rework_reason not between", value1, value2, "reworkReason");
            return (Criteria) this;
        }

        public Criteria andReworkQuantityIsNull() {
            addCriterion("rework_quantity is null");
            return (Criteria) this;
        }

        public Criteria andReworkQuantityIsNotNull() {
            addCriterion("rework_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andReworkQuantityEqualTo(String value) {
            addCriterion("rework_quantity =", value, "reworkQuantity");
            return (Criteria) this;
        }

        public Criteria andReworkQuantityNotEqualTo(String value) {
            addCriterion("rework_quantity <>", value, "reworkQuantity");
            return (Criteria) this;
        }

        public Criteria andReworkQuantityGreaterThan(String value) {
            addCriterion("rework_quantity >", value, "reworkQuantity");
            return (Criteria) this;
        }

        public Criteria andReworkQuantityGreaterThanOrEqualTo(String value) {
            addCriterion("rework_quantity >=", value, "reworkQuantity");
            return (Criteria) this;
        }

        public Criteria andReworkQuantityLessThan(String value) {
            addCriterion("rework_quantity <", value, "reworkQuantity");
            return (Criteria) this;
        }

        public Criteria andReworkQuantityLessThanOrEqualTo(String value) {
            addCriterion("rework_quantity <=", value, "reworkQuantity");
            return (Criteria) this;
        }

        public Criteria andReworkQuantityLike(String value) {
            addCriterion("rework_quantity like", value, "reworkQuantity");
            return (Criteria) this;
        }

        public Criteria andReworkQuantityNotLike(String value) {
            addCriterion("rework_quantity not like", value, "reworkQuantity");
            return (Criteria) this;
        }

        public Criteria andReworkQuantityIn(List<String> values) {
            addCriterion("rework_quantity in", values, "reworkQuantity");
            return (Criteria) this;
        }

        public Criteria andReworkQuantityNotIn(List<String> values) {
            addCriterion("rework_quantity not in", values, "reworkQuantity");
            return (Criteria) this;
        }

        public Criteria andReworkQuantityBetween(String value1, String value2) {
            addCriterion("rework_quantity between", value1, value2, "reworkQuantity");
            return (Criteria) this;
        }

        public Criteria andReworkQuantityNotBetween(String value1, String value2) {
            addCriterion("rework_quantity not between", value1, value2, "reworkQuantity");
            return (Criteria) this;
        }

        public Criteria andReworkMaterialNumberIsNull() {
            addCriterion("rework_material_number is null");
            return (Criteria) this;
        }

        public Criteria andReworkMaterialNumberIsNotNull() {
            addCriterion("rework_material_number is not null");
            return (Criteria) this;
        }

        public Criteria andReworkMaterialNumberEqualTo(String value) {
            addCriterion("rework_material_number =", value, "reworkMaterialNumber");
            return (Criteria) this;
        }

        public Criteria andReworkMaterialNumberNotEqualTo(String value) {
            addCriterion("rework_material_number <>", value, "reworkMaterialNumber");
            return (Criteria) this;
        }

        public Criteria andReworkMaterialNumberGreaterThan(String value) {
            addCriterion("rework_material_number >", value, "reworkMaterialNumber");
            return (Criteria) this;
        }

        public Criteria andReworkMaterialNumberGreaterThanOrEqualTo(String value) {
            addCriterion("rework_material_number >=", value, "reworkMaterialNumber");
            return (Criteria) this;
        }

        public Criteria andReworkMaterialNumberLessThan(String value) {
            addCriterion("rework_material_number <", value, "reworkMaterialNumber");
            return (Criteria) this;
        }

        public Criteria andReworkMaterialNumberLessThanOrEqualTo(String value) {
            addCriterion("rework_material_number <=", value, "reworkMaterialNumber");
            return (Criteria) this;
        }

        public Criteria andReworkMaterialNumberLike(String value) {
            addCriterion("rework_material_number like", value, "reworkMaterialNumber");
            return (Criteria) this;
        }

        public Criteria andReworkMaterialNumberNotLike(String value) {
            addCriterion("rework_material_number not like", value, "reworkMaterialNumber");
            return (Criteria) this;
        }

        public Criteria andReworkMaterialNumberIn(List<String> values) {
            addCriterion("rework_material_number in", values, "reworkMaterialNumber");
            return (Criteria) this;
        }

        public Criteria andReworkMaterialNumberNotIn(List<String> values) {
            addCriterion("rework_material_number not in", values, "reworkMaterialNumber");
            return (Criteria) this;
        }

        public Criteria andReworkMaterialNumberBetween(String value1, String value2) {
            addCriterion("rework_material_number between", value1, value2, "reworkMaterialNumber");
            return (Criteria) this;
        }

        public Criteria andReworkMaterialNumberNotBetween(String value1, String value2) {
            addCriterion("rework_material_number not between", value1, value2, "reworkMaterialNumber");
            return (Criteria) this;
        }

        public Criteria andReworkConfirmResultIsNull() {
            addCriterion("rework_confirm_result is null");
            return (Criteria) this;
        }

        public Criteria andReworkConfirmResultIsNotNull() {
            addCriterion("rework_confirm_result is not null");
            return (Criteria) this;
        }

        public Criteria andReworkConfirmResultEqualTo(String value) {
            addCriterion("rework_confirm_result =", value, "reworkConfirmResult");
            return (Criteria) this;
        }

        public Criteria andReworkConfirmResultNotEqualTo(String value) {
            addCriterion("rework_confirm_result <>", value, "reworkConfirmResult");
            return (Criteria) this;
        }

        public Criteria andReworkConfirmResultGreaterThan(String value) {
            addCriterion("rework_confirm_result >", value, "reworkConfirmResult");
            return (Criteria) this;
        }

        public Criteria andReworkConfirmResultGreaterThanOrEqualTo(String value) {
            addCriterion("rework_confirm_result >=", value, "reworkConfirmResult");
            return (Criteria) this;
        }

        public Criteria andReworkConfirmResultLessThan(String value) {
            addCriterion("rework_confirm_result <", value, "reworkConfirmResult");
            return (Criteria) this;
        }

        public Criteria andReworkConfirmResultLessThanOrEqualTo(String value) {
            addCriterion("rework_confirm_result <=", value, "reworkConfirmResult");
            return (Criteria) this;
        }

        public Criteria andReworkConfirmResultLike(String value) {
            addCriterion("rework_confirm_result like", value, "reworkConfirmResult");
            return (Criteria) this;
        }

        public Criteria andReworkConfirmResultNotLike(String value) {
            addCriterion("rework_confirm_result not like", value, "reworkConfirmResult");
            return (Criteria) this;
        }

        public Criteria andReworkConfirmResultIn(List<String> values) {
            addCriterion("rework_confirm_result in", values, "reworkConfirmResult");
            return (Criteria) this;
        }

        public Criteria andReworkConfirmResultNotIn(List<String> values) {
            addCriterion("rework_confirm_result not in", values, "reworkConfirmResult");
            return (Criteria) this;
        }

        public Criteria andReworkConfirmResultBetween(String value1, String value2) {
            addCriterion("rework_confirm_result between", value1, value2, "reworkConfirmResult");
            return (Criteria) this;
        }

        public Criteria andReworkConfirmResultNotBetween(String value1, String value2) {
            addCriterion("rework_confirm_result not between", value1, value2, "reworkConfirmResult");
            return (Criteria) this;
        }

        public Criteria andReworkConfirmorIsNull() {
            addCriterion("rework_confirmor is null");
            return (Criteria) this;
        }

        public Criteria andReworkConfirmorIsNotNull() {
            addCriterion("rework_confirmor is not null");
            return (Criteria) this;
        }

        public Criteria andReworkConfirmorEqualTo(String value) {
            addCriterion("rework_confirmor =", value, "reworkConfirmor");
            return (Criteria) this;
        }

        public Criteria andReworkConfirmorNotEqualTo(String value) {
            addCriterion("rework_confirmor <>", value, "reworkConfirmor");
            return (Criteria) this;
        }

        public Criteria andReworkConfirmorGreaterThan(String value) {
            addCriterion("rework_confirmor >", value, "reworkConfirmor");
            return (Criteria) this;
        }

        public Criteria andReworkConfirmorGreaterThanOrEqualTo(String value) {
            addCriterion("rework_confirmor >=", value, "reworkConfirmor");
            return (Criteria) this;
        }

        public Criteria andReworkConfirmorLessThan(String value) {
            addCriterion("rework_confirmor <", value, "reworkConfirmor");
            return (Criteria) this;
        }

        public Criteria andReworkConfirmorLessThanOrEqualTo(String value) {
            addCriterion("rework_confirmor <=", value, "reworkConfirmor");
            return (Criteria) this;
        }

        public Criteria andReworkConfirmorLike(String value) {
            addCriterion("rework_confirmor like", value, "reworkConfirmor");
            return (Criteria) this;
        }

        public Criteria andReworkConfirmorNotLike(String value) {
            addCriterion("rework_confirmor not like", value, "reworkConfirmor");
            return (Criteria) this;
        }

        public Criteria andReworkConfirmorIn(List<String> values) {
            addCriterion("rework_confirmor in", values, "reworkConfirmor");
            return (Criteria) this;
        }

        public Criteria andReworkConfirmorNotIn(List<String> values) {
            addCriterion("rework_confirmor not in", values, "reworkConfirmor");
            return (Criteria) this;
        }

        public Criteria andReworkConfirmorBetween(String value1, String value2) {
            addCriterion("rework_confirmor between", value1, value2, "reworkConfirmor");
            return (Criteria) this;
        }

        public Criteria andReworkConfirmorNotBetween(String value1, String value2) {
            addCriterion("rework_confirmor not between", value1, value2, "reworkConfirmor");
            return (Criteria) this;
        }

        public Criteria andReworkConfirmTimeIsNull() {
            addCriterion("rework_confirm_time is null");
            return (Criteria) this;
        }

        public Criteria andReworkConfirmTimeIsNotNull() {
            addCriterion("rework_confirm_time is not null");
            return (Criteria) this;
        }

        public Criteria andReworkConfirmTimeEqualTo(Date value) {
            addCriterion("rework_confirm_time =", value, "reworkConfirmTime");
            return (Criteria) this;
        }

        public Criteria andReworkConfirmTimeNotEqualTo(Date value) {
            addCriterion("rework_confirm_time <>", value, "reworkConfirmTime");
            return (Criteria) this;
        }

        public Criteria andReworkConfirmTimeGreaterThan(Date value) {
            addCriterion("rework_confirm_time >", value, "reworkConfirmTime");
            return (Criteria) this;
        }

        public Criteria andReworkConfirmTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("rework_confirm_time >=", value, "reworkConfirmTime");
            return (Criteria) this;
        }

        public Criteria andReworkConfirmTimeLessThan(Date value) {
            addCriterion("rework_confirm_time <", value, "reworkConfirmTime");
            return (Criteria) this;
        }

        public Criteria andReworkConfirmTimeLessThanOrEqualTo(Date value) {
            addCriterion("rework_confirm_time <=", value, "reworkConfirmTime");
            return (Criteria) this;
        }

        public Criteria andReworkConfirmTimeIn(List<Date> values) {
            addCriterion("rework_confirm_time in", values, "reworkConfirmTime");
            return (Criteria) this;
        }

        public Criteria andReworkConfirmTimeNotIn(List<Date> values) {
            addCriterion("rework_confirm_time not in", values, "reworkConfirmTime");
            return (Criteria) this;
        }

        public Criteria andReworkConfirmTimeBetween(Date value1, Date value2) {
            addCriterion("rework_confirm_time between", value1, value2, "reworkConfirmTime");
            return (Criteria) this;
        }

        public Criteria andReworkConfirmTimeNotBetween(Date value1, Date value2) {
            addCriterion("rework_confirm_time not between", value1, value2, "reworkConfirmTime");
            return (Criteria) this;
        }

        public Criteria andCraftsSectionIsNull() {
            addCriterion("crafts_section is null");
            return (Criteria) this;
        }

        public Criteria andCraftsSectionIsNotNull() {
            addCriterion("crafts_section is not null");
            return (Criteria) this;
        }

        public Criteria andCraftsSectionEqualTo(String value) {
            addCriterion("crafts_section =", value, "craftsSection");
            return (Criteria) this;
        }

        public Criteria andCraftsSectionNotEqualTo(String value) {
            addCriterion("crafts_section <>", value, "craftsSection");
            return (Criteria) this;
        }

        public Criteria andCraftsSectionGreaterThan(String value) {
            addCriterion("crafts_section >", value, "craftsSection");
            return (Criteria) this;
        }

        public Criteria andCraftsSectionGreaterThanOrEqualTo(String value) {
            addCriterion("crafts_section >=", value, "craftsSection");
            return (Criteria) this;
        }

        public Criteria andCraftsSectionLessThan(String value) {
            addCriterion("crafts_section <", value, "craftsSection");
            return (Criteria) this;
        }

        public Criteria andCraftsSectionLessThanOrEqualTo(String value) {
            addCriterion("crafts_section <=", value, "craftsSection");
            return (Criteria) this;
        }

        public Criteria andCraftsSectionLike(String value) {
            addCriterion("crafts_section like", value, "craftsSection");
            return (Criteria) this;
        }

        public Criteria andCraftsSectionNotLike(String value) {
            addCriterion("crafts_section not like", value, "craftsSection");
            return (Criteria) this;
        }

        public Criteria andCraftsSectionIn(List<String> values) {
            addCriterion("crafts_section in", values, "craftsSection");
            return (Criteria) this;
        }

        public Criteria andCraftsSectionNotIn(List<String> values) {
            addCriterion("crafts_section not in", values, "craftsSection");
            return (Criteria) this;
        }

        public Criteria andCraftsSectionBetween(String value1, String value2) {
            addCriterion("crafts_section between", value1, value2, "craftsSection");
            return (Criteria) this;
        }

        public Criteria andCraftsSectionNotBetween(String value1, String value2) {
            addCriterion("crafts_section not between", value1, value2, "craftsSection");
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

        public Criteria andProblemTypeIsNull() {
            addCriterion("problem_type is null");
            return (Criteria) this;
        }

        public Criteria andProblemTypeIsNotNull() {
            addCriterion("problem_type is not null");
            return (Criteria) this;
        }

        public Criteria andProblemTypeEqualTo(String value) {
            addCriterion("problem_type =", value, "problemType");
            return (Criteria) this;
        }

        public Criteria andProblemTypeNotEqualTo(String value) {
            addCriterion("problem_type <>", value, "problemType");
            return (Criteria) this;
        }

        public Criteria andProblemTypeGreaterThan(String value) {
            addCriterion("problem_type >", value, "problemType");
            return (Criteria) this;
        }

        public Criteria andProblemTypeGreaterThanOrEqualTo(String value) {
            addCriterion("problem_type >=", value, "problemType");
            return (Criteria) this;
        }

        public Criteria andProblemTypeLessThan(String value) {
            addCriterion("problem_type <", value, "problemType");
            return (Criteria) this;
        }

        public Criteria andProblemTypeLessThanOrEqualTo(String value) {
            addCriterion("problem_type <=", value, "problemType");
            return (Criteria) this;
        }

        public Criteria andProblemTypeLike(String value) {
            addCriterion("problem_type like", value, "problemType");
            return (Criteria) this;
        }

        public Criteria andProblemTypeNotLike(String value) {
            addCriterion("problem_type not like", value, "problemType");
            return (Criteria) this;
        }

        public Criteria andProblemTypeIn(List<String> values) {
            addCriterion("problem_type in", values, "problemType");
            return (Criteria) this;
        }

        public Criteria andProblemTypeNotIn(List<String> values) {
            addCriterion("problem_type not in", values, "problemType");
            return (Criteria) this;
        }

        public Criteria andProblemTypeBetween(String value1, String value2) {
            addCriterion("problem_type between", value1, value2, "problemType");
            return (Criteria) this;
        }

        public Criteria andProblemTypeNotBetween(String value1, String value2) {
            addCriterion("problem_type not between", value1, value2, "problemType");
            return (Criteria) this;
        }

        public Criteria andProblemTimeIsNull() {
            addCriterion("problem_time is null");
            return (Criteria) this;
        }

        public Criteria andProblemTimeIsNotNull() {
            addCriterion("problem_time is not null");
            return (Criteria) this;
        }

        public Criteria andProblemTimeEqualTo(Date value) {
            addCriterion("problem_time =", value, "problemTime");
            return (Criteria) this;
        }

        public Criteria andProblemTimeNotEqualTo(Date value) {
            addCriterion("problem_time <>", value, "problemTime");
            return (Criteria) this;
        }

        public Criteria andProblemTimeGreaterThan(Date value) {
            addCriterion("problem_time >", value, "problemTime");
            return (Criteria) this;
        }

        public Criteria andProblemTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("problem_time >=", value, "problemTime");
            return (Criteria) this;
        }

        public Criteria andProblemTimeLessThan(Date value) {
            addCriterion("problem_time <", value, "problemTime");
            return (Criteria) this;
        }

        public Criteria andProblemTimeLessThanOrEqualTo(Date value) {
            addCriterion("problem_time <=", value, "problemTime");
            return (Criteria) this;
        }

        public Criteria andProblemTimeIn(List<Date> values) {
            addCriterion("problem_time in", values, "problemTime");
            return (Criteria) this;
        }

        public Criteria andProblemTimeNotIn(List<Date> values) {
            addCriterion("problem_time not in", values, "problemTime");
            return (Criteria) this;
        }

        public Criteria andProblemTimeBetween(Date value1, Date value2) {
            addCriterion("problem_time between", value1, value2, "problemTime");
            return (Criteria) this;
        }

        public Criteria andProblemTimeNotBetween(Date value1, Date value2) {
            addCriterion("problem_time not between", value1, value2, "problemTime");
            return (Criteria) this;
        }

        public Criteria andRootCauseResponsibilityIsNull() {
            addCriterion("root_cause_responsibility is null");
            return (Criteria) this;
        }

        public Criteria andRootCauseResponsibilityIsNotNull() {
            addCriterion("root_cause_responsibility is not null");
            return (Criteria) this;
        }

        public Criteria andRootCauseResponsibilityEqualTo(String value) {
            addCriterion("root_cause_responsibility =", value, "rootCauseResponsibility");
            return (Criteria) this;
        }

        public Criteria andRootCauseResponsibilityNotEqualTo(String value) {
            addCriterion("root_cause_responsibility <>", value, "rootCauseResponsibility");
            return (Criteria) this;
        }

        public Criteria andRootCauseResponsibilityGreaterThan(String value) {
            addCriterion("root_cause_responsibility >", value, "rootCauseResponsibility");
            return (Criteria) this;
        }

        public Criteria andRootCauseResponsibilityGreaterThanOrEqualTo(String value) {
            addCriterion("root_cause_responsibility >=", value, "rootCauseResponsibility");
            return (Criteria) this;
        }

        public Criteria andRootCauseResponsibilityLessThan(String value) {
            addCriterion("root_cause_responsibility <", value, "rootCauseResponsibility");
            return (Criteria) this;
        }

        public Criteria andRootCauseResponsibilityLessThanOrEqualTo(String value) {
            addCriterion("root_cause_responsibility <=", value, "rootCauseResponsibility");
            return (Criteria) this;
        }

        public Criteria andRootCauseResponsibilityLike(String value) {
            addCriterion("root_cause_responsibility like", value, "rootCauseResponsibility");
            return (Criteria) this;
        }

        public Criteria andRootCauseResponsibilityNotLike(String value) {
            addCriterion("root_cause_responsibility not like", value, "rootCauseResponsibility");
            return (Criteria) this;
        }

        public Criteria andRootCauseResponsibilityIn(List<String> values) {
            addCriterion("root_cause_responsibility in", values, "rootCauseResponsibility");
            return (Criteria) this;
        }

        public Criteria andRootCauseResponsibilityNotIn(List<String> values) {
            addCriterion("root_cause_responsibility not in", values, "rootCauseResponsibility");
            return (Criteria) this;
        }

        public Criteria andRootCauseResponsibilityBetween(String value1, String value2) {
            addCriterion("root_cause_responsibility between", value1, value2, "rootCauseResponsibility");
            return (Criteria) this;
        }

        public Criteria andRootCauseResponsibilityNotBetween(String value1, String value2) {
            addCriterion("root_cause_responsibility not between", value1, value2, "rootCauseResponsibility");
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

        public Criteria andAuditorIsNull() {
            addCriterion("auditor is null");
            return (Criteria) this;
        }

        public Criteria andAuditorIsNotNull() {
            addCriterion("auditor is not null");
            return (Criteria) this;
        }

        public Criteria andAuditorEqualTo(String value) {
            addCriterion("auditor =", value, "auditor");
            return (Criteria) this;
        }

        public Criteria andAuditorNotEqualTo(String value) {
            addCriterion("auditor <>", value, "auditor");
            return (Criteria) this;
        }

        public Criteria andAuditorGreaterThan(String value) {
            addCriterion("auditor >", value, "auditor");
            return (Criteria) this;
        }

        public Criteria andAuditorGreaterThanOrEqualTo(String value) {
            addCriterion("auditor >=", value, "auditor");
            return (Criteria) this;
        }

        public Criteria andAuditorLessThan(String value) {
            addCriterion("auditor <", value, "auditor");
            return (Criteria) this;
        }

        public Criteria andAuditorLessThanOrEqualTo(String value) {
            addCriterion("auditor <=", value, "auditor");
            return (Criteria) this;
        }

        public Criteria andAuditorLike(String value) {
            addCriterion("auditor like", value, "auditor");
            return (Criteria) this;
        }

        public Criteria andAuditorNotLike(String value) {
            addCriterion("auditor not like", value, "auditor");
            return (Criteria) this;
        }

        public Criteria andAuditorIn(List<String> values) {
            addCriterion("auditor in", values, "auditor");
            return (Criteria) this;
        }

        public Criteria andAuditorNotIn(List<String> values) {
            addCriterion("auditor not in", values, "auditor");
            return (Criteria) this;
        }

        public Criteria andAuditorBetween(String value1, String value2) {
            addCriterion("auditor between", value1, value2, "auditor");
            return (Criteria) this;
        }

        public Criteria andAuditorNotBetween(String value1, String value2) {
            addCriterion("auditor not between", value1, value2, "auditor");
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

        public Criteria andProjectHandlerIsNull() {
            addCriterion("project_handler is null");
            return (Criteria) this;
        }

        public Criteria andProjectHandlerIsNotNull() {
            addCriterion("project_handler is not null");
            return (Criteria) this;
        }

        public Criteria andProjectHandlerEqualTo(String value) {
            addCriterion("project_handler =", value, "projectHandler");
            return (Criteria) this;
        }

        public Criteria andProjectHandlerNotEqualTo(String value) {
            addCriterion("project_handler <>", value, "projectHandler");
            return (Criteria) this;
        }

        public Criteria andProjectHandlerGreaterThan(String value) {
            addCriterion("project_handler >", value, "projectHandler");
            return (Criteria) this;
        }

        public Criteria andProjectHandlerGreaterThanOrEqualTo(String value) {
            addCriterion("project_handler >=", value, "projectHandler");
            return (Criteria) this;
        }

        public Criteria andProjectHandlerLessThan(String value) {
            addCriterion("project_handler <", value, "projectHandler");
            return (Criteria) this;
        }

        public Criteria andProjectHandlerLessThanOrEqualTo(String value) {
            addCriterion("project_handler <=", value, "projectHandler");
            return (Criteria) this;
        }

        public Criteria andProjectHandlerLike(String value) {
            addCriterion("project_handler like", value, "projectHandler");
            return (Criteria) this;
        }

        public Criteria andProjectHandlerNotLike(String value) {
            addCriterion("project_handler not like", value, "projectHandler");
            return (Criteria) this;
        }

        public Criteria andProjectHandlerIn(List<String> values) {
            addCriterion("project_handler in", values, "projectHandler");
            return (Criteria) this;
        }

        public Criteria andProjectHandlerNotIn(List<String> values) {
            addCriterion("project_handler not in", values, "projectHandler");
            return (Criteria) this;
        }

        public Criteria andProjectHandlerBetween(String value1, String value2) {
            addCriterion("project_handler between", value1, value2, "projectHandler");
            return (Criteria) this;
        }

        public Criteria andProjectHandlerNotBetween(String value1, String value2) {
            addCriterion("project_handler not between", value1, value2, "projectHandler");
            return (Criteria) this;
        }

        public Criteria andProjectHandleTimeIsNull() {
            addCriterion("project_handle_time is null");
            return (Criteria) this;
        }

        public Criteria andProjectHandleTimeIsNotNull() {
            addCriterion("project_handle_time is not null");
            return (Criteria) this;
        }

        public Criteria andProjectHandleTimeEqualTo(Date value) {
            addCriterion("project_handle_time =", value, "projectHandleTime");
            return (Criteria) this;
        }

        public Criteria andProjectHandleTimeNotEqualTo(Date value) {
            addCriterion("project_handle_time <>", value, "projectHandleTime");
            return (Criteria) this;
        }

        public Criteria andProjectHandleTimeGreaterThan(Date value) {
            addCriterion("project_handle_time >", value, "projectHandleTime");
            return (Criteria) this;
        }

        public Criteria andProjectHandleTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("project_handle_time >=", value, "projectHandleTime");
            return (Criteria) this;
        }

        public Criteria andProjectHandleTimeLessThan(Date value) {
            addCriterion("project_handle_time <", value, "projectHandleTime");
            return (Criteria) this;
        }

        public Criteria andProjectHandleTimeLessThanOrEqualTo(Date value) {
            addCriterion("project_handle_time <=", value, "projectHandleTime");
            return (Criteria) this;
        }

        public Criteria andProjectHandleTimeIn(List<Date> values) {
            addCriterion("project_handle_time in", values, "projectHandleTime");
            return (Criteria) this;
        }

        public Criteria andProjectHandleTimeNotIn(List<Date> values) {
            addCriterion("project_handle_time not in", values, "projectHandleTime");
            return (Criteria) this;
        }

        public Criteria andProjectHandleTimeBetween(Date value1, Date value2) {
            addCriterion("project_handle_time between", value1, value2, "projectHandleTime");
            return (Criteria) this;
        }

        public Criteria andProjectHandleTimeNotBetween(Date value1, Date value2) {
            addCriterion("project_handle_time not between", value1, value2, "projectHandleTime");
            return (Criteria) this;
        }

        public Criteria andPlanHandlerIsNull() {
            addCriterion("plan_handler is null");
            return (Criteria) this;
        }

        public Criteria andPlanHandlerIsNotNull() {
            addCriterion("plan_handler is not null");
            return (Criteria) this;
        }

        public Criteria andPlanHandlerEqualTo(String value) {
            addCriterion("plan_handler =", value, "planHandler");
            return (Criteria) this;
        }

        public Criteria andPlanHandlerNotEqualTo(String value) {
            addCriterion("plan_handler <>", value, "planHandler");
            return (Criteria) this;
        }

        public Criteria andPlanHandlerGreaterThan(String value) {
            addCriterion("plan_handler >", value, "planHandler");
            return (Criteria) this;
        }

        public Criteria andPlanHandlerGreaterThanOrEqualTo(String value) {
            addCriterion("plan_handler >=", value, "planHandler");
            return (Criteria) this;
        }

        public Criteria andPlanHandlerLessThan(String value) {
            addCriterion("plan_handler <", value, "planHandler");
            return (Criteria) this;
        }

        public Criteria andPlanHandlerLessThanOrEqualTo(String value) {
            addCriterion("plan_handler <=", value, "planHandler");
            return (Criteria) this;
        }

        public Criteria andPlanHandlerLike(String value) {
            addCriterion("plan_handler like", value, "planHandler");
            return (Criteria) this;
        }

        public Criteria andPlanHandlerNotLike(String value) {
            addCriterion("plan_handler not like", value, "planHandler");
            return (Criteria) this;
        }

        public Criteria andPlanHandlerIn(List<String> values) {
            addCriterion("plan_handler in", values, "planHandler");
            return (Criteria) this;
        }

        public Criteria andPlanHandlerNotIn(List<String> values) {
            addCriterion("plan_handler not in", values, "planHandler");
            return (Criteria) this;
        }

        public Criteria andPlanHandlerBetween(String value1, String value2) {
            addCriterion("plan_handler between", value1, value2, "planHandler");
            return (Criteria) this;
        }

        public Criteria andPlanHandlerNotBetween(String value1, String value2) {
            addCriterion("plan_handler not between", value1, value2, "planHandler");
            return (Criteria) this;
        }

        public Criteria andPlanHandleTimeIsNull() {
            addCriterion("plan_handle_time is null");
            return (Criteria) this;
        }

        public Criteria andPlanHandleTimeIsNotNull() {
            addCriterion("plan_handle_time is not null");
            return (Criteria) this;
        }

        public Criteria andPlanHandleTimeEqualTo(Date value) {
            addCriterion("plan_handle_time =", value, "planHandleTime");
            return (Criteria) this;
        }

        public Criteria andPlanHandleTimeNotEqualTo(Date value) {
            addCriterion("plan_handle_time <>", value, "planHandleTime");
            return (Criteria) this;
        }

        public Criteria andPlanHandleTimeGreaterThan(Date value) {
            addCriterion("plan_handle_time >", value, "planHandleTime");
            return (Criteria) this;
        }

        public Criteria andPlanHandleTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("plan_handle_time >=", value, "planHandleTime");
            return (Criteria) this;
        }

        public Criteria andPlanHandleTimeLessThan(Date value) {
            addCriterion("plan_handle_time <", value, "planHandleTime");
            return (Criteria) this;
        }

        public Criteria andPlanHandleTimeLessThanOrEqualTo(Date value) {
            addCriterion("plan_handle_time <=", value, "planHandleTime");
            return (Criteria) this;
        }

        public Criteria andPlanHandleTimeIn(List<Date> values) {
            addCriterion("plan_handle_time in", values, "planHandleTime");
            return (Criteria) this;
        }

        public Criteria andPlanHandleTimeNotIn(List<Date> values) {
            addCriterion("plan_handle_time not in", values, "planHandleTime");
            return (Criteria) this;
        }

        public Criteria andPlanHandleTimeBetween(Date value1, Date value2) {
            addCriterion("plan_handle_time between", value1, value2, "planHandleTime");
            return (Criteria) this;
        }

        public Criteria andPlanHandleTimeNotBetween(Date value1, Date value2) {
            addCriterion("plan_handle_time not between", value1, value2, "planHandleTime");
            return (Criteria) this;
        }

        public Criteria andProductionHandlerIsNull() {
            addCriterion("production_handler is null");
            return (Criteria) this;
        }

        public Criteria andProductionHandlerIsNotNull() {
            addCriterion("production_handler is not null");
            return (Criteria) this;
        }

        public Criteria andProductionHandlerEqualTo(String value) {
            addCriterion("production_handler =", value, "productionHandler");
            return (Criteria) this;
        }

        public Criteria andProductionHandlerNotEqualTo(String value) {
            addCriterion("production_handler <>", value, "productionHandler");
            return (Criteria) this;
        }

        public Criteria andProductionHandlerGreaterThan(String value) {
            addCriterion("production_handler >", value, "productionHandler");
            return (Criteria) this;
        }

        public Criteria andProductionHandlerGreaterThanOrEqualTo(String value) {
            addCriterion("production_handler >=", value, "productionHandler");
            return (Criteria) this;
        }

        public Criteria andProductionHandlerLessThan(String value) {
            addCriterion("production_handler <", value, "productionHandler");
            return (Criteria) this;
        }

        public Criteria andProductionHandlerLessThanOrEqualTo(String value) {
            addCriterion("production_handler <=", value, "productionHandler");
            return (Criteria) this;
        }

        public Criteria andProductionHandlerLike(String value) {
            addCriterion("production_handler like", value, "productionHandler");
            return (Criteria) this;
        }

        public Criteria andProductionHandlerNotLike(String value) {
            addCriterion("production_handler not like", value, "productionHandler");
            return (Criteria) this;
        }

        public Criteria andProductionHandlerIn(List<String> values) {
            addCriterion("production_handler in", values, "productionHandler");
            return (Criteria) this;
        }

        public Criteria andProductionHandlerNotIn(List<String> values) {
            addCriterion("production_handler not in", values, "productionHandler");
            return (Criteria) this;
        }

        public Criteria andProductionHandlerBetween(String value1, String value2) {
            addCriterion("production_handler between", value1, value2, "productionHandler");
            return (Criteria) this;
        }

        public Criteria andProductionHandlerNotBetween(String value1, String value2) {
            addCriterion("production_handler not between", value1, value2, "productionHandler");
            return (Criteria) this;
        }

        public Criteria andProductionExecutionTimeIsNull() {
            addCriterion("production_execution_time is null");
            return (Criteria) this;
        }

        public Criteria andProductionExecutionTimeIsNotNull() {
            addCriterion("production_execution_time is not null");
            return (Criteria) this;
        }

        public Criteria andProductionExecutionTimeEqualTo(Date value) {
            addCriterion("production_execution_time =", value, "productionExecutionTime");
            return (Criteria) this;
        }

        public Criteria andProductionExecutionTimeNotEqualTo(Date value) {
            addCriterion("production_execution_time <>", value, "productionExecutionTime");
            return (Criteria) this;
        }

        public Criteria andProductionExecutionTimeGreaterThan(Date value) {
            addCriterion("production_execution_time >", value, "productionExecutionTime");
            return (Criteria) this;
        }

        public Criteria andProductionExecutionTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("production_execution_time >=", value, "productionExecutionTime");
            return (Criteria) this;
        }

        public Criteria andProductionExecutionTimeLessThan(Date value) {
            addCriterion("production_execution_time <", value, "productionExecutionTime");
            return (Criteria) this;
        }

        public Criteria andProductionExecutionTimeLessThanOrEqualTo(Date value) {
            addCriterion("production_execution_time <=", value, "productionExecutionTime");
            return (Criteria) this;
        }

        public Criteria andProductionExecutionTimeIn(List<Date> values) {
            addCriterion("production_execution_time in", values, "productionExecutionTime");
            return (Criteria) this;
        }

        public Criteria andProductionExecutionTimeNotIn(List<Date> values) {
            addCriterion("production_execution_time not in", values, "productionExecutionTime");
            return (Criteria) this;
        }

        public Criteria andProductionExecutionTimeBetween(Date value1, Date value2) {
            addCriterion("production_execution_time between", value1, value2, "productionExecutionTime");
            return (Criteria) this;
        }

        public Criteria andProductionExecutionTimeNotBetween(Date value1, Date value2) {
            addCriterion("production_execution_time not between", value1, value2, "productionExecutionTime");
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