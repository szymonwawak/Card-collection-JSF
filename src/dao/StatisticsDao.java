package dao;

import entities.Statistics;

import javax.ejb.Stateless;

@Stateless
public class StatisticsDao extends BasicDao<Statistics> {
    @Override
    protected Class<Statistics> getRecordClass() {
        return Statistics.class;
    }

    @Override
    protected String getClassName() {
        return "Statistics";
    }
}
