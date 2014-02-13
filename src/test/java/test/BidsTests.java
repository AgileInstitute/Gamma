package test;

import org.junit.Assert;
import org.junit.Test;

import Auction.Bid;
import Auction.Bids;

public class BidsTests {
	
	@Test
	public void validateHighestBid()
	{
		Bid bidHigh = Bid.createBid("BidderHigh", 5);
		Bid bidLow = Bid.createBid("BidderLow", 4);
		Bids bids = new Bids();
		
		bids.addBid(bidLow);
		bids.addBid(bidHigh);
		
		Assert.assertTrue(bids.getHighBid().equals(bidHigh));
	}

	@Test
	public void validateNotHighestBid()
	{
		Bid bidHigh = Bid.createBid("BidderHigh", 5);
		Bid bidLow = Bid.createBid("BidderLow", 4);
		Bids bids = new Bids();
		
		bids.addBid(bidLow);
		bids.addBid(bidHigh);
		
		Assert.assertFalse(bids.getHighBid().equals(bidLow));
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
