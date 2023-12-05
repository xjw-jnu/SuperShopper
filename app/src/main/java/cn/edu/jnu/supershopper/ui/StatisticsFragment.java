package cn.edu.jnu.supershopper.ui;

import java.util.ArrayList;
import java.util.List;

import cn.edu.jnu.supershopper.R;
import cn.edu.jnu.supershopper.view.BrokeLineView;

public class StatisticsFragment extends BaseFragment {
    private BrokeLineView brokeLineView;
    private List<Float> yValues = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_statistics;
    }

    @Override
    protected void initView() {

        brokeLineView = (BrokeLineView)getView().findViewById(R.id.brokeLineView);

        yValues.add(0f);
        yValues.add(14f);
        yValues.add(8f);
        yValues.add(12f);
        yValues.add(7f);
        yValues.add(9f);
        yValues.add(16f);
        yValues.add(0f);
        yValues.add(14f);
        yValues.add(8f);
        yValues.add(12f);
        yValues.add(7f);
        yValues.add(9f);
        yValues.add(16f);
        yValues.add(0f);
        yValues.add(14f);
        yValues.add(8f);
        yValues.add(12f);
        yValues.add(7f);
        yValues.add(9f);
        yValues.add(16f);
        yValues.add(0f);
        yValues.add(14f);
        yValues.add(8f);
        yValues.add(12f);
        yValues.add(7f);
        yValues.add(9f);
        yValues.add(16f);
        yValues.add(0f);
        yValues.add(14f);
        yValues.add(8f);
        yValues.add(12f);
        yValues.add(7f);
        yValues.add(9f);
        yValues.add(16f);
        brokeLineView.setYValues(yValues);
    }
}
