#Nomes:
	#Daniel Amarante - 13201876-3 - daniel.amarante2@gmail.com
	#Matthias Nunes - 08105058-5 - matthiasnunes@gmail.com
	#Pedro Kühn - 10200237-5 - pedrohk@gmail.com

JFLEX  = java -jar jFlex/JFlex.jar 
JAVAC  = javac

# targets:

all: AsdrSample.class

run: AsdrSample.class
	java AsdrSample teste01.txt
	java AsdrSample teste02.txt
	java AsdrSample teste03.txt
	java AsdrSample teste04.txt
	java AsdrSample teste05.txt

build: clean AsdrSample.class

clean:
	rm -f *~ *.class Yylex.java

AsdrSample.class: AsdrSample.java Yylex.java
	$(JAVAC) AsdrSample.java

Yylex.java: asdr_lex.flex
	$(JFLEX) asdr_lex.flex

