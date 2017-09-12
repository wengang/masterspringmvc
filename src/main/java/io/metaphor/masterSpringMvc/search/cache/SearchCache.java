package io.metaphor.masterSpringMvc.search.cache;

import io.metaphor.masterSpringMvc.search.LightTweet;
import io.metaphor.masterSpringMvc.search.TweetSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchCache {
    protected  final Log logger = LogFactory.getLog(getClass());
    private TweetSource tweetSource;

    @Autowired
    public SearchCache(TweetSource tweetSource) {
        this.tweetSource = tweetSource;
    }
    @Cacheable("searches")
    public List<LightTweet> fetch(String searchType,String keyword) {
        logger.info("Cache miss for " + keyword);
        return  tweetSource.Fetch(keyword);
    }
}
