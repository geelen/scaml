package org.prohax.scaml.dsl

import org.specs._

object BasicSpec extends Specification {
  "The Tag class" should {
    "print nicely for empty tags" in {
      Tag("html").mkString(0) must beEqualTo("<html></html>")
      Tag("p").mkString(2) must beEqualTo("    <p></p>")
    }
  }
}