package kz.alisher.greetgo.controller;

import kz.alisher.greetgo.domain.Dictionary;
import kz.alisher.greetgo.domain.Star;
import kz.alisher.greetgo.domain.User;
import kz.alisher.greetgo.mappers.DictionaryMapper;
import kz.alisher.greetgo.mappers.StarMapper;
import kz.alisher.greetgo.mappers.UserMapper;
import kz.alisher.greetgo.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by alisher
 */
@RestController
public class StarController {

    @Value("${star.delete.error}")
    private String errorMsg;
    @Value("${star.delete.success}")
    private String successMsg;

    @Autowired
    private StarMapper starMapper;

    @Autowired
    private DictionaryMapper dictionaryMapper;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Star> getAllStars() {
        return starMapper.findAllStars();
    }

    @RequestMapping(value = "/dictionaries", method = RequestMethod.GET)
    public List<Dictionary> getAllDictionary() {
        return dictionaryMapper.findAllDictionary();
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userMapper.findAllUsers();
    }

    @RequestMapping(value = "/user/save", method = RequestMethod.POST)
    public void createUser(@RequestBody User user) {
        userMapper.insert(user);
    }

    @RequestMapping(value = "/user/edit", method = RequestMethod.POST)
    public void updateUser(@RequestBody User user) {
        userMapper.update(user);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    @ResponseBody
    public void updateStar(@RequestBody Star star) {
        starMapper.update(star);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void createStar(@RequestBody Star star) {
        starMapper.insert(star);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ResponseEntity deleteStar(@PathVariable("id") long id, Model model) {
        Star star = starMapper.findStarById(id);
        if (star.getDictionary().getName().equals(Constants.YELLOW)) {
            return new ResponseEntity<>(errorMsg, HttpStatus.NOT_ACCEPTABLE);
        } else {
            starMapper.delete(id);
            return new ResponseEntity<>(successMsg, HttpStatus.OK);
        }
    }
}
