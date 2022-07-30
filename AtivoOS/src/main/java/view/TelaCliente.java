/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.AtivoOS.view;

import java.sql.*;// traz tudo o que existe no banco de dados sql
import br.com.AtivoOS.dal.ModuloConexao; //Impota o modulo de a coxexão da pasta dal 
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
// Importa recursos da biblioteca rs2xml.java
////import net.proteanit.sql.DbUtils;

public class TelaCliente extends javax.swing.JInternalFrame {

    Connection conexao = null;
    //Cria variaveis especiais para conexão com banco de dados
    //Prepared Statenent e ResultSet são framework de pacotes java.sql
    //e servem para preparar e execultar as instruçoes sql
    PreparedStatement pst = null;
    ResultSet rs = null;

    public TelaCliente() {
        initComponents();
        //Executa o metodo de conexão
        conexao = ModuloConexao.conector();

    }

    // Comandos a baixo referente ao botão Adicionar clientes ao servidor BD
    private void adicionar() {
        String sql = "insert into cadastrocliente (nomecliente,endcliente,fonecliente,emailcliente) values(?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            //pst.setString(1, txtUserId.getText());
            pst.setString(1, txtClienteNome.getText());
            pst.setString(2, txtClienteEnd.getText());
            pst.setString(3, txtClienteFone.getText());
            pst.setString(4, txtClienteEmail.getText());
            //pst.setString(6, cboUserPerfil.getSelectedItem().toString());// Linha abaixo referente ao combo box
            // Validação dos campo obrigatorios
            if ((txtClienteNome.getText().isEmpty() || txtClienteEnd.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatorios");

            } else {

                // A linha abaixo atualiza as informaçoes do formulario para o banco de dados
                // A extrutura abaixo é usada para confirmar a inserção dos dados na tabela 
                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso");
                    
                    //Chama metodo para limpar campo limpar();
                    limpar();
                    //Linhas abaicho limpão os campos                    
                    //txtClienteNome.setText(null);
                    //txtClienteEnd.setText(null);
                    //txtClienteFone.setText(null);
                    //txtClienteEmail.setText(null);
                    //cboUserPerfil.setSelectedItem(null);

                }
            }// Final do else if (txtUserId.getText().isEmpty())

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    

    private void remover() {

        // A estrutura abaixo a remoção do usuario
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja excluir o Usuario?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_NO_OPTION) {

            String sql = "delete from cadastrocliente where idcliente = ? ";

            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtClienteId.getText());
                pst.executeUpdate();

                //Linhas abaicho limpão os campos
                txtClienteNome.setText(null);
                txtClienteEnd.setText(null);
                txtClienteFone.setText(null);
                txtClienteEmail.setText(null);
                //cboUserPerfil.setSelectedItem(null);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }

    }

    private void pesquisa_cliente() {
        String sql = "select   idcliente as id, nomecliente as nome,endcliente as endereço,fonecliente as fone, emailcliente as email from cadastrocliente where nomecliente like ?";
        
        
        try {
            pst = conexao.prepareStatement(sql);
            // passando o conteudo da caixa pesquisa para o ?
            // atenção ao porcentagem que é a continuação da string sql
            pst.setString(1,txtClientebusca.getText()+"%");
            rs=pst.executeQuery();
            // Alinha abaixo usa a biblioteca  rs2xml.java para preencher a tabela
            tblClientes.setModel(DbUtils.resultSetToTableModel(rs));
            
            
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
        }

    }
    
    
    // Metodo para setar os campos do formulario com o conteudo da tabela 
    public void setar_campos(){
        int setar = tblClientes.getSelectedRow();
        txtClienteId.setText(tblClientes.getModel().getValueAt(setar, 0).toString());
        txtClienteNome.setText(tblClientes.getModel().getValueAt(setar, 1).toString());
        txtClienteEnd.setText(tblClientes.getModel().getValueAt(setar, 2).toString());
        txtClienteFone.setText(tblClientes.getModel().getValueAt(setar, 3).toString());
        txtClienteEmail.setText(tblClientes.getModel().getValueAt(setar, 4).toString());
        
        //Alinha abaixo desabilita o botao adicionar
        btnClienteCreat.setEnabled(false);
        
    }

