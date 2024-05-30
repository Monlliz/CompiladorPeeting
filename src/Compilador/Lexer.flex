import compilerTools.Token;

%%
%class Lexer
%type Token
%line
%column
%{
    private Token token(String lexeme, String lexicalComp, int line, int column) {
        return new Token(lexeme, lexicalComp, line + 1, column + 1);
    }
%}
/* Variables básicas de comentarios y espacios */
TerminadorDeLinea = \r|\n|\r\n
EntradaDeCaracter = [^\r\n]
EspacioEnBlanco = {TerminadorDeLinea} | [ \t\f]
ComentarioTradicional = "-*" [^*] "*-" | "-*" "*"+ [^*]*
FinDeLineaComentario = "-*" {EntradaDeCaracter}* {TerminadorDeLinea}?
ContenidoComentario = ( [^*] | \*+ [^/*] )*
ComentarioDeDocumentacion = "-**" {ContenidoComentario} "*"+ "-"
Fecha = (0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-\d{4}
hora = (0[0-9]|1[0-9]|2[0-3]):(0[0-9]|[1-5][0-9])
minuto = (0[0-9]|[1-5][0-9]):(0[0-9]|[1-5][0-9])
/* Comentario */
Comentario = {ComentarioTradicional} | {FinDeLineaComentario} | {ComentarioDeDocumentacion}

/*Constantes necesarias*/
Letra = [A-Za-zÑñ_ÁÉÍÓÚáéíóúÜü]
Digito = [0-9]
/* Palabras reservadas */
Prin = [Pp][rR][iI][nN]
Public = [Pp][uU][bB][lL][iI][cC]
Static = [Ss][tT][aA][tT][iI][cC]
If = [Ii][fF]
Else= [Ee][Ll][Ss][Ee]
While = [Ww][hH][iI][lL][eE]
Rep = [Rr][eE][pP]
Do = [Dd][oO]
Footime = [Ff][oO][oO][tT][iI][mM][eE]
Timespace = [Tt][iI][mM][eE][sS][pP][aA][cC][eE]
Switch = [Ss][wW][iI][tT][cC][hH]
Case = [Cc][aA][sS][eE]
Break = [Bb][rR][eE][aA][kK]
Default = [Dd][eE][fF][aA][uU][lL][tT]
Gate = [Gg][aA][tT][eE]
End = [Ee][nN][dD]
Return = [Rr][eE][tT][uU][rR][nN]
Print = [Pp][rR][iI][nN][tT]
Include = [Ii][nN][cC][lL][uU][dD][eE]
Null = [Nn][uU][lL][lL]
Void = [Vv][oO][iI][dD]
Class = [Cc][lL][aA][sS][sS]
This = [Tt][hH][iI][sS]
New = [Nn][eE][wW]
Numf = [Nn][uU][mM][fF]
Numd = [Nn][uU][mM][dD]
Catena = [Cc][aA][tT][eE][nN][aA]
Bool = [Bb][oO][oO][lL]
Yes = [Yy][eE][sS]
No = [Nn][oO]
Minu = [Mm][iI][nN][uU]
Hour = [Hh][oO][uU][rR]
Date = [Dd][aA][tT][eE]

/*Agrupaciones*/
Identificador = {Letra}({Letra}|{Digito})*
Cadena = ({Letra}|{Digito})*
NumeroDecimal = {Digito}+ "." {Digito}+

/* Número */
Numero = 0 | [1-9][0-9]*
NumeroDecimal = {Digito}+ "." {Digito}+
todosNumeros = {Digito}+|"-"{Digito}+|"+"{Digito}+|{NumeroDecimal}|"-"{NumeroDecimal}|"+"{NumeroDecimal}

/*Operadores*/
OpAgrupacion = "("|")"|"{"|"}"|"["|"]"
OpAritmetico = "+"|"-"|"*"|"/"|"%"|"^"
OpComparacion = "=="|"!="|"<"|">"
OpLogico = "AND"|"OR"|"NOT"
OpPuntuacion = ","|"."|";"|":"
OpAsignacion = "="
%%

/* espacios en blanco */
{EspacioEnBlanco}|{Comentario} { /* Ignorar */ }

/* Identificador */
"@"{Identificador} {return token(yytext(), "IDENTIFICADOR", yyline, yycolumn);}
/* Palabras reservadas */
{Prin}|{Public}|{Static}|{Do}|{Break}|{Default}|{End}|{Include}|{Null}|{Void}|{Class}|{This}|{New}|{Yes}|{No} {return token(yytext(), "PALABRA_RESERVADA", yyline, yycolumn);}
/*Tipos de datos*/
{Numf}|{Numd}|{Catena}|{Bool}|{Minu}|{Hour}|{Date}|{Footime}|{Timespace}|{Gate} {return token(yytext(), "TIPO_DE_DATO", yyline, yycolumn);}



/* Operadores de agrupación */
"(" {return token(yytext(), "AG_Parentesis_Abre", yyline, yycolumn);}
")" {return token(yytext(), "AG_Parentesis_Cierra", yyline, yycolumn);}
"{" {return token(yytext(), "AG_Llave_Abre", yyline, yycolumn);}
"}" {return token(yytext(), "AG_Llave_Cierra", yyline, yycolumn);}
"[" {return token(yytext(), "AG_Corchete_Abre", yyline, yycolumn);}
"]" {return token(yytext(), "AG_Corchete_Cierra", yyline, yycolumn);}

/* Operadores de puntuación */
";" {return token(yytext(), "Punto_Coma", yyline, yycolumn);}
"." {return token(yytext(), "Punto", yyline, yycolumn);}
"," {return token(yytext(), "Coma", yyline, yycolumn);}
":" {return token(yytext(), "DosPuntos", yyline, yycolumn);}

/* Operador de asignación */
"=" {return token(yytext(), "A_Asignacion", yyline, yycolumn);}

/* Operadores lógicos */
"AND" {return token(yytext(), "L_SI", yyline, yycolumn);}
"OR" {return token(yytext(), "L_O", yyline, yycolumn);}
"NOT" {return token(yytext(), "L_NO", yyline, yycolumn);}

/* Operadores de comparación */
"=="|"!="|"<"|">" {return token(yytext(), "C", yyline, yycolumn);}

/* Operadores aritméticos */
"+"|"-"|"*"|"/"|"%"|"^" {return token(yytext(), "Arit", yyline, yycolumn);}
/*Ciclos */
{While}|{Rep} {return token(yytext(), "CICLOS", yyline, yycolumn);}
/*CONDICIONALES*/
{If}|{Else} {return token(yytext(), "CONDICIONALES", yyline, yycolumn);}

//FUNCIONES
"_"{Identificador} {return token(yytext(), "FUNCION", yyline, yycolumn);}
{Print}|{Switch}|{Case}|{Return} { return token(yytext(), "NOMBREFUNCION", yyline, yycolumn); }

/*Tipos de valores*/
/* Cadena */
"'"{Cadena}"'"|"'"{Cadena}({Cadena}|{EspacioEnBlanco})+"'" { return token(yytext(), "CADENA", yyline, yycolumn); }
/* Tiempo */
"'"{Fecha}"'" { return token(yytext(), "CADENA_FECHA", yyline, yycolumn); }
"'"{hora}"'" { return token(yytext(), "CADENA_HORA", yyline, yycolumn); }
"'"{minuto}"'" { return token(yytext(), "CADENA_MINUTO", yyline, yycolumn); }

/* Números */
{todosNumeros} { return token(yytext(), "NUMERO", yyline, yycolumn); }

/*FUNCIONES NATIVAS*/
"Calculos" { return token(yytext(), "Biblioteca", yyline, yycolumn); }
"CalcularIMC" { return token(yytext(), "FuNa", yyline, yycolumn); }

/* Errores */
"@"{Digito}+{Letra}({Letra}|{Digito})* { return token(yytext(), "ERROR_LEXICO_2", yyline, yycolumn); } //Error 2: identificador no válido, inicia con un número
"@"{Digito}+ { return token(yytext(), "ERROR_LEXICO_3", yyline, yycolumn); } //Error 3: @identificador no válido porque solo incluye números
{Letra}+ { return token(yytext(), "ERROR_LEXICO_5", yyline, yycolumn); } //Error 5: solo letra
({Letra}|{Digito})+ { return token(yytext(), "ERROR_LEXICO_6", yyline, yycolumn); } //Error 6: letra o dígito sin definición
({OpAritmetico}{OpAritmetico}+)|({OpComparacion}{OpComparacion}+)|({OpLogico}{OpLogico}+)|({OpPuntuacion}{OpPuntuacion}+) {return token(yytext(), "ERROR_LEXICO_7", yyline, yycolumn);}//Error 7: más de un mismo operador junto
{todosNumeros} { return token(yytext(), "ERROR_NUMERO", yyline, yycolumn); } //Número sin propósito
. { return token(yytext(), "ERROR_LEXICO_1", yyline, yycolumn); } //Caracter no reconocido
