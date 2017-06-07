package com.empresa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by cyborg on 6/4/17.
 */
@Controller
public class AppController {




    @RequestMapping("/")
    String home(ModelMap modal) {
        modal.addAttribute("title","CRUD Robekson");
        System.out.println("Passou");
        return "index.html";
    }

   /* @RequestMapping("list")
    String partialHandler() {
        System.out.println("Passou2");
        return "list.html";
    }*/
}
