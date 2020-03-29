package com.example.controllers;

import com.example.entities.Matrix;
import com.example.services.AlgorithmTester;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
public class MyController {

    @Autowired
    private AlgorithmTester algorithmTester;

    @RequestMapping(method = RequestMethod.GET)
    public String matrixForm(Model model){
        model.addAttribute("matrix", new Matrix());
        return "index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String matrixSubmit(@ModelAttribute Matrix matrix, Model model){
        model.addAttribute("textValue", matrix.getStrValue());
        String[] result = algorithmTester.testSort(matrix);
        model.addAttribute("resMatrix", result[0]);
        model.addAttribute("defaultTime", result[1]);
        model.addAttribute("insertionTime", result[2]);
        model.addAttribute("quickTime", result[3]);
        return "result";
    }

}
