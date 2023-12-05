package cn.edu.jnu.supershopper.data;

public class QuestItem {
    public String title;//标题
    public int score;//成就点数
    //    public int quantityCompleted;
//    public int totalQuantity;
    public String tag;//标签
    public int type;//类型，0|每日任务，1|每周任务，2|普通任务，3|副本任务
//    public QuestItem(String title, int score, int quantityCompleted, int totalQuantity) {
//        this.title = title;
//        this.score = score;
//        this.quantityCompleted = quantityCompleted;
//        this.totalQuantity = totalQuantity;
//    }

    public QuestItem(String title, int score, String tag) {
        this.title = title;
        this.score = score;
        this.tag = tag;
    }

    public QuestItem(String title, int score, String tag, int type) {
        this.title = title;
        this.score = score;
        this.tag = tag;
        this.type = type;
    }
}
