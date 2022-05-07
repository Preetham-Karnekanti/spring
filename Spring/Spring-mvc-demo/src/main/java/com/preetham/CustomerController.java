package com.preetham;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @RequestMapping("/showForm")
    public String showForm(Model model)
    {
        model.addAttribute("customer",new Customer());
        return "customer-form";
    }
    @RequestMapping("/processForm")
    public String processForm(@Valid @ModelAttribute("customer") Customer theCustomer, BindingResult theBindingResult)
    {
        System.out.println(theBindingResult);
        if(!theBindingResult.hasErrors())
            return "customer-confirmation";
        return "customer-form";
    }
    @InitBinder
    public void initBinder(WebDataBinder dataBinder)
    {
        StringTrimmerEditor stringTrimmerEditor =new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }
}
