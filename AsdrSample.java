import java.io.*;

public class AsdrSample {

        private static final int BASE_TOKEN_NUM = 301;

        public static final int IDENT  = 337;
        public static final int NUM    = 338;        
        public static final int INT    = 339;
        public static final int BOOL   = 340;
        public static final int DOUBLE = 307;       
        public static final int RETURN = 341;

        public static final int AND       = 302;
        public static final int ARRAY     = 303;
        public static final int BEGIN     = 304;
        public static final int CASE      = 305;
        public static final int CONST     = 306;
        public static final int DIV       = 307;
        public static final int DO        = 308;
        public static final int DOWNTO    = 309;
        public static final int ELSE      = 310;
        public static final int END       = 311;
        public static final int FILE      = 312;
        public static final int FOR       = 313;
        public static final int FUNCTION  = 314;
        public static final int GOTO      = 315;
        public static final int IF        = 316;
        public static final int IN        = 317;
        public static final int LABEL     = 318;
        public static final int MOD       = 319;
        public static final int NIL       = 320;
        public static final int NOT       = 321;
        public static final int OF        = 322;
        public static final int OR        = 323;
        public static final int PACKED    = 324;
        public static final int PROCEDURE = 325;
        public static final int PROGRAM   = 326;
        public static final int RECORD    = 327;
        public static final int REPEAT    = 328;
        public static final int SET       = 329;
        public static final int THEN      = 330;
        public static final int TO        = 331;
        public static final int TYPE      = 332;
        public static final int UNTIL     = 333;
        public static final int VAR       = 334;
        public static final int WHILE     = 335;
        public static final int WITH      = 336;

        public static final String tokenList[] = {"IDENT",
                "NUM",                
                "INT",
                "BOOL",
                "DOUBLE",               
                "RETURN",
                "AND",
                "ARRAY",
                "BEGIN",
                "CASE",
                "CONST",
                "DIV",
                "DO",
                "DOWNTO",
                "ELSE",
                "END",
                "FILE",
                "FOR",
                "FUNCTION",
                "GOTO",
                "IF",
                "IN",
                "LABEL",
                "MOD",
                "NIL",
                "NOT",
                "OF",
                "OR",
                "PACKED",
                "PROCEDURE",
                "PROGRAM",
                "RECORD",
                "REPEAT",
                "SET",
                "THEN",
                "TO",
                "TYPE",
                "UNTIL",
                "VAR",
                "WHILE",
                "WITH"
        };

        /* referencia ao objeto Scanner gerado pelo JFLEX */
        private Yylex lexer;

        public ParserVal yylval;

        private static int laToken;
        private boolean debug = true;

        /* construtor da classe */
        public AsdrSample (Reader r) {
                lexer = new Yylex (r, this);
        }

        private void Prog() {
                if (debug) System.out.println("program -> PROGRAM ID ';' declaration-opc compound-stmt '.'");
                check(PROGRAM);
		ListaID();
                check(';');
                declaration-opc();
                compound-stmt();
                check('.');
              
        }

	private void declaration-opc(){
		if (debug) System.out.println("declaration-opc -> VAR declaration-list | & ");
                        
                if(laToken == VAR){
                         check(VAR);
                         Decl();   
                        
                }else{}
                       
                        

	}
        
        private void compound-stmt(){
                if (debug) System.out.println("compound-stmt -> BEGIN statement-list END");
                check(BEGIN);
                statement-list();
                check(END);                
        }   
        
        private void statement-list(){
                if (debug) System.out.println("statement-list -> statement ';' statement-list | statement");
                if(statement()){
                        statement();
                        check(';');
                        statement-list();     
                }else{
                }
        }   
        
        private statement(){
                if (debug) System.out.println("statement-list -> statement ';' statement-list | statement");
                
                if(laToken==IDENT){
                        check(IDENT);
                        check(':=');
                        expression();
                }else if(laToken==IF){
                        check(IF);
                        expression();
                        check(THEN);
                        statement();
                        RestoIF();                        
                        
                }else if(laToken==WHILE){
                        check(WHILE);
                        expression();
                        check(DO);
                        statement();
                }else if(laToken==BEGIN){
                        compound-stmt();
                }else if(laToken==READLN){
                        check(READLN);
                        check(IDENT);
                }else if(laToken==WRITELN){
                        check(WRITELN);
                        print-list();
                }else {
                }
        }
        
