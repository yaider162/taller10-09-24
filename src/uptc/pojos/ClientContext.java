package uptc.pojos;

import lombok.Getter;

import java.util.List;

@Getter
public class ClientContext{
	private InheritedVariables inheritedVariables;
	private List<Object> clientContextVariables;

}