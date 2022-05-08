package com.ndt2101.helloworld.outputdata;

import com.ndt2101.helloworld.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Create by Tuan
 * 17:44
 * 15 Jan 2022
 */
@Getter
@Setter
public class UserOutput {
    private int currentPage;
    private int totalPage;
    private List<UserDTO> resultList;

}
