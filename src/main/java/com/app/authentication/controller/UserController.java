package com.app.authentication.controller;

//import com.app.authentication.model.Employee;
import com.app.authentication.model.User;
import com.app.authentication.model.ConfirmationToken;
import com.app.authentication.repository.ConfirmationTokenRepository;
//import com.app.authentication.repository.EmployeeRepository;
import com.app.authentication.repository.UserRepository;
import com.app.authentication.service.EmailSenderService;
//import com.app.authentication.service.EmployeeService;
import com.app.authentication.service.SecurityService;
import com.app.authentication.service.UserService;
import com.app.authentication.validator.UserValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

//    @Autowired
//    private EmployeeService employeeService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private EmployeeRepository employeeRepository;

    final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/registration")
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        User userForm = new User();
        modelAndView.addObject("userForm", userForm);
        modelAndView.setViewName("registration");
        return modelAndView;
    }


    @PostMapping("/registration")
    public ModelAndView createNewUser(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName ("registration");
        }

        userService.save(userForm);

        ConfirmationToken confirmationToken = new ConfirmationToken(userForm);

        confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(userForm.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("maciekg999@gmail.com");
        mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:8080/confirm-account?token="+confirmationToken.getConfirmationToken());

        emailSenderService.sendEmail(mailMessage);
//        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        modelAndView.addObject("emailId", userForm.getEmail());

        modelAndView.setViewName("successfulRegistration");

        return modelAndView;

    }
    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken)
    {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            User user = userRepository.findByEmail(token.getUser().getEmail());
            user.setEnabled(true);
            userRepository.save(user);
            modelAndView.setViewName("accountVerified");
        }
        else
        {
            modelAndView.addObject("message","The link is invalid or broken!");
            modelAndView.setViewName("error");
        }

        return modelAndView;
    }
    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }
//
//    @GetMapping("/welcome")
//    public String welcome(Model model) {
//        return "welcome";
//    }

//    @RequestMapping(value = "/toDo", method = RequestMethod.GET)
//    public ModelAndView emp(ModelMap model) {
//
//        ModelAndView modelAndView = new ModelAndView();
//        Employee employeeForm = new Employee();
//        employeeRepository.findAll();
//        model.addAttribute("employees", employeeRepository.findAll());
//
//        modelAndView.addObject("employeeForm", employeeForm);
//
//        return new ModelAndView("jChronicHome", "employee", new Employee());
//    }
//
//    @RequestMapping(value = "/addRow", method = RequestMethod.POST)
//    public ModelAndView submit2(@Valid @ModelAttribute("employee") Employee employee,
//                                @ModelAttribute("employeeForm") Employee employeeForm,
//                         BindingResult result, ModelMap model, ModelAndView modelAndView) {
//        if (result.hasErrors()) {
//            model.addAttribute("message", "Bad data input");
//            modelAndView.setViewName("toDo");
//        }
//        employeeService.saveData(employeeForm);
//
//
//        return new ModelAndView("redirect:/toDo");
//    }

    @GetMapping("/")
    public String asd(Model model) {
        return "login";
    }




//    @RequestMapping(value = "/employee", method = RequestMethod.GET)
//    public ModelAndView showForm() {
//        return new ModelAndView("employeeHome", "employee", new Employee());
//    }
//
//    @RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
//    public String submit(@Valid @ModelAttribute("employee") Employee employee,
//                         BindingResult result, ModelMap model) {
//        if (result.hasErrors()) {
//            return "error";
//        }
//        Span span = Chronic.parse(employee.getDate());
//        model.addAttribute("date", span);
////        model.addAttribute("date", employee.getDate());
//        model.addAttribute("id", employee.getId());
//
//        return "employeeView";
//    }



    public ConfirmationTokenRepository getConfirmationTokenRepository() {
        return confirmationTokenRepository;
    }

    public void setConfirmationTokenRepository(ConfirmationTokenRepository confirmationTokenRepository) {
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    public EmailSenderService getEmailSenderService() {
        return emailSenderService;
    }

    public void setEmailSenderService(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }
    public static void main(String[] args){}

}
