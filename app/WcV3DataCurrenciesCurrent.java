public class WcV3DataCurrenciesCurrent{

	@SerializedName("endpoints")
	private List<EndpointsItem> endpoints;

	@SerializedName("_links")
	private Links links;

	@SerializedName("methods")
	private List<String> methods;

	@SerializedName("namespace")
	private String namespace;

	public List<EndpointsItem> getEndpoints(){
		return endpoints;
	}

	public Links getLinks(){
		return links;
	}

	public List<String> getMethods(){
		return methods;
	}

	public String getNamespace(){
		return namespace;
	}
}
