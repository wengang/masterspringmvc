package io.metaphor.masterSpringMvc.viewModel;

import java.util.Date;

public class Tweet {
    private TwitterProfile user;
    private String text;
    private Date createAt;
    private String languageCode;
    private Integer retweetCount;

    public Tweet(TwitterProfile user, String text, Date createAt, String languageCode, Integer retweetCount) {
        this.user = user;
        this.text = text;
        this.createAt = createAt;
        this.languageCode = languageCode;
        this.retweetCount = retweetCount;
    }

    public TwitterProfile getUser() {
        return user;
    }

    public void setUser(TwitterProfile user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public Integer getRetweetCount() {
        return retweetCount;
    }
}
