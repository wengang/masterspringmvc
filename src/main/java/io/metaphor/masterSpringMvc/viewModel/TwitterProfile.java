package io.metaphor.masterSpringMvc.viewModel;

public class TwitterProfile {

    private String profileImageUrl;
    private String name;

    public TwitterProfile(String profileImageUrl, String name) {
        this.profileImageUrl = profileImageUrl;
        this.name = name;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
