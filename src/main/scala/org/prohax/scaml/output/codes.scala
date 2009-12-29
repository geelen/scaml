package org.prohax.scaml.output

import scala.xml._
import org.prohax.scaml.ScamlFile

import org.prohax.scaml.models._

object codes extends ScamlFile[Unit] {
  def render(t: Unit) = {
    tag("p") {
      text("Counting to three:") ++
      tag("ul") {
        (1 to 3).map(i => {
          tag("li") { inlineText(i) }
        })
      }
    } ++
    tag("p") {
      text("First 9 squares:") ++
      tag("ul") {
        (1 to 3).map(i => {
          tag("li") { inlineText(i * i) }
        })
      }
    } ++
    tag("p") {
      text("Nesting times:") ++
      tag("table", None, Nil) {
        List((1, 2), (3, 4), (5, 6)).map {x => {
          tag("tr") {
            tag("td") { inlineText(x._1) } ++
            (1 to x._2).map {i => {
              tag("td") { inlineText(i) }
            }}
          }
        }}
      }
    }
  }
}