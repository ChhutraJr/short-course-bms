package com.example.topic03pp.repositories;

import com.example.topic03pp.models.Book;
import com.example.topic03pp.models.Category;
import com.example.topic03pp.repositories.providers.CategoryProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository {

    @Select("select * from tb_category order by id")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "books", column = "id", javaType = List.class, many = @Many(select = "getBookByCategoryId"))
    })
    List<Category> getAll();


    @SelectProvider(method = "getBookByCategoryIdProvider", type = CategoryProvider.class)
    List<Book> getBookByCategoryId(Integer id);


    @Select("select count(*) from tb_category")
    Integer count();


    @Insert("insert into tb_category(name, created_at) values(#{name}, #{created_at})")
    @SelectKey(
            keyColumn = "current_id",
            resultType = Integer.class,
            before = false,
            statementType = StatementType.PREPARED,
            keyProperty = "id",
            statement = "select currval('tb_category_id_seq') as current_id"
    )
    boolean create(Category category);


    @Insert({"<script>",
            "insert into tb_category(name) ",
            "values",
            "<foreach collection='categories' item='category' index='ind' separator=','>",
                "(", "#{category.name}", ")",
            "</foreach>",
            "</script>"})
    @SelectKey(
            keyColumn = "current_id",
            resultType = Integer.class,
            before = false,
            statementType = StatementType.PREPARED,
            keyProperty = "category.id",
            statement = "select currval('tb_category_id_seq') as current_id"
    )
    boolean creates(@Param("categories") List<Category> categories);


}
