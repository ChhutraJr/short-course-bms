package com.example.topic03pp.repositories;

import com.example.topic03pp.utilities.Paginate;
import com.example.topic03pp.utilities.filters.BookFilter;
import com.example.topic03pp.models.Book;
import com.example.topic03pp.models.Category;
import com.example.topic03pp.repositories.providers.BookProvider;
import com.example.topic03pp.utilities.Pagination;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository {

    /*@SelectProvider(type = BookProvider.class, method = "getAllProvider")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "cate_id", property = "category.id"),
            @Result(column = "name", property = "category.name")
    })
    List<Book> getAll();*/


    //region These both work together....
    @Select("select * from tb_book")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "title", property = "title"),
            @Result(column = "id", property = "category", one = @One(select = "getCategoryByBookId"))
    })
    List<Book> getAll();

    @Select("select * from tb_category where id=#{id}")
    Category getCategoryByBookId(Integer id);
    //endregion



    @SelectProvider(type = BookProvider.class, method = "getFilterProvider")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "cate_id", property = "category.id"),
            @Result(column = "name", property = "category.name")
    })
    List<Book> getFilter(BookFilter bookFilter);





    @Select("select * from tb_book b INNER JOIN tb_category c ON b.cate_id = c.id where b.id=#{id}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "cate_id", property = "category.id"),
            @Result(column = "name", property = "category.name")
    })
    Book findOne(@Param("id") Integer id);


    @Update("update tb_book set title=#{title}, author=#{author}, publisher=#{publisher}, thumbnail=#{thumbnail}, cate_id=#{category.id} where id=#{id}")
    boolean update(Book book);

    @Delete("delete from tb_book where id=#{id}")
    boolean remove(Integer id);


    @Select("select count(*) from tb_book")
    Integer count();

    @SelectProvider(type = BookProvider.class, method = "countFilter")
    Integer countFilter(BookFilter bookFilter);


    @InsertProvider(type = BookProvider.class, method = "create")
//    @Options(useGeneratedKeys = true, keyColumn = "id"), use with h2
    @SelectKey(
            keyProperty = "id",
            keyColumn = "current_id",
            before = false,
            resultType = Integer.class,
            statementType = StatementType.PREPARED,
            statement = "select currval('tb_book_id_seq') as current_id"
    )
    boolean create(Book book);


    @Insert({
            "<script>" ,
                    "INSERT INTO tb_book (title, author, publisher, thumbnail, cate_id) values" ,
                    "<foreach collection='listBook' item='book' index='myInd' separator=','>(" ,
                    "#{book.title}" ,
                    ",#{book.author}" ,
                    ",#{book.publisher}" ,
                    ",#{book.thumbnail}" ,
                    ",#{book.category.id}" ,
                    ")</foreach>" ,
                    "</script>"
    })
//    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    boolean creates(@Param("listBook") List<Book> books);



    /*
     *
     * TODO: get book filter with Pagination class
     *
     * */

    @SelectProvider(method = "getBookFilterPaginationProvider", type = BookProvider.class)
    @Results({
            @Result(column = "book_id", property = "id"),
            @Result(column = "book_title", property = "title"),
            @Result(column = "book_author", property = "author"),
            @Result(column = "book_publisher", property = "publisher"),
            @Result(column = "book_thumbnail", property = "thumbnail"),
            @Result(column = "category_id", property = "category.id"),
            @Result(column = "category_name", property = "category.name")
    })
    List<Book> getBookFilterPagination(@Param("bookFilter") BookFilter bookFilter,@Param("pagination") Pagination pagination);


    /*
     *
     * TODO: get book filter with Paginate class
     *
     * */

//    @Select(value = "{ CALL get_book_filter_pagination(#{bookFilter.cateId}, #{bookFilter.bookTitle}, #{paginate.limit}, #{paginate.offset}) }")
    @SelectProvider(method = "getBookFilterPaginateProvider", type = BookProvider.class)
    @Results({
            @Result(column = "book_id", property = "id"),
            @Result(column = "book_title", property = "title"),
            @Result(column = "book_author", property = "author"),
            @Result(column = "book_publisher", property = "publisher"),
            @Result(column = "book_thumbnail", property = "thumbnail"),
            @Result(column = "category_id", property = "category.id"),
            @Result(column = "category_name", property = "category.name")
    })
    List<Book> getBookFilterPaginate(@Param("bookFilter") BookFilter bookFilter,@Param("paginate") Paginate paginate);







}
