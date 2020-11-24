public class DateOnSaleTo{

	@SerializedName("description")
	private String description;

	@SerializedName("type")
	private List<String> type;

	@SerializedName("required")
	private boolean required;

	public String getDescription(){
		return description;
	}

	public List<String> getType(){
		return type;
	}

	public boolean isRequired(){
		return required;
	}
}
