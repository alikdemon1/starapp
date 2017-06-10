package kz.alisher.greetgo.controller;

import kz.alisher.greetgo.domain.Login;
import kz.alisher.greetgo.domain.Star;
import kz.alisher.greetgo.mappers.StarMapper;
import kz.alisher.greetgo.validators.LoginValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by alisher
 */
@Controller
public class MainController {

    @Autowired
    private StarMapper starMapper;

    @Autowired
    private LoginValidator loginValidator;

    @InitBinder("login")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(loginValidator);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity doLogin(@RequestBody @Validated Login login, BindingResult result, Model model) {
        if (result.hasErrors()) {
            StringBuilder builder = new StringBuilder();
            builder.append(result.getFieldError("email").getCode());
            builder.append(result.getFieldError("password").getCode());
            return new ResponseEntity<>(builder, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String createNewStar(Model model) {
        return "create";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model) {
        return "main";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editStarById(Model model, @PathVariable("id") Long id) {
        Star star = starMapper.findStarById(id);
        return new ModelAndView("edit", "star", star);
    }
}