        private void print-list(){
                if (debug) System.out.println("print-list -> LITERAL print-list2 | expression print-list2");
                if(laToken==LITERAL){
                        check(LITERAL);
                        print-list();
                }else{
                        expression();
                        print-list2();
                }
        }
        
         private void print-list2() {
                if (debug) System.out.println("print-list2 -> , print-list &");                
                 if(laToken==','){                       
                        check(',');
                        print-list();      
                 }else{}
                                      
        }
        
        private void expression(){
                if (debug) System.out.println("expression -> additive-expression relop additive-expression | additive-expression"); 
                additive-expression();
                if(laToken=='<' || laToken=='>' || laToken=='='){
                     relop();
                     additive-expression();   
                }else {
                }                
        }
        
        private void relop(){
                if (debug) System.out.println("relop -> <= | < | > | >= | = | <>");
                if(laToken=='<'){
                        check('<');
                        restorelop();
                }else if(laToken=='>'){
                        check('>');
                        restorelop();
                }else if(laToken=='='){
                        check('=');
                }
                
        }
        
        private void restorelop(){
                if(laToken=='='){
                        check('=');
                }else if(laToken=='>'){
                        check('>');
                }else{}
        }
        
        private void additive-expression(){
                if (debug) System.out.println("additive-expression -> addop term | term");
                if(laToken=='+' || laToken=='-' || laToken=OR){
                        addop();
                        term();                        
                }else{
                        term();
                }
        }
        
        private void addop(){
               if (debug) System.out.println("additive-expression -> addop term | term"); 
                if(laToken=='+'){
                        check('+');
                }else if(laToken=='-'){
                        check('-');
                }else if(laToken==OR){
                        check(OR);
                }
        }
        
        private void term(){
                 if (debug) System.out.println("term -> term mulop unary-expression | unary-expression"); 
                if(laToken=='*' || laToken==DIV || laToken==MOD || laToken==AND){
                        mulop();
                        unary-expression();
                }else{
                        unary-expression();
                }
        }
        
        private void mulop(){
                if (debug) System.out.println("milop -> * | DIV | MOD | AND"); 
                if(laToken=='*'){
                       check('*'); 
                }else if(laToken==DIV){
                        check(DIV);
                }else if(laToken==MOD){
                        check(MOD);
                }else if(laToken==AND){
                        check(AND);
                }
                
        }
        
        private void unary-expression(){
                if (debug) System.out.println("unary-expression -> NOT unary-expression | factor"); 
                
                if(laToken==NOT){
                        check(NOT);
                        unary-expression();
                }else{
                        factor();
                }
                
        }
        
        private void factor(){
                if (debug) System.out.println("factor -> ( expression ) | ID | NUM | TRUE | FALSE"); 
                if(laToken=='('){
                        check('(');
                        expression();
                        check(')');
                }else if(laToken==IDENT){
                        check(IDENT);
                }else if(laToken==NUM){
                        check(NUM);
                }else if(laToken==TRUE){
                        check(TRUE);
                }else if(laToken==FALSE){
                        check(FALSE);
                }
        }
        
                
        private void declaration-list(){
                 if (debug) System.out.println("declaration-list -> declaration ';' declaration-list | declaration ';' ");    
                  if (laToken == IDENT ) {
                         Decl();
                         check(';');
                         declaration-list();
                  }else{
                          
                  }
        }
        
   
        private void Decl() {
                if (debug) System.out.println("Decl --> Tipo ListaID ;");                
                ListaID();
                check(':');
                Tipo();                          
        }

        private void ListaID(){
                if (debug) System.out.println("ListaID -->  IDENT  RestoLID");
                check(IDENT);
                RestoLID();
        }

        private void RestoLID() {
                if (laToken == ',' ) {
                        if (debug) System.out.println("RestoLID --> , IDENT RestoLID");
                        check(',');
                        check(IDENT);
                        RestoLID();
                } else
                        if (debug) System.out.println("RestoLID --> vazio");
        }

