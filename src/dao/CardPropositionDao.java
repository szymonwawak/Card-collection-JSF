package dao;

import entities.CardProposition;

import javax.ejb.Stateless;

@Stateless
public class CardPropositionDao extends BasicDao<CardProposition> {

    @Override
    protected Class<CardProposition> getRecordClass() {
        return CardProposition.class;
    }

    @Override
    protected String getClassName() {
        return "CardProposition";
    }
}
