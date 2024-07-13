package com.illdangag.iam.data.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ServerInfo {
    private String branch;
    private String commit;
    private String tags;
}
