package com.famiao.search.database.sql.template;

import java.io.Serializable;

public class DynamicSqlTemplate implements Serializable {

    private static final long serialVersionUID = -8227943374226209435L;

    private Integer id; // 动态sql主键id
    private String field; // 更新时间时间戳
    private String index; // 索引名称
    private String mapping;// 索引mapping json
    private String sql; // 需要构建索引的sql语句

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getMapping() {
        return mapping;
    }

    public void setMapping(String mapping) {
        this.mapping = mapping;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
