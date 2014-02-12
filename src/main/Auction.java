package main;

public class Auction
{
	String _userName;
	
	public Auction(String userName)
	{
		_userName = userName;
	}
	
	public boolean IsValidBidder(String bidder)
	{
		if (bidder == null)
			return false;
		return !_userName.equalsIgnoreCase(bidder);
	}
	
}