package org.prohax.scaml.dsl

import org.specs._

import Helpers._

class BranchingSpec extends Specification {
  val cond1 = true
  val cond2 = false

  "The dsl" should {
    "have a sweet conditional syntax" in {
      Tag("p", 'class -> ?(cond1, "cls1")).mkString(0) must beEqualTo("<p class='cls1'></p>")
      Tag("p", 'class -> ?(cond2, "cls2")).mkString(0) must beEqualTo("<p></p>")
      Tag("p", 'class -> ?("hello".length > 5, "cls1", "cls2")).mkString(0) must beEqualTo("<p class='cls2'></p>")
    }
    "do some branching" in {
      def tags(username: Option[String]) = {
        Tag("div", 'class -> "main") {
          if (username.isEmpty) {
            Tag("p", 'class -> "unauthorised") { "Not Permitted!" }
          } else {
            Tag("h1") { "Welcome, " + username.get } &
            Tag("p") { "enjoy your stay."}
          }
        }
      }

      tags(None).mkString(0) must beEqualTo(
"""<div class='main'>
  <p class='unauthorised'>Not Permitted!</p>
</div>""")
      tags(Some("Glen")).mkString(0) must beEqualTo("""<div class='main'>
  <h1>Welcome, Glen</h1>
  <p>enjoy your stay.</p>
</div>""")
    }
  }
}