package sbu.cs.mahkats.Configuration.Units;

import sbu.cs.mahkats.Configuration.Config;

import java.util.ResourceBundle;

public class HeroConfig extends Config{
    private static String hero;

    private HeroConfig(String hero_name) {
        this.bundle = "TowerConfig";
        hero = hero_name;
        config = ResourceBundle.getBundle("Hero/" + hero);
    }

    public static Config getInstance(String hero_name)
    {
        if (config_instance == null ||
                !config_instance.getBundle().equals("HeroConfig") ||
                !hero.equals(hero_name)) {
            config_instance = new HeroConfig(hero_name);
        }
        return config_instance;
    }
}
