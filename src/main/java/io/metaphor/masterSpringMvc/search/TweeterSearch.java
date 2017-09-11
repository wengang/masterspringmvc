package io.metaphor.masterSpringMvc.search;

import java.util.List;

public interface TweeterSearch {
    List<LightTweet> search(String searchType, List<String> keywords);
}
