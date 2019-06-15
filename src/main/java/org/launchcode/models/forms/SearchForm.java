package org.launchcode.models.forms;

import lombok.Data;
import org.launchcode.models.JobFieldType;

/**
 * Created by LaunchCode
 */
@Data
public class SearchForm {

    // The search options
    private JobFieldType[] fields = JobFieldType.values();

    // The selected search options
    private JobFieldType searchField = JobFieldType.ALL;

    // The search string
    private String keyword;

}
