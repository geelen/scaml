package org.prohax.scaml.dsl

import scala.collection.immutable.Map

import org.prohax.scaml.dsl.Helpers._

class Tag(name: String, attrs: Map[Symbol, String], inner: Option[Either[String, Indentable]]) extends Indentable {
  def mkString(depth: Int) = {
    indent(depth) + "<" + name + printAttrs + ">" + printInner(depth) + "</" + name + ">"
  }

  def apply(s: String) = new Tag(name, attrs, Some(Left(s)))

  def apply(i: Indentable) = new Tag(name, attrs, Some(Right(i)))

  def &(i: Indentable) = new MultiTag(List(i, this))

  protected def printAttrs = if (attrs.isEmpty) "" else {
    " " + attrs.map(x => x._1.name + "='" + x._2 + "'").mkString(" ")
  }

  protected def printInner(depth: Int) = inner match {
    case None => ""
    case Some(Left(x)) => x
    case Some(Right(y)) => "\n" + y.mkString(depth + 1) + "\n" + indent(depth)
  }
}

object Tag {
  def apply(s: String) = create(s, Map[Symbol, String](), None)

  def apply(s: String, as: (Symbol, String)*) = {
    val attrMap = as.toList.foldLeft(Map[Symbol, String]())((map, x) => {
      val v = x._1 -> (x._2 :: map.get(x._1).toList).mkString(" ")
      if (v._2.isEmpty) map else map + v
    })
    create(s, attrMap, None)
  }

  private def create(name: String, attrs: Map[Symbol, String], inner: Option[Either[String, Indentable]]) = {
    if (SELF_CLOSING_TAGS.contains(name)) {
      new Tag(name, attrs, inner) {
        override def mkString(depth: Int) = {
          if (inner.isEmpty) {
            indent(depth) + "<" + name + printAttrs + " />"
          } else super.mkString(depth)
        }
      }
    } else new Tag(name, attrs, inner)
  }
}