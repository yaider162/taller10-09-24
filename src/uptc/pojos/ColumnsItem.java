package uptc.pojos;

import lombok.Getter;

import java.util.List;

@Getter
public class ColumnsItem{
	private String renderTypeName;
	private ComputationStrategy computationStrategy;
	private String fieldName;
	private String dataTypeName;
	private int tableColumnId;
	private String name;
	private Format format;
	private String description;
	private int id;
	private int position;
	private List<String> flags;

}
