package org.home.printservice.model.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
public class XmlDataJob extends ArrayList<JobRequest> {

}
