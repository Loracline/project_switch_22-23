package org.switch2022.project.utils;

public enum Effort {

  ONE(1),
  TWO(2),
  THREE(3),
  FIVE(5),
  EIGHT(8),
  THIRTEEN(13),
  THIRTY_FOUR(34);

  final int effortValue;

  Effort(int effortValue) {
    this.effortValue = effortValue;
  }
}