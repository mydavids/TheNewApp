package constructioncompany.api;

import constructioncompany.App;
import constructioncompany.domain.Staff;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Yusiry Davids on 9/12/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
@IntegrationTest({"server.port=8080"})
public class StaffAPITest {

    final String BASE_URL = "http://localhost:8080/";
    private RestTemplate template;

   // @Before
    public void setUp()throws Exception {
        template = new TestRestTemplate();
    }

   // @Test
    public void testRead() throws Exception{
        ResponseEntity<String> response = template.getForEntity(BASE_URL+"api/staff/", String.class);
        System.out.println("The response is " + response.getBody());
    }

    public static final String REST_SERVICE_URI = "http://localhost:8080/api";

    /*GET*/
   // @SuppressWarnings("unchecked")
   // @Test
    public void listAllStaff(){
        System.out.println("Testing listAllStaff API-----------");

        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> SubjectsMap = restTemplate.getForObject(REST_SERVICE_URI+"/staff", List.class);

        if(SubjectsMap!= null){
            for(LinkedHashMap<String, Object> map: SubjectsMap){
                System.out.println("Staff : id="+map.get("id")+",code="+map.get("staffCode")+"Name="+map.get("name"));
            }
        }else{
            System.out.println("No Staff exists------------");
        }
    }

    /* GET */
    private static void getStaff(){
        System.out.println("Testing getStaff API--------------------");
        RestTemplate restTemplate = new RestTemplate();
        Staff staff = restTemplate.getForObject(REST_SERVICE_URI+"staff/1", Staff.class);
        System.out.println(staff);
    }

    /* POST */
    //@Test
    public void createStaff(){
        System.out.println("Testing create staff API--------------------");
        RestTemplate restTemplate = new RestTemplate();
        Staff staff = new Staff.Builder("C01").name("Adam").build();
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/staff/create/", staff, Staff.class);
        System.out.println("Location: " + uri.toASCIIString());
    }

    /* PUT */
    private static void updateStaff(){
        System.out.println("Testing update staff API----------------------");
        RestTemplate restTemplate = new RestTemplate();
        Staff staff = new Staff.Builder("D01").name("Jane").build();
        restTemplate.put(REST_SERVICE_URI+"/staff/update/1", staff);
        System.out.println(staff);
    }

    /* DELETE */
    private static void deleteStaff(){
        System.out.println("Testing delete staff API------------------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/staff/delete/3");
    }




}
