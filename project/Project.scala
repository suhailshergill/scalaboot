import sbt._
import Keys._
import LogSettings._

//TODO change the name of the object to reflect your project name.
object ScalabootBuild extends Build {
  val PROJECT_NAME = "scalaboot" //TODO change this!
  val HADOOP_JOBRUNNER = "sss.scalding.JobRunner"

  lazy val commonResolvers = Seq(
    "Maven.org" at "http://repo1.maven.org/maven2",
    "Sun Maven2 Repo" at "http://download.java.net/maven/2",
    "Scala-Tools" at "http://scala-tools.org/repo-releases/",
    "Sun GF Maven2 Repo" at "http://download.java.net/maven/glassfish",
    "Oracle Maven2 Repo" at "http://download.oracle.com/maven",
    "Sonatype" at "http://oss.sonatype.org/content/repositories/release",
    "spy" at "http://files.couchbase.com/maven2/",
    "Twitter" at "http://maven.twttr.com/"
  )

  lazy val commonDeps = Seq(
    "org.scalaz" %% "scalaz-core" % "7.0.6",
    "com.chuusai" % "shapeless" % "2.0.0" cross CrossVersion.full,
    "org.scalatest" %% "scalatest" % "2.1.6" % "test,it",
    "junit" % "junit" % "4.10" % "test,it",
    "org.mockito" % "mockito-core" % "1.9.0" % "test,it"
  )

  lazy val hadoopResolvers = Seq( // scalding, cascading etc
    "Concurrent Maven Repo" at "http://conjars.org/repo",
    "clojars.org" at "http://clojars.org/repo")

  lazy val hadoopDeps = Seq(
    "com.twitter" %% "scalding-commons" % "0.10.0"
    // "org.apache.hadoop" % "hadoop-core" % "2.3.0-mr1-cdh5.0.1" % "provided",
  )

  lazy val defaultSettings = Defaults.itSettings ++
    logSettings ++
    sbtCompilerPlugins.settings ++
    sbtStartScript.settings ++
    sbtScalariform.settings ++
    Seq(
      libraryDependencies ++= commonDeps,
      libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value,
      resolvers ++= commonResolvers,
      retrieveManaged := true,
      publishMavenStyle := true,
      organization := "sss",
      version := "0.1-SNAPSHOT",
      scalaVersion := "2.10.4"
    )

  def defaultProject: Project => Project = _.
    configs(IntegrationTest).
    settings(defaultSettings: _*).
    settings(testOptions in IntegrationTest := Seq(Tests.Filter(s => s.contains("Test")))).
    settings(parallelExecution in IntegrationTest := false)


  lazy val hadoopSettings = defaultSettings ++
    sbtAssembly.settings(HADOOP_JOBRUNNER) ++
    sbtAvro.settings ++
    Seq(
      resolvers ++= hadoopResolvers
      , libraryDependencies ++= hadoopDeps
    )

  lazy val root = defaultProject(Project(PROJECT_NAME, file(".")))
    .aggregate(core, scalding)

  lazy val core = defaultProject(Project(PROJECT_NAME+"-core", file(PROJECT_NAME+"-core")))

  lazy val scalding = defaultProject(Project(s"${PROJECT_NAME}-scalding", file(s"${PROJECT_NAME}-scalding")))
    .settings(hadoopSettings: _*)
    .dependsOn(core)
}
