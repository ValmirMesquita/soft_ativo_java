package br.com.AtivoOS.Tela;

// bibliotecas para importe de imagem para icone da pagina 
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
//---------------------------------------------------------------------------
import java.sql.*;// traz tudo o que existe no banco de dados sql
import br.com.AtivoOS.dal.ModuloConexao;// Importe da pasta dal o modulo de conexão´para teste
import br.com.AtivoOS.view.TelaPrincipal;// Importe da tela principal da´pasta view.
import java.awt.Color;
import javax.swing.JOptionPane;
import static javax.swing.UIManager.getString;


public class TelaLogin extends javax.swing.JFrame {
    
    // Usa as variaveis da conexão dal
    Connection conexao = null;
    //Cria variaveis especiais para conexão com banco de dados
    //Prepared Statenent e ResultSet são framework de pacotes java.sql
    //e servem para preparar e execultar as instruçoes sql
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    // Cria metodo para logar
    public void logar() {

        //Execulta função sql
        String sql = "select *from cadastrouser where login =? and senha =?";
        //String sql = "select * from cadastrouser where login ='?' and senha ='?'";

        try {
            //Linhas abaixo preparam a consulta ao banco de dados
            // Em função do que foi digitado na caixa de texto
            // o sinal de ? e substituido pelo conteudo das variaveis 
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuario.getText());
            
            //String captura = new String(txtSenha.getPassword());
            String captura = new String(txtSenha.getPassword());
            pst.setString(2, captura);
            
           // A linha abaixo executa a Query consuta no banco 
            rs = pst.executeQuery();
            
           // Se existir usuarios e senhas correspondentes 
            if (rs.next()) {
                // A Linha abaixo obtem o conteudo do campo perfil da tabela  
                //cadastrouser;
                String perfil = rs.getString(6);
                System.out.println(perfil);// Imprime o tipo do perfil no console

                // A estrutura abaixo faz o tratamento do perfil do ususario
                if (perfil.equals("Admin")) {

                    TelaPrincipal principal = new TelaPrincipal();
                    principal.setVisible(true);
                    TelaPrincipal.MenuRelatorio.setEnabled(true);
                    TelaPrincipal.MenuCadastroUsuario.setEnabled(true); 
                    
                    TelaPrincipal.lblUsuario.setText(rs.getString(2));
                    //TelaPrincipal.lblUsuario.setForeground(Color.red);  
                    
                    TelaPrincipal.lblUsuarioStatus.setText(rs.getString(6));
                    //TelaPrincipal.lblUsuarioStatus.setForeground(Color.red);                    
                    this.dispose();
                    
                } else if(perfil.equals("Usuario")){
                    
                    TelaPrincipal principal = new TelaPrincipal();
                    principal.setVisible(true);
                    TelaPrincipal.MenuRelatorio.setEnabled(false);
                    TelaPrincipal.MenuCadastroUsuario.setEnabled(false);   
                    
                    TelaPrincipal.lblUsuario.setText(rs.getString(2));
                    //TelaPrincipal.lblUsuario.setForeground(Color.red);  
                    
                    TelaPrincipal.lblUsuarioStatus.setText(rs.getString(6));
                    //TelaPrincipal.lblUsuarioStatus.setForeground(Color.red);
                    
                    
                    
                }
              
                else {

                    TelaPrincipal principal = new TelaPrincipal();
                    principal.setVisible(true);
                    this.dispose();
                }

                // Linha abaixo exibe o comteudo do campo na tela
                //TelaPrincipal principal = new TelaPrincipal();
                //principal.setVisible(true);
                //this.dispose();// Ensera a tela de logim apois inserir a senha correta
                //conexao.close();// feicha o banco de dados apois o uso 

            } else {
                JOptionPane.showMessageDialog(null, "Usuario e/ou senha invalido(a)");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }
    
    
    public TelaLogin() {
        initComponents();
        conexao = ModuloConexao.conector();
        //Linha abaixo serve de apoio ao estatos de conexão
        //System.out.println(conexao);// Mostra o estado de conexão
        if (conexao != null) {
            lblEstatos.setText("Conectado");
        } else {
            lblEstatos.setText("Não conectado");
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        lblSenha = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        txtSenha = new javax.swing.JPasswordField();
        btnEntrar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblEstatos = new javax.swing.JLabel();

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Estatos do servidor:");
        jLabel3.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jLabel3AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tela de Login");
        setBackground(new java.awt.Color(0, 51, 255));
        setResizable(false);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(54, 88, 115));
        jPanel1.setLayout(null);

        lblUsuario.setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuario.setText("Usuario");
        jPanel1.add(lblUsuario);
        lblUsuario.setBounds(160, 150, 43, 15);

        lblSenha.setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N
        lblSenha.setForeground(new java.awt.Color(255, 255, 255));
        lblSenha.setText("Senha");
        jPanel1.add(lblSenha);
        lblSenha.setBounds(170, 180, 35, 15);

        txtUsuario.setFont(new java.awt.Font("Lucida Sans", 0, 11)); // NOI18N
        txtUsuario.setForeground(new java.awt.Color(153, 153, 153));
        txtUsuario.setBorder(null);
        txtUsuario.setCaretColor(new java.awt.Color(102, 102, 102));
        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });
        jPanel1.add(txtUsuario);
        txtUsuario.setBounds(220, 150, 180, 20);

        txtSenha.setFont(new java.awt.Font("Lucida Sans", 0, 11)); // NOI18N
        txtSenha.setForeground(new java.awt.Color(153, 153, 153));
        txtSenha.setBorder(null);
        jPanel1.add(txtSenha);
        txtSenha.setBounds(220, 180, 180, 21);

        btnEntrar.setBackground(new java.awt.Color(54, 88, 115));
        btnEntrar.setForeground(new java.awt.Color(255, 255, 255));
        btnEntrar.setText("Entrar");
        btnEntrar.setBorder(null);
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEntrar);
        btnEntrar.setBounds(220, 210, 68, 25);

        btnCancelar.setBackground(new java.awt.Color(54, 88, 115));
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 0));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar);
        btnCancelar.setBounds(320, 210, 82, 25);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/AtivoOS/icones/logoTelaLoginMax.png"))); // NOI18N
        jPanel1.add(jLabel4);
        jLabel4.setBounds(100, 20, 260, 76);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/AtivoOS/icones/logoTelaLoginMin.png"))); // NOI18N
        jPanel1.add(jLabel5);
        jLabel5.setBounds(10, 200, 130, 33);

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Status do servidor:");
        jLabel7.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jLabel7AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jPanel1.add(jLabel7);
        jLabel7.setBounds(220, 130, 110, 14);

        lblEstatos.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblEstatos.setForeground(new java.awt.Color(255, 255, 255));
        lblEstatos.setText("Estatos");
        jPanel1.add(lblEstatos);
        lblEstatos.setBounds(320, 130, 120, 15);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 450, 250);

        setSize(new java.awt.Dimension(466, 289));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        // Chamando o metodo logar do botão Entrar:
        logar();


    }//GEN-LAST:event_btnEntrarActionPerformed

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void jLabel3AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jLabel3AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel3AncestorAdded

    private void jLabel7AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jLabel7AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel7AncestorAdded

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
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEntrar;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblEstatos;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
