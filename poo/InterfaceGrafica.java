import java.awt.*;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

import java.awt.event.*;
import java.sql.*;
import java.text.ParseException;
import java.util.*;


public class InterfaceGrafica extends JFrame implements ActionListener{
    ConexaoBancoDeDados objBancoDeDados;
    
    private JButton botaoCalcularIMC;
    private JButton botaoCadastrar;
    private JButton botaoAlterar;
    private JButton botaoRemover;
    private JButton botaoListagem;
    private JButton botaoRelatorio;
    
    private JLabel LabelNome;
    private JLabel LabelEndereco;
    private JLabel LabelTelefone;
    private JLabel LabelCPF;
    private JLabel LabelTipoSanguineo;
    private JLabel LabelFatorRH;
    private JLabel LabelCurso;
    private JLabel LabelContatoDeEmergencia;
    private JLabel LabelTelefoneContatoE;
    private JLabel LabelAltura;
    private JLabel LabelPeso;
    private JLabel LabelIMC;
    private JLabel LabelId;
    //private JLabel LabelValorIMC;
    private JLabel LabelResultadoPesquisa;
    private JLabel LabelMensagemIMC;
    private JLabel TextIMC;

    
    private JTextField TextNome;
    private JTextField TextEndereco;
    private JFormattedTextField TextTelefone;
    private JFormattedTextField TextCPF;
    private JComboBox<String> comboBoxSanguineo;
    private JComboBox<String> comboBoxFatorRH;
    private JComboBox<String> comboBoxCurso;
    private JTextField TextContatoDeEmergencia;
    private JFormattedTextField TextTelefoneContatoE;
    private JTextField TextAltura;
    private JTextField TextPeso;
    private JTextField Textid;
    
    
    private JTextArea listaPesquisaBancoDeDados;
    private JScrollPane scrollPesquisaBancoDeDados;
    
    private Container janelaPrincipal;
    
