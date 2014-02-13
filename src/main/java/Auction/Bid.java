package Auction;

public class Bid {
	private String _user;
	private float _bid;
	private float _maxBid;
	private float _increment;
	
	public Bid(String user, float bid)
	{
		set_user(user);
		set_bid(bid);
		set_maxBid(0);
		set_increment(0);
	}
	
	public String get_user() {
		return _user;
	}
	public void set_user(String _user) {
		this._user = _user;
	}
	public float get_bid() {
		return _bid;
	}
	public void set_bid(float _bid) {
		this._bid = _bid;
	}
	public float get_maxBid() {
		return _maxBid;
	}
	public void set_maxBid(float _maxBid) {
		this._maxBid = _maxBid;
	}
	public float get_increment() {
		return _increment;
	}
	public void set_increment(float _increment) {
		this._increment = _increment;
	}
}
