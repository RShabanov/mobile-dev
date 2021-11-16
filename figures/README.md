Используя как основу проект  https://github.com/ipetrushin/Figures , дополните классы Rect, Circle, Square необходимой функциональностью:

* реализуйте интерфейс Transforming
* осуществите наследование от Figure

В классе Main проверьте работоспособность методов: поворот, перемещение и масштабирование фигур

Результат:
```
Rectangle { x: 0, y: 0, width: 1, height: 1 } - area: 1.0
Circle { x: 0, y: 0, radius: 5 } - area: 78.53982

Rectangle before resize(2): Rectangle { x: 0, y: 3, width: 1, height: 2 }
Rectangle after resize(2): Rectangle { x: 0, y: 3, width: 2, height: 4 }

Rectangle before rotate(RotateDirection.Clockwise, 0, 0): Rectangle { x: 0, y: 3, width: 2, height: 4 }
Rectangle after rotate(RotateDirection.Clockwise, 0, 0): Rectangle { x: 3, y: 0, width: 4, height: 2 }

Circle before resize(2): Circle { x: 0, y: 0, radius: 3 }
Circle after resize(2): Circle { x: 0, y: 0, radius: 6 }

Circle before rotate(RotateDirection.CounterClockwise, 0, 3): Circle { x: 0, y: 0, radius: 6 }
Circle after rotate(RotateDirection.CounterClockwise, 0, 3): Circle { x: 3, y: 3, radius: 6 }
```