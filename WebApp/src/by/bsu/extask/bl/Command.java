package by.bsu.extask.bl;

import by.bsu.extask.controller.InDataWrapper;

public interface Command {
	public String execute(InDataWrapper inData) throws Exception;
}
