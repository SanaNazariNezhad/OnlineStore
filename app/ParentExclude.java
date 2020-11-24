public class ParentExclude{

	@SerializedName("default")
	private List<Object> jsonMemberDefault;

	@SerializedName("description")
	private String description;

	@SerializedName("type")
	private String type;

	@SerializedName("items")
	private Items items;

	@SerializedName("required")
	private boolean required;

	public List<Object> getJsonMemberDefault(){
		return jsonMemberDefault;
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

	public boolean isRequired(){
		return required;
	}
}
