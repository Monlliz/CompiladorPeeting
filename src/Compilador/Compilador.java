package Compilador;

import tablas.TablaSimbolo;
import tablas.TablaToken;
import tablas.TablaSimboloFija;
import com.formdev.flatlaf.FlatIntelliJLaf;
import compilerTools.CodeBlock;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import compilerTools.Directory;
import compilerTools.ErrorLSSL;
import compilerTools.Functions;
import compilerTools.Grammar;
import compilerTools.Production;
import compilerTools.TextColor;
import compilerTools.Token;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Compilador extends javax.swing.JFrame {

    private String title;
    private Directory directorio;
    private ArrayList<Token> tokens;
    private ArrayList<ErrorLSSL> errors;
    private ArrayList<TextColor> textsColor;
    private Timer timerKeyReleased;
    private ArrayList<Production> identProd;
    private HashMap<String, String> identificadores;
    private boolean codeHasBeenCompiled = false;

    List<String> conjuntoPalabrasReservadas = new ArrayList<>();

    /**
     * Creates new form Compilador
     */
    public Compilador() {
        initComponents();
        init();
        // limpOut();
    }

    private void init() {

        title = "Compilador de Petting";
        setLocationRelativeTo(null);
        setTitle(title);
        directorio = new Directory(this, jtpCode, title, ".pet");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                directorio.Exit();
                System.exit(0);
            }
        });
        //Funcion para ponerle color a las palabras reservadas
        Functions.setLineNumberOnJTextComponent(jtpCode); //Pone los numeros de linea
        timerKeyReleased = new Timer((int) (1000 * 0.3), (ActionEvent e) -> {
            timerKeyReleased.stop();

            int posicion = jtpCode.getCaretPosition();
            jtpCode.setText(jtpCode.getText().replaceAll("[\r]+", ""));
            jtpCode.setCaretPosition(posicion);

            colorAnalysis();

        });
        //Pone un * en el nombre, si no se ha guardado el archivo 
        Functions.insertAsteriskInName(this, jtpCode, () -> {
            timerKeyReleased.restart();
        });
        tokens = new ArrayList<>();
        errors = new ArrayList<>();
        textsColor = new ArrayList<>();
        identProd = new ArrayList<>();
        identificadores = new HashMap<>();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rootPanel = new javax.swing.JPanel();
        buttonsFilePanel = new javax.swing.JPanel();
        ComboCRUD = new javax.swing.JComboBox<>();
        btnCompilar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        comboBoxTablas = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtpCode = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaOutputConsole = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        rootPanel.setBackground(new java.awt.Color(212, 241, 244));

        buttonsFilePanel.setBackground(new java.awt.Color(231, 212, 244));

        ComboCRUD.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        ComboCRUD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nuevo", "Abrir", "Guardar", "Guardar Como" }));
        ComboCRUD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboCRUDActionPerformed(evt);
            }
        });

        btnCompilar.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        btnCompilar.setText("Compilar");
        btnCompilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompilarActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(231, 212, 244));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/icon.png"))); // NOI18N
        jLabel1.setMaximumSize(new java.awt.Dimension(31, 31));
        jLabel1.setMinimumSize(new java.awt.Dimension(31, 31));
        jLabel1.setOpaque(true);

        comboBoxTablas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tabla de Tokens", "Tabla de simbolos", "Tabla de simbolos Fija" }));
        comboBoxTablas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxTablasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonsFilePanelLayout = new javax.swing.GroupLayout(buttonsFilePanel);
        buttonsFilePanel.setLayout(buttonsFilePanelLayout);
        buttonsFilePanelLayout.setHorizontalGroup(
            buttonsFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttonsFilePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ComboCRUD, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btnCompilar)
                .addGap(101, 101, 101)
                .addComponent(comboBoxTablas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(188, 188, 188))
        );
        buttonsFilePanelLayout.setVerticalGroup(
            buttonsFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttonsFilePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(buttonsFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(buttonsFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ComboCRUD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCompilar)
                        .addComponent(comboBoxTablas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jtpCode.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jScrollPane1.setViewportView(jtpCode);

        jtaOutputConsole.setEditable(false);
        jtaOutputConsole.setBackground(new java.awt.Color(255, 255, 255));
        jtaOutputConsole.setColumns(20);
        jtaOutputConsole.setRows(5);
        jScrollPane2.setViewportView(jtaOutputConsole);

        javax.swing.GroupLayout rootPanelLayout = new javax.swing.GroupLayout(rootPanel);
        rootPanel.setLayout(rootPanelLayout);
        rootPanelLayout.setHorizontalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootPanelLayout.createSequentialGroup()
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(rootPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, rootPanelLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(buttonsFilePanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 614, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        rootPanelLayout.setVerticalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonsFilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(rootPanel);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompilarActionPerformed
        if (getTitle().contains("*") || getTitle().equals(title)) {
            if (directorio.Save()) {
                compile();
            }
        } else {
            compile();
        }
    }//GEN-LAST:event_btnCompilarActionPerformed

    private void ComboCRUDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboCRUDActionPerformed
        JComboBox<?> cb = (JComboBox<?>) evt.getSource();
        String seleccionado = (String) cb.getSelectedItem();
        if (seleccionado.equals("Nuevo")) {
            directorio.New();
            clearFields();
        } else if (seleccionado.equals("Abrir")) {
            if (directorio.Open()) {
                colorAnalysis();
                clearFields();
            }
        } else if (seleccionado.equals("Guardar")) {
            if (directorio.Save()) {
                clearFields();
            }
        } else if (seleccionado.equals("Guardar Como")) {
            if (directorio.SaveAs()) {
                clearFields();
            }
        }
    }//GEN-LAST:event_ComboCRUDActionPerformed

    private void comboBoxTablasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxTablasActionPerformed
        JComboBox<?> cb = (JComboBox<?>) evt.getSource();
        String seleccionado = (String) cb.getSelectedItem();
        if (seleccionado.equals("Tabla de Tokens")) {
            TablaToken tablaTokenFrame = new TablaToken();
            fillTableTokens(tablaTokenFrame);
            tablaTokenFrame.setVisible(true);
        } else if (seleccionado.equals("Tabla de simbolos")) {
            TablaSimbolo TablaSimboloFrame = new TablaSimbolo();
            fillTableSimbolos(TablaSimboloFrame);
            TablaSimboloFrame.setVisible(true);
        } else if (seleccionado.equals("Tabla de simbolos Fija")) {
            TablaSimboloFija TablaSimboloFFrame = new TablaSimboloFija();
            fillTableSimbolosF(TablaSimboloFFrame);
            TablaSimboloFFrame.setVisible(true);
        }
    }//GEN-LAST:event_comboBoxTablasActionPerformed

    private void compile() {
        clearFields();
        lexicalAnalysis();
        analisisSintactico();
        printConsole();
        codeHasBeenCompiled = true;
    }

    private void analisisSintactico() {
        Grammar gramatica = new Grammar(tokens, errors);
        gramatica.delete(new String[]{"ERROR_LEXICO_1", "ERROR_LEXICO_2", "ERROR_LEXICO_3", "ERROR_LEXICO_4", "ERROR_LEXICO_5", "ERROR_LEXICO_6", "ERROR_ESPACIOS", "ERROR_NUMERO", "ERROR_LEXICO_7"});

        /*Tipo de datos*/
        // Agrupacion 1
        gramatica.group("VALOR", "(CADENA|CADENA_FECHA|CADENA_HORA|CADENA_MINUTO|NUMERO)", true);

        /*Declaracion de variables*/
        // Agrupacion 2
        gramatica.group("VARIABLE", "TIPO_DE_DATO IDENTIFICADOR A_Asignacion VALOR", true);
        // Agrupacion 3
        gramatica.group("VARIABLE", "TIPO_DE_DATO IDENTIFICADOR ", true);
        /*Errores de variables*/
        // Agrupacion 4
        gramatica.group("VARIABLE", "TIPO_DE_DATO A_Asignacion VALOR", true, 2, "Error Sintactico {} falta identificador "
                + "en la variable [#,%]");

        // Agrupacion 5
        gramatica.group("VARIABLE", "TIPO_DE_DATO IDENTIFICADOR A_Asignacion", 3, "Error Sintactico {} falta el valor "
                + "en la declaracion [#,%]");

        /*Errores tipo de dato y = en el aire */
        gramatica.delete("TIPO_DE_DATO", 4, "Error Sintactico {} tipo de dato no esta en una declaracion [#,%]");
        gramatica.delete("A_Asignacion", 5, "Error Sintactico {} el operador de asignacion no esta en una declaracion [#,%]");

        //Agrupar identificadores y parametros
        gramatica.group("VALOR", "IDENTIFICADOR", true);
        gramatica.group("PARAMETROS", "VALOR (Coma VALOR)*");

        //Agrupar Funciones:
         gramatica.group("FUNCIONES", "(NOMBREFUNCION|FUNCION)", true);
         gramatica.group("FUNCIONES_COMPLETA", "FUNCIONES DosPuntos PARAMETROS", true);
        //Agrupar ESTRUCTURAS_CONTROL
        gramatica.group("ESTRUCTURAS_CONTROL", "(CONDICIONALES|CICLOS)", true);
        gramatica.group("ESTRUCTURAS_CONTROL_COMPLETA", "ESTRUCTURAS_CONTROL DosPuntos PARAMETROS", true);

        //punto y coma
        //cualquier columna mal documentar final line column
        gramatica.finalLineColumn();
        gramatica.group("VARIABLE_COMPLETA", "VARIABLE Punto_Coma", true);
        gramatica.group("VARIABLE_COMPLETA", "VARIABLE", true, 1, "Error Sintactico {} falto punto y coma [#,%]");
        //error punto y coma en el aire
        gramatica.group("PUNTO_COMA", "Punto_Coma", true, 15, "Error Sintactico {} Punto y coma en el aire [#,%]");

        gramatica.show();
    }

    private void lexicalAnalysis() {
        // Extraer tokens
        Lexer lexer;
        try {
            File codigo = new File("code.encrypter");
            FileOutputStream output = new FileOutputStream(codigo);
            byte[] bytesText = jtpCode.getText().getBytes();
            output.write(bytesText);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(codigo), "UTF8"));
            lexer = new Lexer(entrada);
            while (true) {
                Token token = lexer.yylex();
                if (token == null) {
                    break;
                }
                tokens.add(token);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("El archivo no pudo ser encontrado... " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error al escribir en el archivo... " + ex.getMessage());
        }
    }

    private void limpOut() {
        for (int i = 0; i < 500; i++) {
            System.out.println("\b");
        }
    }

    private void colorAnalysis() {
        /* Limpiar el arreglo de colores */
        textsColor.clear();
        /* Extraer rangos de colores */
        LexerColor lexerColor;
        try {
            File codigo = new File("color.encrypter");
            FileOutputStream output = new FileOutputStream(codigo);
            byte[] bytesText = jtpCode.getText().getBytes();
            output.write(bytesText);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(codigo), "UTF8"));
            lexerColor = new LexerColor(entrada);
            while (true) {
                TextColor textColor = lexerColor.yylex();
                if (textColor == null) {
                    break;
                }
                textsColor.add(textColor);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("El archivo no pudo ser encontrado... " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error al escribir en el archivo... " + ex.getMessage());
        }
        Functions.colorTextPane(textsColor, jtpCode, new Color(40, 40, 40));
    }

    //lenar la tabla de tokens
    public void fillTableTokens(TablaToken tablaTokenInstance) {
        conjuntoPalabrasReservadas.add("PALABRA_RESERVADA");
        conjuntoPalabrasReservadas.add("TIPO_DE_DATO");
        conjuntoPalabrasReservadas.add("CICLOS");
        conjuntoPalabrasReservadas.add("CONDICIONALES");
        conjuntoPalabrasReservadas.add("CADENA");
        conjuntoPalabrasReservadas.add("CADENA_FECHA");
        conjuntoPalabrasReservadas.add("CADENA_HORA");
        conjuntoPalabrasReservadas.add("CADENA_MINUT");
        tokens.forEach(token -> {
            if (token.getLexicalComp().startsWith("ERROR ") || token.getLexicalComp().startsWith("Usa ")) {

            } else {
                if (conjuntoPalabrasReservadas.contains(token.getLexicalComp().toString())) {
                  Object[] data = new Object[]{"Palabra reservada", token.getLexeme(), "[" + token.getLine() + ", " + token.getColumn() + "]"};
                    Functions.addRowDataInTable(tablaTokenInstance.getTablaTokens(), data);  
                } else {
                    Object[] data = new Object[]{token.getLexicalComp(), token.getLexeme(), "[" + token.getLine() + ", " + token.getColumn() + "]"};
                    Functions.addRowDataInTable(tablaTokenInstance.getTablaTokens(), data);
                }
            }
        });
    }

    //llenar la tabla de simbolos
    public void fillTableSimbolos(TablaSimbolo tablaSimboloInstance) {

        tokens.forEach(token -> {
            if (token.getLexicalComp().startsWith("IDENTIFICADOR")) {
                Object[] data = new Object[]{token.getLexeme(), " "};
                Functions.addRowDataInTable(tablaSimboloInstance.getTablaSimbolos(), data);
            }
        });
    }

    public void fillTableSimbolosF(TablaSimboloFija tablaSimboloInstance) {
        tokens.forEach(token -> {
            if (token.getLexicalComp().startsWith("PALABRA_RESERVADA")) {
                Object[] data = new Object[]{token.getLexeme(), " "};
                Functions.addRowDataInTable(tablaSimboloInstance.getTablaSimbolos(), data);
            }
        });
    }

    private void printConsole() {
        StringBuilder stringBuilder = new StringBuilder();
        tokens.forEach(token -> {
            if (token.getLexicalComp().startsWith("ERROR")) {
                String lexicalComp = "";
                if (token.getLexicalComp().equals("ERROR_LEXICO_1")) {
                    lexicalComp = "Error lexico 1 en la posicion " + "[" + token.getLine() + ", " + token.getColumn() + "]";
                }
                if (token.getLexicalComp().equals("ERROR_LEXICO_2")) {
                    lexicalComp = "Error lexico 2 en la posicion " + "[" + token.getLine() + ", " + token.getColumn() + "]";
                }
                if (token.getLexicalComp().equals("ERROR_LEXICO_3")) {
                    lexicalComp = "Error lexico 3 en la posicion " + "[" + token.getLine() + ", " + token.getColumn() + "]";
                }
                if (token.getLexicalComp().equals("ERROR_LEXICO_4")) {
                    lexicalComp = "Error lexico 4 en la posicion " + "[" + token.getLine() + ", " + token.getColumn() + "]";
                }
                if (token.getLexicalComp().equals("ERROR_LEXICO_5")) {
                    lexicalComp = "Error lexico 5 en la posicion " + "[" + token.getLine() + ", " + token.getColumn() + "]";
                }
                if (token.getLexicalComp().equals("ERROR_LEXICO_6")) {
                    lexicalComp = "Error lexico 6 en la posicion " + "[" + token.getLine() + ", " + token.getColumn() + "]";
                }
                if (token.getLexicalComp().equals("ERROR_LEXICO_7")) {
                    lexicalComp = "Error lexico 7 en la posicion " + "[" + token.getLine() + ", " + token.getColumn() + "]";
                }
                if (token.getLexicalComp().equals("ERROR_ESPACIOS")) {
                    lexicalComp = "Error lexico 8  de espacios en la posicion " + "[" + token.getLine() + ", " + token.getColumn() + "]";
                }
                if (token.getLexicalComp().equals("ERROR_NUMERO")) {
                    lexicalComp = "Error lexico 9 de numeros en la posicion " + "[" + token.getLine() + ", " + token.getColumn() + "]";
                }

                stringBuilder.append(lexicalComp).append("\n"); // Agregar el valor de lexicalComp con una nueva línea
                //limpOut();

            }
        });

        int sizeErrors = errors.size();

        if (sizeErrors > 0) {
            Functions.sortErrorsByLineAndColumn(errors);
            String strErrors = "\n";
            for (ErrorLSSL error : errors) {
                String strError = String.valueOf(error);
                strErrors += strError + "\n";
            }
            jtaOutputConsole.setText(stringBuilder.toString() + strErrors + "\nFinalizo la compilacion con errores");
        } else {
            jtaOutputConsole.setText(stringBuilder.toString() + "Finalizo la compilacion");

        }
        jtaOutputConsole.setCaretPosition(0);
    }

    private void clearFields() {

        jtaOutputConsole.setText("");
        tokens.clear();
        errors.clear();
        identProd.clear();
        identificadores.clear();
        codeHasBeenCompiled = false;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatIntelliJLaf());
            } catch (UnsupportedLookAndFeelException ex) {
                System.out.println("LookAndFeel no soportado: " + ex);
            }
            new Compilador().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboCRUD;
    private javax.swing.JButton btnCompilar;
    private javax.swing.JPanel buttonsFilePanel;
    private javax.swing.JComboBox<String> comboBoxTablas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jtaOutputConsole;
    private javax.swing.JTextPane jtpCode;
    private javax.swing.JPanel rootPanel;
    // End of variables declaration//GEN-END:variables
}
