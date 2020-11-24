public class Links{

	@SerializedName("up")
	private List<UpItem> up;

	@SerializedName("self")
	private List<SelfItem> self;

	public List<UpItem> getUp(){
		return up;
	}

	public List<SelfItem> getSelf(){
		return self;
	}
}
