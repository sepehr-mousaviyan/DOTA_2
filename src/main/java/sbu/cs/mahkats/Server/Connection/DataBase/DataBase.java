package sbu.cs.mahkats.Server.Connection.DataBase;

import org.apache.log4j.Logger;
import org.javatuples.Pair;
import sbu.cs.mahkats.Configuration.Config;
import sbu.cs.mahkats.Configuration.SecurityConfig;

import java.sql.*;
import java.util.Random;

public class DataBase {
    private final Connection conn;
    private String SQL_URL = "";
    private String SQL_USERNAME = "";
    private String SQL_PASSWORD = "";
    private String SQL_TABLE_NAME = "";
    private int LIMIT_USERNAME;
    private int LIMIT_PASSWORD;
    private int LIMIT_EMAIL;
    private static long last_token = 0;


    private final static Logger LOGGER = Logger.getLogger(DataBase.class.getName());

    public DataBase() throws SQLException {
        try {
            parseSqlProperties();
            this.conn = DriverManager.getConnection(SQL_URL, SQL_USERNAME, SQL_PASSWORD);
            LOGGER.info("Connected to sql server");
        } catch (SQLException throwables) {
            LOGGER.fatal("Can not connect to database!", throwables);
            throw throwables;
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
            LOGGER.info("player table has been made");
            try {
                statement.close();
            }catch (SQLException throwables){
                LOGGER.fatal("can not close database!", throwables);
            }
        } catch (SQLException throwables) {
            LOGGER.fatal("can not make player table!", throwables);
        }
    }

    /**
     * this function check if a user is not exist then add player to database and return a pair
     * with boolean true and a long variable token
     * but if user is not ok to sign up return a pair with boolean false and a error string
     * @param usr username
     * @param passw user password
     * @param email user email
     * @return a pair of result
     */
    public Pair signupRequest(String usr , String passw , String email){
        String sql = "INSERT INTO " + SQL_TABLE_NAME + " VALUES (?, ?, ?)";
        PreparedStatement ps;
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
            ps.executeUpdate();
            LOGGER.info("add player to database");
        } catch (SQLException throwables) {
            LOGGER.warn("can not sign up this user!", throwables);
            return new Pair<>(Boolean.FALSE, "this user already exist");
        }
        finally {
            try {
                ps.close();
            } catch (SQLException throwables) {
                LOGGER.fatal("can not close database!", throwables);
            }
        }

        long token;
        do{
            token = Math.abs(new Random().nextLong());
        }while( token == last_token );
        last_token = token;
        return new Pair<>(Boolean.TRUE, token);
    }

    /**
     * this function check if a user is exist and his username password is right return a pair with
     * boolean true an a long variable token
     * but if username password is wrong return a pair with boolean false and a error string
     * @param usr username
     * @param passw user password
     * @return a pair of result
     */
    public Pair loginRequest(String usr , String passw) {
        Pair result = null;
        String sql = "select * from " + SQL_TABLE_NAME + " where username LIKE '"+ usr + "';";
        Statement stmt;
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
        while(true) {
            try {
                if (!rs.next()) break;
                if(rs.getString(2).equals(passw)) {
                    long token;
                    do{
                        token = Math.abs(new Random().nextLong());
                    }while( token == last_token );
                    result = new Pair<>(Boolean.TRUE, token);
                    last_token = token;
                }
                else{
                    result = new Pair<>(Boolean.FALSE, "password is incorrect!");
                }
            } catch (SQLException throwables) {
                LOGGER.fatal("can not find player in database!", throwables);
            }
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
        else result = new Pair<>(Boolean.FALSE, "can not found player!");
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

    private void parseSqlProperties(){
        Config config = SecurityConfig.getInstance();
        this.SQL_USERNAME = config.getStringValue("sql.username");
        this.SQL_PASSWORD = config.getStringValue("sql.password");
        this.SQL_TABLE_NAME = config.getStringValue("sql.table.name");
        this.SQL_URL = config.getStringValue("sql.url");
        config = Config.getInstance();
        this.LIMIT_USERNAME = config.getIntValue("input.limit.userName");
        this.LIMIT_PASSWORD = config.getIntValue("input.limit.passWord");
        this.LIMIT_EMAIL = config.getIntValue("input.limit.email");

        LOGGER.info("properties has been gotten");
    }
}
