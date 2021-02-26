package cn.jwis.qualityworkflow.modules.apqp.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApqpModelMaintenanceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ApqpModelMaintenanceExample() {
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

        public Criteria andPreviousProductIsNull() {
            addCriterion("previous_product is null");
            return (Criteria) this;
        }

        public Criteria andPreviousProductIsNotNull() {
            addCriterion("previous_product is not null");
            return (Criteria) this;
        }

        public Criteria andPreviousProductEqualTo(String value) {
            addCriterion("previous_product =", value, "previousProduct");
            return (Criteria) this;
        }

        public Criteria andPreviousProductNotEqualTo(String value) {
            addCriterion("previous_product <>", value, "previousProduct");
            return (Criteria) this;
        }

        public Criteria andPreviousProductGreaterThan(String value) {
            addCriterion("previous_product >", value, "previousProduct");
            return (Criteria) this;
        }

        public Criteria andPreviousProductGreaterThanOrEqualTo(String value) {
            addCriterion("previous_product >=", value, "previousProduct");
            return (Criteria) this;
        }

        public Criteria andPreviousProductLessThan(String value) {
            addCriterion("previous_product <", value, "previousProduct");
            return (Criteria) this;
        }

        public Criteria andPreviousProductLessThanOrEqualTo(String value) {
            addCriterion("previous_product <=", value, "previousProduct");
            return (Criteria) this;
        }

        public Criteria andPreviousProductLike(String value) {
            addCriterion("previous_product like", value, "previousProduct");
            return (Criteria) this;
        }

        public Criteria andPreviousProductNotLike(String value) {
            addCriterion("previous_product not like", value, "previousProduct");
            return (Criteria) this;
        }

        public Criteria andPreviousProductIn(List<String> values) {
            addCriterion("previous_product in", values, "previousProduct");
            return (Criteria) this;
        }

        public Criteria andPreviousProductNotIn(List<String> values) {
            addCriterion("previous_product not in", values, "previousProduct");
            return (Criteria) this;
        }

        public Criteria andPreviousProductBetween(String value1, String value2) {
            addCriterion("previous_product between", value1, value2, "previousProduct");
            return (Criteria) this;
        }

        public Criteria andPreviousProductNotBetween(String value1, String value2) {
            addCriterion("previous_product not between", value1, value2, "previousProduct");
            return (Criteria) this;
        }

        public Criteria andProductSeriesIsNull() {
            addCriterion("product_series is null");
            return (Criteria) this;
        }

        public Criteria andProductSeriesIsNotNull() {
            addCriterion("product_series is not null");
            return (Criteria) this;
        }

        public Criteria andProductSeriesEqualTo(String value) {
            addCriterion("product_series =", value, "productSeries");
            return (Criteria) this;
        }

        public Criteria andProductSeriesNotEqualTo(String value) {
            addCriterion("product_series <>", value, "productSeries");
            return (Criteria) this;
        }

        public Criteria andProductSeriesGreaterThan(String value) {
            addCriterion("product_series >", value, "productSeries");
            return (Criteria) this;
        }

        public Criteria andProductSeriesGreaterThanOrEqualTo(String value) {
            addCriterion("product_series >=", value, "productSeries");
            return (Criteria) this;
        }

        public Criteria andProductSeriesLessThan(String value) {
            addCriterion("product_series <", value, "productSeries");
            return (Criteria) this;
        }

        public Criteria andProductSeriesLessThanOrEqualTo(String value) {
            addCriterion("product_series <=", value, "productSeries");
            return (Criteria) this;
        }

        public Criteria andProductSeriesLike(String value) {
            addCriterion("product_series like", value, "productSeries");
            return (Criteria) this;
        }

        public Criteria andProductSeriesNotLike(String value) {
            addCriterion("product_series not like", value, "productSeries");
            return (Criteria) this;
        }

        public Criteria andProductSeriesIn(List<String> values) {
            addCriterion("product_series in", values, "productSeries");
            return (Criteria) this;
        }

        public Criteria andProductSeriesNotIn(List<String> values) {
            addCriterion("product_series not in", values, "productSeries");
            return (Criteria) this;
        }

        public Criteria andProductSeriesBetween(String value1, String value2) {
            addCriterion("product_series between", value1, value2, "productSeries");
            return (Criteria) this;
        }

        public Criteria andProductSeriesNotBetween(String value1, String value2) {
            addCriterion("product_series not between", value1, value2, "productSeries");
            return (Criteria) this;
        }

        public Criteria andModelCategoryIsNull() {
            addCriterion("model_category is null");
            return (Criteria) this;
        }

        public Criteria andModelCategoryIsNotNull() {
            addCriterion("model_category is not null");
            return (Criteria) this;
        }

        public Criteria andModelCategoryEqualTo(String value) {
            addCriterion("model_category =", value, "modelCategory");
            return (Criteria) this;
        }

        public Criteria andModelCategoryNotEqualTo(String value) {
            addCriterion("model_category <>", value, "modelCategory");
            return (Criteria) this;
        }

        public Criteria andModelCategoryGreaterThan(String value) {
            addCriterion("model_category >", value, "modelCategory");
            return (Criteria) this;
        }

        public Criteria andModelCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("model_category >=", value, "modelCategory");
            return (Criteria) this;
        }

        public Criteria andModelCategoryLessThan(String value) {
            addCriterion("model_category <", value, "modelCategory");
            return (Criteria) this;
        }

        public Criteria andModelCategoryLessThanOrEqualTo(String value) {
            addCriterion("model_category <=", value, "modelCategory");
            return (Criteria) this;
        }

        public Criteria andModelCategoryLike(String value) {
            addCriterion("model_category like", value, "modelCategory");
            return (Criteria) this;
        }

        public Criteria andModelCategoryNotLike(String value) {
            addCriterion("model_category not like", value, "modelCategory");
            return (Criteria) this;
        }

        public Criteria andModelCategoryIn(List<String> values) {
            addCriterion("model_category in", values, "modelCategory");
            return (Criteria) this;
        }

        public Criteria andModelCategoryNotIn(List<String> values) {
            addCriterion("model_category not in", values, "modelCategory");
            return (Criteria) this;
        }

        public Criteria andModelCategoryBetween(String value1, String value2) {
            addCriterion("model_category between", value1, value2, "modelCategory");
            return (Criteria) this;
        }

        public Criteria andModelCategoryNotBetween(String value1, String value2) {
            addCriterion("model_category not between", value1, value2, "modelCategory");
            return (Criteria) this;
        }

        public Criteria andManufactureTypeIsNull() {
            addCriterion("manufacture_type is null");
            return (Criteria) this;
        }

        public Criteria andManufactureTypeIsNotNull() {
            addCriterion("manufacture_type is not null");
            return (Criteria) this;
        }

        public Criteria andManufactureTypeEqualTo(String value) {
            addCriterion("manufacture_type =", value, "manufactureType");
            return (Criteria) this;
        }

        public Criteria andManufactureTypeNotEqualTo(String value) {
            addCriterion("manufacture_type <>", value, "manufactureType");
            return (Criteria) this;
        }

        public Criteria andManufactureTypeGreaterThan(String value) {
            addCriterion("manufacture_type >", value, "manufactureType");
            return (Criteria) this;
        }

        public Criteria andManufactureTypeGreaterThanOrEqualTo(String value) {
            addCriterion("manufacture_type >=", value, "manufactureType");
            return (Criteria) this;
        }

        public Criteria andManufactureTypeLessThan(String value) {
            addCriterion("manufacture_type <", value, "manufactureType");
            return (Criteria) this;
        }

        public Criteria andManufactureTypeLessThanOrEqualTo(String value) {
            addCriterion("manufacture_type <=", value, "manufactureType");
            return (Criteria) this;
        }

        public Criteria andManufactureTypeLike(String value) {
            addCriterion("manufacture_type like", value, "manufactureType");
            return (Criteria) this;
        }

        public Criteria andManufactureTypeNotLike(String value) {
            addCriterion("manufacture_type not like", value, "manufactureType");
            return (Criteria) this;
        }

        public Criteria andManufactureTypeIn(List<String> values) {
            addCriterion("manufacture_type in", values, "manufactureType");
            return (Criteria) this;
        }

        public Criteria andManufactureTypeNotIn(List<String> values) {
            addCriterion("manufacture_type not in", values, "manufactureType");
            return (Criteria) this;
        }

        public Criteria andManufactureTypeBetween(String value1, String value2) {
            addCriterion("manufacture_type between", value1, value2, "manufactureType");
            return (Criteria) this;
        }

        public Criteria andManufactureTypeNotBetween(String value1, String value2) {
            addCriterion("manufacture_type not between", value1, value2, "manufactureType");
            return (Criteria) this;
        }

        public Criteria andItemIsNull() {
            addCriterion("item is null");
            return (Criteria) this;
        }

        public Criteria andItemIsNotNull() {
            addCriterion("item is not null");
            return (Criteria) this;
        }

        public Criteria andItemEqualTo(String value) {
            addCriterion("item =", value, "item");
            return (Criteria) this;
        }

        public Criteria andItemNotEqualTo(String value) {
            addCriterion("item <>", value, "item");
            return (Criteria) this;
        }

        public Criteria andItemGreaterThan(String value) {
            addCriterion("item >", value, "item");
            return (Criteria) this;
        }

        public Criteria andItemGreaterThanOrEqualTo(String value) {
            addCriterion("item >=", value, "item");
            return (Criteria) this;
        }

        public Criteria andItemLessThan(String value) {
            addCriterion("item <", value, "item");
            return (Criteria) this;
        }

        public Criteria andItemLessThanOrEqualTo(String value) {
            addCriterion("item <=", value, "item");
            return (Criteria) this;
        }

        public Criteria andItemLike(String value) {
            addCriterion("item like", value, "item");
            return (Criteria) this;
        }

        public Criteria andItemNotLike(String value) {
            addCriterion("item not like", value, "item");
            return (Criteria) this;
        }

        public Criteria andItemIn(List<String> values) {
            addCriterion("item in", values, "item");
            return (Criteria) this;
        }

        public Criteria andItemNotIn(List<String> values) {
            addCriterion("item not in", values, "item");
            return (Criteria) this;
        }

        public Criteria andItemBetween(String value1, String value2) {
            addCriterion("item between", value1, value2, "item");
            return (Criteria) this;
        }

        public Criteria andItemNotBetween(String value1, String value2) {
            addCriterion("item not between", value1, value2, "item");
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