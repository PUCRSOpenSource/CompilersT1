JFLEX  = java -jar JFlex.jar 
JAVAC  = javac

VAR = 

# targets:

all: T1.class

run: T1.class
	java T1 $(VAR)

build: clean T1.class

clean:
	rm -f *~ *.class Yylex.java

T1.class: T1.java Yylex.java
	$(JAVAC) T1.java

Yylex.java: T1_lex.flex
	$(JFLEX) T1_lex.flex

