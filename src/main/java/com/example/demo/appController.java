package com.example.demo;

import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by atulsahaney on 9/11/17.
 */

public class appController {

    private final reverse _reverse;

    private final greetPerson _greet;

    public appController(reverse reverse, greetPerson greet) {
        this._reverse = reverse;
        this._greet = greet;
    }

    @RequestMapping(value = "/reverse", method = GET)
    public String reverse(@RequestBody("str") String str) {

        return _reverse.reversed(str);
    }

    @RequestMapping(value = "/person", method = POST)
    public @ResponseBody greetPerson greetPerson(@PathVariable person person) {

        return _greet.greetPerson(person);
    }
}
