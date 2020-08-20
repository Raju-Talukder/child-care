package com.child.service.team;

import com.child.dto.TeamMemberDto;
import com.child.model.Packages;
import com.child.model.TeamMember;

import java.util.List;
import java.util.Optional;

public interface TeamMemberService {
    public TeamMember createMember(TeamMemberDto teamMemberDto);
    Optional<TeamMember> findById(Long id);
    public List<TeamMember> findAll();
}
