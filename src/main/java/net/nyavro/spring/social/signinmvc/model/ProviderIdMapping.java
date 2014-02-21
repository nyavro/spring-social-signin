package net.nyavro.spring.social.signinmvc.model;

/**
 * Created by eny on 2/21/14.
 */
public class ProviderIdMapping {

    private SocialMediaService provider;

    private String id;

    public ProviderIdMapping(String socialId, SocialMediaService service) {
        this.id = socialId;
        this.provider = service;
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ProviderIdMapping that = (ProviderIdMapping) o;
        return id.equals(that.id) && provider == that.provider;
    }

    @Override
    public int hashCode() {
        return 31 * provider.hashCode() + id.hashCode();
    }
}
