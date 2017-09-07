package io.metaphor.masterSpringMvc.search;

import io.metaphor.masterSpringMvc.viewModel.Tweet;
import io.metaphor.masterSpringMvc.viewModel.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private Tweet[] _tweets={};
    public SearchService(){
        List<Tweet> tweets=new ArrayList<>();
        tweets.add(new Tweet(new User("http://icon.nipic.com/BannerPic/20170731/original/20170731105950_1.jpg","guli"),"go player,8 times win gold"));
        tweets.add(new Tweet(new User("http://icon.nipic.com/BannerPic/20170731/original/20170731105950_1.jpg","kejie"),"go player,4 times win gold"));
        tweets.add(new Tweet(new User("http://icon.nipic.com/BannerPic/20170731/original/20170731105950_1.jpg","changhao"),"go player,3 times win gold"));
        tweets.add(new Tweet(new User("http://icon.nipic.com/BannerPic/20170731/original/20170731105950_1.jpg","maxiaochun"),"go player,2 times win gold"));
        tweets.add(new Tweet(new User("http://icon.nipic.com/BannerPic/20170731/original/20170731105950_1.jpg","shiyue"),"go player,1 times win gold"));
        tweets.add(new Tweet(new User("http://icon.nipic.com/BannerPic/20170731/original/20170731105950_1.jpg","xuyinchuan"),"chess player,1 times win gold"));
        tweets.add(new Tweet(new User("http://icon.nipic.com/BannerPic/20170731/original/20170731105950_1.jpg","huronghua"),"chess player,1 times win gold"));
        tweets.add(new Tweet(new User("http://icon.nipic.com/BannerPic/20170731/original/20170731105950_1.jpg","luqing"),"chess player,1 times win gold"));
        _tweets=tweets.toArray(_tweets);
    }
    public List<Tweet> search(String searchType,List<String> keywords) {
        List<Tweet> tweets =
                Arrays.stream(_tweets)
                        .filter(tweet -> {
                            boolean result=false;
                            for(String keyword : keywords) {
                                result = result || tweet.getText().contains(keyword);
                            }
                            return  result;
                        })
                        .collect(Collectors.toList());
        return tweets;
    }
}
