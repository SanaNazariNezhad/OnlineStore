public class PurchaseNote{

	@SerializedName("description")
	private String description;

	@SerializedName("type")
	private String type;

	@SerializedName("required")
	private boolean required;

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
