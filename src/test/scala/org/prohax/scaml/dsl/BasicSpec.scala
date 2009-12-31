package org.prohax.scaml.dsl

import org.specs._

object BasicSpec extends Specification {
  "The Tag class" should {
    "print nicely for empty tags" in {
      Tag("html").mkString(0) must beEqualTo("<html></html>")
      Tag("p").mkString(2) must beEqualTo("    <p></p>")
    }
    "print attributes" in {
      Tag("p", 'id -> "one").mkString(0) must beEqualTo("<p id='one'></p>")
      Tag("p", 'class -> "two").mkString(0) must beEqualTo("<p class='two'></p>")
      Tag("a", 'href -> "http://lol").mkString(0) must beEqualTo("<a href='http://lol'></a>")
    }
    "handle duplicate and empty attrs" in {
      Tag("p", 'class -> "one two").mkString(0) must beEqualTo("<p class='one two'></p>")
      Tag("p", 'class -> "one", 'class -> "two").mkString(0) must beEqualTo("<p class='two one'></p>")
      Tag("a", 'class -> "one", 'href -> "").mkString(0) must beEqualTo("<a class='one'></a>")
    }
  }
}