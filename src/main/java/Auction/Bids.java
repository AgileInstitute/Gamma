package Auction;

import java.util.HashMap;

public class Bids {
	HashMap<String, Bid> bids = new HashMap<String, Bid>();
	Bid highBid = null;
	
	public HashMap<String, Bid> getBids() {
		return bids;
	}
	
	public void addBid(Bid bid) {
		this.bids.put(bid.get_user(), bid);
	}
	
	public void removeBid(Bid bid){
		this.bids.remove(bid);
	}
	
	public Bid getHighBid() {
		return highBid;
	}
	
	public void setHighBid(Bid highBid) {
		this.highBid = highBid;
	}

}
