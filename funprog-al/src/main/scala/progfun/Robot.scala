package fr.esgi.al.funprog

//TODO: Fix immutability errors (In Progress)
/* Implementation de la logique du Robot */
class Robot(x: Int, xMax: Int, y: Int, yMax: Int, direction: Char) {

  /* Mise a jour des coordonnees lorsque le robot a pour instruction d'avancer */
  def avancer(): fr.esgi.al.funprog.Robot = direction match {
    case 'N' => {
      if (this.y + 1 < this.yMax) {
        new Robot(this.x, this.xMax, this.y + 1, this.yMax, this.direction)
      } else {
        this
      }
    }
    case 'E' => {
      if (this.x + 1 < this.xMax) {
        new Robot(this.x + 1, this.xMax, this.y, this.yMax, this.direction)
      } else {
        this
      }
    }
    case 'W' => {
      if (this.x - 1 > 0) {
        new Robot(this.x - 1, this.xMax, this.y, this.yMax, this.direction)
      } else {
        this
      }
    }
    case 'S' => {
      if (this.y - 1 > 0) {
        new Robot(this.x, this.xMax, this.y - 1, this.yMax, this.direction)
      } else {
        this
      }
    }
    case _ => this
  }

  /* Rotation de 90degres vers la gauche ou la droite */
  def rotation(newDest: Char): fr.esgi.al.funprog.Robot = newDest match {
    case 'G' =>
      this.direction match {
        case 'N' => new Robot(this.x, this.xMax, this.y, this.yMax, 'W')
        case 'E' => new Robot(this.x, this.xMax, this.y, this.yMax, 'N')
        case 'W' => new Robot(this.x, this.xMax, this.y, this.yMax, 'S')
        case 'S' => new Robot(this.x, this.xMax, this.y, this.yMax, 'E')
      }
    case 'D' =>
      this.direction match {
        case 'N' => new Robot(this.x, this.xMax, this.y, this.yMax, 'E')
        case 'E' => new Robot(this.x, this.xMax, this.y, this.yMax, 'S')
        case 'W' => new Robot(this.x, this.xMax, this.y, this.yMax, 'N')
        case 'S' => new Robot(this.x, this.xMax, this.y, this.yMax, 'W')
      }
    case _ => this
  }
}
