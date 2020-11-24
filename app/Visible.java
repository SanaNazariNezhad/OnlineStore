public class Visible{

	@SerializedName("default")
	private boolean jsonMemberDefault;

	@SerializedName("context")
	private List<String> context;

	@SerializedName("description")
	private String description;

	@SerializedName("type")
	private String type;

	public boolean isJsonMemberDefault(){
		return jsonMemberDefault;
	}

	public List<String> getContext(){
		return context;
	}

	public String getDescription(){
		return description;
	}

	public String getType(){
		return type;
	}
}
