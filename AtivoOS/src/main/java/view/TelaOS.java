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
import net.proteanit.sql.DbUtils;

public class TelaOS extends javax.swing.JInternalFrame {

    //private static final long serialVersionUID = 1L;
    Connection conexao = null;
    //Cria variaveis especiais para conexão com banco de dados
    //Prepared Statenent e ResultSet são framework de pacotes java.sql
    //e servem para preparar e execultar as instruçoes sql
    PreparedStatement pst = null;
    ResultSet rs = null;

    // Alinha abaixo cria uma variavel para armazenar um texto de acordo com 
    //radion button selecionado.
    private String tipo;

    public TelaOS() {
        initComponents();
        conexao = ModuloConexao.conector();

    }

    private void pesquisa_cliente() {
        // String sql = "select   idcliente as id, nomecliente as nome,endcliente as endereço,fonecliente as fone, emailcliente as email from cadastrocliente where nomecliente like ?";
        String sql = " select idcliente as id,nomecliente as nome,fonecliente as fone from cadastrocliente where nomecliente like ?";

        try {
            pst = conexao.prepareStatement(sql);
            // passando o conteudo da caixa pesquisa para o ?
            // atenção ao porcentagem que é a continuação da string sql
            pst.setString(1, txtClientebusca.getText() + "%");
            rs = pst.executeQuery();
            // Alinha abaixo usa a biblioteca  rs2xml.java para preencher a tabela
            tblClientes.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    // Metodo para setar os campos do formulario com o conteudo da tabela 
    public void setar_campos() {
        int setar = tblClientes.getSelectedRow();
        txtClienteId.setText(tblClientes.getModel().getValueAt(setar, 0).toString());

    }

    // Metodo para cadastrar uma ordem de serviço
    private void emitir_os() {
        String sql = "insert into tabelaordemservico (tipo,situacao,equipamento,defeito,servico,tecnico,valor,idcliente) values(?,?,?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);

            //pst.setString(1, txtUserId.getText());
            pst.setString(1, tipo);
            pst.setString(2, cbOSLista.getSelectedItem().toString());
            pst.setString(3, txtOSEquipamento.getText());
            pst.setString(4, txtOSDefeito.getText());
            pst.setString(5, txtOSServico.getText());
            pst.setString(6, txtOSTecnico.getText());
            pst.setString(7, txtOSValor.getText());
            pst.setString(8, txtClienteId.getText());
            //pst.setString(6, cboUserPerfil.getSelectedItem().toString());// Linha abaixo referente ao combo box
            // Validação dos campo obrigatorios
            if ((txtClienteId.getText().isEmpty() || txtOSDefeito.getText().isEmpty() || txtOSDefeito.getText().isEmpty())|| cbOSLista.getSelectedItem().equals("")) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatorios");

            } else {

                // A linha abaixo atualiza as informaçoes do formulario para o banco de dados
                // A extrutura abaixo é usada para confirmar a inserção dos dados na tabela 
                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "O.S emitida  com sucesso");
                    btnOSCreat.setEnabled(false);
                    btnUserRead.setEnabled(false);
                    btnOSPrint.setEnabled(true);
                    
                    
                    //Chama metodo para limpar campo limpar();
                    //limpar();
                    //Linhas abaicho limpão os campos                    
                    txtClienteId.setText(null);
                    txtOSEquipamento.setText(null);
                    txtOSDefeito.setText(null);
                    txtOSServico.setText(null);
                    txtOSTecnico.setText(null);
                    txtOSValor.setText(null);
                    
                     

                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // Metodo para pesquisar uma O.S 
    private void pesquisar_os() {
        // Alinha abaixo cria uma caixa de entrada tipo JOptionPane
        String num_os = JOptionPane.showInputDialog("Numeros de O.S");
        String sql = "select os,date_format(data_os,'%d/%m/%Y - %H:%i'),\n" +
"tipo,situacao,equipamento,defeito,servico,tecnico,valor,idcliente from tabelaordemservico where os=" + num_os;
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs.next()) {
                txtNumeroOS.setText(rs.getString(1));
                txtData.setText(rs.getString(2));
                String rbtTipo = rs.getString(3);
                if (rbtTipo.equals("OS")) {
                    rbOrdenServico.setSelected(true);
                    tipo = "OS";

                } else {
                    rbOrcamento.setSelected(true);
                    tipo = "Orçamento";
                }
                cbOSLista.setSelectedItem(rs.getString(4));
                txtOSEquipamento.setText(rs.getString(5));
                txtOSDefeito.setText(rs.getString(6));
                txtOSServico.setText(rs.getString(7));
                txtOSTecnico.setText(rs.getString(8));
                txtOSValor.setText(rs.getString(9));
                txtClienteId.setText(rs.getString(10));

                // Evitando problemas 
                btnOSCreat.setEnabled(false);
                btnUserRead.setEnabled(false);
                //txtClientebusca.setEnabled(false);
                tblClientes.setVisible(false);
                // ativar demais botoes 
                
                btnOSUpdate.setEnabled(true);
                btnOSDelet.setEnabled(true);
                btnOSPrint.setEnabled(true);
                
                

            } else {
                JOptionPane.showMessageDialog(null, "O.S não cadastrada");
            }

            // Alinha abaixo usa a biblioteca  rs2xml.java para preencher a tabela
            // tblClientes.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "O.S invalida");
            //System.out.println("e");       
        }
    }

