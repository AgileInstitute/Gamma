package Auction;

import java.util.ArrayList;
import java.util.List;

public class Auction
{
	String _userName;
	String _description;
	ItemCondition _condition;
	int _quantity;
	float _minimumBid;
	String _itemLocation;
	AuctionState _state;
	float _currentBid;
	String _currentBidder;
	float _reserve;
	float _buyItNowPrice;
	

	public float get_buyItNowPrice() {
		return _buyItNowPrice;
	}
	
	public void set_buyItNowPrice(float buyItNowPrice) 
	{
		_buyItNowPrice = buyItNowPrice;
	}

	public float get_reserve() {
		return _reserve;
	}

	public void set_reserve(float _reserve) {
		this._reserve = _reserve;
	}

	public String get_currentBidder() {
		return _currentBidder;
	}

	public void set_currentBidder(String _currentBidder) {
		this._currentBidder = _currentBidder;
	}

	public boolean IsValidBidder(String bidder)
	{
		if (bidder == null)
			return false;
		return !_userName.equalsIgnoreCase(bidder);
	}
	
	public boolean IsValidOwner(String owner)
	{
		if (owner == null) return false;
		return !(IsValidBidder(owner));
	}
	
	public boolean modifyAuctionDescription(String description, String userName)
	{
		// returns true if edit was successful
		if (!IsValidOwner(userName))
		{
			return false;
		}
		else return true;
	}

	public boolean trySubmitBid(String bidder, float bid) 
	{
		if (!isValidBid(bidder, bid)) return false;
		if (bid < this.get_minimumBid()) return false;
		set_currentBid(bid);
		set_currentBidder(bidder);
		return true;
	}
	
	public boolean isValidBid(String bidder, float bid)
	{
		if (!isAuctionOpen()) return false;
		if (!IsValidBidder(bidder)) return false;
		if (!isValidBidAmount(bid)) return false;
		return true;
	}
	
	public boolean isValidBidAmount(float bid)
	{
		if ( bid > _currentBid) return true;
		else return false;
	}
	
	
	public boolean isEditable() 
	{
		if (_state == AuctionState.PENDING || _state == AuctionState.OPEN)
			return true;
		else return false;
	}

	public boolean modifyPrimaryFields(String newDesc, int newQty, ItemCondition newCond, int minBid) 
	{
		if (_state != AuctionState.PENDING) return false;
		set_description(newDesc);
		set_quantity(newQty);
		set_condition(newCond);
		set_minimumBid(minBid);
		return true;
	}

	public void buyItNow(String bidder)
	{
		set_currentBidder(bidder);
		set_currentBid(get_buyItNowPrice());
		close_auction();
	}
	
	public float get_currentBid() {
		return _currentBid;
	}

	public void set_currentBid(float _currentBid) {
		this._currentBid = _currentBid;
	}

	public String get_userName() { 
		return _userName;
	}

	public void set_userName(String _userName) {
		this._userName = _userName;
	}

	public String get_description() {
		return _description;
	}

	public void set_description(String _description) {
		this._description = _description;
	}

	public ItemCondition get_condition() {
		return _condition;
	}

	public void set_condition(ItemCondition _condition) {
		this._condition = _condition;
	}

	public int get_quantity() {
		return _quantity;
	}

	public void set_quantity(int _quantity) {
		this._quantity = _quantity;
	}

	public float get_minimumBid() {
		return _minimumBid;
	}

	public void set_minimumBid(float _minimumBid) {
		this._minimumBid = _minimumBid;
	}

	public String get_itemLocation() {
		return _itemLocation;
	}

	public void set_itemLocation(String _itemLocation) {
		this._itemLocation = _itemLocation;
	}

	private void set_state(AuctionState _state) {
		this._state = _state;
	}
	
	public void open_auction() {
		set_state(AuctionState.OPEN);
	}
	
	public void close_auction() {
		set_state(AuctionState.CLOSED);
	}
	
	public String get_auction_winner() {
		if (wasSold())
			return _currentBidder;
		else
			return null;
	}

	public Auction(String userName)
	{
		_userName = userName;
		_state = AuctionState.PENDING;
	}

	public boolean wasSold() {
		if((_state==AuctionState.CLOSED)&&(this.get_currentBid()>=this.get_reserve()))
				return true;
		return false;
	}
	
	public boolean isAuctionOpen()
	{
		return _state == AuctionState.OPEN;
	}
	
	public boolean isAuctionClosed()
	{
		return _state == AuctionState.CLOSED;
	}

	public boolean setNewbuyItNowPrice(float buyItNowPrice) 
	{
		if (buyItNowPrice <= get_reserve()) return false;
		set_buyItNowPrice(buyItNowPrice);
		return true;
	}



}