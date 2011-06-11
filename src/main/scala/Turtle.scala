package com.example

import scala.math.{Pi, sin, cos}
 
class Turtle {
  def penUp() { pen = false; println("penUp - "+ this) }
  def penDown() { pen = true; println("penDown - "+ this) }
  def forward(distance: Double) { x += cos(angle) * distance; y += sin(angle) * distance; println("forward - "+ this) }
  def turn(angle: Double) { this.angle = (this.angle + angle) % (2 * Pi); println("turn - "+ this) }
  def getAngle = angle
  def getPosition = (x, y)
  
  override def toString = "Pos: "+ (x, y) +", angle: "+ angle +", pen: "+ pen

  private var pen = false
  private var x = 0.0
  private var y = 0.0
  private var angle = 0.0
}