package com.child.service.team;

import com.child.dao.team.TeamMemberDao;
import com.child.dto.TeamMemberDto;
import com.child.model.TeamMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamMemberServiceImp implements TeamMemberService{
    @Autowired
    private TeamMemberDao teamMemberDao;
    @Override
    public TeamMember createMember(TeamMemberDto teamMemberDto) {
        String name = teamMemberDto.getName();
        String position = teamMemberDto.getPosition();
        String about = teamMemberDto.getAbout();

        TeamMember teamMember = new TeamMember();
        teamMember.setName(name);
        teamMember.setPosition(position);
        teamMember.setAbout(about);
        return teamMemberDao.create(teamMember);
    }

    @Override
    public Optional<TeamMember> findById(Long id) {
        return teamMemberDao.findById(id);
    }

    @Override
    public List<TeamMember> findAll() {
        return teamMemberDao.findAll();
    }
}
