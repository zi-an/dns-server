package cn.dns.service;

import cn.dns.dao.RuleMapper;
import cn.dns.entity.Page;
import cn.dns.entity.Rule;
import cn.dns.entity.RuleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by matrixy on 2019/4/28.
 */
@Service
public class RuleService
{
    @Autowired
    RuleMapper ruleMapper;

    public Long create(Rule rule)
    {
        ruleMapper.insert(rule);
        return rule.getId();
    }

    public int update(Rule rule)
    {
        return ruleMapper.updateByPrimaryKey(rule);
    }

    public int remove(Rule rule)
    {
        return remove(rule.getId());
    }

    public int remove(Long id)
    {
        return ruleMapper.deleteByPrimaryKey(id);
    }

    public Rule getById(Long id)
    {
        return ruleMapper.selectByPrimaryKey(id);
    }

    public Page<Rule> find(int pageIndex, int pageSize)
    {
        Page<Rule> page = new Page<Rule>(pageIndex, pageSize);
        RuleExample.Criteria criteria = new RuleExample().createCriteria();
        criteria.example().setOrderByClause(Rule.Column.id.desc());
        criteria.example().setPageInfo(pageIndex, pageSize);
        page.setList(ruleMapper.selectByExample(criteria.example()));
        page.setRecordCount(ruleMapper.countByExample(criteria.example()));
        return page;
    }
}
