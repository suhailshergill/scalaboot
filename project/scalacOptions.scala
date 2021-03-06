object scalacOptions {
  import sbt._
  lazy val settings = Seq(
    Keys.scalacOptions ++= Seq("-deprecation"
                          , "-explaintypes"
                          , "-feature"
                          , "-target:jvm-1.7"
                          , "-unchecked"
                          , "-encoding", "UTF-8"
                          , "-language:higherKinds"
                          , "-Xfatal-warnings"
                          , "-Xlint"
                          , "-Yno-adapted-args"
                          , "-Ywarn-dead-code"
                          , "-Ywarn-numeric-widen"
                          , "-Ywarn-value-discard"
                          , "-Xfuture"
                        )
    // , scalacOptions in Compile ++= Seq("-Xprint-types", "-Xprint:typer")
    )
}
