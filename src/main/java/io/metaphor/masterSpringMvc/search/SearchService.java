package io.metaphor.masterSpringMvc.search;

import io.metaphor.masterSpringMvc.search.cache.SearchCache;
import io.metaphor.masterSpringMvc.viewModel.Tweet;
import io.metaphor.masterSpringMvc.viewModel.TwitterProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Profile("!async")
public class SearchService implements TweeterSearch {
    private SearchCache searchCache;

    @Autowired
    public SearchService(SearchCache searchCache) {
        this.searchCache = searchCache;
    }

    @Override
    public List<LightTweet> search(String searchType, List<String> keywords) {
        List<LightTweet> tweets =
                keywords.stream().flatMap(keyword -> searchCache.fetch(searchType,keyword).stream())
                .collect(Collectors.toList());
        return tweets;
    }
}
