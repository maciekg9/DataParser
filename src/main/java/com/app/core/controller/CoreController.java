package com.app.core.controller;

import com.app.core.model.DataParser;
import com.app.core.repository.DataParserRepository;
import com.app.core.service.DataParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class CoreController {

    @Autowired
    private DataParserService dataParserService;
    @Autowired
    private DataParserRepository dataParserRepository;




    @RequestMapping(value = "/app", method = RequestMethod.GET)
    public ModelAndView emp(ModelMap model) {

        ModelAndView modelAndView = new ModelAndView();
        DataParser tableRowForm = new DataParser();
        dataParserRepository.findAll();
        model.addAttribute("tableRows", dataParserRepository.findAll());

        modelAndView.addObject("tableRowForm", tableRowForm);

        return new ModelAndView("jChronicHome", "dataParser", new DataParser());
    }

    @RequestMapping(value = "/addRow", method = RequestMethod.POST)
    public ModelAndView submit2(@Valid @ModelAttribute("dataParser") DataParser dataParser,
                                @ModelAttribute("tableRowForm") DataParser tableRowForm,
                                BindingResult result, ModelMap model, ModelAndView modelAndView) {
        if (result.hasErrors()) {
            model.addAttribute("message", "Bad data input");
            modelAndView.setViewName("app");
        }
        dataParserService.saveData(tableRowForm);


        return new ModelAndView("redirect:/app");
    }
    @RequestMapping(value = "/deleteRow", method = RequestMethod.DELETE)
    public ModelAndView submit3(@Valid @ModelAttribute("dataParser") DataParser dataParser,
                                @ModelAttribute("tableRowForm") DataParser tableRowForm,
                                BindingResult result, ModelMap model, ModelAndView modelAndView) {
        if (result.hasErrors()) {
            model.addAttribute("message", "Bad data input");
            modelAndView.setViewName("app");
        }
        dataParserService.deleteData(tableRowForm);


        return new ModelAndView("redirect:/app");
    }
}
