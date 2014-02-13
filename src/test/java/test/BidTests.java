package test;

import static org.junit.Assert.*;

import org.junit.Test;

import Auction.Bid;

public class BidTests 
{
	@Test
	public void testDenyCreateBidWithNoBidder() 
	{
		String bidder = null;
		float bidAmount = 10;
		Bid bid = Bid.createBid(bidder, bidAmount);
		assertTrue(bid == null);
	}
	
	@Test
	public void testDenyCreateBidWithZeroOrNegativeBidAmount()
	{
		String bidder = "MrBidder";
		float bidAmount = 0;
		Bid bid = Bid.createBid(bidder, bidAmount);
		assertTrue(bid==null);
	}

}
