package test;

import org.junit.Assert;
import org.junit.Test;

import Auction.Auction;
import Auction.AuctionState;

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
	public void validateAddNewHighestBid() 	
	{ 	
		String seller = "MrSeller";
		Auction auction = new Auction(seller); 		
		String bidder = "MrBidder"; 		
		float bid = 10; 		
		Assert.assertTrue(auction.trySubmitBid(bidder, bid)); 	
	}
	
	@Test
	public void validateDenyAdditionOfBidLowerThanHighestBid()
	{
		String seller = "MrSeller";
		String bidder = "MrBidder";
		Auction auction = new Auction(seller);
		auction.set_currentBid(10);
		float newBid = 8;
		Assert.assertFalse(auction.trySubmitBid(bidder, newBid));
	}
	
	@Test
	public void validateDenyAdditionOfBidByOwner()
	{
		String seller = "MrSeller";
		String bidder = seller;
		Auction auction = new Auction(seller);
		float bid = 10;
		Assert.assertFalse(auction.trySubmitBid(bidder, bid));
	}
	
	@Test
	public void validateDenyAdditionOfBidOnClosedAuction()
	{
		String seller = "MrSeller";
		String bidder = "MrBidder";
		float bid = 10;
		Auction auction = new Auction(seller);
		auction.set_state(AuctionState.CLOSED);
		Assert.assertFalse(auction.trySubmitBid(bidder, bid));
	}
	
	@Test
	public void validateAddValidBidOnOpenAuction()
	{
		String seller = "MrSeller";
		String bidder = "MrBidder";
		float bid = 10;
		Auction auction = new Auction(seller);
		auction.set_state(AuctionState.OPEN);
		Assert.assertTrue(auction.trySubmitBid(bidder, bid));
	}
	
	
}
