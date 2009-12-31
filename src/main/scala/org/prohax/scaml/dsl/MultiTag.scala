package org.prohax.scaml.dsl

import scala.collection.mutable.ArrayBuffer

class MultiTag(is: List[Indentable]) extends Indentable {
  def mkString(depth: Int) = is.reverseMap(_.mkString(depth)).mkString("\n")

  def &(i: Indentable) = new MultiTag(i :: is)
}

object MultiTag {
  def apply(is: Seq[Indentable]) = new MultiTag(is.reverse.toList)
}