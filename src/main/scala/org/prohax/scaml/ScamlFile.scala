package org.prohax.scaml

import dsl.Indentable
import scala.xml.{TopScope, Xhtml, NodeSeq}

trait ScamlFile[T] {
  def headers = ""

  def render(t: T): Indentable

  def renderString(t: T) = render(t).mkString(0)
}