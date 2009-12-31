package org.prohax.scaml.dsl

import org.specs._

import Helpers._

class BasicSpec extends Specification {
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
    "handle inner text" in {
      Tag("p") {"inner"}.mkString(0) must beEqualTo("<p>inner</p>")
      Tag("p", 'class -> "one", 'class -> "two") {"inner"}.mkString(2) must beEqualTo("    <p class='two one'>inner</p>")
    }
    "handle inner tags" in {
      Tag("div") {
        Tag("p") {"text"}
      }.mkString(1) must beEqualTo("  <div>\n    <p>text</p>\n  </div>")

      Tag("div") {
        Tag("h1") {
          "Something" &
          Tag("strong") { "bold" } &
          "up here."
        } &
        Tag("p") {"text"}
      }.mkString(1) must beEqualTo(
"""  <div>
    <h1>
      Something
      <strong>bold</strong>
      up here.
    </h1>
    <p>text</p>
  </div>"""
        )
    }
  }
}