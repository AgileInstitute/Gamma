package test;

import org.junit.Assert;
import org.junit.Test;

import Auction.Auction;
import Auction.AuctionState;
import Auction.ItemCondition;

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
	public void validateAuctionEditPossibleOnPending()
	{
		String seller = "MrSeller";
		Auction auction = new Auction(seller);
		auction.open_auction();
		Assert.assertTrue(auction.isEditable());
	}
	
	@Test
	public void validateauctionEditPossibleOnOpen()
	{
		String seller = "MrSeller";
		Auction auction = new Auction(seller);
		auction.open_auction();
		Assert.assertTrue(auction.isEditable());
	}
	
	@Test
	public void validateauctionEditPossibleOnClosed()
	{
		String seller = "MrSeller";
		Auction auction = new Auction(seller);
		auction.close_auction();
		Assert.assertFalse(auction.isEditable());
	}
	
	@Test 
	public void validateModifyAuctionPrimaryFieldsOnPendingState()
	{
		String seller = "MrSeller";
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
		String seller = "MrSeller";
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
		String seller = "MrSeller";
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
		String seller = "MrSeller";
		Auction auction = new Auction(seller);
		auction.open_auction();
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
		auction.close_auction();
		Assert.assertFalse(auction.trySubmitBid(bidder, bid));
	}
	
	@Test
	public void validateAddValidBidOnOpenAuction()
	{
		String seller = "MrSeller";
		String bidder = "MrBidder";
		float bid = 10;
		Auction auction = new Auction(seller);
		auction.open_auction();
		Assert.assertTrue(auction.trySubmitBid(bidder, bid));
	}
	
	@Test
	public void validateAuctionEndedReserveNotMet()
	{
		String seller = "MrSeller";
		String bidder = "MrBidder";
		float bid = 4;
		float reserve = 5;
		Auction auction = new Auction(seller);
		auction.trySubmitBid(bidder, bid);
		auction.close_auction();
		auction.set_reserve(reserve);
		Assert.assertFalse(auction.wasSold());
	}
	
	@Test
	public void validateAuctionEndedReserveMet()
	{
		String seller = "MrSeller";
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
		String seller = "MrSeller";
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
		String seller = "MrSeller";
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
		String seller = "MrSeller";
		Auction auction = new Auction(seller);
		Assert.assertTrue(auction.get_auction_winner() == null);
	}
	
	@Test
	public void validateAuctionIsNotOpenForBid()
	{
		String seller = "MrSeller";
		String bidder = "MrBidder";
		float bid = 4;
		Auction auction = new Auction(seller);
		Assert.assertFalse(auction.trySubmitBid(bidder, bid));
	}
	
	@Test
	public void validateAuctionIsOpenForBid()
	{
		String seller = "MrSeller";
		String bidder = "MrBidder";
		float bid = 4;
		Auction auction = new Auction(seller);
		auction.open_auction();
		Assert.assertTrue(auction.trySubmitBid(bidder, bid));
	}
	
	@Test
	public void validateCantBidBelowMinimumBid()
	{
		String seller = "MrSeller";
		String bidder = "MrBidder";
		float minimumBid = 5;
		float bid = 4;
		Auction auction = new Auction(seller);
		auction.set_minimumBid(minimumBid);
		Assert.assertFalse(auction.trySubmitBid(bidder, bid));
	}
	
	@Test
	public void validateDontAllowSetBuyItNowPriceBelowReserve()
	{
		String seller = "MrSeller";
		float buyItNowPrice = 10;
		float reservePrice = 20;
		
		Auction auction = new Auction(seller);
		auction.set_reserve(reservePrice);
		
		Assert.assertFalse(auction.setNewbuyItNowPrice(buyItNowPrice));
	}
	
	@Test
	public void validateAllowSetBuyItNowPriceAboveReserve()
	{
		String seller = "MrSeller";
		float buyItNowPrice = 20;
		float reservePrice = 10;
		
		Auction auction = new Auction(seller);
		auction.set_reserve(reservePrice);
		
		Assert.assertTrue(auction.setNewbuyItNowPrice(buyItNowPrice));
	}
	
	@Test
	public void validateCanBidAboveMinimumBid()
	{
		String seller = "MrSeller";
		String bidder = "MrBidder";
		float minimumBid = 5;
		float bid = 6;
		Auction auction = new Auction(seller);
		auction.set_minimumBid(minimumBid);
		auction.open_auction();
		Assert.assertTrue(auction.trySubmitBid(bidder, bid));
	}
}
