package com.github.princesslana.smalld.start;

import com.github.princesslana.smalld.SmallD;

public class PingBot {
  public static void main(String[] args) {
    try (SmallD smalld = SmallD.create(System.getProperty("smalld.token"))) {
      smalld.run();
    }
  }
}