    public InterfaceGrafica() {
        setSize(1360,800);
        setTitle("Tela Inicial");
        janelaPrincipal = getContentPane();
        janelaPrincipal.setLayout(null);

        String[] tiposSanguineos = {"A", "B", "AB", "O"};
        comboBoxSanguineo = new JComboBox<>(tiposSanguineos);
        comboBoxSanguineo.setBounds(150, 160, 100, 25);
        janelaPrincipal.add(comboBoxSanguineo);

        String[] fatoresRH = {"+", "-"};
        comboBoxFatorRH = new JComboBox<>(fatoresRH);
        comboBoxFatorRH.setBounds(150, 195, 100, 25);
        janelaPrincipal.add(comboBoxFatorRH);

        String[] cursos = {
            "Direito", "Ciência da Computação", 
            "Sistemas de Informação", "Medicina", 
            "Psicologia", "Nutrição"
        };
        comboBoxCurso = new JComboBox<>(cursos);
        comboBoxCurso.setBounds(150, 230, 200, 25);
        janelaPrincipal.add(comboBoxCurso);

        try {
            
            MaskFormatter maskTelefone = new MaskFormatter("(##) #####-####");
            maskTelefone.setPlaceholderCharacter('_');
            TextTelefone = new JFormattedTextField(maskTelefone);
            TextTelefone.setBounds(150, 90, 200, 25);
            janelaPrincipal.add(TextTelefone);

            
            MaskFormatter maskCPF = new MaskFormatter("###.###.###-##");
            maskCPF.setPlaceholderCharacter('_');
            TextCPF = new JFormattedTextField(maskCPF);
            TextCPF.setBounds(150, 125, 200, 25);
            janelaPrincipal.add(TextCPF);

            
            MaskFormatter maskTelefoneContato = new MaskFormatter("(##) #####-####");
            maskTelefoneContato.setPlaceholderCharacter('_');
            TextTelefoneContatoE = new JFormattedTextField(maskTelefoneContato);
            TextTelefoneContatoE.setBounds(150, 300, 200, 25);
            janelaPrincipal.add(TextTelefoneContatoE);

        } catch (ParseException e) {
            e.printStackTrace(); 
        }


        
        
        
        TextNome = new JTextField();
        TextEndereco = new JTextField();
        TextContatoDeEmergencia = new JTextField();
        TextAltura = new JTextField();
        TextPeso = new JTextField();
        Textid = new JTextField();
        
        
        
        LabelNome = new JLabel("Nome");
        LabelEndereco = new JLabel("Endereco");
        LabelTelefone = new JLabel("Telefone");
        LabelCPF = new JLabel("CPF");
        LabelTipoSanguineo = new JLabel("Tipo Sanguineo");
        LabelFatorRH = new JLabel("CPF");
        LabelCurso = new JLabel("Curso");
        LabelContatoDeEmergencia = new JLabel("Contato de Emergencia");
        LabelTelefoneContatoE = new JLabel("Telefone Contato de Emergencia");
        LabelAltura = new JLabel("Altura");
        LabelPeso = new JLabel("Peso");
        LabelIMC = new JLabel("IMC");
        LabelIMC = new JLabel("IMC");
        LabelMensagemIMC = new JLabel("Seu IMC é de: ");
        LabelId = new JLabel("ID");
        LabelResultadoPesquisa = new JLabel("Resultado da pesquisa no Banco de Dados");
        TextIMC = new JLabel("");
        //LabelValorIMC = new JLabel("imc");
        
        
        botaoCalcularIMC = new JButton("Calcular IMC");
        botaoCadastrar = new JButton("Inserir");
        botaoAlterar = new JButton("Alterar");
        botaoRemover = new JButton("Remover");
        botaoListagem = new JButton("Listagem");
        botaoRelatorio = new JButton("Relatorio");
        
        listaPesquisaBancoDeDados = new JTextArea();
        scrollPesquisaBancoDeDados = new JScrollPane(listaPesquisaBancoDeDados);
        
        
        
        LabelNome.setBounds(10, 20, 80, 20);
        LabelEndereco.setBounds(10, 55, 80, 20);
        LabelTelefone.setBounds(10, 90, 80, 20);
        LabelCPF.setBounds(10, 125, 80, 20);
        LabelTipoSanguineo.setBounds(10, 160, 100, 20);
        LabelFatorRH.setBounds(10, 195, 100, 20);
        LabelCurso.setBounds(10, 230, 80, 20);
        LabelContatoDeEmergencia.setBounds(10, 265, 140, 20);
        LabelTelefoneContatoE.setBounds(10, 300, 140, 20);
        LabelAltura.setBounds(10, 335, 80, 20);
        LabelPeso.setBounds(10, 370, 80, 20);
        LabelIMC.setBounds(10, 405, 80, 20);
        LabelMensagemIMC.setBounds(10, 405, 80, 20);
        //LabelValorIMC.setBounds(240, 405, 80, 20);
        LabelId.setBounds(220, 450, 30, 30);
        LabelResultadoPesquisa.setBounds(480, 5, 300, 20);


        TextNome.setBounds(150, 20, 200, 25);
        TextEndereco.setBounds(150, 55, 200, 25);
        TextTelefone.setBounds(150, 90, 200, 25);
        TextCPF.setBounds(150, 125, 200, 25);
        comboBoxSanguineo.setBounds(150, 160, 100, 25);
        comboBoxFatorRH.setBounds(150, 195, 100, 25);
        comboBoxCurso.setBounds(150, 230, 200, 25);
        TextContatoDeEmergencia.setBounds(150, 265, 200, 25);
        TextTelefoneContatoE.setBounds(150, 300, 200, 25);
        TextAltura.setBounds(150, 335, 80, 25);
        TextPeso.setBounds(150, 370, 80, 25);
        TextIMC.setBounds(150, 405, 80, 25);
        Textid.setBounds(250, 450, 50, 30);

        botaoCalcularIMC.setBounds(10, 450, 150, 30);
        botaoCadastrar.setBounds(10, 490, 120, 30);
        botaoAlterar.setBounds(140, 490, 120, 30);
        botaoRemover.setBounds(270, 490, 120, 30);
        botaoListagem.setBounds(10, 530, 120, 30);
        botaoRelatorio.setBounds(140, 530, 120, 30);

        scrollPesquisaBancoDeDados.setBounds(400, 20, 450, 300);
        
        
        janelaPrincipal.add(TextNome); 
        janelaPrincipal.add(TextEndereco); 
        janelaPrincipal.add(TextTelefone); 
        janelaPrincipal.add(TextCPF); 
        janelaPrincipal.add(comboBoxSanguineo); 
        janelaPrincipal.add(comboBoxFatorRH); 
        janelaPrincipal.add(comboBoxCurso); 
        janelaPrincipal.add(TextContatoDeEmergencia); 
        janelaPrincipal.add(TextTelefoneContatoE); 
        janelaPrincipal.add(TextAltura); 
        janelaPrincipal.add(TextPeso); 
        janelaPrincipal.add(TextIMC); 
        janelaPrincipal.add(Textid);

        janelaPrincipal.add(LabelResultadoPesquisa);
        janelaPrincipal.add(scrollPesquisaBancoDeDados);
        
        
        janelaPrincipal.add(LabelNome);
        janelaPrincipal.add(LabelEndereco);
        janelaPrincipal.add(LabelTelefone);
        janelaPrincipal.add(LabelCPF);
        janelaPrincipal.add(LabelTipoSanguineo);
        janelaPrincipal.add(LabelFatorRH);
        janelaPrincipal.add(LabelCurso);
        janelaPrincipal.add(LabelContatoDeEmergencia);
        janelaPrincipal.add(LabelTelefoneContatoE);
        janelaPrincipal.add(LabelAltura);
        janelaPrincipal.add(LabelPeso);
        janelaPrincipal.add(LabelIMC);
        //janelaPrincipal.add(LabelValorIMC);
        janelaPrincipal.add(LabelId);

        

        janelaPrincipal.add(botaoCalcularIMC);
        janelaPrincipal.add(botaoCadastrar);
        janelaPrincipal.add(botaoAlterar);
        janelaPrincipal.add(botaoRemover);
        janelaPrincipal.add(botaoListagem);
        janelaPrincipal.add(botaoRelatorio);
        
        setVisible(true);

        botaoCalcularIMC.addActionListener(this);
        botaoCadastrar.addActionListener(this);
        botaoAlterar.addActionListener(this);
        botaoRemover.addActionListener(this);
        botaoListagem.addActionListener(this);
        botaoRelatorio.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("Inserir")){
            try{
                String telefone = "";
                String cpf = "";
                String telefoneEmergencia = "";

                for(int i = 0; i < TextTelefone.getText().length(); i++) {
                    if(TextTelefone.getText().charAt(i) != '(' && TextTelefone.getText().charAt(i) != ')' && TextTelefone.getText().charAt(i) != '-' && TextTelefone.getText().charAt(i) != ' ') telefone += TextTelefone.getText().charAt(i);
                    if(TextTelefoneContatoE.getText().charAt(i) != '(' && TextTelefoneContatoE.getText().charAt(i) != ')' && TextTelefoneContatoE.getText().charAt(i) != '-' && TextTelefoneContatoE.getText().charAt(i) != ' ') telefoneEmergencia += TextTelefoneContatoE.getText().charAt(i);
                }

                for(int i = 0; i < TextCPF.getText().length(); i++) {
                    if(TextCPF.getText().charAt(i) != '-' && TextCPF.getText().charAt(i) != '.') cpf += TextCPF.getText().charAt(i);
                }

                System.out.println(telefone);
                Pessoa objeto = new Pessoa(TextNome.getText(), TextEndereco.getText(), telefone, cpf, (String)comboBoxSanguineo.getSelectedItem(), (String)comboBoxFatorRH.getSelectedItem(), (String)comboBoxCurso.getSelectedItem(), TextContatoDeEmergencia.getText(), telefoneEmergencia, Double.parseDouble(TextAltura.getText()), Double.parseDouble(TextPeso.getText()));
                ConexaoBancoDeDados objBancoDeDados = new ConexaoBancoDeDados();
                String mensagem = objBancoDeDados.InserirDadosPessoa(objeto);
                JOptionPane.showMessageDialog(this, mensagem);
                // LabelMensagem.setText(mensagem);
            }
            catch (SQLException e1){
                e1.printStackTrace();
            }
        }

