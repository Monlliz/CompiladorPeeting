import compilerTools.TextColor;
import java.awt.Color;

%%
%class LexerColor
%type TextColor
%char
%{
    private TextColor textColor(long start, int size, Color color){
        return new TextColor((int) start, size, color);
    }
%}
/* Variables básicas de comentarios y espacios */
TerminadorDeLinea = \r|\n|\r\n
EntradaDeCaracter = [^\r\n]
EspacioEnBlanco = {TerminadorDeLinea} | [ \t\f]
ComentarioTradicional = "-*" [^*] ~"*-" | "-*" "*"+ "-"
FinDeLineaComentario = "-*" {EntradaDeCaracter}* {TerminadorDeLinea}?
ContenidoComentario = ( [^*] | \*+ [^/*] )*
ComentarioDeDocumentacion = "-**" {ContenidoComentario} "*"+ "-"
Fecha = (0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-\d{4}
hora = (0[0-9]|1[0-9]|2[0-3]):(0[0-9]|[1-5][0-9])
minuto = (0[0-9]|[1-5][0-9]):(0[0-9]|[1-5][0-9])


Letra = [A-Za-zÑñ_ÁÉÍÓÚáéíóúÜü]
Digito = [0-9]
/* Palabras reservadas */
Prin = [Pp][rR][iI][nN]
Public = [Pp][uU][bB][lL][iI][cC]
Static = [Ss][tT][aA][tT][iI][cC]
If = [Ii][fF]
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
Num = [Nn][uU][mM]
Numd = [Nn][uU][mM][dD]
Catena = [Cc][aA][tT][eE][nN][aA]
Bool = [Bb][oO][oO][lL]
Yes = [Yy][eE][sS]
No = [Nn][oO]
Minu = [Mm][iI][nN][uU]
Hour = [Hh][oO][uU][rR]
Date = [Dd][aA][tT][eE]

Digito = [0-9]
NumeroDecimal = {Digito}+ "." {Digito}+
todosNumeros = {Digito}+|"-"{Digito}+|"+"{Digito}+|{ NumeroDecimal }|"-"{ NumeroDecimal }|"+"{ NumeroDecimal } 
/* Comentario */
Comentario = {ComentarioTradicional} | {FinDeLineaComentario} | {ComentarioDeDocumentacion}
/*Palabras reservadas*/
palabrasReservadas = {Prin}|{Public}|{Static}|{If}|{While}|{Rep}|{Do}|{Switch}|{Case}|{Break}|{Default}|{End}|{Return}|{Print}|{Include}|{Null}|{Void}|{Class}|{This}|{New}|{Yes}|{No} 
/*Tipo de dato*/
tipoDeDato = {Num}|{Numd}|{Catena}|{Bool}|{Minu}|{Hour}|{Date}|{Footime}|{Timespace}|{Gate}
/* Identificador */
Letra = [A-Za-zÑñ_ÁÉÍÓÚáéíóúÜü]
Digito = [0-9]
Identificador = {Letra}({Letra}|{Digito})*
Cadena = ({Letra}|{Digito})*

/* Número */
Numero = 0 | [1-9][0-9]*
%%

/* Comentarios o espacios en blanco */
{Comentario} { return textColor(yychar, yylength(), new Color(146, 146, 146)); }
"@"{ Identificador } { return textColor(yychar, yylength(), new Color(0, 146, 0)); }
{EspacioEnBlanco} { /*Ignorar*/ }
{tipoDeDato} { return textColor(yychar, yylength(), new Color(0,67,105)); }
{palabrasReservadas} { return textColor(yychar, yylength(), new Color(1,148,154)); }
/*cadena*/
"'"{Cadena}"'"|"'"{Cadena}({Cadena}|{EspacioEnBlanco})+"'" { return textColor(yychar, yylength(), new Color(146, 0, 0)); }
/*tiempo*/
"'"{Fecha}"'" { return textColor(yychar, yylength(), new Color(153, 153, 0)); }
"'"{hora}"'" { return textColor(yychar, yylength(), new Color(153, 153, 0)); }
"'"{minuto}"'" { return textColor(yychar, yylength(), new Color(153, 153, 0)); }

{ todosNumeros }|" "{ todosNumeros }|"="{ todosNumeros } { return textColor(yychar, yylength(), new Color(146, 0, 0)); }

. { todosNumeros } { return textColor(yychar, yylength(), new Color(0, 0, 0)); }

. { /* Ignorar */ }