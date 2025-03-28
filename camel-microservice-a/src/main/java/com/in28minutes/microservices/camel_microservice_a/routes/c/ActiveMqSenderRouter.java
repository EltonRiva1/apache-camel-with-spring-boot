package com.in28minutes.microservices.camel_microservice_a.routes.c;

import java.io.IOException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.crypto.CryptoDataFormat;
import org.springframework.stereotype.Component;

@Component
public class ActiveMqSenderRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		from("timer:active-mq-timer?period=10000").transform().constant("My message for Active MQ").log("${body}")
				.marshal(this.createEncryptor()).to("activemq:my-activemq-queue");
	}

	@SuppressWarnings("unused")
	private CryptoDataFormat createEncryptor() throws KeyStoreException, IOException, NoSuchAlgorithmException,
			CertificateException, UnrecoverableKeyException {
		KeyStore keyStore = KeyStore.getInstance("JCEKS");
		ClassLoader classLoader = getClass().getClassLoader();
		keyStore.load(classLoader.getResourceAsStream("myDesKey.jceks"), "someKeystorePassword".toCharArray());
		Key sharedKey = keyStore.getKey("myDesKey", "someKeyPassword".toCharArray());

		CryptoDataFormat sharedKeyCrypto = new CryptoDataFormat("DES", sharedKey);
		return sharedKeyCrypto;
	}
}
