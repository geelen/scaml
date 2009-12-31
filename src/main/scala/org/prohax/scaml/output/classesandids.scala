package org.prohax.scaml.output

import scala.xml._
import org.prohax.scaml.ScamlFile

import org.prohax.scaml.models._
import org.prohax.scaml.dsl._
import org.prohax.scaml.dsl.Helpers._

object classesandids extends ScamlFile[Unit] {
  override def headers = """<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">"""

  def render(t:Unit) = {
    Tag("html") {
      Tag("head") {
        Tag("title")
      } &
      Tag("body") {
        Tag("div", 'id -> "first") {
          Tag("h1", 'class -> "megaBig") &
          Tag("div", 'class -> "super win", 'id -> "name") {
            Tag("p", 'class -> "thin") {
              Tag("strong")
            } &
            Tag("p", 'class -> "wide") &
            Tag("br")
          }
        } &
        Tag("div", 'id -> "last")
      }
    }
  }
}