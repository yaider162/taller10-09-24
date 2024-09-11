package uptc.pojos;

import lombok.Getter;

import java.util.List;

@Getter
public class GrantsItem{
	private boolean inherited;
	private List<String> flags;
	private String type;

}