package cleanmanage;

import javax.persistence.*;

import org.springframework.beans.BeanUtils;

@Entity
@Table(name="Cleanmanage_table")
public class Cleanmanage {
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    private String orderId;
    private String custel;
    private String location;
    private String state; //호출,호출중,호출확정,호출취소
    private Integer cost;
    
    private String cleanmanid;
    private String cleanman;
    private String cleanmantel;
    
    @PostPersist
    public void onPostPersist(){
    	System.out.println("###############################=================================");

//    	CleanmanageAssigned CleanmanageAssigned = new CleanmanageAssigned();
//        BeanUtils.copyProperties(this, CleanmanageAssigned);
//        CleanmanageAssigned.publishAfterCommit();
        System.out.println("휴대폰번호 " + custel);
        System.out.println("location " + location);
        System.out.println("state " + state);
        System.out.println("cost " + cost);
    	
        System.out.println("orderId " + orderId);
        System.out.println("id " + getId());
        //System.out.println("location " + location);
        //System.out.println("state " + state);
        //System.out.println("cost " + cost);
    	
        
        if("호출취소".equals(state)){
			CleanmanageCancelled 택시할당취소됨 = new CleanmanageCancelled();
            BeanUtils.copyProperties(this, 택시할당취소됨);
            택시할당취소됨.publish();

        }else{

        	state = "called";
        	CleanmanageAssigned 택시할당요청됨 = new CleanmanageAssigned();
        	택시할당요청됨.setId(Long.valueOf(orderId));
        	
        	택시할당요청됨.set고객위치(location);
        	택시할당요청됨.setcustel(custel);
        	택시할당요청됨.set예상요금(cost);
        	택시할당요청됨.set호출상태(state);
            BeanUtils.copyProperties(this, 택시할당요청됨);
            택시할당요청됨.publishAfterCommit();
            
            
            // 테스트 코드~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//            try {
//                Thread.currentThread().sleep((long) (400 + Math.random() * 220));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }    
    }
    



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


	public String getCustel() {
		return custel;
	}


	public void set휴대폰번호(String 휴대폰번호) {
		this.custel = 휴대폰번호;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation( String location ) {
		this.location = location;
	}
	
	public String getState() {
		return state;
	}
	public void setState( String state ) {
		this.state = state;
	}

	public Integer getCost() {
		return cost;
	}


	public void setCost( Integer cost ) {
		this.cost = cost;
	}


	public String getCleanmanid() {
		return cleanmanid;
	}


	public void setCleanmanid( String cleanmanid ) {
		this.cleanmanid = cleanmanid;
	}


	public String getCleanman() {
		return cleanman;
	}


	public void setCleanman( String cleanman ) {
		this.cleanman = cleanman;
	}


	public String getCleanmantel() {
		return cleanmantel;
	}


	public void setCleanmantel( String cleanmantel ) {
		this.cleanmantel = cleanmantel;
	}


	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}




}
