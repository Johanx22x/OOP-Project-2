CLASS=./lib
SRC=./src
JAVA=java

all:
	$(MAKE) -C $(SRC)

run: all
	$(JAVA) -cp $(CLASS) Main

clean:
	rm -rf $(CLASS)/*
