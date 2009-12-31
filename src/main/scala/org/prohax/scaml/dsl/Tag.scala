package org.prohax.scaml.dsl

import org.prohax.scaml.Helpers._

class Tag(name: String) {
  def mkString(depth: Int) = {
    indent(depth) + "<" + name + "></" + name + ">"
  }
}
object Tag {
  def apply(s: String) = new Tag(s)
}