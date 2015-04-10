
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
GeneralComment   = "{"([^*]|\*+[^*/])*+"}"

%% 

"INTEGER" { return AsdrSample.INTEGER; }
"BOOLEAN" { return AsdrSample.BOOLEAN; }
"PROGRAM" { return AsdrSample.PROGRAM; }
"VAR"     { return AsdrSample.VAR; }
"BEGIN"   { return AsdrSample.BEGIN; }
"END"     { return AsdrSample.END; }
"IF"      { return AsdrSample.IF; }
"THEN"    { return AsdrSample.THEN; }
"ELSE"    { return AsdrSample.ELSE; }
"WHILE"   { return AsdrSample.WHILE; }
"DO"      { return AsdrSample.DO; }
"READLN"  { return AsdrSample.READLN; }
"WRITELN" { return AsdrSample.WRITELN; }
"LITERAL" { return AsdrSample.LITERAL; }
"OR"      { return AsdrSample.OR; }
"DIV"     { return AsdrSample.DIV; }
"MOD"     { return AsdrSample.MOD; }
"AND"     { return AsdrSample.AND; }
"NOT"     { return AsdrSample.NOT; }
"TRUE"    { return AsdrSample.TRUE; }
"FALSE"   { return AsdrSample.FALSE; }


[:jletter:][:jletterdigit:]* { return AsdrSample.IDENT; }  

[0-9]+ 	{ return AsdrSample.NUM; }




";" |
"=" |
"," |
"(" |
")" |
":" |
"<" |
">" |
"."    	{ return yytext().charAt(0); } 


{GeneralComment} { }
{WHITE_SPACE_CHAR}+ { }

. { System.out.println("Erro lexico: caracter invalido: <" + yytext() + ">"); }



