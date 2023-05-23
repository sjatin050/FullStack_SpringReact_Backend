package com.panel.admin.user.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.panel.admin.user.constants.constant.*;

@Service
public class RootService {
    @Autowired
    private RootRepository rootRepository;
    public List<Root> allRoot(){
        return rootRepository.findAll();
    }
    public List<Root> getRootByIdType(String idType,String idNumber){
        if(idType.equalsIgnoreCase(String.valueOf(LEAD_ID))){
            return rootRepository.findAllBy_id(idNumber);
        }
        else if(idType.equalsIgnoreCase(String.valueOf(CUSTOMER_ID))){
            return rootRepository.findAllByCustomerId(idNumber);
        }
        else if(idType.equalsIgnoreCase(String.valueOf(PRODUCT_TYPE))){
            return rootRepository.findAllByProductType(idNumber);
        }
        else if(idType.equalsIgnoreCase(String.valueOf(LOAN_ACCOUNT_NUMBER))){
            return null;
        }
        return null;
    }

    public void updateEmail(String id , String email){

        List<Root> optionalRoot = rootRepository.findAllBy_id(id);

        Root root = optionalRoot.get(0);
        root.setEmail(email);
        rootRepository.save(root);
//        rootRepository.updateEmail(id,email);
    }
}
