package uptc.pojos;

import lombok.Getter;

import java.util.List;

@Getter
public class View{
	private Metadata metadata;
	private int rowsUpdatedAt;
	private List<ColumnsItem> columns;
	private List<String> flags;
	private String description;
	private int oid;
	private int totalTimesRated;
	private int numberOfComments;
	private int createdAt;
	private String provenance;
	private boolean newBackend;
	private int averageRating;
	private List<ApprovalsItem> approvals;
	private List<String> rights;
	private String id;
	private int viewCount;
	private String licenseId;
	private boolean locked;
	private int publicationDate;
	private int publicationGroup;
	private ClientContext clientContext;
	private List<GrantsItem> grants;
	private Owner owner;
	private String publicationStage;
	private Query query;
	private String rowsUpdatedBy;
	private boolean hideFromCatalog;
	private boolean hideFromDataJson;
	private String assetType;
	private List<String> tags;
	private License license;
	private String displayType;
	private TableAuthor tableAuthor;
	private int viewLastModified;
	private String name;
	private String attribution;
	private String viewType;
	private int tableId;
	private String category;
	private int downloadCount;
	private boolean publicationAppendEnabled;

}