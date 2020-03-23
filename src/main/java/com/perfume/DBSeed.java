package com.perfume;

import com.perfume.constant.StatusEnum;
import com.perfume.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

public class DBSeed {
    @Autowired
    private UserRepository userRepository;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedUsersTable();
    }

    private void seedUsersTable() {
        userRepository.findByStatus(StatusEnum.TEST.getValue());
    }
}
