package com.aki.jobms.Job.repository;

import com.aki.jobms.Job.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
