package org.prohax.scaml.dsl

class MultiTag(is: List[Indentable]) extends Indentable {
  def mkString(depth: Int) = is.reverseMap(_.mkString(depth)).mkString("\n")

  def &(i: Indentable) = new MultiTag(i :: is)
}