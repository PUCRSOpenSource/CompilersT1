JFLEX  = java -jar jFlex/JFlex.jar 
JAVAC  = javac

VAR = 

# targets:

all: AsdrSample.class

run: AsdrSample.class
	java AsdrSample $(VAR)

build: clean AsdrSample.class

clean:
	rm -f *~ *.class Yylex.java

AsdrSample.class: AsdrSample.java Yylex.java
	$(JAVAC) AsdrSample.java

Yylex.java: asdr_lex.flex
	$(JFLEX) asdr_lex.flex

