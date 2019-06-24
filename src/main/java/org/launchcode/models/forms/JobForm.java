package org.launchcode.models.forms;

import lombok.Data;
import lombok.NonNull;
import org.launchcode.models.*;
import org.launchcode.models.data.JobData;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LaunchCode
 */
@Data
public class JobForm {

    @NotNull
    @Size(min=1, message = "Name may not be empty")
    private String name;

    @NotNull
    private int employerId;

    @NonNull
    private String locationId;

    @NonNull
    private String coreCompetencyId;

    @NonNull
    private String positionTypeId;

    /*
        TODO #3 - Included other fields needed to create a job,
        with correct validation attributes and display names.
        Don't forget to add getters and setters
     */
    private List<Job> jobs;
    private List<Employer> employers;
    private List<Location> locations;
    private List<CoreCompetency> coreCompetencies;
    private List<PositionType> positionTypes;

    public JobForm() {

        JobData jobData = JobData.getInstance();

        /*
            TODO #4 - populate the other ArrayList collections needed in the view
        */
        jobs = jobData.findAll();
        locations = jobData.getLocations().findAll();
        coreCompetencies = jobData.getCoreCompetencies().findAll();
        positionTypes = jobData.getPositionTypes().findAll();
        employers = jobData.getEmployers().findAll();


    }

}
