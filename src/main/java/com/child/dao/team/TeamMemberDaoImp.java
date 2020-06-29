package com.child.dao.team;

import com.child.model.TeamMember;
import com.child.repository.TeamMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TeamMemberDaoImp implements TeamMemberDao{
    @Autowired
    private TeamMemberRepository teamMemberRepository;
    @Override
    public Optional<TeamMember> findById(long id) {
        return teamMemberRepository.findById(id);
    }

    @Override
    public List<TeamMember> findAll() {
        return teamMemberRepository.findAll();
    }

    @Override
    public TeamMember create(TeamMember entity) {
        return teamMemberRepository.save(entity);
    }

    @Override
    public TeamMember update(TeamMember entity) {
        return teamMemberRepository.save(entity);
    }

    @Override
    public void delete(TeamMember entity) {
        teamMemberRepository.delete(entity);
    }

    @Override
    public void deleteById(long entityId) {
        teamMemberRepository.deleteById(entityId);
    }
}
