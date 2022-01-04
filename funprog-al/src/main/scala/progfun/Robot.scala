package fr.esgi.al.funprog

//TODO: Fix immutability errors (every variable has one)
/* Implementation de la logique du Robot */
class Robot(x: Int, xMax: Int, y: Int, yMax: Int, direction: Char) {

  def setDirection(c: Char): Char = {
    this.direction = c
  }

  /* Mise a jour des coordonnees lorsque le robot a pour instruction d'avancer */
  def avancer(): Boolean = direction match {
    case 'N' => {
      if (this.y + 1 < this.yMax) {
        this.y += 1
        true
      } else {
        false
      }
    }
    case 'E' => {
      if (this.x + 1 < this.xMax) {
        this.x += 1
        true
      } else {
        false
      }
    }
    case 'W' => {
      if (this.x - 1 > 0) {
        this.x -= 1
        true
      } else {
        false
      }
    }
    case 'S' => {
      if (this.y - 1 > 0) {
        this.y -= 1
        true
      } else {
        false
      }
    }
    case _ => false
  }

  /* Rotation de 90degres vers la gauche ou la droite */
  def rotation(newDest: Char): Char = newDest match {
    case 'G' =>
      this.direction match {
        case 'N' => this.setDirection('W')
        case 'E' => this.setDirection('N')
        case 'W' => this.setDirection('S')
        case 'S' => this.setDirection('E')
      }
    case 'D' =>
      this.direction match {
        case 'N' => this.setDirection('E')
        case 'E' => this.setDirection('S')
        case 'W' => this.setDirection('N')
        case 'S' => this.setDirection('W')
      }
    case _ => break
  }
}
