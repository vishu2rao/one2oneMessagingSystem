package com.eventm.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eventm.domain.EventMessageModel;
import com.eventm.message.EventMessageAlt;
import com.eventm.repository.EventMessageRepository;
import com.mongodb.MongoClient;

/**
 * @author  visweswarar
 */

@Controller
public class EventMessageController {
	
//	MongoTemplate mongoTemplate;
	

    private static final RequestMethod[] GET = null;


	@Autowired
    private EventMessageRepository eventMessageRepository;


	private MongoTemplate mongoTemplate;
    
    
   
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/chat")
   
    public String chat() {
        return "chat";
    }
    public @Bean
	MongoDbFactory mongoDbFactory() throws Exception {
		return new SimpleMongoDbFactory(new MongoClient("localhost",27017), "eventm");
	}

	public @Bean
	MongoTemplate mongoTemplate() throws Exception {

		mongoTemplate = new MongoTemplate(mongoDbFactory());
		System.out.println("mongoTemplate stats :" +mongoTemplate.getCollection("eventMessageModel").getCount());
		return mongoTemplate;

	}
	 @RequestMapping(value = "/mymessage", method = RequestMethod.POST)
	    HttpEntity<EventMessageAlt> create(@RequestBody EventMessageModel messageRequest) {
		 System.out.println("Message Object" +messageRequest.toString());

		 EventMessageModel savedMessage = eventMessageRepository.save(new EventMessageModel(
				    messageRequest.getText(),
				 	messageRequest.getFrom(),
				 	new Date(),
	                messageRequest.getTo()
	            )
	        );
		    System.out.println("Writing Message for" +savedMessage.toString());
	        return new ResponseEntity<EventMessageAlt>(new EventMessageAlt(savedMessage), HttpStatus.OK);
	    }
	   @RequestMapping(value = "/newmessage", method = RequestMethod.POST)
	   @SendTo("/topic/newMessage")
	    public ResponseEntity save1(EventMessageModel eventMessageModel) {
	    	//System.out.println("Inside save" +chatid);
	        EventMessageModel chatMessage = new EventMessageModel(eventMessageModel.getText(), eventMessageModel.getFrom(), new Date(),eventMessageModel.getTo());
	        System.out.println("Writing Message" +chatMessage.toString());
	        EventMessageModel message = eventMessageRepository.save(chatMessage);
	        Query query = new Query();
	    	query.addCriteria(Criteria.where("to").is(eventMessageModel.getTo()));
	    	query.addCriteria(Criteria.where("from").is(eventMessageModel.getFrom()));
	    	System.out.println("Query is :" +query.toString());
	    	List<EventMessageModel> chatMessageModelList=null ;
	    	if(mongoTemplate!=null)
	    	{
	    	
	    	 chatMessageModelList = mongoTemplate.find(query, EventMessageModel.class);
	    	}else
	    		System.out.println("mongoTemplate is NULL,Please mongodb connection details..");
	    
	    	return new ResponseEntity(chatMessageModelList, HttpStatus.OK);
	    	
	    }
	
    @RequestMapping(value = "/messages", method = RequestMethod.POST)  
    @MessageMapping("/newMessage")
    @SendTo("/topic/newMessage")
    public ResponseEntity save(@RequestBody EventMessageModel eventMessageModel) {
    	//System.out.println("Inside save" +chatid);
        EventMessageModel chatMessage = new EventMessageModel(eventMessageModel.getText(), eventMessageModel.getFrom(), new Date(),eventMessageModel.getTo());
        //System.out.println("Writing Message" +chatMessage.toString());
        EventMessageModel message = eventMessageRepository.save(chatMessage);
        Query query = new Query();
    	query.addCriteria(Criteria.where("to").is(eventMessageModel.getTo()));
    	query.addCriteria(Criteria.where("from").is(eventMessageModel.getFrom()));
    	//System.out.println("Query is :" +query.toString());
    	List<EventMessageModel> chatMessageModelList=null ;
    	if(mongoTemplate!=null)
    	{
    	
    	 chatMessageModelList = mongoTemplate.find(query, EventMessageModel.class);
    	}else
    		System.out.println("mongoTemplate is NULL, Please mongodb connection details..");
       // List<EventMessageModel> chatMessageModelList = eventMessageRepository.findAll(new PageRequest(0, 5, Sort.Direction.DESC, "createDate")).getContent();
    	
    	
    	if(!chatMessageModelList.isEmpty())
       	 System.out.println("MessageList" +chatMessageModelList.get(0));
    	
       // return new EventMessage(chatMessageModelList.toString());
    	
    	
    	 return new ResponseEntity(chatMessageModelList, HttpStatus.OK);
    	
    }
    
 
   @RequestMapping(value = "/getMessages",params ={"from","to"}, method = RequestMethod.GET)
    public  @ResponseBody HttpEntity 	 list( 
    		@RequestParam(value = "from") String from,
    		@RequestParam(value = "to") String to ) {
	   System.out.println("Reading  to:" +to+" from: "+from);
	   
	   EventMessageModel eventMessageModel =new EventMessageModel();
	  
	   eventMessageModel.setFrom(from);
	   eventMessageModel.setTo(to);
      
	
	   System.out.println("READ Message" +eventMessageModel.toString());
    	Query query = new Query();
    	query.addCriteria(Criteria.where("to").is(eventMessageModel.getTo()));
    	query.addCriteria(Criteria.where("from").is(eventMessageModel.getFrom()));
    	System.out.println("READ Query is :" +query.toString());
    	List<EventMessageModel> chatMessageModelList = mongoTemplate.find(query, EventMessageModel.class);
    	if(!chatMessageModelList.isEmpty())
    	 System.out.println(" READ MessageList" +chatMessageModelList.get(0));
    	 return new ResponseEntity(chatMessageModelList, HttpStatus.OK);
      
    }
   
   
   
 
    
    
  

}
