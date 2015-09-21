package constructioncompany.api;

import constructioncompany.domain.Site;
import constructioncompany.services.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by Yusiry Davids on 5/17/2015.
 */
@RestController
@RequestMapping("/api/")
public class SitePage {
    @Autowired
    private SiteService service;

    //---------------------------------Retrieve All Sites ----------------------------------

    @RequestMapping(value="/sites", method= RequestMethod.GET)
    public ResponseEntity<List<Site>> listAllSites(){
        List<Site> Site = service.findAll();
        if(Site.isEmpty()){
            return new ResponseEntity<List<constructioncompany.domain.Site>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<constructioncompany.domain.Site>>(Site, HttpStatus.OK);
    }

    //------------------------------------Retrieve Single Site ------------------------------------

    @RequestMapping(value = "/site/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Site> getSite(@PathVariable("id") String id){
        System.out.println("Fetching site with code: " + id);
        Site Site = service.findById(id);
        if(Site == null){
            System.out.println("Site with id " + id + " not found");
        }
        return new ResponseEntity<constructioncompany.domain.Site>(Site, HttpStatus.OK);
    }

    //------------------------------------Create A Site ----------------------------------------------

    @RequestMapping(value = "/site/create", method = RequestMethod.POST)
    public ResponseEntity<Void> createStaff(@RequestBody Site site, UriComponentsBuilder uBuilder){
        System.out.println("Creating site " + site.getSiteName());

        service.save(site);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uBuilder.path("/site/{id}").buildAndExpand(site.getSiteCode()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //-----------------------------------------Update a site ---------------------------------------------

    @RequestMapping(value = "site/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Site> updateStaff(@PathVariable("id") String id, @RequestBody Site site){
        System.out.println("Updating Site");

        Site currentSite = service.findById(id);

        if(currentSite == null){
            System.out.println("Site with site code " + id + " not found");
            return new ResponseEntity<Site>(HttpStatus.NOT_FOUND);
        }

        Site updatedSite = new Site.Builder(currentSite.getSiteCode()).copy(currentSite).build();

        service.update(updatedSite);

        return new ResponseEntity<Site>(updatedSite, HttpStatus.OK);
    }

    //----------------------------------------------Delete a Site member ---------------------------------------

    @RequestMapping(value = "/site/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Site> deleteSite(@PathVariable("id") String id){
        System.out.println("Fetching and deleting a site member with id " + id);

        Site site = service.findById(id);
        if(site == null){
            System.out.println("Unable to delete. Site with id + " + id + " not found");
            return new ResponseEntity<Site>(HttpStatus.NOT_FOUND);
        }

        service.delete(site);
        return new ResponseEntity<Site>(HttpStatus.NO_CONTENT);
    }
}
