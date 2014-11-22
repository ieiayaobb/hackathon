package com.hackathon.web;

import com.hackathon.service.FetchCompanyService;
import com.hackathon.service.FetchDataService;
import com.sun.org.apache.xpath.internal.operations.Mod;
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

    @RequestMapping(value = "/fd")
    public String fd(Model model) {
        model.addAttribute("companies", fetchDataService.getAllCompany());
        return "fd";
    }
    @RequestMapping(value = "/tree/{id}")
    public String tree(@PathVariable String id, Model model){
        model.addAttribute("competes", fetchDataService.getComptetesByCompanyId(id));
        return "tree";
    }
    @RequestMapping(value = "/detail/{name}")
    public String detail(@PathVariable String name, Model model){
        model.addAttribute("company", fetchCompanyService.getDetailByName(name));
        return "detail";
    }
}
