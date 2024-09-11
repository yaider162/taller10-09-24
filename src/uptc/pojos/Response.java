package uptc.pojos;

import lombok.Getter;

import java.util.List;

@Getter
public class Response{
	private List<List<String>> data;
	private Meta meta;

}