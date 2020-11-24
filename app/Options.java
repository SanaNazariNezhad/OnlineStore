public class Options{

	@SerializedName("context")
	private List<String> context;

	@SerializedName("description")
	private String description;

	@SerializedName("type")
	private String type;

	@SerializedName("items")
	private Items items;

	public List<String> getContext(){
		return context;
	}

	public String getDescription(){
		return description;
	}

	public String getType(){
		return type;
	}

	public Items getItems(){
		return items;
	}
}
