package cn.edu.jnu.supershopper.data;

public class RewardsItem {
    public String title;//标题
    public int score;//消耗成就点数
    public String tag;//标签

    public RewardsItem(String title, int score, String tag) {
        this.title = title;
        this.score = score;
        this.tag = tag;
    }
}
