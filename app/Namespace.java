public class Namespace{

	@SerializedName("default")
	private String jsonMemberDefault;

	@SerializedName("required")
	private boolean required;

	public String getJsonMemberDefault(){
		return jsonMemberDefault;
	}

	public boolean isRequired(){
		return required;
	}
}
