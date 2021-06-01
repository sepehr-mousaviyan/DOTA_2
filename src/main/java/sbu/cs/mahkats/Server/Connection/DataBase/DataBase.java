package sbu.cs.mahkats.Server.Connection.DataBase;

import com.mysql.cj.conf.ConnectionUrlParser;
import org.javatuples.Pair;
import sbu.cs.mahkats.Api.ApiCTS_Signin_Ok;
import sbu.cs.mahkats.Api.ApiSTC_Signup_Fail;
import sbu.cs.mahkats.Configuration.Config;

import java.sql.*;

public class DataBase {
    private Connection conn = null;
    private String SQL_URL = "";
    private String SQL_USERNAME = "";
    private String SQL_PASSWORD = "";
    private String SQL_TABLE_NAME = "";
    private int LIMIT_USERNAME;
    private int LIMIT_PASSWORD;
    private int LIMIT_EMAIL;

    public DataBase(){
        try {
            this.conn = DriverManager.getConnection(SQL_URL, SQL_USERNAME, SQL_PASSWORD);
            System.out.println("Connected to sql server");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        this.SQL_TABLE_NAME = "player";
        initiateTable();
    }

    private void initiateTable() {
        String sql = "CREATE TABLE IF NOT EXISTS " + SQL_TABLE_NAME + " (\n" +
                "  username varchar("+ LIMIT_USERNAME +") NOT NULL,\n" +
                "  password varchar("+ LIMIT_PASSWORD +") NOT NULL,\n" +
                "  email varchar("+ LIMIT_EMAIL +") NOT NULL UNIQUE ,\n" +
                "  PRIMARY KEY (username)\n" +
                ")";
        Statement statement = null;
        try {
            statement = conn.createStatement();
            statement.execute(sql);
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println("initiate table done");
    }

    public Pair signupRequest(String usr , String passw , String email){
        boolean res = false;
        String sql = "INSERT INTO " + SQL_TABLE_NAME + " VALUES (?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        try {
            ps.setString(1, usr);
            ps.setString(2, passw);
            ps.setString(3 , email);
        } catch (SQLException throwables) {
            System.out.println("can not sign up this user!");
            try {
                ps.close();
            } catch (SQLException e) {
                System.out.println("Error! can not close database!");
            }
            return new Pair<Boolean, String>(Boolean.FALSE , "this user already exist");
        }

        int i = 0;
        System.out.println(ps);
        try {
            i = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error! can not insert to table!");
        }

        try {
            ps.close();
        } catch (SQLException throwables) {
            System.out.println("Error! can not close database!");
        }

        return new Pair<Boolean , String>(Boolean.TRUE , "OK");
    }

    public Pair loginRequest(String usr , String passw) throws SQLException {
        Pair result = null;
        String sql = "select * from " + SQL_TABLE_NAME + " where username LIKE '"+ usr + "';";
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        ResultSet rs=stmt.executeQuery(sql);
        while(rs.next()) {
            result = new Pair<Boolean , Pair<String , String>>(Boolean.TRUE , new Pair<String , String>(rs.getString(1) , rs.getString(2)));
        }
        try {
            conn.close();
            stmt.close();
        } catch (SQLException throwables) {
            System.out.println("Error! can not close database!");
        }
        if(result != null)
            return result;
        else result = new Pair<Boolean , String>(Boolean.FALSE , "can not found player!");
        return result;
    }

    public void close(){
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error! can not close database!");
        }
    }

    private static void parseSqlProperties(DataBase db){
        Config config = Config.getInstance();
        db.SQL_USERNAME = config.getStringValue("sql.username");
        db.SQL_PASSWORD = config.getStringValue("sql.password");
        db.SQL_TABLE_NAME = config.getStringValue("sql.table.name");
        db.SQL_URL = config.getStringValue("sql.url");
        db.LIMIT_USERNAME = config.getIntValue("input.limit.username");
        db.LIMIT_PASSWORD = config.getIntValue("input.limit.password");
        db.LIMIT_EMAIL = config.getIntValue("input.limit.email");
    }
}
