package mediateca.app.works;

import java.util.List;
import mediateca.core.Library;
import pt.tecnico.uilib.menus.Command;

/**
 * Command to perform search according to miscellaneous criteria.
 */
public class DoPerformSearch extends Command<Library> {

	/**
	 * @param m
	 */
	public DoPerformSearch(Library m) {
		super(Label.PERFORM_SEARCH, m);
		addStringField("searchTerm", Message.requestSearchTerm());
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() {
		String keyword = stringField("searchTerm").toLowerCase();    //ma, Ma, mA, MA
		List<String> resultList = _receiver.performSearch(keyword);
		  for(String s: resultList) {
			  _display.addLine(s);
		  }
	    _display.display();
		}

}
