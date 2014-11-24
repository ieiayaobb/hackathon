package com.hackathon.web;

import com.hackathon.service.FetchCompanyService;
import com.hackathon.service.FetchDataService;
import com.hackathon.service.FreebaseService;
import com.hackathon.service.SimulateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by zhangxiaoxue on 14/11/22.
 */
@Controller
@RequestMapping(value = "/compete")
public class CompeteController {
    @Autowired
    private FetchDataService fetchDataService;
    @Autowired
    private FetchCompanyService fetchCompanyService;
    @Autowired
    private FreebaseService freebaseService;

    @RequestMapping(value = "/fd")
    public String fd(@RequestParam int limit, Model model) {
        try {
            model.addAttribute("companies", fetchDataService.getAllCompany(limit));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "fd";
    }
    @RequestMapping(value = "/document")
    public String fd(Model model) {
        try {
            model.addAttribute("document", fetchDataService.getAllCompany());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "document";
    }
    @RequestMapping(value = "/tree/{id}")
    public String tree(@PathVariable String id, Model model) throws Exception {
        model.addAttribute("competes", fetchDataService.getComptetesByCompanyId(id));
        return "tree";
    }
    @RequestMapping(value = "/detail/{name}")
    public String detail(@PathVariable String name, Model model){
        model.addAttribute("company", freebaseService.getCompanyInfo(name));
        return "detail";
    }
}
