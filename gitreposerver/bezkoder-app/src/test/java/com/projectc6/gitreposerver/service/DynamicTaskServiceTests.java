package com.projectc6.gitreposerver.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

class DynamicTaskServiceTests extends DynamicTaskService {
    @Test
    void testCreateTaskScheduler() {
        try {
            ThreadPoolTaskScheduler threadPoolTaskScheduler = threadPoolTaskScheduler();

            assertNotNull(threadPoolTaskScheduler);
            assertTrue(threadPoolTaskScheduler instanceof ThreadPoolTaskScheduler);
        } catch (Exception e) {
            fail("ERROR: Exception, not instanceof");
        }
    }
}
