public class Src{

	@SerializedName("format")
	private String format;

	@SerializedName("context")
	private List<String> context;

	@SerializedName("description")
	private String description;

	@SerializedName("type")
	private String type;

	public String getFormat(){
		return format;
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
