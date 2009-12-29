package org.prohax.scaml

import scala.xml.{TopScope, Xhtml, NodeSeq}

trait ScamlFile[T] {
  def render(t: T): String

  def renderString(t: T) = render(t).replaceFirst("\n","")

  def a(as: (Symbol, String)*) = if (as.isEmpty) "" else {
    as.map(x => x._1.toString + "='" + x._2 + "'").mkString(" ")
  }
}