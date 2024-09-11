package uptc.pojos;

import lombok.Getter;

import java.util.List;

@Getter
public class Metadata{
	private CustomFields customFields;
	private String rowLabel;
	private List<String> availableDisplayTypes;

}