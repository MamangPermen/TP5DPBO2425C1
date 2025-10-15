import java.sql.*;

public class Database
{
    private Connection connection;
    private Statement statement;

    // constructor
    public Database()
    {
        try {
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/db_product",
                "root",
                "");
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // digunakan untuk select
    public ResultSet executeQuery(String sql)
    {
        try {
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int insertUpdateDeleteQuery(String sql)
    {
        try {
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // getter
    public Statement getStatement() {
        return statement;
    }
}
