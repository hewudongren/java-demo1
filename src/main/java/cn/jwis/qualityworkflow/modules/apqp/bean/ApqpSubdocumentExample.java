package cn.jwis.qualityworkflow.modules.apqp.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApqpSubdocumentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ApqpSubdocumentExample() {
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

        public Criteria andMasterIdIsNull() {
            addCriterion("master_id is null");
            return (Criteria) this;
        }

        public Criteria andMasterIdIsNotNull() {
            addCriterion("master_id is not null");
            return (Criteria) this;
        }

        public Criteria andMasterIdEqualTo(String value) {
            addCriterion("master_id =", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdNotEqualTo(String value) {
            addCriterion("master_id <>", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdGreaterThan(String value) {
            addCriterion("master_id >", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdGreaterThanOrEqualTo(String value) {
            addCriterion("master_id >=", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdLessThan(String value) {
            addCriterion("master_id <", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdLessThanOrEqualTo(String value) {
            addCriterion("master_id <=", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdLike(String value) {
            addCriterion("master_id like", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdNotLike(String value) {
            addCriterion("master_id not like", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdIn(List<String> values) {
            addCriterion("master_id in", values, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdNotIn(List<String> values) {
            addCriterion("master_id not in", values, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdBetween(String value1, String value2) {
            addCriterion("master_id between", value1, value2, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdNotBetween(String value1, String value2) {
            addCriterion("master_id not between", value1, value2, "masterId");
            return (Criteria) this;
        }

        public Criteria andApqpNumberIsNull() {
            addCriterion("apqp_number is null");
            return (Criteria) this;
        }

        public Criteria andApqpNumberIsNotNull() {
            addCriterion("apqp_number is not null");
            return (Criteria) this;
        }

        public Criteria andApqpNumberEqualTo(String value) {
            addCriterion("apqp_number =", value, "apqpNumber");
            return (Criteria) this;
        }

        public Criteria andApqpNumberNotEqualTo(String value) {
            addCriterion("apqp_number <>", value, "apqpNumber");
            return (Criteria) this;
        }

        public Criteria andApqpNumberGreaterThan(String value) {
            addCriterion("apqp_number >", value, "apqpNumber");
            return (Criteria) this;
        }

        public Criteria andApqpNumberGreaterThanOrEqualTo(String value) {
            addCriterion("apqp_number >=", value, "apqpNumber");
            return (Criteria) this;
        }

        public Criteria andApqpNumberLessThan(String value) {
            addCriterion("apqp_number <", value, "apqpNumber");
            return (Criteria) this;
        }

        public Criteria andApqpNumberLessThanOrEqualTo(String value) {
            addCriterion("apqp_number <=", value, "apqpNumber");
            return (Criteria) this;
        }

        public Criteria andApqpNumberLike(String value) {
            addCriterion("apqp_number like", value, "apqpNumber");
            return (Criteria) this;
        }

        public Criteria andApqpNumberNotLike(String value) {
            addCriterion("apqp_number not like", value, "apqpNumber");
            return (Criteria) this;
        }

        public Criteria andApqpNumberIn(List<String> values) {
            addCriterion("apqp_number in", values, "apqpNumber");
            return (Criteria) this;
        }

        public Criteria andApqpNumberNotIn(List<String> values) {
            addCriterion("apqp_number not in", values, "apqpNumber");
            return (Criteria) this;
        }

        public Criteria andApqpNumberBetween(String value1, String value2) {
            addCriterion("apqp_number between", value1, value2, "apqpNumber");
            return (Criteria) this;
        }

        public Criteria andApqpNumberNotBetween(String value1, String value2) {
            addCriterion("apqp_number not between", value1, value2, "apqpNumber");
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

        public Criteria andOwnerIsNull() {
            addCriterion("owner is null");
            return (Criteria) this;
        }

        public Criteria andOwnerIsNotNull() {
            addCriterion("owner is not null");
            return (Criteria) this;
        }

        public Criteria andOwnerEqualTo(String value) {
            addCriterion("owner =", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerNotEqualTo(String value) {
            addCriterion("owner <>", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerGreaterThan(String value) {
            addCriterion("owner >", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerGreaterThanOrEqualTo(String value) {
            addCriterion("owner >=", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerLessThan(String value) {
            addCriterion("owner <", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerLessThanOrEqualTo(String value) {
            addCriterion("owner <=", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerLike(String value) {
            addCriterion("owner like", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerNotLike(String value) {
            addCriterion("owner not like", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerIn(List<String> values) {
            addCriterion("owner in", values, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerNotIn(List<String> values) {
            addCriterion("owner not in", values, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerBetween(String value1, String value2) {
            addCriterion("owner between", value1, value2, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerNotBetween(String value1, String value2) {
            addCriterion("owner not between", value1, value2, "owner");
            return (Criteria) this;
        }

        public Criteria andQeIsNull() {
            addCriterion("qe is null");
            return (Criteria) this;
        }

        public Criteria andQeIsNotNull() {
            addCriterion("qe is not null");
            return (Criteria) this;
        }

        public Criteria andQeEqualTo(String value) {
            addCriterion("qe =", value, "qe");
            return (Criteria) this;
        }

        public Criteria andQeNotEqualTo(String value) {
            addCriterion("qe <>", value, "qe");
            return (Criteria) this;
        }

        public Criteria andQeGreaterThan(String value) {
            addCriterion("qe >", value, "qe");
            return (Criteria) this;
        }

        public Criteria andQeGreaterThanOrEqualTo(String value) {
            addCriterion("qe >=", value, "qe");
            return (Criteria) this;
        }

        public Criteria andQeLessThan(String value) {
            addCriterion("qe <", value, "qe");
            return (Criteria) this;
        }

        public Criteria andQeLessThanOrEqualTo(String value) {
            addCriterion("qe <=", value, "qe");
            return (Criteria) this;
        }

        public Criteria andQeLike(String value) {
            addCriterion("qe like", value, "qe");
            return (Criteria) this;
        }

        public Criteria andQeNotLike(String value) {
            addCriterion("qe not like", value, "qe");
            return (Criteria) this;
        }

        public Criteria andQeIn(List<String> values) {
            addCriterion("qe in", values, "qe");
            return (Criteria) this;
        }

        public Criteria andQeNotIn(List<String> values) {
            addCriterion("qe not in", values, "qe");
            return (Criteria) this;
        }

        public Criteria andQeBetween(String value1, String value2) {
            addCriterion("qe between", value1, value2, "qe");
            return (Criteria) this;
        }

        public Criteria andQeNotBetween(String value1, String value2) {
            addCriterion("qe not between", value1, value2, "qe");
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

        public Criteria andDvt1DocumentIsNull() {
            addCriterion("dvt1_document is null");
            return (Criteria) this;
        }

        public Criteria andDvt1DocumentIsNotNull() {
            addCriterion("dvt1_document is not null");
            return (Criteria) this;
        }

        public Criteria andDvt1DocumentEqualTo(String value) {
            addCriterion("dvt1_document =", value, "dvt1Document");
            return (Criteria) this;
        }

        public Criteria andDvt1DocumentNotEqualTo(String value) {
            addCriterion("dvt1_document <>", value, "dvt1Document");
            return (Criteria) this;
        }

        public Criteria andDvt1DocumentGreaterThan(String value) {
            addCriterion("dvt1_document >", value, "dvt1Document");
            return (Criteria) this;
        }

        public Criteria andDvt1DocumentGreaterThanOrEqualTo(String value) {
            addCriterion("dvt1_document >=", value, "dvt1Document");
            return (Criteria) this;
        }

        public Criteria andDvt1DocumentLessThan(String value) {
            addCriterion("dvt1_document <", value, "dvt1Document");
            return (Criteria) this;
        }

        public Criteria andDvt1DocumentLessThanOrEqualTo(String value) {
            addCriterion("dvt1_document <=", value, "dvt1Document");
            return (Criteria) this;
        }

        public Criteria andDvt1DocumentLike(String value) {
            addCriterion("dvt1_document like", value, "dvt1Document");
            return (Criteria) this;
        }

        public Criteria andDvt1DocumentNotLike(String value) {
            addCriterion("dvt1_document not like", value, "dvt1Document");
            return (Criteria) this;
        }

        public Criteria andDvt1DocumentIn(List<String> values) {
            addCriterion("dvt1_document in", values, "dvt1Document");
            return (Criteria) this;
        }

        public Criteria andDvt1DocumentNotIn(List<String> values) {
            addCriterion("dvt1_document not in", values, "dvt1Document");
            return (Criteria) this;
        }

        public Criteria andDvt1DocumentBetween(String value1, String value2) {
            addCriterion("dvt1_document between", value1, value2, "dvt1Document");
            return (Criteria) this;
        }

        public Criteria andDvt1DocumentNotBetween(String value1, String value2) {
            addCriterion("dvt1_document not between", value1, value2, "dvt1Document");
            return (Criteria) this;
        }

        public Criteria andDvt1UploaderIsNull() {
            addCriterion("dvt1_uploader is null");
            return (Criteria) this;
        }

        public Criteria andDvt1UploaderIsNotNull() {
            addCriterion("dvt1_uploader is not null");
            return (Criteria) this;
        }

        public Criteria andDvt1UploaderEqualTo(String value) {
            addCriterion("dvt1_uploader =", value, "dvt1Uploader");
            return (Criteria) this;
        }

        public Criteria andDvt1UploaderNotEqualTo(String value) {
            addCriterion("dvt1_uploader <>", value, "dvt1Uploader");
            return (Criteria) this;
        }

        public Criteria andDvt1UploaderGreaterThan(String value) {
            addCriterion("dvt1_uploader >", value, "dvt1Uploader");
            return (Criteria) this;
        }

        public Criteria andDvt1UploaderGreaterThanOrEqualTo(String value) {
            addCriterion("dvt1_uploader >=", value, "dvt1Uploader");
            return (Criteria) this;
        }

        public Criteria andDvt1UploaderLessThan(String value) {
            addCriterion("dvt1_uploader <", value, "dvt1Uploader");
            return (Criteria) this;
        }

        public Criteria andDvt1UploaderLessThanOrEqualTo(String value) {
            addCriterion("dvt1_uploader <=", value, "dvt1Uploader");
            return (Criteria) this;
        }

        public Criteria andDvt1UploaderLike(String value) {
            addCriterion("dvt1_uploader like", value, "dvt1Uploader");
            return (Criteria) this;
        }

        public Criteria andDvt1UploaderNotLike(String value) {
            addCriterion("dvt1_uploader not like", value, "dvt1Uploader");
            return (Criteria) this;
        }

        public Criteria andDvt1UploaderIn(List<String> values) {
            addCriterion("dvt1_uploader in", values, "dvt1Uploader");
            return (Criteria) this;
        }

        public Criteria andDvt1UploaderNotIn(List<String> values) {
            addCriterion("dvt1_uploader not in", values, "dvt1Uploader");
            return (Criteria) this;
        }

        public Criteria andDvt1UploaderBetween(String value1, String value2) {
            addCriterion("dvt1_uploader between", value1, value2, "dvt1Uploader");
            return (Criteria) this;
        }

        public Criteria andDvt1UploaderNotBetween(String value1, String value2) {
            addCriterion("dvt1_uploader not between", value1, value2, "dvt1Uploader");
            return (Criteria) this;
        }

        public Criteria andDvt1UploadTimeIsNull() {
            addCriterion("dvt1_upload_time is null");
            return (Criteria) this;
        }

        public Criteria andDvt1UploadTimeIsNotNull() {
            addCriterion("dvt1_upload_time is not null");
            return (Criteria) this;
        }

        public Criteria andDvt1UploadTimeEqualTo(Date value) {
            addCriterion("dvt1_upload_time =", value, "dvt1UploadTime");
            return (Criteria) this;
        }

        public Criteria andDvt1UploadTimeNotEqualTo(Date value) {
            addCriterion("dvt1_upload_time <>", value, "dvt1UploadTime");
            return (Criteria) this;
        }

        public Criteria andDvt1UploadTimeGreaterThan(Date value) {
            addCriterion("dvt1_upload_time >", value, "dvt1UploadTime");
            return (Criteria) this;
        }

        public Criteria andDvt1UploadTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("dvt1_upload_time >=", value, "dvt1UploadTime");
            return (Criteria) this;
        }

        public Criteria andDvt1UploadTimeLessThan(Date value) {
            addCriterion("dvt1_upload_time <", value, "dvt1UploadTime");
            return (Criteria) this;
        }

        public Criteria andDvt1UploadTimeLessThanOrEqualTo(Date value) {
            addCriterion("dvt1_upload_time <=", value, "dvt1UploadTime");
            return (Criteria) this;
        }

        public Criteria andDvt1UploadTimeIn(List<Date> values) {
            addCriterion("dvt1_upload_time in", values, "dvt1UploadTime");
            return (Criteria) this;
        }

        public Criteria andDvt1UploadTimeNotIn(List<Date> values) {
            addCriterion("dvt1_upload_time not in", values, "dvt1UploadTime");
            return (Criteria) this;
        }

        public Criteria andDvt1UploadTimeBetween(Date value1, Date value2) {
            addCriterion("dvt1_upload_time between", value1, value2, "dvt1UploadTime");
            return (Criteria) this;
        }

        public Criteria andDvt1UploadTimeNotBetween(Date value1, Date value2) {
            addCriterion("dvt1_upload_time not between", value1, value2, "dvt1UploadTime");
            return (Criteria) this;
        }

        public Criteria andDvt1AuditTimeIsNull() {
            addCriterion("dvt1_audit_time is null");
            return (Criteria) this;
        }

        public Criteria andDvt1AuditTimeIsNotNull() {
            addCriterion("dvt1_audit_time is not null");
            return (Criteria) this;
        }

        public Criteria andDvt1AuditTimeEqualTo(Date value) {
            addCriterion("dvt1_audit_time =", value, "dvt1AuditTime");
            return (Criteria) this;
        }

        public Criteria andDvt1AuditTimeNotEqualTo(Date value) {
            addCriterion("dvt1_audit_time <>", value, "dvt1AuditTime");
            return (Criteria) this;
        }

        public Criteria andDvt1AuditTimeGreaterThan(Date value) {
            addCriterion("dvt1_audit_time >", value, "dvt1AuditTime");
            return (Criteria) this;
        }

        public Criteria andDvt1AuditTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("dvt1_audit_time >=", value, "dvt1AuditTime");
            return (Criteria) this;
        }

        public Criteria andDvt1AuditTimeLessThan(Date value) {
            addCriterion("dvt1_audit_time <", value, "dvt1AuditTime");
            return (Criteria) this;
        }

        public Criteria andDvt1AuditTimeLessThanOrEqualTo(Date value) {
            addCriterion("dvt1_audit_time <=", value, "dvt1AuditTime");
            return (Criteria) this;
        }

        public Criteria andDvt1AuditTimeIn(List<Date> values) {
            addCriterion("dvt1_audit_time in", values, "dvt1AuditTime");
            return (Criteria) this;
        }

        public Criteria andDvt1AuditTimeNotIn(List<Date> values) {
            addCriterion("dvt1_audit_time not in", values, "dvt1AuditTime");
            return (Criteria) this;
        }

        public Criteria andDvt1AuditTimeBetween(Date value1, Date value2) {
            addCriterion("dvt1_audit_time between", value1, value2, "dvt1AuditTime");
            return (Criteria) this;
        }

        public Criteria andDvt1AuditTimeNotBetween(Date value1, Date value2) {
            addCriterion("dvt1_audit_time not between", value1, value2, "dvt1AuditTime");
            return (Criteria) this;
        }

        public Criteria andDvt1AuditorIsNull() {
            addCriterion("dvt1_auditor is null");
            return (Criteria) this;
        }

        public Criteria andDvt1AuditorIsNotNull() {
            addCriterion("dvt1_auditor is not null");
            return (Criteria) this;
        }

        public Criteria andDvt1AuditorEqualTo(String value) {
            addCriterion("dvt1_auditor =", value, "dvt1Auditor");
            return (Criteria) this;
        }

        public Criteria andDvt1AuditorNotEqualTo(String value) {
            addCriterion("dvt1_auditor <>", value, "dvt1Auditor");
            return (Criteria) this;
        }

        public Criteria andDvt1AuditorGreaterThan(String value) {
            addCriterion("dvt1_auditor >", value, "dvt1Auditor");
            return (Criteria) this;
        }

        public Criteria andDvt1AuditorGreaterThanOrEqualTo(String value) {
            addCriterion("dvt1_auditor >=", value, "dvt1Auditor");
            return (Criteria) this;
        }

        public Criteria andDvt1AuditorLessThan(String value) {
            addCriterion("dvt1_auditor <", value, "dvt1Auditor");
            return (Criteria) this;
        }

        public Criteria andDvt1AuditorLessThanOrEqualTo(String value) {
            addCriterion("dvt1_auditor <=", value, "dvt1Auditor");
            return (Criteria) this;
        }

        public Criteria andDvt1AuditorLike(String value) {
            addCriterion("dvt1_auditor like", value, "dvt1Auditor");
            return (Criteria) this;
        }

        public Criteria andDvt1AuditorNotLike(String value) {
            addCriterion("dvt1_auditor not like", value, "dvt1Auditor");
            return (Criteria) this;
        }

        public Criteria andDvt1AuditorIn(List<String> values) {
            addCriterion("dvt1_auditor in", values, "dvt1Auditor");
            return (Criteria) this;
        }

        public Criteria andDvt1AuditorNotIn(List<String> values) {
            addCriterion("dvt1_auditor not in", values, "dvt1Auditor");
            return (Criteria) this;
        }

        public Criteria andDvt1AuditorBetween(String value1, String value2) {
            addCriterion("dvt1_auditor between", value1, value2, "dvt1Auditor");
            return (Criteria) this;
        }

        public Criteria andDvt1AuditorNotBetween(String value1, String value2) {
            addCriterion("dvt1_auditor not between", value1, value2, "dvt1Auditor");
            return (Criteria) this;
        }

        public Criteria andDvt1AlarmDateIsNull() {
            addCriterion("dvt1_alarm_date is null");
            return (Criteria) this;
        }

        public Criteria andDvt1AlarmDateIsNotNull() {
            addCriterion("dvt1_alarm_date is not null");
            return (Criteria) this;
        }

        public Criteria andDvt1AlarmDateEqualTo(Date value) {
            addCriterion("dvt1_alarm_date =", value, "dvt1AlarmDate");
            return (Criteria) this;
        }

        public Criteria andDvt1AlarmDateNotEqualTo(Date value) {
            addCriterion("dvt1_alarm_date <>", value, "dvt1AlarmDate");
            return (Criteria) this;
        }

        public Criteria andDvt1AlarmDateGreaterThan(Date value) {
            addCriterion("dvt1_alarm_date >", value, "dvt1AlarmDate");
            return (Criteria) this;
        }

        public Criteria andDvt1AlarmDateGreaterThanOrEqualTo(Date value) {
            addCriterion("dvt1_alarm_date >=", value, "dvt1AlarmDate");
            return (Criteria) this;
        }

        public Criteria andDvt1AlarmDateLessThan(Date value) {
            addCriterion("dvt1_alarm_date <", value, "dvt1AlarmDate");
            return (Criteria) this;
        }

        public Criteria andDvt1AlarmDateLessThanOrEqualTo(Date value) {
            addCriterion("dvt1_alarm_date <=", value, "dvt1AlarmDate");
            return (Criteria) this;
        }

        public Criteria andDvt1AlarmDateIn(List<Date> values) {
            addCriterion("dvt1_alarm_date in", values, "dvt1AlarmDate");
            return (Criteria) this;
        }

        public Criteria andDvt1AlarmDateNotIn(List<Date> values) {
            addCriterion("dvt1_alarm_date not in", values, "dvt1AlarmDate");
            return (Criteria) this;
        }

        public Criteria andDvt1AlarmDateBetween(Date value1, Date value2) {
            addCriterion("dvt1_alarm_date between", value1, value2, "dvt1AlarmDate");
            return (Criteria) this;
        }

        public Criteria andDvt1AlarmDateNotBetween(Date value1, Date value2) {
            addCriterion("dvt1_alarm_date not between", value1, value2, "dvt1AlarmDate");
            return (Criteria) this;
        }

        public Criteria andDvt1DeadlineIsNull() {
            addCriterion("dvt1_deadline is null");
            return (Criteria) this;
        }

        public Criteria andDvt1DeadlineIsNotNull() {
            addCriterion("dvt1_deadline is not null");
            return (Criteria) this;
        }

        public Criteria andDvt1DeadlineEqualTo(Date value) {
            addCriterion("dvt1_deadline =", value, "dvt1Deadline");
            return (Criteria) this;
        }

        public Criteria andDvt1DeadlineNotEqualTo(Date value) {
            addCriterion("dvt1_deadline <>", value, "dvt1Deadline");
            return (Criteria) this;
        }

        public Criteria andDvt1DeadlineGreaterThan(Date value) {
            addCriterion("dvt1_deadline >", value, "dvt1Deadline");
            return (Criteria) this;
        }

        public Criteria andDvt1DeadlineGreaterThanOrEqualTo(Date value) {
            addCriterion("dvt1_deadline >=", value, "dvt1Deadline");
            return (Criteria) this;
        }

        public Criteria andDvt1DeadlineLessThan(Date value) {
            addCriterion("dvt1_deadline <", value, "dvt1Deadline");
            return (Criteria) this;
        }

        public Criteria andDvt1DeadlineLessThanOrEqualTo(Date value) {
            addCriterion("dvt1_deadline <=", value, "dvt1Deadline");
            return (Criteria) this;
        }

        public Criteria andDvt1DeadlineIn(List<Date> values) {
            addCriterion("dvt1_deadline in", values, "dvt1Deadline");
            return (Criteria) this;
        }

        public Criteria andDvt1DeadlineNotIn(List<Date> values) {
            addCriterion("dvt1_deadline not in", values, "dvt1Deadline");
            return (Criteria) this;
        }

        public Criteria andDvt1DeadlineBetween(Date value1, Date value2) {
            addCriterion("dvt1_deadline between", value1, value2, "dvt1Deadline");
            return (Criteria) this;
        }

        public Criteria andDvt1DeadlineNotBetween(Date value1, Date value2) {
            addCriterion("dvt1_deadline not between", value1, value2, "dvt1Deadline");
            return (Criteria) this;
        }

        public Criteria andPemeaDocumentIsNull() {
            addCriterion("pemea_document is null");
            return (Criteria) this;
        }

        public Criteria andPemeaDocumentIsNotNull() {
            addCriterion("pemea_document is not null");
            return (Criteria) this;
        }

        public Criteria andPemeaDocumentEqualTo(String value) {
            addCriterion("pemea_document =", value, "pemeaDocument");
            return (Criteria) this;
        }

        public Criteria andPemeaDocumentNotEqualTo(String value) {
            addCriterion("pemea_document <>", value, "pemeaDocument");
            return (Criteria) this;
        }

        public Criteria andPemeaDocumentGreaterThan(String value) {
            addCriterion("pemea_document >", value, "pemeaDocument");
            return (Criteria) this;
        }

        public Criteria andPemeaDocumentGreaterThanOrEqualTo(String value) {
            addCriterion("pemea_document >=", value, "pemeaDocument");
            return (Criteria) this;
        }

        public Criteria andPemeaDocumentLessThan(String value) {
            addCriterion("pemea_document <", value, "pemeaDocument");
            return (Criteria) this;
        }

        public Criteria andPemeaDocumentLessThanOrEqualTo(String value) {
            addCriterion("pemea_document <=", value, "pemeaDocument");
            return (Criteria) this;
        }

        public Criteria andPemeaDocumentLike(String value) {
            addCriterion("pemea_document like", value, "pemeaDocument");
            return (Criteria) this;
        }

        public Criteria andPemeaDocumentNotLike(String value) {
            addCriterion("pemea_document not like", value, "pemeaDocument");
            return (Criteria) this;
        }

        public Criteria andPemeaDocumentIn(List<String> values) {
            addCriterion("pemea_document in", values, "pemeaDocument");
            return (Criteria) this;
        }

        public Criteria andPemeaDocumentNotIn(List<String> values) {
            addCriterion("pemea_document not in", values, "pemeaDocument");
            return (Criteria) this;
        }

        public Criteria andPemeaDocumentBetween(String value1, String value2) {
            addCriterion("pemea_document between", value1, value2, "pemeaDocument");
            return (Criteria) this;
        }

        public Criteria andPemeaDocumentNotBetween(String value1, String value2) {
            addCriterion("pemea_document not between", value1, value2, "pemeaDocument");
            return (Criteria) this;
        }

        public Criteria andPemeaUploaderIsNull() {
            addCriterion("pemea_uploader is null");
            return (Criteria) this;
        }

        public Criteria andPemeaUploaderIsNotNull() {
            addCriterion("pemea_uploader is not null");
            return (Criteria) this;
        }

        public Criteria andPemeaUploaderEqualTo(String value) {
            addCriterion("pemea_uploader =", value, "pemeaUploader");
            return (Criteria) this;
        }

        public Criteria andPemeaUploaderNotEqualTo(String value) {
            addCriterion("pemea_uploader <>", value, "pemeaUploader");
            return (Criteria) this;
        }

        public Criteria andPemeaUploaderGreaterThan(String value) {
            addCriterion("pemea_uploader >", value, "pemeaUploader");
            return (Criteria) this;
        }

        public Criteria andPemeaUploaderGreaterThanOrEqualTo(String value) {
            addCriterion("pemea_uploader >=", value, "pemeaUploader");
            return (Criteria) this;
        }

        public Criteria andPemeaUploaderLessThan(String value) {
            addCriterion("pemea_uploader <", value, "pemeaUploader");
            return (Criteria) this;
        }

        public Criteria andPemeaUploaderLessThanOrEqualTo(String value) {
            addCriterion("pemea_uploader <=", value, "pemeaUploader");
            return (Criteria) this;
        }

        public Criteria andPemeaUploaderLike(String value) {
            addCriterion("pemea_uploader like", value, "pemeaUploader");
            return (Criteria) this;
        }

        public Criteria andPemeaUploaderNotLike(String value) {
            addCriterion("pemea_uploader not like", value, "pemeaUploader");
            return (Criteria) this;
        }

        public Criteria andPemeaUploaderIn(List<String> values) {
            addCriterion("pemea_uploader in", values, "pemeaUploader");
            return (Criteria) this;
        }

        public Criteria andPemeaUploaderNotIn(List<String> values) {
            addCriterion("pemea_uploader not in", values, "pemeaUploader");
            return (Criteria) this;
        }

        public Criteria andPemeaUploaderBetween(String value1, String value2) {
            addCriterion("pemea_uploader between", value1, value2, "pemeaUploader");
            return (Criteria) this;
        }

        public Criteria andPemeaUploaderNotBetween(String value1, String value2) {
            addCriterion("pemea_uploader not between", value1, value2, "pemeaUploader");
            return (Criteria) this;
        }

        public Criteria andPemeaUploadTimeIsNull() {
            addCriterion("pemea_upload_time is null");
            return (Criteria) this;
        }

        public Criteria andPemeaUploadTimeIsNotNull() {
            addCriterion("pemea_upload_time is not null");
            return (Criteria) this;
        }

        public Criteria andPemeaUploadTimeEqualTo(Date value) {
            addCriterion("pemea_upload_time =", value, "pemeaUploadTime");
            return (Criteria) this;
        }

        public Criteria andPemeaUploadTimeNotEqualTo(Date value) {
            addCriterion("pemea_upload_time <>", value, "pemeaUploadTime");
            return (Criteria) this;
        }

        public Criteria andPemeaUploadTimeGreaterThan(Date value) {
            addCriterion("pemea_upload_time >", value, "pemeaUploadTime");
            return (Criteria) this;
        }

        public Criteria andPemeaUploadTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("pemea_upload_time >=", value, "pemeaUploadTime");
            return (Criteria) this;
        }

        public Criteria andPemeaUploadTimeLessThan(Date value) {
            addCriterion("pemea_upload_time <", value, "pemeaUploadTime");
            return (Criteria) this;
        }

        public Criteria andPemeaUploadTimeLessThanOrEqualTo(Date value) {
            addCriterion("pemea_upload_time <=", value, "pemeaUploadTime");
            return (Criteria) this;
        }

        public Criteria andPemeaUploadTimeIn(List<Date> values) {
            addCriterion("pemea_upload_time in", values, "pemeaUploadTime");
            return (Criteria) this;
        }

        public Criteria andPemeaUploadTimeNotIn(List<Date> values) {
            addCriterion("pemea_upload_time not in", values, "pemeaUploadTime");
            return (Criteria) this;
        }

        public Criteria andPemeaUploadTimeBetween(Date value1, Date value2) {
            addCriterion("pemea_upload_time between", value1, value2, "pemeaUploadTime");
            return (Criteria) this;
        }

        public Criteria andPemeaUploadTimeNotBetween(Date value1, Date value2) {
            addCriterion("pemea_upload_time not between", value1, value2, "pemeaUploadTime");
            return (Criteria) this;
        }

        public Criteria andPemeaAuditTimeIsNull() {
            addCriterion("pemea_audit_time is null");
            return (Criteria) this;
        }

        public Criteria andPemeaAuditTimeIsNotNull() {
            addCriterion("pemea_audit_time is not null");
            return (Criteria) this;
        }

        public Criteria andPemeaAuditTimeEqualTo(Date value) {
            addCriterion("pemea_audit_time =", value, "pemeaAuditTime");
            return (Criteria) this;
        }

        public Criteria andPemeaAuditTimeNotEqualTo(Date value) {
            addCriterion("pemea_audit_time <>", value, "pemeaAuditTime");
            return (Criteria) this;
        }

        public Criteria andPemeaAuditTimeGreaterThan(Date value) {
            addCriterion("pemea_audit_time >", value, "pemeaAuditTime");
            return (Criteria) this;
        }

        public Criteria andPemeaAuditTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("pemea_audit_time >=", value, "pemeaAuditTime");
            return (Criteria) this;
        }

        public Criteria andPemeaAuditTimeLessThan(Date value) {
            addCriterion("pemea_audit_time <", value, "pemeaAuditTime");
            return (Criteria) this;
        }

        public Criteria andPemeaAuditTimeLessThanOrEqualTo(Date value) {
            addCriterion("pemea_audit_time <=", value, "pemeaAuditTime");
            return (Criteria) this;
        }

        public Criteria andPemeaAuditTimeIn(List<Date> values) {
            addCriterion("pemea_audit_time in", values, "pemeaAuditTime");
            return (Criteria) this;
        }

        public Criteria andPemeaAuditTimeNotIn(List<Date> values) {
            addCriterion("pemea_audit_time not in", values, "pemeaAuditTime");
            return (Criteria) this;
        }

        public Criteria andPemeaAuditTimeBetween(Date value1, Date value2) {
            addCriterion("pemea_audit_time between", value1, value2, "pemeaAuditTime");
            return (Criteria) this;
        }

        public Criteria andPemeaAuditTimeNotBetween(Date value1, Date value2) {
            addCriterion("pemea_audit_time not between", value1, value2, "pemeaAuditTime");
            return (Criteria) this;
        }

        public Criteria andPemeaAuditorIsNull() {
            addCriterion("pemea_auditor is null");
            return (Criteria) this;
        }

        public Criteria andPemeaAuditorIsNotNull() {
            addCriterion("pemea_auditor is not null");
            return (Criteria) this;
        }

        public Criteria andPemeaAuditorEqualTo(String value) {
            addCriterion("pemea_auditor =", value, "pemeaAuditor");
            return (Criteria) this;
        }

        public Criteria andPemeaAuditorNotEqualTo(String value) {
            addCriterion("pemea_auditor <>", value, "pemeaAuditor");
            return (Criteria) this;
        }

        public Criteria andPemeaAuditorGreaterThan(String value) {
            addCriterion("pemea_auditor >", value, "pemeaAuditor");
            return (Criteria) this;
        }

        public Criteria andPemeaAuditorGreaterThanOrEqualTo(String value) {
            addCriterion("pemea_auditor >=", value, "pemeaAuditor");
            return (Criteria) this;
        }

        public Criteria andPemeaAuditorLessThan(String value) {
            addCriterion("pemea_auditor <", value, "pemeaAuditor");
            return (Criteria) this;
        }

        public Criteria andPemeaAuditorLessThanOrEqualTo(String value) {
            addCriterion("pemea_auditor <=", value, "pemeaAuditor");
            return (Criteria) this;
        }

        public Criteria andPemeaAuditorLike(String value) {
            addCriterion("pemea_auditor like", value, "pemeaAuditor");
            return (Criteria) this;
        }

        public Criteria andPemeaAuditorNotLike(String value) {
            addCriterion("pemea_auditor not like", value, "pemeaAuditor");
            return (Criteria) this;
        }

        public Criteria andPemeaAuditorIn(List<String> values) {
            addCriterion("pemea_auditor in", values, "pemeaAuditor");
            return (Criteria) this;
        }

        public Criteria andPemeaAuditorNotIn(List<String> values) {
            addCriterion("pemea_auditor not in", values, "pemeaAuditor");
            return (Criteria) this;
        }

        public Criteria andPemeaAuditorBetween(String value1, String value2) {
            addCriterion("pemea_auditor between", value1, value2, "pemeaAuditor");
            return (Criteria) this;
        }

        public Criteria andPemeaAuditorNotBetween(String value1, String value2) {
            addCriterion("pemea_auditor not between", value1, value2, "pemeaAuditor");
            return (Criteria) this;
        }

        public Criteria andPemeaAlarmDateIsNull() {
            addCriterion("pemea_alarm_date is null");
            return (Criteria) this;
        }

        public Criteria andPemeaAlarmDateIsNotNull() {
            addCriterion("pemea_alarm_date is not null");
            return (Criteria) this;
        }

        public Criteria andPemeaAlarmDateEqualTo(Date value) {
            addCriterion("pemea_alarm_date =", value, "pemeaAlarmDate");
            return (Criteria) this;
        }

        public Criteria andPemeaAlarmDateNotEqualTo(Date value) {
            addCriterion("pemea_alarm_date <>", value, "pemeaAlarmDate");
            return (Criteria) this;
        }

        public Criteria andPemeaAlarmDateGreaterThan(Date value) {
            addCriterion("pemea_alarm_date >", value, "pemeaAlarmDate");
            return (Criteria) this;
        }

        public Criteria andPemeaAlarmDateGreaterThanOrEqualTo(Date value) {
            addCriterion("pemea_alarm_date >=", value, "pemeaAlarmDate");
            return (Criteria) this;
        }

        public Criteria andPemeaAlarmDateLessThan(Date value) {
            addCriterion("pemea_alarm_date <", value, "pemeaAlarmDate");
            return (Criteria) this;
        }

        public Criteria andPemeaAlarmDateLessThanOrEqualTo(Date value) {
            addCriterion("pemea_alarm_date <=", value, "pemeaAlarmDate");
            return (Criteria) this;
        }

        public Criteria andPemeaAlarmDateIn(List<Date> values) {
            addCriterion("pemea_alarm_date in", values, "pemeaAlarmDate");
            return (Criteria) this;
        }

        public Criteria andPemeaAlarmDateNotIn(List<Date> values) {
            addCriterion("pemea_alarm_date not in", values, "pemeaAlarmDate");
            return (Criteria) this;
        }

        public Criteria andPemeaAlarmDateBetween(Date value1, Date value2) {
            addCriterion("pemea_alarm_date between", value1, value2, "pemeaAlarmDate");
            return (Criteria) this;
        }

        public Criteria andPemeaAlarmDateNotBetween(Date value1, Date value2) {
            addCriterion("pemea_alarm_date not between", value1, value2, "pemeaAlarmDate");
            return (Criteria) this;
        }

        public Criteria andPemeaDeadlineIsNull() {
            addCriterion("pemea_deadline is null");
            return (Criteria) this;
        }

        public Criteria andPemeaDeadlineIsNotNull() {
            addCriterion("pemea_deadline is not null");
            return (Criteria) this;
        }

        public Criteria andPemeaDeadlineEqualTo(Date value) {
            addCriterion("pemea_deadline =", value, "pemeaDeadline");
            return (Criteria) this;
        }

        public Criteria andPemeaDeadlineNotEqualTo(Date value) {
            addCriterion("pemea_deadline <>", value, "pemeaDeadline");
            return (Criteria) this;
        }

        public Criteria andPemeaDeadlineGreaterThan(Date value) {
            addCriterion("pemea_deadline >", value, "pemeaDeadline");
            return (Criteria) this;
        }

        public Criteria andPemeaDeadlineGreaterThanOrEqualTo(Date value) {
            addCriterion("pemea_deadline >=", value, "pemeaDeadline");
            return (Criteria) this;
        }

        public Criteria andPemeaDeadlineLessThan(Date value) {
            addCriterion("pemea_deadline <", value, "pemeaDeadline");
            return (Criteria) this;
        }

        public Criteria andPemeaDeadlineLessThanOrEqualTo(Date value) {
            addCriterion("pemea_deadline <=", value, "pemeaDeadline");
            return (Criteria) this;
        }

        public Criteria andPemeaDeadlineIn(List<Date> values) {
            addCriterion("pemea_deadline in", values, "pemeaDeadline");
            return (Criteria) this;
        }

        public Criteria andPemeaDeadlineNotIn(List<Date> values) {
            addCriterion("pemea_deadline not in", values, "pemeaDeadline");
            return (Criteria) this;
        }

        public Criteria andPemeaDeadlineBetween(Date value1, Date value2) {
            addCriterion("pemea_deadline between", value1, value2, "pemeaDeadline");
            return (Criteria) this;
        }

        public Criteria andPemeaDeadlineNotBetween(Date value1, Date value2) {
            addCriterion("pemea_deadline not between", value1, value2, "pemeaDeadline");
            return (Criteria) this;
        }

        public Criteria andQcChartDocumentIsNull() {
            addCriterion("qc_chart_document is null");
            return (Criteria) this;
        }

        public Criteria andQcChartDocumentIsNotNull() {
            addCriterion("qc_chart_document is not null");
            return (Criteria) this;
        }

        public Criteria andQcChartDocumentEqualTo(String value) {
            addCriterion("qc_chart_document =", value, "qcChartDocument");
            return (Criteria) this;
        }

        public Criteria andQcChartDocumentNotEqualTo(String value) {
            addCriterion("qc_chart_document <>", value, "qcChartDocument");
            return (Criteria) this;
        }

        public Criteria andQcChartDocumentGreaterThan(String value) {
            addCriterion("qc_chart_document >", value, "qcChartDocument");
            return (Criteria) this;
        }

        public Criteria andQcChartDocumentGreaterThanOrEqualTo(String value) {
            addCriterion("qc_chart_document >=", value, "qcChartDocument");
            return (Criteria) this;
        }

        public Criteria andQcChartDocumentLessThan(String value) {
            addCriterion("qc_chart_document <", value, "qcChartDocument");
            return (Criteria) this;
        }

        public Criteria andQcChartDocumentLessThanOrEqualTo(String value) {
            addCriterion("qc_chart_document <=", value, "qcChartDocument");
            return (Criteria) this;
        }

        public Criteria andQcChartDocumentLike(String value) {
            addCriterion("qc_chart_document like", value, "qcChartDocument");
            return (Criteria) this;
        }

        public Criteria andQcChartDocumentNotLike(String value) {
            addCriterion("qc_chart_document not like", value, "qcChartDocument");
            return (Criteria) this;
        }

        public Criteria andQcChartDocumentIn(List<String> values) {
            addCriterion("qc_chart_document in", values, "qcChartDocument");
            return (Criteria) this;
        }

        public Criteria andQcChartDocumentNotIn(List<String> values) {
            addCriterion("qc_chart_document not in", values, "qcChartDocument");
            return (Criteria) this;
        }

        public Criteria andQcChartDocumentBetween(String value1, String value2) {
            addCriterion("qc_chart_document between", value1, value2, "qcChartDocument");
            return (Criteria) this;
        }

        public Criteria andQcChartDocumentNotBetween(String value1, String value2) {
            addCriterion("qc_chart_document not between", value1, value2, "qcChartDocument");
            return (Criteria) this;
        }

        public Criteria andQcChartUploaderIsNull() {
            addCriterion("qc_chart__uploader is null");
            return (Criteria) this;
        }

        public Criteria andQcChartUploaderIsNotNull() {
            addCriterion("qc_chart__uploader is not null");
            return (Criteria) this;
        }

        public Criteria andQcChartUploaderEqualTo(String value) {
            addCriterion("qc_chart__uploader =", value, "qcChartUploader");
            return (Criteria) this;
        }

        public Criteria andQcChartUploaderNotEqualTo(String value) {
            addCriterion("qc_chart__uploader <>", value, "qcChartUploader");
            return (Criteria) this;
        }

        public Criteria andQcChartUploaderGreaterThan(String value) {
            addCriterion("qc_chart__uploader >", value, "qcChartUploader");
            return (Criteria) this;
        }

        public Criteria andQcChartUploaderGreaterThanOrEqualTo(String value) {
            addCriterion("qc_chart__uploader >=", value, "qcChartUploader");
            return (Criteria) this;
        }

        public Criteria andQcChartUploaderLessThan(String value) {
            addCriterion("qc_chart__uploader <", value, "qcChartUploader");
            return (Criteria) this;
        }

        public Criteria andQcChartUploaderLessThanOrEqualTo(String value) {
            addCriterion("qc_chart__uploader <=", value, "qcChartUploader");
            return (Criteria) this;
        }

        public Criteria andQcChartUploaderLike(String value) {
            addCriterion("qc_chart__uploader like", value, "qcChartUploader");
            return (Criteria) this;
        }

        public Criteria andQcChartUploaderNotLike(String value) {
            addCriterion("qc_chart__uploader not like", value, "qcChartUploader");
            return (Criteria) this;
        }

        public Criteria andQcChartUploaderIn(List<String> values) {
            addCriterion("qc_chart__uploader in", values, "qcChartUploader");
            return (Criteria) this;
        }

        public Criteria andQcChartUploaderNotIn(List<String> values) {
            addCriterion("qc_chart__uploader not in", values, "qcChartUploader");
            return (Criteria) this;
        }

        public Criteria andQcChartUploaderBetween(String value1, String value2) {
            addCriterion("qc_chart__uploader between", value1, value2, "qcChartUploader");
            return (Criteria) this;
        }

        public Criteria andQcChartUploaderNotBetween(String value1, String value2) {
            addCriterion("qc_chart__uploader not between", value1, value2, "qcChartUploader");
            return (Criteria) this;
        }

        public Criteria andQcChartUploadTimeIsNull() {
            addCriterion("qc_chart__upload_time is null");
            return (Criteria) this;
        }

        public Criteria andQcChartUploadTimeIsNotNull() {
            addCriterion("qc_chart__upload_time is not null");
            return (Criteria) this;
        }

        public Criteria andQcChartUploadTimeEqualTo(Date value) {
            addCriterion("qc_chart__upload_time =", value, "qcChartUploadTime");
            return (Criteria) this;
        }

        public Criteria andQcChartUploadTimeNotEqualTo(Date value) {
            addCriterion("qc_chart__upload_time <>", value, "qcChartUploadTime");
            return (Criteria) this;
        }

        public Criteria andQcChartUploadTimeGreaterThan(Date value) {
            addCriterion("qc_chart__upload_time >", value, "qcChartUploadTime");
            return (Criteria) this;
        }

        public Criteria andQcChartUploadTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("qc_chart__upload_time >=", value, "qcChartUploadTime");
            return (Criteria) this;
        }

        public Criteria andQcChartUploadTimeLessThan(Date value) {
            addCriterion("qc_chart__upload_time <", value, "qcChartUploadTime");
            return (Criteria) this;
        }

        public Criteria andQcChartUploadTimeLessThanOrEqualTo(Date value) {
            addCriterion("qc_chart__upload_time <=", value, "qcChartUploadTime");
            return (Criteria) this;
        }

        public Criteria andQcChartUploadTimeIn(List<Date> values) {
            addCriterion("qc_chart__upload_time in", values, "qcChartUploadTime");
            return (Criteria) this;
        }

        public Criteria andQcChartUploadTimeNotIn(List<Date> values) {
            addCriterion("qc_chart__upload_time not in", values, "qcChartUploadTime");
            return (Criteria) this;
        }

        public Criteria andQcChartUploadTimeBetween(Date value1, Date value2) {
            addCriterion("qc_chart__upload_time between", value1, value2, "qcChartUploadTime");
            return (Criteria) this;
        }

        public Criteria andQcChartUploadTimeNotBetween(Date value1, Date value2) {
            addCriterion("qc_chart__upload_time not between", value1, value2, "qcChartUploadTime");
            return (Criteria) this;
        }

        public Criteria andQcChartAuditTimeIsNull() {
            addCriterion("qc_chart__audit_time is null");
            return (Criteria) this;
        }

        public Criteria andQcChartAuditTimeIsNotNull() {
            addCriterion("qc_chart__audit_time is not null");
            return (Criteria) this;
        }

        public Criteria andQcChartAuditTimeEqualTo(Date value) {
            addCriterion("qc_chart__audit_time =", value, "qcChartAuditTime");
            return (Criteria) this;
        }

        public Criteria andQcChartAuditTimeNotEqualTo(Date value) {
            addCriterion("qc_chart__audit_time <>", value, "qcChartAuditTime");
            return (Criteria) this;
        }

        public Criteria andQcChartAuditTimeGreaterThan(Date value) {
            addCriterion("qc_chart__audit_time >", value, "qcChartAuditTime");
            return (Criteria) this;
        }

        public Criteria andQcChartAuditTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("qc_chart__audit_time >=", value, "qcChartAuditTime");
            return (Criteria) this;
        }

        public Criteria andQcChartAuditTimeLessThan(Date value) {
            addCriterion("qc_chart__audit_time <", value, "qcChartAuditTime");
            return (Criteria) this;
        }

        public Criteria andQcChartAuditTimeLessThanOrEqualTo(Date value) {
            addCriterion("qc_chart__audit_time <=", value, "qcChartAuditTime");
            return (Criteria) this;
        }

        public Criteria andQcChartAuditTimeIn(List<Date> values) {
            addCriterion("qc_chart__audit_time in", values, "qcChartAuditTime");
            return (Criteria) this;
        }

        public Criteria andQcChartAuditTimeNotIn(List<Date> values) {
            addCriterion("qc_chart__audit_time not in", values, "qcChartAuditTime");
            return (Criteria) this;
        }

        public Criteria andQcChartAuditTimeBetween(Date value1, Date value2) {
            addCriterion("qc_chart__audit_time between", value1, value2, "qcChartAuditTime");
            return (Criteria) this;
        }

        public Criteria andQcChartAuditTimeNotBetween(Date value1, Date value2) {
            addCriterion("qc_chart__audit_time not between", value1, value2, "qcChartAuditTime");
            return (Criteria) this;
        }

        public Criteria andQcChartAuditorIsNull() {
            addCriterion("qc_chart__auditor is null");
            return (Criteria) this;
        }

        public Criteria andQcChartAuditorIsNotNull() {
            addCriterion("qc_chart__auditor is not null");
            return (Criteria) this;
        }

        public Criteria andQcChartAuditorEqualTo(String value) {
            addCriterion("qc_chart__auditor =", value, "qcChartAuditor");
            return (Criteria) this;
        }

        public Criteria andQcChartAuditorNotEqualTo(String value) {
            addCriterion("qc_chart__auditor <>", value, "qcChartAuditor");
            return (Criteria) this;
        }

        public Criteria andQcChartAuditorGreaterThan(String value) {
            addCriterion("qc_chart__auditor >", value, "qcChartAuditor");
            return (Criteria) this;
        }

        public Criteria andQcChartAuditorGreaterThanOrEqualTo(String value) {
            addCriterion("qc_chart__auditor >=", value, "qcChartAuditor");
            return (Criteria) this;
        }

        public Criteria andQcChartAuditorLessThan(String value) {
            addCriterion("qc_chart__auditor <", value, "qcChartAuditor");
            return (Criteria) this;
        }

        public Criteria andQcChartAuditorLessThanOrEqualTo(String value) {
            addCriterion("qc_chart__auditor <=", value, "qcChartAuditor");
            return (Criteria) this;
        }

        public Criteria andQcChartAuditorLike(String value) {
            addCriterion("qc_chart__auditor like", value, "qcChartAuditor");
            return (Criteria) this;
        }

        public Criteria andQcChartAuditorNotLike(String value) {
            addCriterion("qc_chart__auditor not like", value, "qcChartAuditor");
            return (Criteria) this;
        }

        public Criteria andQcChartAuditorIn(List<String> values) {
            addCriterion("qc_chart__auditor in", values, "qcChartAuditor");
            return (Criteria) this;
        }

        public Criteria andQcChartAuditorNotIn(List<String> values) {
            addCriterion("qc_chart__auditor not in", values, "qcChartAuditor");
            return (Criteria) this;
        }

        public Criteria andQcChartAuditorBetween(String value1, String value2) {
            addCriterion("qc_chart__auditor between", value1, value2, "qcChartAuditor");
            return (Criteria) this;
        }

        public Criteria andQcChartAuditorNotBetween(String value1, String value2) {
            addCriterion("qc_chart__auditor not between", value1, value2, "qcChartAuditor");
            return (Criteria) this;
        }

        public Criteria andQcChartAlarmDateIsNull() {
            addCriterion("qc_chart_alarm_date is null");
            return (Criteria) this;
        }

        public Criteria andQcChartAlarmDateIsNotNull() {
            addCriterion("qc_chart_alarm_date is not null");
            return (Criteria) this;
        }

        public Criteria andQcChartAlarmDateEqualTo(Date value) {
            addCriterion("qc_chart_alarm_date =", value, "qcChartAlarmDate");
            return (Criteria) this;
        }

        public Criteria andQcChartAlarmDateNotEqualTo(Date value) {
            addCriterion("qc_chart_alarm_date <>", value, "qcChartAlarmDate");
            return (Criteria) this;
        }

        public Criteria andQcChartAlarmDateGreaterThan(Date value) {
            addCriterion("qc_chart_alarm_date >", value, "qcChartAlarmDate");
            return (Criteria) this;
        }

        public Criteria andQcChartAlarmDateGreaterThanOrEqualTo(Date value) {
            addCriterion("qc_chart_alarm_date >=", value, "qcChartAlarmDate");
            return (Criteria) this;
        }

        public Criteria andQcChartAlarmDateLessThan(Date value) {
            addCriterion("qc_chart_alarm_date <", value, "qcChartAlarmDate");
            return (Criteria) this;
        }

        public Criteria andQcChartAlarmDateLessThanOrEqualTo(Date value) {
            addCriterion("qc_chart_alarm_date <=", value, "qcChartAlarmDate");
            return (Criteria) this;
        }

        public Criteria andQcChartAlarmDateIn(List<Date> values) {
            addCriterion("qc_chart_alarm_date in", values, "qcChartAlarmDate");
            return (Criteria) this;
        }

        public Criteria andQcChartAlarmDateNotIn(List<Date> values) {
            addCriterion("qc_chart_alarm_date not in", values, "qcChartAlarmDate");
            return (Criteria) this;
        }

        public Criteria andQcChartAlarmDateBetween(Date value1, Date value2) {
            addCriterion("qc_chart_alarm_date between", value1, value2, "qcChartAlarmDate");
            return (Criteria) this;
        }

        public Criteria andQcChartAlarmDateNotBetween(Date value1, Date value2) {
            addCriterion("qc_chart_alarm_date not between", value1, value2, "qcChartAlarmDate");
            return (Criteria) this;
        }

        public Criteria andQcChartDeadlineIsNull() {
            addCriterion("qc_chart_deadline is null");
            return (Criteria) this;
        }

        public Criteria andQcChartDeadlineIsNotNull() {
            addCriterion("qc_chart_deadline is not null");
            return (Criteria) this;
        }

        public Criteria andQcChartDeadlineEqualTo(Date value) {
            addCriterion("qc_chart_deadline =", value, "qcChartDeadline");
            return (Criteria) this;
        }

        public Criteria andQcChartDeadlineNotEqualTo(Date value) {
            addCriterion("qc_chart_deadline <>", value, "qcChartDeadline");
            return (Criteria) this;
        }

        public Criteria andQcChartDeadlineGreaterThan(Date value) {
            addCriterion("qc_chart_deadline >", value, "qcChartDeadline");
            return (Criteria) this;
        }

        public Criteria andQcChartDeadlineGreaterThanOrEqualTo(Date value) {
            addCriterion("qc_chart_deadline >=", value, "qcChartDeadline");
            return (Criteria) this;
        }

        public Criteria andQcChartDeadlineLessThan(Date value) {
            addCriterion("qc_chart_deadline <", value, "qcChartDeadline");
            return (Criteria) this;
        }

        public Criteria andQcChartDeadlineLessThanOrEqualTo(Date value) {
            addCriterion("qc_chart_deadline <=", value, "qcChartDeadline");
            return (Criteria) this;
        }

        public Criteria andQcChartDeadlineIn(List<Date> values) {
            addCriterion("qc_chart_deadline in", values, "qcChartDeadline");
            return (Criteria) this;
        }

        public Criteria andQcChartDeadlineNotIn(List<Date> values) {
            addCriterion("qc_chart_deadline not in", values, "qcChartDeadline");
            return (Criteria) this;
        }

        public Criteria andQcChartDeadlineBetween(Date value1, Date value2) {
            addCriterion("qc_chart_deadline between", value1, value2, "qcChartDeadline");
            return (Criteria) this;
        }

        public Criteria andQcChartDeadlineNotBetween(Date value1, Date value2) {
            addCriterion("qc_chart_deadline not between", value1, value2, "qcChartDeadline");
            return (Criteria) this;
        }

        public Criteria andNqeAuditorIsNull() {
            addCriterion("nqe_auditor is null");
            return (Criteria) this;
        }

        public Criteria andNqeAuditorIsNotNull() {
            addCriterion("nqe_auditor is not null");
            return (Criteria) this;
        }

        public Criteria andNqeAuditorEqualTo(String value) {
            addCriterion("nqe_auditor =", value, "nqeAuditor");
            return (Criteria) this;
        }

        public Criteria andNqeAuditorNotEqualTo(String value) {
            addCriterion("nqe_auditor <>", value, "nqeAuditor");
            return (Criteria) this;
        }

        public Criteria andNqeAuditorGreaterThan(String value) {
            addCriterion("nqe_auditor >", value, "nqeAuditor");
            return (Criteria) this;
        }

        public Criteria andNqeAuditorGreaterThanOrEqualTo(String value) {
            addCriterion("nqe_auditor >=", value, "nqeAuditor");
            return (Criteria) this;
        }

        public Criteria andNqeAuditorLessThan(String value) {
            addCriterion("nqe_auditor <", value, "nqeAuditor");
            return (Criteria) this;
        }

        public Criteria andNqeAuditorLessThanOrEqualTo(String value) {
            addCriterion("nqe_auditor <=", value, "nqeAuditor");
            return (Criteria) this;
        }

        public Criteria andNqeAuditorLike(String value) {
            addCriterion("nqe_auditor like", value, "nqeAuditor");
            return (Criteria) this;
        }

        public Criteria andNqeAuditorNotLike(String value) {
            addCriterion("nqe_auditor not like", value, "nqeAuditor");
            return (Criteria) this;
        }

        public Criteria andNqeAuditorIn(List<String> values) {
            addCriterion("nqe_auditor in", values, "nqeAuditor");
            return (Criteria) this;
        }

        public Criteria andNqeAuditorNotIn(List<String> values) {
            addCriterion("nqe_auditor not in", values, "nqeAuditor");
            return (Criteria) this;
        }

        public Criteria andNqeAuditorBetween(String value1, String value2) {
            addCriterion("nqe_auditor between", value1, value2, "nqeAuditor");
            return (Criteria) this;
        }

        public Criteria andNqeAuditorNotBetween(String value1, String value2) {
            addCriterion("nqe_auditor not between", value1, value2, "nqeAuditor");
            return (Criteria) this;
        }

        public Criteria andNqeAuditTimeIsNull() {
            addCriterion("nqe_audit_time is null");
            return (Criteria) this;
        }

        public Criteria andNqeAuditTimeIsNotNull() {
            addCriterion("nqe_audit_time is not null");
            return (Criteria) this;
        }

        public Criteria andNqeAuditTimeEqualTo(Date value) {
            addCriterion("nqe_audit_time =", value, "nqeAuditTime");
            return (Criteria) this;
        }

        public Criteria andNqeAuditTimeNotEqualTo(Date value) {
            addCriterion("nqe_audit_time <>", value, "nqeAuditTime");
            return (Criteria) this;
        }

        public Criteria andNqeAuditTimeGreaterThan(Date value) {
            addCriterion("nqe_audit_time >", value, "nqeAuditTime");
            return (Criteria) this;
        }

        public Criteria andNqeAuditTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("nqe_audit_time >=", value, "nqeAuditTime");
            return (Criteria) this;
        }

        public Criteria andNqeAuditTimeLessThan(Date value) {
            addCriterion("nqe_audit_time <", value, "nqeAuditTime");
            return (Criteria) this;
        }

        public Criteria andNqeAuditTimeLessThanOrEqualTo(Date value) {
            addCriterion("nqe_audit_time <=", value, "nqeAuditTime");
            return (Criteria) this;
        }

        public Criteria andNqeAuditTimeIn(List<Date> values) {
            addCriterion("nqe_audit_time in", values, "nqeAuditTime");
            return (Criteria) this;
        }

        public Criteria andNqeAuditTimeNotIn(List<Date> values) {
            addCriterion("nqe_audit_time not in", values, "nqeAuditTime");
            return (Criteria) this;
        }

        public Criteria andNqeAuditTimeBetween(Date value1, Date value2) {
            addCriterion("nqe_audit_time between", value1, value2, "nqeAuditTime");
            return (Criteria) this;
        }

        public Criteria andNqeAuditTimeNotBetween(Date value1, Date value2) {
            addCriterion("nqe_audit_time not between", value1, value2, "nqeAuditTime");
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