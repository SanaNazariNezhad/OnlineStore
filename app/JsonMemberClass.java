public class JsonMemberClass{

	@SerializedName("description")
	private String description;

	@SerializedName("type")
	private String type;

	@SerializedName("required")
	private boolean required;

	@SerializedName("enum")
	private List<String> jsonMemberEnum;

	public String getDescription(){
		return description;
	}

	public String getType(){
		return type;
	}

	public boolean isRequired(){
		return required;
	}

	public List<String> getJsonMemberEnum(){
		return jsonMemberEnum;
	}
}
