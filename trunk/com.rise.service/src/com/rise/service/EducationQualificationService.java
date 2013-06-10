package com.rise.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface EducationQualificationService extends BaseService {

	public List<String> getStates();
}
