import kotlin.math.absoluteValue

enum class Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT,
}

class Robot(_x: Int, _y: Int, dir: Direction) {
    var x: Int = _x
        private set

    var y: Int = _y
        private set

    var direction: Direction = dir
        private set

    fun moveTo(toX: Int, toY: Int) {
        moveX(toX)
        moveY(toY)
    }

    private fun moveX(toX: Int) {
        if (toX != x) {

            val targetDir = if (x < toX) {
                Direction.RIGHT
            } else if (x > toX) {
                Direction.LEFT
            } else {
                direction
            }

            turnTo(targetDir)
            while (x != toX) forward()
        }
    }

    private fun moveY(toY: Int) {
        if (toY != y) {
            val targetDir = if (y < toY) {
                Direction.UP
            } else if (y > toY) {
                Direction.DOWN
            } else {
                direction
            }

            turnTo(targetDir)
            while (y != toY) forward()
        }
    }

    private fun turnTo(targetDir: Direction) {
        /***
         * Imagine directions with weights:
         * RIGHT = 0, UP = 1, LEFT = 2, DOWN = 3
         *
         * And let's count distance between directions with formula: abs(direction - targetDir) % 2
         * result:
         *  * is 1 -> two nearest directions (1 rotation)
         *  * is 0 -> two furthest directions (2 rotations)
         ***/

        val getDirectionNumber = {dir: Direction -> when (dir) {
                Direction.RIGHT -> 0
                Direction.UP -> 1
                Direction.LEFT -> 2
                Direction.DOWN -> 3
            }
        }

        when ((getDirectionNumber(direction) - getDirectionNumber(targetDir)).absoluteValue % 2) {
            0 -> {
                turnRight()
                turnRight()
            }
            1 -> {
                if (direction == Direction.UP) {
                    if (targetDir == Direction.LEFT) turnLeft()
                    else turnRight()
                } else if (direction == Direction.DOWN) {
                    if (targetDir == Direction.LEFT) turnRight()
                    else turnLeft()
                }
            }
        }
    }

    fun turnLeft() {
        println("Robot turned left")
        direction = when (direction) {
            Direction.UP -> Direction.LEFT
            Direction.DOWN -> Direction.RIGHT
            Direction.LEFT -> Direction.DOWN
            Direction.RIGHT -> Direction.UP
        }
    }

    fun turnRight() {
        println("Robot turned right")
        direction = when (direction) {
            Direction.UP -> Direction.RIGHT
            Direction.DOWN -> Direction.LEFT
            Direction.LEFT -> Direction.UP
            Direction.RIGHT -> Direction.DOWN
        }
    }

    fun forward() {
        println("Robot stepped forward")
        when (direction) {
            Direction.UP -> y += 1
            Direction.DOWN -> y -= 1
            Direction.LEFT -> x -= 1
            Direction.RIGHT -> x += 1
        }
    }

    override fun toString(): String {
        return "{x: ${x}, y: ${y}, direction: ${direction}}"
    }
}

/*
    function works just like Robot.moveTo(toX: Int, toY: Int)
    only for the task
 */
fun moveRobot(robot: Robot, toX: Int, toY: Int) {
    val turnTo = { r: Robot, targetDir: Direction ->
        val getDirectionNumber = {dir: Direction -> when (dir) {
                Direction.RIGHT -> 0
                Direction.UP -> 1
                Direction.LEFT -> 2
                Direction.DOWN -> 3
            }
        }

        when ((getDirectionNumber(r.direction) - getDirectionNumber(targetDir)).absoluteValue % 2) {
            0 -> {
                r.turnRight()
                r.turnRight()
            }
            1 -> {
                if (r.direction == Direction.UP) {
                    if (targetDir == Direction.LEFT) r.turnLeft()
                    else r.turnRight()
                } else if (r.direction == Direction.DOWN) {
                    if (targetDir == Direction.LEFT) r.turnRight()
                    else r.turnLeft()
                }
            }
        }
    }

    if (toX != robot.x) {

        val targetDir = if (robot.x < toX) {
            Direction.RIGHT
        } else if (robot.x > toX) {
            Direction.LEFT
        } else {
            robot.direction
        }

        turnTo(robot, targetDir)
        while (robot.x != toX) robot.forward()
    }

    if (toY != robot.y) {
        val targetDir = if (robot.y < toY) {
            Direction.UP
        } else if (robot.y > toY) {
            Direction.DOWN
        } else {
            robot.direction
        }

        turnTo(robot, targetDir)
        while (robot.y != toY) robot.forward()
    }
}