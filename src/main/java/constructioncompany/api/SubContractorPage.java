package constructioncompany.api;

import com.mysql.jdbc.jdbc2.optional.SuspendableXAConnection;
import constructioncompany.domain.SubContractor;
import constructioncompany.services.SubContractorService;
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
public class SubContractorPage {
    @Autowired
    private SubContractorService service;

    //-------------------------Retrieve All Subcontractors------------------------

    @RequestMapping(value = "/subcontractors/", method = RequestMethod.GET)
    public ResponseEntity<List<SubContractor>> listAllSubContractors(){
        List<SubContractor> SubContractors = service.findAll();
        if(SubContractors.isEmpty()){
            return new ResponseEntity<List<SubContractor>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<SubContractor>>(SubContractors, HttpStatus.OK);
    }

    //------------------------Retrieve Single Subcontractor-----------------------------

    @RequestMapping(value = "/subcontractor/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubContractor> getSubcontractor(@PathVariable("id") String id){
        System.out.println("Fetching SubContractor with code: " + id);
        SubContractor SubContractor = service.findById(id);
        if(SubContractor == null){
            System.out.println("Subcontractor with id " + id + " not found");
            return new ResponseEntity<constructioncompany.domain.SubContractor>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<constructioncompany.domain.SubContractor>(SubContractor, HttpStatus.OK);
    }

    //----------------------------Create a Subcontractor----------------------------------------

    @RequestMapping(value = "/subcontractor/create", method = RequestMethod.POST)
    public ResponseEntity<Void> createSubcontractor(@RequestBody SubContractor subContractor, UriComponentsBuilder uBuilder){
        System.out.println("Creating subcontractor " + subContractor.getName());

        service.save(subContractor);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uBuilder.path("/subcontractor/{id}").buildAndExpand(subContractor.getContractorCode()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //-----------------------------Update a Subcontractor---------------------------------------------

    @RequestMapping(value =  "/subcontractor/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<SubContractor> updateContractor(@PathVariable("id") String id, @RequestBody SubContractor subContractor){
        System.out.println("Updating Subcontractor");

        SubContractor currentContractor = service.findById(id);

        if(currentContractor == null){
            System.out.println("Subcontractor with Contractor Code: " + id + " not found");
            return new ResponseEntity<SubContractor>(HttpStatus.NOT_FOUND);
        }

        SubContractor updatedContractor = new SubContractor.Builder(currentContractor.getContractorCode()).copy(currentContractor).build();

        service.update(updatedContractor);

        return new ResponseEntity<SubContractor>(updatedContractor, HttpStatus.OK);
    }

    //--------------------------------Delete a Subcontractor----------------------------------------------------

    @RequestMapping(value = "subcontractor/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<SubContractor> deleteContractor(@PathVariable("id") String id){
        System.out.println("Fetching and deleting a subcontractor with id " + id);

        SubContractor subContractor = service.findById(id);
        if(subContractor == null){
            System.out.println("Unable to delete. Subcontracot with id " + id + " not found");
            return new ResponseEntity<SubContractor>(HttpStatus.NOT_FOUND);
        }

        service.delete(subContractor);
        return new ResponseEntity<SubContractor>(HttpStatus.NO_CONTENT);
    }
}
