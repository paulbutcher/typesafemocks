import sbt._ 
 
class Project(info: ProjectInfo) extends DefaultProject(info) { 
  val scalaToolsSnapshots = ScalaToolsSnapshots

  val scalatest = "org.scalatest" %% "scalatest" % "1.4.1"
  val borachioLibrary = "com.borachio" %% "borachio-library" % "2.0.SNAPSHOT"
  val borachioPlugin = "com.borachio" %% "borachio-plugin" % "2.0.SNAPSHOT"

  def managedSources = "src_managed"
  def managedTestSource = managedSources / "test" / "scala"
  override def testSourceRoots = super.testSourceRoots +++ (managedTestSource##)
  override def cleanAction = super.cleanAction dependsOn cleanTask(managedSources)
  
  def generateMocksSourceRoots = "src" / "generate_mocks" / "scala"
  def generateMocksSources = sources(generateMocksSourceRoots)
  
  def generateMockCompileOptions = 
    compileOptions(
      "-Xplugin:lib_managed/scala_2.9.0/compile/borachio-plugin_2.9.0-2.0.SNAPSHOT.jar",
      "-Xplugin-require:borachio",
      "-Ylog:generatemocks",
      "-P:borachio:generatemocks:src_managed/test/scala"
    ) ++ super.compileOptions
  
	class GenerateMocksCompileConfig extends TestCompileConfig
	{
		override def baseCompileOptions = generateMockCompileOptions
		override def label = "generatemocks"
    override def sourceRoots = generateMocksSourceRoots
		override def sources = generateMocksSources
	}
	def generateMocksCompileConfiguration = new GenerateMocksCompileConfig
	lazy val generateMocksCompileConditional = new CompileConditional(generateMocksCompileConfiguration, buildCompiler)

  lazy val generateMocks = generateMocksAction
  def generateMocksAction = generateMocksTask dependsOn(compileAction) describedAs "Generates sources for classes with the @mock annotation"
  def generateMocksTask = task {
    generateMocksCompileConditional.run
    None
  }
}