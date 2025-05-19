package config;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvConfig {
    String accessToken = EnvConfig.get("ACCESS_TOKEN");

    private static final Dotenv dotenv = Dotenv.load();

    public static String get(String key) {
        return dotenv.get(key);
    }
}
