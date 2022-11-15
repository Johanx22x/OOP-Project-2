CLASS=./lib
SRC=./src

all:
	$(MAKE) -C $(SRC)

run: all
	java -cp $(CLASS) Main

clean:
	rm -rf $(CLASS)/*
