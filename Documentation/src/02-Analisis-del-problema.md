# Analisis del problema

Según el problema planteado, se requieren resolver los siguientes puntos: 

##1)Registro y verificación de los jugadores 

Los usuarios, podrán registrarse en el sistema proporcionando su nombre de usuario y su contraseña, estos datos serán almacenados en una estructura tipo ArrayList, a la cual se podrá acceder durante el inicio de sesión cuando el usuario desee entrar a la central de juegos para así validar el usuario y la contraseña. 

##2)Menú Central de Juegos 

Muestra en pantalla las diferentes opciones de juegos disponibles, así los jugadores pueden seleccionar su juego de preferencia pulsando sobre el botón respectivo.  

##3) Menú Individual del Juego 

En pantalla se muestra al usuario, los datos necesarios para inicializar el juego escogido, por ejemplo, el signo con el que se desea participar en una partida, etc. También mediante botones, se le brinda al usuario las opciones de ver sus estadísticas personales y las estadísticas generales. 

Las estadísticas personales se almacenan en una lista en la que se registra el puntaje obtenido en cada partida del jugador, además de la fecha y hora de inicio, fecha y hora de finalización, y tiempo que tomó la partida. En la parte superior de la ventana se mostrará también la cantidad de tiempo de juego diario, la cantidad de horas y la cantidad de partidas efectuadas.  

Por su parte las estadísticas generales, le muestran al jugador cuales son las diez mejores partidas esto basándose en una comparación de los puntajes obtenidos por todos los jugadores. Se imprime en pantalla el nombre del jugador además de la puntuación obtenida y la fecha y hora en la que se realizó. 

Se muestran también los botones respectivos para iniciar la partida o cerrar la ventana, haciendo que el usuario regrese al menú central de juegos. 

##4) Desarrollo de los juegos del equipo 

Se analiza en primera instancia desarrollar los siguientes juegos: 

- Tic Tac Toe: El juego de gato, tradicional, aunque la partida se desarrolla de manera individual entre el usuario y el computador, en el menú individual, se le brinda al usuario la opción de escoger el símbolo con el que desea efectuar la partida, y inicia esta, ganando al completar una línea de 3 espacios con su símbolo de manera vertical, horizontal y diagonalmente 

- Memoria: Muestra al usuario en pantalla dieciséis tarjetas volteadas , permitiéndole al usuario escoger dos cartas y comprobar si estas tienen la misma imagen en su reverso, si esto es correcto, suma 20 puntos al marcador del juego, de lo contrario, si las cartas no coinciden , se voltean nuevamente y resta 10 puntos al puntaje del jugador. En este juego también se pretende implementar el patrón de diseño Singleton. 

Ambos juegos permiten al usuario finalizar la partida pulsando el botón de salida, almacenando en el registro la información de la partida y señalando que el estado de esta fue inconcluso, también el juego de memoria permite al usuario seleccionar la opción de reiniciar la partida.

##5)Implementación del tercer juego  

Se requiere implementar en el sistema el juego HiddenNumberGame, creado por Aaron Gonzáles, David Arguedas y Leiner Alvarado, esto mediante la incorporación del archivo .class de su juego. 

##6)Manejo de archivos y excepciones 

Se requiere implementar excepciones para evitar la caída de la aplicación en las secciones vulnerables, además se deben guardar en archivos los registros de usuario y puntajes, los cuales, volverán a cargarse cada vez que se ejecute el programa. 