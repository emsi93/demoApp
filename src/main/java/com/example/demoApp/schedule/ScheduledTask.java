package com.example.demoapp.schedule;

import com.example.demoapp.mvc.entity.Link;
import com.example.demoapp.mvc.repository.LinkRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class ScheduledTask {

    @Autowired
    private LinkRepository linkRepository;

    @Value("${hours}")
    private String numberOfHours;

    /*@Autowired
    private DateUtil dateUtil;*/

    @Scheduled(fixedRate = 1000 * 60 * 60)
    public void checkLinks() {
        List<Link> links = linkRepository.findAll();

        for(Link link:links){
            if(link.getData().plusHours(Long.parseLong(numberOfHours)).isBefore(LocalDateTime.now())){
                linkRepository.delete(link);
                log.info("DELETED LINK: " + link.getId() + "," + link.getType() + ", " + link.getLink());
            }
        }
    }
}
