package com.example.topic03pp.repositories.providers;

import org.apache.ibatis.jdbc.SQL;

public class CategoryProvider {

    public String getBookByCategoryIdProvider(Integer id) {
//        System.out.println("CategoryProvider: " + id);
        return new SQL() {{
            SELECT("*");
            FROM("tb_book b");
            WHERE("b.cate_id = #{id}");
        }}.toString();
    }
}
