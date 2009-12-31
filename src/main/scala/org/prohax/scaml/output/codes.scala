package org.prohax.scaml.output

import scala.xml._
import org.prohax.scaml.ScamlFile

import org.prohax.scaml.models._
import org.prohax.scaml.dsl._
import org.prohax.scaml.dsl.Helpers._

object codes extends ScamlFile[Unit] {
  def render(t: Unit) = {
    Tag("p") {
      "Counting to three:" &
      Tag("ul") {
        (1 to 3).map(i => {
          Tag("li") { i.toString }
        })
      }
    } &
    Tag("p") {
      "First 9 squares:" &
      Tag("ul") {
        (1 to 3).map(i => {
          Tag("li") { (i * i).toString }
        })
      }
    } &
    Tag("p") {
      "Nesting times:" &
      Tag("table") {
        List((1, 2), (3, 4), (5, 6)).map {x => {
          Tag("tr") {
            Tag("td") { x._1.toString } &
            (1 to x._2).map {i => {
              Tag("td") { i.toString }
            }}
          }
        }}
      }
    }
  }
}