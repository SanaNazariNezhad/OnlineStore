public class WcV3CustomersPIdD{

	@SerializedName("endpoints")
	private List<EndpointsItem> endpoints;

	@SerializedName("methods")
	private List<String> methods;

	@SerializedName("namespace")
	private String namespace;

	public List<EndpointsItem> getEndpoints(){
		return endpoints;
	}

	public List<String> getMethods(){
		return methods;
	}

	public String getNamespace(){
		return namespace;
	}
}
