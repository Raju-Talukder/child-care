package com.child.controller;

import com.child.dto.TeamMemberDto;
import com.child.service.team.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class TeamMemberController {
    @Autowired
    private TeamMemberService teamMemberService;

    @GetMapping("/add/team")
    public String getAddTeamMember(Model model, TeamMemberDto teamMemberDto){
        model.addAttribute("teamMemberDto",teamMemberDto);
        return "admin/addTeam";
    }

    @PostMapping("/add/team")
    public String addTeamMember(Model model, TeamMemberDto teamMemberDto){
        teamMemberService.createMember(teamMemberDto);
        return "redirect:/admin/add/team";
    }
}
