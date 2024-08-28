package com.projectc6.gitreposerver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={
		"com.projectc6.gitreposerver.controller", "com.projectc6.gitreposerver.driver",
		"com.projectc6.gitreposerver.model", "com.projectc6.gitreposerver.repository",
		"com.projectc6.gitreposerver.service", "com.projectc6.gitreposerver.util"})
public class GitreposerverApplication{
	public static void main(String[] args) {
		SpringApplication.run(GitreposerverApplication.class, args);
	}
}
