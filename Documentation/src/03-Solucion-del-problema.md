# Solución del problema
Solución del problema 

Antes de comenzar a dar solución a los puntos planteados en el análisis del problema, se inició por implementar las interfaces iRegistro, iJugador, iJuego e iCentroJuego, estas fueron facilitadas por el profesor Leonardo Villalobos. Con estas interfaces se pudo acceder a diferentes funciones como extraer el nombre del jugador, registrar la puntuación, devolver el registro histórico de partidas , asignar la fecha y hora de inicio y finalización de las partidas, etc. Seguidamente se implementaron y resolvieron las siguientes etapas:  

##Etapa 1: 

En la primera etapa se le dará solución a la creación de la estructura para los registros de personas, para esto se crea la clase “Register.java”, la cual cuando el usuario lo solicite, le muestra una nueva ventana, en la cual se le pide al usuario ingresar dos datos, su nombre de usuario y su contraseña, estos son ingresados dentro de los “JTextField” los cuales mediante el método “getText” capturan los datos ingresados por teclado por el jugador, además de esto le pide al usuario que confirme su contraseña, y al capturar esta, mediante condicionales comprueba que esta confirmación sea igual a la primera contraseña ingresada, si estas no concuerdan, muestra un mensaje de error en la pantalla, de lo contrario, almacena dentro de otra clase llamada Login, en una estructura HashMap, el nuevo registro y le confirma al usuario que este se a concretado de manera correcta. 

Para la validación de usuarios, dentro de la clase Login, en el método “actionPerformed”, se captura el usuario y la contraseña proporcionadas en la ventana de inicio para ingresar a la central de juegos. El método comprueba mediante condicionales “if” que el usuario y la contraseña ingresadas en los  “JTextField” sea diferentes al nombre de usuario y contraseña de los registros almacenados en el  “HashMap” de “users”, si la condicional se cumple, muestra un mensaje en pantalla indicando que las credenciales del usuario son incorrectas. De manera contraria devuelve un “true” en la variable booleana “loginSuccess”, el método getter de esta variable es llamado en la clase “Main.java” como confirmación de validación de credenciales e inicialización del juego. 

##Etapa 2: 

La creación del Menú Central de Juegos será el problema solucionado en la etapa dos. Dentro de la clase “GameCenter.java”, se define una nueva ventana, la cual implementa las interfaces iCentroJuego, y ActionListener. Esta clase contiene además tres atributos definidos, el primero es un ArrayList de la interfaz iJugador y contiene los juegos disponibles. El siguiente es otro ArrayList de la interfaz iRegistro y contiene los records de partidas registradas, finalmente, el atributo player referenciado de la interfaz iJugador. 

En esta clase se agregan al ArrayList de “games” las clases correspondientes a los dos juegos creados (Tic Tac Toe y Memory). Además del saludo mostrado en la ventana, se muestra el nombre de este gracias a el método “getNombre” del atributo “player”. Se agregan la cantidad de botones correspondientes a cada opción de juego que se encuentre dentro de ”games”, esto mediante un ciclo for que verifica que, para cada elemento dentro del largo de la lista, se agregue un botón y mediante el método “getNombre”, escribe el nombre del juego en cada uno de los botones creados. Además de esto, crea un botón para que al ser seleccionado muestra el podio de la plataforma. 

Gracias a los métodos “getToolTipText()” y “setToolTipText()” le permite al usuario obtener una pequeña descripción del  juego que desea  seleccionar cuando se posiciona encima del botón correspondiente a este. 

Mediante el método “actionPerformed”, y el getter “getActionCommand” comprueba la selección del botón que haya presionado el usuario, y basándose en esta, muestra en pantalla el menú de inicio del juego seleccionado. 

##Etapa 3: 

En cuanto a los menús individuales para cada juego: 

- Tic Tac Toe: Dentro del método “iniciarPartida” de la clase “TicTacToe.java”, se define una nueva ventana, la cual lleva por título el nombre del juego, además muestra los botones de: 

-- PersonalStatus: contiene un HashMap definido como “playedDays” el cual recibe datos tipo LocalDate e Integer. Con un ciclo “for”, recorre los registros de juegos en el centro de juegos y mediante el método “getRegistros”, obtiene los, registros de las partidas Tic Tac Toe, luego, de igual manera con base al registro busca los registros cuyo nombre de jugador sea igual al del usuario que está solicitando el listado, esto mediante los métodos getJugador y getNombre de la interfaz register y el método getNombre de la interfaz jugador, e imprime los datos de inicio y finalización de la partida, con los métodos de la interfaz register llamados “getInicio()” y “getFinalizacion()”, también muestre el puntaje de la partida, la duración de esta y el estado de la misma, por medio de “getPuntaje()”,”getSegundosTotalesPartida()” y “getSegundosTotalesPartida()”. 

Para registrar los minutos de juego por día en promedio, se basa en la fecha de inicio de los registros y las almacena en “playerDays” , luego hace un calculo de la cantidad de tiempo jugado por día acorde con lo registrado anteriormente, dividiendo las horas entre 3600, los minutos equivalen al modular del total de horas, dividido entre 60, y los segundos al modular del total de horas dividido entre 60; esto se imprime en un label colocado en la parte superior del programa en el formato horas,minutos y segundos.  

La ventana de “PersonalStatus” permite al usuario abandonar la ventana pulsando sobre el botón inferior que dice “Back”, el cual contiene la función “dispose()” la cual cierra la ventana actual. 

-- El botón de GeneralStatus, permite al usuario visualizar en pantalla las 10 primeras partidas con el mejor puntaje, esto lo hace gracias a una lista referenciada de la interfaz registro, llamada registers, la cual corresponde al registro que se almacene en la central del juego correspondiente al juego del TicTacToe. 

