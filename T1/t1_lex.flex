
%%

%{
  private int comment_count = 0;

  private T1 yyparser;

  public Yylex(java.io.Reader r, T1 yyparser) {
    this(r);
    this.yyparser = yyparser;
  }


%} 

%integer
%line
%char

WHITE_SPACE_CHAR=[\n\r\ \t\b\012]

%% 

"int" 		{ return T1.INT; }
"boolean"	{ return T1.BOOL; }
"double"	{ return T1.DOUBLE; }
"while"	 	{ return T1.WHILE; }
"if"		{ return T1.IF; }
"else"		{ return T1.ELSE; }
"var"		{ return T1.VAR; }
"program"		{ return T1.PROGRAM; }
"readln"		{ return T1.READLN; }
"writeln"		{ return T1.WRITELN; }
"literal"		{ return T1.LITERAL; }
"+"|"-"|"or" 	{ return T1.ADOPT; }
"*"|"div"|"mod"|"and" 	{ return T1.MULOP; }
"not"		{ return T1.UNARY-EXPRESSION; }
"true"		{ return T1.TRUE; }
"false"		{ return T1.FALSE; }


[:jletter:][:jletterdigit:]* { return T1.IDENT; }  

[0-9]+ 	{ return T1.NUM; }


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





