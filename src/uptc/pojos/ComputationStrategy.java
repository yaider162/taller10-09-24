package uptc.pojos;

import lombok.Getter;

import java.util.List;

@Getter
public class ComputationStrategy{
	private List<String> sourceColumns;
	private String type;
	private Parameters parameters;

}