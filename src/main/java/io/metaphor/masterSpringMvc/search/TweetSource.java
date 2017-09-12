package io.metaphor.masterSpringMvc.search;

import io.metaphor.masterSpringMvc.viewModel.Tweet;
import io.metaphor.masterSpringMvc.viewModel.TwitterProfile;
import org.springframework.context.annotation.Bean;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

public class TweetSource {
    private Tweet[] _tweets={};
    public TweetSource() {
        List<Tweet> tweets=new ArrayList<>();
        DateFormat dateTimeFormat=DateFormat.getDateTimeInstance();
        Date dateTime= null;
        try {
            dateTime = dateTimeFormat.parse("2017-07-31 18:33:22");
        } catch (ParseException e) {
            Calendar calendar= Calendar.getInstance();
            calendar.set(2017,8,9,12,12,11);
            dateTime=calendar.getTime();
            e.printStackTrace();
        }
        tweets.add(new Tweet(new TwitterProfile("http://icon.nipic.com/BannerPic/20170731/original/20170731105950_1.jpg",
                "guli"),"go player,8 times win gold",dateTime , "zh_CN", 4));
        tweets.add(new Tweet(new TwitterProfile("http://icon.nipic.com/BannerPic/20170731/original/20170731105950_1.jpg",
                "kejie"),"go player,4 times win gold", dateTime, "zh_CN", 5));
        tweets.add(new Tweet(new TwitterProfile("http://icon.nipic.com/BannerPic/20170731/original/20170731105950_1.jpg",
                "changhao"),"go player,3 times win gold", dateTime, "zh_CN", 5));
        tweets.add(new Tweet(new TwitterProfile("http://icon.nipic.com/BannerPic/20170731/original/20170731105950_1.jpg",
                "maxiaochun"),"go player,2 times win gold", dateTime, "zh_CN", 6));
        tweets.add(new Tweet(new TwitterProfile("http://icon.nipic.com/BannerPic/20170731/original/20170731105950_1.jpg",
                "shiyue"),"go player,1 times win gold", dateTime, "zh_CN", 6));
        tweets.add(new Tweet(new TwitterProfile("http://icon.nipic.com/BannerPic/20170731/original/20170731105950_1.jpg",
                "xuyinchuan"),"chess player,1 times win gold", dateTime, "zh_CN", 6));
        tweets.add(new Tweet(new TwitterProfile("http://icon.nipic.com/BannerPic/20170731/original/20170731105950_1.jpg",
                "huronghua"),"chess player,1 times win gold", dateTime, "zh_CN", 6));
        tweets.add(new Tweet(new TwitterProfile("http://icon.nipic.com/BannerPic/20170731/original/20170731105950_1.jpg",
                "luqing"),"chess player,1 times win gold", dateTime, "zh_CN", 6));
        _tweets=tweets.toArray(_tweets);
    }

    public List<LightTweet> Fetch(String keyword) {
        List<LightTweet> tweets =
                Arrays.stream(_tweets)
                        .filter(tweet -> tweet.getText().contains(keyword))
                        .map(LightTweet::ofTweet)
                        .collect(Collectors.toList());
        return tweets;
    }
}
