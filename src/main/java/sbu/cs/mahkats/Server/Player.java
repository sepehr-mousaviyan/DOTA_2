package sbu.cs.mahkats.Server;

import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.javatuples.Pair;
import sbu.cs.mahkats.Api.Data.GamePlayData;
import sbu.cs.mahkats.Api.Data.HeroData;
import sbu.cs.mahkats.Api.MassageMaker;
import sbu.cs.mahkats.Api.Data.UserData;
import sbu.cs.mahkats.Server.App.GamePlay;
import sbu.cs.mahkats.Server.Connection.Client.Client;
import sbu.cs.mahkats.Server.Unit.Movable.Hero.Hero;
import sbu.cs.mahkats.Server.Unit.Unit;
import sbu.cs.mahkats.Server.Unit.unitList;

import java.util.ArrayList;

public class Player {
    private long TOKEN;
    private PlayerData playerData;

    private final static Logger LOGGER = Logger.getLogger(Player.class.getName());

    /**
     * parse and check response of sql and if response is Ok add player and
     * make response signup massage to client that contains Ok massage and give Token to client
     * else response is fail make response signup massage to client that contains fail and
     * wants to get massage again
     * @param res response massage
     * @param playerData is the information of player that should be storage
     */
    public String ResSignup(Pair res, PlayerData playerData, Client client) {
        if (res.getValue0().equals(Boolean.TRUE)) {
            client.setTOKEN ((long) res.getValue1());
            UserData userData = new UserData(TOKEN);
            MassageMaker massageMaker = new MassageMaker();
            JsonObject json = massageMaker.massage("OK", "res_signup", userData);
            this.playerData = playerData;
            LOGGER.info(res.getValue1() + " signed up.");
            return json.toString();
        } else if (res.getValue0().equals(Boolean.FALSE)) {
            UserData userData = new UserData((String) res.getValue1());
            MassageMaker massageMaker = new MassageMaker();
            JsonObject json = massageMaker.massage("fail", "res_signup", userData);
            LOGGER.info(res.getValue1());
            return json.toString();
        } else {
            UserData userData = new UserData("can not sign up! try later");
            MassageMaker massageMaker = new MassageMaker();
            JsonObject json = massageMaker.massage("fail", "res_signup", userData);
            LOGGER.warn("can not sign up!");
            return json.toString();
        }
    }

    /**
     * parse and check response of sql and if response is Ok add player and
     * make response login massage to client that contains Ok massage and give Token to client
     * else response is fail make response signup massage to client that contains fail and
     * wants to get massage again
     * @param res response massage
     * @param playerData is the information of player that should be storage
     */
    public String ResSignin(Pair res, PlayerData playerData, Client client) {
        if (res.contains(Boolean.TRUE)) {
            client.setTOKEN ((long) res.getValue1());
            UserData userData = new UserData(TOKEN);
            MassageMaker massageMaker = new MassageMaker();
            JsonObject json = massageMaker.massage("OK", "res_signin", userData);
            this.playerData = playerData;
            LOGGER.info(res.getValue0() + " signed in. " + "acces TOKEN: " + TOKEN);
            return json.toString();
        } else if (res.getValue0().equals(Boolean.FALSE)) {
            UserData userData = new UserData((String) res.getValue1());
            MassageMaker massageMaker = new MassageMaker();
            JsonObject json = massageMaker.massage("fail", "res_signin", userData);
            LOGGER.info(res.getValue1());
            return json.toString();
        } else {
            UserData userData = new UserData("can not login! try later");
            MassageMaker massageMaker = new MassageMaker();
            JsonObject json = massageMaker.massage("fail", "res_signup", userData);
            LOGGER.warn("can not login!");
            return json.toString();
        }
    }



}
