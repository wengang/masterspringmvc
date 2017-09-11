package io.metaphor.masterSpringMvc.search.api;
import io.metaphor.masterSpringMvc.search.LightTweet;
import io.metaphor.masterSpringMvc.search.TweeterSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchApiController {
    private TweeterSearch tweeterSearch;

    @Autowired
    public SearchApiController(TweeterSearch tweeterSearch) {
        this.tweeterSearch = tweeterSearch;
    }
    @RequestMapping(value = "/{searchType}",method = RequestMethod.GET)
    public List<LightTweet> search(@PathVariable String searchType, @MatrixVariable List<String> keywords) {
        return tweeterSearch.search(searchType,keywords);
    }
}
