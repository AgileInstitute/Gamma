package test;

import static org.junit.Assert.*;
import junit.framework.Assert;
import Auction.Auction;

import org.junit.Test;

public class AuctionTests {

	@Test
	public void validateSellerCannotBidOnOwnItem() 
	{
		Auction auction = new Auction("MrSeller");
		String bidder = "MrSeller";
		Assert.assertFalse(auction.IsValidBidder(bidder));
	}
	
	@Test
	public void validateSellerCanBidOnItem()
	{
		Auction auction = new Auction("MrSeller");
		String bidder = "MrBidder";
		Assert.assertTrue(auction.IsValidBidder(bidder));
	}

}
