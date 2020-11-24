public class Subtotal{

	@SerializedName("readonly")
	private boolean readonly;

	@SerializedName("context")
	private List<String> context;

	@SerializedName("description")
	private String description;

	@SerializedName("type")
	private String type;

	public boolean isReadonly(){
		return readonly;
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
