package net.nyavro.spring.social.signinmvc.model;

public enum SocialMediaService {

    LOCAL("local"),
    FACEBOOK("facebook"),
    TWITTER("twitter"),
    VKONTAKTE("vkontakte");

    private final String name;

    private SocialMediaService(final String nm) {
        this.name = nm;
    }

    public static SocialMediaService resolve(String name) {
        for(SocialMediaService item : SocialMediaService.values()) {
            if(item.name.equals(name)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown SocialMediaService: " + name);
    }
}
