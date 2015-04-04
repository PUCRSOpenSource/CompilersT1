
%%


%{
  private int comment_count = 0;

  private AsdrSample yyparser;

  public Yylex(java.io.Reader r, AsdrSample yyparser) {
    this(r);
    this.yyparser = yyparser;
  }


%} 

%integer
%line
%char
%ignorecase

WHITE_SPACE_CHAR=[\n\r\ \t\b\012]

%% 

"integer" 		{ return AsdrSample.INTEGER; }
"boolean"	{ return AsdrSample.BOOLEAN; }
"while"	 	{ return AsdrSample.WHILE; }
"if"		{ return AsdrSample.IF; }
"else"		{ return AsdrSample.ELSE; }
"var"		{ return AsdrSample.VAR; }
"program"		{ return AsdrSample.PROGRAM; }
"readln"		{ return AsdrSample.READLN; }
"writeln"		{ return AsdrSample.WRITELN; }
"literal"		{ return AsdrSample.LITERAL; }
"or" 	{ return AsdrSample.OR; }
"div" 	{ return AsdrSample.DIV; }
"mod" 	{ return AsdrSample.MOD; }
"and" 	{ return AsdrSample.AND; }
"not"		{ return AsdrSample.NOT; }
"true"		{ return AsdrSample.TRUE; }
"false"		{ return AsdrSample.FALSE; }


[:jletter:][:jletterdigit:]* { return AsdrSample.IDENT; }  

[0-9]+ 	{ return AsdrSample.NUM; }


";" |
"," |
"{" |
"}" |
"(" |
")" |
":" |
":="    	{ return yytext().charAt(0); } 


{WHITE_SPACE_CHAR}+ { }

. { System.out.println("Erro lexico: caracter invalido: <" + yytext() + ">"); }





