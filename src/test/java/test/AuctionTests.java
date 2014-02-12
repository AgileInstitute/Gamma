package test;

import org.junit.Assert;
import org.junit.Test;

import Auction.Auction;

public class AuctionTests {

	@Test
	public void validateSellerCannotBidOnOwnItem() 
	{
		String seller = "MrSeller";
		Auction auction = new Auction(seller);
		Assert.assertFalse(auction.IsValidBidder(seller));
	}
	
	@Test
	public void validateBidderCanBidOnItem()
	{
		String bidder = "MrBidder";
		String seller = "MrSeller";
		Auction auction = new Auction(seller);
		Assert.assertTrue(auction.IsValidBidder(bidder));
	}
	
	@Test
	public void validateAuctionDescriptionEditDeniedByNonOwner()
	{
		String seller = "MrSeller";
		String notSeller = "NotMrSeller";
		Auction auction = new Auction(seller);
		Assert.assertFalse(auction.modifyAuctionDescription("New description", notSeller));
	}
	
	@Test
	public void validateAuctionDescriptionEditAllowedByOwner()
	{
		String seller = "MrSeller";
		Auction auction = new Auction(seller);
		Assert.assertTrue(auction.modifyAuctionDescription("New description", seller));
	}
	@Test
	public void validateAddBid()
	{
		Auction auction = new Auction("MrSeller");
		String bidder = "MrBidder";
		float bid = 10;
		Assert.assertTrue(auction.trySubmitBid(bidder, bid));
	}

}
