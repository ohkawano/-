package com.example.demo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.TalkRepository;

@Service
public class DateService {
	@Autowired
	TalkRepository talkRepository;

	public Date getNowDate() {
		return null;

	}

}
