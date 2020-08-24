package com.bhavya.dw.cli;


import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Post {

  private String postId;
  private String title;
  private String body;

  public Post(final String title, final String body) {
    this.title = title;
    this.body = body;
    this.postId = UUID.randomUUID().toString();
  }

  @JsonProperty
  public String getPostId() {
    return postId;
  }

  public void setPostId(final String postId) {
    this.postId = postId;
  }

  @JsonProperty
  public String getTitle() {
    return title;
  }

  public void setTitle(final String title) {
    this.title = title;
  }

  public String getBody() {
    return body;
  }

  public void setBody(final String body) {
    this.body = body;
  }
}
