package org.prohax.scaml.dsl

import scala.collection.immutable.Map

import org.prohax.scaml.dsl.Helpers._

trait Indentable {
  def mkString(depth: Int): String
}

class Tag(name: String, attrs: Map[Symbol, String], inner: Option[Either[String, Indentable]]) extends Indentable {
  def mkString(depth: Int) = {
    indent(depth) + "<" + name + printAttrs + ">" + printInner(depth) + "</" + name + ">"
  }

  def apply(s: String) = new Tag(name, attrs, Some(Left(s)))

  def apply(i: Indentable) = new Tag(name, attrs, Some(Right(i)))

  private def printAttrs = if (attrs.isEmpty) "" else {
    " " + attrs.map(x => x._1.name + "='" + x._2 + "'").mkString(" ")
  }

  private def printInner(depth: Int) = inner match {
    case None => ""
    case Some(Left(x)) => x
    case Some(Right(y)) => "\n" + y.mkString(depth + 1) + "\n" + indent(depth)
  }
}
object Tag {
  def apply(s: String) = new Tag(s, Map[Symbol, String](), None)

  def apply(s: String, as: (Symbol, String)*) = {
    val attrs: List[(Symbol, String)] = as.toList
    new Tag(s, attrs.foldLeft(Map[Symbol, String]())((map, x) => {
      val v = x._1 -> (x._2 :: map.get(x._1).toList).mkString(" ")
      if (v._2.isEmpty) map else map + v
    }), None)
  }
}