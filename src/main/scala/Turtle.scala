package com.example

import scala.math.{Pi, sin, cos}
 
class Turtle {
  def penUp() {}
  def penDown() {}
  def forward(distance: Double) { x += cos(angle) * distance; y += sin(angle) * distance }
  def turn(angle: Double) { this.angle = (this.angle + angle) % (2 * Pi) }
  def getAngle = angle
  def getPosition = (x, y)
  
  private var x = 0.0
  private var y = 0.0
  private var angle = 0.0
}