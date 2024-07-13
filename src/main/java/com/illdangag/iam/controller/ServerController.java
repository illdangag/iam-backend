package com.illdangag.iam.controller;

import com.illdangag.iam.data.response.ServerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PropertySources({
        @PropertySource(value = "classpath:git.properties", ignoreResourceNotFound = false),
})
public class ServerController {
    private final String branch;
    private final String commit;
    private final String tags;

    @Autowired
    public ServerController(@Value("${git.branch:dev}") String branch,
                            @Value("${git.commit.id:}") String commit,
                            @Value("${git.tags:}") String tags) {
        this.branch = branch;
        this.commit = commit;
        this.tags = tags;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ServerInfo> getServerInfo() {
        ServerInfo serverInfo = ServerInfo.builder()
                .branch(this.branch)
                .commit(this.commit)
                .tags(this.tags)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(serverInfo);
    }
}
