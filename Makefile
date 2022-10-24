SRC=./src
CLASS=./lib
JAVAC=javac 
JAVA=java

all: 
	$(JAVAC) -d $(CLASS) $(SRC)/*.java

clean:
	rm -f $(CLASS)/*.class

run: all
	$(JAVA) -cp $(CLASS) Main
