package constructioncompany.services.Impl;

import constructioncompany.domain.Staff;
import constructioncompany.respository.StaffRepository;
import constructioncompany.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yusiry Davids on 4/23/2015.
 */
@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    StaffRepository repository;

    @Override
    public Staff findById(String id){
        return repository.findOne(id);

    }

    @Override
    public Staff save(Staff entity){
        return repository.save(entity);
    }

    @Override
    public Staff update(Staff entity){
        return repository.save(entity);
    }

    @Override
    public void delete(Staff entity){
        repository.delete(entity);
    }

    public List<Staff> findAll(){
        List<Staff> allStaff = new ArrayList<Staff>();

        Iterable<Staff> staff = repository.findAll();
        for (Staff staffmembers : staff){
            allStaff.add(staffmembers);
        }
        return allStaff;
    }
}
