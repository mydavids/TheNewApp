package constructioncompany.api;

import constructioncompany.domain.Site;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Yusiry Davids on 9/19/2015.
 */
@RestController
@RequestMapping("/")
public class LandingPage {
    @RequestMapping(method = RequestMethod.GET)
    public Site getSite(){
        Site site = new Site.Builder("S012").siteName("TestSite").siteSize(20).build();
        return site;
    }

}
