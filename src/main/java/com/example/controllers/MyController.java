package com.example.controllers;

import com.example.entities.Matrix;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
public class MyController {

    @RequestMapping(method = RequestMethod.GET)
    public String matrixForm(Model model){
        model.addAttribute("matrix", new Matrix());
        return "index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String matrixSubmit(@ModelAttribute Matrix matrix, Model model){
        int[][] test = matrix.getIntValue();
        log.info("Matrix value: {}", matrix.getValue());
        model.addAttribute("textValue", matrix.getValue());
        return "result";
    }

}
