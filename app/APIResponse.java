public class APIResponse{

	@SerializedName("routes")
	private Routes routes;

	@SerializedName("_links")
	private Links links;

	@SerializedName("namespace")
	private String namespace;

	public Routes getRoutes(){
		return routes;
	}

	public Links getLinks(){
		return links;
	}

	public String getNamespace(){
		return namespace;
	}
}
