package test;

import org.junit.Assert;
import org.junit.Test;

import Auction.Bid;
import Auction.Bids;

public class BidsTests {
	
	private static String seller = "MrSeller";
	
	@Test
	public void validateHighestBid()
	{
		Bid bidHigh = Bid.createBid("BidderHigh", 5);
		Bid bidLow = Bid.createBid("BidderLow", 4);
		Bids bids = new Bids();
		
		bids.addBid(bidLow);
		bids.addBid(bidHigh);
		
		Assert.assertEquals(bidHigh, bids.getHighBid());
	}

	@Test
	public void validateNotHighestBid()
	{
		Bid bidHigh = Bid.createBid("BidderHigh", 5);
		Bid bidLow = Bid.createBid("BidderLow", 4);
		Bids bids = new Bids();
		
		bids.addBid(bidLow);
		bids.addBid(bidHigh);
		
		Assert.assertNotEquals(bidLow, bids.getHighBid());
	}
	
	@Test
	public void validateBidIsNotHigher()
	{
		Bid bidHigh = Bid.createBid("BidderHigh", 5);
		Bid bidLow = Bid.createBid("BidderLow", 4);
		Bids bids = new Bids();
		
		bids.addBid(bidHigh);
		
		Assert.assertFalse(bids.addBid(bidLow));
		
	}
}
