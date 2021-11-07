На игровом поле находится робот. Позиция робота на поле описывается двумя целочисленным координатами: X и Y. Ось X смотрит слева направо, ось Y — снизу вверх. 

В начальный момент робот находится в некоторой позиции на поле. Также известно, куда робот смотрит: вверх, вниз, направо или налево. Ваша задача — привести робота в заданную точку игрового поля.

![Как это выглядит](https://ucarecdn.com/54490c3b-9a32-4f7b-85c2-efd12455b5c0/ "How it looks like")

```
fun main() {
    val robot = Robot(0, 0, Direction.UP)
    println("Current robot state: $robot")

    moveRobot(robot, 3, 0)

    println("Current robot state: $robot")
}
```
Ожидаемый вывод:
```
Current robot state: {x: 0, y: 0, direction: UP}
Robot turned right
Robot stepped forward
Robot stepped forward
Robot stepped forward
Current robot state: {x: 3, y: 0, direction: RIGHT}
```