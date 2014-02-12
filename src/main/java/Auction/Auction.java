package Auction;

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
		if (!isValidBidAmount(bid)) return false;
		set_currentBid(bid);
		set_currentBidder(bidder);
		return true;
	}
	
	public boolean isValidBidAmount(float bid)
	{
		if ( bid > _currentBid) return true;
		else return false;
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

	public AuctionState get_state() {
		return _state;
	}

	public void set_state(AuctionState _state) {
		this._state = _state;
	}

	public Auction(String userName)
	{
		_userName = userName;
	}

	

}