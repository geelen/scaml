package org.prohax.scaml.dsl

import xml._

object Tag {
  def apply(s: String) = Elem(null, s, Null, TopScope)
}