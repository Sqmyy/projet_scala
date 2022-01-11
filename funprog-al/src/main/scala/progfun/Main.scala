package fr.esgi.al.funprog

import better.files._

object Main extends App {
  val f = File("src/main/resources/test")
  val lines = f.lines.toList

  println("Parsing file...")
  def processLines(lines: List[String]): Unit = lines match {
    case hd :: tl => {
      processLine(hd, acc)
      processLines(tl, acc++)
    }
    case Nil => println("EOF")  //TODO: At the end of file we stop execution
    case _   => println("EXCEPTION ! BAD FORMAT") // Is this case really useful ?
  }

  def processLine(str: String, acc: Int): Unit = {
    if(acc == 0) {
      initGrid()    //TODO: split line 0 get both int and create grid with good dimensions, also xMax and yMax
    } else if(acc % 2 == 0) {
      initRobot()   //TODO: split line 1,3,5,... to create robot with good x, y and direction
    } else {
      followInstructions()    //TODO: if line is not empty execute robot.action(line[0]) and recursive call with line.substring(1)
    }
    println(str)
  }

  processLines(lines)

}
