package test;

import org.junit.Assert;
import org.junit.Test;

import Auction.Auction;
import Auction.ItemCondition;

public class AuctionTests {

	private static String seller = "MrSeller";
	
	@Test
	public void validateSellerCannotBidOnOwnItem() 
	{
		Auction auction = new Auction(seller);
		Assert.assertFalse(auction.IsValidBidder(seller));
	}
	
	@Test
	public void validateBidderCanBidOnItem()
	{
		String bidder = "MrBidder";	
		Auction auction = new Auction(seller);
		Assert.assertTrue(auction.IsValidBidder(bidder));
	}
	
	@Test
	public void validateAuctionDescriptionEditDeniedByNonOwner()
	{	
		String notSeller = "NotMrSeller";
		Auction auction = new Auction(seller);
		Assert.assertFalse(auction.modifyAuctionDescription("New description", notSeller));
	}
	
	@Test
	public void validateAuctionDescriptionEditAllowedByOwner()
	{
		
		Auction auction = new Auction(seller);
		Assert.assertTrue(auction.modifyAuctionDescription("New description", seller));
	}
	
	@Test
	public void validateAuctionEditPossibleOnPending()
	{
		
		Auction auction = new Auction(seller);
		Assert.assertTrue(auction.isEditable());
	}
	
	@Test
	public void validateauctionEditPossibleOnOpen()
	{
		
		Auction auction = new Auction(seller);
		auction.open_auction();
		Assert.assertTrue(auction.isEditable());
	}
	
	@Test
	public void validateauctionEditPossibleOnClosed()
	{
		
		Auction auction = new Auction(seller);
		auction.close_auction();
		Assert.assertFalse(auction.isEditable());
	}
	
	@Test 
	public void validateModifyAuctionPrimaryFieldsOnPendingState()
	{
		
		String newDesc = "NewDescription";
		int newQty = 10;
		ItemCondition newCond = ItemCondition.USEDDAMAGED;
		int minBid = 5;
		
		Auction auction = new Auction(seller);
		
		boolean result = auction.modifyPrimaryFields(newDesc, newQty, newCond, minBid);
		Assert.assertTrue(result);
	}
	
	@Test
	public void validateModifyAuctionPrimaryFieldsOnOpenState()
	{
		
		String newDesc = "NewDescription";
		int newQty = 10;
		ItemCondition newCond = ItemCondition.USEDDAMAGED;
		int minBid = 5;
		
		Auction auction = new Auction(seller);
		auction.open_auction();
		
		boolean result = auction.modifyPrimaryFields(newDesc, newQty, newCond, minBid);
		Assert.assertFalse(result);
	}
	
	@Test
	public void validateModifyAuctionPrimaryFieldsOnClosedState()
	{
		
		String newDesc = "NewDescription";
		int newQty = 10;
		ItemCondition newCond = ItemCondition.USEDDAMAGED;
		int minBid = 5;
		
		Auction auction = new Auction(seller);
		auction.close_auction();
		
		boolean result = auction.modifyPrimaryFields(newDesc, newQty, newCond, minBid);
		Assert.assertFalse(result);
	}
	
		
	@Test 	
	public void validateAddNewHighestBid() 	
	{ 	
		
		Auction auction = new Auction(seller);
		auction.open_auction();
		String bidder = "MrBidder"; 		
		float bid = 10; 		
		Assert.assertTrue(auction.trySubmitBid(bidder, bid)); 	
	}
	
	@Test
	public void validateDenyAdditionOfBidLowerThanHighestBid()
	{
		
		String bidder = "MrBidder";
		Auction auction = new Auction(seller);
		auction.open_auction();
		auction.set_currentBid(10);
		float newBid = 8;
		Assert.assertFalse(auction.trySubmitBid(bidder, newBid));
	}
	
	@Test
	public void validateDenyAdditionOfBidByOwner()
	{
		
		String bidder = seller;
		Auction auction = new Auction(seller);
		auction.open_auction();
		float bid = 10;
		Assert.assertFalse(auction.trySubmitBid(bidder, bid));
	}
	
	@Test
	public void validateDenyAdditionOfBidOnClosedAuction()
	{
		
		String bidder = "MrBidder";
		float bid = 10;
		Auction auction = new Auction(seller);
		auction.close_auction();
		Assert.assertFalse(auction.trySubmitBid(bidder, bid));
	}
	
