scalacOptions ++= Seq("-deprecation"
                      , "-explaintypes"
                      , "-feature"
                      , "higherKinds"
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
                    )

// scalacOptions in Compile ++= Seq("-Xprint-types", "-Xprint:typer")
