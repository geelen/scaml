package org.prohax.scaml.dsl

trait Indentable {
  def mkString(depth: Int): String
}