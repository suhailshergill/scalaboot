object sbtAvro {
  import sbt._
  import sbtavro.SbtAvro._

  private lazy val deps = Seq(
    "com.twitter" %% "scalding-core" % "0.10.0"
    , "com.twitter" %% "scalding-avro" % "0.10.0"
    )


  lazy val settings = avroSettings ++ Seq(
    Keys.resolvers += "sbt-plugin-releases" at
      "http://repo.scala-sbt.org/scalasbt/sbt-plugin-releases"
    , Keys.libraryDependencies ++= deps
    , Keys.version in avroConfig := "1.7.5" // remove this if you want cdh5 avro
                                            // to be pulled in
    , stringType in avroConfig := "String"
    )
}