        private void Tipo() { 
                if (laToken == INTEGER) {
                        if (debug) System.out.println("Tipo --> int");
                        check(INTEGER);
                } else if (laToken == BOOLEAN) {
                        if (debug) System.out.println("Tipo --> boolean");
                        check(BOOLEAN);
                 
                } else yyerror("Esperado: integer, boolean ");
        }
        
        
        private void RestoIF() {
                if (laToken == ELSE) {
                        if (debug) System.out.println("RestoIF --> else Cmd ");
                        check(ELSE);
                        statement();
                } else 
                        if (debug) System.out.println("RestoIF --> vazio ");
        }     
        
/*
        private void Bloco() {
                if (debug) System.out.println("Bloco --> { Cmd }");

                check('{');
                Cmd();
                check('}');
        }

        private void Cmd() {
                if (laToken == '{') {
                        if (debug) System.out.println("Cmd --> Bloco");
                        Bloco();
                }

                else if (laToken == WHILE) {
                        if (debug) System.out.println("Cmd --> WHILE E Cmd");
                        check(WHILE);
                        E();
                        Cmd();
                }
                else if (laToken == IDENT) {
                        if (debug) System.out.println("Cmd --> ident = E ;");
                        check(IDENT);
                        check('=');
                        E();
                        check(';');
                }
                else if (laToken == IF) {
                        if (debug) System.out.println("Cmd --> if E Cmd RestoIF");
                        check(IF);
                        check('(');
                        E();
                        check(')');
                        Cmd();
                        RestoIF();
                }
                else yyerror("Esperado {, if, while ou identificador");
        }

        private void RestoIF() {
                if (laToken == ELSE) {
                        if (debug) System.out.println("RestoIF --> else Cmd ");
                        check(ELSE);
                        Cmd();
                } else 
                        if (debug) System.out.println("RestoIF --> vazio ");
        }     



        private void E() {
                if (laToken == IDENT) {
                        if (debug) System.out.println("E --> IDENT");
                        check(IDENT);
                }
                else if (laToken == NUM) {
                        if (debug) System.out.println("E --> NUM");
                        check(NUM);
                }
                else if (laToken == '(') {
                        if (debug) System.out.println("E --> ( E )");
                        check('(');
                        E();        
                        check(')');
                }
                else yyerror("Esperado operando (, identificador ou numero");
        }*/

        private void check(int expected) {
                if (laToken == expected)
                        laToken = this.yylex();
                else {
                        String expStr, laStr;       

                        expStr = ((expected < BASE_TOKEN_NUM )
                                        ? ""+(char)expected
                                        : tokenList[expected-BASE_TOKEN_NUM]);

                        laStr = ((laToken < BASE_TOKEN_NUM )
                                        ? new Character((char)laToken).toString()
                                        : tokenList[laToken-BASE_TOKEN_NUM]);

                        yyerror( "esperado token: " + expStr +
                                        " na entrada: " + laStr);
                }
        }

        /* metodo de acesso ao Scanner gerado pelo JFLEX */
        private int yylex() {
                int retVal = -1;
                try {
                        yylval = new ParserVal(0); //zera o valor do token
                        retVal = lexer.yylex(); //le a entrada do arquivo e retorna um token
                } catch (IOException e) {
                        System.err.println("IO Error:" + e);
                }
                return retVal; //retorna o token para o Parser 
        }

        /* metodo de manipulacao de erros de sintaxe */
        public void yyerror (String error) {
                System.err.println("Erro: " + error);
                System.err.println("Entrada rejeitada");
                System.out.println("\n\nFalhou!!!");
                System.exit(1);

        }


        /**
         * Runs the scanner on input files.
         *
         * This main method is the debugging routine for the scanner.
         * It prints debugging information about each returned token to
         * System.out until the end of file is reached, or an error occured.
         *
         * @param args   the command line, contains the filenames to run
         *               the scanner on.
         */
        public static void main(String[] args) {
                AsdrSample parser = null;
                try {
                        if (args.length == 0)
                                parser = new AsdrSample(new InputStreamReader(System.in));
                        else 
                                parser = new  AsdrSample( new java.io.FileReader(args[0]));

                        laToken = parser.yylex();          

                        parser.Prog();

                        if (laToken== Yylex.YYEOF)
                                System.out.println("\n\nSucesso!");
                        else     
                                System.out.println("\n\nFalhou - esperado EOF.");               

                }
                catch (java.io.FileNotFoundException e) {
                        System.out.println("File not found : \""+args[0]+"\"");
                }
                //catch (java.io.IOException e) {
                        //System.out.println("IO error scanning file \""+args[0]+"\"");
                        //System.out.println(e);
                //}
                //catch (Exception e) {
                        //System.out.println("Unexpected exception:");
                        //e.printStackTrace();
                //}

        }

}

