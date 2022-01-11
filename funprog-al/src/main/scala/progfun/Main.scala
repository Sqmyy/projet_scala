package fr.esgi.al.funprog

import better.files._

object Main extends App {

  /* Definition variables test */

  val f = File("src/main/resources/test")
  val lines = f.lines.toList
  val fileAsString = f.contentAsString
  val linesAsStrings = fileAsString.split('\n')
  val acc = 0

  val xMax = (linesAsStrings(0).split(' ')(0)).toInt
  val yMax = (linesAsStrings(0).split(' ')(1)).toInt

  val rob = new Robot(0, 0, 0, 0, "N")

  /* ------------------------- */

  /* Fonctions lecture de fichier d'entree */

  def processLines(lines: List[String], acc: Int, rob: Robot): Robot =
    lines match {
      case hd :: tl => {
        if (acc == 0) {
          processLines(tl, acc + 1, rob)
        } else if (acc != 0 && acc % 2 != 0) {
          val tmp = initRobot(hd, xMax, yMax)
          tmp.desc()
          processLines(tl, acc + 1, tmp)
        } else {
          val tmp = followInstructions(hd, rob)
          processLines(tl, acc + 1, tmp)
        }
      }
      case _ => rob
    }

  def initRobot(str: String, xMax: Int, yMax: Int): Robot = {
    val args = str.split(' ')
    new Robot(args(0).toInt, xMax, args(1).toInt, yMax, args(2))
  }

  def followInstructions(str: String, rob: Robot): Robot = {
    if (str.length() < 1) {
      rob
    } else {
      val tmp = rob.action(str.charAt(0).toString)
      followInstructions(str.substring(1), tmp)
    }
  }

  /* ------------------------- */

  val finalRobot = processLines(lines, acc, rob)
  // println(finalRobot.desc())

}
