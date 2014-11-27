package www.christuniversity.in;

import java.util.List;

public class User {

	private List<List<String>> totalTokensList;
	private List<List<String>> journalsList;
	private List<String> keysList;
	private List<String> valuesList;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<List<String>> getTotalTokensList() {
		return totalTokensList;
	}

	public void setTotalTokensList(List<List<String>> totalTokensList) {
		this.totalTokensList = totalTokensList;
	}

	public List<String> getKeysList() {
		return keysList;
	}

	public void setKeysList(List<String> keysList) {
		this.keysList = keysList;
	}

	public List<String> getValuesList() {
		return valuesList;
	}

	public void setValuesList(List<String> valuesList) {
		this.valuesList = valuesList;
	}

	public List<List<String>> getJournalsList() {
		return journalsList;
	}

	public void setJournalsList(List<List<String>> journalsList) {
		this.journalsList = journalsList;
	}

}
