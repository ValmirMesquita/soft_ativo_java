/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.AtivoOS.view;
//import java.lang.NullPointerException;
//import java.io.InputStreamReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import br.com.AtivoOS.dal.ModuloConexao;

import java.io.InputStream;
import java.sql.*;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;



public class TelaPrincipal extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;

    Connection conexao = null;

    public TelaPrincipal() {
        initComponents();

        conexao = ModuloConexao.conector();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktop = new javax.swing.JDesktopPane();
        jLabel2 = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        lblUsuarioStatus = new javax.swing.JLabel();
        MenuBar = new javax.swing.JMenuBar();
        MenuCadastro = new javax.swing.JMenu();
        MenuCadastroCliente = new javax.swing.JMenuItem();
        MenuCadastroOS = new javax.swing.JMenuItem();
        MenuCadastroUsuario = new javax.swing.JMenuItem();
        MenuRelatorio = new javax.swing.JMenu();
        MenuRelatorioCliente = new javax.swing.JMenuItem();
        MenuRelatorioServico = new javax.swing.JMenuItem();
        MenuAjuda = new javax.swing.JMenu();
        MenuAjudaSobre = new javax.swing.JMenuItem();
        MenuOpcoes = new javax.swing.JMenu();
        MenuOpcoesSair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema para controle de O.S");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 584, Short.MAX_VALUE)
        );

        jLabel2.setText("DATA:");

        lblUsuario.setText("Valmir Sousa");

        lblData.setText("16/01/2022");
        lblData.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                lblDataAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        lblUsuarioStatus.setText("Status");

        MenuCadastro.setText("Cadastro");

        MenuCadastroCliente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_DOWN_MASK));
        MenuCadastroCliente.setText("Cliente");
        MenuCadastroCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCadastroClienteActionPerformed(evt);
            }
        });
        MenuCadastro.add(MenuCadastroCliente);

        MenuCadastroOS.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_DOWN_MASK));
        MenuCadastroOS.setText("OS");
        MenuCadastroOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCadastroOSActionPerformed(evt);
            }
        });
        MenuCadastro.add(MenuCadastroOS);

        MenuCadastroUsuario.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_DOWN_MASK));
        MenuCadastroUsuario.setText("Usuários");
        MenuCadastroUsuario.setEnabled(false);
        MenuCadastroUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCadastroUsuarioActionPerformed(evt);
            }
        });
        MenuCadastro.add(MenuCadastroUsuario);

        MenuBar.add(MenuCadastro);

        MenuRelatorio.setText("Relatório");
        MenuRelatorio.setEnabled(false);
        MenuRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuRelatorioActionPerformed(evt);
            }
        });

        MenuRelatorioCliente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_DOWN_MASK));
        MenuRelatorioCliente.setText("Clientes");
        MenuRelatorioCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuRelatorioClienteActionPerformed(evt);
            }
        });
        MenuRelatorio.add(MenuRelatorioCliente);

        MenuRelatorioServico.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_DOWN_MASK));
        MenuRelatorioServico.setText("Serviços");
        MenuRelatorio.add(MenuRelatorioServico);

        MenuBar.add(MenuRelatorio);

        MenuAjuda.setText("Ajuda");
        MenuAjuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuAjudaActionPerformed(evt);
            }
        });

        MenuAjudaSobre.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, java.awt.event.InputEvent.ALT_DOWN_MASK));
        MenuAjudaSobre.setText("Sobre");
        MenuAjudaSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuAjudaSobreActionPerformed(evt);
            }
        });
        MenuAjuda.add(MenuAjudaSobre);

        MenuBar.add(MenuAjuda);

        MenuOpcoes.setText("Opções");
        MenuOpcoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuOpcoesActionPerformed(evt);
            }
        });

        MenuOpcoesSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_DOWN_MASK));
        MenuOpcoesSair.setText("Sair");
        MenuOpcoesSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuOpcoesSairActionPerformed(evt);
            }
        });
        MenuOpcoes.add(MenuOpcoesSair);

        MenuBar.add(MenuOpcoes);

        setJMenuBar(MenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(752, Short.MAX_VALUE)
                .addComponent(lblUsuario)
                .addGap(29, 29, 29)
                .addComponent(lblUsuarioStatus)
                .addGap(32, 32, 32)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblData, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(desktop)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(desktop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblUsuario)
                    .addComponent(lblData)
                    .addComponent(lblUsuarioStatus))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(1052, 677));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // As linhas abaixo substitui a data lblData pela data atual do sistema:
        Date data = new Date();
        DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);//Define o formato da Data
        lblData.setText(formatador.format(data));

    }//GEN-LAST:event_formWindowActivated

    private void MenuOpcoesSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuOpcoesSairActionPerformed
        // Eventos do botão Sair no menu opções, com op de sair ou não 
        int sair = JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja sair?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (sair == JOptionPane.YES_NO_OPTION) {
            System.exit(0);
        }


    }//GEN-LAST:event_MenuOpcoesSairActionPerformed

    private void MenuAjudaSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuAjudaSobreActionPerformed
        // Chamando a tela sobre 
        //TelaSobre sobre = new TelaSobre();
        //sobre.setVisible(true);

        TelaSobreAutor autor = new TelaSobreAutor();
        autor.setVisible(true);
        desktop.add(autor);


    }//GEN-LAST:event_MenuAjudaSobreActionPerformed

    private void MenuCadastroUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCadastroUsuarioActionPerformed
        // Chama a tela  form Usuarios dentro do painel do desktop
        TelaUsuario usuario = new TelaUsuario();
        usuario.setVisible(true);
        desktop.add(usuario);


    }//GEN-LAST:event_MenuCadastroUsuarioActionPerformed

    private void MenuAjudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuAjudaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MenuAjudaActionPerformed

    private void lblDataAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_lblDataAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_lblDataAncestorAdded

    private void MenuOpcoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuOpcoesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MenuOpcoesActionPerformed

    private void MenuCadastroClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCadastroClienteActionPerformed
        // Chama a tela cliente:
        TelaCliente cliente = new TelaCliente();
        cliente.setVisible(true);
        desktop.add(cliente);
    }//GEN-LAST:event_MenuCadastroClienteActionPerformed

    private void MenuCadastroOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCadastroOSActionPerformed
        // Chama tela O.S:

        TelaOS os = new TelaOS();
        os.setVisible(true);
        desktop.add(os);

    }//GEN-LAST:event_MenuCadastroOSActionPerformed

    private void MenuRelatorioClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuRelatorioClienteActionPerformed
        // TODO add your handling code here:
        // Gerando relatorio de clientes:

        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a impressão", "Atenção", JOptionPane.YES_NO_OPTION);
        // imprimindo relatorio com frameworks Jasper Reports
        if (confirma == JOptionPane.YES_NO_OPTION) {
            
           
            
            
            
            
            
            
			

        }
    }//GEN-LAST:event_MenuRelatorioClienteActionPerformed

    private void MenuRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuRelatorioActionPerformed

    }//GEN-LAST:event_MenuRelatorioActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu MenuAjuda;
    private javax.swing.JMenuItem MenuAjudaSobre;
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JMenu MenuCadastro;
    private javax.swing.JMenuItem MenuCadastroCliente;
    private javax.swing.JMenuItem MenuCadastroOS;
    public static javax.swing.JMenuItem MenuCadastroUsuario;
    private javax.swing.JMenu MenuOpcoes;
    private javax.swing.JMenuItem MenuOpcoesSair;
    public static javax.swing.JMenu MenuRelatorio;
    private javax.swing.JMenuItem MenuRelatorioCliente;
    private javax.swing.JMenuItem MenuRelatorioServico;
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblData;
    public static javax.swing.JLabel lblUsuario;
    public static javax.swing.JLabel lblUsuarioStatus;
    // End of variables declaration//GEN-END:variables
}
