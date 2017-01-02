package masterSpringMvc.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.social.twitter.api.SearchParameters;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

import masterSpringMvc.search.cache.SearchCache;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Profile("!async")
public class SearchService implements TwitterSearch {
	private SearchCache searchCache;

	@Autowired
	public SearchService(SearchCache searchCache) {
		this.searchCache = searchCache;
	}

	@Override
	public List<LightTweet> search(String searchType, List<String> keywords) {
		return keywords.stream().flatMap(keyword -> searchCache.fetch(searchType, keyword).stream())
				.collect(Collectors.toList());
	}

}