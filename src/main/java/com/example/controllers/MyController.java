package com.example.controllers;

import com.example.entities.sort.Matrix;
import com.example.services.AlgorithmTester;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class MyController {

    @Autowired
    private AlgorithmTester algorithmTester;

    @RequestMapping(method = RequestMethod.GET, value = "search")
    public String arrayForm(Model model) {
        return "index_a";
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    public String matrixForm(Model model) {
        return "index_m";
    }

    @RequestMapping(method = RequestMethod.POST, value = "search")
    public String arraySubmit(Model model,
                              @RequestParam("array") String array,
                              @RequestParam("toFind") String find,
                              @RequestParam("toRemove")String remove,
                              @RequestParam("toAdd")String add) {
        //Save form data
        model.addAttribute("saveArray", array);
        model.addAttribute("saveFind", find);
        model.addAttribute("saveAdd", add);
        model.addAttribute("saveRemove", remove);
        //Logic
        String[] res = algorithmTester.testIntSearch(array, find, add, remove);
        //Results
        model.addAttribute("res", res[0]);
        model.addAttribute("resFind", res[1]);
        model.addAttribute("resAdd", res[2]);
        model.addAttribute("resRemove", res[3]);
        return "index_a";
    }

    @RequestMapping(method = RequestMethod.POST, value = "")
    public String matrixSubmit(@RequestParam("matrix") String strMatrix, Model model) {
        Matrix matrix = new Matrix(strMatrix);
        model.addAttribute("saveMatrix", matrix.getStrValue());
        String[] res = algorithmTester.testSort(matrix);
        model.addAttribute("resMatrix", res[0]);
        model.addAttribute("defaultTime", res[1]);
        model.addAttribute("insertionTime", res[2]);
        model.addAttribute("quickTime", res[3]);
        return "index_m";
    }

}
