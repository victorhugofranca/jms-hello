package br.com.hello.world;

import java.util.UUID;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.PostActivate;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Stateless
public class Producer {

    @Resource(lookup = "jms/queue/myqueue")
    private Queue queue;

    @Inject
    private JMSContext jmsContext;

	@PostActivate
	private void init() {

	}

	@Path("/jms20")
	@GET
	public String produce() {
		String payload = new String(UUID.randomUUID().toString());

		jmsContext.createProducer().send(queue, payload);

		return "OK";
	}

	@PreDestroy
	private void finish() {

	}

}
