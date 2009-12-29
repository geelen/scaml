import sbt._

class ScamlProject(info: ProjectInfo) extends DefaultProject(info) {
  import BasicScalaProject._
  
  val specs = "org.scala-tools.testing" % "specs" % "1.6.1-2.8.0.Beta1-RC5"

  val newReleaseToolsRepository = "Scala Tools Repository" at "http://nexus.scala-tools.org/content/repositories/snapshots/"

  val scalatest = "org.scalatest" % "scalatest" % "1.0.1-for-scala-2.8.0.Beta1-RC5-with-test-interfaces-0.2-SNAPSHOT" % "test" 
}