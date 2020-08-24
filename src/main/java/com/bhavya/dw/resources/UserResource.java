package com.bhavya.dw.resources;


import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;
import java.util.OptionalInt;
import java.util.OptionalLong;

import com.bhavya.dw.model.User;
import com.bhavya.dw.repository.UserRepository;
import com.google.common.base.Preconditions;
import com.google.inject.Inject;


@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

  private final UserRepository repository;

  @Inject
  public UserResource(final UserRepository repository) {
    this.repository = repository;
  }

  @POST
  @Path("/")
  public User create(String name) {
    Preconditions.checkState(repository.findByName(name) == null,
                             "User with name %s already exists", name);
    User user = new User();
    user.setName(name);
    return repository.save(user);
  }

  @PUT
  public void update(User user) {
    repository.save(user);
  }

  @GET
  public List<User> findAll() {
    return repository.findAll();
  }

  @GET
  @Path("/setup")
  public String setup() {
    repository.setup();
    return "ok";
  }

  @GET
  @Path("/random")
  public User randomUser() {
    return repository.createRandomUser();
  }
  @DELETE
  @Path("/{id}")
  public String randomUser(@PathParam("id") OptionalInt id) {
    repository.delete(id.getAsInt());
    return "deleted";
  }
}
