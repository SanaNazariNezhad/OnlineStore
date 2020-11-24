public class DateCreated{

	@SerializedName("description")
	private String description;

	@SerializedName("type")
	private List<String> type;

	@SerializedName("required")
	private boolean required;

	@SerializedName("readonly")
	private boolean readonly;

	@SerializedName("context")
	private List<String> context;

	public String getDescription(){
		return description;
	}

	public List<String> getType(){
		return type;
	}

	public boolean isRequired(){
		return required;
	}

	public boolean isReadonly(){
		return readonly;
	}

	public List<String> getContext(){
		return context;
	}
}
