package com.bhavya.dw.repository.mapper.bind;

import com.bhavya.dw.model.User;
import org.jdbi.v3.core.statement.SqlStatement;
import org.jdbi.v3.sqlobject.customizer.SqlStatementCustomizerFactory;
import org.jdbi.v3.sqlobject.customizer.SqlStatementCustomizingAnnotation;
import org.jdbi.v3.sqlobject.customizer.SqlStatementParameterCustomizer;

import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;

/**
 * It's not installed by any guicey installer because DBI recognize annotations directly from usage.
 *
 * @author Vyacheslav Rusakov
 * @since 01.11.2018
 */
@SqlStatementCustomizingAnnotation(UserBind.UserBinder.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface UserBind {

  class UserBinder implements SqlStatementCustomizerFactory {

    @Override
    public SqlStatementParameterCustomizer createForParameter(Annotation annotation,
                                                              Class<?> sqlObjectType,
                                                              Method method,
                                                              Parameter param,
                                                              int index,
                                                              Type paramType) {
      return (stmt, obj) -> {
        User arg = (User) obj;
        ((SqlStatement) stmt)
          .bind("id", arg.getId())
          .bind("version", arg.getVersion())
          .bind("name", arg.getName());
      };
    }
  }
}
