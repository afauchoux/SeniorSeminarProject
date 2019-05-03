package com.example.seniorseminarproject;

public class Reward {

    String rewardId;
    String rewardName;
    String rewardCost;
    String rewardDescription;

    public Reward(){

    }

    public Reward(String rewardId, String rewardName, String rewardCost, String rewardDescription) {
        this.rewardId = rewardId;
        this.rewardName = rewardName;
        this.rewardCost = rewardCost;
        this.rewardDescription = rewardDescription;
    }

    public String getRewardId() {
        return rewardId;
    }

    public void setRewardId(String rewardId) {
        this.rewardId = rewardId;
    }

    public String getRewardName() {
        return rewardName;
    }

    public void setRewardName(String rewardName) {
        this.rewardName = rewardName;
    }

    public String getRewardCost() {
        return rewardCost;
    }

    public void setRewardCost(String rewardCost) {
        this.rewardCost = rewardCost;
    }

    public String getRewardDescription() {
        return rewardDescription;
    }

    public void setRewardDescription(String rewardDescription) {
        this.rewardDescription = rewardDescription;
    }
}
