package com.demo.controller;

import com.demo.jpa.Girl;
import com.demo.repository.GirlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GirlController {

    @Autowired
    private GirlRepository girlRepository;

    @GetMapping(value = "/getGirls")
    public List<Girl> getGirlList() {
        return girlRepository.findAll();
    }

    @PostMapping(value = "/saveGirl")
    public Girl saveGirl(@RequestParam(value = "name",required = false) String name,
                         @RequestParam(value = "cupSize") String cupSize,
                         @RequestParam(value = "age") Integer age) {
        Girl girl = new Girl();
        girl.setAge(age);
        girl.setCupSize(cupSize);
        girl.setName(name);
        return girlRepository.save(girl);
    }

    @GetMapping(value = "/girl/{id}")
    public Girl findById(@PathVariable("id") Integer id) {

        return girlRepository.getOne(id);
    }

    @PutMapping(value = "/girl/{id}")
    public Girl updateGirl(@PathVariable("id") Integer id,
                           @RequestParam(value = "name") String name,
                           @RequestParam(value = "age") Integer age,
                           @RequestParam(value = "cupSize") String cupSize) {
        Girl girl = new Girl();
        girl.setId(id);
        girl.setName(name);
        girl.setCupSize(cupSize);
        girl.setAge(age);
        return girlRepository.saveAndFlush(girl);
    }

    @DeleteMapping(value = "/deleteGirl/{id}")
    public void deleteById(@PathVariable("id") Integer id) {
        girlRepository.deleteById(id);
    }

    @GetMapping(value = "/girl/age/{age}")
    public List<Girl> findByAge(@PathVariable("age") Integer age) {
        return girlRepository.findByAge(age);
    }
}
