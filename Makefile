SRC=./src
CLASS=./lib
JAVAC=javac 
JAVA=java

all: 
	$(JAVAC) -d $(CLASS) $(SRC)/*.java

clean:
	rm -f $(CLASS)/*.class

run:
	$(JAVA) -cp $(CLASS) Main
