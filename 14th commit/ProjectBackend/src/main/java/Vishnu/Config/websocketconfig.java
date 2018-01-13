package Vishnu.Config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
 

@Configuration
@EnableWebSocketMessageBroker
@ComponentScan(basePackages="Vishnu")
public class websocketconfig implements WebSocketMessageBrokerConfigurer {

	public void configureClientInboundChannel(ChannelRegistration arg0) {
		 
		
	}

	public void configureClientOutboundChannel(ChannelRegistration arg0) {
		 
		
	}

	public void configureMessageBroker(MessageBrokerRegistry configurer) {
	
		System.out.println("CONFIGURE MESSAGE BROKER REGISTRY");
		configurer.enableSimpleBroker("/queue/","/topic/");
		configurer.setApplicationDestinationPrefixes("/app");
	}

	public void registerStompEndpoints(StompEndpointRegistry registry) {
		System.out.println("REGISTER STOM ENDPOINT");
		registry.addEndpoint("/chatmodule").withSockJS();
		
	}


	

	 

}