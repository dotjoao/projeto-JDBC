import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Func {
    private Connection conexao;

    public Func(Connection conexao) {
        this.conexao = conexao;
    }


    //CRIAR TABELA
    public void createTableIfNotExists( String nome, String campo1, String campo2, String campo3, String campo4) throws SQLException {
        String createTableQuery = "CREATE TABLE "+nome+ " (" + campo1+", "+campo2 + ", "+ campo3 +", "+campo4+"); ";
        System.out.println("Query SQL: " + createTableQuery);
        try (PreparedStatement createTableStatement = conexao.prepareStatement(createTableQuery)) {
            createTableStatement.execute();
        }
    }


    //INSERIR
    public void insertRecord(String campo1Value, int campo2Value, String campo3Value, String campo4Value) throws SQLException {
        String insertQuery = "INSERT INTO usuario (nome, idade, CPF, sexo) VALUES (?, ?, ?, ?)";
        try (PreparedStatement insertStatement = conexao.prepareStatement(insertQuery)) {
            insertStatement.setString(1, campo1Value);
            insertStatement.setInt(2, campo2Value);
            insertStatement.setString(3, campo3Value);
            insertStatement.setString(4, campo4Value);
            insertStatement.executeUpdate();
        }
    }

    // Ler registros
    public void readRecords() throws SQLException {
        String selectQuery = "SELECT * FROM usuario";
        try (PreparedStatement selectStatement = conexao.prepareStatement(selectQuery);
             ResultSet resultSet = selectStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id_usuario");
                String campo1 = resultSet.getString("nome");
                int campo2 = resultSet.getInt("idade");
                String campo3 = resultSet.getString("CPF");
                String campo4 = resultSet.getString("sexo");
                System.out.println("id_usuario: " + id + ", nome: " + campo1 + ", idade: " + campo2 + ", CPF: " + campo3 + ", sexo: " + campo4);
            }
        }
    }


    //ATUALIZAR
    public void updateRecord(int id_usuario, String campo, String novoValor) throws SQLException {
        String updateQuery = "UPDATE usuario SET " + campo +" = ? WHERE id_usuario = ?";
        try (PreparedStatement updateStatement = conexao.prepareStatement(updateQuery)) {
            updateStatement.setString(1, novoValor);
            updateStatement.setInt(2, id_usuario);
            updateStatement.executeUpdate();
        }
    }


    //DELETAR
    public void deleteRecord(int id) throws SQLException {
        String deleteQuery = "DELETE FROM usuario WHERE id_usuario = ?";
        try (PreparedStatement deleteStatement = conexao.prepareStatement(deleteQuery)) {
            deleteStatement.setInt(1, id);
            deleteStatement.executeUpdate();
        }
    }
}

    

