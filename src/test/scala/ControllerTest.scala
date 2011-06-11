package com.example
 
import org.scalatest.Suite
import com.borachio.scalatest.MockFactory
import scala.math.{Pi, sqrt}
 
class ControllerTest extends Suite with MockFactory {
 
  val mockTurtle = new MockTurtle(this)
  val controller = new Controller(mockTurtle)
 
  def testDrawLine() {
    inSequence {
      mockTurtle.expects.getPosition.returning(0.0, 0.0)
      mockTurtle.expects.getAngle.returning(0.0)
      mockTurtle.expects.penUp
      mockTurtle.expects.turn(~(Pi / 4))
      mockTurtle.expects.forward(~sqrt(2.0))
      mockTurtle.expects.getAngle.returning(Pi / 4)
      mockTurtle.expects.turn(~(-Pi / 4))
      mockTurtle.expects.penDown
      mockTurtle.expects.forward(1.0)
    }
 
    controller.drawLine((1.0, 1.0), (2.0, 1.0))
  }
}