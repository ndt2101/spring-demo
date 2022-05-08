package com.ndt2101.helloworld.controller;

import com.ndt2101.helloworld.outputdata.UserOutput;
import com.ndt2101.helloworld.dto.UserDTO;
import com.ndt2101.helloworld.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

/**
 * Create by Tuan
 * 12:08
 * 14 Jan 2022
 */
@CrossOrigin //?
@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    IUserService userService;

    @PostMapping("register")
    public UserDTO register(@RequestBody UserDTO userDTO) {
        return userService.save(userDTO);
    }

    @PutMapping("user/{id}")
    public UserDTO update(@RequestBody UserDTO userDTO, @PathVariable("id") Long id) {
        return userService.save(userDTO);
    }

    @GetMapping("user")
    public UserOutput getAllUser(@RequestParam(name = "page", required = false) Integer page,
                                 @RequestParam(name = "limit", required = false) Integer limit) {
        UserOutput userOutput = new UserOutput();
        if (page != null && limit != null) {
            Pageable pageable = PageRequest.of(page, limit, Sort.by("name"));
            userOutput.setCurrentPage(page);
            userOutput.setTotalPage((int) Math. ceil((double) userService.totalUser() / limit));
            userOutput.setResultList(userService.findAllUser(pageable));

        } else {
            userOutput.setResultList(userService.findAllUser());
        }
        return userOutput;
    }


}
