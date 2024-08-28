package com.projectc6.gitreposerver.service;

import com.projectc6.gitreposerver.util.DynamicConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.ScheduledFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DynamicTaskServiceTest {

    @Mock
    private DynamicConfiguration mockDynamicConfiguration;
    @Mock
    private ThreadPoolTaskScheduler mockThreadPoolTaskScheduler;

    @InjectMocks
    private DynamicTaskService dynamicTaskServiceUnderTest;

    @Test
    void testStartCron() {
        // Setup
        doAnswer(invocation -> {
            ((Runnable) invocation.getArguments()[0]).run();
            final ScheduledFuture<?> mockFuture = mock(ScheduledFuture.class);
            doReturn(null).when(mockFuture).get();
            doReturn(true).when(mockFuture).isDone();
            return mockFuture;
        }).when(mockThreadPoolTaskScheduler).schedule(any(Runnable.class), any(Trigger.class));
        when(mockDynamicConfiguration.getTimeInterval()).thenReturn("result");

        // Run the test
        dynamicTaskServiceUnderTest.startCron("repoLink");

        // Verify the results
    }

    @Test
    void testStopCron() {
        // Setup

        // Run the test
        dynamicTaskServiceUnderTest.stopCron();

        // Verify the results
    }
}
