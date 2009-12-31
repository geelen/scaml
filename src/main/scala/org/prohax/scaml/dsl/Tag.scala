package org.prohax.scaml.dsl

import scala.collection.immutable.Map

import org.prohax.scaml.Helpers._

class Tag(name: String, attrs: Map[Symbol, String]) {
  def mkString(depth: Int) = {
    indent(depth) + "<" + name + "></" + name + ">"
  }
}
object Tag {
  def apply(s: String) = new Tag(s, Map[Symbol, String]())
  def apply(s: String, as: (Symbol, String)*) = {
    new Tag(s, Map(as:_*))
  }
}