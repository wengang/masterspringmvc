package io.metaphor.masterSpringMvc.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SearchController {
    private TweeterSearch tweeterSearch;

    @Autowired
    public SearchController(TweeterSearch tweeterSearch) {
        this.tweeterSearch = tweeterSearch;
    }
    @RequestMapping("/search/{searchType}")
    public ModelAndView search(@PathVariable String searchType, @MatrixVariable List<String> keywords) {

        List<LightTweet> tweets = tweeterSearch.search(searchType,keywords);
        ModelAndView modelAndView = new ModelAndView("resultPage");
        modelAndView.addObject("tweets",tweets);
        modelAndView.addObject("search",String.join(",",keywords));
        return modelAndView;
    }
}
