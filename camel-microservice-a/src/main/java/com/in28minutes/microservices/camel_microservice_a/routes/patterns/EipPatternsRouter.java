package com.in28minutes.microservices.camel_microservice_a.routes.patterns;

import java.util.List;
import java.util.Map;

import org.apache.camel.Body;
import org.apache.camel.ExchangeProperties;
import org.apache.camel.Headers;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.in28minutes.microservices.camel_microservice_a.CurrencyExchange;

public class EipPatternsRouter extends RouteBuilder {
	@Autowired
	SplitterComponent splitterComponent;
	@Autowired
	DynamicRouterBean dynamicRouterBean;

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		getContext().setTracing(true);
		errorHandler(deadLetterChannel("activemq:dead-letter-queu"));
		from("file:files/aggregate-json").unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class)
				.aggregate(simple("${body.to}"), new ArrayListAggregationStrategy()).completionSize(3)
				.to("log:aggregate-json");
		from("timer:dynamicRouting?period={{timePeriod}}").transform().constant("My Message is Hardcoded")
				.dynamicRouter(method(this.dynamicRouterBean));
		from("direct:endpoint1").wireTap("log:wire-tap").to("{{endpoint-for-logging}}");
		from("direct:endpoint2").to("log:directendpoint2");
		from("direct:endpoint3").to("log:directendpoint3");
	}

}

@Component
class SplitterComponent {
	public List<String> splitInput(String body) {
		return List.of("ABC", "DEF", "GHI");
	}
}

@Component
class DynamicRouterBean {
	Logger logger = LoggerFactory.getLogger(DynamicRouterBean.class);
	int invocations;

	public String decideTheNextEndpoint(@ExchangeProperties Map<String, String> properties,
			@Headers Map<String, String> headers, @Body String body) {
		this.logger.info("{} {} {}", properties, headers, body);
		this.invocations++;
		if (this.invocations % 3 == 0)
			return "direct:endpoint1";
		if (this.invocations % 3 == 1)
			return "direct:endpoint2,direct:endpoint3";
		return null;
	}
}