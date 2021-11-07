fun main() {
    val robot = Robot(0, 0, Direction.UP)
    println("Current robot state: $robot")

    moveRobot(robot, 3, 0)

    println("Current robot state: $robot")
}

