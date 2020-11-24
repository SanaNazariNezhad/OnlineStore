public class MetaData{

	@SerializedName("description")
	private String description;

	@SerializedName("type")
	private String type;

	@SerializedName("items")
	private Items items;

	@SerializedName("required")
	private boolean required;

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
