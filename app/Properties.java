public class Properties{

	@SerializedName("name")
	private Name name;

	@SerializedName("id")
	private Id id;

	@SerializedName("option")
	private Option option;

	@SerializedName("value")
	private Value value;

	@SerializedName("key")
	private Key key;

	@SerializedName("file")
	private File file;

	public Name getName(){
		return name;
	}

	public Id getId(){
		return id;
	}

	public Option getOption(){
		return option;
	}

	public Value getValue(){
		return value;
	}

	public Key getKey(){
		return key;
	}

	public File getFile(){
		return file;
	}
}
