package edu.bank.users.controller;

import edu.bank.users.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(value = "/employee", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
@Slf4j
public class EmployeeController {

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result<String> getEmployee(@PathVariable Long id){
        return Result.success("hello");
    }
}
