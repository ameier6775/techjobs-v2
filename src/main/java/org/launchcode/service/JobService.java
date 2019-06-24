package org.launchcode.service;

import org.launchcode.models.Job;
import org.launchcode.models.data.JobData;
import org.springframework.stereotype.Service;

@Service
public class JobService {
    private JobData jobData;

    public JobService() {
        this.jobData = JobData.getInstance();

    }
    public Job getJobById(int id) {
        return jobData.findById(id);
    }
}
