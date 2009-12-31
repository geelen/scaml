package org.prohax.scaml.dsl

object Helpers {
  def indent(depth: Int) = "  " * depth

  def ?(b: => Boolean, ifTrue: String): String = ?(b, ifTrue, "")

  def ?(b: => Boolean, ifTrue: String, ifFalse: String): String = if (b) ifTrue else ifFalse

  implicit def stringToStringTag(s: String) = new StringTag(s)

  implicit def seqToMultiTag(is: Seq[Indentable]) = MultiTag(is)
}