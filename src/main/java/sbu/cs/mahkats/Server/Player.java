package sbu.cs.mahkats.Server;

import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.javatuples.Pair;
import sbu.cs.mahkats.Api.MassageMaker;
import sbu.cs.mahkats.Api.UserData;
import sbu.cs.mahkats.Server.Connection.Client.Client;

public class Player {
    private static long TOKEN;

    private final static Logger LOGGER = Logger.getLogger(Player.class.getName());




    public void ResSignup(Pair<Boolean, String> res, Client client) {
        if (res.getValue0().equals(Boolean.TRUE)) {
            UserData userData = new UserData(TOKEN);
            MassageMaker massageMaker = new MassageMaker();
            JsonObject json = massageMaker.massage("ok", "res_signup", userData);
            client.send(json.toString());
            LOGGER.info(res.getValue1() + " signed up.");
        } else if (res.getValue0().equals(Boolean.FALSE)) {
            UserData userData = new UserData(res.getValue1());
            MassageMaker massageMaker = new MassageMaker();
            JsonObject json = massageMaker.massage("fail", "res_signup", userData);
            client.send(json.toString());
        } else {

        }


    }

    public void ResSignin(Pair res, Client client) {
        if (res.contains(Boolean.TRUE)) {
            TOKEN = (long) res.getValue1();
            UserData userData = new UserData(TOKEN);
            MassageMaker massageMaker = new MassageMaker();
            JsonObject json = massageMaker.massage("ok", "res_signin", userData);
            client.send(json.toString());
            LOGGER.info(res.getValue0() + " signed in. " + "acces TOKEN: " + TOKEN);
        } else if (res.getValue0().equals(Boolean.FALSE)) {
            UserData userData = new UserData((String) res.getValue1());
            MassageMaker massageMaker = new MassageMaker();
            JsonObject json = massageMaker.massage("fail", "res_signin", userData);
            client.send(json.toString());
        } else {

        }

    }


}