Se hace uso de la interfaz “Comparator” de la implementación de la clase “Util” , para aplicar un ordenamiento mediante el método “sort” de los registros, comparando dos registros, y obteniendo el mayor de estos.  

Luego mediante un ciclo “for” comprueba que i sea menor a 10, e imprime cada registro en pantalla hasta que la condicional deje de cumplirse. 

De igual forma existe un botón de salida de esta ventana permitiendo al usuario regresar al menú individual del juego. 

-- En este juego también se le muestra al usuario un mensaje en la ventana que lo incita a escoger el símbolo con el que desea jugar la nueva partida, estos están colocados sobre botones y corresponden a las letras “X” y “O”. 

Si el usuario selecciona la “X”, significa que es el turno del jugador 1 por lo tanto este iniciará la partida 

Si el usuario escoge la letra “O” el primer turno será el del jugador 2, por lo tanto, deberá esperar que la computadora finalice su turno.  

-- El botón de “Start” en la parte inferior de la pantalla cierra el menú individual del juego e inicializa la partida mediante el método “game()” de la clase TicTacToe.  

-- El botón de “Exit” devuelve al jugador al menú central de juegos 

- Memory Game: En esta ventana encuentran los botones de  “General Stats”, “Personal Stats”, “Start” y “Exit”. 

-- Para mostrar las estadísticas personales, se creó un evento que cuando el usuario presione el botón “General Stats” se genere la ventana con los datos de las primeras 10 partidas ordenadas que tengan el mayor puntaje del registro de todos los usuarios. Esto ocurre de la misma manera que el recorrido que se utiliza en el primer juego solo que este caso la lista de registros está referenciada al juego de Memoria. Tal y como se observa en la Figura 1. 

-- En el caso de las estadísticas personales, si el usuario presiona “Personal Stats” se llamará al evento que crea la ventana donde se muestra el historial de partidas que ha realizado el usuario dentro del juego. Para obtener estos datos se recorre el registro del centro de juegos, se compara el usuario actual con los demás usuarios y se almacena la hora de inicio y finalización con los puntos obtenidos de cada partida, si fue finalizada con éxito. Además, se guarda el promedio de tiempo diario en el juego, que se obtiene dividiendo la cantidad de días por la cantidad de horas y también se guarda la cantidad de partidas realizadas. Exactamente igual al botón con la misma función del primer juego pero su recorrido se basa en los registros del juego Memoria. 

-- Los botones de “Start” y “Exit” cumplen las mismas funciones de inicio de partida y cierre de ventana actual que en el primer juego. 


##Etapa 4: Desarrollo de los juegos del equipo 

###Primer juego: 

###Segundo juego: 

Para la elaboración del segundo juego se tomó como referencia parte del código creado por [@Darwin Galindo] y [@Cinthya Chinchilla] donde cada uno desarrollan un juego de memoria similar. Tomando como base estos ejemplos, se procedió a fusionar partes de ambos códigos para obtener un  “Memory Game” adaptado a los requerimientos del proyecto. 

Se crearon dos archivos llamados “jMemory” y “Memory”, donde el primero cuenta con la estructura y el funcionamiento del juego que se le va a mostrar al usuario y el segundo, incorpora la estructura del menú principal del juego y lo conecta con el centro de juegos. 

A continuación, se detalla un poco más el proceso de desarrollo: 

###Paso 1: 

En el primer paso para el desarrollo de este juego se inició por personalizar la interfaz gráfica de la base del juego, cambiando la dimensión y las imágenes de las cartas y agregando otros elementos a la ventana de la partida.  

###Paso 2: 

Seguidamente, se procedió a implementar las interfaces brindadas por el profesor a la base del “Memory Game” y se incorporó el patrón de diseño Singleton para que solo exista una única instancia del juego y así evitar que se creen repetidas. En la figura 4 se observa el método publico creado para acceder al constructor privado de la interfaz gráfica del juego y así lograr crear la única instancia que se va a utilizar.  

###Paso 3:

Se incluye al juego la ventana principal del mismo, cuya descripción de elaboración se encuentra en definida en la Etapa 3 de la solución del problema del presente proyecto.  

###Paso 4: 

Se incorporó el método de “terminarPartida” , donde se guarda la fecha y hora en la que se finaliza la partida, se elimina la instancia y se verifica si la partida fue finalizada completamente o se terminó antes de completar el juego. A partir de esto se guarda el puntaje y se registra en el historial del jugador. Además, se almacena la información en el registro del centro de juegos.

###Paso 5: 

Para este último paso se implementa la clase “JMemory” cual contiene metodos como el “setCards()” que declara los 16 botones correspondientes a las 16 cartas del juego , las cuales inicialmente hacen uso del método setDisabledIcon() para ocultar la imagen de su reverso. Luego, contiene el método “btnEnable” el cual se encarga de saber si una carta se ha volteado. 

Al presionar la primera carta, mediante una condicional, se visualiza el icono que esta tiene, si al presionar la segunda carta, el icono es igual, se suman 20 puntos al marcador, de lo contrario si mediante el método “compare()” se demuestra que el icono de las cartas es diferente, esto gracias al método “getDescription()”, se le suma 10 puntos . 

Los mejores puntajes están calculados en base a los jugadores que hayan obtenido la menor cantidad de puntos, ya que entre más intentos fallidos tengan, el puntaje será mucho mayor. 

#Etapa 5:  

En esta etapa se busca la implementación del tercer juego…. 

#Etapa 6: 

Sobre el manejo de archivos y excepciones…. 