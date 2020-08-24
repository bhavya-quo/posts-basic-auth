package com.bhavya.dw.resources;


import static java.util.Optional.of;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.Arrays;

import com.bhavya.dw.cli.Post;
import com.google.common.collect.ImmutableList;


@Path("/posts")
@Produces(MediaType.APPLICATION_JSON)
public class PostsResource {

  @GET
  public ImmutableList<Post> getPosts() {

    return (ImmutableList<Post>) ImmutableList.of(new Post("Sample title","Sample Body"),new Post("Second","I am a post"));
  }
}
