import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class ConexaoBancoDeDados {
    private Connection conexao;
    private String URL_BancoDeDados;
    private String usuario;
    private String senha;

    public ConexaoBancoDeDados(){
        URL_BancoDeDados = "jdbc:mysql://localhost:3306/a";
        usuario = "root";
        senha = "root";
    }

    public String IniciarConexao(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(URL_BancoDeDados, usuario, senha);
            return "Funcionou";
        } catch (Exception e) {
            e.printStackTrace();
            return "Deu errado";
        }
    }

    private void EncerrarConexao() throws SQLException{
        if(conexao != null){
            conexao.close();
        }
    }
    
    public String InserirDadosPessoa(Pessoa objetoPessoa) throws SQLException {
        IniciarConexao();
    
        if (conexao != null) {
            PreparedStatement psInsert = conexao.prepareStatement("INSERT INTO pessoa (nome, endereco, telefone, cpf, tipo_sanguineo, fator_rh, curso, " +
                         "contato_emergencia, telefone_emergencia, altura, peso, imc) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            psInsert.setString(1, objetoPessoa.getNome());
            psInsert.setString(2, objetoPessoa.getEndereco());
            psInsert.setString(3, objetoPessoa.getTelefone());
            psInsert.setString(4, objetoPessoa.getCpf());
            psInsert.setString(5, objetoPessoa.getTipoSanguineo());
            psInsert.setString(6, objetoPessoa.getFatorRh());
            psInsert.setString(7, objetoPessoa.getCurso());
            psInsert.setString(8, objetoPessoa.getContatoEmergencia());
            psInsert.setString(9, objetoPessoa.getTelefoneContatoEmergencia());
            psInsert.setDouble(10, objetoPessoa.getAltura());
            psInsert.setDouble(11, objetoPessoa.getPeso());
            psInsert.setDouble(12, objetoPessoa.getImc());
    
            psInsert.execute();
            EncerrarConexao();
            
            return "Inserção Realizado com Sucesso!";
        } else {
            return "Erro ao acessar o Banco de Dados. Tente novamente!";
        }
    }
    
    public String AlterarDadosPessoa(Pessoa objetoPessoa, int id) throws SQLException {
        IniciarConexao();
    
        if (conexao != null) {
            PreparedStatement psInsert = conexao.prepareStatement("UPDATE Pessoa SET nome=?, endereco=?, telefone=?, cpf=?, tipo_sanguineo=?, fator_rh=?, curso=?, contato_emergencia=?, telefone_emergencia=?, altura=?, peso=?, imc=? WHERE pessoa.id = " + id);    
            String telefone = "";
            String cpf = "";
            String telefoneEmergencia = "";

            for(int i = 0; i < objetoPessoa.getTelefone().length(); i++) {
                if(objetoPessoa.getTelefone().charAt(i) != '(' && objetoPessoa.getTelefone().charAt(i) != ')' && objetoPessoa.getTelefone().charAt(i) != '-' && objetoPessoa.getTelefone().charAt(i) != ' ') telefone += objetoPessoa.getTelefone().charAt(i);
                if(objetoPessoa.getTelefoneContatoEmergencia().charAt(i) != '(' && objetoPessoa.getTelefoneContatoEmergencia().charAt(i) != ')' && objetoPessoa.getTelefoneContatoEmergencia().charAt(i) != '-' && objetoPessoa.getTelefoneContatoEmergencia().charAt(i) != ' ') telefoneEmergencia += objetoPessoa.getTelefoneContatoEmergencia().charAt(i);
            }

            for(int i = 0; i < objetoPessoa.getCpf().length(); i++) {
                if(objetoPessoa.getCpf().charAt(i) != '-' && objetoPessoa.getCpf().charAt(i) != '.') cpf += objetoPessoa.getCpf().charAt(i);
            }
            
            objetoPessoa.calcularIMC();

            psInsert.setString(1, objetoPessoa.getNome());
            psInsert.setString(2, objetoPessoa.getEndereco());
            psInsert.setString(3, telefone);
            psInsert.setString(4, cpf);
            psInsert.setString(5, objetoPessoa.getTipoSanguineo());
            psInsert.setString(6, objetoPessoa.getFatorRh());
            psInsert.setString(7, objetoPessoa.getCurso());
            psInsert.setString(8, objetoPessoa.getContatoEmergencia());
            psInsert.setString(9, telefoneEmergencia);
            psInsert.setDouble(10, objetoPessoa.getAltura());
            psInsert.setDouble(11, objetoPessoa.getPeso());
            psInsert.setDouble(12, objetoPessoa.getImc());
    
            psInsert.executeUpdate();
            EncerrarConexao();
            
            return "Alteração Realizada com Sucesso!";
        } else {
            return "Erro ao acessar o Banco de Dados. Tente novamente!";
        }
    }

    public String RemoverPessoa(int id) throws SQLException {
        IniciarConexao();
    
        if (conexao != null) {
            PreparedStatement psInsert = conexao.prepareStatement("DELETE FROM Pessoa WHERE id = " + id + ";");

            psInsert.executeUpdate();
            EncerrarConexao();
            
            return "Remoção Realizada com Sucesso!";
        } else {
            return "Erro ao acessar o Banco de Dados. Tente novamente!";
        }
    }

    public ArrayList<Pessoa> ListarPessoas() throws SQLException {
        IniciarConexao();
    
        if (conexao != null) {
            Statement psInsert = conexao.createStatement();

            ResultSet lista = psInsert.executeQuery("SELECT * FROM Pessoa;");
            ArrayList<Pessoa> p = new ArrayList<>();

            while(lista.next()) {
                Pessoa pessoa = new Pessoa(lista.getString("nome"), lista.getString("endereco"), lista.getString("telefone"), lista.getString("cpf"), lista.getString("tipo_sanguineo"), lista.getString("fator_rh"), lista.getString("curso"), lista.getString("contato_emergencia"), lista.getString("telefone_emergencia"), Double.parseDouble(lista.getString("altura")), Double.parseDouble(lista.getString("peso")));
                p.add(pessoa);
            }

            EncerrarConexao();
            
            return p;
        } else {
            return null;
        }
    }

    public ArrayList<String> Relatorio() throws SQLException {
        IniciarConexao();
        if (conexao != null) {
            Statement psInsert = conexao.createStatement();

            ResultSet relatorio = psInsert.executeQuery("SELECT * FROM Pessoa;");
            ArrayList<String> p = new ArrayList<>();

            double maiorPeso = 0, menorPeso = Double.MAX_VALUE, mediaPeso = 0, menorAltura = Double.MAX_VALUE, maiorAltura = 0, mediaAlturas = 0, mediaIMCs = 0, maiorIMC = 0, menorIMC = Double.MAX_VALUE;
            String nomeMaiorPeso = null, tipoSanguineoMaiorPeso = null, nomeMenorPeso = null, tipoSanguineoMenorPeso = null, nomeMaiorAltura = null, cursoMaiorAltura = null, nomeMenorAltura = null, cursoMenorAltura = null, nomeMaiorIMC = null, nomeMenorIMC = null;
            int totalPessoas = 0;

            while(relatorio.next()) {
                Pessoa pessoa = new Pessoa(relatorio.getString("nome"), relatorio.getString("endereco"), relatorio.getString("telefone"), relatorio.getString("cpf"), relatorio.getString("tipo_sanguineo"), relatorio.getString("fator_rh"), relatorio.getString("curso"), relatorio.getString("contato_emergencia"), relatorio.getString("telefone_emergencia"), Double.parseDouble(relatorio.getString("altura")), Double.parseDouble(relatorio.getString("peso")));
                
                if(maiorPeso < pessoa.getPeso()) {
                    maiorPeso = pessoa.getPeso();
                    nomeMaiorPeso = pessoa.getNome();
                    tipoSanguineoMaiorPeso = pessoa.getTipoSanguineo() + pessoa.getFatorRh();
                }
                if(menorPeso > pessoa.getPeso()) {
                    menorPeso = pessoa.getPeso();
                    nomeMenorPeso = pessoa.getNome();
                    tipoSanguineoMenorPeso = pessoa.getTipoSanguineo() + pessoa.getFatorRh();
                }

                mediaPeso += pessoa.getPeso();
                mediaAlturas += pessoa.getAltura();
                mediaIMCs += pessoa.getImc();
                totalPessoas++;

                if(maiorAltura < pessoa.getAltura()) {
                    maiorAltura = pessoa.getAltura();
                    nomeMaiorAltura = pessoa.getNome();
                    cursoMaiorAltura = pessoa.getCurso();
                }
                if(menorAltura > pessoa.getAltura()) {
                    menorAltura = pessoa.getAltura();
                    nomeMenorAltura = pessoa.getNome();
                    cursoMenorAltura = pessoa.getCurso();
                }

                if(maiorIMC < pessoa.getImc()) {
                    maiorIMC = pessoa.getAltura();
                    nomeMaiorIMC = pessoa.getNome();
                }
                if(menorIMC > pessoa.getImc()) {
                    maiorIMC = pessoa.getAltura();
                    nomeMenorIMC = pessoa.getNome();
                }
            }

            mediaAlturas /= totalPessoas;
            mediaPeso /= totalPessoas;
            mediaIMCs /= totalPessoas;

            p.add("Maior peso: " + maiorPeso + " | Nome: " + nomeMaiorPeso + " | Tipo Sanguineo: " + tipoSanguineoMaiorPeso);
            p.add("Menor peso: " + menorPeso + " | Nome: " + nomeMenorPeso + " | Tipo Sanguineo: " + tipoSanguineoMenorPeso);
            p.add("Media peso: " + mediaPeso);
            p.add("Maior Altura: " + maiorAltura + " | Nome: " + nomeMaiorAltura + " | Curso: " + cursoMaiorAltura);
            p.add("Menor Altura: " + menorAltura + " | Nome: " + nomeMenorAltura + " | Curso: " + cursoMenorAltura);
            p.add("Media alturas: " + mediaAlturas);
            p.add("Media IMCs: " + mediaIMCs);
            p.add("Maior IMC: " + maiorIMC + " | Nome: " + nomeMaiorIMC);
            p.add("Menor IMC: " + menorIMC + " | Nome: " + nomeMenorIMC);

            EncerrarConexao();
            return p;
        } else {
            return null;
        }
    }

    public Pessoa getOne(int id) throws SQLException {
        IniciarConexao();
    
        if (conexao != null) {
            Statement psInsert = conexao.createStatement();
            ResultSet resultado = psInsert.executeQuery("SELECT * FROM Pessoa WHERE id = " + id + ";");
            resultado.next();
            Pessoa pessoa = new Pessoa(resultado.getString("nome"), resultado.getString("endereco"), resultado.getString("telefone"), resultado.getString("cpf"), resultado.getString("tipo_sanguineo"), resultado.getString("fator_rh"), resultado.getString("curso"), resultado.getString("contato_emergencia"), resultado.getString("telefone_emergencia"), Double.parseDouble(resultado.getString("altura")), Double.parseDouble(resultado.getString("peso")));

            EncerrarConexao();
            
            return pessoa;
        } else {
            return null;
        }
    }
}