    // Metodo para alterar O.S
    private void alterar_os() {
        String sql = "update tabelaordemservico set tipo=?,situacao=?,equipamento=?,defeito=?,servico=?,tecnico=?,valor=? where os=?";
        try {
            pst = conexao.prepareStatement(sql);

            //pst.setString(1, txtUserId.getText());
            pst.setString(1, tipo);
            pst.setString(2, cbOSLista.getSelectedItem().toString());
            pst.setString(3, txtOSEquipamento.getText());
            pst.setString(4, txtOSDefeito.getText());
            pst.setString(5, txtOSServico.getText());
            pst.setString(6, txtOSTecnico.getText());
            pst.setString(7, txtOSValor.getText());
            pst.setString(8, txtNumeroOS.getText());
            //pst.setString(6, cboUserPerfil.getSelectedItem().toString());// Linha abaixo referente ao combo box
            // Validação dos campo obrigatorios
            if ((txtClienteId.getText().isEmpty() || txtOSDefeito.getText().isEmpty() || txtOSDefeito.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatorios");

            } else {

                // A linha abaixo atualiza as informaçoes do formulario para o banco de dados
                // A extrutura abaixo é usada para confirmar a inserção dos dados na tabela 
                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "O.S alterada  com sucesso");

                    //Chama metodo para limpar campo limpar();
                    //limpar();
                    //Linhas abaicho limpão os campos  
                    txtClienteId.setText(null);
                    txtClientebusca.setText(null);
                    txtData.setText(null);
                    cbOSLista.setSelectedItem(" ");
                    txtNumeroOS.setText(null);
                    txtOSEquipamento.setText(null);
                    txtOSDefeito.setText(null);
                    txtOSServico.setText(null);
                    txtOSTecnico.setText(null);
                    txtOSValor.setText(null);
                    // Linha abaicho limpa tabela
                    ((DefaultTableModel)tblClientes.getModel()).setRowCount(0);
        
                    // Linha abaicho abilita botoes desativados
                    btnOSCreat.setEnabled(true);
                    
                    btnUserRead.setEnabled(true);
                    txtClientebusca.setEnabled(true);
                    tblClientes.setVisible(true);
                    
                    btnOSUpdate.setEnabled(false);
                    btnOSDelet.setEnabled(false);
                    btnOSPrint.setEnabled(false);

                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void remover_os() {

        // A estrutura abaixo a remoção do usuario
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja excluir o Usuario?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_NO_OPTION) {

            String sql = "delete from tabelaordemservico where os = ? ";

            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtNumeroOS.getText());
                pst.executeUpdate();

                //Linhas abaicho limpão os campos
                txtClienteId.setText(null);
                txtData.setText(null);
                txtNumeroOS.setText(null);
                txtOSEquipamento.setText(null);
                txtOSDefeito.setText(null);
                txtOSServico.setText(null);
                txtOSTecnico.setText(null);
                txtOSValor.setText(null);
                //Linha abaicho abilita botoes desativados
                btnUserRead.setEnabled(true);
                btnOSCreat.setEnabled(true);
                txtClientebusca.setEnabled(true);
                tblClientes.setVisible(true);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNumeroOS = new javax.swing.JTextField();
        txtData = new javax.swing.JTextField();
        rbOrcamento = new javax.swing.JRadioButton();
        rbOrdenServico = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        txtClientebusca = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtClienteId = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbOSLista = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtOSEquipamento = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtOSDefeito = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtOSServico = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtOSTecnico = new javax.swing.JTextField();
        txtOSValor = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnOSCreat = new javax.swing.JButton();
        btnOSDelet = new javax.swing.JButton();
        btnOSUpdate = new javax.swing.JButton();
        btnOSPrint = new javax.swing.JButton();
        btnUserRead = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Tela O.S");
        setEnabled(false);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Nº da OS");

        jLabel2.setText("Data");

        txtNumeroOS.setEditable(false);

        txtData.setEditable(false);

        buttonGroup1.add(rbOrcamento);
        rbOrcamento.setText("Orçamento");
        rbOrcamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbOrcamentoActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbOrdenServico);
        rbOrdenServico.setText("OS");
        rbOrdenServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbOrdenServicoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rbOrcamento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rbOrdenServico)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtNumeroOS, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtData)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumeroOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbOrcamento)
                    .addComponent(rbOrdenServico))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblClientes = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex,int colIndex){
                return false;

            }
        };
        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Nome", "Fone"
            }
        ));
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        txtClientebusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClientebuscaActionPerformed(evt);
            }
        });
        txtClientebusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClientebuscaKeyReleased(evt);
            }
        });

        jLabel3.setText("*Id");

        txtClienteId.setEditable(false);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/AtivoOS/icones/iconLocalizar.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtClientebusca, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(txtClienteId, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtClientebusca)
                    .addComponent(txtClienteId)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel5.setText("Situação");

        cbOSLista.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Na Bancada", "Entrega OK", "Orçamento Reprovado", "Aguardando Aprovação", "Aguardando Peças", "Abandonado pelo Cliente", "Retornou" }));

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setText("*Equipamento");

        jLabel7.setText("*Defeito");

        jLabel8.setText("Serviço");

        jLabel9.setText("Tecnico");

        txtOSValor.setText("0");

        jLabel10.setText("Valor Total");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtOSTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtOSValor, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
                    .addComponent(txtOSServico)
                    .addComponent(txtOSDefeito)
                    .addComponent(txtOSEquipamento))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtOSEquipamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtOSDefeito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtOSServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(txtOSValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(txtOSTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnOSCreat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/AtivoOS/icones/creat.png"))); // NOI18N
        btnOSCreat.setToolTipText("Adicionar");
        btnOSCreat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOSCreat.setPreferredSize(new java.awt.Dimension(70, 70));
        btnOSCreat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOSCreatActionPerformed(evt);
            }
        });

        btnOSDelet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/AtivoOS/icones/delet.png"))); // NOI18N
        btnOSDelet.setToolTipText("Excluir");
        btnOSDelet.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOSDelet.setEnabled(false);
        btnOSDelet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOSDeletActionPerformed(evt);
            }
        });

        btnOSUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/AtivoOS/icones/updat.png"))); // NOI18N
        btnOSUpdate.setToolTipText("Editar");
        btnOSUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOSUpdate.setEnabled(false);
        btnOSUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOSUpdateActionPerformed(evt);
            }
        });

        btnOSPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/AtivoOS/icones/iconPrint.png"))); // NOI18N
        btnOSPrint.setToolTipText("Imprimir");
        btnOSPrint.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOSPrint.setEnabled(false);
        btnOSPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOSPrintActionPerformed(evt);
            }
        });

        btnUserRead.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/AtivoOS/icones/read.png"))); // NOI18N
        btnUserRead.setToolTipText("Localizar");
        btnUserRead.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUserRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserReadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnOSCreat, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUserRead, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(btnOSUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnOSDelet, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnOSPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(276, 276, 276))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnOSUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnOSPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnOSDelet, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnUserRead, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnOSCreat, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cbOSLista, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbOSLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        setBounds(0, 0, 760, 525);
    }// </editor-fold>//GEN-END:initComponents

    private void rbOrcamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbOrcamentoActionPerformed
        // A linha abaixo atribui um texto a variavel tipo radiun button se tiver selecionado  :
        tipo = "Orçamento";


    }//GEN-LAST:event_rbOrcamentoActionPerformed

    private void txtClientebuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClientebuscaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClientebuscaActionPerformed

    private void btnOSCreatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOSCreatActionPerformed
        // Adicionando o metodo emitir o.s de Adicionar ao banco de dados
        emitir_os();


    }//GEN-LAST:event_btnOSCreatActionPerformed

    private void btnOSDeletActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOSDeletActionPerformed
        // Chama metodo para remover O.S
        remover_os();

    }//GEN-LAST:event_btnOSDeletActionPerformed

    private void btnOSUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOSUpdateActionPerformed
        // Chama o metodo alterar O.S:
        alterar_os();

    }//GEN-LAST:event_btnOSUpdateActionPerformed

    private void btnOSPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOSPrintActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOSPrintActionPerformed

    private void txtClientebuscaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClientebuscaKeyReleased
        // Chama o metodo pesquisar cliente:
        pesquisa_cliente();

    }//GEN-LAST:event_txtClientebuscaKeyReleased

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        // Chamando o metodo seta campos :
        setar_campos();

    }//GEN-LAST:event_tblClientesMouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked

    }//GEN-LAST:event_jLabel4MouseClicked

    private void btnUserReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserReadActionPerformed
        // Chama o metodo pesquisar O.S:
        pesquisar_os();


    }//GEN-LAST:event_btnUserReadActionPerformed

    private void rbOrdenServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbOrdenServicoActionPerformed
        // A linha abaixo atribui um texto a variavel tipo radiun button se tiver selecionado  :
        tipo = "OS";

    }//GEN-LAST:event_rbOrdenServicoActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // Ao abrir o forme marcar o rediun orçamento:
        rbOrcamento.setSelected(true);
        tipo = "Orçamento";


    }//GEN-LAST:event_formInternalFrameOpened


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOSCreat;
    private javax.swing.JButton btnOSDelet;
    private javax.swing.JButton btnOSPrint;
    private javax.swing.JButton btnOSUpdate;
    private javax.swing.JButton btnUserRead;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbOSLista;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbOrcamento;
    private javax.swing.JRadioButton rbOrdenServico;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtClienteId;
    private javax.swing.JTextField txtClientebusca;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtNumeroOS;
    private javax.swing.JTextField txtOSDefeito;
    private javax.swing.JTextField txtOSEquipamento;
    private javax.swing.JTextField txtOSServico;
    private javax.swing.JTextField txtOSTecnico;
    private javax.swing.JTextField txtOSValor;
    // End of variables declaration//GEN-END:variables

    private void getRowCount(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
