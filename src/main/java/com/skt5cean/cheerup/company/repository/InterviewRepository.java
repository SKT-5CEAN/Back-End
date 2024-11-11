package com.skt5cean.cheerup.company.repository;

import com.skt5cean.cheerup.company.domain.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterviewRepository extends JpaRepository<Interview, Long> {

    List<Interview> findAllByCompanyId(Long companyId);
    List<Interview> findAllByCompanyIdAndState(Long companyId, Interview.StateType state);

}
