package org.prohax.scaml.dsl

import scala.collection.immutable.Map

import org.prohax.scaml.dsl.Helpers._

class Tag(name: String, attrs: Map[Symbol, String]) {
  def mkString(depth: Int) = {
    indent(depth) + "<" + name + printAttrs + "></" + name + ">"
  }

  private def printAttrs = if (attrs.isEmpty) "" else {
    " " + attrs.map(x => x._1.name + "='" + x._2 + "'").mkString(" ")
  }
}
object Tag {
  def apply(s: String) = new Tag(s, Map[Symbol, String]())
  
  def apply(s: String, as: (Symbol, String)*) = {
    val attrs: List[(Symbol, String)] = as.toList
    new Tag(s, attrs.foldLeft(Map[Symbol,String]())((map, x) => {
      val v = x._1 -> (x._2 :: map.get(x._1).toList).mkString(" ")
      if (v._2.isEmpty) map else map + v 
    }))
  }
}