        if(e.getActionCommand().equals("Remover")){
            try{
                ConexaoBancoDeDados objBancoDeDados = new ConexaoBancoDeDados();
                int id = Integer.parseInt(Textid.getText());
                String mensagem = objBancoDeDados.RemoverPessoa(id);
                JOptionPane.showMessageDialog(this, mensagem);
                // LabelMensagem.setText(mensagem);
            }
            catch (SQLException e1){
                e1.printStackTrace();
            }
        }

        if(e.getActionCommand().equals("Alterar")){
            try{
                Pessoa objeto = new Pessoa(TextNome.getText(), TextEndereco.getText(), TextTelefone.getText(), TextCPF.getText(), (String)comboBoxSanguineo.getSelectedItem(), (String)comboBoxFatorRH.getSelectedItem(), (String)comboBoxCurso.getSelectedItem(), TextContatoDeEmergencia.getText(), TextTelefoneContatoE.getText(), Double.parseDouble(TextAltura.getText()), Double.parseDouble(TextPeso.getText()));
                ConexaoBancoDeDados objBancoDeDados = new ConexaoBancoDeDados();
                int id = Integer.parseInt(Textid.getText());
                String mensagem = objBancoDeDados.AlterarDadosPessoa(objeto, id);
                JOptionPane.showMessageDialog(this, mensagem);
                // LabelMensagem.setText(mensagem);
            }
            catch (SQLException e1){
                e1.printStackTrace();
            }
        }
        
