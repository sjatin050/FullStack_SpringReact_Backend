package com.panel.admin.user.demo;

import com.panel.admin.user.user.Root;
import com.panel.admin.user.user.RootService;
import com.panel.admin.user.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RootController {

    @Autowired
    public RootService rootService;
    @Autowired
    public UserService userService;

    @GetMapping("/check")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from secured endpoint");
    }

    @GetMapping("/results")
    public ResponseEntity<List<Root>> getAllRoot(){
        return new ResponseEntity<List<Root>>(rootService.allRoot(), HttpStatus.OK);
    }
    @GetMapping("/results/{values}")
    public ResponseEntity<List<Root>> getRootsById(@PathVariable String values){
        String idType = values.substring(0,values.indexOf("&"));
        String idNumber = values.substring(idType.length()+1,values.length());
        var result = rootService.getRootByIdType(idType,idNumber);
        System.out.println("\n\n\n");
        System.out.println(result);
        System.out.println("\n\n\n");
        return new ResponseEntity<List<Root>>(result, HttpStatus.OK);
    }

    // this will return a single list element
    @GetMapping("/result/{id}")
    public ResponseEntity<List<Root>> getARootById(@PathVariable String id){

        var result = rootService.getRootByIdType("lead_id",id);
        System.out.println("\n\n\n");
        System.out.println(result);
        System.out.println("\n\n\n");

        return new ResponseEntity<List<Root>>(result, HttpStatus.OK);
    }

    // when we do update we use PUT method
    @PutMapping("/results/{_id}/email/{email}")
    public void updateEmail(@PathVariable String _id, @PathVariable String email )
    {

        //System.out.println(todo);
        rootService.updateEmail(_id ,email);

    }

}
