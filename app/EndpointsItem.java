public class EndpointsItem{

	@SerializedName("args")
	private Args args;

	@SerializedName("methods")
	private List<String> methods;

	public Args getArgs(){
		return args;
	}

	public List<String> getMethods(){
		return methods;
	}
}
