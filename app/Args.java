public class Args{

	@SerializedName("menu_order")
	private MenuOrder menuOrder;

	@SerializedName("attribute_id")
	private AttributeId attributeId;

	@SerializedName("name")
	private Name name;

	@SerializedName("description")
	private Description description;

	@SerializedName("slug")
	private Slug slug;

	@SerializedName("per_page")
	private PerPage perPage;

	@SerializedName("include")
	private Include include;

	@SerializedName("hide_empty")
	private HideEmpty hideEmpty;

	@SerializedName("parent")
	private Parent parent;

	@SerializedName("product")
	private Product product;

	@SerializedName("orderby")
	private Orderby orderby;

	@SerializedName("search")
	private Search search;

	@SerializedName("context")
	private Context context;

	@SerializedName("exclude")
	private Exclude exclude;

	@SerializedName("page")
	private Page page;

	@SerializedName("order")
	private Order order;

	public MenuOrder getMenuOrder(){
		return menuOrder;
	}

	public AttributeId getAttributeId(){
		return attributeId;
	}

	public Name getName(){
		return name;
	}

	public Description getDescription(){
		return description;
	}

	public Slug getSlug(){
		return slug;
	}

	public PerPage getPerPage(){
		return perPage;
	}

	public Include getInclude(){
		return include;
	}

	public HideEmpty getHideEmpty(){
		return hideEmpty;
	}

	public Parent getParent(){
		return parent;
	}

	public Product getProduct(){
		return product;
	}

	public Orderby getOrderby(){
		return orderby;
	}

	public Search getSearch(){
		return search;
	}

	public Context getContext(){
		return context;
	}

	public Exclude getExclude(){
		return exclude;
	}

	public Page getPage(){
		return page;
	}

	public Order getOrder(){
		return order;
	}
}