    private void alterar() {
        String sql = "update cadastrocliente set nomecliente=?,endcliente=?,fonecliente=?,emailcliente=? where idcliente=?";
        
        try {
            pst = conexao.prepareStatement(sql);
            
            //pst.setString(1, txtClienteId.getText());
            pst.setString(1, txtClienteNome.getText());
            pst.setString(2, txtClienteEnd.getText());
            pst.setString(3, txtClienteFone.getText());
            pst.setString(4, txtClienteEmail.getText());
            pst.setString(5, txtClienteId.getText());
            //pst.setString(6, txtClienteId.getText());
                        
            // Validação dos campo obrigatorios
            if ((txtClienteNome.getText().isEmpty()||txtClienteFone.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatorios");

            } else {

                // A linha abaixo atualiza as informaçoes do formulario para o banco de dados
                // A extrutura abaixo é usada para confirmar a inserção dos dados na tabela 
                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do cliente alterado com sucesso");

                    //Linhas abaicho limpão os campos
                    txtClienteNome.setText(null);
                    txtClienteEnd.setText(null);
                    txtClienteFone.setText(null);
                    txtClienteEmail.setText(null);
                    btnClienteCreat.setEnabled(true);

                }
            }// Final do else if (txtUserId.getText().isEmpty())

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    //Metodo para limpar campos dos formulario 
    private void limpar(){
        
        //Linhas abaicho limpão os campos
        
        txtClientebusca.setText(null);
        txtClienteId.setText(null);        
        txtClienteNome.setText(null);
        txtClienteEnd.setText(null);
        txtClienteFone.setText(null);
        txtClienteEmail.setText(null);
        // Linha abaicho limpa tabela
        ((DefaultTableModel)tblClientes.getModel()).setRowCount(0);
        
        
    }
    
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtUserLogin = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cboUserPerfil = new javax.swing.JComboBox<>();
        txtUserSenha = new javax.swing.JPasswordField();
        jPanel3 = new javax.swing.JPanel();
        btnClienteCreat = new javax.swing.JButton();
        btnUserDelet = new javax.swing.JButton();
        btnUserUpdate = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtClienteNome = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtClienteEnd = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtClienteFone = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtClienteEmail = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtClienteId = new javax.swing.JTextField();
        txtClientebusca = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel4.setText("*Login");

        jLabel5.setText("*Senha");

        jLabel11.setText("*Perfil");

        cboUserPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Usuario" }));

        txtUserSenha.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(23, 23, 23)
                        .addComponent(txtUserLogin))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtUserSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(25, 25, 25)
                                .addComponent(cboUserPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 15, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtUserLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtUserSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cboUserPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Tela Clientes");
        setEnabled(false);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnClienteCreat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/AtivoOS/icones/creat.png"))); // NOI18N
        btnClienteCreat.setToolTipText("Adicionar");
        btnClienteCreat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClienteCreat.setPreferredSize(new java.awt.Dimension(70, 70));
        btnClienteCreat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClienteCreatActionPerformed(evt);
            }
        });

        btnUserDelet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/AtivoOS/icones/delet.png"))); // NOI18N
        btnUserDelet.setToolTipText("Excluir");
        btnUserDelet.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUserDelet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserDeletActionPerformed(evt);
            }
        });

        btnUserUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/AtivoOS/icones/updat.png"))); // NOI18N
        btnUserUpdate.setToolTipText("Editar");
        btnUserUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUserUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnClienteCreat, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUserUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUserDelet, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnUserDelet, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnUserUpdate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(btnClienteCreat, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setText("*Nome");

        txtClienteNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClienteNomeActionPerformed(evt);
            }
        });

        jLabel9.setText("*Endereço");

        jLabel10.setText("*Fone");

        jLabel1.setText("Email");

        jLabel3.setText("Id Cliente");

        txtClienteId.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtClienteNome, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtClienteEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtClienteId, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtClienteFone, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtClienteEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtClienteId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtClienteNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtClienteEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtClienteFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txtClienteEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        txtClientebusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClientebuscaKeyReleased(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/AtivoOS/icones/iconLocalizar.png"))); // NOI18N

        tblClientes = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex,int colIndex){
                return false;

            }
        };
        tblClientes.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Nome", "Endereço", "Fone", "Email"
            }
        ));
        tblClientes.setFocusable(false);
        tblClientes.getTableHeader().setReorderingAllowed(false);
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtClientebusca, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtClientebusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        setBounds(0, 0, 750, 525);
    }// </editor-fold>//GEN-END:initComponents

    private void txtClienteNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClienteNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClienteNomeActionPerformed

    private void btnUserUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserUpdateActionPerformed
        // TODO add your handling code here:
        alterar();
        
    }//GEN-LAST:event_btnUserUpdateActionPerformed

    private void btnUserDeletActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserDeletActionPerformed
        // Esta linha remove o cliente o metodo remover
        remover();
    }//GEN-LAST:event_btnUserDeletActionPerformed

    private void btnClienteCreatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteCreatActionPerformed
        // Adicionando o metodo de Adicionar ao banco de dados
        adicionar();

    }//GEN-LAST:event_btnClienteCreatActionPerformed
        // O evento a baixo é enquanto tiver digitando 
    private void txtClientebuscaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClientebuscaKeyReleased
        // Chama o metodo pesquisa cliente  :
        pesquisa_cliente();
        
        
    }//GEN-LAST:event_txtClientebuscaKeyReleased

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        // Evento que serar usado para setar os eventos do mause clicando:
        setar_campos();
        
    }//GEN-LAST:event_tblClientesMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClienteCreat;
    private javax.swing.JButton btnUserDelet;
    private javax.swing.JButton btnUserUpdate;
    private javax.swing.JComboBox<String> cboUserPerfil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtClienteEmail;
    private javax.swing.JTextField txtClienteEnd;
    private javax.swing.JTextField txtClienteFone;
    private javax.swing.JTextField txtClienteId;
    private javax.swing.JTextField txtClienteNome;
    private javax.swing.JTextField txtClientebusca;
    private javax.swing.JTextField txtUserLogin;
    private javax.swing.JPasswordField txtUserSenha;
    // End of variables declaration//GEN-END:variables
}
