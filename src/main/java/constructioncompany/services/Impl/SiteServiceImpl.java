package constructioncompany.services.Impl;

import constructioncompany.domain.Site;
import constructioncompany.respository.SiteRepository;
import constructioncompany.services.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yusiry Davids on 4/22/2015.
 */
@Service
public class SiteServiceImpl implements SiteService {
    @Autowired
    SiteRepository repository;

    @Override
    public Site findById(String id){
        return repository.findBysiteCode(id);
    }

    @Override
    public Site save(Site entity){
        return repository.save(entity);
    }

    @Override
    public Site update(Site entity){
        return repository.save(entity);
    }

    @Override
    public Site delete(Site entity){
        return repository.delete(entity);
    }

    public List<Site> findAll(){
        List<Site> allSites = new ArrayList<Site>();

        Iterable<Site> sites = repository.findAll();
        for (Site someSites : sites){
            allSites.add(someSites);
        }
        return allSites;
    }


}
