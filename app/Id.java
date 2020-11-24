public class Id{

	@SerializedName("description")
	private String description;

	@SerializedName("type")
	private String type;

	@SerializedName("required")
	private boolean required;

	@SerializedName("context")
	private List<String> context;

	@SerializedName("readonly")
	private boolean readonly;

	public String getDescription(){
		return description;
	}

	public String getType(){
		return type;
	}

	public boolean isRequired(){
		return required;
	}

	public List<String> getContext(){
		return context;
	}

	public boolean isReadonly(){
		return readonly;
	}
}
