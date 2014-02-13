package Auction;

import java.util.HashMap;

public class Bids {

	HashMap<String, Bid> bids;
	Bid highBid;
	
	public Bids()
	{
		bids = new HashMap<String, Bid>();
		highBid = null;
	}
	
	
	public HashMap<String, Bid> getBids() {
		return bids;
	}
	
	public boolean addBid(Bid bid) {
		if (highBid == null || bid.get_bid() > highBid.get_bid())
		{
			bids.put(bid.get_user(), bid);
			highBid = bid;
			return true;
		}
		else
			return false;
	}
	
	public void removeBid(Bid bid){
		bids.remove(bid);
	}
	
	public Bid getHighBid() {
		return highBid;
	}
	
	public void setHighBid(Bid highBid) {
		this.highBid = highBid;
	}
	
}
