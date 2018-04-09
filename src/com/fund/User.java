package com.fund;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {

	private int id;
	private String name;

	private ProteinData proteinData;

	// private Set<UserHistory> history = new HashSet<>();
	private List<UserHistory> history = new ArrayList<>();
	// private Map<String,UserHistory> history = new HashMap<>();
	// private Collection<UserHistory> history = new ArrayList<>();

	private Set<GoalAlert> goalAlerts = new HashSet<>();
	
	public User() {

		setProteinData(new ProteinData());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProteinData getProteinData() {
		return proteinData;
	}

	// sets the relationship bw user and protein
	public void setProteinData(ProteinData proteinData) {
		this.proteinData = proteinData;
		proteinData.setUser(this);
	}

	public List<UserHistory> getHistory() {
		return history;
	}

	public void setHistory(List<UserHistory> history) {
		this.history = history;
	}

	// sets the bi-directional relationship
	public void addHistory(UserHistory historyItem) {

		historyItem.setUser(this);
		history.add(historyItem);

	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", total=" + getProteinData().getTotal() + ", goal="
				+ getProteinData().getGoal() + "]";
	}

	public Set<GoalAlert> getGoalAlerts() {
		return goalAlerts;
	}

	public void setGoalAlerts(Set<GoalAlert> goalAlert) {
		this.goalAlerts = goalAlert;
	}

	/*public GoalAlert getGoalAlert() {
		return goalAlert;
	}

	public void setGoalAlert(GoalAlert goalAlert) {
		this.goalAlert = goalAlert;
	}*/
}
