package com.bhavya.dw.service;


public class RandomNameGenerator {

  public String generateName() {
    // implementation doesn't matter
    return "test" + Math.round(1000 * Math.random());
  }
}
