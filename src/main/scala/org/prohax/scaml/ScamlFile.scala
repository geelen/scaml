package org.prohax.scaml

import scala.xml.{TopScope, Xhtml, NodeSeq}

trait ScamlFile[T] {

  def render(t: T): String

  def renderString(t: T) = render(t).replaceFirst("\n","")

  def a(as: (Symbol, String)*) = if (as.isEmpty) "" else {
    as.map(x => x._1.toString + "='" + x._2 + "'").mkString(" ")
  }

  trait Sub {
//    def apply(t: Sub): Sub = apply(List(t))
    def apply(t: Seq[Sub]) = HasChildren(this, t)

    def ++(o: Seq[Sub]) = List(this) ++ o
  }
  case class HasChildren(s: Sub, children: Seq[Sub]) extends Sub {
    override def toString = s.toString + "\n" + children.mkString("\n")
  }
  case class tag(s: String, id: Option[String], classes: List[String]) extends Sub
  object tag {
    def apply(s: String) = new tag(s, None, Nil)
    def apply(s: String, id: String) = new tag(s, Some(id), Nil)
    def apply(s: String, id: String, classes: List[String]) = new tag(s, Some(id), classes)
  }
  case class text(s: String) extends Sub
  case class inlineText(s: String) extends Sub
  object inlineText {
    def apply(a: Any): inlineText = apply(a.toString)
  }
  implicit def subToString(s: Sub) = s.toString
  implicit def subsToString(s: Seq[Sub]) = s.toList.mkString("\n")
  implicit def subToSeq(s: Sub) = List(s)
}