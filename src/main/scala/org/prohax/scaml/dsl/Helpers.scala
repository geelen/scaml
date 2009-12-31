package org.prohax.scaml.dsl

object Helpers {
  def indent(depth: Int) = "  " * depth

  implicit def stringToStringTag(s: String) = new StringTag(s)
}