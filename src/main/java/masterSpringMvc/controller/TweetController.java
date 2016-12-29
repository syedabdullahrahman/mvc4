package masterSpringMvc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import masterSpringMvc.search.LightTweet;
import masterSpringMvc.search.SearchService;

@Controller
public class TweetController {
	@Autowired
	private SearchService searchService;
	@Autowired
	private Twitter twitter;

	@RequestMapping("/lookup")
	public String home(){
		return "searchPage";
	}
	
	@RequestMapping(value = "/postSearch", method = RequestMethod.POST)
	public String postSearch(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		String search = request.getParameter("search");
		if (search.toLowerCase().contains("trash")) {
			redirectAttributes.addFlashAttribute("error", "");
			return "redirect:/";
		}
		redirectAttributes.addAttribute("search", search);
		return "redirect:result";
	}

	@RequestMapping("/result")
	public String result(@RequestParam(defaultValue = "holidays") String search, Model model) {
		ArrayList<String> list = new ArrayList<>();
		list.add(search);
		List<LightTweet> tweets = searchService.search("mixed",list);
		model.addAttribute("tweets", tweets);
		model.addAttribute("search", search);
		return "resultPage";
	}

}