	@Test
	public void validateAddValidBidOnOpenAuction()
	{
		
		String bidder = "MrBidder";
		float bid = 10;
		Auction auction = new Auction(seller);
		auction.open_auction();
		Assert.assertTrue(auction.trySubmitBid(bidder, bid));
	}
	
	@Test
	public void validateAuctionEndedReserveNotMet()
	{
		
		String bidder = "MrBidder";
		float bid = 4;
		float reserve = 5;
		Auction auction = new Auction(seller);
		auction.open_auction();
		auction.trySubmitBid(bidder, bid);
		auction.close_auction();
		auction.set_reserve(reserve);
		Assert.assertFalse(auction.wasSold());
	}
	
	@Test
	public void validateAuctionEndedReserveMet()
	{
		
		String bidder = "MrBidder";
		float bid = 4;
		float reserve = 3;
		Auction auction = new Auction(seller);
		auction.open_auction();
		auction.trySubmitBid(bidder, bid);
		auction.close_auction();
		auction.set_reserve(reserve);
		Assert.assertTrue(auction.wasSold());
	}

	@Test
	public void validateAuctionWinner()
	{
		String bidder = "MrBidder";
		float bid = 4;
		Auction auction = new Auction(seller);
		auction.open_auction();
		if (auction.trySubmitBid(bidder, bid))
		{
			auction.close_auction();
			Assert.assertTrue(auction.get_auction_winner() == bidder);
		}
		else
			Assert.fail("Auction is not open for bidding");
	}
	
	@Test
	public void validateProperWinner()
	{
		
		String bidder = "MrBidder";
		String losingBidder = "MrRandomBidder";
		float bid = 4;
		Auction auction = new Auction(seller);
		auction.open_auction();
		if (auction.trySubmitBid(bidder, bid))
		{
			auction.close_auction();
			Assert.assertFalse(auction.get_auction_winner() == losingBidder);
		}
		else
			Assert.fail("Auction is not open for bidding");
	}
	
	@Test
	public void validateNoAuctionWinner()
	{
		
		Auction auction = new Auction(seller);
		Assert.assertTrue(auction.get_auction_winner() == null);
	}
	
	@Test
	public void validateAuctionIsNotOpenForBid()
	{
		
		String bidder = "MrBidder";
		float bid = 4;
		Auction auction = new Auction(seller);
		Assert.assertFalse(auction.trySubmitBid(bidder, bid));
	}
	
	@Test
	public void validateAuctionIsOpenForBid()
	{
		
		String bidder = "MrBidder";
		float bid = 4;
		Auction auction = new Auction(seller);
		auction.open_auction();
		Assert.assertTrue(auction.trySubmitBid(bidder, bid));
	}
	
	@Test
	public void validateCantBidBelowMinimumBid()
	{
		
		String bidder = "MrBidder";
		float minimumBid = 5;
		float bid = 4;
		Auction auction = new Auction(seller);
		auction.set_minimumBid(minimumBid);
		auction.open_auction();
		Assert.assertFalse(auction.trySubmitBid(bidder, bid));
	}
	
	@Test
	public void validateDontAllowSetBuyItNowPriceBelowReserve()
	{
		
		float buyItNowPrice = 10;
		float reservePrice = 20;
		
		Auction auction = new Auction(seller);
		auction.set_reserve(reservePrice);
		
		Assert.assertFalse(auction.setNewbuyItNowPrice(buyItNowPrice));
	}
	
	@Test
	public void validateAllowSetBuyItNowPriceAboveReserve()
	{
		
		float buyItNowPrice = 20;
		float reservePrice = 10;
		
		Auction auction = new Auction(seller);
		auction.set_reserve(reservePrice);
		
		Assert.assertTrue(auction.setNewbuyItNowPrice(buyItNowPrice));
	}
	
	@Test
	public void validateCanBidAboveMinimumBid()
	{
		
		String bidder = "MrBidder";
		float minimumBid = 5;
		float bid = 6;
		Auction auction = new Auction(seller);
		auction.set_minimumBid(minimumBid);
		auction.open_auction();
		Assert.assertTrue(auction.trySubmitBid(bidder, bid));
	}
	
	@Test
	public void validateBuyItNowBid()
	{
		String bidder = "MrBidder";
		float buyItNowPrice = 30;
		Auction auction = new Auction(seller);
		auction.set_buyItNowPrice(buyItNowPrice);
		auction.open_auction();
		auction.buyItNow(bidder);
		Assert.assertTrue(auction.get_auction_winner() == bidder);
		Assert.assertTrue(auction.get_currentBid() == buyItNowPrice);
	}
}
