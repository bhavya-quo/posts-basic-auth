package com.bhavya.dw.model;


public abstract class IdEntity {
  private long id;
  private Integer version;


  public long getId() {
    return id;
  }

  public void setId(final long id) {
    this.id = id;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(final Integer version) {
    this.version = version;
  }

}
