package com.bpg.BankOfUsers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
	public class HomeController {

		@GetMapping("/")
		public String handler(Model model) {

			return "home";
		}
	}