package utilities;

public class PlatformManager {

    public enum Platform {
        ANDROID("android"),
        IOS("ios");

        private String platform;

        Platform(String platform) {
            this.platform = platform;
        }
    }

    public Platform getPlatform() {
        String platform = System.getProperty("platform", "ios").toLowerCase();

        if (platform.equals("android"))
            return Platform.ANDROID;
        else if (platform.equals("ios"))
            return Platform.IOS;

        return Platform.ANDROID;
    }
}

