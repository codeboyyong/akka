import AssemblyKeys._
import sbtassembly.Plugin.MergeStrategy


name := "myakkasample"

version := "1.0"

organization := "codeboyyong"

scalaVersion := "2.10.4"



resolvers ++= Seq("Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
                  "Maven Central" at "http://repo1.maven.org",
                  "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/",
                  "Sonarype releases" at "https://oss.sonatype.org/content/repositories/releases/",
                  "Spray Repo" at "http://repo.spray.io")

  libraryDependencies ++= {
    val akkaVersion = "2.3.2"
    val sprayV = "1.3.0"
    Seq(
      "io.spray"             %     "spray-can"                     %    sprayV,
      "io.spray"             %     "spray-routing"                 %    sprayV,
      "io.spray"             %     "spray-testkit"                 %    sprayV          %   "test",
      "com.typesafe.akka"    %%    "akka-actor"                    %    akkaVersion,
      "com.typesafe.akka"    %%    "akka-remote"                   %    akkaVersion,
      "com.typesafe.akka"    %%    "akka-slf4j"                    %    akkaVersion,
      "com.typesafe.akka"    %%    "akka-testkit"                  %    akkaVersion,
      "com.typesafe.akka"    %%    "akka-kernel"                   %    akkaVersion,
      "com.typesafe.akka"    %%    "akka-cluster"                  %    akkaVersion,
      "com.typesafe.akka"    %%    "akka-persistence-experimental" % akkaVersion,
      "org.json4s"           %%    "json4s-native"                 %    "3.2.4",
      "org.scalatest"        %%    "scalatest"                     %    "1.9.1"         %    "test",
      "org.scalamock"        %%    "scalamock-core"                %    "3.1.RC1"       %    "test",
      "org.scalamock"        %%    "scalamock-scalatest-support"   %    "3.1.RC1"       %    "test",
      "com.github.axel22"    %%    "scalameter"                    %    "0.4",
      "net.sourceforge.findbugs" %     "annotations"               %    "1.3.2"         %    "compile"
    )
  }

javacOptions ++= Seq("-source", "1.6", "-target", "1.6")

scalacOptions ++= Seq("-unchecked", "-deprecation")

pollInterval := 1000

shellPrompt <<= name(name => { state: State =>
	object devnull extends ProcessLogger {
		def info(s: => String) {}
		def error(s: => String) { }
		def buffer[T](f: => T): T = f
	}
	val current = """\*\s+(\w+)""".r
	def gitBranches = ("git branch --no-color" lines_! devnull mkString)
	"%s:%s>" format (
		name,
		current findFirstMatchIn gitBranches map (_.group(1)) getOrElse "-"
	)
})

fork := true

fork in Test := false

javaOptions += "-Xmx2G"

parallelExecution := true

parallelExecution in Test := false
