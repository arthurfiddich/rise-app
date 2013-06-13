package com.rise.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rise.common.model.State;

@Service
public interface EducationQualificationService extends BaseService {

	public List<State> getStates();
}
