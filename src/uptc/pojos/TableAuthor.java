package uptc.pojos;

import lombok.Getter;

import java.util.List;

@Getter
public class TableAuthor{
	private String displayName;
	private String profileImageUrlLarge;
	private List<String> flags;
	private String profileImageUrlSmall;
	private String id;
	private String screenName;
	private String type;
	private String profileImageUrlMedium;

}