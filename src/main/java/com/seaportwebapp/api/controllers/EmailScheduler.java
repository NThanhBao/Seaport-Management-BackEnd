package com.seaportwebapp.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.seaportwebapp.api.repositories.UsersRepository;
import com.seaportwebapp.api.services.EmailService;
import com.seaportwebapp.api.models.SEC;
import com.seaportwebapp.api.models.Users;

@Component
public class EmailScheduler {

	private final EmailService emailService;
	private final UsersRepository repository;

	@Autowired
	public EmailScheduler(EmailService emailService, UsersRepository userRepository) {
		this.emailService = emailService;
		this.repository = userRepository;
	}

	@Scheduled(cron = "0 20 17 * * ?") // Lịch trình: 18:26
	public void sendAutomaticEmail() {
		List<Users> users = repository.findAll();

		for (Users user : users) {
			String from = SEC.email;
			String to = user.getEmail();
			String subject = SEC.subject;
			String text = SEC.text;

			try {
				emailService.sendEmail(from, to, subject, text);
				System.out.println("Automatic email sent successfully to: " + to);
			} catch (Exception e) {
				System.out.println("Failed to send automatic email to: " + to + ", Error: " + e.getMessage());
			}
		}
	}
}
