package com.bhavya.dw.repository;


import javax.inject.Inject;

import java.util.List;

import com.bhavya.dw.model.User;
import com.bhavya.dw.repository.mapper.bind.UserBind;
import com.bhavya.dw.service.RandomNameGenerator;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlCall;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import ru.vyarus.guicey.jdbi3.installer.repository.JdbiRepository;
import ru.vyarus.guicey.jdbi3.tx.InTransaction;


@JdbiRepository
@InTransaction
public interface UserRepository extends Crud<User>{

  @Inject
  RandomNameGenerator getGenerator();

  default User createRandomUser() {
    final User user = new User();
    user.setName(getGenerator().generateName());
    save(user);
    return user;
  }

  @Override
  @SqlUpdate("insert into users (name, version) values (:name, :version)")
  @GetGeneratedKeys
  long insert(@UserBind User entry);

  @Override
  @SqlUpdate("update users set version=:version, name=:name where id=:id and version")
  int update(@UserBind User entry);

  @SqlQuery("select * from users")
  List<User> findAll();

  @SqlQuery("select * from users where name = :name")
  User findByName(@Bind("name") String name);

  @SqlUpdate("CREATE TABLE users (\n" +
              "  id IDENTITY NOT NULL,\n" +
              "  version INTEGER NOT NULL,\n" +
              "  name VARCHAR,\n" +
              "  CONSTRAINT users_id PRIMARY KEY (id)\n" +
              ")\n")
  void setup();

  @SqlUpdate("DELETE from users where id=:id")
  void delete(@Bind("id") Integer id);
}
