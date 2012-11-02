package it.softphone.rd.model.shared;
/**
 * Enum used by {@link Feed} to identify the action
 * The action, will be used in the ui components
 * @author Alessandro Casolla
 *
 */
public enum FeedAction {
	
	COMMENT ("default"),
    REPLY ("has responded to the request nr %s"),
    CLOSE ("has closed a request"),
    OPEN ("has opened a request"),
    ASSIGN ("has assigned request to"),
    SEND_MESSAGE ("has sent message to");
    
    private final String action;
    
    public String getAction(){
    	return action;
    }
    FeedAction(String action){
        this.action = action;
    }

}
