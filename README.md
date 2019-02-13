# laboratorio4
arsw
### Mateo Guzman
### Italo Novoa


## Parte 1


What is the reason of this CPU consumption?

![image](https://user-images.githubusercontent.com/42522754/52665794-7c9e2d00-2eda-11e9-99a0-8822eeabf410.png)


Ya que tenemos la clase (hilo) que siempre esta realizando una consulta a una cola y esto genera un uso de la CPU.

What is the class responsable of that consumption?
La clase Consumer.

## Parte 2
##### 2
La cantidad total de puntos de vida es igual a la cantidad de inmortales iniciales por 100

##### 3 Run the application and verify how the "pause and check" option works, is the invariant satisfied?


No ya que no hay un control para que dos  o mas inmortales cogan al mismo tiempo a un tercero modificando asi la cantidad de vida total positivamente

##### 5 erify again the functionality clicking many times. Is the invariant satisfied?

No ya que aun no se implementa el control de syncronizacion sobre las zonas criticas
