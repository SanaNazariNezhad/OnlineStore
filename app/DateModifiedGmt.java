public class DateModifiedGmt{

	@SerializedName("readonly")
	private boolean readonly;

	@SerializedName("context")
	private List<String> context;

	@SerializedName("description")
	private String description;

	@SerializedName("type")
	private List<String> type;

	public boolean isReadonly(){
		return readonly;
	}

	public List<String> getContext(){
		return context;
	}

	public String getDescription(){
		return description;
	}

	public List<String> getType(){
		return type;
	}
}
