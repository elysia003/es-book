package xyz.ciyo.es;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import xyz.ciyo.entity.Page;
import xyz.ciyo.entity.Xs;

@Controller
public class indexController {
	@Autowired
	private XsService service;
	@RequestMapping("/test")
	@ResponseBody
	private String test() {
		return "ssss";
	}
	@RequestMapping("/init")
	private String init() {
		if (!service.indexExists()) {
			System.out.println("unexit.....");
			service.createOrUpdateIndex();
		}
		if (service.indexExists()) {
			System.out.println("init db success");
		}
		return "index";
	}
	@RequestMapping(value="/search",method = RequestMethod.GET)
	private String search(@RequestParam String recode,@RequestParam int pageindex,@RequestParam int pagesize,@RequestParam String mode,Model model) {
		System.out.println(recode);
		long  startTime = System.currentTimeMillis();
		SearchHits<Xs> res = null;
		long endTime = System.currentTimeMillis();
		
		if("match".equals(mode)) {
			res = service.searchOnePageMatch(recode, pageindex, pagesize);
		}
		if("term".equals(mode)) {
			res = service.searchOnePageTerm(recode, pageindex, pagesize);
		}
		if("phrase".equals(mode)) {
			res = service.searchOnePagePhrase(recode, pageindex, pagesize);
		}
		model.addAttribute("res",new Page(recode, (int) res.getTotalHits()/pagesize, pageindex, pagesize, res.getSearchHits(),(int) res.getTotalHits(),endTime - startTime,mode));
		return "searchRes";
	}
	@RequestMapping("/v/{id}")
	public String getContex(@PathVariable Integer id,Model model) {
		model.addAttribute("res",service.getById(id));
		return "readView";
	}
}
