import java.sql.*; // importa a biblioteca SQL



public class App {

    public static void main(String[] args) throws Exception {

        System.out.println("Hello, Meu projeto JDBC!");


        Connection conexao = null;

        try { // organizar seus dados de conexão em strings é uma boa ideia!
        
        String mySQLURL = "jdbc:mysql://localhost:3306/crudJava"; // informar o nome do banco no final da URL é opcional

        String usuario = "root";

        String senha = "****";
        
        conexao = DriverManager.getConnection(mySQLURL, usuario, senha); 

        // realiza a conexão com o banco

            if (conexao != null) {

                System.out.println("Conectado com sucesso à instância MySQL!");

             


            Func func = new Func(conexao);

            //EXEMPLO INSERT
            func.insertRecord("jania", 15, "78545570213", "f");

            //EXEMPLO UPDATE
            func.updateRecord(2, "sexo", "m");

            //EXEMPLO CREATE TABLE
            func.createTableIfNotExists("cadastro", "id int AUTO_INCREMENT PRIMARY KEY ", "nome varchar(50)", "idade integer", "situacao char");

            //EXEMPLO DELETE RECORD
            func.deleteRecord(2);


         } else {
             System.out.println("Conexão está inativa. Verifique sua conexão.");
         }
     } catch (SQLException e) {
         System.out.println("Erro ao estabelecer a conexão: " + e.getMessage());
     } finally {
         try {
             if (conexao != null) {
                 conexao.close();
             }
         } catch (SQLException e) {
             System.out.println("Erro ao fechar a conexão: " + e.getMessage());
         }
     }
 }
}
