JAVA=./src
JAVAC=javac 
CLASS=./lib

all: 
	$(JAVAC) -d $(CLASS) $(JAVA)/*.java

clean:
	rm -f $(CLASS)/*.class

run:
	java -cp $(CLASS) Main
