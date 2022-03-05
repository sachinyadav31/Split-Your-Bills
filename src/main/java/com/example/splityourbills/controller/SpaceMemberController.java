package com.example.splityourbills.controller;

import com.example.splityourbills.common.BaseApiResponse;
import com.example.splityourbills.dto.space.SpaceDTO;
import com.example.splityourbills.dto.spacemember.NewSpaceMemberDTO;
import com.example.splityourbills.exception.custom_exceptions.common.InternalServerException;
import com.example.splityourbills.exception.custom_exceptions.common.ResourceNotFoundException;
import com.example.splityourbills.response.space.SpaceResponse;
import com.example.splityourbills.response.spaceMember.AddMemberToSpaceResponse;
import com.example.splityourbills.security.CurrentUser;
import com.example.splityourbills.security.UserPrincipal;
import com.example.splityourbills.service.implementation.SpaceMemberServiceImpl;
import com.example.splityourbills.service.implementation.SpaceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Add or invite into space
 * Get members by space id - divide it by invites/present or not
 * Get details by userId
 * Get Details by inviteId
 * Delete person by Person id
 * Delete person by Invite id
 * **/

@RestController
@RequestMapping("/api/spaces/member")
public class SpaceMemberController {

    @Autowired
    SpaceMemberServiceImpl spaceMemberService;

    @PostMapping("/add")
    public BaseApiResponse addOrInviteMemberIntoSpace(@RequestBody List<NewSpaceMemberDTO> spaceDTO){

        AddMemberToSpaceResponse addMemberToSpaceResponse =  spaceMemberService.addOrInviteMemberToSpace(spaceDTO);
        if (addMemberToSpaceResponse!=null){
            BaseApiResponse baseApiResponse = new BaseApiResponse(true);
            baseApiResponse.setData(addMemberToSpaceResponse);
            return baseApiResponse;
        }else{
            throw new InternalServerException("Unexpected error occured");
        }


    }

}