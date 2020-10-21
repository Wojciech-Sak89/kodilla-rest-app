package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import com.crud.tasks.scheduler.SchedulerSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    private SchedulerSettings schedulerSettings;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private CompanyConfig companyConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        basicMailInfo_ContextSetter(message, context, false, false);

        context.setVariable("application_functionality", functionality);

        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildAvailableTasksEmail(String message) {
        List<String> schedulerModes = new ArrayList<>();
        schedulerModes.add(schedulerSettings.getEvery_10_seconds());
        schedulerModes.add(schedulerSettings.getEveryDayAt_10AM());

        Context context = new Context();
        basicMailInfo_ContextSetter(message, context, true, true);

        context.setVariable("scheduler_settings", schedulerSettings);
        context.setVariable("scheduler_modes", schedulerModes);

        return templateEngine.process("mail/available-tasks-mail", context);
    }

    private void basicMailInfo_ContextSetter(String message, Context context, boolean isFriend, boolean showButton) {
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("preview_message", "Created Trello Card INFO");
        context.setVariable("goodbye_message",
                "Thank you " + adminConfig.getAdminName() + " for using our services");
        context.setVariable("company_details_name_and_goal",
                companyConfig.getCompanyName() + "\u2122 " + companyConfig.getCompanyGoal());
        context.setVariable("company_details_mail_and_phone",
                companyConfig.getCompanyEmail() + " tel.:" + companyConfig.getCompanyPhone());
        context.setVariable("show_button", showButton);
        context.setVariable("is_friend", isFriend);
        context.setVariable("admin_config", adminConfig);
    }
}
