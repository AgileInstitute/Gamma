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
	
	public boolean IsValidBidder(String bidder)
	{
		if (bidder == null)
			return false;
		return !_userName.equalsIgnoreCase(bidder);
	}
	
	public void modifyAuctionDescription(String description)
	{
		
	}
	
}