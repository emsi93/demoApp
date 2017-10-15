package com.example.demoApp.schedule;

import com.example.demoApp.mvc.entity.Link;
import com.example.demoApp.mvc.repository.LinkRepository;
import com.example.demoApp.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component
@Slf4j
public class ScheduledTask {

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private DateUtil dateUtil;

    @Scheduled(fixedRate = 1000 * 60 * 60)
    public void checkLinks() {
        List<Link> links = linkRepository.findAll();

        for(Link link:links){
            Timestamp timestamp = link.getData();
            if(!dateUtil.checkValidityUrl(timestamp)){
                linkRepository.delete(link);
                log.info("DELETED LINK: " + link.getId() + "," + link.getType() + ", " + link.getLink());
            }
        }
    }
}
