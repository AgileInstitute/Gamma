package test;

import org.junit.Assert;
import org.junit.Test;

import Auction.Auction;

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
	
	@Test
	public void validateAuctionDescriptionEditDeniedByNonOwner()
	{
		Auction auction = new Auction("MrSeller");
		Assert.assertFalse(auction.modifyAuctionDescription("New description", "NotMrSeller"));
	}
	
	@Test
	public void validateAuctionDescriptionEditAllowedByOwner()
	{
		Auction auction = new Auction("MrSeller");
		Assert.assertTrue(auction.modifyAuctionDescription("New description", "MrSeller"));
	}

}
