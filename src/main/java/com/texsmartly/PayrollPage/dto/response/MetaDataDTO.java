package com.texsmartly.PayrollPage.dto.response;

import java.io.Serializable;
import java.util.List;

public class MetaDataDTO implements Serializable {
    private long totalCount;
    private Integer currentPageSize;
    private  Integer size;
    private Boolean status;
    private List<ErrorModelDTO> errorList;
}
