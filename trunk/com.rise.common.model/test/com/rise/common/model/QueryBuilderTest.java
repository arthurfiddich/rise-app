package com.rise.common.model;

import com.rise.common.util.hibernate.QueryBuilder;

public class QueryBuilderTest {
	public static void main(String[] args) {
		System.out.println(QueryBuilder.buildQuery(Person.class));
	}
}
