package org.prohax.scaml

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import scala.collection.mutable.Stack

import org.prohax.scaml.dsl._

class TagUnitSpec extends FlatSpec with ShouldMatchers {
  "A Tag" should "print nicely" in {
    Tag("p").toString should be ("<p></p>")
  }
}