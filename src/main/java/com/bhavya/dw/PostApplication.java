package com.bhavya.dw;

import ch.qos.logback.core.db.dialect.PostgreSQLDialect;
import com.bhavya.dw.resources.PostsResource;
import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.flyway.FlywayBundle;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.h2.H2DatabasePlugin;
import org.jdbi.v3.guava.GuavaPlugin;
import org.jdbi.v3.postgres.PostgresPlugin;
import ru.vyarus.dropwizard.guice.GuiceBundle;
import ru.vyarus.guicey.jdbi3.JdbiBundle;


public class PostApplication extends Application<PostsConfiguration> {

  public static void main(final String[] args) throws Exception {
    new PostApplication().run(args);
  }

  @Override
  public String getName() {
    return "posts-basic-auth";
  }

  @Override
  public void initialize(final Bootstrap<PostsConfiguration> bootstrap) {
    // Add Guice Bundle
    bootstrap.addBundle(
      GuiceBundle.builder()
      .enableAutoConfig(PostApplication.class.getPackage().getName())
      .bundles(
        JdbiBundle.<PostsConfiguration>forDatabase((conf,env) -> conf.getDataSourceFactory())
        .withPlugins(new H2DatabasePlugin()))
      .build());
    // initialize database
    bootstrap.addBundle(new FlywayBundle<PostsConfiguration>(){
      @Override public PooledDataSourceFactory getDataSourceFactory(final PostsConfiguration postsConfiguration) {
        return postsConfiguration.getDataSourceFactory();
      }
    });
  }

  @Override
  public void run(final PostsConfiguration configuration,
                  final Environment environment) {
    // TODO: implement application
//    Jdbi jdbi = Jdbi.create("jdbc:postgresql://host:port/database")
//                    .installPlugin(new PostgresPlugin());
//    final JdbiFactory factory = new JdbiFactory();
//    final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(),"postgresql");
//    jdbi.installPlugin(new GuavaPlugin());
//    environment.jersey().register(new PostsResource(jdbi));
  }

}
