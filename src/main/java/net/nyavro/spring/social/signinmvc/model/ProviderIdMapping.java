package net.nyavro.spring.social.signinmvc.model;

/**
 * Created by eny on 2/21/14.
 */
public class ProviderIdMapping {

    private SocialMediaService provider;

    private String id;

    public ProviderIdMapping() {
    }

    public ProviderIdMapping(String socialId, SocialMediaService service) {
        this.id = socialId;
        this.provider = provider;
    }

    public SocialMediaService getProvider() {
        return provider;
    }

    public void setProvider(SocialMediaService provider) {
        this.provider = provider;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
