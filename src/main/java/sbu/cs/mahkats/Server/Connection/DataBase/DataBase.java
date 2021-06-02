package sbu.cs.mahkats.Server.Connection.DataBase;

import com.mysql.cj.conf.ConnectionUrlParser;
import org.apache.log4j.Logger;
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
    private int LIMIT_FNAME;
    private int LIMIT_LNAME;


    private final static Logger LOGGER = Logger.getLogger(DataBase.class.getName());

    public DataBase(){
        try {
            this.conn = DriverManager.getConnection(SQL_URL, SQL_USERNAME, SQL_PASSWORD);
            LOGGER.info("Connected to sql server");
        } catch (SQLException throwables) {
            LOGGER.fatal("Can not connect to database!", throwables);
        }
        this.SQL_TABLE_NAME = "player";
        initiateTable();
    }

    private void initiateTable() {
        String sql = "CREATE TABLE IF NOT EXISTS " + SQL_TABLE_NAME + " (\n" +
                "  username varchar("+ LIMIT_USERNAME +") NOT NULL,\n" +
                "  password varchar("+ LIMIT_PASSWORD +") NOT NULL,\n" +
                "  email varchar("+ LIMIT_EMAIL +") NOT NULL UNIQUE ,\n" +
                "  first_name varchar("+ LIMIT_FNAME +") NOT NULL ,\n" +
                "  last_name varchar("+ LIMIT_LNAME +") NOT NULL ,\n" +
                "  PRIMARY KEY (username)\n" +
                ")";
        Statement statement = null;
        try {
            statement = conn.createStatement();
            statement.execute(sql);
            LOGGER.info("player table has been made");
            try {
                statement.close();
            }catch (SQLException throwables){
                LOGGER.fatal("can not close database!", throwables);
            }
        } catch (SQLException throwables) {
            LOGGER.fatal("can not make playe table!", throwables);
        }
    }

    public Pair signupRequest(String usr , String passw , String email){
        boolean res = false;
        String sql = "INSERT INTO " + SQL_TABLE_NAME + " VALUES (?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
        } catch (SQLException throwables) {
            LOGGER.fatal("can not connect to database!", throwables);
            return null;
        }
        try {
            ps.setString(1, usr);
            ps.setString(2, passw);
            ps.setString(3 , email);
            LOGGER.info("add player to database");
        } catch (SQLException throwables) {
            LOGGER.warn("can not sign up this user!", throwables);
            return new Pair<Boolean, String>(Boolean.FALSE , "this user already exist");
        }
        finally {
            try {
                ps.close();
            } catch (SQLException throwables) {
                LOGGER.fatal("can not close database!", throwables);
            }
        }

        return new Pair<Boolean , String>(Boolean.TRUE , "OK");
    }

    public Pair loginRequest(String usr , String passw) {
        Pair result = null;
        String sql = "select * from " + SQL_TABLE_NAME + " where username LIKE '"+ usr + "';";
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException throwables) {
            LOGGER.error("can not connect to database!", throwables);
            return null;
        }
        ResultSet rs= null;
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException throwables) {
            LOGGER.fatal("can not connect to database!", throwables);
        }
        LOGGER.info("check and serach the player");
        while(rs.next()) {
            result = new Pair<Boolean , Pair<String , String>>(Boolean.TRUE , new Pair<String , String>(rs.getString(1) , rs.getString(2)));
        }
        try {
            stmt.close();
        } catch (SQLException throwables) {
            LOGGER.error("can not close database!", throwables);
        }
        if(result != null) {
            LOGGER.info("player is found");
            return result;
        }
        else result = new Pair<Boolean , String>(Boolean.FALSE , "can not found player!");
        LOGGER.warn("player is not found!");
        return result;
    }

    public void close(){
        try {
            conn.close();
            LOGGER.info("databse has been closed");
        } catch (SQLException throwables) {
            LOGGER.fatal("can not close database!", throwables);
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
        db.LIMIT_FNAME = config.getIntValue("input.limit.first.name");
        db.LIMIT_LNAME = config.getIntValue("input.limit.last.name");

        LOGGER.info("properties has been gotten");
    }
}
