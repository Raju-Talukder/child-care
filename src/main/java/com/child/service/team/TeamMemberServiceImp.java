package com.child.service.team;

import com.child.dto.TeamMemberDto;
import com.child.model.TeamMember;
import com.child.repository.TeamMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamMemberServiceImp implements TeamMemberService{
    @Autowired
    private TeamMemberRepository teamMemberRepository;
    @Override
    public TeamMember createMember(TeamMemberDto teamMemberDto) {
        String name = teamMemberDto.getName();
        String position = teamMemberDto.getPosition();
        String about = teamMemberDto.getAbout();

        TeamMember teamMember = new TeamMember();
        teamMember.setName(name);
        teamMember.setPosition(position);
        teamMember.setAbout(about);
        return teamMemberRepository.save(teamMember);
    }

    @Override
    public Optional<TeamMember> findById(Long id) {
        return teamMemberRepository.findById(id);
    }

    @Override
    public List<TeamMember> findAll() {
        return teamMemberRepository.findAll();
    }
}
