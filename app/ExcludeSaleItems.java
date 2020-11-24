public class ExcludeSaleItems{

	@SerializedName("default")
	private boolean jsonMemberDefault;

	@SerializedName("description")
	private String description;

	@SerializedName("type")
	private String type;

	@SerializedName("required")
	private boolean required;

	public boolean isJsonMemberDefault(){
		return jsonMemberDefault;
	}

	public String getDescription(){
		return description;
	}

	public String getType(){
		return type;
	}

	public boolean isRequired(){
		return required;
	}
}
