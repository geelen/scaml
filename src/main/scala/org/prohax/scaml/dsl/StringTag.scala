package org.prohax.scaml.dsl

import Helpers._

class StringTag(s: String) extends Indentable {
  def mkString(depth: Int) = indent(depth) + s

  def &(i: Indentable) = new MultiTag(List(i, this))
}
