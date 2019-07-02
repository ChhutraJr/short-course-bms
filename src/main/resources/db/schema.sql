create table tb_category
(
    id serial primary key,
    name varchar,
    created_at date default now(),
    updated_at date default now()

);


create table tb_book
(
    id serial primary key,
    title varchar,
    author varchar,
    publisher varchar,
    thumbnail varchar,
    cate_id integer references tb_category(id)
);


create table tb_role
(
    id serial primary key,
    role varchar
);


create table tb_user
(
    id serial primary key,
    username varchar,
    password varchar,
    status boolean,
    profile_img varchar
);

create table tb_user_role
(
    user_id integer not null references tb_user(id),
    role_id integer not null references tb_role(id),
    primary key (user_id, role_id)
);


create function get_book_filter_pagination(cate_id_param integer, book_title_param character varying, limit_param integer, offset_param integer)
    returns TABLE(book_id integer, book_title character varying, book_author character varying, book_publiser character varying, book_thumbnail character varying, category_id integer, category_name character varying)
    language plpgsql
as $$
DECLARE
    --   your variable;
BEGIN
    if cate_id_param isnull
    then
        raise notice 'meme';
        return query select b.id     as book_id,
                            b.title  as book_title,
                            b.author as book_author,
                            b.publisher book_publiser,
                            b.thumbnail book_thumbnail,
                            c.id        category_id,
                            c.name      category_name
                     from tb_book b
                              inner join tb_category c on b.cate_id = c.id
                     where b.title ilike '%' || book_title_param || '%'
                     limit limit_param
                         offset offset_param;
    else
        return query select b.id, b.title, b.author, b.publisher, b.thumbnail, c.id, c.name
                     from tb_book b
                              inner join tb_category c on b.cate_id = c.id
                     where b.cate_id = cate_id_param
                       and b.title ilike '%' || book_title_param || '%'
                     limit limit_param
                         offset offset_param;
    end if;
END;
$$;
