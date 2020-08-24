package com.bhavya.dw.dao;


import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;


public interface PostDAO {
  @SqlUpdate("create table post (postid varchar primary key, title varchar(100), body varchar(500)")
  void createPostTable();

  @SqlUpdate("insert into post (postid, title, body) values (:postid, :title, :body)")
  void insert(@Bind("postid") String postId, @Bind("title") String title, @Bind("body") String body);

  @SqlQuery("select title from post where postid = :postid")
  String findTitleById(@Bind("postid") int postid);
}

