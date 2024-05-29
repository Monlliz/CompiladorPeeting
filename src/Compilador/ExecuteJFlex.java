package Compilador;


import jflex.exceptions.SilentExit;


public class ExecuteJFlex {

    public static void main(String omega[]) {
        String lexerFile = System.getProperty("user.dir") + "/src/Compilador/Lexer.flex",
                lexerFileColor = System.getProperty("user.dir") + "/src/Compilador/LexerColor.flex";
        try {
            jflex.Main.generate(new String[]{lexerFile, lexerFileColor});
        } catch (SilentExit ex) {
            System.out.println("Error al compilar/generar el archivo flex: " + ex);
        }
    }
}
