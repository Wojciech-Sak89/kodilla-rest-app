package com.crud.tasks.scheduler;

import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Getter
public class SchedulerSettings {
    @Value("cron = \"0 0 10 * * *\"")
    private String everyDayAt_10AM;

    @Value("fixedDelay = 10000")
    private String every_10_seconds;
}
