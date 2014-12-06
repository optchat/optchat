//----------------------------------------
// Setup Jacoco test coverage.
// Invocation: jacoco:cover
// Results: target/jacoco/html/index.html
// See: https://github.com/sbt/jacoco4sbt
//      project/plugins.sbt
//----------------------------------------

jacoco.settings

parallelExecution in jacoco.Config := false

jacoco.outputDirectory in jacoco.Config := file("target/jacoco")

jacoco.includes in jacoco.Config := Seq(
  "controllers*",
  "models*"
)

jacoco.excludes in jacoco.Config := Seq(
  "controllers*javascript*",
  "controllers*ref*",
  "controllers*routes*",
  "controllers*Assets*"
)