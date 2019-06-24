package org.launchcode.controllers;

import lombok.Data;
import org.launchcode.models.*;
import org.launchcode.models.forms.JobForm;
import org.launchcode.models.data.JobData;
import org.launchcode.service.JobService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.launchcode.TechjobsApplication.toSingleton;

/**
 * Created by LaunchCode
 */
@Data
@Controller
@RequestMapping(value = "job")
public class JobController {

    private JobService jobService;

    public JobController (JobService jobService) {
        this.jobService = jobService;
    }

    // The detail display for a given Job at URLs like /job?id=17
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model, int id) {

        // TODO #1 - get the Job with the given ID and pass it into the view
        model.addAttribute("job", jobService.getJobById(id));


        return "job-detail";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("jobForm", new JobForm());
        return "new-job";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @Valid JobForm jobForm, Errors errors) {

        // TODO #6 - Validate the JobForm model, and if valid, create a
        // new Job and add it to the jobData data store. Then
        // redirect to the job detail view for the new Job.
        if (errors.hasErrors()) {
            return "new-job";
        }



        Job job = new Job();



        job.setName(jobForm.getName());
        Employer employer =
                jobForm.getEmployers()
                    .stream()
                    .filter(e -> jobForm.getEmployerId() == e.getId()).collect(toSingleton());

        Location location =
                jobForm.getLocations()
                    .stream()
                    .filter(l -> jobForm.getLocationId().equals(l.getValue())).collect(toSingleton());

        PositionType positionType =
                jobForm.getPositionTypes()
                    .stream()
                    .filter(p -> jobForm.getPositionTypeId().equals(p.getValue())).collect(toSingleton());

        CoreCompetency coreCompetency =
                jobForm.getCoreCompetencies()
                    .stream()
                    .filter(c -> jobForm.getCoreCompetencyId().equals(c.getValue())).collect(toSingleton());

        job.setEmployer(employer);
        job.setLocation(location);
        job.setPositionType(positionType);
        job.setCoreCompetency(coreCompetency);

        JobData.getInstance().add(job);

        model.addAttribute("job", job);

        return "job-detail";
    }
}