        if(e.getActionCommand().equals("Relatorio")){
            try{
                ConexaoBancoDeDados objBancoDeDados = new ConexaoBancoDeDados();
                ArrayList<String> relatorioBancoDeDados = objBancoDeDados.Relatorio();
                
                listaPesquisaBancoDeDados.setText("");
                for(String texto: relatorioBancoDeDados){
                    listaPesquisaBancoDeDados.append(texto + "\n");
                }
                System.out.println(relatorioBancoDeDados);
            } catch(SQLException e1){
                e1.printStackTrace();
            }
        }

        if(e.getActionCommand().equals("Listagem")){
            try{
                ConexaoBancoDeDados objBancoDeDados = new ConexaoBancoDeDados();
                ArrayList<Pessoa> listagemBancoDeDados = objBancoDeDados.ListarPessoas();

                listaPesquisaBancoDeDados.setText("");
                for(Pessoa pessoa : listagemBancoDeDados){
                    String info = pessoa.getNome() + " | " + pessoa.getEndereco() + pessoa.getTelefone() + " | " + pessoa.getAltura() + " | " + pessoa.getCpf() + " | " + pessoa.getCurso() + " | " + pessoa.getContatoEmergencia() + " | " + pessoa.getTelefoneContatoEmergencia() + " | " + pessoa.getImc() + " | " + pessoa.getTipoSanguineo() + pessoa.getFatorRh();
                    listaPesquisaBancoDeDados.append(info + "\n\n");
                }
            } catch(SQLException e1){
                e1.printStackTrace();
            }
        }

        if(e.getActionCommand().equals("Calcular IMC")){
            try{
                ConexaoBancoDeDados objBancoDeDados = new ConexaoBancoDeDados();
                Pessoa pessoa = objBancoDeDados.getOne(Integer.parseInt(Textid.getText()));

                pessoa.calcularIMC();

                TextIMC.setText("" + pessoa.getImc());
                String mensagem = pessoa.getImc() < 18.5 ? "Você está abaixo do peso ideal!" : pessoa.getImc() > 25 ? "Você está acima do peso ideal!" : "Peso ideal";
                JOptionPane.showMessageDialog(this, mensagem);
            } catch(SQLException e1){
                e1.printStackTrace();
            }
        }
    }
}