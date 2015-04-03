
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

WHITE_SPACE_CHAR=[\n\r\ \t\b\012]

%% 

"and"       { return AsdrSample.AND;       }
"array"     { return AsdrSample.ARRAY;     }
"begin"     { return AsdrSample.BEGIN;     }
"case"      { return AsdrSample.CASE;      }
"const"     { return AsdrSample.CONST;     }
"div"       { return AsdrSample.DIV;       }
"do"        { return AsdrSample.DO;        }
"downto"    { return AsdrSample.DOWNTO;    }
"else"      { return AsdrSample.ELSE;      }
"end"       { return AsdrSample.END;       }
"file"      { return AsdrSample.FILE;      }
"for"       { return AsdrSample.FOR;       }
"function"  { return AsdrSample.FUNCTION;  }
"goto"      { return AsdrSample.GOTO;      }
"if"        { return AsdrSample.IF;        }
"in"        { return AsdrSample.IN;        }
"label"     { return AsdrSample.LABEL;     }
"mod"       { return AsdrSample.MOD;       }
"nil"       { return AsdrSample.NIL;       }
"not"       { return AsdrSample.NOT;       }
"of"        { return AsdrSample.OF;        }
"or"        { return AsdrSample.OR;        }
"packed"    { return AsdrSample.PACKED;    }
"procedure" { return AsdrSample.PROCEDURE; }
"program"   { return AsdrSample.PROGRAM;   }
"record"    { return AsdrSample.RECORD;    }
"repeat"    { return AsdrSample.REPEAT;    }
"set"       { return AsdrSample.SET;       }
"then"      { return AsdrSample.THEN;      }
"to"        { return AsdrSample.TO;        }
"type"      { return AsdrSample.TYPE;      }
"until"     { return AsdrSample.UNTIL;     }
"var"       { return AsdrSample.VAR;       }
"while"     { return AsdrSample.WHILE;     }
"with"      { return AsdrSample.WITH;      }

[:jletter:][:jletterdigit:]* { return AsdrSample.IDENT; }  

[0-9]+ 	{ return AsdrSample.NUM; }


";" |
"," |
"{" |
"}" |
"(" |
")" |
"+" |
"="    	{ return yytext().charAt(0); } 


{WHITE_SPACE_CHAR}+ { }

. { System.out.println("Erro lexico: caracter invalido: <" + yytext() + ">"); }





