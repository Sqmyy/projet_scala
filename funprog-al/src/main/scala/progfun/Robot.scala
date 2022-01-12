package fr.esgi.al.funprog

/* Implementation de la logique du Robot */
class Robot(x: Int, xMax: Int, y: Int, yMax: Int, direction: String) {

  /* Simplement pour check les valeurs des attributs du robot */
  // def desc(): String = {
  //   s"${this.x};${this.y};${this.direction}"
  // }

  def getDirection(): String = {
    this.direction
  }

  /* Mise a jour des coordonnees lorsque le robot a pour instruction d'avancer */
  def avancer(): fr.esgi.al.funprog.Robot = direction match {
    case "N" => {
      if (this.y + 1 <= this.yMax) {
        new Robot(this.x, this.xMax, this.y + 1, this.yMax, this.direction)
      } else {
        this
      }
    }
    case "E" => {
      if (this.x + 1 <= this.xMax) {
        new Robot(this.x + 1, this.xMax, this.y, this.yMax, this.direction)
      } else {
        this
      }
    }
    case "W" => {
      if (this.x - 1 >= 0) {
        new Robot(this.x - 1, this.xMax, this.y, this.yMax, this.direction)
      } else {
        this
      }
    }
    case "S" => {
      if (this.y - 1 >= 0) {
        new Robot(this.x, this.xMax, this.y - 1, this.yMax, this.direction)
      } else {
        this
      }
    }
    case _ => this
  }

  /* Rotation de 90degres vers la gauche ou la droite */
  def rotation(side: String): fr.esgi.al.funprog.Robot = side match {
    case "G" =>
      this.direction match {
        case "N" => new Robot(this.x, this.xMax, this.y, this.yMax, "W")
        case "E" => new Robot(this.x, this.xMax, this.y, this.yMax, "N")
        case "W" => new Robot(this.x, this.xMax, this.y, this.yMax, "S")
        case "S" => new Robot(this.x, this.xMax, this.y, this.yMax, "E")
      }
    case "D" =>
      this.direction match {
        case "N" => new Robot(this.x, this.xMax, this.y, this.yMax, "E")
        case "E" => new Robot(this.x, this.xMax, this.y, this.yMax, "S")
        case "W" => new Robot(this.x, this.xMax, this.y, this.yMax, "N")
        case "S" => new Robot(this.x, this.xMax, this.y, this.yMax, "W")
      }
    case _ => this
  }

  def action(action: String): Robot = action match {
    case "G" => this.rotation("G")
    case "D" => this.rotation("D")
    case "A" => this.avancer()
    case _   => this
  }
}
