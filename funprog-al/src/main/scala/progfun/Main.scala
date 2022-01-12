package fr.esgi.al.funprog

import better.files._
import com.typesafe.config.{Config, ConfigFactory}

// TODO: Fichier output en json et/ou csv
object Main extends App {

  /* Chargement fichier conf */

  val conf: Config = ConfigFactory.load()

  val input: String = conf.getString("appplication.input-file")
  val outputCSV: String = conf.getString("appplication.output-csv-file")

  /* ------------------------- */

  /* Definition variables test */
  val f = File(input)
  val lines = f.lines.toList
  val fileAsString = f.contentAsString
  val linesAsStrings = fileAsString.split('\n')
  val acc = 0

  val xMax = (linesAsStrings(0).split(' ')(0)).toInt
  val yMax = (linesAsStrings(0).split(' ')(1)).toInt

  // TODO: creation fichier output
  val fo = File(outputCSV)
  fo.createIfNotExists()
    .overwrite(
      "debut_x;debut_y;debut_direction;fin_x;fin_y;fin_direction;instructions\n"
    )

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
          processLines(tl, acc + 1, tmp)
        } else {
          val tmp = followInstructions(hd, rob)
          // TODO: Ecrire instructions dans csv
          fo.append(s";$hd\n")
          processLines(tl, acc + 1, tmp)
        }
      }
      case _ => rob
    }

  def initRobot(str: String, xMax: Int, yMax: Int): Robot = {
    val args = str.split(' ')
    // TODO: Ecriture coordonnees initiales du robot
    val data = s"${args(0)};${args(1)};${args(2)}"
    fo.append(data)
    new Robot(args(0).toInt, xMax, args(1).toInt, yMax, args(2))
  }

  def followInstructions(str: String, rob: Robot): Robot = {
    if (str.length() < 1) {
      // TODO: Ecriture coordonnees finales du robot dans csv
      fo.append(
        s";can't convert int to string atm;can't convert int to string atm;${rob.getDirection()}"
      )
      rob
    } else {
      val tmp = rob.action(str.charAt(0).toString)
      followInstructions(str.substring(1), tmp)
    }
  }

  /* ------------------------- */

  val finalRobot = processLines(lines, acc, rob)

}
