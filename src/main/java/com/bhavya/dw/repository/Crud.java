package com.bhavya.dw.repository;


import java.util.ConcurrentModificationException;

import com.bhavya.dw.model.IdEntity;


public interface Crud<T extends IdEntity> {

  default T save(final T entry) {
    if (entry.getId() == 0) {
      entry.setVersion(1);
      entry.setId(insert(entry));
    } else {
      final int version = entry.getVersion();
      entry.setVersion(version + 1);
      if (update(entry) == 0) {
        throw new ConcurrentModificationException(String.format(
          "Concurrent modification for object %s %s version %s",
          entry.getClass().getName(), entry.getId(), version));
      }
    }
    return entry;
  }

  long insert(T entry);

  int update(T entry);


}
