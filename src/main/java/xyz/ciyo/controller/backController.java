package xyz.ciyo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xyz.ciyo.entity.Xs;
import xyz.ciyo.es.XsServiceImpl;
@Controller
public class backController {
	private XsServiceImpl service;
    @RequestMapping(value = "/post",method = RequestMethod.POST)
    public boolean addUser(Xs recode){
    	System.out.println(recode.getId());
        return service.save(recode);
    }
    
}