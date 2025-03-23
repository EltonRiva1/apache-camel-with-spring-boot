package com.in28minutes.microservices.camel_microservice_a.routes.patterns;

import java.util.ArrayList;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;

public class ArrayListAggregationStrategy implements AggregationStrategy {

	@SuppressWarnings("unchecked")
	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		// TODO Auto-generated method stub
		Object newBody = newExchange.getIn().getBody();
		ArrayList<Object> list = null;
		if (oldExchange == null) {
			list = new ArrayList<Object>();
			list.add(newBody);
			newExchange.getIn().setBody(list);
			return newExchange;
		} else {
			list = oldExchange.getIn().getBody(ArrayList.class);
			list.add(newBody);
			return oldExchange;
		}
	}

}