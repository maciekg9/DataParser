package com.app.core.controller;

import com.app.authentication.model.User;
import com.app.authentication.repository.UserRepository;
import com.app.core.model.DataParser;
import com.app.core.model.Event;
import com.app.core.repository.CalendarRepository;
import com.app.core.repository.DataParserRepository;
import com.app.core.service.DataParserService;
import com.app.core.service.EventService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Controller
public class CoreController {

    @Autowired
    private DataParserService dataParserService;

    @Autowired
    private DataParserRepository dataParserRepository;

    @Autowired
    private UserRepository userRepository;



    @Autowired
    private EventService eventService;

    @Autowired
    private CalendarRepository calendarRepository;

    @RequestMapping(value = "/app", method = RequestMethod.GET)
    public ModelAndView emp(ModelMap model, DataParser dataParser) {

        ModelAndView modelAndView = new ModelAndView();
        DataParser tableRowForm = new DataParser();
        Event tableRowForm2 = new Event();
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = this.userRepository.findByUsername(name);

        dataParserRepository.findByUser(user);
        model.addAttribute("tableRows", dataParserRepository.findByUser(user));
        modelAndView.addObject("tableRowForm", tableRowForm);

        calendarRepository.findByUser(user);
        model.addAttribute("tableRows2", calendarRepository.findByUser(user));
        modelAndView.addObject("tableRowForm2", tableRowForm2 );

        return new ModelAndView("Home", "dataParser", new DataParser());
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

    @RequestMapping(value = "/addQuickRow", method = RequestMethod.POST)
    public ModelAndView addQuickEvent(@Valid @ModelAttribute("dataParser") DataParser dataParser,
                                @ModelAttribute("tableRowForm") DataParser tableRowForm,
                                BindingResult result, ModelMap model, ModelAndView modelAndView) {
        if (result.hasErrors()) {
            model.addAttribute("message", "Bad data input");
            modelAndView.setViewName("app");
        }

        dataParserService.saveData(tableRowForm);

        return new ModelAndView("redirect:/calendar");
    }
    @RequestMapping(value = "/deleteRow/{id}", method = RequestMethod.DELETE)
    public ModelAndView submit2(@Valid @ModelAttribute("dataParser") DataParser dataParser,
                                @ModelAttribute("tableRowForm") DataParser tableRowForm,@PathVariable("id") Long id,
                                BindingResult result, ModelMap model, ModelAndView modelAndView) {
        if (result.hasErrors()) {
            model.addAttribute("message", "Bad data input");
            modelAndView.setViewName("app");
        }

        dataParserRepository.deleteById(id);

        return new ModelAndView("redirect:/app");
    }
    @RequestMapping(value = "/deleteRow2/{id}", method = RequestMethod.DELETE)
    public ModelAndView submit3(@ModelAttribute ("event") Event event
                                ,@PathVariable("id") Long id,
                                BindingResult result, ModelMap model, ModelAndView modelAndView) {
        if (result.hasErrors()) {
            model.addAttribute("message", "Bad data input");
            modelAndView.setViewName("app");
        }

        calendarRepository.deleteById(id);

        return new ModelAndView("redirect:/app");
    }
    @RequestMapping(value = "/findAllDP", method = RequestMethod.GET)
    @ResponseBody
    public String findallDP () {

        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = this.userRepository.findByUsername(name);
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return  gson.toJson(dataParserRepository.findByUser(user)).replace("CEST", "");
    }
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public String findall () {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = this.userRepository.findByUsername(name);
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return  gson.toJson(calendarRepository.findByUser(user));
    }

    @RequestMapping(value = "/calendar", method = RequestMethod.GET)
    public ModelAndView getCalendar (ModelAndView modelAndView, ModelMap modelMap){
        DataParser tableRowForm = new DataParser();
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = this.userRepository.findByUsername(name);
        modelAndView.addObject("tableRowForm", tableRowForm);
        modelMap.put("event", new Event());
    return new ModelAndView("calendar3", "dataParser", new DataParser());
    }

    @RequestMapping(value = "/calendarAdd", method = RequestMethod.POST)
    public ModelAndView add2 (ModelAndView modelAndView, @ModelAttribute ("event") Event event, @Valid @ModelAttribute("dataParser") DataParser dataParser,
                              @ModelAttribute("tableRowForm") DataParser tableRowForm, @Param("date") String date, HttpServletRequest request, ModelMap modelMap) {
        if (date == null || date.isEmpty()) {

        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            event.setStart(simpleDateFormat.parse(request.getParameter("start")));

            event.setEnd(simpleDateFormat.parse(request.getParameter("end")));

            eventService.saveEvent(event);
        }
        catch(Exception e){
            modelMap.put("event", event);
            return new ModelAndView("calendar");
        }
    }
        else{
            dataParserService.saveData(tableRowForm);

        }
        return new ModelAndView("redirect:/calendar");

    }

}

