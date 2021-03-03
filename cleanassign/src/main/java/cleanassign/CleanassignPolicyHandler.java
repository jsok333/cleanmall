package cleanassign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import cleanassign.config.kafka.KafkaProcessor;
import cleanassign.util.Assigner;

@Service
public class CleanassignPolicyHandler {
	@Autowired
    CleanassignRepository 할당Repository;
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }
    
    //private String 호출상태; //호출,호출중,호출확정,호출취소
    @StreamListener(KafkaProcessor.INPUT)
    public void whenever택시할당요청됨_(@Payload CleanmanageAssigned 택시할당요청됨){
    	System.out.println("##### EVT TYPE[CleanmanageAssigned]  : " + 택시할당요청됨.getEventType());
        if(택시할당요청됨.isMe()){
            System.out.println("##### listener  : " + 택시할당요청됨.toJson());
            
            if(택시할당요청됨.get호출상태() != null  && 택시할당요청됨.get호출상태().equals("called"))
            {
            	
            	택시할당요청됨.set호출상태("호출확정");
            	//CleanassignCompleted CleanassignCompleted = Assigner.get택시할당됨();
            	//BeanUtils.copyProperties(CleanmanageAssigned, CleanassignCompleted);
            	//CleanassignCompleted.setEventType("CleanassignCompleted");
            	택시할당요청됨.publish();
            	
            	CleanassignCompleted 할당확인됨 = Assigner.get택시할당됨();
            	할당확인됨.setId(택시할당요청됨.getId());
            	할당확인됨.set할당상태("할당확정");
                할당확인됨.set고객휴대폰번호(택시할당요청됨.get고객휴대폰번호());
                할당확인됨.set호출위치(택시할당요청됨.get고객위치());
            	할당확인됨.setEventType("CleanassignCompleted");
            	//CleanmanageAssigned.publishAfterCommit();
            	할당확인됨.publish(); 
            }  
        }
    }
    
    @StreamListener(KafkaProcessor.INPUT)
    public void whenever할당확인됨_(@Payload CleanassignCompleted 할당확인됨){
    	System.out.println("##### EVT TYPE[CleanassignCompleted]  : " + 할당확인됨.getEventType());
        if(할당확인됨.isMe()){
            System.out.println("##### listener  : " + 할당확인됨.toJson());
            
            if(할당확인됨.get할당상태() != null  && 할당확인됨.get할당상태().equals("할당확정"))
            {
            	
//            	CleanassignCompleted CleanassignCompleted = Assigner.get택시할당됨();
//            	BeanUtils.copyProperties(CleanmanageAssigned, CleanassignCompleted);
//            	
//                //CleanassignCompleted.setEventType("CleanassignCompleted");
//            	CleanassignCompleted.setEventType("CleanassignCompleted");
//            	//CleanmanageAssigned.publishAfterCommit();
//            	CleanassignCompleted.publish();
            }  
        }
    }
    
    
    @StreamListener(KafkaProcessor.INPUT)
    public void whenever택시할당취소됨_(@Payload CleanmanageCancelled 택시할당취소됨){
    	
        if(택시할당취소됨.isMe()){
            System.out.println("##### listener  : " + 택시할당취소됨.toJson());
            
            
            할당Repository.findById(Long.valueOf(택시할당취소됨.getId())).ifPresent((택시호출) -> {
				택시호출.setStatus("할당취소");
				할당Repository.save(택시호출);
			});
            
            택시할당취소됨.publish();
        }
    }

}
