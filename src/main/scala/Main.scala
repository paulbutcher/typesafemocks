package com.example

object TurtleApp extends App {
  val turtle = new Turtle
  val controller = new Controller(turtle)
  
  controller.drawLine((1.0, 1.0), (2.0, 1.0))
}