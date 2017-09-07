package io.metaphor.masterSpringMvc.controller;

import io.metaphor.masterSpringMvc.profile.ProfileForm;
import io.metaphor.masterSpringMvc.profile.UserProfileSession;
import io.metaphor.masterSpringMvc.viewModel.Tweet;
import io.metaphor.masterSpringMvc.viewModel.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TweetController {
    private Tweet[] _tweets={};
    private UserProfileSession userProfileSession;
    @Autowired
    public TweetController(UserProfileSession userProfileSession) {
        this.userProfileSession = userProfileSession;
        List<Tweet> tweets=new ArrayList<>();
        tweets.add(new Tweet(new User("http://icon.nipic.com/BannerPic/20170731/original/20170731105950_1.jpg","guli"),"go player,8 times win gold"));
        tweets.add(new Tweet(new User("http://icon.nipic.com/BannerPic/20170731/original/20170731105950_1.jpg","kejie"),"go player,4 times win gold"));
        tweets.add(new Tweet(new User("http://icon.nipic.com/BannerPic/20170731/original/20170731105950_1.jpg","changhao"),"go player,3 times win gold"));
        tweets.add(new Tweet(new User("http://icon.nipic.com/BannerPic/20170731/original/20170731105950_1.jpg","maxiaochun"),"go player,2 times win gold"));
        tweets.add(new Tweet(new User("http://icon.nipic.com/BannerPic/20170731/original/20170731105950_1.jpg","shiyue"),"go player,1 times win gold"));
        _tweets=tweets.toArray(_tweets);
    }
    @RequestMapping("/")
    public String home() {
        ProfileForm profileForm = userProfileSession.toForm();
        if(profileForm.getTastes().isEmpty()){
            return "redirect:/profile";
        }
        return "searchPage";
    }
    @RequestMapping(value = "/postSearch",method = RequestMethod.POST)
    public String postSearch(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String search = request.getParameter("search");
        if(search.toLowerCase().contains("chess")) {
            redirectAttributes.addFlashAttribute("error","Try using go insteaded!");
            return "redirect:/";
        }
        redirectAttributes.addAttribute("search",search);
        return "redirect:result";
    }
    @RequestMapping("/result")
//    @ResponseBody
    public String hello(@RequestParam(defaultValue = "go") String search, Model model) {
        List<Tweet> tweets =
                Arrays.stream(_tweets)
                        .filter(tweet -> tweet.getText().startsWith(search))
                        .collect(Collectors.toList());
        model.addAttribute("tweets",tweets);
        model.addAttribute("search",search);
        return "resultPage";
    }
}
