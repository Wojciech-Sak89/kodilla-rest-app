package com.crud.tasks.scheduler;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class SchedulerSettings {
    @Value("cron = \"0 0 10 * * *\"")
    private String everyDayAt_10AM;

    @Value("fixedDelay = 10000")
    private String every_10_seconds;
}
